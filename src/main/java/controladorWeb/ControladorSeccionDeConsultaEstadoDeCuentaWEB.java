
package controladorWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorSeccionDeConsultaEstadoDeCuentaWEB", urlPatterns = {"/vistaWeb/SeleccionDeConsultaEstadoDeCuenta"})
public class ControladorSeccionDeConsultaEstadoDeCuentaWEB extends HttpServlet {
    private String numeroCuenta;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        numeroCuenta = request.getParameter("numeroCuenta");
        request.setAttribute("numeroDeCuenta", numeroCuenta);
        
        request.getRequestDispatcher("SeleccionDeConsultaEstadoDeCuenta.jsp").forward(request, response);
        }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String event = request.getParameter("event");
        if(event.equals("Colones")) {
            response.sendRedirect("InformacionPorConsultaDeEstadoCuentaColones?numeroCuenta=" + numeroCuenta);
        }
        if(event.equals("Dolares")) {
            response.sendRedirect("InformacionPorConsultaDeEstadoCuentaDolares?numeroCuenta=" + numeroCuenta);
        }
    }
}
