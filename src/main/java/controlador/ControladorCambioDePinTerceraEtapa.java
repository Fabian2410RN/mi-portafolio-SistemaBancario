/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import vistaGUI.CambioDePinTerceraEtapa;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorCambioDePinTerceraEtapa implements ActionListener{
    private String numeroDeCuenta;
    public CambioDePinTerceraEtapa vistaGUI;
    
    public ControladorCambioDePinTerceraEtapa(String pNumeroDeCuenta, CambioDePinTerceraEtapa pVistaGUI) {
        this.numeroDeCuenta = pNumeroDeCuenta;
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnAceptar.addActionListener(this);
        this.vistaGUI.btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Aceptar")) {
            String nuevoPin = this.vistaGUI.txtPinNuevo.getText();
            if (registrarCambioDePin(nuevoPin, numeroDeCuenta)){
                
                RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
                accion.registrarEnBitacoras(LocalDate.now(), "Cambio de pin", "GUI");
                
                MensajeEnPantallaCuenta.imprimirMensajeCambioDePinExitoso(numeroDeCuenta);
                ControladorMenuPrincipal.volverMenuPrincipal();
                vistaGUI.setVisible(false);
            }
        }
        if(evento.getActionCommand().equals("Cancelar")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
    
    public static boolean registrarCambioDePin(String nuevoPin, String numeroCuentaCambiada){
        if(validacion.ValidacionCuenta.validarFormatoDePin(nuevoPin)) {
            IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
            daoCuenta.cambiarPin(numeroCuentaCambiada, nuevoPin);
            return true;
        }
        return false;
    }

}
