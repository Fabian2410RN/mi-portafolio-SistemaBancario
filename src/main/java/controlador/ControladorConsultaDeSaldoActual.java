/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import serviciosExternos.TipoCambioBCCR;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import validacion.ValidacionCuenta;
import vistaGUI.ConsultaDeSaldoActual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
/**
 *
 * @author estadm
 */
public class ControladorConsultaDeSaldoActual implements ActionListener{
    public ConsultaDeSaldoActual vistaGUI;
    private int cantidadDeIntentos = 0;
    
    public ControladorConsultaDeSaldoActual(ConsultaDeSaldoActual pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnConsultarColones.addActionListener(this);
        this.vistaGUI.btnConsultarDolares.addActionListener(this);
        this.vistaGUI.btnVolverConsultarSaldoActual.addActionListener(this);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String numeroDeCuenta = this.vistaGUI.txtNumeroCuentaConsultaSaldo.getText();
        String pin = this.vistaGUI.txtPinCuentaConsultaSaldo.getText();
        
        if(evento.getActionCommand().equals("Volver")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
        } else{
            
            this.cantidadDeIntentos++;
            boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuenta);
        if(existeCuenta) {
            boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
            if(cuentaEstaActiva) {
                boolean pinCorrespondeACuenta = ValidacionCuenta.validarPinCorrespondeACuenta(numeroDeCuenta, pin);
                if(pinCorrespondeACuenta) {
                    if(evento.getActionCommand().equals("Consultar colones")) {
                        IDAOCuentaIndividual cuentaAconsultarColones = new DAOCuentaIndividual();
                        double saldoActualColones = cuentaAconsultarColones.consultarSaldoActual(numeroDeCuenta);
                        
                        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
                        accion.registrarEnBitacoras(LocalDate.now(), "Consulta de saldo actual en colones", "GUI");
                        
                        MensajeEnPantallaCuenta.imprimirMensajeSaldoCuentaActualColones(saldoActualColones);
                    }
                    if(evento.getActionCommand().equals("Consultar dólares")) {
                        TipoCambioBCCR tc = new TipoCambioBCCR();
                        IDAOCuentaIndividual cuentaAconsultarDolares = new DAOCuentaIndividual();
                        double saldoActualColones = cuentaAconsultarDolares.consultarSaldoActual(numeroDeCuenta);
                        double valorDeCompra = tc.obtenerValorCompra();
                        double saldoConvertidoADolares = saldoActualColones / valorDeCompra;
                        
                        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
                        accion.registrarEnBitacoras(LocalDate.now(), "Consulta de saldo actual en dólares", "GUI");
                        
                        MensajeEnPantallaCuenta.imprimirMensajeSaldoCuentaActualDolares(saldoConvertidoADolares, valorDeCompra);
                    }
                } else {
                    if(this.cantidadDeIntentos == 1) {
                        MensajeEnPantallaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(numeroDeCuenta, pin);
                    }
                    else {
                        cantidadDeIntentos = 0;
                        validacion.ValidacionCuenta.inactivarCuenta(numeroDeCuenta);
                    }
                    }
            }else{
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
            }
        } else {
            MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
          }
        }
       }
    }
    
    

