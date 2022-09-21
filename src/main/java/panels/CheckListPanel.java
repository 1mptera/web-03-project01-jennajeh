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
import java.awt.FlowLayout;
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
    private JPanel buttonPanel;
    private JPanel contentPanel;
    private JPanel inputPanel;
    private JPanel wholePanel;
    private JPanel fieldPanel;
    private JPanel initListsPanel;

    public CheckListPanel() throws FileNotFoundException {
        initWholePanel();

        initButtonPanel();

        initContentPanel();

        inputPanel();

        checkListFileManager = new CheckListFileManager();

        checkLists = checkListFileManager.loadCheckList();

        initListsPanel(new CheckListDetailPanel(checkLists));
    }

    private void initWholePanel() {
        wholePanel = new JPanel();
        wholePanel.setBackground(new Color(0, 0, 0, 122));
        wholePanel.setLayout(new BorderLayout());

        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(wholePanel, BorderLayout.PAGE_START);
    }

    private void initButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setOpaque(false);

        buttonPanel.add(mainButton());
        buttonPanel.add(logoutButton());

        wholePanel.add(buttonPanel);
    }

    private JButton mainButton() {
        JButton mainButton = new JButton("메인 화면");
        mainButton.addActionListener(event -> {
            updateContentPanel(new MainPanel());
        });

        return mainButton;
    }

    private JButton logoutButton() {
        JButton logoutButton = new JButton("로그아웃");
        logoutButton.addActionListener(event -> {
            JOptionPane optionPane = new JOptionPane();

            JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.", "Fries!", JOptionPane.PLAIN_MESSAGE);

            updateContentPanel(new InitLoginPanel());
        });

        return logoutButton;
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.setOpaque(false);

        JLabel label = new JLabel("체크리스트 관리");
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);

        contentPanel.add(label);
        buttonPanel.add(contentPanel, BorderLayout.PAGE_START);
    }

    private void inputPanel() {
        inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        contentPanel.add(inputPanel, BorderLayout.PAGE_START);

        fieldPanel = new JPanel();
        fieldPanel.setOpaque(false);

        JLabel label = new JLabel("* 찜한 식당 *");
        label.setForeground(Color.WHITE);
        fieldPanel.add(label);

        textField = new JTextField(10);
        fieldPanel.add(textField);

        makeComboBox();

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

        fieldPanel.add(button);

        inputPanel.add(fieldPanel);
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

        fieldPanel.add(comboBox);
    }

    private void initListsPanel(JPanel panel) {
        initListsPanel = new JPanel();

        initListsPanel.add(panel);

        initListsPanel.setOpaque(false);

        this.add(initListsPanel);
    }

    private void updatePanel(JPanel panel) {
        initListsPanel.removeAll();
        initListsPanel.add(panel);

        initListsPanel.setVisible(false);
        initListsPanel.setVisible(true);
    }

    private void updateContentPanel(JPanel panel) {
        this.removeAll();
        this.add(panel);

        this.setVisible(false);
        this.setVisible(true);
    }

}
