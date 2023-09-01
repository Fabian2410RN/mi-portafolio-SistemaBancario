/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import validacion.ValidacionCuenta;
import vistaGUI.TransferenciasEntreCuentas;
import vistaGUI.VerificacionMensajeDeTexto;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorTransferenciasEntreCuentas implements ActionListener{
    public TransferenciasEntreCuentas vistaGUI;
    private int cantidadDeIntentos = 0;
    
    public ControladorTransferenciasEntreCuentas(TransferenciasEntreCuentas pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnContinuarTransferencia.addActionListener(this);
        this.vistaGUI.btnCancelarTransferencia.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Continuar")) {
            this.cantidadDeIntentos++;
            String numeroDeCuenta = this.vistaGUI.txtNumeroCuentaOrigen.getText();
            String pin = this.vistaGUI.txtPinCuentaOrigen.getText();
            boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuenta);
            boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
            if(existeCuenta) {
                if(cuentaEstaActiva) {
                boolean pinCorrespondeACuenta = ValidacionCuenta.validarPinCorrespondeACuenta(numeroDeCuenta, pin);
                if(pinCorrespondeACuenta) {
                    VerificacionMensajeDeTexto vistaVerificacionMensajeDeTexto = new VerificacionMensajeDeTexto();
                    ControladorVerificacionMensajeDeTexto controladorVerificacionMensajeDeTexto = new ControladorVerificacionMensajeDeTexto(vistaVerificacionMensajeDeTexto, numeroDeCuenta, "Transferencia");
                    controladorVerificacionMensajeDeTexto.vistaGUI.setVisible(true);
                    controladorVerificacionMensajeDeTexto.vistaGUI.setLocationRelativeTo(null);
                    this.vistaGUI.setVisible(false);
                    MensajeEnPantallaCuenta.imprimirMensajeNotificacionDeEnvioDeMensaje();
                }
                else {
                    if(this.cantidadDeIntentos == 1) {
                        MensajeEnPantallaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(numeroDeCuenta, pin);
                    }
                    else {
                        validacion.ValidacionCuenta.inactivarCuenta(numeroDeCuenta);
                    }
                }
                } else{
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
