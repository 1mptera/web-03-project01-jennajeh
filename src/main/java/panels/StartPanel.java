package panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class StartPanel extends JPanel {
    private JPanel contentPanel;

    public StartPanel() {
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
            updatePanel(new InitLoginPanel());
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

    public void updatePanel(JPanel panel) {
        this.removeAll();
        this.add(panel);
        this.setVisible(false);
        this.setVisible(true);
    }
}
