package logicaDeNegocios;

import java.time.LocalDate;
import listaDinamica.Lista;
import clasesUtilitarias.Comparable;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import clasesUtilitarias.Encriptacion;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import serviciosExternos.EnvioCorreoElectronico;
import singletonClasesUtilitarias.EncriptacionSingleton;
/**
 *
 * @author Jairo Calderón
 */
public class Cuenta implements ICuenta, Comparable, CorreoDeCuenta{
    public String numeroCuenta;
    public LocalDate fechaCreacion;
    private double saldo;
    private String pin;
    public String estatus;
    Lista<Operacion> operacionesRealizadas;
    public ICliente propietario;  
    
    public Cuenta(String pNumeroCuenta, double pSaldo, String pEstatus, String pPin) throws Exception {
        this.numeroCuenta = pNumeroCuenta;
        this.fechaCreacion = LocalDate.now();
        this.saldo = pSaldo;
        this.estatus = pEstatus;
        this.pin = pPin;
        this.operacionesRealizadas = new Lista<>();
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Encriptacion encriptacionDeDatos = EncriptacionSingleton.instanciar();
        daoCuenta.registrarCuenta(this.numeroCuenta, encriptacionDeDatos.encriptar(this.pin), this.fechaCreacion.toString(), encriptacionDeDatos.encriptar(String.valueOf(this.saldo)), pEstatus);
    }
    
    public Cuenta(String pNumeroCuenta, LocalDate pFechaCreacion, double pSaldo, String pEstatus, String pPin, ICliente pPropietario) throws Exception {
        this.numeroCuenta = pNumeroCuenta;
        this.fechaCreacion = pFechaCreacion;
        this.saldo = pSaldo;
        this.estatus = pEstatus;
        this.pin = pPin;
        this.propietario = pPropietario;
        this.operacionesRealizadas = new Lista<>();
    }
    
    @Override
    public void cambiarPin(String pNuevoPin) {
        this.pin = pNuevoPin;
    }
    
    @Override
    public void registrarOperacion(String pTipoOperacion, boolean pSeAplicoComision, double pMontoComision) {
        Operacion nuevaOperacion = new Operacion(this.numeroCuenta, pTipoOperacion, pSeAplicoComision, pMontoComision);
        this.operacionesRealizadas.agregarNodo(nuevaOperacion);
    }
    
    @Override
    public void asignarPropietario(ICliente pPropietario) {
        this.propietario = pPropietario;
        
    }
    
    @Override
    public void depositar(double pMontoDepositado) {
        this.saldo =  this.saldo + pMontoDepositado;
        double montoComision = 0;
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(this.numeroCuenta);
        if(cantidadRetirosYDepositosRealizados >= 3) {
            montoComision = this.calcularMontoComision(pMontoDepositado);
            this.aplicarCobroComision(montoComision);
        }
        IDAOCuentaIndividual nuevoDeposito = new DAOCuentaIndividual();
        nuevoDeposito.depositar(this.numeroCuenta, pMontoDepositado - montoComision);
        this.registrarOperacion("Deposito", montoComision > 0.0, montoComision);
    }
    
    @Override
    public void retirar(double pMontoRetirado) {
        this.saldo = this.saldo - pMontoRetirado;
        double montoComision = 0;
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(this.numeroCuenta);
        if(cantidadRetirosYDepositosRealizados >= 3) {
            montoComision = this.calcularMontoComision(pMontoRetirado);
            this.aplicarCobroComision(montoComision);
        }
        IDAOCuentaIndividual nuevoDeposito = new DAOCuentaIndividual();
        nuevoDeposito.retirar(this.numeroCuenta, pMontoRetirado + montoComision);
        this.registrarOperacion("Retiro", montoComision > 0.0, montoComision);
    }
    
    @Override
    public void transferir(ICuenta pCuentaDestino, double pMontoTransferido) {
       Cuenta cuentaDestino = (Cuenta) pCuentaDestino;
       cuentaDestino.recibirTransferencia(pMontoTransferido);
       this.retirar(pMontoTransferido);
    }
    
    @Override
    public void recibirTransferencia(double pMontoTransferido) {
        this.saldo =  this.saldo + pMontoTransferido;
        IDAOCuentaIndividual nuevoDeposito = new DAOCuentaIndividual();
        nuevoDeposito.depositar(this.numeroCuenta, pMontoTransferido);
        this.registrarOperacion("Transferencia recibida",false,0);
    }
    
    @Override
    public double calcularMontoComision(double pMontoOperacion) {
        return pMontoOperacion * 0.02;
        
    }
    
    @Override
    public void aplicarCobroComision(double pMontoComision) {
        this.saldo = this.saldo - pMontoComision;
    }
    
    @Override
    public boolean menorQue(Comparable objeto) {
        Cuenta cuentaPorComparar = (Cuenta) objeto;
        return this.saldo < cuentaPorComparar.saldo;
    }
    
    public double getSaldo() {
        return saldo;
    }

    public String getPin() {
        return pin;
    }
    
    public void inactivarCuenta(String pAsuntoCorreo, String pMensajeCorreo) {
        this.estatus = "Inactiva";
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        daoCuenta.actualizarEstatus(this.numeroCuenta, this.estatus);
        this.enviarCorreoDeInactivacionDeCuenta(pAsuntoCorreo, pMensajeCorreo);
    }
    
    @Override
    public String generarMensajeDeCorreoInactivacionDeCuenta(String pMotivoInactivacion) {
        String mensajeDeCorreo = "";
        mensajeDeCorreo += "Estimado cliente: su cuenta " + this.numeroCuenta + " ha sido desactividada \n";
        mensajeDeCorreo += "La inactivación se debe a: " + pMotivoInactivacion + "\n";
        return mensajeDeCorreo;
    }
    
    @Override
    public String generarAsuntoDeCorreoInactivacionDeCuenta() {
        String asuntoDeCorreo = "Inactivación de cuenta " + this.numeroCuenta;
        return asuntoDeCorreo;
    }
    
    private void enviarCorreoDeInactivacionDeCuenta(String pAsunto, String pMensaje) {
        Cliente clienteAsociadoACuenta = (Cliente) this.propietario;
        EnvioCorreoElectronico.enviarCorreo(clienteAsociadoACuenta.correoElectronico, pAsunto, pMensaje);
    }
}
