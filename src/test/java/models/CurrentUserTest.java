package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrentUserTest {

    @Test
    void creation() {
        CurrentUser currentUser = new CurrentUser("hello");

        assertEquals("hello", currentUser.id());
    }

    @Test
    void login() {
        CurrentUser currentUser = new CurrentUser("");

        currentUser.login("hello");

        assertEquals("hello", currentUser.id());
    }

    @Test
    void logout() {
        CurrentUser currentUser = new CurrentUser("hello");

        currentUser.logout();

        assertEquals("", currentUser.id());
    }


}
