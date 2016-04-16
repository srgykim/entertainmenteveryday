package com.srgykim.entertainmenteveryday.data;

import com.srgykim.entertainmenteveryday.model.Article;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ArticleDAO acts as an interface to persistence layer to do CRUD operations with articles.
 */
@Component
public class ArticleDAO {

    private static final String GET_ALL_ARTICLES =
            "SELECT article_id, main_image_url, title, short_titled_id, publication_date, " +
            "article_content, article_extract, category_id, category_name, author_id, " +
            "CONCAT(first_name, ' ', last_name) AS author_full_name " +
            "FROM ARTICLES NATURAL JOIN CATEGORIES NATURAL JOIN AUTHORS";

    private static final String PUBLISH_ARTICLE =
            "INSERT INTO ARTICLES " +
            "(title, main_image_url, short_titled_id, publication_date, " +
            "article_content, article_extract, category_id, author_id) " +
            "VALUES (?,?,?,?,?,?,?,?)";

    @SuppressWarnings("SqlResolve")
    private static final String GET_LAST_ARTICLE_ID =
            "SELECT auto_increment " +
            "FROM  INFORMATION_SCHEMA.TABLES " +
            "WHERE TABLE_SCHEMA = 'entertainmenteveryday' " +
            "AND TABLE_NAME = 'ARTICLES'";

    private static final String GET_ARTICLE_BY_SHORT_TITLED_ID =
            "SELECT article_id, main_image_url, title, short_titled_id, publication_date, " +
            "article_content, article_extract, category_id, category_name, author_id, " +
            "CONCAT(first_name, ' ', last_name) AS author_full_name " +
            "FROM ARTICLES NATURAL JOIN CATEGORIES NATURAL JOIN AUTHORS " +
            "WHERE short_titled_id=?";

    private static final String PUBLISH_SLIDER_ARTICLE =
            "INSERT INTO SLIDER_ARTICLES VALUES (?)";

    private static final String GET_ALL_SLIDER_ARTICLES =
            "SELECT article_id, main_image_url, title, short_titled_id, publication_date, " +
            "article_content, article_extract, category_id, category_name, author_id, " +
            "CONCAT(first_name, ' ', last_name) AS author_full_name " +
            "FROM ARTICLES NATURAL JOIN CATEGORIES " +
            "NATURAL JOIN AUTHORS NATURAL JOIN SLIDER_ARTICLES";

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
                        results.getString("main_image_url"),
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
            statement.setString(2, article.getMainImageUrl());
            statement.setString(3, article.getShortTitledId());
            statement.setString(4, article.getPublicationDate());
            statement.setString(5, article.getArticleContent());
            statement.setString(6, article.getArticleExtract());
            statement.setInt(7, article.getCategoryId());
            statement.setString(8, article.getAuthorId());
            statement.executeUpdate();

        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public int getLastArticleId() {
        int last = -1;

        try(
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(GET_LAST_ARTICLE_ID)
        ) {
            results.next();
            last = results.getInt("auto_increment");
        } catch(SQLException se) {
            se.printStackTrace();
        }

        return last;
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
                        results.getString("main_image_url"),
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

    public void publishSliderArticle(Article sliderArticle) {
        try(
                PreparedStatement statement = connection.prepareStatement(PUBLISH_SLIDER_ARTICLE)
        ) {
            statement.setString(1, sliderArticle.getShortTitledId());
            statement.executeUpdate();
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public List<Article> getAllSliderArticles() {

        List<Article> articles = new ArrayList<>();

        try(
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(GET_ALL_SLIDER_ARTICLES)
        ) {
            while(results.next()) {
                articles.add(new Article(
                        results.getInt("article_id"),
                        results.getString("main_image_url"),
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
}
