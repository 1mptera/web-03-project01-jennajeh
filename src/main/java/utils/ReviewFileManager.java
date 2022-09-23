package utils;

import models.Review;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReviewFileManager {
    public List<Review> loadReviews() throws FileNotFoundException {
        List<Review> reviews = new ArrayList<>();

        File file = new File("reviews.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Review review = parseReviews(line);

            reviews.add(review);
        }

        return reviews;
    }

    private Review parseReviews(String line) {
        String[] words = line.split(",");

        long amount = Long.parseLong(words[5]);

        return new Review(words[0], words[1], words[2], words[3], amount, words[4]);
    }

    public void saveReviews(List<Review> reviews) throws IOException {
        FileWriter fileWriter = new FileWriter("reviews.csv");

        for (Review review : reviews) {
            String line = review.toCsvRow();
            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }
}
