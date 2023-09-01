/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import listaDinamica.Lista;
import vistaGUI.ConsultaGananciasCobroComisionesTotalesPorTodasLasCuentas;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeNegocios.Cuenta;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeNegocios.ICuenta;
import clasesUtilitarias.Conversion;
import java.time.LocalDate;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;

/**
 *
 * @author estadm
 */
public class ControladorConsultaGananciasCobroComisionesTotalesPorTodasLasCuentas implements ActionListener{
    public ConsultaGananciasCobroComisionesTotalesPorTodasLasCuentas vistaGUI;
    
    public ControladorConsultaGananciasCobroComisionesTotalesPorTodasLasCuentas(ConsultaGananciasCobroComisionesTotalesPorTodasLasCuentas pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnCancelarMontosCadaCuenta.addActionListener(this);
        this.vistaGUI.btnConsultarMontosCadaCuenta.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Consultar")) {
            RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
            accion.registrarEnBitacoras(LocalDate.now(), "Ganancias de comisiones en todas las cuentas", "GUI");
            Lista<ICuenta> listaCuentas;
            Cuenta[] arregloCuentasOrdenadas;
            
            IDAOCatalogoDeCuentas cuentas = new DAOCatalogoDeCuentas();
            int cantidadCuentas = cuentas.consultarCantidadCuentas();
            listaCuentas = cuentas.consultarListaDeCuentas();
            
            Conversion convertidorDeDatos = ConversionSingleton.instanciar();
            arregloCuentasOrdenadas = convertidorDeDatos.convertirListaCuentaEnArreglo(listaCuentas, cantidadCuentas);
            
            JTable tabla = this.vistaGUI.tblMontosTotalesDeCadaCuenta; 
        
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Cuenta");
            model.addColumn("Total de depósitos y retiros");
            model.addColumn("Total depósitos");
            model.addColumn("Total retiros");
            for (int i = 0; i < cantidadCuentas; i++) {
                String numeroDeCuenta = arregloCuentasOrdenadas[i].numeroCuenta;
                IDAOOperacionCuenta operacionesCenta = new DAOOperacionCuenta();
                double cantidadDepositosRealizados = operacionesCenta.consultarMontoTotalCobradoComisionesPorDepositos(numeroDeCuenta);
                double cantidadRetirosRealizados = operacionesCenta.consultarMontoTotalCobradoComisionesPorRetiros(numeroDeCuenta);
                double cantidadRetirosDepositosRealizados = operacionesCenta.consultarMontoTotalCobradoComisionesPorRetirosYDepositos(numeroDeCuenta);
                model.addRow(new Object[]{numeroDeCuenta, String.format("%.2f", cantidadRetirosDepositosRealizados) +" ₡",  String.format("%.2f",cantidadDepositosRealizados) +" ₡",  String.format("%.2f",cantidadRetirosRealizados)+" ₡"});
            }
        
            tabla.setModel(model);
            
        }
        if(evento.getActionCommand().equals("Cancelar")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
}
