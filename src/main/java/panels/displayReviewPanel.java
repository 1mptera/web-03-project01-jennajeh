package panels;

import models.CurrentUser;
import models.Review;
import models.User;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.List;

public class displayReviewPanel extends JPanel {
    private String categoryData = "";
    private String title = "";
    private String category = "";
    private String content = "";
    private String id = "";

    private JPanel initPanel;
    private List<User> users;
    private List<Review> reviews;
    private CurrentUser currentUser;
    private JTextField titleField;
    private JPanel textAreaPanel;
    private JTextArea textArea;
    private JPanel buttonPanel;

    public displayReviewPanel(List<User> users, List<Review> reviews, CurrentUser currentUser) {
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
        //titleLabel.setForeground(Color.WHITE);
        initPanel.add(titleLabel);

        titleField();
    }

    private void titleField() {
        titleField = new JTextField(10);
        titleField.setEditable(false);
        titleField.setText("[서울] 와하카: 웨이팅이 좀 길어요.");
        initPanel.add(titleField);

        contentLabel();
    }

    private void contentLabel() {
        JLabel contentLabel = new JLabel("내용 : ");
        //contentLabel.setForeground(Color.WHITE);
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
        textArea.setText("웨이팅은 무조건 있으니까 여유롭게 가세요!");
        textArea.setBorder(new LineBorder(Color.BLACK, 1));
        textAreaPanel.add(textArea);

        buttonPanel();
    }

    private void buttonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        this.add(buttonPanel, BorderLayout.SOUTH);

        backButton();
    }

    private void backButton() {
        JButton backButton = new JButton("목록 보기");
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
