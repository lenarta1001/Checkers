package com.checkers.control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.checkers.view.MainFrame;

/**
 * A főablak vezérlő osztálya
 */
public class MainWindowListener extends WindowAdapter {
    private MainFrame mainFrame;

    /**
     * A főablak vezérlő konstruktora
     * @param mainFrame a főablak
     */
    public MainWindowListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * A kilépés kezelését végzi el
     * @param e a kilépés miatt létrejövő esemény
     */
    @Override
    public void windowClosing(WindowEvent e) {
        mainFrame.handleExit();
    }
}
