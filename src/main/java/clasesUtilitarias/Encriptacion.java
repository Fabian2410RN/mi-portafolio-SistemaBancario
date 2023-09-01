/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesUtilitarias;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Jairo Calderón
 */
public class Encriptacion {
    private SecretKeySpec claveSecreta;
    
    private void generarClaveSecreta() throws Exception{
        String clave = "remontada";
        byte[] claveEncriptacion = clave.getBytes("UTF-8");
        MessageDigest algoritmoHash = MessageDigest.getInstance("SHA-1");
        claveEncriptacion = algoritmoHash.digest(claveEncriptacion);
        claveEncriptacion = Arrays.copyOf(claveEncriptacion, 16);
        this.claveSecreta = new SecretKeySpec(claveEncriptacion, "AES");
    }
    
    private Cipher generarAlgortimoCipherEncriptado() throws Exception {
        Cipher algoritmoCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");        
        algoritmoCipher.init(Cipher.ENCRYPT_MODE, this.claveSecreta);
        return algoritmoCipher;
    }
    
    private Cipher generarAlgortimoCipherDesencriptado() throws Exception {
        Cipher algoritmoCipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        algoritmoCipher.init(Cipher.DECRYPT_MODE, this.claveSecreta);
        return algoritmoCipher;
    }
    
    public String encriptar(String pMensajePorEncriptar) throws Exception {
        this.generarClaveSecreta();
        Cipher algoritmoCipher = this.generarAlgortimoCipherEncriptado();
        byte[] byestPorEncriptar = pMensajePorEncriptar.getBytes("UTF-8");
        byte[] bytesEncriptados = algoritmoCipher.doFinal(byestPorEncriptar);
        String mensajeEncriptado = Base64.getEncoder().encodeToString(bytesEncriptados);
        return mensajeEncriptado;
    }
    
    public String desencriptar(String pMensajePorDesencriptar) throws Exception {
        this.generarClaveSecreta();
        Cipher algoritmoCipher = this.generarAlgortimoCipherDesencriptado();
        byte[] bytesEncriptados = Base64.getDecoder().decode(pMensajePorDesencriptar);
        byte[] bytesDesencriptados = algoritmoCipher.doFinal(bytesEncriptados);
        String datos = new String(bytesDesencriptados);
        return datos;
    }
}
