package uy.edu.ort.paoo.datos.dao.memoria;

import java.util.ArrayList;
import java.util.List;

import uy.edu.ort.paoo.datos.dominio.Cliente;
import uy.edu.ort.paoo.datos.dominio.Programa;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class DB {

    private List<Cliente> clientes;
    private List<Programa> programas;
    private static DB instance;

    private DB() {
        clientes = new ArrayList<>();
        programas = new ArrayList<>();
    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Programa> getProgramas() {
        return programas;
    }

    public void setProgramas(List<Programa> programas) {
        this.programas = programas;
    }
}
