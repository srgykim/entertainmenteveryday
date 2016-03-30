package com.srgykim.entertainmenteveryday.data;

import com.srgykim.entertainmenteveryday.model.Article;
import org.springframework.stereotype.Component;

import java.util.*;
import java.sql.*;

@Component
public class ArticleDAO {

    private static final String GET_ALL_ARTICLES =
            "SELECT article_id, title, short_titled_id, publication_date, " +
            "article_content, article_extract, category_id, category_name, author_id, " +
            "CONCAT(first_name, ' ', last_name) AS author_full_name " +
            "FROM ARTICLES NATURAL JOIN CATEGORIES NATURAL JOIN AUTHORS";

    private static final String PUBLISH_ARTICLE =
            "INSERT INTO ARTICLES " +
            "(title, short_titled_id, publication_date, " +
            "article_content, article_extract, category_id, author_id) " +
            "VALUES(?,?,?,?,?,?,?)";

    private static final String GET_LAST_ARTICLE_ID =
            "SELECT MAX(article_id) AS last_article_id FROM ARTICLES";

    private static final String GET_ARTICLE_BY_SHORT_TITLED_ID =
            "SELECT article_id, title, short_titled_id, publication_date, " +
            "article_content, article_extract, category_id, category_name, author_id, " +
            "CONCAT(first_name, ' ', last_name) AS author_full_name " +
            "FROM ARTICLES NATURAL JOIN CATEGORIES NATURAL JOIN AUTHORS " +
            "WHERE short_titled_id=?";


    private static Connection connection = SingletonConnection.getConnection();

    public ArticleDAO() {}

    public List<Article> getAllArticles() {

        List<Article> articles = new ArrayList<>();

        try(
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(GET_ALL_ARTICLES)
        ) {
            while(results.next()) {
                articles.add(new Article(
                        results.getInt("article_id"),
                        results.getString("title"),
                        results.getString("short_titled_id"),
                        results.getString("publication_date"),
                        results.getString("article_content"),
                        results.getString("article_extract"),
                        results.getInt("category_id"),
                        results.getString("category_name"),
                        results.getString("author_id"),
                        results.getString("author_full_name")));
            }
        } catch(SQLException se) {
            se.printStackTrace();
        }

        return articles;
    }

    public void publishArticle(Article article) {

        try(
                PreparedStatement statement = connection.prepareStatement(PUBLISH_ARTICLE)
        ) {
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getShortTitledId());
            statement.setString(3, article.getPublicationDate());
            statement.setString(4, article.getArticleContent());
            statement.setString(5, article.getArticleExtract());
            statement.setInt(6, article.getCategoryId());
            statement.setString(7, article.getAuthorId());
            statement.executeUpdate();

        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public int getLastArticleId() {

        int lastArticleId = 1;

        try(
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(GET_LAST_ARTICLE_ID)
        ) {
            results.next();
            if (results.getInt("last_article_id") == 0) {
                return lastArticleId;
            } else {
                lastArticleId += results.getInt("last_article_id");
            }
        } catch(SQLException se) {
            se.printStackTrace();
        }

        return lastArticleId;
    }

    public Article getArticleByShortTitledId(String shortTitledId) {

        Article article = new Article();

        try(
                PreparedStatement statement = connection.prepareStatement(GET_ARTICLE_BY_SHORT_TITLED_ID)
        ) {
            statement.setString(1, shortTitledId);
            try(ResultSet results = statement.executeQuery()) {
                results.next();
                article.setAllFields(
                        results.getInt("article_id"),
                        results.getString("title"),
                        results.getString("short_titled_id"),
                        results.getString("publication_date"),
                        results.getString("article_content"),
                        results.getString("article_extract"),
                        results.getInt("category_id"),
                        results.getString("category_name"),
                        results.getString("author_id"),
                        results.getString("author_full_name"));
            }

        } catch(SQLException se) {
            se.printStackTrace();
            return null;
        }

        return article;
    }
}
