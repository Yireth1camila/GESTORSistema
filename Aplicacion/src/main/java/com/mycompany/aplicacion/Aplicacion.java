/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.aplicacion;

import javax.swing.SwingUtilities;

public class Aplicacion {

    public static void main(String[] args) {
        // Lanzar la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            new AplicacionGUI().setVisible(true);
        });
    }
}

