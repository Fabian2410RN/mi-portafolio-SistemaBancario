/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import logicaDeNegocios.RegistroGeneralBitacoras;
import serviciosExternos.TipoCambioBCCR;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import vistaGUI.ConsultaTipoCambioDeVenta;

/**
 *
 * @author estadm
 */
public class ControladorConsultaTipoCambioDeVenta implements ActionListener{
    public ConsultaTipoCambioDeVenta vistaGUI;
    
    public ControladorConsultaTipoCambioDeVenta(ConsultaTipoCambioDeVenta pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnVolverTipoCambioVenta.addActionListener(this);
        consultarTipoDeCambioVenta();
    }
    
    public void consultarTipoDeCambioVenta(){
        TipoCambioBCCR tc = new TipoCambioBCCR();
        double tipoVenta = tc.obtenerValorVenta();
        
        vistaGUI.txtCambioVenta.setText(""+tipoVenta+" ₡");
        
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Consultar tipo cambio venta", "GUI");
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Volver")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
}
