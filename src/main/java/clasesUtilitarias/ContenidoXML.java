/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesUtilitarias;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author calde
 */
public class ContenidoXML {
    private String xml;
    private Element rootElement;

    public ContenidoXML(String pContenidoHTML) throws SAXException, IOException, ParserConfigurationException{
      
        String contenidoHTMLRemplazado =  remplazarCamposDeHTML(pContenidoHTML);
        this.xml = contenidoHTMLRemplazado;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructorDeDocumento = factory.newDocumentBuilder();
        Document documento = constructorDeDocumento.parse(new InputSource(new StringReader(this.xml)));
        this.rootElement = documento.getDocumentElement();
    }

    public String obtenerValorDeEtiqueta(String pEtiqueta){
        try {
            NodeList listaDeNodo = this.rootElement.getElementsByTagName(pEtiqueta);
                if (listaDeNodo != null && listaDeNodo.getLength() > 0) {
                    NodeList sublistaDeNodo = listaDeNodo.item(0).getChildNodes();

                if (sublistaDeNodo != null && sublistaDeNodo.getLength() > 0) {
                    return sublistaDeNodo.item(0).getNodeValue();
                }
            }
        } 
        catch (Exception campoVacio) {
            return "0";
        }
        return "0";
    }

    private String remplazarCamposDeHTML(String pCadena){
      pCadena = pCadena.replace("&lt;", "<");
      pCadena = pCadena.replace("&gt;", ">");
      return pCadena;
    }
}
