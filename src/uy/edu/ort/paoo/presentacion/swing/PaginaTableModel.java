/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.presentacion.swing;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import uy.edu.ort.paoo.datos.dominio.Pagina;

/**
 *
 * @author bruno
 */
public class PaginaTableModel extends AbstractTableModel{

    List<Pagina> paginas;
    String[] headers = new String[]{"Nombre","Peso","Lineas","Body"};
    
    PaginaTableModel(List<Pagina> l){
        this.paginas = l;
    }
    
    @Override
    public int getRowCount() {
        return paginas.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return paginas.get(rowIndex).getNombre();
        }
        if(columnIndex == 1){
            return paginas.get(rowIndex).getPeso();
        }
        if(columnIndex == 2){
            return paginas.get(rowIndex).getLineas();
        }
        if(columnIndex == 3){
            return paginas.get(rowIndex).getBody();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return headers[columnIndex];
    }
    
}
