package com.checkers.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Az exportálás dialógus osztálya
 * A segítségével a felhasználó ki tudja választani, az exportálás részletei
 */
public class ExportDialog extends JDialog {
    private JTextField pathField;
    private JTextField fileNameField;
    private File selectedDirectory;
    private boolean confirmed = false;

    /**
     * Az exportálás dialógus konstruktora
     * @param owner a tartalmazó komponens
     */
    public ExportDialog(Frame owner) {
        super(owner, "Export Game", true);
        initComponents();
        pack();
        setLocationRelativeTo(owner);
    }

    /**
     * Az exportálás dialógus által tartalmazott komponenseket inicializálja
     */
    private void initComponents() {
        JLabel pathLabel = new JLabel("Save in:");
        pathField = new JTextField(20);
        pathField.setEditable(false);
        
        JButton browseBtn = new JButton("Browse...");
        browseBtn.addActionListener(e -> chooseDirectory());

        JLabel nameLabel = new JLabel("File Name:");
        fileNameField = new JTextField("game_export", 15);
        JLabel extensionLabel = new JLabel(".txt");

        JButton exportBtn = new JButton("Export");
        exportBtn.addActionListener(e -> {
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
                .addComponent(pathLabel)
                .addComponent(pathField)
                .addComponent(browseBtn))

            .addGroup(layout.createSequentialGroup()
                .addComponent(nameLabel)
                .addComponent(fileNameField)
                .addComponent(extensionLabel))

            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(exportBtn)
                .addComponent(cancelBtn))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(pathLabel)
                .addComponent(pathField)
                .addComponent(browseBtn))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nameLabel)
                .addComponent(fileNameField)
                .addComponent(extensionLabel))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(exportBtn)
                .addComponent(cancelBtn))
        );

        add(panel);
    }

    /**
     * A könyvtár kiválasztását végzi el
     */
    private void chooseDirectory() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectedDirectory = chooser.getSelectedFile();
            pathField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    /**
     * A kiválasztott könyvtár érvényességének ellenőrzését végzi el
     * @return kiválasztott könyvtár érvényes-e
     */
    private boolean validateInput() {
        if (selectedDirectory == null || !selectedDirectory.exists() || pathField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a directory.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (fileNameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a file name.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * @return ki lett-e választva érvényes fájl és meg van-e nyomva az export gomb
     */
    public boolean isConfirmed() {
        return confirmed;
    }

    /**
     * @return a kiválasztott .txt kiterkesztésú fájl
     */
    public File getSelectedFile() {
        String name = fileNameField.getText().trim();
        if (!name.toLowerCase().endsWith(".txt")) {
            name += ".txt";
        }
        return new File(selectedDirectory, name);
    }
}