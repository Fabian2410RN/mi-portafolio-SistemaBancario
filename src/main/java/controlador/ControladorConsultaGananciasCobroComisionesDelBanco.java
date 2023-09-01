
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import logicaDeAccesoADatos.DAOOperacionCatalogoDeCuentas;
import vistaGUI.ConsultaGananciasCobroComisionesDelBanco;
import logicaDeAccesoADatos.IDAOOperacionCatalogoDeCuentas;
import logicaDeNegocios.RegistroGeneralBitacoras;
import serviciosExternos.TipoCambioBCCR;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;

/**
 *
 * @author estadm
 */
public class ControladorConsultaGananciasCobroComisionesDelBanco implements ActionListener{
    public ConsultaGananciasCobroComisionesDelBanco vistaGUI;
    
    
    public ControladorConsultaGananciasCobroComisionesDelBanco(ConsultaGananciasCobroComisionesDelBanco pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnCancelarConsultaComision.addActionListener(this);
        this.vistaGUI.btnConsultarMontosPorCobroComision.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Consultar")) {
            IDAOOperacionCatalogoDeCuentas comisionesCobradas = new DAOOperacionCatalogoDeCuentas();
            double comisionesTotalesDeposito = comisionesCobradas.consultarMontoTotalCobradoComisionesPorDepositos();
            double comisionesTotalesRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetiros();
            double comisionesTotalesDepositoRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetirosYDepositos();
            vistaGUI.txtMontoTotalOperacionesDepositosColones.setText("₡" + String.format("%.2f",comisionesTotalesDeposito));
            vistaGUI.txtMontoTotalOperacionesRetiroColones.setText("₡" + String.format("%.2f",comisionesTotalesRetiros));
            vistaGUI.txtMontoTotalOperacionesDepositosYRetirosColones.setText("₡" + String.format("%.2f",comisionesTotalesDepositoRetiros));
            TipoCambioBCCR tc = new TipoCambioBCCR();
            double tipoCompra = tc.obtenerValorCompra();
            
            vistaGUI.txtMontoTotalOperacionesDepositosDolares.setText("$" + String.format("%.2f",comisionesTotalesDeposito/tipoCompra));
            vistaGUI.txtMontoTotalOperacionesRetirosDolares.setText("$" + String.format("%.2f",comisionesTotalesRetiros/tipoCompra));
            vistaGUI.txtMontoTotalOperacionesDepositosRetirosDolares.setText("$" + String.format("%.2f",comisionesTotalesDepositoRetiros/tipoCompra));
            
            RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
            accion.registrarEnBitacoras(LocalDate.now(), "Consulta de ganancias por cobro del banco", "GUI");
            
        }
        if(evento.getActionCommand().equals("Cancelar")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
    
}
