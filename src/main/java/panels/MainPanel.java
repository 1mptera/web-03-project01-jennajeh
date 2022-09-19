package panels;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.io.FileNotFoundException;

public class MainPanel extends JPanel {
    public MainPanel() {
        initButtonPanel();
    }

    private void initButtonPanel() {
        JPanel initButtonPanel = new JPanel();
        initButtonPanel.add(checkListButton());
        initButtonPanel.add(reviewButton());
        initButtonPanel.add(logoutButton());
        initButtonPanel.setOpaque(false);

        this.setOpaque(false);
        this.add(initButtonPanel);
    }

    private JButton checkListButton() {
        JButton checkListButton = new JButton("체크 리스트");
        checkListButton.addActionListener(event -> {
            try {
                updatePanel(new CheckListPanel());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        return checkListButton;
    }

    private JButton reviewButton() {
        JButton checkListButton = new JButton("리뷰 게시판");
        checkListButton.addActionListener(event -> {

        });

        return checkListButton;
    }

    private JButton logoutButton() {
        JButton checkListButton = new JButton("로그아웃");
        checkListButton.addActionListener(event -> {
            JOptionPane optionPane = new JOptionPane();

            JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.", "Cloud!", JOptionPane.PLAIN_MESSAGE);

            updatePanel(new InitLoginPanel());
        });

        return checkListButton;
    }

    private void updatePanel(JPanel panel) {
        this.removeAll();
        this.add(panel);
        this.setVisible(false);
        this.setVisible(true);
    }
}
