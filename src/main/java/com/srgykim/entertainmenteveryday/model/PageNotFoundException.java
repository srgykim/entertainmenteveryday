package com.srgykim.entertainmenteveryday.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown whenever user tries to visit the page
 * containing the article by following its corresponding URI and
 * this article is not found. See ArticleController class.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PageNotFoundException extends RuntimeException {}
