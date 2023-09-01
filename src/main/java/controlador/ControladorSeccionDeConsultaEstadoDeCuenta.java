/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistaGUI.InformacionPorConsultaDeEstadoCuentaColones;
import vistaGUI.InformacionPorConsultaDeEstadoCuentaDolares;
import vistaGUI.SeleccionDeConsultaEstadoDeCuenta;
/**
 *
 * @author estadm
 */
public class ControladorSeccionDeConsultaEstadoDeCuenta implements ActionListener{
    public SeleccionDeConsultaEstadoDeCuenta vistaGUI;
    private String numeroCuenta;
    
    public ControladorSeccionDeConsultaEstadoDeCuenta(SeleccionDeConsultaEstadoDeCuenta pVistaGUI, String pNumeroCuenta){
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnColonesEstadoCuenta.addActionListener(this);
        this.vistaGUI.btnDolaresEstadoCuenta.addActionListener(this);
        this.numeroCuenta = pNumeroCuenta;
    }
     public ControladorSeccionDeConsultaEstadoDeCuenta(SeleccionDeConsultaEstadoDeCuenta pVistaGUI){
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnColonesEstadoCuenta.addActionListener(this);
        this.vistaGUI.btnDolaresEstadoCuenta.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Colones")) {
            
            InformacionPorConsultaDeEstadoCuentaColones vistaConsultaDeEstadoCuentaColones = new InformacionPorConsultaDeEstadoCuentaColones();
            ControladorInformacionPorConsultaDeEstadoCuentaColones controladorConsultaEstadoCuentaColones = new ControladorInformacionPorConsultaDeEstadoCuentaColones(vistaConsultaDeEstadoCuentaColones, this.numeroCuenta);
                    
            controladorConsultaEstadoCuentaColones.vistaGUI.setVisible(true);
            controladorConsultaEstadoCuentaColones.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Dólares")) {
            InformacionPorConsultaDeEstadoCuentaDolares vistaConsultaDeEstadoCuentaDolares = new InformacionPorConsultaDeEstadoCuentaDolares();
            ControladorInformacionPorConsultaDeEstadoCuentaDolares controladorConsultaEstadoCuentaColones = new ControladorInformacionPorConsultaDeEstadoCuentaDolares(vistaConsultaDeEstadoCuentaDolares, this.numeroCuenta);
                    
            controladorConsultaEstadoCuentaColones.vistaGUI.setVisible(true);
            controladorConsultaEstadoCuentaColones.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
    }
}

