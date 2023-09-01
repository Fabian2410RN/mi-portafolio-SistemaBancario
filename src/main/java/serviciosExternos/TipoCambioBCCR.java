/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosExternos;

import clasesUtilitarias.ContenidoXML;
import java.text.SimpleDateFormat;
import java.util.Date;
import clasesUtilitarias.ContenidoHTML;
import singletonClasesUtilitarias.ContenidoHTMLSingleton;

/**
 *
 * @author calde
 */
public class TipoCambioBCCR {
    private int indicadorCompraOVenta = 0;
    private String fechaConsulta;
    private String direccionUrl;

    public TipoCambioBCCR(){
      generarFechaConsulta();
    }
    
    public double obtenerValorCompra(){
        this.indicadorCompraOVenta = 317;

        double valorDeEtiqueta = Double.parseDouble(obtenerValorDeEtiqueta());
        return valorDeEtiqueta;
    }
    
    public double obtenerValorVenta(){
        this.indicadorCompraOVenta = 318;

        double valorDeEtiqueta = Double.parseDouble(obtenerValorDeEtiqueta());
        return valorDeEtiqueta;
    }
    
    private String obtenerValorDeEtiqueta(){
        try {
            generarURL();
            ContenidoHTML convertidorDeTextoHTML = ContenidoHTMLSingleton.instanciar();
            String contenidoHTML = convertidorDeTextoHTML.obtenerContenidoHTML(direccionUrl);
            ContenidoXML xml = new ContenidoXML(contenidoHTML);
            return xml.obtenerValorDeEtiqueta("NUM_VALOR");
        } 
        catch (Exception datosNoEncontrados) {
            return "0";
        }
    }

    private void generarURL(){
        String params = "Indicador="+indicadorCompraOVenta+"&FechaInicio="+fechaConsulta+"&FechaFinal="+fechaConsulta+"&Nombre="+"TEC"+"&SubNiveles="+"N"+"&CorreoElectronico="+"calderonjairo88@gmail.com"+"&Token="+"I5O2J0INIA";
        this.direccionUrl = "https://gee.bccr.fi.cr/Indicadores/Suscripciones/WS/wsindicadoreseconomicos.asmx/ObtenerIndicadoresEconomicosXML"+"?"+params;
    }

    private void generarFechaConsulta(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        this.fechaConsulta = sdf.format(date);
    }
}
