/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.RegistroGeneralBitacoras;
import serviciosExternos.TipoCambioBCCR;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorConsultaGananciasCobroComisionesTotalesPorCadaCuentaWEB", urlPatterns = {"/vistaWeb/ConsultaGananciasCobroComisionesTotalesPorCadaCuenta"})
public class ControladorConsultaGananciasCobroComisionesTotalesPorCadaCuentaWEB extends HttpServlet {
    private String numeroCuenta;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        numeroCuenta = request.getParameter("numeroCuenta");
        TipoCambioBCCR tc = new TipoCambioBCCR();
        double tipoCompra = tc.obtenerValorCompra();
        IDAOOperacionCuenta operacionTipo = new DAOOperacionCuenta();
        double montoTotalDepositos = operacionTipo.consultarMontoTotalCobradoComisionesPorDepositos(numeroCuenta);
        double montoTotalRetiros = operacionTipo.consultarMontoTotalCobradoComisionesPorRetiros(numeroCuenta);
        double montoTotalDepositosRetiros = operacionTipo.consultarMontoTotalCobradoComisionesPorRetirosYDepositos(numeroCuenta);
        request.setAttribute("montoDepositos",String.format("%.2f",montoTotalDepositos));
        request.setAttribute("montoRetiros", String.format("%.2f",montoTotalRetiros));
        request.setAttribute("montoDepositosYRetiros",String.format("%.2f",montoTotalDepositosRetiros));
        
        request.setAttribute("montoDepositosDolares", String.format("%.2f",montoTotalDepositos/tipoCompra));
        request.setAttribute("montoRetirosDolares", String.format("%.2f",montoTotalRetiros/tipoCompra));
        request.setAttribute("montoDepositosYRetirosDolares", String.format("%.2f",montoTotalDepositosRetiros/tipoCompra));
        
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Ganancias por comisiones en cada cuenta", "Web");
        
        request.getRequestDispatcher("ConsultaGananciasCobroComisionesTotalesPorCadaCuenta.jsp").forward(request, response);
        }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        }
}
