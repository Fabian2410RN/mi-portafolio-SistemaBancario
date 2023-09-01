/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import clasesUtilitarias.Conversion;
import controlador.MensajeEnPantallaCuenta;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.RegistroGeneralBitacoras;
import serviciosExternos.TipoCambioBCCR;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;
import validacion.ValidacionCuenta;

/**
 *
 * @author sebashdez
 */
@WebServlet(name = "ControladorSolicitarMontoDeRetiroEnDolaresTerceraEtapaWEB", urlPatterns = {"/vistaWeb/SolicitarMontoDeRetiroEnDolaresTerceraEtapa"})
public class ControladorSolicitarMontoDeRetiroEnDolaresTerceraEtapaWEB extends HttpServlet {
    private String numeroDeCuenta;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        numeroDeCuenta = request.getParameter("numeroCuenta");
        request.setAttribute("numeroDeCuenta", numeroDeCuenta);
        
        request.getRequestDispatcher("SolicitarMontoDeRetiroEnDolaresTerceraEtapa.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroDeCuentaOrigen = request.getParameter("numeroDeCuenta");
        String montoDeRetiroEnDolares = request.getParameter("montoRetirado");
        boolean formatoDeMontoDeRetiroEsCorrecto = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(montoDeRetiroEnDolares);
        if(formatoDeMontoDeRetiroEsCorrecto) {
            boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuentaOrigen);
            if(existeCuenta) {
                boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuentaOrigen);
                if(cuentaEstaActiva) {
                    Conversion convertidorDeDatos = ConversionSingleton.instanciar();
                    double montoDeRetiroEnDolaresEnFormatoDecimal = convertidorDeDatos.convertirStringEnDecimal(montoDeRetiroEnDolares);
                    TipoCambioBCCR tipoDeCambioDolar = new TipoCambioBCCR();
                    double tipoDeCambioDeDolarVenta = tipoDeCambioDolar.obtenerValorVenta();
                    double montoARetirarEnColones = montoDeRetiroEnDolaresEnFormatoDecimal * tipoDeCambioDeDolarVenta;
                    double montoComision = calcularMontoComision(montoARetirarEnColones, numeroDeCuentaOrigen);
                    boolean hayFondosSuficientes = ValidacionCuenta.validarHayFondosSuficientes(numeroDeCuentaOrigen, montoARetirarEnColones + montoComision);
                    if(hayFondosSuficientes) {
                        efectuarRetiro(montoARetirarEnColones, montoDeRetiroEnDolaresEnFormatoDecimal, tipoDeCambioDeDolarVenta, numeroDeCuentaOrigen);
                        response.sendRedirect("../index.html");
                    }
                    else {
                        request.setAttribute("numeroDeCuenta", numeroDeCuenta);
                        MensajeEnPantallaCuenta.imprimirMensajeDeErrorFondosInsuficientes();
                        request.getRequestDispatcher("SolicitarMontoDeRetiroEnDolaresTerceraEtapa.jsp?numeroCuenta=" + numeroDeCuentaOrigen).forward(request, response);
                    }
                }
                else {
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                    response.sendRedirect("../index.html");
                }
            }
            else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuentaOrigen);
                response.sendRedirect("../index.html");
            }
        }
        else {
            MensajeEnPantallaCuenta.imprimirMensajeDeErrorFormatoDeMontoDeRetiroIncorrecto();
            request.getRequestDispatcher("SolicitarMontoDeRetiroEnDolaresTerceraEtapa.jsp?numeroCuenta=" + numeroDeCuentaOrigen).forward(request, response);
        }
    }
    
    private double calcularMontoComision(double pMontoPorRetirar, String numeroDeCuenta) {
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadDeRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(numeroDeCuenta);
        double montoComision = 0.0;
        if(cantidadDeRetirosYDepositosRealizados >= 3) {
            montoComision += pMontoPorRetirar * 0.02;
        }
        return montoComision;
    }
    
    private void efectuarRetiro(double pMontoRetiradoColones, double pMontoRetiradoDolares, double pTipoCambio, String numeroDeCuenta) {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuenta = (Cuenta) daoCuenta.consultarCuenta(numeroDeCuenta);
        cuenta.retirar(pMontoRetiradoColones);
        double montoComision = calcularMontoComision(pMontoRetiradoColones, numeroDeCuenta);
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Retiro en dólares", "Web");
        MensajeEnPantallaCuenta.imprimirMensajeRetiroEnDolaresExitoso(pMontoRetiradoColones, pMontoRetiradoDolares, pTipoCambio, montoComision);
    }
}
