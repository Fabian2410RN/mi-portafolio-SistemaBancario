/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDeNegocios;

import serviciosExternos.TraductorDeGoogle;

/**
 *
 * @author Jairo Calderón
 */
public class DecoradorCorreoEspanolEInglesDeCuenta extends DecoradorCorreoDeCuenta{
    public DecoradorCorreoEspanolEInglesDeCuenta(CorreoDeCuenta pCuentaDecorada){
        super(pCuentaDecorada);
    }

    @Override
    public String generarMensajeDeCorreoInactivacionDeCuenta(String pMotivoInactivacion) {
        String mensaje = super.CorreoDeCuentaDecorado.generarMensajeDeCorreoInactivacionDeCuenta(pMotivoInactivacion);
        mensaje += "\n\n";
        mensaje += this.traducirMensajeAIngles(super.CorreoDeCuentaDecorado.generarMensajeDeCorreoInactivacionDeCuenta(pMotivoInactivacion));
        return mensaje;
    }

    @Override
    public String generarAsuntoDeCorreoInactivacionDeCuenta() {
        String mensaje = super.CorreoDeCuentaDecorado.generarAsuntoDeCorreoInactivacionDeCuenta()+ " | " + this.traducirMensajeAIngles(super.CorreoDeCuentaDecorado.generarAsuntoDeCorreoInactivacionDeCuenta());
        return mensaje;
    }
    
    private String traducirMensajeAIngles(String pMensajeEnEspanol){
        TraductorDeGoogle traductor = new TraductorDeGoogle();
        return traductor.traducirDeEspanolAIngles(pMensajeEnEspanol);
    }
}