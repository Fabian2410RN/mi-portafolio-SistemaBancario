/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import controlador.ControladorMenuPrincipal;
import vistaGUI.MenuPrincipal;

/**
 *
 * @author sebashdez
 */
public class appGUI {
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        MenuPrincipal vista = new MenuPrincipal();
        ControladorMenuPrincipal controladorSala = new ControladorMenuPrincipal(vista);
        controladorSala.vistaGUI.setVisible(true);
        controladorSala.vistaGUI.setLocationRelativeTo(null);
    }
}
