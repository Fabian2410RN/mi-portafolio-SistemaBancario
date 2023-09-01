package controladorWeb;

import clasesUtilitarias.Conversion;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import listaDinamica.Lista;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.Operacion;
import logicaDeNegocios.RegistroGeneralBitacoras;
import serviciosExternos.TipoCambioBCCR;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorInformacionPorConsultaDeEstadoCuentaDolaresWEB", urlPatterns = {"/vistaWeb/InformacionPorConsultaDeEstadoCuentaDolares"})
public class ControladorInformacionPorConsultaDeEstadoCuentaDolaresWEB extends HttpServlet {
    private String numeroCuenta;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        numeroCuenta = request.getParameter("numeroCuenta");
        
        TipoCambioBCCR tc = new TipoCambioBCCR();
        
        Operacion[] operacionesTotales;
        IDAOCuentaIndividual cuenta = new DAOCuentaIndividual();
        Cuenta cuentaRecibida = (Cuenta) cuenta.consultarCuenta(numeroCuenta);
        
        Cliente clientePropietario = (Cliente) cuentaRecibida.propietario;
        IDAOOperacionCuenta operacion = new DAOOperacionCuenta();
        
        int cantidadOperaciones = operacion.consultarCantidadOperaciones(numeroCuenta);
        request.setAttribute("numeroCuenta", cuentaRecibida.numeroCuenta);
        double obtenerSaldoColones = cuentaRecibida.getSaldo();
        double saldoConvertidoADolares = obtenerSaldoColones / tc.obtenerValorCompra();
        request.setAttribute("saldo", String.format("%.2f", saldoConvertidoADolares)+" $");
        request.setAttribute("propietario", clientePropietario.nombre +" "+ clientePropietario.primerApellido +" "+ clientePropietario.segundoApellido);
        request.setAttribute("correoElectronico", clientePropietario.correoElectronico);
        request.setAttribute("numeroTelefono", clientePropietario.numeroTelefono);
        
        if(cantidadOperaciones > 0){
        Lista<Operacion> operaciones = operacion.consultarOperacionesCuenta(numeroCuenta);
        Conversion convertidorDeDatos = ConversionSingleton.instanciar();
        operacionesTotales = convertidorDeDatos.convertirListaOperacionEnArreglo(operaciones, cantidadOperaciones);
       
        List<OperacionDto> operacionesAMostrar =  new LinkedList<>();
        for (int i = 0; i < cantidadOperaciones; i++) {
            boolean aplicaComision = operacionesTotales[i].seAplicoComision;
            double montoComisionN = operacionesTotales[i].montoComision;
            double montoComisionEnDolares = montoComisionN / tc.obtenerValorCompra();
            String montoComisionEnDolaresConvertidoAString = String.format("%.2f",montoComisionEnDolares);
            String aplicaComisionSiNo;
            if (aplicaComision == true){
                aplicaComisionSiNo = "Sí";
            }else{
                aplicaComisionSiNo = "No";
            }
            operacionesAMostrar.add(new OperacionDto(operacionesTotales[i].tipoOperacion, operacionesTotales[i].fechaOperacion,aplicaComisionSiNo, montoComisionEnDolaresConvertidoAString));
        }
        
        request.setAttribute("operacionesAsociadas", operacionesAMostrar);
        }
        
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Consulta estado de cuenta en dólares", "Web");
        
        request.getRequestDispatcher("InformacionPorConsultaDeEstadoCuentaDolares.jsp").forward(request, response);
        }
    
    public class OperacionDto {
        private String tipoOperacion;
        private LocalDate fecha;
        private String monto;
        private String comision;

        public OperacionDto(String pTipoOperacion, LocalDate pFecha, String pComision, String pMonto) {
            this.tipoOperacion = pTipoOperacion;
            this.fecha = pFecha;
            this.comision = pComision;
            this.monto = pMonto;
        }

        public String getTipoOperacion() {
            return tipoOperacion;
        }

        public LocalDate getFecha() {
            return fecha;
        }

        public String getMonto() {
            return monto;
        }
        public String getComision() {
            return comision;
        }
    }
   
}
