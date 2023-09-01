/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDeAccesoADatos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

/**
 *
 * @author Jairo Calderón
 */
public class DAOOperacionCatalogoDeCuentas implements IDAOOperacionCatalogoDeCuentas{
    public static MongoCollection coleccionOperaciones = ConexionBaseDeDatos.ConectarBase().getCollection("Operaciones");
    
    @Override
    public double consultarMontoTotalCobradoComisionesPorDepositos() {
        try {
            MongoCursor<Document> cursorOperaciones = coleccionOperaciones.find().cursor();
            double montoTotalCobradoComisionesPorDepositos = 0;
            while(cursorOperaciones.hasNext()) {
                Document documentoOperaciones = cursorOperaciones.next();
                String tipoDeOperacion = documentoOperaciones.getString("tipoOperacion");
                double montoComision = documentoOperaciones.getDouble("montoComision");
                if(tipoDeOperacion.equals("Deposito")) {
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
    public double consultarMontoTotalCobradoComisionesPorRetiros() {
        try {
            MongoCursor<Document> cursorOperaciones = coleccionOperaciones.find().cursor();
            double montoTotalCobradoComisionesPorRetiros = 0;
            while(cursorOperaciones.hasNext()) {
                Document documentoOperaciones = cursorOperaciones.next();
                String tipoDeOperacion = documentoOperaciones.getString("tipoOperacion");
                double montoComision = documentoOperaciones.getDouble("montoComision");
                if(tipoDeOperacion.equals("Retiro")) {
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
    public double consultarMontoTotalCobradoComisionesPorRetirosYDepositos() {
        try {
            MongoCursor<Document> cursorOperaciones = coleccionOperaciones.find().cursor();
            double montoTotalCobradoComisionesPorDepositos = 0;
            while(cursorOperaciones.hasNext()) {
                Document documentoOperaciones = cursorOperaciones.next();
                double montoComision = documentoOperaciones.getDouble("montoComision");
                montoTotalCobradoComisionesPorDepositos += montoComision;
            }
            return montoTotalCobradoComisionesPorDepositos;
        }
        catch(Error error) {
            return -1;
        }
    }
}
