/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import validacion.ValidacionCuenta;
import vistaGUI.CambioDePinPrimeraEtapa;
import vistaGUI.CambioDePinSegundaEtapa;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorCambioDePinPrimeraEtapa implements ActionListener{
    public CambioDePinPrimeraEtapa vistaGUI;
    
    public ControladorCambioDePinPrimeraEtapa(CambioDePinPrimeraEtapa pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnAceptar.addActionListener(this);
        this.vistaGUI.btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Aceptar")) {
            IDAOCatalogoDeCuentas daoCuenta = new DAOCatalogoDeCuentas();
            String numeroDeCuenta = this.vistaGUI.txtNumeroCuenta.getText();
            boolean consultarExisteCuenta = daoCuenta.consultarSiExisteCuenta(numeroDeCuenta);
            if(consultarExisteCuenta) {
                boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
                if(cuentaEstaActiva) {
                CambioDePinSegundaEtapa vistaSegundaEtapa = new CambioDePinSegundaEtapa();
                ControladorCambioDePinSegundaEtapa controladorSegundaEtapa = new ControladorCambioDePinSegundaEtapa(numeroDeCuenta, vistaSegundaEtapa);
                controladorSegundaEtapa.vistaGUI.setVisible(true);
                controladorSegundaEtapa.vistaGUI.setLocationRelativeTo(null);
                this.vistaGUI.setVisible(false);
                }else{
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                }
            }
            else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
            }
        }
        if(evento.getActionCommand().equals("Cancelar")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
}
