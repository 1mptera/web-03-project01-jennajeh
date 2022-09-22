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

public class WriteReviewPanel extends JPanel {
    private List<User> users;
    private List<Review> reviews;
    private CurrentUser currentUser;

    private String categoryData = "";
    private String title = "";
    private String category = "";
    private String content = "";
    private String id = "";

    private JPanel initPanel;
    private JPanel textAreaPanel;
    private JPanel buttonPanel;
    private JTextField titleField;
    private JComboBox comboBox;
    private JTextArea textArea;

    public WriteReviewPanel(List<User> users, List<Review> reviews, CurrentUser currentUser) {
        this.users = users;
        this.reviews = reviews;
        this.currentUser = currentUser;

        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        start();
    }

    private void start() {
        initPanel();
        titleLabel();
        titleField();
        categoryLabel();
        comboBox();
        contentLabel();
        textAreaPanel();
        textArea();
        buttonPanel();
        addButton();
        backButton();
    }

    private void initPanel() {
        initPanel = new JPanel();
        initPanel.setBackground(new Color(0, 0, 0, 122));
        initPanel.setLayout(new GridLayout(5, 1));

        this.add(initPanel, BorderLayout.PAGE_START);
    }

    private void titleLabel() {
        JLabel titleLabel = new JLabel("제목 : ");
        titleLabel.setForeground(Color.WHITE);
        initPanel.add(titleLabel);
    }

    private void titleField() {
        titleField = new JTextField(10);
        initPanel.add(titleField);
    }

    private void categoryLabel() {
        JLabel categoryLabel = new JLabel("카테고리 : ");
        categoryLabel.setForeground(Color.WHITE);
        initPanel.add(categoryLabel);
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

    private void contentLabel() {
        JLabel contentLabel = new JLabel("내용 : ");
        contentLabel.setForeground(Color.WHITE);
        initPanel.add(contentLabel);
    }

    private void textAreaPanel() {
        textAreaPanel = new JPanel();
        textAreaPanel.setOpaque(false);
        this.add(textAreaPanel);
    }

    private void textArea() {
        textArea = new JTextArea(15, 20);
        textArea.setBorder(new LineBorder(Color.BLACK, 1));
        textAreaPanel.add(textArea);
    }

    private void buttonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addButton() {
        JButton addButton = new JButton("등록");
        addButton.addActionListener(event -> {
            category = categoryData;
            title = titleField.getText();
            id = currentUser.id();
            content = textArea.getText();

            if (content.isBlank()) {
                return;
            }

            Review review = new Review(category, title, id, content);
            reviews.add(review);

            try {
                ReviewFileManager reviewFileManager = new ReviewFileManager();

                reviewFileManager.saveReviews(reviews);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

//              removeAll();
//            setVisible(false);
//            setVisible(true);


            try {
                updateContentPanel(new ReviewPanel(users, currentUser));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        });
        buttonPanel.add(addButton);
    }

    private void backButton() {
        JButton backButton = new JButton("취소");
        backButton.addActionListener(event -> {
            try {
                updateContentPanel(new ReviewPanel(users, currentUser));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        buttonPanel.add(backButton);
    }

    private void updateContentPanel(JPanel panel) {
        this.removeAll();
        this.add(panel);
        this.setVisible(false);
        this.setVisible(true);
    }
}
