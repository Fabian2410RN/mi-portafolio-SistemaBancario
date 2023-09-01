/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import vistaGUI.ConsultaEstadoDeCuenta;
import java.awt.event.ActionListener;
import validacion.ValidacionCuenta;
import vistaGUI.SeleccionDeConsultaEstadoDeCuenta;

/**
 *
 * @author estadm
 */
public class ControladorConsultaEstadoDeCuenta implements ActionListener{
    public ConsultaEstadoDeCuenta vistaGUI;
    private int cantidadDeIntentos = 0;    
    
    public ControladorConsultaEstadoDeCuenta(ConsultaEstadoDeCuenta pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnConsultarEstadoCuenta.addActionListener(this);
        this.vistaGUI.btnCancelarEstadoCuenta.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Consultar")) {
            this.cantidadDeIntentos++;
            String numeroDeCuenta = this.vistaGUI.txtNumeroCuentaEstadoCuenta.getText();
            String pin = this.vistaGUI.txtPinEstadoCuenta.getText();
            boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuenta);
            if(existeCuenta) {
                boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
                if(cuentaEstaActiva) {
                    boolean pinCorrespondeACuenta = ValidacionCuenta.validarPinCorrespondeACuenta(numeroDeCuenta, pin);
                    if(pinCorrespondeACuenta) {
                        SeleccionDeConsultaEstadoDeCuenta vistaSeccionConsultaEstadoCuenta = new SeleccionDeConsultaEstadoDeCuenta();
                        ControladorSeccionDeConsultaEstadoDeCuenta controladorSeccionConsultaEstadoCuenta = new ControladorSeccionDeConsultaEstadoDeCuenta(vistaSeccionConsultaEstadoCuenta, numeroDeCuenta);
                        controladorSeccionConsultaEstadoCuenta.vistaGUI.setVisible(true);
                        controladorSeccionConsultaEstadoCuenta.vistaGUI.setLocationRelativeTo(null);
                        vistaGUI.setVisible(false);
                    } else {
                    if(this.cantidadDeIntentos == 1) {
                        MensajeEnPantallaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(numeroDeCuenta, pin);
                    }
                    else {
                        validacion.ValidacionCuenta.inactivarCuenta(numeroDeCuenta);
                    }
                  }
                    
                }else{
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                }
            }else{
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
            }
            
        }
        if(evento.getActionCommand().equals("Cancelar")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }

}
