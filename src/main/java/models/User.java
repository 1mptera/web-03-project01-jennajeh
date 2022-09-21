package models;

public class User {
    private static final String LOGIN = "login";
    private static final String LOGOUT = "logout";

    private String userId;
    private String password;
    private String name;
    private String gender;
    private String status;

    public User(String userId, String password, String name, String gender) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.status = LOGIN;
    }

    public String userId() {
        return userId;
    }

    public String password() {
        return password;
    }

    public String name() {
        return name;
    }

    public String gender() {
        return gender;
    }

    public String status() {
        return status;
    }

    public void login() {
        this.status = "login";
    }

    public void logout() {
        this.status = "logout";
    }

    public String toCsvRow() {
        return userId + "," + password + "," + name + "," + gender;
    }
}
