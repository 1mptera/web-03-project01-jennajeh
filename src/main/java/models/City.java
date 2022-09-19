package models;

public class City {
    private String city;

    public City(String city) {
        this.city = city;
    }

    public String city() {
        return city;
    }

    @Override
    public String toString() {
        return city;
    }
}
