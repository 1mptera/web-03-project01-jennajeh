package panels;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanelWithoutId extends JPanel {

    public MainPanelWithoutId() {
        initButtonPanel();
    }

    private void initButtonPanel() {
        JPanel initButtonPanel = new JPanel();
        initButtonPanel.add(reviewButton());
        initButtonPanel.add(quitButton());
        initButtonPanel.setOpaque(false);

        this.setOpaque(false);
        this.add(initButtonPanel);
    }

    private JButton reviewButton() {
        JButton checkListButton = new JButton("리뷰 게시판");
        checkListButton.addActionListener(event -> {
            updatePanel(new ReviewPanelWithoutId());
        });

        return checkListButton;
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
