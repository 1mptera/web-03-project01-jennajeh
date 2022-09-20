package panels;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;

public class InitLoginPanel extends JPanel {
    private JPanel initInputPanel;
    private JPanel initButtonPanel;
    private JPanel contentPanel;

    public InitLoginPanel() {
        initInputPanel();

        initButtonPanel();
    }

    private void initInputPanel() {
        initInputPanel = new JPanel();
        initInputPanel.setLayout(new GridLayout(0, 1));
        initInputPanel.setBackground(new Color(0, 0, 0, 122));
        initInputPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        initInputPanel.setPreferredSize(new Dimension(450, 250));

        this.add(initInputPanel);
        this.setOpaque(false);

        inputLoginInfo();
    }

    private void inputLoginInfo() {
        initInputPanel.add(userIdLabel());
        initInputPanel.add(userIdTextField());
        initInputPanel.add(userPwdLabel());
        initInputPanel.add(userPwdTextField());
    }

    private JLabel userIdLabel() {
        JLabel userIdLabel = new JLabel("아이디");
        userIdLabel.setForeground(Color.WHITE);
        return userIdLabel;
    }

    private TextField userIdTextField() {
        TextField userIdTextField = new TextField(10);
        return userIdTextField;
    }

    private JLabel userPwdLabel() {
        JLabel userPwdLabel = new JLabel("비밀번호");
        userPwdLabel.setForeground(Color.WHITE);
        return userPwdLabel;
    }

    private TextField userPwdTextField() {
        TextField userPwdTextField = new TextField(10);
        userPwdTextField.selectAll();
        userPwdTextField.setEchoChar('*');
        return userPwdTextField;
    }

    private void initButtonPanel() {
        initButtonPanel = new JPanel();
        initButtonPanel.setOpaque(false);
        initButtonPanel.add(loginButton());
        initButtonPanel.add(signUpButton());
        initButtonPanel.add(quitButton());

        initInputPanel.add(initButtonPanel);
    }

    private JButton loginButton() {
        JButton loginButton = new JButton("로그인");
        loginButton.addActionListener(event -> {
            updateContentPanel(new MainPanel());
        });

        return loginButton;
    }

    private JButton signUpButton() {
        JButton signUpButton = new JButton("회원가입");
        signUpButton.addActionListener(event -> {

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

    private void updateContentPanel(JPanel panel) {
        this.removeAll();
        this.add(panel);
        this.setVisible(false);
        this.setVisible(true);
    }
}
