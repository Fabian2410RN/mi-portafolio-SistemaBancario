/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import vistaGUI.DetalleCuenta;
import vistaGUI.ListaCuentas;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeAccesoADatos.DAOOperacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorDetalleCuenta implements ActionListener{
    private String numeroDeCuenta;
    public DetalleCuenta vistaGUI;
    
    public ControladorDetalleCuenta(String pNumeroDeCuenta, DetalleCuenta pVistaGUI) {
        this.numeroDeCuenta = pNumeroDeCuenta;
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnVolverDetalleCuenta.addActionListener(this);
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuenta = (Cuenta) daoCuenta.consultarCuenta(this.numeroDeCuenta);
        Cliente cliente = (Cliente) cuenta.propietario;
        String nombreDePropietario = cliente.nombre +" " +cliente.primerApellido +" "+ cliente.segundoApellido;
        this.vistaGUI.txtNumeroCuenta.setText(cuenta.numeroCuenta);
        this.vistaGUI.txtFechaCreacion.setText(cuenta.fechaCreacion.toString());
        this.vistaGUI.txtSaldoCuenta.setText(String.valueOf((String.format("%.2f",cuenta.getSaldo()))));
        //this.vistaGUI.txtPin.setText(cuenta.getPin());
        this.vistaGUI.txtNombrePropietario.setText(nombreDePropietario);
        this.vistaGUI.txtEstatus.setText(cuenta.estatus);
        IDAOOperacionCuenta obtenerCantidadRetirosYDepositos = new DAOOperacionCuenta();
        
        this.vistaGUI.txtCantidadRD.setText(""+obtenerCantidadRetirosYDepositos.consultarCantidadDeDepositosYRetirosRealizados(pNumeroDeCuenta));
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Volver")) {
            ListaCuentas vistaListaCuentass = new ListaCuentas();
            ControladorListaCuentas controladorListaCuentas = new ControladorListaCuentas(vistaListaCuentass);
            controladorListaCuentas.vistaGUI.setVisible(true);
            controladorListaCuentas.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
       }
    }
}
