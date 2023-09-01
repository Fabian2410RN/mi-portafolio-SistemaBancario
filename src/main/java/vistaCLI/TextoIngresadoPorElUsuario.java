/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Jairo Calderón
 */
public class TextoIngresadoPorElUsuario {
    public static String solicitarIngresoDeTextoAlUsuario() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String textoIngresadoPorElUsuario = reader.readLine();
        return textoIngresadoPorElUsuario;
    }
    
    public static boolean regresarAMenuPrincipal() {
        try {
            String mensaje = "";
            mensaje += "\nPulse 1 para volver al menú principal \n";
            mensaje += "Pulse cualquier otra tecla para reintentar";
            System.out.println(mensaje);
            String seleccionDeUsuario = solicitarIngresoDeTextoAlUsuario();
            boolean usuarioDeseaVolverAMenuPrincipal = seleccionDeUsuario.equals("1");
            return usuarioDeseaVolverAMenuPrincipal;
        } 
        catch (Exception ex) {
            System.out.println("Ha ocurrido un error al recibir el texto");
            return false;
        }
    }
}
