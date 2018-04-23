package com.didactapp.didact.models;

public abstract class ChapterModel {

    protected final int chapterId;
    protected final int bookId;
    protected final int chapterNum;
    protected final String name;
    protected final String description;
    protected final String thumbnailUrl;


    protected ChapterModel(int chapterId, int bookId, int chapterNum, String name, String description, String thumbNail) {
        this.chapterId = chapterId;
        this.bookId = bookId;
        this.chapterNum = chapterNum;
        this.name = name;
        this.description = description;
        this.thumbnailUrl = thumbNail;
    }

    public int getBookId() {
        return bookId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public int getChapterNum() {
        return chapterNum;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @Override
    public String toString() {
        return "ChapterModel{" +
                "bookId=" + bookId +
                ", chapterId=" + chapterId +
                ", chapterNum=" + chapterNum +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
