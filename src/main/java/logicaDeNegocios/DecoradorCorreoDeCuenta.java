/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDeNegocios;

/**
 *
 * @author Jairo Calderón
 */
public abstract class DecoradorCorreoDeCuenta implements CorreoDeCuenta{
    protected CorreoDeCuenta CorreoDeCuentaDecorado;
    
    public DecoradorCorreoDeCuenta (CorreoDeCuenta pCuentaDecorada) {
        this.CorreoDeCuentaDecorado = pCuentaDecorada;
    }
}
