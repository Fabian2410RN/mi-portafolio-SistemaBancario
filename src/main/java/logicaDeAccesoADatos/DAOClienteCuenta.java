/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDeAccesoADatos;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Projections;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import listaDinamica.Lista;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICliente;
import logicaDeNegocios.ICuenta;
import org.bson.Document;

/**
 *
 * @author sebashdez
 */
public class DAOClienteCuenta implements IDAOClienteCuenta{
    public static MongoCollection coleccionClienteCuenta = ConexionBaseDeDatos.ConectarBase().getCollection("ClienteCuenta");
    /**
     *
     * @param pNumeroCuentaAsocidada
     * @param pIdentificacion
     * @return
     */
    
    @Override
    public boolean registrarCuentaACliente(String pNumeroCuentaAsocidada, int pIdentificacion) {
        try{
        
            Document CuentaACliente = new Document("numeroCuentaAsocidada", pNumeroCuentaAsocidada).append("identificacion", pIdentificacion);
            coleccionClienteCuenta.insertOne(CuentaACliente);
            
            return true;
            
        } catch (Error e){
            return false;
        }
    }

    @Override
    public Lista<ICuenta> consultarCuentasDeCliente(int pIdentificacion) {
        try {
            MongoCursor<Document> cursorClientesYCuentas = coleccionClienteCuenta.find().cursor();
            Lista<ICuenta> listaDeResultados = new Lista<>();
            while(cursorClientesYCuentas.hasNext()) {
                Document documentoClienteYCuenta = cursorClientesYCuentas.next();
                int identificacion = documentoClienteYCuenta.getInteger("identificacion");
                if(identificacion == pIdentificacion) {
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    
                    String numeroDeCuenta = documentoClienteYCuenta.getString("numeroCuentaAsocidada");;
                    MongoCollection coleccionCuentas = ConexionBaseDeDatos.ConectarBase().getCollection("Cuentas");
                    Document documentoCuenta = (Document) coleccionCuentas.find(new BasicDBObject("numeroCuenta", numeroDeCuenta)).projection(Projections.fields(Projections.include("numeroCuenta"), Projections.include("pin"), Projections.include("fechaCreacion"), Projections.include("saldo"), Projections.include("estatus"))).first();
                    if(documentoCuenta != null){
                    String pin = documentoCuenta.getString("pin");
                    String fechaCreacion = documentoCuenta.getString("fechaCreacion");
                    LocalDate localDate = LocalDate.parse(fechaCreacion, formato);
                    //double saldo = documentoCuenta.getDouble("saldo");
                    String estatus = documentoCuenta.getString("estatus");
                    IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
                    Cliente duenoDeCuenta = (Cliente) daoClienteCuenta.consultarClienteAsociadoACuenta(numeroDeCuenta);
                    Cuenta cuentaEncontrada = null;
                    try {
                        cuentaEncontrada = new Cuenta(numeroDeCuenta, localDate, 000000, estatus, pin, duenoDeCuenta);
                    } catch (Exception ex) {
                        Logger.getLogger(DAOClienteCuenta.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    listaDeResultados.agregarNodo(cuentaEncontrada);
                    }else{
                        listaDeResultados = null;
                    }
                }
            }
            return listaDeResultados;
        }
        catch(Error error) {
            return null;
        }
    }

    @Override
    public ICliente consultarClienteAsociadoACuenta(String pNumeroCuenta) {
        try {
            MongoCursor<Document> cursorClientesYCuentas = coleccionClienteCuenta.find().cursor();
            Cliente clienteEncontrado = null;
            while(cursorClientesYCuentas.hasNext()) {
                Document documentoClienteYCuenta = cursorClientesYCuentas.next();
                String numeroDeCuenta = documentoClienteYCuenta.getString("numeroCuentaAsocidada");
                if(numeroDeCuenta.equals(pNumeroCuenta)) {
                    int identificacionCliente = documentoClienteYCuenta.getInteger("identificacion");
                    MongoCollection coleccionCuentas = ConexionBaseDeDatos.ConectarBase().getCollection("Clientes");
                    Document documento = (Document) coleccionCuentas.find(new BasicDBObject("identificacion", identificacionCliente)).projection(Projections.fields(Projections.include("identificacion"), Projections.include("nombre"), Projections.include("codigo"), Projections.include("primerApellido"), Projections.include("segundoApellido"), Projections.include("fechaNacimiento"), Projections.include("numeroTelefono"), Projections.include("correoElectronico"))).first();
                    String codigo = documento.getString("codigo");
                    String nombre = documento.getString("nombre");
                    String primerApellido = documento.getString("primerApellido");
                    String segundoApellido = documento.getString("segundoApellido");
                    String fechaNacimiento = documento.getString("fechaNacimiento");
                    int numeroDeTelefono = documento.getInteger("numeroTelefono");
                    String correoElectronico = documento.getString("correoElectronico");
                    clienteEncontrado = new Cliente(codigo, nombre, primerApellido, segundoApellido, identificacionCliente, null, numeroDeTelefono, correoElectronico);
                }
            }
            return clienteEncontrado;
        }
        catch(Error error) {
            return null;
        }
    }

    @Override
    public int consultarCantidadDeCuentasDeCliente(int pIdentificacionCliente) {
        try {
            MongoCursor<Document> cursorClientesYCuentas = coleccionClienteCuenta.find().cursor();
            int cantidadDeCuentasDeCliente = 0;
            while(cursorClientesYCuentas.hasNext()) {
                Document documentoClienteYCuenta = cursorClientesYCuentas.next();
                int identificacion = documentoClienteYCuenta.getInteger("identificacion");
                if(identificacion == pIdentificacionCliente) {
                    cantidadDeCuentasDeCliente++;
                }
            }
            return cantidadDeCuentasDeCliente;
        }
        catch(Error error) {
            return -1;
        }
    }
}
