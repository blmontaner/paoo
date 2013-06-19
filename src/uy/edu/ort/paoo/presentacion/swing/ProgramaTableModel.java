package uy.edu.ort.paoo.presentacion.swing;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import uy.edu.ort.paoo.datos.dominio.Programa;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ProgramaTableModel extends AbstractTableModel{

    List<Programa> programas;
    String[] headers = new String[]{"Nombre","Cliente","Paginas","Peso"};
    
    ProgramaTableModel(List<Programa> l){
        this.programas = l;
    }
    
    @Override
    public int getRowCount() {
        return programas.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return programas.get(rowIndex).getNombre();
        }
        if(columnIndex == 1){
            return programas.get(rowIndex).getCliente().getIdentificador();
        }
        if(columnIndex == 2){
            return programas.get(rowIndex).getCantidadPaginas();
        }
        if(columnIndex == 3){
            return programas.get(rowIndex).getPesoTotal();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return headers[columnIndex];
    }
    
}
