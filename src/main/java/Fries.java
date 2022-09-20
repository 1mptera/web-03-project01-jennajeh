import panels.ImagePanel;
import panels.StartPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Fries {
    private JFrame frame;
    private JPanel contentPanel;
    private JPanel imagePanel;
    private StartPanel startPanel;

    public static void main(String[] args) {
        Fries application = new Fries();
        application.run();
    }

    public void run() {
        frame = new JFrame("Fries!");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        initImagePanel();

        initContentPanel();

        initStartPanel();

        frame.setVisible(true);
    }

    private void initImagePanel() {
        imagePanel = new ImagePanel("src/main/resources/img.png");
        imagePanel.setLayout(new BorderLayout());
        frame.add(imagePanel);
    }

    public void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        imagePanel.add(contentPanel);
    }

    public void initStartPanel() {
        startPanel = new StartPanel();

        contentPanel.removeAll();
        contentPanel.add(startPanel);
        contentPanel.setVisible(false);
        contentPanel.setVisible(true);
    }
}
