/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

/**
 *
 * @author Jairo Calderón
 */
public final class MenuPrincipalCLI {
    public MenuPrincipalCLI() throws Exception {
        this.imprimirOpciones();
    }
    
    public void imprimirOpciones() throws Exception {
        String menuDeOpciones = "";
        menuDeOpciones += "¡BIENVENIDO AL SISTEMA! \n";
        menuDeOpciones += "PULSE 1 para registrar un cliente \n";
        menuDeOpciones += "PULSE 2 para registrar una cuenta \n";
        menuDeOpciones += "PULSE 3 para consultar los datos de un cliente \n";
        menuDeOpciones += "PULSE 4 para consultar los datos de una cuenta \n";
        menuDeOpciones += "PULSE 5 para cambiar el pin de una cuenta \n";
        menuDeOpciones += "PULSE 6 para realizar un depósito en colones \n";
        menuDeOpciones += "PULSE 7 para realizar un depósito en dólares \n";
        menuDeOpciones += "PULSE 8 para realizar un retiro en colones \n";
        menuDeOpciones += "PULSE 9 para realizar un retiro en dólares \n";
        menuDeOpciones += "PULSE 10 para realizar una transferencia \n";
        menuDeOpciones += "PULSE 11 para consultar el tipo de cambio de compra del dólar \n";
        menuDeOpciones += "PULSE 12 para consultar el tipo de cambio de venta del dólar \n";
        menuDeOpciones += "PULSE 13 para consultar el saldo actual \n";
        menuDeOpciones += "PULSE 14 para consultar el saldo actual en dólares \n";
        menuDeOpciones += "PULSE 15 para consultar el estado de cuenta \n";
        menuDeOpciones += "PULSE 16 para consultar el estado de cuenta en dólares \n";
        menuDeOpciones += "PULSE 17 para consultar el estatus de una cuenta \n";
        menuDeOpciones += "PULSE 18 para consultar ganancias totales del banco producto del cobro de comisiones \n";
        menuDeOpciones += "PULSE 19 para Consultar ganancias del banco producto del cobro de comisiones para una cuenta en específico \n";
        menuDeOpciones += "PULSE 20 para Consultar ganancias del banco producto del cobro de comisiones para cada cuenta \n";
        menuDeOpciones += "PULSE 21 para salir \n";
        System.out.println(menuDeOpciones);
        this.leerOpcionIngresada();
    }
    
    public void leerOpcionIngresada() throws Exception {
        String opcionIngresada = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
        switch(opcionIngresada) {
            case "1":
                RegistroDeClientesCLI abrirRegistroDeClientes = new RegistroDeClientesCLI();
            case "2":
                RegistroDeCuentasCLI abrirRegistroDeCuentas = new RegistroDeCuentasCLI();
            case "3":
                ConsultaDeDatosDeUnClienteCLI abrirConsultaDeClientes = new ConsultaDeDatosDeUnClienteCLI();
            case "4":
                ConsultaDeDatosDeUnaCuentaCLI abrirConsultaDeCuentas = new ConsultaDeDatosDeUnaCuentaCLI();
            case "5":
                CambiarPinCLI abrirMenuDeCambioDePin = new CambiarPinCLI();
            case "6":
                DepositoEnColonesCLI abrirMenuDeDepositoEnColones = new DepositoEnColonesCLI();
            case "7":
                DepositoEnDolaresCLI abrirMenuDeDepositoEnDolares = new DepositoEnDolaresCLI();
            case "8":
                RetiroEnColonesCLI abrirMenuDeRetiroEnColones = new RetiroEnColonesCLI();
            case "9":
                RetiroEnDolaresCLI abrirMenuDeRetiroEnDolares = new RetiroEnDolaresCLI();
            case "10":
                TransferenciaCLI abrirMenuDeRealizarTransferencia = new TransferenciaCLI();
            case "11":
                TipoDeCambioDeCompraCLI abrirMenuDeTipoDeCambioDeCompra = new TipoDeCambioDeCompraCLI();
            case "12":
                TipoDeCambioDeVentaCLI abrirMenuDeTipoDeCambioDeVenta = new TipoDeCambioDeVentaCLI();
            case "13":
                ConsultaDeSaldoActualCLI abrirMenuDeConsultaDeSaldoActual = new ConsultaDeSaldoActualCLI();
            case "14":
                ConsultaDeSaldoActualEnDolaresCLI abrirMenuDeConsultaDeSaldoActualEnDolares = new ConsultaDeSaldoActualEnDolaresCLI();
            case "15":
                ConsultaDeEstadoDeCuentaCLI abrirMenuDeConsultaDeEstadoDeCuenta = new ConsultaDeEstadoDeCuentaCLI();
            case "16":
                ConsultaDeEstadoDeCuentaEnDolaresCLI abrirMenuDeConsultaDeEstadoDeCuentaEnDolares = new ConsultaDeEstadoDeCuentaEnDolaresCLI(); 
            case "17":
                ConsultaDeEstatusDeCuentaCLI abrirMenuDeConsultaDeEstatusDeCuenta = new ConsultaDeEstatusDeCuentaCLI();
            case "18":
                ConsultaDeGananciasTotalesDeBancoPorComisionesCLI abrirMenuDeConsultaDeGananciasTotalesDeBancoPorComisiones = new ConsultaDeGananciasTotalesDeBancoPorComisionesCLI();
            case "19":
                ConsultaDeGananciasParaUnaCuentaPorCobroDeComisiones abrirMenuDeConsultaDeGananciasParaUnaCuentaPorCobroDeComisiones = new ConsultaDeGananciasParaUnaCuentaPorCobroDeComisiones();
            case "20":
                ConsultaDeGananciasParaCadaCuentaPorCobroDeComisiones abrirMenuDeConsultaDeGananciasParaCadaCuentaPorCobroDeComisiones = new ConsultaDeGananciasParaCadaCuentaPorCobroDeComisiones();
            case "21":
                System.exit(0);
            default:
                System.out.println("La opción ingresada no es válida");
                this.imprimirOpciones();
        }
    }
}
