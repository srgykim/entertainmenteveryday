package com.srgykim.entertainmenteveryday.controller;

import com.srgykim.entertainmenteveryday.data.ArticleDAO;
import com.srgykim.entertainmenteveryday.data.AuthorDAO;
import com.srgykim.entertainmenteveryday.model.Article;
import com.srgykim.entertainmenteveryday.model.Author;
import com.srgykim.entertainmenteveryday.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Class AuthorController handles requests related to articles and author.
 */
@Controller
public class AuthorController {

    @Autowired
    private ArticleDAO articleDao;

    @Autowired
    private AuthorDAO authorDao;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/author/login")
    public String authorLogin() {

        return "author-login";
    }

    @RequestMapping(value="/author", method=RequestMethod.GET)
    public String authorPage(Model model, Principal principal) {

        // method principal.getName() returns the author_id. see author-login.html
        Author author = authorDao.getAuthorById(principal.getName());

        model.addAttribute("author", author);
        model.addAttribute("article", new Article());
        model.addAttribute("sliderArticle", new Article());

        return "author";
    }

    @RequestMapping(value="/author", params="articlePublication", method=RequestMethod.POST)
    public String articleSubmit(@ModelAttribute Article article, Model model,
                                Principal principal) {

        article.setShortTitledId(articleService.formatShortTitledId(article));
        article.setAuthorId(principal.getName());

        articleDao.publishArticle(article);

        model.addAttribute("article", article);

        return "just-published";
    }

    @RequestMapping(value="/author", params="sliderArticlePublication", method=RequestMethod.POST)
    public String sliderArticleSubmit(@ModelAttribute Article sliderArticle, Model model) {

        articleDao.publishSliderArticle(sliderArticle);

        return "just-published";
    }
}
