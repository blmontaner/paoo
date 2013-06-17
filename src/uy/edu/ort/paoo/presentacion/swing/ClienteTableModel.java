/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.presentacion.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import uy.edu.ort.paoo.datos.dominio.Cliente;

/**
 *
 * @author alumnoFI
 */
public class ClienteTableModel extends AbstractTableModel{

    List<Cliente> clientes;
    String[] headers = new String[]{"Identificador","Nombre","Pagina","Telefono","Direccion"};
    
    ClienteTableModel(List<Cliente> l){
        this.clientes = l;
    }
    
    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return clientes.get(rowIndex).getIdentificador();
        }
        if(columnIndex == 1){
            return clientes.get(rowIndex).getNombre();
        }
        if(columnIndex == 2){
            return clientes.get(rowIndex).getPagina();
        }
        if(columnIndex == 3){
            return clientes.get(rowIndex).getTelefono();
        }
        if(columnIndex == 4){
            return clientes.get(rowIndex).getDireccion();
        }
        
        return null;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return headers[columnIndex];
    }
    
}
