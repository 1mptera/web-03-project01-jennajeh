package utils;

import models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserFileManager {
    public List<User> loadUserLists() throws FileNotFoundException {
        List<User> users = new ArrayList<>();

        File file = new File("userLists.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            User user = parseUserLists(line);

            users.add(user);
        }

        return users;
    }

    private User parseUserLists(String line) {
        String[] words = line.split(",");

        return new User(words[0], words[1], words[2], words[3]);
    }

    public void saveUser(List<User> users) throws IOException {
        FileWriter fileWriter = new FileWriter("userLists.csv");

        for (User user : users) {
            String line = user.toCsvRow();

            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }
}
