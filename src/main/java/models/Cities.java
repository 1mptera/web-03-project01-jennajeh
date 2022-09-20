package models;

import java.util.List;

public class Cities {
    public static final City SEOUL = new City("서울");
    public static final City GYEONGGI = new City("경기");
    public static final City INCHEON = new City("인천");
    public static final City JEJU = new City("제주");
    public static final City GYEONGSANG = new City("경상도");
    public static final City GANGWON = new City("강원도");
    public static final City CHUNGCHEONG = new City("충청도");
    public static final City JEONLA = new City("전라도");
    public static final City DAEJEON = new City("대전");

    public static final List<City> CITIES = List.of(
            SEOUL, GYEONGGI, INCHEON, JEJU, GYEONGSANG,
            GANGWON, CHUNGCHEONG, JEONLA, DAEJEON
    );
}
