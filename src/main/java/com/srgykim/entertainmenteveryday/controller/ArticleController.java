package com.srgykim.entertainmenteveryday.controller;

import com.srgykim.entertainmenteveryday.data.ArticleDAO;
import com.srgykim.entertainmenteveryday.model.Article;
import com.srgykim.entertainmenteveryday.model.PageNotFoundException;
import com.srgykim.entertainmenteveryday.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Class ArticleController handles requests related to articles and readers.
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleDAO articleDao;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/")
    public String homePage(Model model) {

        List<Article> articles = articleDao.getAllArticles();
        List<Article> sliderArticles = articleDao.getAllSliderArticles();

        for (Article article : articles) {
            article.setPublicationDate(articleService.formatArticleDate(article));
        }

        for (Article article : sliderArticles) {
            article.setPublicationDate(articleService.formatArticleDate(article));
        }

        model.addAttribute("articles", articles);
        model.addAttribute("sliderArticles", sliderArticles);
        return "home";
    }

    @RequestMapping("/article/{shortTitledId}")
    public String getArticleDetails(@PathVariable String shortTitledId, Model model) {

        Article article = articleDao.getArticleByShortTitledId(shortTitledId);

        if (article == null) {
            throw new PageNotFoundException();
        }

        article.setPublicationDate(articleService.formatArticleDate(article));

        model.addAttribute("article", article);
        return "article-details";
    }
}
