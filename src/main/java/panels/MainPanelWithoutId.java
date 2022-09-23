package panels;

import models.User;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.io.FileNotFoundException;
import java.util.List;

public class MainPanelWithoutId extends JPanel {
    private List<User> users;

    public MainPanelWithoutId() {
        initButtonPanel();
    }

    private void initButtonPanel() {
        JPanel initButtonPanel = new JPanel();
        initButtonPanel.add(reviewButton());
        initButtonPanel.add(loginButton());
        initButtonPanel.add(signUpButton());
        initButtonPanel.add(quitButton());
        initButtonPanel.setOpaque(false);

        this.setOpaque(false);
        this.add(initButtonPanel);
    }

    private JButton reviewButton() {
        JButton checkListButton = new JButton("리뷰 게시판");
        checkListButton.addActionListener(event -> {
            try {
                updatePanel(new ReviewPanelWithoutId());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        return checkListButton;
    }

    private JButton loginButton() {
        JButton loginButton = new JButton("로그인");
        loginButton.addActionListener(event -> {
            try {
                updatePanel(new InitLoginPanel());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        return loginButton;
    }

    private JButton signUpButton() {
        JButton signUpButton = new JButton("회원가입");
        signUpButton.addActionListener(event -> {
            updatePanel(new signUpPanel(users));
        });

        return signUpButton;
    }

    private JButton quitButton() {
        JButton signUpButton = new JButton("종료하기");
        signUpButton.addActionListener(event -> {
            System.exit(0);
        });

        return signUpButton;
    }

    private void updatePanel(JPanel panel) {
        this.removeAll();
        this.add(panel);
        this.setVisible(false);
        this.setVisible(true);
    }
}
