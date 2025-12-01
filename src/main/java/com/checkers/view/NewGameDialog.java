package com.checkers.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Az új játék dialógus osztálya
 * A segítségével a felhasználó ki tudja választani, az új játék indításának részletei
 */
public class NewGameDialog extends JDialog {
    private JComboBox<String> colorCombo;
    private JComboBox<String> opponentCombo;
    private JCheckBox importCheck;
    private JTextField filePathField;
    
    private boolean confirmed = false;
    private File selectedFile;

    /**
     * Az új játék dialógus konstruktora
     * @param owner a tartalmazó komponens
     */
    public NewGameDialog(Frame owner) {
        super(owner, "New Game Settings", true);
        initComponents();
        pack();
        setLocationRelativeTo(owner);
    }

    /**
     * Az új játék dialógus által tartalmazott komponenseket inicializálja
     */
    private void initComponents() {
        JLabel colorLabel = new JLabel("Your Colour:");
        String[] colors = {"Black (Starts)", "White"};
        colorCombo = new JComboBox<>(colors);
        colorCombo.setEnabled(false);

        JLabel opponentLabel = new JLabel("Opponent:");
        String[] opponents = {"Player vs Player", "Player vs Computer"};
        opponentCombo = new JComboBox<>(opponents);
  
        importCheck = new JCheckBox("Import game from file");
        
        filePathField = new JTextField(20);
        filePathField.setEditable(false);
        filePathField.setEnabled(false);
        
        JButton browseBtn = new JButton("Browse...");
        browseBtn.setEnabled(false);
        browseBtn.addActionListener(e -> chooseFile());
        
        
        importCheck.addActionListener(e -> {
            browseBtn.setEnabled(importCheck.isSelected());
        });
        
        opponentCombo.addActionListener(e -> {
            colorCombo.setEnabled(isVsComputer());
        });
        
        JButton startBtn = new JButton("Start Game");
        startBtn.addActionListener(e -> {
            if (validateInput()) {
                confirmed = true;
                dispose();
            }
        });

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> dispose());

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(colorLabel)
                    .addComponent(opponentLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(colorCombo)
                    .addComponent(opponentCombo)))
            .addComponent(importCheck)
            .addGroup(layout.createSequentialGroup()
                .addComponent(filePathField)
                .addComponent(browseBtn))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(startBtn)
                .addComponent(cancelBtn))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(colorLabel)
                .addComponent(colorCombo))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(opponentLabel)
                .addComponent(opponentCombo))
            .addGap(10)
            .addComponent(importCheck)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(filePathField)
                .addComponent(browseBtn))
            .addGap(10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(startBtn)
                .addComponent(cancelBtn))
        );
        add(panel);
    }

    /**
     * A fájl kiválasztását végzi el
     */
    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Game File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedFile.getAbsolutePath());
        }
    }

    /**
     * A kiválasztott fájl érvényességének ellenőrzését végzi el
     * @return kiválasztott fájl érvényes-e
     */
    private boolean validateInput() {
        if (importCheck.isSelected() && (selectedFile == null || !selectedFile.exists())) {
            JOptionPane.showMessageDialog(this, "Please select a valid file to import.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * @return ki lett-e választva érvényes fájl és meg van-e nyomva a start gomb
     */
    public boolean isConfirmed() { return confirmed; }

    /**
     * @return a kezdőjátékos fekete-e
     */
    public boolean isBlack() { return colorCombo.getSelectedIndex() == 0; }

    /**
     * @return a játéktípus bot elleni-e
     */
    public boolean isVsComputer() { return opponentCombo.getSelectedIndex() == 1; }

    /**
     * @return a játék kezdetét importáljuk-e
     */
    public boolean isImport() { return importCheck.isSelected(); }

    /**
     * @return a kiválasztott fájl
     */
    public File getSelectedFile() { return selectedFile; }
}