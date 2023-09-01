/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import clasesUtilitarias.Conversion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;
import validacion.ValidacionCuenta;
import vistaGUI.SolicitarMontoDepositoYCuentaDestinoDeTransferencia;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorSolicitarMontoDepositoYCuentaDestinoDeTransferencia implements ActionListener{
    public SolicitarMontoDepositoYCuentaDestinoDeTransferencia vistaGUI;
    private String numeroDeCuentaDeOrigen;
    
    public ControladorSolicitarMontoDepositoYCuentaDestinoDeTransferencia(SolicitarMontoDepositoYCuentaDestinoDeTransferencia pVistaGUI, String pNumeroDeCuentaDeOrigen) {
        this.vistaGUI = pVistaGUI;
        this.numeroDeCuentaDeOrigen = pNumeroDeCuentaDeOrigen;
        this.vistaGUI.btnEnviarDineroTransferencia.addActionListener(this);
        this.vistaGUI.btnCancelarTransferencia.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Enviar")) {
            String montoDeRetiro = this.vistaGUI.txtMontoAEnviarTransferencia.getText();
            String numeroDeCuentaDeDestino = this.vistaGUI.txtNumeroCuentaDeTransferencia.getText();
            boolean formatoDeMontoDeRetiroEsCorrecto = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(montoDeRetiro);
            if(formatoDeMontoDeRetiroEsCorrecto) {
                boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuentaDeDestino);
                if(existeCuenta) {
                    boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuentaDeDestino);
                    if(cuentaEstaActiva) {
                        Conversion convertidorDeDatos = ConversionSingleton.instanciar();
                        double montoDeRetiroEnFormatoDecimal = convertidorDeDatos.convertirStringEnDecimal(montoDeRetiro);
                        double montoComision = this.calcularMontoComision(montoDeRetiroEnFormatoDecimal);
                        boolean hayFondosSuficientes = ValidacionCuenta.validarHayFondosSuficientes(this.numeroDeCuentaDeOrigen, montoDeRetiroEnFormatoDecimal + montoComision);
                        if(hayFondosSuficientes) {
                            efectuarTransferencia(numeroDeCuentaDeDestino, montoDeRetiroEnFormatoDecimal);
                            RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
                            accion.registrarEnBitacoras(LocalDate.now(), "Transferencia", "GUI");
                            
                            ControladorMenuPrincipal.volverMenuPrincipal();
                            vistaGUI.setVisible(false);
                        }
                        else {
                            MensajeEnPantallaCuenta.imprimirMensajeDeErrorFondosInsuficientes();
                        }
                    }
                    else {
                        MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                    }
                }
                else {
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuentaDeDestino);
                }
            }
            else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorFormatoDeMontoDeRetiroIncorrecto();
            }
        }
        if(evento.getActionCommand().equals("Cancelar")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
    
    private double calcularMontoComision(double pMontoPorRetirar) {
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadDeRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(this.numeroDeCuentaDeOrigen);
        double montoComision = 0.0;
        if(cantidadDeRetirosYDepositosRealizados >= 3) {
            montoComision += pMontoPorRetirar * 0.02;
        }
        return montoComision;
    }
    
    public void efectuarTransferencia(String pNumeroDeCuentaDestino, double pMontoTransferido) {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuentaDeOrigen = (Cuenta) daoCuenta.consultarCuenta(this.numeroDeCuentaDeOrigen);
        Cuenta cuentaDeDestino = (Cuenta) daoCuenta.consultarCuenta(pNumeroDeCuentaDestino);
        double montoComision = calcularMontoComision(pMontoTransferido);
        cuentaDeOrigen.transferir(cuentaDeDestino, pMontoTransferido);
        //cuentaDeOrigen.retirar(pMontoTransferido);
        
            
        MensajeEnPantallaCuenta.imprimirMensajeTransferenciaExitosa(pMontoTransferido, montoComision);
    }
}
