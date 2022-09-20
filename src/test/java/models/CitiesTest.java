package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CitiesTest {

    @Test
    void city() {
        List<City> cities1 = new ArrayList<>(Cities.CITIES);
        List<City> cities2 = new ArrayList<>(Cities.CITIES);

        assertEquals(9, cities1.size());

        Assertions.assertIterableEquals(cities1, cities2);
    }

}
