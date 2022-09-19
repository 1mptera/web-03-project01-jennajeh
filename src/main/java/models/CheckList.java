package models;

import java.util.Objects;

public class CheckList {
    private static final String DELETE = "delete";
    private static final String NOT_CHECKED = "notChecked";
    private static final String GONE = "gone";

    private String title;
    private String status;
    private String city;

    public CheckList(String city, String title) {
        this.city = city;
        this.title = title;
        this.status = NOT_CHECKED;
    }

    public CheckList(String city, String title, String status) {
        this.city = city;
        this.title = title;
        this.status = status;
    }

    public String title() {
        return title;
    }

    public String status() {
        return status;
    }

    public String city() {
        return city;
    }

    public void delete() {
        this.status = "delete";
    }

    public void notChecked() {
        this.status = "notChecked";
    }

    public void gone() {
        this.status = "gone";
    }

    public String toCsvRow() {
        return city + "," + title + "," + status;
    }

    @Override
    public String toString() {
        return toCsvRow();
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        CheckList otherCheckList = (CheckList) other;

        return Objects.equals(this.title, otherCheckList.title())
                && this.status.equals(otherCheckList.status())
                && this.city.equals(otherCheckList.city());
    }
}
