/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import listaDinamica.Lista;
import vistaGUI.InformacionPorConsultaDeEstadoCuentaColones;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeNegocios.Operacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import listaDinamica.Nodo;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import vistaGUI.ConsultaEstadoDeCuenta;

/**
 *
 * @author estadm
 */
public class ControladorInformacionPorConsultaDeEstadoCuentaColones implements ActionListener{
    public InformacionPorConsultaDeEstadoCuentaColones vistaGUI;
    private String numeroCuenta;
    
    public ControladorInformacionPorConsultaDeEstadoCuentaColones(InformacionPorConsultaDeEstadoCuentaColones pVistaGUI ,String pNumeroCuenta){
        this.vistaGUI = pVistaGUI;
        numeroCuenta = pNumeroCuenta;
        this.vistaGUI.btnVolverEstadoCuentaColones.addActionListener(this);
        Operacion[] arregloOperaciones;
        IDAOCuentaIndividual cuenta = new DAOCuentaIndividual();
        Cuenta cuentaRecibida = (Cuenta) cuenta.consultarCuenta(numeroCuenta);
        
        Cliente clientePropietario = (Cliente) cuentaRecibida.propietario;
        IDAOOperacionCuenta operacion = new DAOOperacionCuenta();
        Lista<Operacion> operaciones = operacion.consultarOperacionesCuenta(pNumeroCuenta);
        
        
        this.vistaGUI.txtNumeroCuentaEstadoColones.setText(cuentaRecibida.numeroCuenta);
        double obtenerSaldo = cuentaRecibida.getSaldo();
        this.vistaGUI.txtSaldoActualEstadoColones.setText(""+obtenerSaldo);
        this.vistaGUI.txtPropietarioEstadoColones.setText(clientePropietario.nombre);
        this.vistaGUI.txtCorreoEstadoColones.setText(clientePropietario.correoElectronico);
        int obtenerNumeroCliente = clientePropietario.numeroTelefono;
        this.vistaGUI.txtNumeroEstadoColones.setText(""+obtenerNumeroCliente);
        
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Consulta estado de cuenta en colones", "GUI");
        
        cargarDatosAOperacion(operaciones);
        
    }
    
    public void cargarDatosAOperacion(Lista<Operacion> operacionesCuenta){
        JTable tabla = this.vistaGUI.tblEstadoDeCuentaColones; 
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("Tipo de operación");
        model.addColumn("Fecha");
        model.addColumn("Comisión");
        model.addColumn("Monto cobrado");
        
        
        Nodo puntero = operacionesCuenta.inicio;
        while(puntero != null) {
            Operacion obtenerOperacion = (Operacion)puntero.objeto;
            String tipoOperacion = obtenerOperacion.tipoOperacion;
            LocalDate fechaOperacion = obtenerOperacion.fechaOperacion;
            String fechaOperacionConvertidaString = fechaOperacion.toString();
            double montoComision = obtenerOperacion.montoComision;
            String montoComisionConvertidaADouble = ""+montoComision;
            boolean aplicaComision = obtenerOperacion.seAplicoComision;
            String aplicaComisionSiNo;
            if (aplicaComision == true){
                aplicaComisionSiNo = "Sí";
            }else{
                aplicaComisionSiNo = "No";
            }
            model.addRow(new Object[]{tipoOperacion, fechaOperacionConvertidaString, aplicaComisionSiNo, montoComisionConvertidaADouble+" ₡"});
            puntero = puntero.siguiente;
        }
        tabla.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Volver")) {
        ConsultaEstadoDeCuenta vistaConsultaEstadoDeCuenta = new ConsultaEstadoDeCuenta();
        ControladorConsultaEstadoDeCuenta controladorConsultaEstadoDeCuenta = new ControladorConsultaEstadoDeCuenta(vistaConsultaEstadoDeCuenta);
        controladorConsultaEstadoDeCuenta.vistaGUI.setVisible(true);
        controladorConsultaEstadoDeCuenta.vistaGUI.setLocationRelativeTo(null);
        vistaGUI.setVisible(false);
        }
    }
}
