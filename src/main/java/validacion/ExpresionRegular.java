/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jairo Calderón
 */
public class ExpresionRegular {
    static Pattern patronDeComparacion;
    static Matcher comparacion;
    
    public static boolean verificarContieneLetraMayuscula(String textoPorVerificar) {
        patronDeComparacion = Pattern.compile("[A-Z]+");
        comparacion = patronDeComparacion.matcher(textoPorVerificar);
        boolean contieneLetraMayuscula = comparacion.find();
        return contieneLetraMayuscula;
    }
    
    public static boolean verificarContieneNumero(String textoPorVerificar) {
        patronDeComparacion = Pattern.compile("[0-9]+");
        comparacion = patronDeComparacion.matcher(textoPorVerificar);
        boolean contieneNumero = comparacion.find();
        return contieneNumero;
    }
    
    public static boolean verificarContieneCaracterEspecial(String textoPorVerificar) {
        ExpresionRegular.patronDeComparacion = Pattern.compile("[^A-Za-z0-9]");
        ExpresionRegular.comparacion = ExpresionRegular.patronDeComparacion.matcher(textoPorVerificar);
        boolean contieneLetrasONumeros = comparacion.find();
        return contieneLetrasONumeros;
    }
    
    public static boolean verificarContieneSeisCaracteresSinEspacios(String textoPorVerificar) {
        ExpresionRegular.patronDeComparacion = Pattern.compile("^[\\S][\\S][\\S][\\S][\\S][\\S]$");
        ExpresionRegular.comparacion = ExpresionRegular.patronDeComparacion.matcher(textoPorVerificar);
        boolean contieneSeisCaracteresSinEspacios = comparacion.find();
        return contieneSeisCaracteresSinEspacios;
    }
    
    public static boolean verificarFormatoNumeroTelefonicoEsValido(String textoPorVerificar) {
        ExpresionRegular.patronDeComparacion = Pattern.compile("^[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$");
        ExpresionRegular.comparacion = ExpresionRegular.patronDeComparacion.matcher(textoPorVerificar);
        boolean numeroTelefonicoEsValido = comparacion.find();
        return numeroTelefonicoEsValido;
    }
    
    public static boolean verificarFormatoCorreoElectronicoEsValido(String textoPorVerificar) {
        ExpresionRegular.patronDeComparacion = Pattern.compile("^[\\w]+[@][\\w]+[.][\\w]{2,}$");
        ExpresionRegular.comparacion = ExpresionRegular.patronDeComparacion.matcher(textoPorVerificar);
        boolean correoElectronicoEsValido = comparacion.find();
        return correoElectronicoEsValido;
    }
    
    public static boolean verificarEsPalabra(String textoPorVerificar) {
        ExpresionRegular.patronDeComparacion = Pattern.compile("^[A-Za-z]+$");
        ExpresionRegular.comparacion = ExpresionRegular.patronDeComparacion.matcher(textoPorVerificar);
        boolean esPalabra = comparacion.find();
        return esPalabra;
    }
    
    public static boolean verificarEsNumero(String textoPorVerificar) {
        /*
        ExpresionRegular.patronDeComparacion = Pattern.compile("^[0-9]+[.]{0,1}[0-9]*[0-9]$");
        ExpresionRegular.comparacion = ExpresionRegular.patronDeComparacion.matcher(textoPorVerificar);
        boolean esPalabra = comparacion.find();
        
        
        .println(esPalabra);
        */
        boolean isNumeric =  textoPorVerificar.matches("[+-]?\\d*(\\.\\d+)?");
        return isNumeric;
    }
    
    public static boolean verificarFormatoDeFecha(String textoPorVerificar) {
        ExpresionRegular.patronDeComparacion = Pattern.compile("^[0-9][0-9][/][0-9][0-9][/][0-9][0-9][0-9][0-9]$");
        ExpresionRegular.comparacion = ExpresionRegular.patronDeComparacion.matcher(textoPorVerificar);
        boolean formatoDeFechaEsValido = comparacion.find();
        return formatoDeFechaEsValido;
    }
}
