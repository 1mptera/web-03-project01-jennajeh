package panels;

import models.User;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;

public class StartPanel extends JPanel {
    private JPanel contentPanel;
    private List<User> users;

    public StartPanel(List<User> users) {
        this.users = users;

        initButtonPanel();
    }

    private void initButtonPanel() {
        JPanel panel = new JPanel();
        panel.add(startButton());
        panel.add(quitButton());
        panel.setOpaque(false);

        this.add(panel, BorderLayout.PAGE_START);
        this.setOpaque(false);
    }

    private JButton startButton() {
        JButton startButton = new JButton("시작하기");
        startButton.addActionListener(event -> {
            updatePanel(new InitLoginPanel(users));
        });

        return startButton;
    }

    private JButton quitButton() {
        JButton quitButton = new JButton("종료하기");
        quitButton.addActionListener(event -> {
            System.exit(0);
        });

        return quitButton;
    }

    private void updatePanel(JPanel panel) {
        this.removeAll();
        this.add(panel);
        this.setVisible(false);
        this.setVisible(true);
    }
}
