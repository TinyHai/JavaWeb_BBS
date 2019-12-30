package bbs.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {

    private long id;

    private long articleId;

    private int floor;

    private String commentByWho;

    private int commentToFloor;

    private String content;

    private Timestamp publishDate;

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getCommentByWho() {
        return commentByWho;
    }

    public void setCommentByWho(String commentByWho) {
        this.commentByWho = commentByWho;
    }

    public int getCommentToFloor() {
        return commentToFloor;
    }

    public void setCommentToFloor(int commentToFloor) {
        this.commentToFloor = commentToFloor;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", floor=" + floor +
                ", commentByWho='" + commentByWho + '\'' +
                ", commentToFloor=" + commentToFloor +
                ", content='" + content + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
}
