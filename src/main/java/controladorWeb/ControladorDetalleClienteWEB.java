/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import logicaDeAccesoADatos.DAOCliente;
import logicaDeAccesoADatos.DAOClienteCuenta;
import logicaDeAccesoADatos.IDAOCliente;
import logicaDeAccesoADatos.IDAOClienteCuenta;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICuenta;
import singletonClasesUtilitarias.ConversionSingleton;

/**
 *
 * @author 
 */
@WebServlet(name = "ControladorDetalleClienteWEB", urlPatterns = {"/vistaWeb/DetalleCliente"})
public class ControladorDetalleClienteWEB extends HttpServlet {
    private Lista<ICuenta> consultarListaCuenta;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String identificacionClienteWEB = request.getParameter("identificacion");
        Conversion convertidorDeDatos = ConversionSingleton.instanciar();
        int identificacionCliente = convertidorDeDatos.convertirStringEnEntero(identificacionClienteWEB);
        
        Cuenta[] arregloDeCuentasDeCliente;
        IDAOCliente daoCliente = new DAOCliente();
        Cliente cliente = (Cliente) daoCliente.consultarCliente(identificacionCliente);
        
        
        IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
        int cantidadCuentasCliente = daoClienteCuenta.consultarCantidadDeCuentasDeCliente(identificacionCliente);
        if(cantidadCuentasCliente > 0){
            consultarListaCuenta = daoClienteCuenta.consultarCuentasDeCliente(identificacionCliente);
            arregloDeCuentasDeCliente = convertidorDeDatos.convertirListaCuentaEnArreglo(consultarListaCuenta, cantidadCuentasCliente);
            List<CuentaDto> cuentas =  new LinkedList<CuentaDto>();
            for (int i = 0; i < cantidadCuentasCliente; i++) {
                cuentas.add(new CuentaDto(arregloDeCuentasDeCliente[i].numeroCuenta));
            }
        
            request.setAttribute("cuentasAsociadas", cuentas);
        }
        
        request.setAttribute("codigo",cliente.getCodigo());
        
        int identificacionConvertidaString = cliente.identificacion;
        request.setAttribute("identificacion",""+identificacionConvertidaString);
        
        request.setAttribute("primerApellido",cliente.primerApellido);
        request.setAttribute("segundoApellido",cliente.segundoApellido);
        request.setAttribute("nombre",cliente.nombre);
        
        LocalDate fechaNacimiento = cliente.getFechaNacimiento();
        String fechaNacimientoConvertidaString = fechaNacimiento.toString();
        request.setAttribute("fechaNacimiento",fechaNacimientoConvertidaString);
        
        request.setAttribute("correoElectronico",cliente.correoElectronico);
        
        int telefonoConvertidoString = cliente.numeroTelefono;
        request.setAttribute("numeroTelefono",""+telefonoConvertidoString);
        
        
        
        request.getRequestDispatcher("DetalleCliente.jsp").forward(request, response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
    }
    
    public class CuentaDto {
        private String numeroCuenta;        

        public CuentaDto(String numeroCuenta) {
            this.numeroCuenta = numeroCuenta;
        }

        public String getNumeroDeCuenta() {
            return numeroCuenta;
        }
    }
}
