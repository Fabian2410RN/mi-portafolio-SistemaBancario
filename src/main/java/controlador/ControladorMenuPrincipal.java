/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistaGUI.CambioDePinPrimeraEtapa;
import vistaGUI.ConsultaDeEstatusCuenta;
import vistaGUI.ConsultaDeSaldoActual;
import vistaGUI.ConsultaEstadoDeCuenta;
import vistaGUI.ConsultaGananciasCobroComisionesDelBanco;
import vistaGUI.ConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa;
import vistaGUI.ConsultaGananciasCobroComisionesTotalesPorTodasLasCuentas;
import vistaGUI.ConsultaTipoCambioDeCompra;
import vistaGUI.ConsultaTipoCambioDeVenta;
import vistaGUI.ListaClientes;
import vistaGUI.ListaCuentas;
import vistaGUI.MenuPrincipal;
import vistaGUI.RegistroClientes;
import vistaGUI.RegistroCuentasVista;
import vistaGUI.RetiroPrimeraEtapa;
import vistaGUI.SeleccionDeDeposito;
import vistaGUI.TransferenciasEntreCuentas;

/**
 *
 * @author estadm
 */
public class ControladorMenuPrincipal implements ActionListener{
    
    public MenuPrincipal vistaGUI;
    
    
    public ControladorMenuPrincipal(MenuPrincipal pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnCambiarPinMenu.addActionListener(this);
        this.vistaGUI.btnConsultaEstadoCuentaMenu.addActionListener(this);
        this.vistaGUI.btnConsultaEstatusCuentaMenu.addActionListener(this);
        this.vistaGUI.btnConsultaGananciasTotalizadoMenu.addActionListener(this);
        this.vistaGUI.btnConsultaGananciasindividualCuentaMenu.addActionListener(this);
        this.vistaGUI.btnConsultaSaldoActualMenu.addActionListener(this);
        this.vistaGUI.btnConsultaTipoCambioCompraMenu.addActionListener(this);
        this.vistaGUI.btnConsultaTipoCambioVentaMenu.addActionListener(this);
        this.vistaGUI.btnDepositarMenu.addActionListener(this);
        this.vistaGUI.btnListaClienteMenu.addActionListener(this);
        this.vistaGUI.btnListaCuentaMenu.addActionListener(this);
        this.vistaGUI.btnRegistrarClienteMenu.addActionListener(this);
        this.vistaGUI.btnRegistrarCuentaMenu.addActionListener(this);
        this.vistaGUI.btnRetirarMenu.addActionListener(this);
        this.vistaGUI.btnTransferenciaMenu.addActionListener(this);
        this.vistaGUI.btnConsultarGananciasComisionPorCadaCuenta.addActionListener(this);
        //this.vistaGUI.btnConsultaGananciasindividualCuentaMenu.addActionListener(this);
        
        
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Listar clientes")) {
            ListaClientes vistaListaClientes = new ListaClientes();
            ControladorListaClientes controladorListaClientes = new ControladorListaClientes(vistaListaClientes);
            controladorListaClientes.vistaGUI.setVisible(true);
            controladorListaClientes.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Listar cuentas")) {
            ListaCuentas vistaListaCuentas = new ListaCuentas();
            ControladorListaCuentas controladorListaCuentas = new ControladorListaCuentas(vistaListaCuentas);
            controladorListaCuentas.vistaGUI.setVisible(true);
            controladorListaCuentas.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Registrar cliente")) {
            RegistroClientes vistaRegistroClientes = new RegistroClientes();
            ControladorRegistroClientes controladorRegistroClientes = new ControladorRegistroClientes(vistaRegistroClientes);
            controladorRegistroClientes.vistaGUI.setVisible(true);
            controladorRegistroClientes.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Registrar cuenta")) {
            RegistroCuentasVista vistaRegistroCuentasVista = new RegistroCuentasVista();
            ControladorRegistroCuentas controladorRegistroCuentasVista = new ControladorRegistroCuentas(vistaRegistroCuentasVista);
            controladorRegistroCuentasVista.vistaGUI.setVisible(true);
            controladorRegistroCuentasVista.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Depositar")) {
            SeleccionDeDeposito vistaSeleccionDeDeposito = new SeleccionDeDeposito();
            ControladorSeleccionDeDeposito controladorSeleccionDeDeposito = new ControladorSeleccionDeDeposito(vistaSeleccionDeDeposito);
            controladorSeleccionDeDeposito.vistaGUI.setVisible(true);
            controladorSeleccionDeDeposito.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Retirar")) {
            RetiroPrimeraEtapa vistaRetiroPrimeraEtapa = new RetiroPrimeraEtapa();
            ControladorRetiroPrimeraEtapa controladorRetiroPrimeraEtapa = new ControladorRetiroPrimeraEtapa(vistaRetiroPrimeraEtapa);
            controladorRetiroPrimeraEtapa.vistaGUI.setVisible(true);
            controladorRetiroPrimeraEtapa.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Transferencia")) {
            TransferenciasEntreCuentas vistaTransferenciasEntreCuentas = new TransferenciasEntreCuentas();
            ControladorTransferenciasEntreCuentas controladorTransferenciasEntreCuentas = new ControladorTransferenciasEntreCuentas(vistaTransferenciasEntreCuentas);
            controladorTransferenciasEntreCuentas.vistaGUI.setVisible(true);
            controladorTransferenciasEntreCuentas.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        
        if(evento.getActionCommand().equals("Cambiar pin")) {
            CambioDePinPrimeraEtapa vistaCambioDePinPrimeraEtapa = new CambioDePinPrimeraEtapa();
            ControladorCambioDePinPrimeraEtapa controladorCambioDePinPrimeraEtapa = new ControladorCambioDePinPrimeraEtapa(vistaCambioDePinPrimeraEtapa);
            controladorCambioDePinPrimeraEtapa.vistaGUI.setVisible(true);
            controladorCambioDePinPrimeraEtapa.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Consultar saldo actual")) {
            ConsultaDeSaldoActual vistaConsultaDeSaldoActual = new ConsultaDeSaldoActual();
            ControladorConsultaDeSaldoActual controladorConsultaDeSaldoActual = new ControladorConsultaDeSaldoActual(vistaConsultaDeSaldoActual);
            controladorConsultaDeSaldoActual.vistaGUI.setVisible(true);
            controladorConsultaDeSaldoActual.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Consultar estado de cuenta")) {
            ConsultaEstadoDeCuenta vistaConsultaEstadoDeCuenta = new ConsultaEstadoDeCuenta();
            ControladorConsultaEstadoDeCuenta controladorConsultaEstadoDeCuenta = new ControladorConsultaEstadoDeCuenta(vistaConsultaEstadoDeCuenta);
            controladorConsultaEstadoDeCuenta.vistaGUI.setVisible(true);
            controladorConsultaEstadoDeCuenta.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Consultar estatus de cuenta")) {
            ConsultaDeEstatusCuenta vistaConsultaDeEstatusCuenta = new ConsultaDeEstatusCuenta();
            ControladorConsultaDeEstatusCuenta controladorConsultaDeEstatusCuenta = new ControladorConsultaDeEstatusCuenta(vistaConsultaDeEstatusCuenta);
            controladorConsultaDeEstatusCuenta.vistaGUI.setVisible(true);
            controladorConsultaDeEstatusCuenta.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Consultar tipo cambio de compra")) {
            ConsultaTipoCambioDeCompra vistaConsultaTipoCambioDeCompra = new ConsultaTipoCambioDeCompra();
            ControladorConsultaTipoCambioDeCompra controladorConsultaTipoCambioDeCompra = new ControladorConsultaTipoCambioDeCompra(vistaConsultaTipoCambioDeCompra);
            controladorConsultaTipoCambioDeCompra.vistaGUI.setVisible(true);
            controladorConsultaTipoCambioDeCompra.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Consultar tipo cambio de venta")) {
            ConsultaTipoCambioDeVenta vistaConsultaTipoCambioDeVenta = new ConsultaTipoCambioDeVenta();
            ControladorConsultaTipoCambioDeVenta controladorConsultaTipoCambioDeVenta = new ControladorConsultaTipoCambioDeVenta(vistaConsultaTipoCambioDeVenta);
            controladorConsultaTipoCambioDeVenta.vistaGUI.setVisible(true);
            controladorConsultaTipoCambioDeVenta.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Consultar ganancias por concepto de comisión totalizado")) {
            ConsultaGananciasCobroComisionesDelBanco vistaConsultaGananciasCobroComisionesDelBanco = new ConsultaGananciasCobroComisionesDelBanco();
            ControladorConsultaGananciasCobroComisionesDelBanco controladorConsultaGananciasCobroComisionesDelBanco = new ControladorConsultaGananciasCobroComisionesDelBanco(vistaConsultaGananciasCobroComisionesDelBanco);
            controladorConsultaGananciasCobroComisionesDelBanco.vistaGUI.setVisible(true);
            controladorConsultaGananciasCobroComisionesDelBanco.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Consultar ganancias por concepto de comisión ")) {
            ConsultaGananciasCobroComisionesTotalesPorTodasLasCuentas vistaConsultaGananciasCobroComisionesPorCadaCuenta = new ConsultaGananciasCobroComisionesTotalesPorTodasLasCuentas();
            ControladorConsultaGananciasCobroComisionesTotalesPorTodasLasCuentas controladorConsultaGananciasCobroComisionesPorCadaCuenta = new ControladorConsultaGananciasCobroComisionesTotalesPorTodasLasCuentas(vistaConsultaGananciasCobroComisionesPorCadaCuenta);
            controladorConsultaGananciasCobroComisionesPorCadaCuenta.vistaGUI.setVisible(true);
            controladorConsultaGananciasCobroComisionesPorCadaCuenta.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Consultar ganancias por concepto")) {
            ConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa vistaConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa = new ConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa();
            ControladorConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa controladorConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa = new ControladorConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa(vistaConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa);
            controladorConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa.vistaGUI.setVisible(true);
            controladorConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
    }
    
    public static void volverMenuPrincipal(){
        MenuPrincipal vistaMenuPrincipal = new MenuPrincipal();
        ControladorMenuPrincipal controladorMenuPrincipal = new ControladorMenuPrincipal(vistaMenuPrincipal);
        controladorMenuPrincipal.vistaGUI.setVisible(true);
        controladorMenuPrincipal.vistaGUI.setLocationRelativeTo(null);
        //pvistaGUI.setVisible(false);
    }
    
}
