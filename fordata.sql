# DROP TABLE ARTICLES;
# DROP TABLE AUTHORS;
# DROP TABLE CATEGORIES;


CREATE TABLE CATEGORIES (
  category_id INT PRIMARY KEY,
  parent_category_id INT,
  category_name VARCHAR(255),
  category_description TEXT,
  FOREIGN KEY (parent_category_id) REFERENCES CATEGORIES(category_id)
);

CREATE TABLE AUTHORS (
  author_id VARCHAR(255) PRIMARY KEY,
  password VARCHAR(255),
  first_name VARCHAR(255),
  middle_name VARCHAR(255),
  last_name VARCHAR(255),
  role VARCHAR(255) DEFAULT 'ROLE_ADMIN',
  enabled TINYINT NOT NULL DEFAULT 1
);

CREATE TABLE ARTICLES (
  article_id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255),
  short_titled_id VARCHAR(255),
  publication_date DATE,
  article_content TEXT,
  article_extract TEXT,
  category_id int,
  author_id VARCHAR(255),
  FOREIGN KEY (category_id) REFERENCES CATEGORIES (category_id),
  FOREIGN KEY (author_id) REFERENCES AUTHORS (author_id)
);

# Default categories
INSERT INTO CATEGORIES VALUES(1, null, 'TV & Movies', 'Description');
INSERT INTO CATEGORIES VALUES(2, 1, 'TV Shows', 'Description');
INSERT INTO CATEGORIES VALUES(3, 1, 'Movies', 'Description');
INSERT INTO CATEGORIES VALUES(4, null, 'Food & Drinks', 'Description');
INSERT INTO CATEGORIES VALUES(5, 4, 'Food', 'Description');
INSERT INTO CATEGORIES VALUES(6, 4, 'Drinks', 'Description');
INSERT INTO CATEGORIES VALUES(7, 4, 'Quick Recipes', 'Description');
INSERT INTO CATEGORIES VALUES(8, 4, 'Health', 'Description');
INSERT INTO CATEGORIES VALUES(9, null, 'Video & Gaming', 'Description');
INSERT INTO CATEGORIES VALUES(10, 9, 'Viral Videos', 'Description');
INSERT INTO CATEGORIES VALUES(11, 9, 'Gaming', 'Description');
INSERT INTO CATEGORIES VALUES(12, null, 'Books', 'Description');
INSERT INTO CATEGORIES VALUES(13, 12, 'Best Sellers', 'Description');
INSERT INTO CATEGORIES VALUES(14, 12, 'Top', 'Description');

# Default author
INSERT INTO AUTHORS VALUES('srgykim', 'secret', 'Sergey', '', 'Kim', DEFAULT, DEFAULT);


# Queries for further usage
SELECT * FROM AUTHORS;

SELECT CONCAT(first_name, ' ', last_name) AS full_name FROM AUTHORS;

SELECT * FROM ARTICLES NATURAL JOIN CATEGORIES;

SELECT MAX(article_id) AS last_id FROM ARTICLES;

SELECT PARENT.category_name, SUB.category_name
FROM CATEGORIES PARENT
  JOIN CATEGORIES SUB
    ON PARENT.category_id = SUB.parent_category_id;
