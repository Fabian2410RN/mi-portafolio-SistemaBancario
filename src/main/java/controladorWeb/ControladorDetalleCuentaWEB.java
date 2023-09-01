/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorDetalleCuentaWEB", urlPatterns = {"/vistaWeb/DetalleCuenta"})
public class ControladorDetalleCuentaWEB extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroCuenta = request.getParameter("numeroCuenta");
        
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuenta = (Cuenta) daoCuenta.consultarCuenta(numeroCuenta);
        Cliente cliente = (Cliente) cuenta.propietario;
        String nombreDePropietario = cliente.nombre +" " +cliente.primerApellido +" "+ cliente.segundoApellido;
        request.setAttribute("numeroCuenta", cuenta.numeroCuenta);
        request.setAttribute("fechaCreacion", cuenta.fechaCreacion);
        request.setAttribute("saldo", cuenta.getSaldo());
        request.setAttribute("estatus", cuenta.estatus);
        IDAOOperacionCuenta obtenerCantidadRetirosYDepositos = new DAOOperacionCuenta();
        
        int cantidadOperaciones = obtenerCantidadRetirosYDepositos.consultarCantidadDeDepositosYRetirosRealizados(numeroCuenta);
        request.setAttribute("cantidadDepositosYRetiros", cantidadOperaciones);
        request.setAttribute("nombrePropietario", nombreDePropietario);
        
        request.getRequestDispatcher("DetalleCuenta.jsp").forward(request, response);
        
        }
   
}
