/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validacion;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 *
 * @author Jairo Calderón
 */
public class ValidacionTipoDeDato {
    
    public static boolean verificarFechaValida(int pDia, int pMes, int pAno) {
        try {
            LocalDate.of(pAno, pMes, pDia);
            return true;
        }
        catch (DateTimeException excepcion) {
            return false;
        }
    }
    
    public static boolean verificarEsEntero(String pCadena) {
        try{ 
            Integer.parseInt(pCadena); 
            return true;
        }  
        catch (NumberFormatException excepcion)  
        { 
            return false;
        } 
    }
    
    public static boolean verificarEsDecimal(String pCadena) {
        try{ 
            Double.parseDouble(pCadena); 
            return true;
        }  
        catch (NumberFormatException eexcepcion)  
        { 
            return false;
        } 
    }
    
    public static boolean esPositivo(int pNumero) {
        return pNumero > 0;
    }
    
    public static boolean esPositivo(double pNumero) {
        return pNumero > 0;
    }
}
