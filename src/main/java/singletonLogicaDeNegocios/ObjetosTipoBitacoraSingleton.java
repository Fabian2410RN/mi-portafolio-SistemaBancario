/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singletonLogicaDeNegocios;
import logicaDeNegocios.RegistroGeneralBitacoras;
/**
 *
 * @author estadm
 */
public class ObjetosTipoBitacoraSingleton {
    private static RegistroGeneralBitacoras instancia;
    
    private ObjetosTipoBitacoraSingleton() {}
    
    public static synchronized RegistroGeneralBitacoras instanciar() {
        if(instancia == null) {
            instancia = new RegistroGeneralBitacoras();
        }
        return instancia;
    }
}
