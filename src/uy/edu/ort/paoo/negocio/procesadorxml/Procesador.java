package uy.edu.ort.paoo.negocio.procesadorxml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import uy.edu.ort.paoo.datos.dao.IClienteDAO;
import uy.edu.ort.paoo.datos.dao.IProgramaDAO;
import uy.edu.ort.paoo.datos.dominio.Cliente;
import uy.edu.ort.paoo.datos.dominio.Pagina;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.datos.factory.Factory;
import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;
import uy.edu.ort.paoo.util.Utilidades;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class Procesador {

    public static String NODO_PROGRAMA = "programa";
    public static String NODO_CLIENTE = "cliente";
    public static String NODO_NOMBRE = "nombre";
    public static String NODO_PAGINA = "pagina";
    public static String NODO_PAGINAS = "paginas";
    public static String NODO_HTMLDATA = "htmlData";
    public static ClientesLista clientes = null;

    /**
     * Metodo Procesador de archivos XML, convirtiendolos a programas y paginas.
     *
     * @param ruta Path del Archivo XML a convertir
     * @return
     * @throws PaooException
     */
    private static Resultado procesarProgramas(String ruta) throws PaooException {
        Resultado resultado = new Resultado();
        IClienteDAO clienteDAO = Factory.getClienteDAO();
        IProgramaDAO programaDAO = Factory.getProgramaDAO();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Programa prog;
        Pagina pag;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(ruta));
            NodeList programas = document.getElementsByTagName(NODO_PROGRAMA);
            Boolean saltar;

            for (int i = 0; i < programas.getLength(); i++) {
                Node programaNode = programas.item(i);
                prog = new Programa();
                saltar = false;
                NodeList hijos = programaNode.getChildNodes();
                for (int j = 0; j < hijos.getLength(); j++) {
                    Node n = hijos.item(j);
                    if (n.getNodeType() == Node.ELEMENT_NODE) {
                        if (n.getNodeName().equals(NODO_CLIENTE)) {
                            Cliente cAux = clienteDAO.getByPK(n.getTextContent());
                            if (cAux != null) {
                                prog.setCliente(cAux);
                            } else {
                                resultado.aumentarDescartados();
                                saltar = true;
                                break;                                
                            }
                        }
                        if (n.getNodeName().equals(NODO_NOMBRE)) {
                            if (Utilidades.isValidName(n.getTextContent())&& programaDAO.getByPK(n.getTextContent())==null) {
                                prog.setNombre(n.getTextContent());
                            } else {
                                resultado.aumentarDescartados();
                                saltar = true;
                                break;
                            }
                        }
                        if (n.getNodeName().equals(NODO_PAGINAS)) {
                            NodeList pags = n.getChildNodes();
                            for (int p = 0; p < pags.getLength(); p++) {
                                Node pagNode = pags.item(p);
                                boolean errorPagina = true;
                                if (pagNode.getNodeType() == Node.ELEMENT_NODE) {
                                    pag = new Pagina();
                                    //recorro los nodos de la pagina
                                    NodeList nodos = pagNode.getChildNodes();
                                    for (int x = 0; x < nodos.getLength(); x++) {
                                        Node atributoPagina = nodos.item(x);
                                        if (pagNode.getNodeType() == Node.ELEMENT_NODE) {
                                            if (atributoPagina.getNodeName().equals(NODO_NOMBRE)) {

                                                if (Utilidades.isValidName(atributoPagina.getTextContent())) {
                                                    pag.setNombre(atributoPagina.getTextContent());

                                                    errorPagina = false;
                                                } else {
                                                    resultado.aumentarErrores();
                                                    errorPagina = true;
                                                }
                                            }
                                        }
                                        if (!errorPagina) {
                                            if (atributoPagina.getNodeName().equals(NODO_HTMLDATA)) {
                                                pag.setBody(atributoPagina.getTextContent());
                                            }
                                        }
                                    }
                                    prog.getPaginas().add(pag);
                                }
                            }
                        }
                    }
                }
                if(!saltar){
                    programaDAO.save(prog);
                    resultado.getObjetosProcesados().add(prog);
                }
                resultado.aumentarProcesados();
            }
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new PaooException(ex.getMessage());
        }

        return resultado;
    }

    /**
     * Metodo encargado de parsear los Clientes, desde un archivo XML
     * convirtiendolos en Objetos clientes.
     *
     * @param ruta path del archivo XML de clientes.
     * @return
     * @throws PaooException
     */
    public static Resultado ingresarClientes(String nombreArchivo) throws PaooException {
        JAXBContext context;
        Resultado res = new Resultado();
        String ruta = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathRecursos") + nombreArchivo;
        try {
            context = JAXBContext.newInstance(ClientesLista.class);
            Unmarshaller um = context.createUnmarshaller();
            clientes = (ClientesLista) um.unmarshal(new FileReader(ruta));
        } catch (JAXBException | FileNotFoundException e) {
            throw new PaooException(e.getMessage());
        }
        //uso un hashMap para asegurarme q no tengo repetidos
        Map<String, Cliente> cmap = new HashMap<>();
        //valido q los clientes q se quieren ingresar no existan ya en el sistema
        IClienteDAO clienteDAO = Factory.getClienteDAO();

        for (Cliente c : clientes.getClientes()) {
            if (!cmap.containsKey(c.getIdentificador()) && clienteDAO.getByPK(c.getIdentificador()) == null) {
                cmap.put(c.getIdentificador(), c);
                clienteDAO.save(c);
            } else {
                res.aumentarDescartados();
            }
            res.aumentarProcesados();
        }
        
        return res;
    }

    /*
     * Metodo para cargar programas y validarlos
     * Antes de llamarlo deberia cargar los clientes
     */
    /**
     *
     * @param rutaXML
     * @param rutaXSD
     * @throws PaooException
     */
    public static Resultado cargarProgramas(String nombreXML) throws PaooException {

        String rutaXSD = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathXSD");
        String rutaXML = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathRecursos") + nombreXML;

        File xml = new File(rutaXML);
        File xsd = new File(rutaXSD);

        try {
            if (Utilidades.validarXMLContraXSD(xml, xsd)) {
                //Realizar el resto de las validaciones
                return procesarProgramas(rutaXML);
            }
        } catch (PaooException ex) {
            throw ex;
        }
        return null;
    }
}
