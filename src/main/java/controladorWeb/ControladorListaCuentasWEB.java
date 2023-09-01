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
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICuenta;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;
import singletonClasesUtilitarias.OrdenamientoSingleton;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorListaCuentasWEB", urlPatterns = {"/vistaWeb/ListaCuentas"})
public class ControladorListaCuentasWEB extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cuenta[] arregloCuentasDesordenadas;
        IDAOCatalogoDeCuentas daoCatalogoDeCuentas = new DAOCatalogoDeCuentas();
        int cantidadDeCuentas = daoCatalogoDeCuentas.consultarCantidadCuentas();
        
        Lista<ICuenta> consultarListaCuentas = daoCatalogoDeCuentas.consultarListaDeCuentas();
        
        Conversion convertidorDeDatos = ConversionSingleton.instanciar();
        arregloCuentasDesordenadas = convertidorDeDatos.convertirListaCuentaEnArreglo(consultarListaCuentas, cantidadDeCuentas);
        Ordenamiento ordenamientoDeCuentas = OrdenamientoSingleton.instanciar();
        Cuenta cuenta[] = ordenamientoDeCuentas.ordenarDescendentemente(arregloCuentasDesordenadas);
        
        List<CuentaDto> dtos =  new LinkedList<CuentaDto>();
        
        for(int i = 0; i < cantidadDeCuentas; i++) {
            Cliente duenoDeCuenta = (Cliente) cuenta[i].propietario;
            String nombreCompletoDePropietarioDeCuenta = duenoDeCuenta.nombre +" "+ duenoDeCuenta.primerApellido +" "+ duenoDeCuenta.segundoApellido;
            int identificacionDePropietarioDeCuenta = duenoDeCuenta.identificacion;
            
            //String saldoString = String.format("%.2f", cuenta[i].getSaldo());
            double saldoConvertido = cuenta[i].getSaldo();
            
            dtos.add(new CuentaDto(cuenta[i].numeroCuenta, String.format("%.2f",saldoConvertido), cuenta[i].estatus, nombreCompletoDePropietarioDeCuenta, identificacionDePropietarioDeCuenta));
        }
        request.setAttribute("dtos", dtos);
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Listar cuentas", "Web");
        request.getRequestDispatcher("ListaCuentas.jsp").forward(request, response);
    }
    
    public class CuentaDto {
        private String numeroCuenta;        
        private String saldo;
        private String estatus;
        private String propietario;
        private int identificacion;

        public CuentaDto(String pNumeroCuenta, String pSaldo, String pEstatus, String pPropietario, int pIdentificacion) {
            this.numeroCuenta = pNumeroCuenta;
            this.saldo = pSaldo;
            this.estatus = pEstatus;
            this.propietario = pPropietario;
            this.identificacion = pIdentificacion;
        }

        public String getNumeroCuenta() {
            return numeroCuenta;
        }

        public String getSaldo() {
            return saldo;
        }

        public String getEstatus() {
            return estatus;
        }
        public String getPropietario() {
            return propietario;
        }

        public int getIdentificacion() {
            return identificacion;
        }
    }
}