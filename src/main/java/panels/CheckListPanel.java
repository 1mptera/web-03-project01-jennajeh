package panels;

import models.CheckList;
import models.Cities;
import models.City;
import utils.CheckListFileManager;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckListPanel extends JPanel {
    private CheckListFileManager checkListFileManager;

    private String data = "";
    private String text = "";
    private List<CheckList> checkLists;

    private JTextField textField;
    private JLabel titleLabel;
    private JPanel buttonPanel;
    private JPanel contentPanel;
    private JPanel inputPanel;
    private JPanel wholePanel;
    private JPanel addButtonPanel;

    public CheckListPanel() throws FileNotFoundException {
        initWholePanel();

        initButtonPanel();

        initContentPanel();

        inputPanel();

        checkListFileManager = new CheckListFileManager();

        checkLists = checkListFileManager.loadCheckList();
    }

    private void initWholePanel() {
        wholePanel = new JPanel();
        //wholePanel.setOpaque(false);
        wholePanel.setLayout(new BorderLayout());
        wholePanel.setBackground(Color.yellow);

        //this.setOpaque(false);
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridLayout(0, 1));
        this.add(wholePanel, BorderLayout.PAGE_START);
    }

    private void initButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        buttonPanel.add(mainButton());
        buttonPanel.add(logoutButton());

        wholePanel.add(buttonPanel);
    }

    private JButton mainButton() {
        JButton mainButton = new JButton("메인 화면");
        mainButton.addActionListener(event -> {
            updatePanel(new MainPanel());
        });

        return mainButton;
    }

    private JButton logoutButton() {
        JButton logoutButton = new JButton("로그아웃");
        logoutButton.addActionListener(event -> {
            JOptionPane optionPane = new JOptionPane();

            JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.", "Cloud!", JOptionPane.PLAIN_MESSAGE);

            updatePanel(new InitLoginPanel());
        });

        return logoutButton;
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.setBackground(Color.red);
        //contentPanel.setOpaque(false);

        JLabel label = new JLabel("체크리스트 관리");
        label.setHorizontalAlignment(JLabel.CENTER);
        contentPanel.add(label);
        //contentPanel.add(inputPanel());

        this.add(contentPanel);
    }

    private void inputPanel() {
        inputPanel = new JPanel();
        //inputPanel.setOpaque(false);
        inputPanel.setBackground(Color.GREEN);
        JLabel label = new JLabel("* 찜한 식당 *");
        inputPanel.add(label);

        textField = new JTextField(10);

        inputPanel.add(textField);

        makeComboBox();

        inputPanel.add(addLists());

        this.add(inputPanel);
    }

    private JButton addLists() {
        JButton button = new JButton("등록");
        button.addActionListener(event -> {
            text = textField.getText();

            if (text.equals("")) {
                return;
            }

            checkLists.add(new CheckList(data, text));

            try {
                checkListFileManager.saveLists(checkLists);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            CheckListDetailPanel checkListDetailPanel = new CheckListDetailPanel(checkLists);

            updatePanel(checkListDetailPanel);

            textField.setText("");
        });

        return button;
    }

    private void makeComboBox() {
        List<City> cities = new ArrayList<>(Cities.CITIES);

        JComboBox comboBox = new JComboBox();

        for (int i = 0; i < cities.size(); i += 1) {
            comboBox.addItem(cities.get(i).toString());
        }

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                data = "[" + cb.getItemAt(cb.getSelectedIndex()) + "]";
            }
        });

        inputPanel.add(comboBox);
    }

    public void updatePanel(JPanel panel) {
        this.removeAll();

        initWholePanel();
        initButtonPanel();
        initContentPanel();
        inputPanel();

        this.add(panel);

        this.setVisible(false);
        this.setVisible(true);
    }
}
