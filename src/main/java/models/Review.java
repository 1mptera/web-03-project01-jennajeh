package models;

public class Review {
    public static final String TITLE = "제목";
    public static final String USERID = "아이디";

    private String title;
    private String userId;
    private String userName;
    private String content;
    private String status;
    private String category;

    public Review() {
    }

    public Review(String category, String title, String userId, String content) {
        this.category = category;
        this.title = title;
        this.userId = userId;
        this.content = content;
        this.status = "created";
    }

    public Review(String category, String title, String userId, String content, String status) {
        this.category = category;
        this.title = title;
        this.userId = userId;
        this.content = content;
        this.status = status;
    }

    public String title() {
        return title;
    }

    public String userId() {
        return userId;
    }

    public String userName() {
        return userName;
    }

    public String content() {
        return content;
    }

    public String status() {
        return status;
    }

    public String category() {
        return category;
    }

    public String toCsvRow() {
        return category + "," + title + "," + userId + "," + content + "," + status;
    }

    public void updateContent(String category, String title, String userId, String content) {
        this.category = category;
        this.title = title;
        this.userId = userId;
        this.content = content;
        this.status = "modified";
    }

    public void delete() {
        this.status = "delete";
    }

    public void modified() {
        this.status = "modified";
    }
}
