
package logicaDeNegocios;

import java.time.LocalDate;
import listaDinamica.Lista;
import clasesUtilitarias.Comparable;
import logicaDeAccesoADatos.DAOCliente;
import logicaDeAccesoADatos.DAOClienteCuenta;
import logicaDeAccesoADatos.IDAOCliente;
import logicaDeAccesoADatos.IDAOClienteCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class Cliente implements ICliente, Comparable{
    public String codigo;
    public String nombre;
    public String primerApellido;
    public String segundoApellido;
    public int identificacion;
    public LocalDate fechaNacimiento;
    public int numeroTelefono;
    public String correoElectronico;
    Lista<ICuenta> misCuentas;
    
    public Cliente(String pCodigo, String pNombre, String pPrimerApellido, String pSegundoApellido, int pIdentificacion, int pDia, int pMes, int pAno, int pNumeroTelefono, String pCorreoElectronico) {
        this.codigo = pCodigo;
        this.nombre = pNombre;
        this.primerApellido = pPrimerApellido;
        this.segundoApellido = pSegundoApellido;
        this.identificacion = pIdentificacion;
        this.fechaNacimiento = LocalDate.of(pAno, pMes, pDia);
        this.numeroTelefono = pNumeroTelefono;
        this.correoElectronico = pCorreoElectronico;
        this.misCuentas = new Lista<>();
        IDAOCliente daoCliente = new DAOCliente();
        daoCliente.registrarCliente(pCodigo, pNombre, pPrimerApellido, pSegundoApellido, pIdentificacion, this.fechaNacimiento.toString(), pNumeroTelefono, pCorreoElectronico);
    }
    
    public Cliente(String pCodigo, String pNombre, String pPrimerApellido, String pSegundoApellido, int pIdentificacion, LocalDate pFechaNacimiento, int pNumeroTelefono, String pCorreoElectronico) {
        this.codigo = pCodigo;
        this.nombre = pNombre;
        this.primerApellido = pPrimerApellido;
        this.segundoApellido = pSegundoApellido;
        this.identificacion = pIdentificacion;
        this.fechaNacimiento = pFechaNacimiento;
        this.numeroTelefono = pNumeroTelefono;
        this.correoElectronico = pCorreoElectronico;
        this.misCuentas = new Lista<>();
    }
    
    @Override
    public void asignarCuenta(ICuenta pCuenta) {
        this.misCuentas.agregarNodo(pCuenta);
        IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
        Cuenta cuenta = (Cuenta) pCuenta;
        daoClienteCuenta.registrarCuentaACliente(cuenta.numeroCuenta, this.identificacion);
    }
    
    @Override
    public boolean menorQue(Comparable objeto) {
        Cliente clientePorComparar = (Cliente) objeto;
        return this.primerApellido.compareTo(clientePorComparar.primerApellido) < 0;
    }
    
    public void setCodigoCliente(String pCodigo){
        codigo = pCodigo;
    }
    public String getCodigo(){
        return codigo;
    }
    
    public void setFechaNacimiento(LocalDate pFechaNacimiento){
        fechaNacimiento = pFechaNacimiento;
    }
    public LocalDate getFechaNacimiento(){
        return fechaNacimiento;
    }
    
}
