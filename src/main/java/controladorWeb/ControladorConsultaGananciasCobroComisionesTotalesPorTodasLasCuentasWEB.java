package controladorWeb;

import clasesUtilitarias.Conversion;
import clasesUtilitarias.Ordenamiento;
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
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICuenta;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;
import singletonClasesUtilitarias.OrdenamientoSingleton;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorConsultaGananciasCobroComisionesTotalesPorTodasLasCuentasWEB", urlPatterns = {"/vistaWeb/ConsultaGananciasCobroComisionesTotalesPorTodasLasCuentas"})
public class ControladorConsultaGananciasCobroComisionesTotalesPorTodasLasCuentasWEB extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cuenta[] arregloCuentasDesordenadas;
        IDAOCatalogoDeCuentas daoCatalogoDeCuentas = new DAOCatalogoDeCuentas();
        int cantidadCuentas = daoCatalogoDeCuentas.consultarCantidadCuentas();
        
        Lista<ICuenta> consultarListaCuentas = daoCatalogoDeCuentas.consultarListaDeCuentas();
        
        Conversion convertidorDeDatos = ConversionSingleton.instanciar();
        arregloCuentasDesordenadas = convertidorDeDatos.convertirListaCuentaEnArreglo(consultarListaCuentas, cantidadCuentas);
        Ordenamiento ordenamientoDeCuentas = OrdenamientoSingleton.instanciar();
        Cuenta cuenta[] = ordenamientoDeCuentas.ordenarDescendentemente(arregloCuentasDesordenadas);
        
        List<OperacionDto> operacionesAMostrar =  new LinkedList<OperacionDto>();
        
        for (int i = 0; i < cantidadCuentas; i++) {
            String numeroCuentaConsultada = cuenta[i].numeroCuenta;
            IDAOOperacionCuenta operacionesCenta = new DAOOperacionCuenta();
            double cantidadDepositosRealizados = operacionesCenta.consultarMontoTotalCobradoComisionesPorDepositos(numeroCuentaConsultada);
            double cantidadRetirosRealizados = operacionesCenta.consultarMontoTotalCobradoComisionesPorRetiros(numeroCuentaConsultada);
            double cantidadRetirosDepositosRealizados = operacionesCenta.consultarMontoTotalCobradoComisionesPorRetirosYDepositos(numeroCuentaConsultada);

            operacionesAMostrar.add(new OperacionDto(numeroCuentaConsultada, String.format("%.2f",cantidadDepositosRealizados), String.format("%.2f",cantidadRetirosRealizados), String.format("%.2f",cantidadRetirosDepositosRealizados)));
        }
        
        request.setAttribute("operacionesAMostrar", operacionesAMostrar);
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Ganancias de comisiones en todas las cuentas", "Web");
        
        request.getRequestDispatcher("ConsultaGananciasCobroComisionesTotalesPorTodasLasCuentas.jsp").forward(request, response);
    }
    public class OperacionDto {
        private String numeroCuenta;
        private String depositos;
        private String retiros;
        private String depositosYRetiros;

        public OperacionDto(String pNumeroCuenta, String cantDepositos, String cantRetiros, String cantDepositosRetiros) {
            this.numeroCuenta = pNumeroCuenta;
            this.depositos = cantDepositos;
            this.retiros = cantRetiros;
            this.depositosYRetiros = cantDepositosRetiros;
        }
        public String getNumeroCuenta() {
            return numeroCuenta;
        }
        
        public String getDepositos() {
            return depositos;
        }

        public String getRetiros() {
            return retiros;
        }

        public String getDepositosYRetiros() {
            return depositosYRetiros;
        }

        
    }
}
