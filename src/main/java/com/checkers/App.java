package com.checkers;

import com.checkers.view.MainFrame;

/**
 * A fő osztály
 */
public class App {
    /**
     * Létrehoz egy főablakot és elindítja azt
     * @param args a programnak adott argumentumok
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}