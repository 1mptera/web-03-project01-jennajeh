package utils;

import models.CheckList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CheckListFileManagerTest {

    @Test
    void loadCheckList() throws FileNotFoundException {
        CheckListFileManager checkListFileManager = new CheckListFileManager();

        List<CheckList> checkLists = checkListFileManager.loadCheckList();

        assertNotNull(checkLists);

        CheckList checkList1 = checkLists.get(0);
        CheckList checkList2 = checkLists.get(1);

        assertEquals(new CheckList("[서울]", "와하카", "notChecked"), checkList1);
        assertEquals(new CheckList("[대전]", "성심당", "notChecked"), checkList2);
    }
}
