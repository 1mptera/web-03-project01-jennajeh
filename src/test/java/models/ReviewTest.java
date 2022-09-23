package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReviewTest {

    @Test
    void creation() {
        Review review = new Review("카테고리", "제목", "아이디", "내용");

        assertEquals("카테고리", review.category());
        assertEquals("제목", review.title());
        assertEquals("아이디", review.userId());
        assertEquals("내용", review.content());
        assertEquals("created", review.status());
    }

    @Test
    void csvRow() {
        Review review = new Review("카테고리", "제목", "아이디", "내용");

        assertEquals("카테고리,제목,아이디,내용", review.toCsvRow());
    }

    @Test
    void updateContent() {
        Review review = new Review("카테고리", "타이틀", "아이디", "바뀌기 전 내용");

        assertEquals("바뀌기 전 내용", review.content());
        assertEquals("created", review.status());

        review.updateContent("바뀐카테고리", "바뀐타이틀", "바뀐아이디", "바뀐내용");

        assertEquals("바뀐내용", review.content());
        assertEquals("modified", review.status());
    }
}
