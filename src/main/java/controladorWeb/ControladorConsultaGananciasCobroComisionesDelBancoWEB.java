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
import logicaDeAccesoADatos.DAOOperacionCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOOperacionCatalogoDeCuentas;
import logicaDeNegocios.RegistroGeneralBitacoras;
import serviciosExternos.TipoCambioBCCR;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;

/**
 *
 * @author sebashdez
 */
@WebServlet(name = "ControladorConsultaGananciasCobroComisionesDelBancoWEB", urlPatterns = {"/vistaWeb/ConsultaGananciasCobroComisionesDelBanco"})
public class ControladorConsultaGananciasCobroComisionesDelBancoWEB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        IDAOOperacionCatalogoDeCuentas comisionesCobradas = new DAOOperacionCatalogoDeCuentas();
        double comisionesTotalesDeposito = comisionesCobradas.consultarMontoTotalCobradoComisionesPorDepositos();
        double comisionesTotalesRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetiros();
        double comisionesTotalesDepositoRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetirosYDepositos();
        
        request.setAttribute("montoDepositos", (String.format("%.2f",comisionesTotalesDeposito)));
        request.setAttribute("montoRetiros", (String.format("%.2f",comisionesTotalesRetiros)));
        request.setAttribute("montoDepositosYRetiros", (String.format("%.2f",comisionesTotalesDepositoRetiros)));
        
        TipoCambioBCCR tc = new TipoCambioBCCR();
        double tipoCompra = tc.obtenerValorCompra();
        
        request.setAttribute("montoDepositosDolares", (String.format("%.2f",comisionesTotalesDeposito/tipoCompra)));
        request.setAttribute("montoRetirosDolares", (String.format("%.2f",comisionesTotalesRetiros/tipoCompra)));
        request.setAttribute("montoDepositosYRetirosDolares", (String.format("%.2f",comisionesTotalesDepositoRetiros/tipoCompra)));
        
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Consulta de ganancias por cobro del banco", "Web");
        
        request.getRequestDispatcher("ConsultaGananciasCobroComisionesDelBanco.jsp").forward(request, response);
    }
}
