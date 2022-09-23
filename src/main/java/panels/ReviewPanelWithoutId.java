package panels;

import models.Review;
import utils.ReviewFileManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.List;

public class ReviewPanelWithoutId extends JPanel {
    private List<Review> reviews;

    private JPanel buttonPanel;
    private JPanel contentPanel;
    private JPanel guidePanel;
    private JLabel reviewLabel;

    public ReviewPanelWithoutId() throws FileNotFoundException {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        ReviewFileManager reviewFileManager = new ReviewFileManager();
        reviews = reviewFileManager.loadReviews();

        buttonPanel();
    }

    private void buttonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        this.add(buttonPanel, BorderLayout.PAGE_START);

        buttonPanel.add(mainButton());
        buttonPanel.add(quitButton());

        contentPanel();
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
        contentPanel.setBackground(new Color(0, 0, 0, 122));
        contentPanel.setBorder(new LineBorder(Color.BLACK, 1));
        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.setPreferredSize(new Dimension(550, 350));

        this.add(contentPanel, BorderLayout.SOUTH);

        guidePanel();
    }

    private void guidePanel() {
        guidePanel = new JPanel();
        guidePanel.setOpaque(false);
        JLabel label1 = new JLabel("제목");
        label1.setForeground(Color.WHITE);
        label1.setPreferredSize(new Dimension(140, 15));
        guidePanel.add(label1);

        JLabel label2 = new JLabel("작성자");
        label2.setForeground(Color.WHITE);
        label2.setPreferredSize(new Dimension(35, 15));
        guidePanel.add(label2);

        contentPanel.add(guidePanel, BorderLayout.PAGE_START);

        reviewsPanel();
    }

    private void reviewsPanel() {
        for (Review review : reviews) {
            if (review.status().equals("delete")) {
                continue;
            }

            JPanel panel = new JPanel();
            panel.setBackground(new Color(255, 255, 255, 122));
            panel.setBorder(new LineBorder(Color.BLACK, 1));
            contentPanel.add(panel);

            reviewLabel = new JLabel();
            reviewLabel.setHorizontalAlignment(JLabel.CENTER);
            reviewLabel.setText(review.category() + " " + review.title() + "                       "
                    + review.userId());
            panel.add(reviewLabel);

            reviewLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane optionPane = new JOptionPane();

                    optionPane.showMessageDialog(null, "회원만 조회 가능합니다.", "Access denied", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
            });
        }
    }

    private void updateContentPanel(JPanel panel) {
        this.removeAll();
        this.add(panel);

        this.setVisible(false);
        this.setVisible(true);
    }
}
