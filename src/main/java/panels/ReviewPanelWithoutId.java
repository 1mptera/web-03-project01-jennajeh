package panels;

import models.Review;
import utils.ReviewFileManager;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

public class ReviewPanelWithoutId extends JPanel {
    private List<Review> reviews;
    String choice = "";

    private JPanel buttonPanel;
    private JPanel contentPanel;
    private JButton searchButton;
    private JTextField searchTextField;
    private JPanel searchPanel;
    private JPanel guidePanel;

    public ReviewPanelWithoutId() throws FileNotFoundException {
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
        buttonPanel.add(quitButton());

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

    private JButton mainButton() {
        JButton mainButton = new JButton("메인 화면");
        mainButton.addActionListener(event -> {
            updateContentPanel(new MainPanelWithoutId());
        });

        return mainButton;
    }

    private JButton quitButton() {
        JButton signUpButton = new JButton("종료하기");
        signUpButton.addActionListener(event -> {
            System.exit(0);
        });

        return signUpButton;
    }

    private void contentPanel() {
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.BLACK);
        //contentPanel.setBackground(new Color(0, 0, 0, 122));
        contentPanel.setBorder(new LineBorder(Color.BLACK, 1));
        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.setPreferredSize(new Dimension(550, 350));

        this.add(contentPanel, BorderLayout.SOUTH);

        guidePanel();
    }

    private void guidePanel() {
        guidePanel = new JPanel();
        guidePanel.setBackground(Color.GREEN);
        JLabel label1 = new JLabel("제목");
        label1.setPreferredSize(new Dimension(200, 15));
        guidePanel.add(label1);

        JLabel label2 = new JLabel("작성자");
        label2.setPreferredSize(new Dimension(50, 15));
        guidePanel.add(label2);

        contentPanel.add(guidePanel, BorderLayout.PAGE_START);

        reviewsPanel();
    }

    private void reviewsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.ORANGE);
        contentPanel.add(panel);

        for (Review review : reviews) {
            JLabel label = new JLabel();
            label.setText(review.category() + " " + review.title() + "                                       "
                    + review.userId());
            panel.add(label);
        }
    }

    private void updateContentPanel(JPanel panel) {
        this.removeAll();
        this.add(panel);

        this.setVisible(false);
        this.setVisible(true);
    }
}
