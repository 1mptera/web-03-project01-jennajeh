package models;

public class Review {
    private static final String DELETE = "delete";
    private static final String CREATED = "created";
    private static final String MODIFIED = "modified";

    private static final String TITLE = "제목";
    private static final String USERID = "아이디";

    public static final String[] CATEGORY = {"선택하세요.", "제목", "아이디",};

    private String title;
    private String userId;
    private String userName;
    private String time;
    private String content;
    private String status;
    private String category;

    public Review(String title, String userId, String userName, String time, String content, String status) {
        this.title = title;
        this.userId = userId;
        this.userName = userName;
        this.time = time;
        this.content = content;
        this.status = "created";
    }

    public Review(String category, String title, String userId, String content) {
        this.category = category;
        this.title = title;
        this.userId = userId;
        this.content = content;
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

    public String time() {
        return time;
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
        return category + "," + title + "," + userId + "," + content;
    }

    public void updateContent(String content) {
        this.content = content;
        this.status = "modified";
    }

    public void delete() {
        this.status = "delete";
    }

    public String displayTime() {
        String displayTime = "";

        String[] times = time.split("-");

        displayTime = times[0];

        return displayTime;
    }
}
