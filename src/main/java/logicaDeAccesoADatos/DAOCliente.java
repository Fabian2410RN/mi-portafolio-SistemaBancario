/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDeAccesoADatos;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import logicaDeNegocios.Cliente;
import org.bson.Document;

/**
 *
 * @author sebashdez
 */
public class DAOCliente implements IDAOCliente{
    public static MongoCollection coleccionClientes = ConexionBaseDeDatos.ConectarBase().getCollection("Clientes");
    
    /**
     *
     * @param pNombre
     * @param pPrimerApellido
     * @param pSegundoApellido
     * @param pIdentificacion
     * @param pFechaNacimiento
     * @param pNumeroTelefono
     * @param pCorreoElectronico
     * @return
     */
    @Override
    public boolean registrarCliente(String pCodigo, String pNombre, String pPrimerApellido, String pSegundoApellido, int pIdentificacion, String pFechaNacimiento, int pNumeroTelefono,  String pCorreoElectronico) {
        try{
            Document Cliente = new Document("codigo", pCodigo).append("nombre", pNombre).append("primerApellido", pPrimerApellido).append("segundoApellido", 
                pSegundoApellido).append("identificacion", pIdentificacion).append("fechaNacimiento", pFechaNacimiento).append("numeroTelefono", 
                        pNumeroTelefono).append("correoElectronico", pCorreoElectronico);
            coleccionClientes.insertOne(Cliente);
        
            return true;
        } 
        catch (Error error){
            return false;
        }
    }

    @Override
    public Cliente consultarCliente(int pIdentificacion) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Document documento = (Document) coleccionClientes.find(new BasicDBObject("identificacion", pIdentificacion)).projection(Projections.fields(Projections.include("identificacion"), Projections.include("nombre"), Projections.include("codigo"), Projections.include("primerApellido"), Projections.include("segundoApellido"), Projections.include("fechaNacimiento"), Projections.include("numeroTelefono"), Projections.include("correoElectronico"))).first();
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
        return clienteEncontrado;
    }
}
