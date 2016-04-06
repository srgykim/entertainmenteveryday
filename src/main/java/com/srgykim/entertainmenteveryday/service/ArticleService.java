package com.srgykim.entertainmenteveryday.service;

import com.srgykim.entertainmenteveryday.data.ArticleDAO;
import com.srgykim.entertainmenteveryday.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ArticleService {

    @Autowired
    private ArticleDAO articleDao;

    public ArticleService() {}

    public String formatArticleDate(Article article) {

        String stringDate = "";

        SimpleDateFormat inFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outFormatter = new SimpleDateFormat("MMMM dd, yyyy");

        try {
            Date date = inFormatter.parse(article.getPublicationDate());
            stringDate = outFormatter.format(date);
        } catch(ParseException pe) {
            pe.printStackTrace();
        }

        return stringDate;
    }

    public String formatShortTitledId(Article article) {

        return articleDao.getLastArticleId() + "-" +
                String.join("-", article.getShortTitledId().toLowerCase().split("[^\\w]+"));
    }
}
