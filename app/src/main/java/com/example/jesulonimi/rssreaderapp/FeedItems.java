package com.example.jesulonimi.rssreaderapp;

public class FeedItems {
    String title;
    String pubDate;
    String link;
    String description;
    String thumbnailUrl;

    public FeedItems() {
    }

    public FeedItems(String title, String pubDate, String link, String description, String thumbnailUrl) {
        this.title = title;
        this.pubDate = pubDate;
        this.link = link;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnail) {
        this.thumbnailUrl = thumbnail;
    }
}
