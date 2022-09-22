package panels;

import models.CurrentUser;
import models.User;
import utils.UserFileManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.List;

public class InitLoginPanel extends JPanel {
    private List<User> users;
    private CurrentUser currentUser;

    private JPanel loginPanel;
    private JPanel buttonPanel;
    private JTextField passwordField;
    private JTextField idTextField;

    public InitLoginPanel() throws FileNotFoundException {
        currentUser = new CurrentUser("");

        UserFileManager userFileManager = new UserFileManager();
        users = userFileManager.loadUserLists();

//        ReviewFileManager reviewFileManager = new ReviewFileManager();
//        reviews = reviewFileManager.loadReviews();

        loginPanel();
    }


    private void loginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(0, 1));
        loginPanel.setBackground(new Color(0, 0, 0, 122));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        loginPanel.setPreferredSize(new Dimension(550, 250));

        idLabel();
        idTextField();
        passwordLabel();
        passwordField();
        buttonPanel();

        this.setOpaque(false);

        this.add(loginPanel);
    }

    private void idLabel() {
        JLabel idLabel = new JLabel("아이디");
        idLabel.setForeground(Color.WHITE);
        loginPanel.add(idLabel);
    }

    private void idTextField() {
        idTextField = new JTextField(10);
        loginPanel.add(idTextField);
    }

    private void passwordLabel() {
        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setForeground(Color.WHITE);
        loginPanel.add(passwordLabel);
    }

    private void passwordField() {
        passwordField = new JTextField(10);
        loginPanel.add(passwordField);
    }

    private void buttonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        buttonPanel.add(loginButton());
        buttonPanel.add(signUpButton());
        buttonPanel.add(withoutIdButton());
        buttonPanel.add(quitButton());

        loginPanel.add(buttonPanel);
    }

    private JButton loginButton() {
        JButton loginButton = new JButton("로그인");
        loginButton.addActionListener(event -> {
            String id = idTextField.getText();
            String password = passwordField.getText();

            if (id.length() == 0) {
                JOptionPane optionPane = new JOptionPane();

                optionPane.showMessageDialog(null, "아이디를 입력하세요.", "Access denied", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            if (password.length() == 0) {
                JOptionPane optionPane = new JOptionPane();

                optionPane.showMessageDialog(null, "비밀번호를 입력하세요.", "Access denied", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            if (id.length() != 0 && password.length() != 0) {
                for (User user : users) {
                    if (id.equals(user.userId()) && password.equals(user.password())) {
                        currentUser.login(idTextField.getText());

                        updateContentPanel(new MainPanel(users, currentUser));

                        return;
                    }

                    if (user.userId().equals(id) && !user.password().equals(password)) {
                        JOptionPane optionPane = new JOptionPane();

                        optionPane.showMessageDialog(null, "비밀번호를 잘못 입력했습니다.", "Access denied", JOptionPane.PLAIN_MESSAGE);

                        return;
                    }
                }
            }

            if (!users.contains(id) && !users.contains(password)) {
                JOptionPane optionPane = new JOptionPane();

                optionPane.showMessageDialog(null, "없는 계정입니다. 회원가입을 진행해 주세요.", "Access denied", JOptionPane.PLAIN_MESSAGE);

                idTextField.setText("");
                passwordField.setText("");

                return;
            }
        });

        return loginButton;
    }

    private JButton signUpButton() {
        JButton signUpButton = new JButton("회원가입");
        signUpButton.addActionListener(event -> {
            updateContentPanel(new signUpPanel(users));
        });

        return signUpButton;
    }

    private JButton withoutIdButton() {
        JButton withoutIdButton = new JButton("비회원 입장");
        withoutIdButton.addActionListener(event -> {
            updateContentPanel(new MainPanelWithoutId());
        });

        return withoutIdButton;
    }

    private JButton quitButton() {
        JButton signUpButton = new JButton("종료하기");
        signUpButton.addActionListener(event -> {
            System.exit(0);
        });

        return signUpButton;
    }

    private void updateContentPanel(JPanel panel) {
        this.removeAll();
        this.add(panel);
        this.setVisible(false);
        this.setVisible(true);
    }
}
