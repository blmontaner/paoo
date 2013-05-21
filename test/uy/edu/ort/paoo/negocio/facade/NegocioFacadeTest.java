/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.negocio.facade;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import uy.edu.ort.paoo.datos.dao.memoria.DB;
import uy.edu.ort.paoo.datos.dominio.Cliente;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.negocio.procesadorxml.Resultado;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class NegocioFacadeTest {

    
    public static String dirRecursos = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathRecursos");

    /**
     * Test of cargarClientes method, of class NegocioFacade.
     */
    @Test
    public void testCargarClientes() throws Exception {
        try {
            Resultado r = NegocioFacade.cargarClientes(dirRecursos + "4ClientesTestIdRep.xml");
            Assert.assertEquals(4, r.getProcesados());
            Assert.assertEquals(3, r.getExitosos());

            r = NegocioFacade.cargarClientes(dirRecursos + "4ClientesTestIdRep.xml");
            Assert.assertEquals(4, r.getProcesados());
            Assert.assertEquals(4, r.getDescartados());

            r = NegocioFacade.cargarClientes(dirRecursos + "5ClientesOK.xml");
            Assert.assertEquals(5, r.getProcesados());
            Assert.assertEquals(5, r.getExitosos());

            //No existe el archivo
            r = NegocioFacade.cargarClientes(dirRecursos + "NoFile.xml");
            fail();
        } catch (PaooException e) {
        }
    }

    /**
     * Test of cargarProgramas method, of class NegocioFacade.
     */
    @Test
    public void testCargarProgramas() throws Exception {
        
        DB.getInstance().setClientes(new ArrayList<Cliente>());
        DB.getInstance().setProgramas(new ArrayList<Programa>());
        
        System.out.println("cargarProgramas");
        
        String url = "programas_test.xml";
        Resultado result = NegocioFacade.cargarProgramas(url);
        assertEquals(1, result.getProcesados());
        
    }

    /**
     * Test of programasSolicitados method, of class NegocioFacade.
     */
    @Test
    public void testProgramasSolicitados() throws Exception {
        
        DB.getInstance().setClientes(new ArrayList<Cliente>());
        DB.getInstance().setProgramas(new ArrayList<Programa>());
        
        System.out.println("programasSolicitados");
        NegocioFacade.cargarClientes("clientes_test.xml");
        NegocioFacade.cargarProgramas("programas_test.xml");
        
        List result = NegocioFacade.programasSolicitados("");
        assertEquals(1, result.size());
    }

    /**
     * Test of listadoClientes method, of class NegocioFacade.
     */
    @Test
    public void testListadoClientes() throws Exception {
        
        DB.getInstance().setClientes(new ArrayList<Cliente>());
        DB.getInstance().setProgramas(new ArrayList<Programa>());
        
        try {
            NegocioFacade.cargarClientes("5ClientesOK.xml");
        } catch (PaooException e) {
            e.printStackTrace();
        }
        //Busco por id
        List<Cliente> lista = NegocioFacade.listadoClientes("CL13");
        Assert.assertEquals(1, lista.size());
        Assert.assertEquals("CL13", lista.get(0).getIdentificador());

        //Devuelve todos
        lista = NegocioFacade.listadoClientes("");
        Assert.assertEquals(5, lista.size());

        //Busco un cliente q no existe
        lista = NegocioFacade.listadoClientes("NOTEXIST");;
        Assert.assertEquals(null, lista.get(0));
    }

    /**
     * Test of programasSolicitadosCliente method, of class NegocioFacade.
     */
    @Test
    public void testProgramasSolicitadosCliente() throws Exception {
        System.out.println("programasSolicitadosCliente");
        
        DB.getInstance().setClientes(new ArrayList<Cliente>());
        DB.getInstance().setProgramas(new ArrayList<Programa>());
        
        NegocioFacade.cargarClientes("clientes_test.xml");
        NegocioFacade.cargarProgramas("programas_test.xml");
        
        String idCliente = "CL1";
        List result = NegocioFacade.programasSolicitadosCliente(idCliente);
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of topProgramasNroPaginas method, of class NegocioFacade.
     */
    @Test
    public void testTopProgramasNroPaginas() throws Exception {
        System.out.println("topProgramasNroPaginas");
        
        DB.getInstance().setClientes(new ArrayList<Cliente>());
        DB.getInstance().setProgramas(new ArrayList<Programa>());
        
        NegocioFacade.cargarClientes("clientes_test.xml");
        NegocioFacade.cargarProgramas("programas_test.xml");
        
        List result = NegocioFacade.topProgramasNroPaginas();
        assertTrue(result.size() <= 10);
    }

    /**
     * Test of topProgramasMasPesados method, of class NegocioFacade.
     */
    @Test
    public void testTopProgramasMasPesados() throws Exception {
        System.out.println("topProgramasMasPesados");
        
        DB.getInstance().setClientes(new ArrayList<Cliente>());
        DB.getInstance().setProgramas(new ArrayList<Programa>());
        
        NegocioFacade.cargarClientes("clientes_test.xml");
        NegocioFacade.cargarProgramas("programas_test.xml");
        
        List result = NegocioFacade.topProgramasMasPesados();
        assertTrue(result.size() < 10);
    }
}