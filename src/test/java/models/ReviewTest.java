package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReviewTest {

    @Test
    void creation() {
        Review review = new Review("제목", "작성자아이디", "작성자이름", "날짜", "내용", "0");

        assertEquals("제목", review.title());
        assertEquals("작성자아이디", review.userId());
        assertEquals("작성자이름", review.userName());
        assertEquals("날짜", review.time());
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
        Review review = new Review("제목", "작성자아이디", "작성자이름", "날짜", "바뀌기 전 내용", "0");

        assertEquals("바뀌기 전 내용", review.content());
        assertEquals("created", review.status());

        review.updateContent("내용 바꾸기");

        assertEquals("내용 바꾸기", review.content());
        assertEquals("modified", review.status());
    }

    @Test
    void displayTime() {
        Review review = new Review("제목", "작성자아이디", "작성자이름", "2022-09-21-11-31-30", "바뀌기 전 내용", "글상태");

        review.displayTime();
        assertEquals("2022-09-21-11-31-30", review.time());

    }
}
