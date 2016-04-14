package com.srgykim.entertainmenteveryday.data;

import com.srgykim.entertainmenteveryday.model.Author;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class AuthorDAO acts as an interface to persistence layer to do CRUD operations with authors.
 */
@Component
public class AuthorDAO {

    private static final String GET_AUTHOR_BY_ID =
            "SELECT * FROM AUTHORS WHERE author_id=?";


    private static Connection connection = SingletonConnection.getConnection();

    public AuthorDAO() {}

    public Author getAuthorById(String possible) {

        Author author = new Author();

        try(
                PreparedStatement statement = connection.prepareStatement(GET_AUTHOR_BY_ID)
        ) {
            statement.setString(1, possible);
            try(ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    author.setAllFields(
                            results.getString("author_id"),
                            results.getString("password"),
                            results.getString("first_name"),
                            results.getString("middle_name"),
                            results.getString("last_name"));
                } else {
                    return null;
                }
            }

        } catch(SQLException se) {
            se.printStackTrace();
        }

        return author;
    }
}
