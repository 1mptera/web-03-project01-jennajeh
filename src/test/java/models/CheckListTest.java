package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckListTest {

    @Test
    void creation() {
        CheckList checkList = new CheckList("서울", "와하카");

        assertEquals("서울", checkList.city());
        assertEquals("와하카", checkList.title());
    }

    @Test
    void delete() {
        CheckList checkList1 = new CheckList("서울", "와하카");

        CheckList checkList2 = new CheckList("서울", "와하카");
        checkList2.delete();

        assertEquals("notChecked", checkList1.status());
        assertEquals("delete", checkList2.status());
    }

    @Test
    void gone() {
        CheckList checkList1 = new CheckList("서울", "와하카");

        CheckList checkList2 = new CheckList("서울", "와하카");
        checkList2.gone();

        assertEquals("notChecked", checkList1.status());
        assertEquals("gone", checkList2.status());
    }

    @Test
    void notChecked() {
        CheckList checkList1 = new CheckList("서울", "와하카");

        CheckList checkList2 = new CheckList("서울", "와하카");
        checkList2.notChecked();

        assertEquals("notChecked", checkList1.status());
        assertEquals("notChecked", checkList2.status());
    }

    @Test
    void csvRow() {
        CheckList checkList = new CheckList("서울", "와하카");

        assertEquals("서울,와하카,notChecked", checkList.toCsvRow());
    }
}
