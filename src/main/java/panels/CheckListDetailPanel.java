package panels;

import models.CheckList;
import utils.CheckListFileManager;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

public class CheckListDetailPanel extends JPanel {
    private List<CheckList> checkLists;

    public CheckListDetailPanel(List<CheckList> checkLists) {
        this.checkLists = checkLists;

        this.setLayout(new GridLayout(0, 1));

        this.setOpaque(false);

        initLists();
    }

    private void initLists() {
        for (CheckList checkList : checkLists) {
            if (checkList.status().equals("delete")) {
                continue;
            }

            JPanel checkListsPanel = new JPanel();
            //checkListsPanel.setOpaque(false);
            checkListsPanel.setBackground(Color.BLUE);
            this.add(checkListsPanel);

            updateCheckLists(checkList, checkListsPanel);
        }
    }

    private void updateCheckLists(CheckList checkList, JPanel checkListsPanel) {
        JCheckBox checkBox = new JCheckBox();
        CheckListFileManager checkListFileManager = new CheckListFileManager();

        if (checkList.status().equals("gone")) {
            checkBox.setSelected(true);
        }

        checkBox.addActionListener(event -> {
            if (checkBox.isSelected()) {
                checkList.gone();

                saveLists(checkListFileManager);
            }

            if (!checkBox.isSelected()) {
                checkList.notChecked();
            }
        });

        checkListsPanel.add(checkBox);

        JLabel checkListTitleLabel = new JLabel(checkList.city() + " " + checkList.title());
        checkListsPanel.add(checkListTitleLabel);

        JButton deleteButton = new JButton("X");
        deleteButton.addActionListener(event -> {
            checkList.delete();

            saveLists(checkListFileManager);

            updateDisplay();
        });

        checkListsPanel.add(deleteButton);
    }

    private void saveLists(CheckListFileManager checkListFileManager) {
        try {
            checkListFileManager.saveLists(checkLists);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDisplay() {
        this.removeAll();
        this.initLists();
        this.setVisible(false);
        this.setVisible(true);
    }
}
