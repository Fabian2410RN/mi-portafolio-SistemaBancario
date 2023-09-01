
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import vistaGUI.ConsultaDeEstatusCuenta;
import validacion.ValidacionCuenta;

/**
 *
 * @author estadm
 */
public class ControladorConsultaDeEstatusCuenta implements ActionListener{
    public ConsultaDeEstatusCuenta  vistaGUI;
    
    public ControladorConsultaDeEstatusCuenta(ConsultaDeEstatusCuenta pVistaGUI){
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnCancelarEstatusCuenta.addActionListener(this);
        this.vistaGUI.btnConsultarEstatusCuenta.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Consultar")) {
            String numeroDeCuenta = this.vistaGUI.txtNumeroCuentaConsultarEstatus.getText();
            consultarEstatusCuenta(numeroDeCuenta);
            RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
            accion.registrarEnBitacoras(LocalDate.now(), "Consultar estatus de cuenta", "GUI");
        }
        if(evento.getActionCommand().equals("Cancelar")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
    public static boolean consultarEstatusCuenta(String numeroDeCuenta){
        IDAOCuentaIndividual estadoCuenta = new DAOCuentaIndividual();
        String estatusCuentaActual = estadoCuenta.consultarEstatusCuenta(numeroDeCuenta);

        if(ValidacionCuenta.validarExisteCuenta(numeroDeCuenta)){
            
            MensajeEnPantallaCuenta.imprimirMensajeEstatusDeCuenta(numeroDeCuenta, estatusCuentaActual);
            return true;
        }
        MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
        return false;
    }
}
