package models;

public class Review {
    public static final String TITLE = "제목";
    public static final String USERID = "아이디";

    private String title;
    private String userId;
    private String content;
    private String status;
    private String category;
    private long idNumber;

    public Review() {
    }

    public Review(String category, String title, String userId, String content, long idNumber, String status) {
        this.category = category;
        this.title = title;
        this.userId = userId;
        this.content = content;
        this.idNumber = idNumber;
        this.status = status;
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

    public String content() {
        return content;
    }

    public String status() {
        return status;
    }

    public String category() {
        return category;
    }

    public long idNumber() {
        return idNumber;
    }

    public String toCsvRow() {
        return category + "," + title + "," + userId + "," + content + "," + status + "," + idNumber;
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
