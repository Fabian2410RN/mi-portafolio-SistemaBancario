/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import controlador.MensajeEnPantallaCuenta;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeNegocios.RegistroGeneralBitacoras;
import serviciosExternos.TipoCambioBCCR;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import validacion.ValidacionCuenta;

/**
 *
 * @author sebashdez
 */
@WebServlet(name = "ControladorConsultaDeSaldoActualWEB", urlPatterns = {"/vistaWeb/ConsultaDeSaldoActual"})
public class ControladorConsultaDeSaldoActualWEB extends HttpServlet {
    private int cantidadDeIntentos;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("ConsultaDeSaldoActual.jsp").forward(request, response);
        this.cantidadDeIntentos = 0;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String numeroDeCuenta = request.getParameter("numeroCuenta");
        String pin = request.getParameter("pin");
        this.cantidadDeIntentos++;
        boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuenta);
        if(existeCuenta) {
            boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
            if(cuentaEstaActiva) {
                boolean pinCorrespondeACuenta = ValidacionCuenta.validarPinCorrespondeACuenta(numeroDeCuenta, pin);
                if(pinCorrespondeACuenta) {
                    String event = request.getParameter("event");
                    if(event.equals("Consultar en colones")) {
                        IDAOCuentaIndividual cuentaAconsultarColones = new DAOCuentaIndividual();
                        double saldoActualColones = cuentaAconsultarColones.consultarSaldoActual(numeroDeCuenta);
                        MensajeEnPantallaCuenta.imprimirMensajeSaldoCuentaActualColones(saldoActualColones);
                        
                        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
                        accion.registrarEnBitacoras(LocalDate.now(), "Consulta de saldo actual en colones", "Web");
                        response.sendRedirect("../index.html");
                    }
                    if(event.equals("Consultar en dolares")) {
                        TipoCambioBCCR tc = new TipoCambioBCCR();
                        IDAOCuentaIndividual cuentaAconsultarDolares = new DAOCuentaIndividual();
                        double saldoActualColones = cuentaAconsultarDolares.consultarSaldoActual(numeroDeCuenta);
                        double valorDeCompra = tc.obtenerValorCompra();
                        double saldoConvertidoADolares = saldoActualColones / valorDeCompra;
                        
                        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
                        accion.registrarEnBitacoras(LocalDate.now(), "Consulta de saldo actual en dólares", "Web");
                        MensajeEnPantallaCuenta.imprimirMensajeSaldoCuentaActualDolares(saldoConvertidoADolares, valorDeCompra);
                        response.sendRedirect("../index.html");
                    }
                } else {
                    if(this.cantidadDeIntentos == 1) {
                        MensajeEnPantallaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(numeroDeCuenta, pin);
                        request.getRequestDispatcher("ConsultaDeSaldoActual.jsp").forward(request, response);
                    }
                    else {
                        validacion.ValidacionCuenta.inactivarCuenta(numeroDeCuenta);
                        response.sendRedirect("../index.html");
                    }
                    }
            }else{
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                response.sendRedirect("../index.html");
            }
        } else {
            MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
            response.sendRedirect("../index.html");
          }
    }
}
