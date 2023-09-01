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
import vistaGUI.ConsultaTipoCambioDeCompra;

/**
 *
 * @author estadm
 */
public class ControladorConsultaTipoCambioDeCompra implements ActionListener{
    public ConsultaTipoCambioDeCompra vistaGUI;
    
    public ControladorConsultaTipoCambioDeCompra(ConsultaTipoCambioDeCompra pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnVolverTipoCambioCompra.addActionListener(this);
        consultarTipoDeCambioCompra();
    }
    
    public void consultarTipoDeCambioCompra(){
        TipoCambioBCCR tc = new TipoCambioBCCR();
        double tipoCompra = tc.obtenerValorCompra();
        
        vistaGUI.txtCambioCompra.setText(""+tipoCompra+" ₡");
        
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Consultar tipo cambio compra", "GUI");
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Volver")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
}
