package logicaDeNegocios;

import java.time.LocalDate;

/**
 *
 * @author estadm
 */
public class RegistroGeneralBitacoras {
    
    public void registrarEnBitacoras(LocalDate pFecha, String pAccion, String pVista){
        
        RegistroDeBitacora registro1 = new RegistroDeBitacora(LocalDate.now(), pAccion, pVista);
        Bitacora bitacoraXML1 = new BitacoraXML(registro1);
        Bitacora bitacoraCSV1 = new BitacoraCSV(registro1);
        Bitacora bitacoraTP1 = new BitacoraTramaPlana(registro1);
        registro1.agregarBitacora(bitacoraXML1);
        registro1.agregarBitacora(bitacoraCSV1);
        registro1.agregarBitacora(bitacoraTP1);
        registro1.registrarEnBitacoras();
        
    }
}
