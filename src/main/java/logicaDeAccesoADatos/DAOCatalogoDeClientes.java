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
import listaDinamica.Lista;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.ICliente;
import org.bson.Document;

/**
 *
 * @author sebashdez
 */
public class DAOCatalogoDeClientes implements IDAOCatalogoDeClientes {
    final public static MongoCollection coleccionClientes = ConexionBaseDeDatos.ConectarBase().getCollection("Clientes");
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public Lista<ICliente> consultarListaDeClientes() {
        Lista<ICliente> listaDeResultados = new Lista<>();
        MongoCursor<Document> cursor = coleccionClientes.find().cursor();
        while(cursor.hasNext()) {
            Document documento = cursor.next();
            int identificacion = documento.getInteger("identificacion");
            String codigo = documento.getString("codigo");
            String nombre = documento.getString("nombre");
            String primerApellido = documento.getString("primerApellido");
            String segundoApellido = documento.getString("segundoApellido");
            String fechaNacimiento = documento.getString("fechaNacimiento");
            LocalDate fechaNacimientoLocalDate = LocalDate.parse(fechaNacimiento, formato);
            int numeroDeTelefono = documento.getInteger("numeroTelefono");
            String correoElectronico = documento.getString("correoElectronico");
            Cliente clienteEncontrado = new Cliente(codigo, nombre, primerApellido, segundoApellido, identificacion, fechaNacimientoLocalDate, numeroDeTelefono, correoElectronico);
            listaDeResultados.agregarNodo(clienteEncontrado);
        }
        return listaDeResultados;
    }

    @Override
    public int consultarCantidadDeClientes() {
        MongoCursor<Document> cursor = coleccionClientes.find().cursor();
        int cantidadDeClientes = 0;
        while(cursor.hasNext()) {
            cursor.next();
            cantidadDeClientes++;
        }
        return cantidadDeClientes;
    }
    
    @Override
    public boolean consultarSiExisteCliente(int pIdentificacion){
        Document documento = (Document) coleccionClientes.find(new BasicDBObject("identificacion", pIdentificacion)).projection(Projections.fields(Projections.include("identificacion"))).first();
        
        if (documento == null){
            return true;
        } else {
            int identificacion = documento.getInteger("identificacion");
            return identificacion != pIdentificacion;
        }
    }
    
    @Override
    public boolean consultarSiNOExisteCliente(int pIdentificacion){
        Document documento = (Document) coleccionClientes.find(new BasicDBObject("identificacion", pIdentificacion)).projection(Projections.fields(Projections.include("identificacion"))).first();
        
        if (documento == null){
            return false;
        } else {
            int identificacion = documento.getInteger("identificacion");
            return identificacion == pIdentificacion;
        }
    }

}
