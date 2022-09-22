import models.CurrentUser;
import models.Review;
import models.User;
import panels.ImagePanel;
import panels.StartPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.util.List;

public class Fries {
    private List<User> users;
    private List<Review> reviews;
    private CurrentUser currentUser;

    private JFrame frame;
    private JPanel contentPanel;
    private JPanel imagePanel;

    public static void main(String[] args) throws FileNotFoundException {
        Fries application = new Fries();
        application.run();
    }

    public Fries() throws FileNotFoundException {
//        currentUser = new CurrentUser("");
//
//        UserFileManager userFileManager = new UserFileManager();
//        users = userFileManager.loadUserLists();
//
//        ReviewFileManager reviewFileManager = new ReviewFileManager();
//        reviews = reviewFileManager.loadReviews();
    }

    public void run() {
        frame = new JFrame("Fries!");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // TODO: 맨 마지막에 주석 해제할 것
        //frame.setResizable(false);
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
        contentPanel.removeAll();
        contentPanel.add(new StartPanel());
        contentPanel.setVisible(false);
        contentPanel.setVisible(true);
    }
}
