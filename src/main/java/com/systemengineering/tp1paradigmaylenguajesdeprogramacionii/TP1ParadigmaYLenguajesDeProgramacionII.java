/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.systemengineering.tp1paradigmaylenguajesdeprogramacionii;

import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.controllers.MenuController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author José Fernando Usui y Luciana Rojas
 */
public class TP1ParadigmaYLenguajesDeProgramacionII {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                MenuController menuController = new MenuController();
                menuController.iniciar();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(TP1ParadigmaYLenguajesDeProgramacionII.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
