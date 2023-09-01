/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDeNegocios;

import java.io.File;
import java.io.StringWriter;
import java.time.LocalDate;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import listaDinamica.Lista;
import listaDinamica.Nodo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Jairo Calderón
 */
public class BitacoraXML extends Bitacora{
    private RegistroDeBitacora registroGuardado;

    public BitacoraXML(RegistroDeBitacora pRegistroGuardado) {
        this.registroGuardado = pRegistroGuardado;
    }

    @Override
    protected boolean agregarRegistro() {
        try {
            DocumentBuilderFactory fabricaDeDocumentos = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabricaDeDocumentos.newDocumentBuilder();
            
            Document documento = constructor.parse(new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoXML\\Bitacora.xml"));
            Element documentoRaiz = documento.getDocumentElement();
            Element elemento = documento.createElement("Registro");
            documentoRaiz.appendChild(elemento);
            elemento.setAttribute("Fecha", this.registroGuardado.fecha.toString());
            elemento.setAttribute("AccionEjecutada", this.registroGuardado.tipoDeAccion);
            elemento.setAttribute("VistaDeAcceso", this.registroGuardado.vista);
            TransformerFactory fabricaDeTransformadoresDeDatos = TransformerFactory.newInstance();
            Transformer transformadorDeDatos = fabricaDeTransformadoresDeDatos.newTransformer();
            //transformadorDeDatos.setOutputProperty(OutputKeys.INDENT, "yes");
            //transformadorDeDatos.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2"); 
            DOMSource fuente = new DOMSource(documento);
            
            StreamResult resultado = new StreamResult(new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoXML\\Bitacora.xml"));
            transformadorDeDatos.transform(fuente, resultado);
            return true;
        }
        catch (Exception excepcion) {
            return false;
        }
    }
    
    @Override
    protected String visualizarBitacora() throws Exception {
        String resultadoDeConsulta = "";
        DocumentBuilderFactory fabricaDeDocumentos = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabricaDeDocumentos.newDocumentBuilder();
        
        Document documento = constructor.parse(new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoXML\\VisualizacionDeBitacora.xml"));
        documento.getDocumentElement().normalize();
        NodeList listaDeNodos = documento.getElementsByTagName("Registro");
        resultadoDeConsulta += "<Registros>";
        for (int indice = 0; indice < listaDeNodos.getLength(); indice++) 
        {
            Node nodo = listaDeNodos.item(indice);
            Element elemento = (Element) nodo;
            NamedNodeMap listaDeAtributos = elemento.getAttributes();
            resultadoDeConsulta += "\n\t <" + elemento.getNodeName() + " ";
            for (int indiceDeAtributo = 0; indiceDeAtributo < listaDeAtributos.getLength(); indiceDeAtributo++) {
                resultadoDeConsulta += " " + listaDeAtributos.item(indiceDeAtributo).getNodeName() + "=" + listaDeAtributos.item(indiceDeAtributo).getNodeValue();
            }
            resultadoDeConsulta += "/>";
        }
        resultadoDeConsulta += "\n</Registros>";
        return resultadoDeConsulta;
    }

    @Override
    protected void vaciarVisualizadorDeBitacora() throws Exception {
        DocumentBuilderFactory fabricaDeDocumentos = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabricaDeDocumentos.newDocumentBuilder();
        Document documento = constructor.parse(new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoXML\\VisualizacionDeBitacora.xml"));
        documento.getDocumentElement().normalize();
        NodeList listaDeNodos = documento.getElementsByTagName("Registro");
        int cantidadDeNodos = listaDeNodos.getLength();
        for (int indice = 0; indice < cantidadDeNodos; indice++) 
        {
            Node nodo = listaDeNodos.item(0);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) nodo;
                nodo.getParentNode().removeChild(nodo);
            }
        }
        TransformerFactory fabricaDeTrabsformadoresDeDatos = TransformerFactory.newInstance();
        Transformer transformadorDeDatos = fabricaDeTrabsformadoresDeDatos.newTransformer();
        DOMSource fuente = new DOMSource(documento);
        StreamResult resultado = new StreamResult(new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoXML\\VisualizacionDeBitacora.xml"));
        transformadorDeDatos.transform(fuente, resultado);
    }
    
    @Override
    protected void cargarVisualizadorDeBitacora(Lista<RegistroDeBitacora> pListaDeRegistros) throws Exception{
        DocumentBuilderFactory fabricaDeDocumentos = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabricaDeDocumentos.newDocumentBuilder();
        Document documento = constructor.parse(new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoXML\\VisualizacionDeBitacora.xml"));
        Element documentoRaiz = documento.getDocumentElement();
        Nodo puntero = pListaDeRegistros.inicio;
        while(puntero != null) {
            RegistroDeBitacora registro = (RegistroDeBitacora) puntero.objeto;
            Element elemento = documento.createElement("Registro");
            documentoRaiz.appendChild(elemento);
            elemento.setAttribute("Fecha", registro.fecha.toString());
            elemento.setAttribute("AccionEjecutada", registro.tipoDeAccion);
            elemento.setAttribute("VistaDeAcceso", registro.vista);
            puntero = puntero.siguiente;
        }
        TransformerFactory fabricaDeTrabsformadoresDeDatos = TransformerFactory.newInstance();
        Transformer transformadorDeDatos = fabricaDeTrabsformadoresDeDatos.newTransformer();
        //transformadorDeDatos.setOutputProperty(OutputKeys.INDENT, "yes");
        //transformadorDeDatos.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2"); 
        DOMSource fuente = new DOMSource(documento);
        StreamResult resultado = new StreamResult(new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoXML\\VisualizacionDeBitacora.xml"));
        transformadorDeDatos.transform(fuente, resultado);
    }
    
    @Override
    public String consultarRegistrosDelDia() throws Exception {
        this.vaciarVisualizadorDeBitacora();
        Lista<RegistroDeBitacora> resultadosDeConsulta = new Lista<>();
        DocumentBuilderFactory fabricaDeDocumentos = DocumentBuilderFactory.newInstance();
        fabricaDeDocumentos.setIgnoringComments(true);
        fabricaDeDocumentos.setIgnoringElementContentWhitespace(true);
        DocumentBuilder constructor = fabricaDeDocumentos.newDocumentBuilder();
        
        Document documento = constructor.parse(new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoXML\\Bitacora.xml"));
        documento.getDocumentElement().normalize();
        NodeList listaDeNodos = documento.getElementsByTagName("Registro");
        for (int indice = 0; indice < listaDeNodos.getLength(); indice++) 
        {
            Node nodo = listaDeNodos.item(indice);
            Element elemento = (Element) nodo;
            String fechaDeRegistro = elemento.getAttribute("Fecha");
            if(fechaDeRegistro.equals(LocalDate.now().toString()))
            {
                String tipoDeAccion = elemento.getAttribute("AccionEjecutada");
                String vista = elemento.getAttribute("VistaDeAcceso");
                LocalDate fechaEnFormatoLocalDate = LocalDate.parse(fechaDeRegistro);
                RegistroDeBitacora registro = new RegistroDeBitacora(fechaEnFormatoLocalDate, tipoDeAccion, vista);
                resultadosDeConsulta.agregarNodo(registro);
            }
        }
        this.cargarVisualizadorDeBitacora(resultadosDeConsulta);
        return this.visualizarBitacora();
    }

    @Override
    public String consultarRegistrosDeVista(String pVista) throws Exception {
        this.vaciarVisualizadorDeBitacora();
        Lista<RegistroDeBitacora> resultadosDeConsulta = new Lista<>();
        DocumentBuilderFactory fabricaDeDocumentos = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabricaDeDocumentos.newDocumentBuilder();
        Document documento = constructor.parse(new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoXML\\Bitacora.xml"));
        documento.getDocumentElement().normalize();
        NodeList listaDeNodos = documento.getElementsByTagName("Registro");
        for (int indice = 0; indice < listaDeNodos.getLength(); indice++) 
        {
            Node nodo = listaDeNodos.item(indice);
            Element elemento = (Element) nodo;
            String vista = elemento.getAttribute("VistaDeAcceso");
            if(vista.equals(pVista))
            {
                String fechaDeRegistro = elemento.getAttribute("Fecha");
                String tipoDeAccion = elemento.getAttribute("AccionEjecutada");
                LocalDate fechaEnFormatoLocalDate = LocalDate.parse(fechaDeRegistro);
                RegistroDeBitacora registro = new RegistroDeBitacora(fechaEnFormatoLocalDate, tipoDeAccion, vista);
                resultadosDeConsulta.agregarNodo(registro);
            }
        }
        this.cargarVisualizadorDeBitacora(resultadosDeConsulta);
        return this.visualizarBitacora();
    }

    @Override
    public String consultarTodosLosRegistros() throws Exception {
        this.vaciarVisualizadorDeBitacora();
        Lista<RegistroDeBitacora> resultadosDeConsulta = new Lista<>();
        DocumentBuilderFactory fabricaDeDocumentos = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabricaDeDocumentos.newDocumentBuilder();
        Document documento = constructor.parse(new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoXML\\Bitacora.xml"));
        documento.getDocumentElement().normalize();
        NodeList listaDeNodos = documento.getElementsByTagName("Registro");
        for (int indice = 0; indice < listaDeNodos.getLength(); indice++) 
        {
            Node nodo = listaDeNodos.item(indice);
            Element elemento = (Element) nodo;
            String fechaDeRegistro = elemento.getAttribute("Fecha");
            String tipoDeAccion = elemento.getAttribute("AccionEjecutada");
            String vista = elemento.getAttribute("VistaDeAcceso");
            LocalDate fechaEnFormatoLocalDate = LocalDate.parse(fechaDeRegistro);
            RegistroDeBitacora registro = new RegistroDeBitacora(fechaEnFormatoLocalDate, tipoDeAccion, vista);
            resultadosDeConsulta.agregarNodo(registro);
        }
        this.cargarVisualizadorDeBitacora(resultadosDeConsulta);
        return this.visualizarBitacora();
    }
}
