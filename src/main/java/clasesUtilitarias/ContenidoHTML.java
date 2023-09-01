/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesUtilitarias;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Jairo Calderón
 */
public class ContenidoHTML {
    public String obtenerContenidoHTML(String pDireccionURL) throws Exception {
        StringBuilder resultadoHTML = new StringBuilder();
        URL url = new URL(pDireccionURL);
        HttpURLConnection conexionURL = (HttpURLConnection) url.openConnection();
        conexionURL.setRequestMethod("GET");

        BufferedReader lecturaDeArchivo = new BufferedReader(new InputStreamReader(conexionURL.getInputStream()));
        String line;
        while ((line = lecturaDeArchivo.readLine()) != null) {
           resultadoHTML.append(line);
        }
        lecturaDeArchivo.close();
        return resultadoHTML.toString();
    }
}
