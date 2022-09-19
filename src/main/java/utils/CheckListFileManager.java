package utils;

import models.CheckList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckListFileManager {
    public List<CheckList> loadCheckList() throws FileNotFoundException {
        List<CheckList> checkLists = new ArrayList<>();

        File file = new File("checkLists.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            CheckList checkList = parseCheckLists(line);

            checkLists.add(checkList);
        }

        return checkLists;
    }

    private CheckList parseCheckLists(String line) {
        String[] words = line.split(",");

        return new CheckList(words[0], words[1], words[2]);
    }

    public void saveLists(List<CheckList> checkLists) throws IOException {
        FileWriter fileWriter = new FileWriter("checkLists.csv");

        for (CheckList checkList : checkLists) {
            String line = checkList.toCsvRow();
            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }
}
