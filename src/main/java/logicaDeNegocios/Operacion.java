package logicaDeNegocios;

import java.time.LocalDate;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOOperacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class Operacion {
    public LocalDate fechaOperacion;
    public String tipoOperacion;
    public boolean seAplicoComision;
    public double montoComision;
    
    public Operacion(String pNumeroCuenta, String pTipoOperacion, boolean pSeAplicoComision, double pMontoComision) {
        this.fechaOperacion = LocalDate.now();
        this.tipoOperacion = pTipoOperacion;
        this.seAplicoComision = pSeAplicoComision;
        this.montoComision = pMontoComision;
        IDAOOperacionCuenta daoOperacion = new DAOOperacionCuenta();
        daoOperacion.registrarOperacion(pNumeroCuenta, this.fechaOperacion.toString(), this.tipoOperacion, this.seAplicoComision, this.montoComision);
    }
    
    public Operacion(String pTipoOperacion, LocalDate pFechaCreacion, boolean pSeAplicoComision, double pMontoComision) {
        this.fechaOperacion = pFechaCreacion;
        this.tipoOperacion = pTipoOperacion;
        this.seAplicoComision = pSeAplicoComision;
        this.montoComision = pMontoComision;
    }
}
