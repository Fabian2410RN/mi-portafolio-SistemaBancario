/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistaGUI.RetiroPrimeraEtapa;
import vistaGUI.SeleccionDeRetiroEnDolaresOColones;
import vistaGUI.SolicitarMontoDeRetiroEnColonesTerceraEtapa;
import vistaGUI.SolicitarMontoDeRetiroEnDolaresTerceraEtapa;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorSeleccionDeRetiroEnDolaresOColones implements ActionListener{
    public SeleccionDeRetiroEnDolaresOColones vistaGUI;
    private String numeroDeCuenta;
    
    public ControladorSeleccionDeRetiroEnDolaresOColones(SeleccionDeRetiroEnDolaresOColones pVistaGUI, String pNumeroDeCuenta) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnVolverRetiro.addActionListener(this);
        this.numeroDeCuenta = pNumeroDeCuenta;
        this.vistaGUI.jButton1.addActionListener(this);
        this.vistaGUI.jButton2.addActionListener(this);
    }
    

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Colones")) {
            SolicitarMontoDeRetiroEnColonesTerceraEtapa vistaSolicitarMontoDeRetiroEnColonesTerceraEtapa = new SolicitarMontoDeRetiroEnColonesTerceraEtapa();
            ControladorSolicitarMontoDeRetiroEnColonesTerceraEtapa controladorSolicitarMontoDeRetiroEnColonesTerceraEtapa = new ControladorSolicitarMontoDeRetiroEnColonesTerceraEtapa(vistaSolicitarMontoDeRetiroEnColonesTerceraEtapa, numeroDeCuenta);
            controladorSolicitarMontoDeRetiroEnColonesTerceraEtapa.vistaGUI.setVisible(true);
            controladorSolicitarMontoDeRetiroEnColonesTerceraEtapa.vistaGUI.setLocationRelativeTo(null);
            this.vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Dólares")) {
            SolicitarMontoDeRetiroEnDolaresTerceraEtapa vistaSolicitarMontoDeRetiroEnDolaresTerceraEtapa = new SolicitarMontoDeRetiroEnDolaresTerceraEtapa();
            ControladorSolicitarMontoDeRetiroEnDolaresTerceraEtapa controladorSSolicitarMontoDeRetiroEnDolaresTerceraEtapa = new ControladorSolicitarMontoDeRetiroEnDolaresTerceraEtapa(vistaSolicitarMontoDeRetiroEnDolaresTerceraEtapa, numeroDeCuenta);
            controladorSSolicitarMontoDeRetiroEnDolaresTerceraEtapa.vistaGUI.setVisible(true);
            controladorSSolicitarMontoDeRetiroEnDolaresTerceraEtapa.vistaGUI.setLocationRelativeTo(null);
            this.vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Volver")) {
            RetiroPrimeraEtapa vistaRetiroPrimeraEtapa = new RetiroPrimeraEtapa();
            ControladorRetiroPrimeraEtapa controladorRetiroPrimeraEtapa = new ControladorRetiroPrimeraEtapa(vistaRetiroPrimeraEtapa);
            controladorRetiroPrimeraEtapa.vistaGUI.setVisible(true);
            controladorRetiroPrimeraEtapa.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
       }
    }
}
