
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
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorInformacionPorConsultaDeEstadoCuentaColonesWEB", urlPatterns = {"/vistaWeb/InformacionPorConsultaDeEstadoCuentaColones"})
public class ControladorInformacionPorConsultaDeEstadoCuentaColonesWEB extends HttpServlet {
    private String numeroCuenta;
    Lista<Operacion> operaciones;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        numeroCuenta = request.getParameter("numeroCuenta");
        
        Operacion[] operacionesTotales;
        IDAOCuentaIndividual cuenta = new DAOCuentaIndividual();
        Cuenta cuentaRecibida = (Cuenta) cuenta.consultarCuenta(numeroCuenta);
        
        Cliente clientePropietario = (Cliente) cuentaRecibida.propietario;
        IDAOOperacionCuenta operacion = new DAOOperacionCuenta();
        
        int cantidadOperaciones = operacion.consultarCantidadOperaciones(numeroCuenta);
        System.out.println(cantidadOperaciones);

        request.setAttribute("numeroCuenta", cuentaRecibida.numeroCuenta);
        
        if(cantidadOperaciones > 0){
         operaciones = operacion.consultarOperacionesCuenta(numeroCuenta);
         Conversion convertidorDeDatos = ConversionSingleton.instanciar();
         operacionesTotales = convertidorDeDatos.convertirListaOperacionEnArreglo(operaciones, cantidadOperaciones);
         
         List<OperacionDto> operacionesAMostrar =  new LinkedList<>();
        for (int i = 0; i < cantidadOperaciones; i++) {
            boolean aplicaComision = operacionesTotales[i].seAplicoComision;
            String aplicaComisionSiNo;
            if (aplicaComision == true){
                aplicaComisionSiNo = "Sí";
            }else{
                aplicaComisionSiNo = "No";
            }
            operacionesAMostrar.add(new OperacionDto(operacionesTotales[i].tipoOperacion, operacionesTotales[i].fechaOperacion,aplicaComisionSiNo, operacionesTotales[i].montoComision));
        }
        
        request.setAttribute("operacionesAsociadas", operacionesAMostrar);
        }
        
        request.setAttribute("numeroCuenta", cuentaRecibida.numeroCuenta);
        
        request.setAttribute("saldo", String.format("%.2f", cuentaRecibida.getSaldo()));
        request.setAttribute("propietario", clientePropietario.nombre +" "+ clientePropietario.primerApellido +" "+ clientePropietario.segundoApellido);
        request.setAttribute("correoElectronico", clientePropietario.correoElectronico);
        request.setAttribute("numeroTelefono", clientePropietario.numeroTelefono);
        
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Consulta estado de cuenta en colones", "Web");
        
        request.getRequestDispatcher("InformacionPorConsultaDeEstadoCuentaColones.jsp").forward(request, response);
        }
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        }
    
    public class OperacionDto {
        private String tipoOperacion;
        private LocalDate fecha;
        private double monto;
        private String comision;

        public OperacionDto(String pTipoOperacion, LocalDate pFecha, String pComision, double pMonto) {
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

        public double getMonto() {
            return monto;
        }
        public String getComision() {
            return comision;
        }

        
    }
}
