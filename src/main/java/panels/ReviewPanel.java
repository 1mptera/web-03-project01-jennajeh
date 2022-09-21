package panels;

import models.Review;
import models.User;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

public class ReviewPanel extends JPanel {
    private List<User> users;
    String choice = "";

    private JPanel buttonPanel;
    private JPanel contentPanel;
    private JButton searchButton;
    private JTextField searchTextField;
    private JPanel searchPanel;
    private JPanel listsPanel;
    private JPanel underPanel;

    public ReviewPanel(List<User> users) {
        this.users = users;
        
        initButtonPanel();

        initContentPanel();

        listsPanel();
    }

    private void initButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        buttonPanel.add(mainButton());
        buttonPanel.add(checkListButton());
        buttonPanel.add(createReviewButton());
        buttonPanel.add(logoutButton());

        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.add(buttonPanel, BorderLayout.PAGE_START);
    }

    private JButton mainButton() {
        JButton mainButton = new JButton("메인 화면");
        mainButton.addActionListener(event -> {
            updateContentPanel(new MainPanel());
        });

        return mainButton;
    }

    private JButton checkListButton() {
        JButton checkListButton = new JButton("체크 리스트");
        checkListButton.addActionListener(event -> {
            try {
                updateContentPanel(new CheckListPanel());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        return checkListButton;
    }

    private JButton createReviewButton() {
        JButton createReviewButton = new JButton("글쓰기");
        createReviewButton.addActionListener(event -> {
            // TODO: 나중에 프레임 창 띄워서 글 쓰기 생성하기
            //WriteFrame writeFrame = new WriteFrame();
            Review review = new Review("서브웨이 성수점", "hello");

            JLabel title = new JLabel(review.title());
            JLabel id = new JLabel(review.userId());

            listsPanel.add(title);
            listsPanel.add(id);

            test();

        });
        return createReviewButton;
    }

    private JButton logoutButton() {
        JButton logoutButton = new JButton("로그아웃");
        logoutButton.addActionListener(event -> {
            JOptionPane optionPane = new JOptionPane();

            JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.", "Fries!", JOptionPane.PLAIN_MESSAGE);

            updateContentPanel(new InitLoginPanel(users));
        });

        return logoutButton;
    }

    private void initContentPanel() {
        contentPanel = new JPanel();

        contentPanel.setBackground(new Color(0, 0, 0, 122));
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(620, 400));

        contentPanel.add(searchPanel(), BorderLayout.PAGE_START);
        contentPanel.add(underPanel(), BorderLayout.PAGE_END);
        this.add(contentPanel);
    }

    private JPanel searchPanel() {
        searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        searchPanel.setOpaque(false);

        makeComboBox();

        searchTextField = new JTextField(10);
        searchPanel.add(searchTextField);

        searchButton = new JButton("검색");
        searchPanel.add(searchButton);

        return searchPanel;
    }

    private void makeComboBox() {
        String[] category = {"제목", "작성자"};

        JComboBox comboBox = new JComboBox();

        for (int i = 0; i < category.length; i += 1) {
            comboBox.addItem(category[i].toString());
        }

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                choice = (String) cb.getItemAt(cb.getSelectedIndex());
            }
        });

        searchPanel.add(comboBox);
    }

    private void listsPanel() {
        listsPanel = new JPanel();
        listsPanel.setOpaque(false);
        // TODO: GridLayout 사이즈 나중에 바꿔야함.
        listsPanel.setLayout(new BorderLayout());
        listsPanel.setBorder(new LineBorder(Color.BLACK, 1));
        listsPanel.setPreferredSize(new Dimension(550, 300));
        contentPanel.add(listsPanel, BorderLayout.PAGE_START);

        JPanel categoryPanel = new JPanel();
        categoryPanel.setOpaque(false);
        listsPanel.add(categoryPanel);

        JLabel label = new JLabel("제목");
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(280, 20));
        label.setBackground(Color.BLACK);
        categoryPanel.add(label);

        JLabel label2 = new JLabel("작성자");
        label2.setForeground(Color.WHITE);
        label2.setPreferredSize(new Dimension(50, 20));
        label.setBackground(Color.pink);
        categoryPanel.add(label2);

        listsPanel.add(eachReviewPanel());
    }

    private JPanel eachReviewPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255, 122));
        panel.setPreferredSize(new Dimension(480, 10));
        // TODO: 고정값으로 설정해놓은것!! 나중에 바꿔야함
        JLabel titleLabel = new JLabel("[서울] 성수 위드번");
        titleLabel.setBackground(Color.darkGray);
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel);

        JLabel userIdLabel = new JLabel("hello");
        userIdLabel.setBackground(Color.BLACK);
        userIdLabel.setForeground(Color.WHITE);
        panel.add(userIdLabel);

        return panel;
    }

    private JPanel underPanel() {
        underPanel = new JPanel();
        underPanel.setOpaque(false);

        JButton button = new JButton("1");
        underPanel.add(button);

        return underPanel;
    }

    private void updateContentPanel(JPanel panel) {
        this.removeAll();
        this.add(panel);

        this.setVisible(false);
        this.setVisible(true);
    }

    private void test() {
        this.removeAll();

        initButtonPanel();

        initContentPanel();

        listsPanel();

        this.setVisible(false);
        this.setVisible(true);
    }
}
