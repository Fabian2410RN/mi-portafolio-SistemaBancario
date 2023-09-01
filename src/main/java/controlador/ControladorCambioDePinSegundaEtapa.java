/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import vistaGUI.CambioDePinPrimeraEtapa;
import vistaGUI.CambioDePinSegundaEtapa;
import vistaGUI.CambioDePinTerceraEtapa;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorCambioDePinSegundaEtapa implements ActionListener{
    private String numeroDeCuenta;
    public CambioDePinSegundaEtapa vistaGUI;
    private int cantidadDeIntentos = 0;
    
    public ControladorCambioDePinSegundaEtapa(String pNumeroDeCuenta, CambioDePinSegundaEtapa pVistaGUI) {
        this.numeroDeCuenta = pNumeroDeCuenta;
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnAceptar.addActionListener(this);
        this.vistaGUI.btnCancelarCambioDePinSegundaEtapa.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Aceptar")) {
            IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
            String pin = this.vistaGUI.txtPinCuenta.getText();
            this.cantidadDeIntentos++;
            boolean pinCorrespondeACuenta = daoCuenta.verificarPinCorrespondeACuenta(this.numeroDeCuenta, pin);
            if(pinCorrespondeACuenta) {
                CambioDePinTerceraEtapa vistaTerceraEtapa = new CambioDePinTerceraEtapa();
                ControladorCambioDePinTerceraEtapa controladorTerceraEtapa = new ControladorCambioDePinTerceraEtapa(this.numeroDeCuenta, vistaTerceraEtapa);
                controladorTerceraEtapa.vistaGUI.setVisible(true);
                controladorTerceraEtapa.vistaGUI.setLocationRelativeTo(null);
                this.vistaGUI.setVisible(false);
            } else {
                if(this.cantidadDeIntentos == 1) {
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(numeroDeCuenta, pin);
                }
                else {
                    validacion.ValidacionCuenta.inactivarCuenta(numeroDeCuenta);
                }
                
            }
        }
        if(evento.getActionCommand().equals("Cancelar")) {
            CambioDePinPrimeraEtapa vistaCambioDePinPrimeraEtapa = new CambioDePinPrimeraEtapa();
            ControladorCambioDePinPrimeraEtapa controladorCambioDePinPrimeraEtapa = new ControladorCambioDePinPrimeraEtapa(vistaCambioDePinPrimeraEtapa);
            controladorCambioDePinPrimeraEtapa.vistaGUI.setVisible(true);
            controladorCambioDePinPrimeraEtapa.vistaGUI.setLocationRelativeTo(null);
           vistaGUI.setVisible(false);
       }
    }
    /*
    private void inactivarCuenta() {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        daoCuenta.actualizarEstatus(this.numeroDeCuenta, "Inactiva");
        MensajeEnPantallaCuenta.imprimirMensajeAlertaDeInactivacionDeCuenta();
    }*/
}
