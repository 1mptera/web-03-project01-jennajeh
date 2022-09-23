package panels;

import models.Cities;
import models.City;
import models.CurrentUser;
import models.Review;
import models.User;
import utils.ReviewFileManager;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReviewEditPanel extends JPanel {
    private List<User> users;
    private Review review;
    private List<Review> reviews;
    private CurrentUser currentUser;
    private JPanel initPanel;
    private JTextField titleField;
    private JPanel textAreaPanel;
    private JTextArea textArea;
    private JPanel buttonPanel;
    private JComboBox comboBox;

    private String categoryData = "";
    private String title = "";
    private String category = "";
    private String content = "";
    private String id = "";

    public ReviewEditPanel(Review review, List<Review> reviews, CurrentUser currentUser) {
        this.review = review;
        this.reviews = reviews;
        this.currentUser = currentUser;

        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        initPanel();
    }

    private void initPanel() {
        for (Review review : reviews) {
            if (review.status().equals("delete")) {
                continue;
            }
        }

        initPanel = new JPanel();
        initPanel.setBackground(new Color(255, 255, 255, 122));
        initPanel.setLayout(new GridLayout(5, 1));

        this.add(initPanel, BorderLayout.PAGE_START);

        categoryLabel();
    }

    private void categoryLabel() {
        JLabel categoryLabel = new JLabel("카테고리 : ");
        initPanel.add(categoryLabel);

        comboBox();

        titleLabel();
    }

    private void comboBox() {
        List<City> cities = new ArrayList<>(Cities.CITIES);

        comboBox = new JComboBox();

        for (int i = 0; i < cities.size(); i += 1) {
            comboBox.addItem(cities.get(i).toString());
        }

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                categoryData = "[" + cb.getItemAt(cb.getSelectedIndex()) + "]";
            }
        });

        initPanel.add(comboBox);
    }

    private void titleLabel() {
        JLabel titleLabel = new JLabel("제목 : ");
        initPanel.add(titleLabel);

        titleField();
    }

    private void titleField() {
        titleField = new JTextField(10);

        titleField.setText(reviews.get(0).title());

        initPanel.add(titleField);

        contentLabel();
    }

    private void contentLabel() {
        JLabel contentLabel = new JLabel("내용 : ");
        initPanel.add(contentLabel);

        textAreaPanel();
    }

    private void textAreaPanel() {
        textAreaPanel = new JPanel();
        textAreaPanel.setOpaque(false);

        textArea();

        this.add(textAreaPanel);
    }

    private void textArea() {
        textArea = new JTextArea(15, 20);
        textArea.setBorder(new LineBorder(Color.BLACK, 1));

        textArea.setText(reviews.get(0).content());

        textAreaPanel.add(textArea);

        buttonPanel();
    }

    private void buttonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        this.add(buttonPanel, BorderLayout.SOUTH);

        backButton();
        editButton();
    }

    private void backButton() {
        JButton backButton = new JButton("목록");
        backButton.addActionListener(event -> {
            try {
                updateContentPanel(new ReviewPanel(users, currentUser));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        buttonPanel.add(backButton);
    }

    private void editButton() {
        JButton editButton = new JButton("완료");
        editButton.addActionListener(event -> {
            if (!review.status().equals("delete")) {
                category = categoryData;
                title = titleField.getText();
                id = currentUser.id();
                content = textArea.getText();

                review.updateContent(category, title, id, content);

                saveReview();

                try {
                    updateContentPanel(new ReviewPanel(users, currentUser));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        buttonPanel.add(editButton);
    }

    private void saveReview() {
        try {
            ReviewFileManager reviewFileManager = new ReviewFileManager();

            reviewFileManager.saveReviews(reviews);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateContentPanel(JPanel panel) {
        this.removeAll();
        this.add(panel);
        this.setVisible(false);
        this.setVisible(true);
    }
}
