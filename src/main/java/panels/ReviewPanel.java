package panels;

import models.CurrentUser;
import models.Review;
import models.User;
import utils.ReviewFileManager;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

public class ReviewPanel extends JPanel {
    private List<User> users;
    private List<Review> reviews;
    private CurrentUser currentUser;
    String choice = "";

    private JPanel buttonPanel;
    private JPanel contentPanel;
    private JButton searchButton;
    private JTextField searchTextField;
    private JPanel searchPanel;
    private JPanel guidePanel;

    public ReviewPanel(List<User> users, CurrentUser currentUser) throws FileNotFoundException {
        this.users = users;
        this.currentUser = currentUser;

        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        ReviewFileManager reviewFileManager = new ReviewFileManager();
        reviews = reviewFileManager.loadReviews();

        buttonPanel();
    }

    private void buttonPanel() {
        buttonPanel = new JPanel();
//        buttonPanel.setOpaque(false);
        buttonPanel.setBackground(Color.pink);
        this.add(buttonPanel, BorderLayout.PAGE_START);

        buttonPanel.add(mainButton());
        buttonPanel.add(checkListButton());
        buttonPanel.add(createReviewButton());
        buttonPanel.add(logoutButton());

        searchPanel();
    }

    private void searchPanel() {
        searchPanel = new JPanel();
        //searchPanel.setOpaque(false);
        //searchPanel.setLayout(new BorderLayout());
        searchPanel.setBackground(Color.blue);
        this.add(searchPanel, BorderLayout.CENTER);

        makeComboBox();

        searchTextField = new JTextField(10);
        searchPanel.add(searchTextField);

        searchButton = new JButton("검색");
        searchPanel.add(searchButton);

        contentPanel();
    }

    private JButton mainButton() {
        JButton mainButton = new JButton("메인 화면");
        mainButton.addActionListener(event -> {
            updateContentPanel(new MainPanel(users, currentUser));
        });

        return mainButton;
    }

    private JButton checkListButton() {
        JButton checkListButton = new JButton("체크 리스트");
        checkListButton.addActionListener(event -> {
            try {
                updateContentPanel(new CheckListPanel(currentUser));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        return checkListButton;
    }

    private JButton createReviewButton() {
        JButton createReviewButton = new JButton("글쓰기");
        createReviewButton.addActionListener(event -> {
            updateContentPanel(new WriteReviewPanel(users, reviews, currentUser));
        });
        return createReviewButton;
    }

    private JButton logoutButton() {
        JButton logoutButton = new JButton("로그아웃");
        logoutButton.addActionListener(event -> {
            JOptionPane optionPane = new JOptionPane();

            JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.", "Fries!", JOptionPane.PLAIN_MESSAGE);

            currentUser.logout();

            try {
                updateContentPanel(new InitLoginPanel());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        return logoutButton;
    }

    private void makeComboBox() {
        String[] category = {"제목", "작성자"};

        JComboBox comboBox = new JComboBox();

        for (int i = 0; i < category.length; i += 1) {
            comboBox.addItem(category[i].toString());
        }

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                choice = (String) cb.getItemAt(cb.getSelectedIndex());
            }
        });

        searchPanel.add(comboBox);
    }

    private void contentPanel() {
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.yellow);
        //contentPanel.setBackground(new Color(0, 0, 0, 122));
        contentPanel.setBorder(new LineBorder(Color.BLACK, 1));
//        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.setPreferredSize(new Dimension(550, 350));

        this.add(contentPanel, BorderLayout.SOUTH);

        guidePanel();
    }

    private void guidePanel() {
        guidePanel = new JPanel();

        JLabel label1 = new JLabel("제목");
        label1.setPreferredSize(new Dimension(400, 15));
        guidePanel.add(label1);

        JLabel label2 = new JLabel("작성자");
        label2.setPreferredSize(new Dimension(50, 15));
        guidePanel.add(label2);

        contentPanel.add(guidePanel);
    }

    private void updateContentPanel(JPanel panel) {
        this.removeAll();
        this.add(panel);

        this.setVisible(false);
        this.setVisible(true);
    }
}
