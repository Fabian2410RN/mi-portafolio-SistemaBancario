/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDeAccesoADatos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.time.LocalDate;
import listaDinamica.Lista;
import logicaDeNegocios.Operacion;
import org.bson.Document;

/**
 *
 * @author sebashdez
 */
public class DAOOperacionCuenta implements IDAOOperacionCuenta{
    public static MongoCollection coleccionOperaciones = ConexionBaseDeDatos.ConectarBase().getCollection("Operaciones");
    
    /**
     *
     * @param pNumeroCuentaAsocidada
     * @param pFechaOperacion
     * @param pTipoOperacion
     * @param pSeAplicoComision
     * @param pMontoComision
     * @return
     */
    
    @Override
    public boolean registrarOperacion(String pNumeroCuentaAsocidada, String pFechaOperacion, String pTipoOperacion, boolean pSeAplicoComision, double pMontoComision) {
        try{
            Document Operacion = new Document("numeroCuentaAsocidada", pNumeroCuentaAsocidada).append("fechaOperacion", pFechaOperacion).append("tipoOperacion", pTipoOperacion).append("seAplicoComision", 
                pSeAplicoComision).append("montoComision", pMontoComision);
            coleccionOperaciones.insertOne(Operacion);
            
            return true;
            
        } catch (Error e){
            return false;
        }
    }

    @Override
    public Lista<Operacion> consultarOperacionesCuenta(String pNumeroCuenta) {
        try {
            MongoCursor<Document> cursorOperaciones = coleccionOperaciones.find().cursor();
            Lista<Operacion> listaDeResultados = new Lista<>();
            while(cursorOperaciones.hasNext()) {
                Document documentoOperaciones = cursorOperaciones.next();
                String numeroDeCuenta = documentoOperaciones.getString("numeroCuentaAsocidada");
                if(numeroDeCuenta.equals(pNumeroCuenta)) {
                    String tipoDeOperacion = documentoOperaciones.getString("tipoOperacion");
                    LocalDate fechaOperacion = LocalDate.parse(documentoOperaciones.getString("fechaOperacion"));
                    boolean seAplicoComision = documentoOperaciones.getBoolean("seAplicoComision");
                    double montoComision = documentoOperaciones.getDouble("montoComision");
                    Operacion operacionEncontrada = new Operacion(tipoDeOperacion, fechaOperacion, seAplicoComision, montoComision);
                    listaDeResultados.agregarNodo(operacionEncontrada);
                }
            }
            return listaDeResultados;
        }
        catch(Error error) {
            return null;
        }
    }

    @Override
    public int consultarCantidadDeDepositosYRetirosRealizados(String pNumeroCuenta) {
        try {
            MongoCursor<Document> cursorOperaciones = coleccionOperaciones.find().cursor();
            int cantidadDeDepositosYRetirosRealizados = 0;
            while(cursorOperaciones.hasNext()) {
                Document documentoOperaciones = cursorOperaciones.next();
                String numeroDeCuenta = documentoOperaciones.getString("numeroCuentaAsocidada");
                String tipoDeOperacion = documentoOperaciones.getString("tipoOperacion");
                if(numeroDeCuenta.equals(pNumeroCuenta) && (tipoDeOperacion.equals("Deposito") || tipoDeOperacion.equals("Retiro"))) {
                    cantidadDeDepositosYRetirosRealizados++;
                }
            }
            return cantidadDeDepositosYRetirosRealizados;
        }
        catch(Error error) {
            return -1;
        }
    }
    
   @Override
    public int consultarCantidadOperaciones(String pNumeroCuenta) {
        try {
            MongoCursor<Document> cursorOperaciones = coleccionOperaciones.find().cursor();
            int cantidadDeDepositosYRetirosRealizados = 0;
            while(cursorOperaciones.hasNext()) {
                Document documentoOperaciones = cursorOperaciones.next();
                String numeroDeCuenta = documentoOperaciones.getString("numeroCuentaAsocidada");
                String tipoDeOperacion = documentoOperaciones.getString("tipoOperacion");
                if(numeroDeCuenta.equals(pNumeroCuenta) && (tipoDeOperacion.equals("Deposito") || tipoDeOperacion.equals("Retiro") || tipoDeOperacion.equals("Transferencia recibida"))) {
                    cantidadDeDepositosYRetirosRealizados++;
                }
            }
            return cantidadDeDepositosYRetirosRealizados;
        }
        catch(Error error) {
            return -1;
        }
    }

    @Override
    public double consultarMontoTotalCobradoComisionesPorDepositos(String pNumeroCuenta) {
        try {
            MongoCursor<Document> cursorOperaciones = coleccionOperaciones.find().cursor();
            double montoTotalCobradoComisionesPorDepositos = 0;
            while(cursorOperaciones.hasNext()) {
                Document documentoOperaciones = cursorOperaciones.next();
                String numeroDeCuenta = documentoOperaciones.getString("numeroCuentaAsocidada");
                String tipoDeOperacion = documentoOperaciones.getString("tipoOperacion");
                double montoComision = documentoOperaciones.getDouble("montoComision");
                if(numeroDeCuenta.equals(pNumeroCuenta) && tipoDeOperacion.equals("Deposito")) {
                    montoTotalCobradoComisionesPorDepositos += montoComision;
                }
            }
            return montoTotalCobradoComisionesPorDepositos;
        }
        catch(Error error) {
            return -1;
        }
    }

    @Override
    public double consultarMontoTotalCobradoComisionesPorRetiros(String pNumeroCuenta) {
        try {
            MongoCursor<Document> cursorOperaciones = coleccionOperaciones.find().cursor();
            double montoTotalCobradoComisionesPorRetiros = 0;
            while(cursorOperaciones.hasNext()) {
                Document documentoOperaciones = cursorOperaciones.next();
                String numeroDeCuenta = documentoOperaciones.getString("numeroCuentaAsocidada");
                String tipoDeOperacion = documentoOperaciones.getString("tipoOperacion");
                double montoComision = documentoOperaciones.getDouble("montoComision");
                if(numeroDeCuenta.equals(pNumeroCuenta) && tipoDeOperacion.equals("Retiro")) {
                    montoTotalCobradoComisionesPorRetiros += montoComision;
                }
            }
            return montoTotalCobradoComisionesPorRetiros;
        }
        catch(Error error) {
            return -1;
        }
    }

    @Override
    public double consultarMontoTotalCobradoComisionesPorRetirosYDepositos(String pNumeroCuenta) {
        try {
            MongoCursor<Document> cursorClientesYCuentas = coleccionOperaciones.find().cursor();
            double montoTotalCobradoComisionesPorRetirosYDepositos = 0;
            while(cursorClientesYCuentas.hasNext()) {
                Document documentoOperaciones = cursorClientesYCuentas.next();
                String numeroDeCuenta = documentoOperaciones.getString("numeroCuentaAsocidada");
                double montoComision = documentoOperaciones.getDouble("montoComision");
                if(numeroDeCuenta.equals(pNumeroCuenta)) {
                    montoTotalCobradoComisionesPorRetirosYDepositos += montoComision;
                }
            }
            return montoTotalCobradoComisionesPorRetirosYDepositos;
        }
        catch(Error error) {
            return -1;
        }
    }
}
