/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladorWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controlador.ControladorDepositoConTipoDeCambio;
import java.time.LocalDate;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorDepositoConTipoDeCambioWEB", urlPatterns = {"/vistaWeb/DepositoConTipoDeCambio"})
public class ControladorDepositoConTipoDeCambioWEB extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("DepositoConTipoDeCambio.jsp").forward(request, response);
    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
         String cuenta = request.getParameter("numeroCuenta");
        String montoDeposito = request.getParameter("montoDepositado");
        
        response.setContentType("text/html; charset=UTF-8");
        
        if (ControladorDepositoConTipoDeCambio.depositarDolaresACuenta(cuenta,montoDeposito) == true){
            RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
            accion.registrarEnBitacoras(LocalDate.now(), "Depósito en dólares", "Web");
            response.sendRedirect("../index.html");
        } else {
            request.getRequestDispatcher("DepositoConTipoDeCambio.jsp").forward(request, response);
        }
    }
}
