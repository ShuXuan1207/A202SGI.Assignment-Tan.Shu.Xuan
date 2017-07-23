package com.inti.student.androidassignment;

public class Article {

    private String author;
    private String urlToImage;
    private String title;
    private String description;
    private String publishedAt;
    private String url;

    public Article(String author, String urlToImage, String title, String description, String publishedAt, String url) {
        this.author = author;
        this.urlToImage = urlToImage;
        this.title = title;
        this.description = description;
        this.publishedAt = publishedAt;
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }
    public String getUrlToImage() {
        return urlToImage;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getPublishedAt() {
        return publishedAt;
    }
    public String getUrl() {
        return url;
    }

}
