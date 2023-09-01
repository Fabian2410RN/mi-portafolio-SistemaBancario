    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDeNegocios;

import listaDinamica.Lista;

/**
 *
 * @author Jairo Calderón
 */
public abstract class Bitacora {
    protected abstract boolean agregarRegistro();
    protected abstract String visualizarBitacora() throws Exception;
    protected abstract void vaciarVisualizadorDeBitacora() throws Exception;
    protected abstract void cargarVisualizadorDeBitacora(Lista<RegistroDeBitacora> pListaDeRegistros) throws Exception;
    public abstract String consultarRegistrosDelDia() throws Exception;
    public abstract String consultarRegistrosDeVista(String pVista) throws Exception;
    public abstract String consultarTodosLosRegistros() throws Exception;
}