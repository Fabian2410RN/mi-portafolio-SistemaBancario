/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDeNegocios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import listaDinamica.Lista;
import listaDinamica.Nodo;
import org.threeten.bp.LocalDate;

/**
 *
 * @author Jairo Calderón
 */
public class BitacoraTramaPlana extends Bitacora{
    private static final int limiteDeCaracteresDeTipoDeAccion = 50;
    private static final int limiteDeCaracteresDeVista = 3;
    private RegistroDeBitacora registroGuardado;
    
    public BitacoraTramaPlana(RegistroDeBitacora pRegistroGuardado) {
        this.registroGuardado = pRegistroGuardado;
    }

    @Override
    protected boolean agregarRegistro() {
        FileWriter escritorDeArchivos = null;
        try {
            
            File archivo = new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoTXT\\Bitacora.txt");
            escritorDeArchivos = new FileWriter(archivo, true);
            String fecha = this.registroGuardado.fecha.toString();
            String tipoDeAccion = this.rellenarEspaciosDeCampo(limiteDeCaracteresDeTipoDeAccion, this.registroGuardado.tipoDeAccion);
            String vista = this.rellenarEspaciosDeCampo(limiteDeCaracteresDeVista, this.registroGuardado.vista);
            String lineaPorAgregar = fecha + " " + tipoDeAccion + " " + vista + "\n";
            escritorDeArchivos.write(lineaPorAgregar);
            escritorDeArchivos.close();
            return true;
        } 
        catch (Exception e) {
            return false;
        }
    }

    @Override
    protected String visualizarBitacora() throws Exception {
        String resultado = "";
        File documento = new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoTXT\\VisualizacionDeBitacora.txt");
        Scanner objetoDeLectura = new Scanner(documento);
        while (objetoDeLectura.hasNextLine()){
            resultado += objetoDeLectura.nextLine() + "\n";
        }
        return resultado;
    }

    @Override
    protected void vaciarVisualizadorDeBitacora() throws Exception {
        /*
        File archivoDeVisualizacionDeBitacoraPorBorrar = new File(System.getProperty("user.dir") + "\\almacenamientoDeBitacoras\\VisualizacionDeBitacora.txt"); 
        archivoDeVisualizacionDeBitacoraPorBorrar.delete();
        File archivoDeVisualizacionDeBitacoraPorCrear = new File(System.getProperty("user.dir") + "\\almacenamientoDeBitacoras\\VisualizacionDeBitacora.txt"); 
        archivoDeVisualizacionDeBitacoraPorCrear.createNewFile();
        */
        File archivoDeVisualizacionDeBitacoraPorBorrar = new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoTXT\\VisualizacionDeBitacora.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDeVisualizacionDeBitacoraPorBorrar));
        bw.write("");
        bw.close();
    }

    @Override
    protected void cargarVisualizadorDeBitacora(Lista<RegistroDeBitacora> pListaDeRegistros) throws Exception {
        this.vaciarVisualizadorDeBitacora();
        Nodo puntero = pListaDeRegistros.inicio;
        FileWriter escritorDeArchivos = null;
        File archivo = new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoTXT\\VisualizacionDeBitacora.txt");
        escritorDeArchivos = new FileWriter(archivo, true);
        while(puntero != null) {
            RegistroDeBitacora registro = (RegistroDeBitacora) puntero.objeto;
            String fecha = registro.fecha.toString();
            String tipoDeAccion = this.rellenarEspaciosDeCampo(limiteDeCaracteresDeTipoDeAccion, registro.tipoDeAccion);
            String vista = this.rellenarEspaciosDeCampo(limiteDeCaracteresDeVista, registro.vista);
            String lineaPorAgregar = fecha + " " + tipoDeAccion + " " + vista + "\n";
            escritorDeArchivos.write(lineaPorAgregar);
            puntero = puntero.siguiente;
        }
        escritorDeArchivos.close();
    }

    @Override
    public String consultarRegistrosDelDia() throws Exception {
        this.vaciarVisualizadorDeBitacora();
        File documento = new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoTXT\\Bitacora.txt");
        Scanner objetoDeLectura = new Scanner(documento);
        Lista<RegistroDeBitacora> resultadosDeConsulta = new Lista<>();
        while (objetoDeLectura.hasNextLine()){
            String linea = objetoDeLectura.nextLine();
            String fechaDeRegistro = linea.substring(0, 10);
            if(fechaDeRegistro.equals(LocalDate.now().toString())) {
                String tipoDeAccion = linea.substring(11, limiteDeCaracteresDeTipoDeAccion + 1);
                String vista = linea.substring(11 + limiteDeCaracteresDeTipoDeAccion + 1, 11 + limiteDeCaracteresDeTipoDeAccion + 1 + limiteDeCaracteresDeVista);
                java.time.LocalDate fechaEnFormatoLocalDate = java.time.LocalDate.parse(fechaDeRegistro);
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
        File documento = new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoTXT\\Bitacora.txt");
        Scanner objetoDeLectura = new Scanner(documento);
        Lista<RegistroDeBitacora> resultadosDeConsulta = new Lista<>();
        while (objetoDeLectura.hasNextLine()){
            String linea = objetoDeLectura.nextLine();
            String vista = linea.substring(10 + limiteDeCaracteresDeTipoDeAccion + 2, 10 + limiteDeCaracteresDeTipoDeAccion + 2 + limiteDeCaracteresDeVista);
            if(vista.equals(pVista)) {
                String fechaDeRegistro = linea.substring(0, 10);
                String tipoDeAccion = linea.substring(11, limiteDeCaracteresDeTipoDeAccion + 1);
                java.time.LocalDate fechaEnFormatoLocalDate = java.time.LocalDate.parse(fechaDeRegistro);
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
        File documento = new File(System.getProperty("user.home") + "\\DesignProject.VF\\src\\main\\resources\\almacenamientoDeBitacoras\\almacenamientoTXT\\Bitacora.txt");
        Scanner objetoDeLectura = new Scanner(documento);
        Lista<RegistroDeBitacora> resultadosDeConsulta = new Lista<>();
        while (objetoDeLectura.hasNextLine()){
            String linea = objetoDeLectura.nextLine();
            String fechaDeRegistro = linea.substring(0, 10);
            String tipoDeAccion = linea.substring(11, limiteDeCaracteresDeTipoDeAccion + 1);
            String vista = linea.substring(11 + limiteDeCaracteresDeTipoDeAccion + 1, 11 + limiteDeCaracteresDeTipoDeAccion + 1 + limiteDeCaracteresDeVista);
            java.time.LocalDate fechaEnFormatoLocalDate = java.time.LocalDate.parse(fechaDeRegistro);
            RegistroDeBitacora registro = new RegistroDeBitacora(fechaEnFormatoLocalDate, tipoDeAccion, vista);
            resultadosDeConsulta.agregarNodo(registro);
        }
        this.cargarVisualizadorDeBitacora(resultadosDeConsulta);
        return this.visualizarBitacora();
    }
    
    private String rellenarEspaciosDeCampo(int pLimiteDeCaracteres, String pCampo) {
        int cantidadDeCaracteres = pCampo.length();
        while(cantidadDeCaracteres < pLimiteDeCaracteres) {
            pCampo += " ";
            cantidadDeCaracteres += 1;
        }
        return pCampo;
    }
}
