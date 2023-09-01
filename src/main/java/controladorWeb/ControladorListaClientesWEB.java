/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import logicaDeAccesoADatos.DAOCatalogoDeClientes;
import logicaDeAccesoADatos.IDAOCatalogoDeClientes;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.ICliente;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;
import singletonClasesUtilitarias.OrdenamientoSingleton;

/**
 *
 * @author sebashdez
 */
@WebServlet(name = "ControladorListaClientesWEB", urlPatterns = {"/vistaWeb/ListaClientes"})
public class ControladorListaClientesWEB extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        
        Cliente[] arregloClientesDesordenados;
        IDAOCatalogoDeClientes daoCatalogoDeClientes = new DAOCatalogoDeClientes();
        int cantidadDeClientes = daoCatalogoDeClientes.consultarCantidadDeClientes();
        
        Lista<ICliente> consultarListaCliente = daoCatalogoDeClientes.consultarListaDeClientes();
        
        Conversion convertidorDeDatos = ConversionSingleton.instanciar();
        arregloClientesDesordenados = convertidorDeDatos.convertirListaClienteEnArreglo(consultarListaCliente, cantidadDeClientes);
        Ordenamiento ordenamientoDeClientes = OrdenamientoSingleton.instanciar();
        Cliente cliente[] = ordenamientoDeClientes.ordenarAscendentemente(arregloClientesDesordenados);
        List<ClienteDto> dtos =  new LinkedList<ClienteDto>();
      
        for (int i = 0; i < cantidadDeClientes; i++) {
            dtos.add(new ClienteDto(cliente[i].primerApellido, cliente[i].segundoApellido, cliente[i].nombre, cliente[i].identificacion));
        }
        
        request.setAttribute("dtos", dtos);
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Listar clientes", "Web");
        request.getRequestDispatcher("ListaClientes.jsp").forward(request, response);
    }
    
    public class ClienteDto {
        private String primerApellido;        
        private String segundoApellido;
        private String nombre;
        private int identificacion;

        public ClienteDto(String primerApellido, String segundoApellido, String nombre, int identificacion) {
            this.primerApellido = primerApellido;
            this.segundoApellido = segundoApellido;
            this.nombre = nombre;
            this.identificacion = identificacion;
        }

        public String getPrimerApellido() {
            return primerApellido;
        }

        public String getSegundoApellido() {
            return segundoApellido;
        }

        public String getNombre() {
            return nombre;
        }

        public int getIdentificacion() {
            return identificacion;
        }
    }
}
