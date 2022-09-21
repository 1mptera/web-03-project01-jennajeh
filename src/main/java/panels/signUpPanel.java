package panels;

import models.User;
import utils.UserFileManager;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class signUpPanel extends JPanel {
    private List<User> users;
    String female = "";
    String male = "";

    private JPanel signUpPanel;
    private JPanel buttonPanel;
    private JTextField idTextField;
    private JTextField passwordField;
    private JTextField nameField;

    public signUpPanel(List<User> users) {
        this.users = users;

        this.setOpaque(false);

        this.add(signUpPanel());
    }

    private JPanel signUpPanel() {
        signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridLayout(0, 1));
        signUpPanel.setBackground(new Color(0, 0, 0, 122));
        signUpPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        signUpPanel.setPreferredSize(new Dimension(550, 300));

        idLabel();
        idTextField();

        passwordLabel();
        passwordField();

        nameLabel();
        nameField();

        genderLabel();
        selectGender();

        buttonPanel();

        return signUpPanel;
    }

    private void idLabel() {
        JLabel idLabel = new JLabel("아이디");
        idLabel.setForeground(Color.WHITE);
        signUpPanel.add(idLabel);
    }

    private void idTextField() {
        idTextField = new JTextField(10);
        signUpPanel.add(idTextField);
    }

    private void passwordLabel() {
        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setForeground(Color.WHITE);
        signUpPanel.add(passwordLabel);
    }

    private void passwordField() {
        passwordField = new JTextField(10);
        signUpPanel.add(passwordField);
    }

    private void nameLabel() {
        JLabel nameLabel = new JLabel("이름");
        nameLabel.setForeground(Color.WHITE);
        signUpPanel.add(nameLabel);
    }

    private void nameField() {
        nameField = new JTextField(10);
        signUpPanel.add(nameField);
    }

    private void genderLabel() {
        JLabel genderLabel = new JLabel("성별");
        genderLabel.setForeground(Color.WHITE);
        signUpPanel.add(genderLabel);
    }

    private void selectGender() {
        JRadioButton femaleButton = new JRadioButton("여자");
        JRadioButton maleButton = new JRadioButton("남자");
        femaleButton.setOpaque(false);
        maleButton.setOpaque(false);

        ButtonGroup group = new ButtonGroup();

        group.add(femaleButton);
        group.add(maleButton);

        signUpPanel.add(femaleButton);
        signUpPanel.add(maleButton);

        femaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (femaleButton.isSelected()) {
                    female = femaleButton.getText();
                    return;
                }

                if (maleButton.isSelected()) {
                    male = maleButton.getText();
                    return;
                }
            }
        });
    }

    private void buttonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        buttonPanel.add(signUpButton());

        signUpPanel.add(buttonPanel);
    }

    private JButton signUpButton() {
        JButton signUpButton = new JButton("가입하기");
        signUpButton.addActionListener(event -> {
            String id = idTextField.getText();
            String password = passwordField.getText();
            String name = nameField.getText();
            String gender = "";

            if (!female.isBlank()) {
                male = "";
                gender = female;
            }

            if (!male.isBlank()) {
                female = "";
                gender = male;
            }

            if (id.length() == 0 || password.length() == 0 || name.length() == 0) {
                JOptionPane optionPane = new JOptionPane();

                optionPane.showMessageDialog(null, "빈 칸 없이 모두 기입해 주세요.", "Access denied", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            if (female.isBlank() && male.isBlank()) {
                JOptionPane optionPane = new JOptionPane();

                optionPane.showMessageDialog(null, "성별을 선택해 주세요.", "Access denied", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            User user = new User(id, password, name, gender);

            users.add(user);

            try {
                saveUsers();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            updatePanel(new InitLoginPanel(users));

        });

        return signUpButton;
    }

    private void saveUsers() throws IOException {
        UserFileManager userFileManager = new UserFileManager();

        userFileManager.saveUser(users);
    }

    private void updatePanel(JPanel panel) {
        this.removeAll();
        this.add(panel);
        this.setVisible(false);
        this.setVisible(true);
    }
}
