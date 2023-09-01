/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDeAccesoADatos;

import clasesUtilitarias.Conversion;
import clasesUtilitarias.Encriptacion;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Projections;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import listaDinamica.Lista;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICuenta;
import org.bson.Document;
import singletonClasesUtilitarias.ConversionSingleton;
import singletonClasesUtilitarias.EncriptacionSingleton;

/**
 *
 * @author sebashdez
 */
public class DAOCatalogoDeCuentas implements IDAOCatalogoDeCuentas{
    public static MongoCollection coleccionCuentas = ConexionBaseDeDatos.ConectarBase().getCollection("Cuentas");
    
    @Override
    public Lista<ICuenta> consultarListaDeCuentas(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Lista<ICuenta> listaCuentas = new Lista<>();
        MongoCursor<Document> cursor = coleccionCuentas.find().cursor();
        while(cursor.hasNext()) {
            Document documento = cursor.next();
            String numeroCuenta = documento.getString("numeroCuenta");
            String fechaCreacion = documento.getString("fechaCreacion");
            String saldoEncriptado = documento.getString("saldo");
            String estatus = documento.getString("estatus");
            String pPinEncriptado = documento.getString("pin");
            LocalDate fechaCreacionEnFormatoLocalDate = LocalDate.parse(fechaCreacion);
            try {
                Encriptacion encriptacionDeDatos = EncriptacionSingleton.instanciar();
                String saldoDesencriptado = encriptacionDeDatos.desencriptar(saldoEncriptado);
                String pinDesencriptado = encriptacionDeDatos.desencriptar(pPinEncriptado);
                Conversion convertidorDeDatos = ConversionSingleton.instanciar();
                double saldoConvertido = convertidorDeDatos.convertirStringEnDecimal(saldoDesencriptado);
                IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
                Cliente duenoDeCuenta = (Cliente) daoClienteCuenta.consultarClienteAsociadoACuenta(numeroCuenta);
                Cuenta cuentaEncontrada = new Cuenta(numeroCuenta, fechaCreacionEnFormatoLocalDate, saldoConvertido, estatus, pinDesencriptado, duenoDeCuenta);
                listaCuentas.agregarNodo(cuentaEncontrada);
            } 
            catch (Exception ex) {
                return null;
            }
        }
        return listaCuentas;
    }
    
    @Override
    public int consultarCantidadCuentas(){
        MongoCursor<Document> cursor = coleccionCuentas.find().cursor();
        int contador = 0;
        while(cursor.hasNext()) {
            cursor.next();
            contador++;
        }
        return contador;
    }
    
    @Override
    public boolean consultarSiExisteCuenta(String pNumeroCuenta){
        Document documento = (Document) coleccionCuentas.find(new BasicDBObject("numeroCuenta", pNumeroCuenta)).projection(Projections.fields(Projections.include("numeroCuenta"))).first();
        
        return documento != null;
    }
    
}
