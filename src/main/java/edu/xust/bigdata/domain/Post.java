package edu.xust.bigdata.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 帖子类
 */
public class Post {
    //帖子标题
    private String title;
    //楼主
    private String author;
    //镇楼图
    List<String> images = new ArrayList<String>();

    public Post() {
    }

    public Post(String title, String author, List images) {
        this.title = title;
        this.author = author;
        this.images = images;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", images=" + images +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List getImages() {
        return images;
    }

    public void setImages(List images) {
        this.images = images;
    }
}
