/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaGUI;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author estadm
 */
public class ColorCelda extends DefaultTableCellRenderer{
    
    private final int columna_patron;
    
    public ColorCelda(int colPatron){
        this.columna_patron = colPatron;
    }
   
   @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int rowIndex, int columnIndex) {
       //azul = "BECB66";
        
        String cadena = table.getValueAt(rowIndex, columna_patron).toString();
        if (validacion.ValidacionTipoDeDato.verificarEsEntero(cadena)){
            int valor = Integer.parseInt(table.getValueAt(rowIndex, columna_patron).toString());
            if (valor >= 1) {
                setBackground(Color.LIGHT_GRAY);
                setForeground(Color.black);
            } else { //si no ponemos el else se pinta toda la columna
                setBackground(Color.white);
                setForeground(Color.black);
              }
        }else{
            setBackground(Color.LIGHT_GRAY);
            setForeground(Color.black);
        }
        
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, rowIndex, columnIndex);
        return this;
    }
   
}
