/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import clasesUtilitarias.PalabraSecreta;
import controlador.MensajeEnPantallaCuenta;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicaDeAccesoADatos.DAOClienteCuenta;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOClienteCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeNegocios.Cliente;
import serviciosExternos.EnvioCorreoElectronico;
import serviciosExternos.EnvioMensajeDeTexto;
import singletonClasesUtilitarias.PalabraSecretaSingleton;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorVerificacionMensajeDeTextoWEB", urlPatterns = {"/vistaWeb/VerificacionMensajeDeTexto"})
public class ControladorVerificacionMensajeDeTextoWEB extends HttpServlet {
    private int cantidadDeIntentos = 0;
    private String numeroDeCuenta;
    private int numeroDeTelefono;
    private String mensajeSecreto;
    private String transaccionAsociada;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
            numeroDeCuenta = request.getParameter("numeroCuenta");
            IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
            Cliente clienteAsociadoACuenta = (Cliente) daoClienteCuenta.consultarClienteAsociadoACuenta(numeroDeCuenta);
            this.cantidadDeIntentos = 0;
            this.numeroDeTelefono = clienteAsociadoACuenta.numeroTelefono;
            enviarMensajeDeTexto();
            MensajeEnPantallaCuenta.imprimirMensajeNotificacionDeEnvioDeMensaje();
            request.getRequestDispatcher("VerificacionMensajeDeTexto.jsp").forward(request, response);
            
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            
            request.setAttribute("numeroDeCuenta", numeroDeCuenta);
            String mensajeSecretoIngresado = request.getParameter("mensajeTexto");
            this.cantidadDeIntentos++;
            
            if(this.mensajeSecreto.equals(mensajeSecretoIngresado)) {
                response.sendRedirect("SolicitarMontoDepositoYCuentaDestinoDeTransferencia?numeroCuentaOrigen=" + numeroDeCuenta);
            }else{
                if(this.cantidadDeIntentos == 1) {
                    MensajeEnPantallaCuenta.imprimirMensajeAdvertenciaSegundoIntentoPalabraSecreta();
                    request.setAttribute("error", "El texto ingresado no es valido");
                    request.setAttribute("numeroDeCuenta", numeroDeCuenta);
                    request.getRequestDispatcher("VerificacionMensajeDeTexto.jsp?numeroCuenta=" + numeroDeCuenta).forward(request, response);
                }
                else {
                    this.inactivarCuenta();
                    response.sendRedirect("../index.html");
                }
            }
        }
    
    
    private void enviarMensajeDeTexto() {
        EnvioMensajeDeTexto mensajeDeTexto = new EnvioMensajeDeTexto();
        PalabraSecreta generadorDePalabraSecreta = PalabraSecretaSingleton.instanciar();
        this.mensajeSecreto = generadorDePalabraSecreta.generarPalabraSecreta();
        String mensaje = "Estimado usuario de la cuenta: " + this.numeroDeCuenta + " su palabra secreta es: \n";
        mensaje += this.mensajeSecreto + "\n";
        mensaje += "Ingrese esta palabra correctamente para proceder con su retiro";
        
        mensajeDeTexto.enviarMensaje(String.valueOf(this.numeroDeTelefono), mensaje);
    }
    private void inactivarCuenta() {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        daoCuenta.actualizarEstatus(this.numeroDeCuenta, "Inactiva");
        MensajeEnPantallaCuenta.imprimirMensajeAlertaDeInactivacionDeCuenta();
        IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
        Cliente clienteAsociadoACuenta = (Cliente) daoClienteCuenta.consultarClienteAsociadoACuenta(this.numeroDeCuenta);
        String correoDestinatario = clienteAsociadoACuenta.correoElectronico;
        String mensajeDeCorreo = "";
        mensajeDeCorreo += "Estimado cliente: su cuenta " + this.numeroDeCuenta + " ha sido desactividada \n";
        mensajeDeCorreo += "La inactivación se debe a que realizó muchos intentos de validación de retiro/transferencia \n";
        String asuntoDeCorreo = "Inactivación de cuenta " + this.numeroDeCuenta;
        EnvioCorreoElectronico.enviarCorreo(correoDestinatario, asuntoDeCorreo, mensajeDeCorreo);
    }
}
