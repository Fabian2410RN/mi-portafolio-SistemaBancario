/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import vistaGUI.ConsultaGananciasCobroComisionesTotalesPorCadaCuenta;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.RegistroGeneralBitacoras;
import serviciosExternos.TipoCambioBCCR;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;

/**
 *
 * @author estadm
 */
public class ControladorConsultaGananciasCobroComisionesTotalesPorCadaCuenta implements ActionListener{
    public ConsultaGananciasCobroComisionesTotalesPorCadaCuenta vistaGUI;
    public String numeroCuenta;
    
    public ControladorConsultaGananciasCobroComisionesTotalesPorCadaCuenta(ConsultaGananciasCobroComisionesTotalesPorCadaCuenta pVistaGUI, String pNumeroCuenta) {
        this.vistaGUI = pVistaGUI;
        this.numeroCuenta = pNumeroCuenta;
        this.vistaGUI.btnVolverComision.addActionListener(this);
        mostrarTotaleCuentaIndividual();
    }
    
    public void mostrarTotaleCuentaIndividual(){
        
        TipoCambioBCCR tc = new TipoCambioBCCR();
        double tipoCompra = tc.obtenerValorCompra();
        IDAOOperacionCuenta operacionTipo = new DAOOperacionCuenta();
        double montoTotalDepositos = operacionTipo.consultarMontoTotalCobradoComisionesPorDepositos(numeroCuenta);
        double montoTotalRetiros = operacionTipo.consultarMontoTotalCobradoComisionesPorRetiros(numeroCuenta);
        double montoTotalDepositosRetiros = operacionTipo.consultarMontoTotalCobradoComisionesPorRetirosYDepositos(numeroCuenta);
        vistaGUI.txtMontoTotalDepositoCuentaIndividualColones.setText(String.format("%.2f",montoTotalDepositos)+" ₡");
        vistaGUI.txtMontoTotalRetiroCuentaIndividualColones.setText(String.format("%.2f",montoTotalRetiros)+" ₡");
        vistaGUI.txtMontoTotalDepositoRetiroCuentaIndividualColones.setText(String.format("%.2f",montoTotalDepositosRetiros)+" ₡");
        
        vistaGUI.txtMontoTotalDepositoCuentaIndividualDolares.setText(String.format("%.2f",montoTotalDepositos/tipoCompra)+" $");
        vistaGUI.txtMontoTotalRetiroCuentaIndividualDolares.setText(String.format("%.2f",montoTotalRetiros/tipoCompra)+" $");
        vistaGUI.txtMontoTotalDepositoRetiroCuentaIndividualDolares.setText(String.format("%.2f",montoTotalDepositosRetiros/tipoCompra)+" $");
        
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Ganancias por comisiones en cada cuenta", "GUI");
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Volver")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
    
}
