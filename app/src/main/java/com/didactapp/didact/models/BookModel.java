package com.didactapp.didact.models;


public abstract class BookModel {

    protected final int bookId;
    protected final String coverUrl;
    protected final String thumbnailUrl;
    protected final String title;
    protected final String tagLine;
    protected final String description;
    protected final int publishedDate;
    protected final int revisionDate;
    protected final int version;

    protected BookModel(int bookId, String coverUrl, String thumbnailUrl, String title, String tagLine, String description, int publishedDate, int revisionDate, int version) {
        this.bookId = bookId;
        this.coverUrl = coverUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.tagLine = tagLine;
        this.description = description;
        this.publishedDate = publishedDate;
        this.revisionDate = revisionDate;
        this.version = version;
    }

    public final int getBookId() {
        return bookId;
    }

    public final String getCoverUrl() {
        return coverUrl;
    }

    public final String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public final String getTitle() {
        return title;
    }

    public final String getTagLine() {
        return tagLine;
    }

    public final String getDescription() {
        return description;
    }

    public final int getPublishedDate() {
        return publishedDate;
    }

    public final int getRevisionDate() {
        return revisionDate;
    }

    public final int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "bookId='" + bookId + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", title='" + title + '\'' +
                ", tagLine='" + tagLine + '\'' +
                ", description='" + description + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", revisionDate='" + revisionDate + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
