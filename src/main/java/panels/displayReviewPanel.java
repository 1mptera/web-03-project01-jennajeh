package panels;

import models.CurrentUser;
import models.Review;
import models.User;
import utils.ReviewFileManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class displayReviewPanel extends JPanel {
    private List<User> users;
    private List<Review> reviews;
    private CurrentUser currentUser;
    private Review review;

    private JPanel initPanel;
    private JTextField titleField;
    private JPanel textAreaPanel;
    private JTextArea textArea;
    private JPanel buttonPanel;
    private ReviewEditPanel reviewEditPanel;

    public displayReviewPanel(Review review, List<User> users, List<Review> reviews, CurrentUser currentUser) {
        this.review = review;
        this.users = users;
        this.reviews = reviews;
        this.currentUser = currentUser;

        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        initPanel();
    }

    private void initPanel() {
        initPanel = new JPanel();
        initPanel.setBackground(new Color(255, 255, 255, 122));
        initPanel.setLayout(new GridLayout(3, 1));

        this.add(initPanel, BorderLayout.PAGE_START);

        titleLabel();
    }

    private void titleLabel() {
        JLabel titleLabel = new JLabel("제목 : ");
        initPanel.add(titleLabel);

        titleField();
    }

    private void titleField() {
        titleField = new JTextField(10);
        titleField.setEditable(false);

        titleField.setText(reviews.get(0).category() + " " + reviews.get(0).title());

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
        textArea.setEditable(false);
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
        deleteButton();
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

    private void deleteButton() {
        JButton deleteButton = new JButton("삭제");
        deleteButton.addActionListener(event -> {
            review.delete();

            saveReview();

            try {
                updateContentPanel(new ReviewPanel(users, currentUser));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        buttonPanel.add(deleteButton);
    }

    private void editButton() {
        JButton editButton = new JButton("수정");
        editButton.addActionListener(event -> {
            if (!currentUser.id().equals(review.userId())) {
                JOptionPane optionPane = new JOptionPane();

                optionPane.showMessageDialog(null, "작성자만 수정할 수 있습니다.", "Access denied", JOptionPane.PLAIN_MESSAGE);

                return;
            }
            
            updateContentPanel(new ReviewEditPanel(review, reviews, currentUser));
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
