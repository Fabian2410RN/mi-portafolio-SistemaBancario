
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import serviciosExternos.TipoCambioBCCR;
import java.time.LocalDate;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import listaDinamica.Lista;
import listaDinamica.Nodo;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.RegistroGeneralBitacoras;
import logicaDeNegocios.Operacion;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import vistaGUI.ConsultaEstadoDeCuenta;
import vistaGUI.InformacionPorConsultaDeEstadoCuentaDolares;

/**
 *
 * @author estadm
 */
public class ControladorInformacionPorConsultaDeEstadoCuentaDolares implements ActionListener{
    public InformacionPorConsultaDeEstadoCuentaDolares vistaGUI;
    private String numeroCuenta;
    
    public ControladorInformacionPorConsultaDeEstadoCuentaDolares(InformacionPorConsultaDeEstadoCuentaDolares pVistaGUI ,String pNumeroCuenta){
        this.vistaGUI = pVistaGUI;
        this.numeroCuenta = pNumeroCuenta;
        this.vistaGUI.btnVolverEstadoCuentaDolares.addActionListener(this);
        IDAOCuentaIndividual cuenta = new DAOCuentaIndividual();
        Cuenta cuentaRecibida = (Cuenta) cuenta.consultarCuenta(numeroCuenta);
        
        Cliente clientePropietario = (Cliente) cuentaRecibida.propietario;
        IDAOOperacionCuenta operacion = new DAOOperacionCuenta();
        Lista<Operacion> operaciones = operacion.consultarOperacionesCuenta(pNumeroCuenta);
        
        this.vistaGUI.txtNumeroCuentaEstadoDolares.setText(cuentaRecibida.numeroCuenta);
        double obtenerSaldo = cuentaRecibida.getSaldo();
        TipoCambioBCCR tc = new TipoCambioBCCR();
        double saldoConvertidoADolares = obtenerSaldo / tc.obtenerValorCompra();
        this.vistaGUI.txtSaldoActualEstadoDolares.setText(String.format("%.2f",saldoConvertidoADolares)+" $");
        this.vistaGUI.txtPropietarioEstadoDolares.setText(clientePropietario.nombre);
        this.vistaGUI.txtCorreoEstadoDolares.setText(clientePropietario.correoElectronico);
        int obtenerNumeroCliente = clientePropietario.numeroTelefono;
        this.vistaGUI.txtNumeroEstadoDolares.setText(""+obtenerNumeroCliente);
        
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Consulta estado de cuenta en dólares", "GUI");
        
        cargarDatosAOperacion(operaciones);
        
    }
    
    public void cargarDatosAOperacion(Lista<Operacion> operacionesCuenta){
        JTable tabla = this.vistaGUI.tblEstadoDeCuentaDolares; 
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
            TipoCambioBCCR tc = new TipoCambioBCCR();
            double montoComisionEnDolares = montoComision / tc.obtenerValorCompra();
            boolean aplicaComision = obtenerOperacion.seAplicoComision;
            String aplicaComisionSiNo;
            if (aplicaComision == true){
                aplicaComisionSiNo = "Sí";
            }else{
                aplicaComisionSiNo = "No";
            }
            model.addRow(new Object[]{tipoOperacion, fechaOperacionConvertidaString, aplicaComisionSiNo, String.format("%.2f", montoComisionEnDolares)+" $"});
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
