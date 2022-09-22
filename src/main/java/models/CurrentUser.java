package models;

public class CurrentUser {
    private String id;

    public CurrentUser(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    public void login(String id) {
        this.id = id;
    }

    public void logout() {
        this.id = "";
    }
}
