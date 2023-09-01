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
import vistaGUI.ConsultaGananciasCobroComisionesTotalesPorCadaCuenta;
import vistaGUI.ConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa;

/**
 *
 * @author estadm
 */
public class ControladorConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa implements ActionListener{
    public ConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa vistaGUI;
    
    public ControladorConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa(ConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnVolverComision.addActionListener(this);
        this.vistaGUI.btnConsultarCuenta.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Consultar")) {
            IDAOCatalogoDeCuentas daoCuenta = new DAOCatalogoDeCuentas();
            String numeroDeCuenta = this.vistaGUI.txtNumeroCuentaConsultar.getText();
            boolean consultarExisteCuenta = daoCuenta.consultarSiExisteCuenta(numeroDeCuenta);
            if(consultarExisteCuenta) {
                 boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
                 if(cuentaEstaActiva) {
                     ConsultaGananciasCobroComisionesTotalesPorCadaCuenta vistaConsultaGananciasCobroComisionesTotalesPorCadaCuenta = new ConsultaGananciasCobroComisionesTotalesPorCadaCuenta();
                    ControladorConsultaGananciasCobroComisionesTotalesPorCadaCuenta controladorConsultaGananciasCobroComisionesTotalesPorCadaCuenta = new ControladorConsultaGananciasCobroComisionesTotalesPorCadaCuenta(vistaConsultaGananciasCobroComisionesTotalesPorCadaCuenta, numeroDeCuenta);
                    controladorConsultaGananciasCobroComisionesTotalesPorCadaCuenta.vistaGUI.setVisible(true);
                    controladorConsultaGananciasCobroComisionesTotalesPorCadaCuenta.vistaGUI.setLocationRelativeTo(null);
                    this.vistaGUI.setVisible(false);
                 }else{
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                }
                
            }else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
            }
        }
        if(evento.getActionCommand().equals("Volver")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
}
