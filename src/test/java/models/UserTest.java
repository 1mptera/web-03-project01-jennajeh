package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void creation() {
        User user = new User("nada", "asdf1234", "전겨울", "여자");

        assertEquals("nada", user.userId());
        assertEquals("asdf1234", user.password());
        assertEquals("전겨울", user.name());
        assertEquals("여자", user.gender());
        assertEquals("login", user.status());
    }

    @Test
    void csvRow() {
        User user = new User("nada", "asdf1234", "전겨울", "여자");

        assertEquals("nada,asdf1234,전겨울,여자", user.toCsvRow());
    }

}
