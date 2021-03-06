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
import uy.edu.ort.paoo.datos.DatosPaooException;

import uy.edu.ort.paoo.datos.dao.IClienteDAO;
import uy.edu.ort.paoo.datos.dao.IProgramaDAO;
import uy.edu.ort.paoo.datos.dominio.Cliente;
import uy.edu.ort.paoo.datos.dominio.Pagina;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.datos.factory.Factory;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;
import uy.edu.ort.paoo.propiedades.PropiedadesPaooException;
import uy.edu.ort.paoo.util.UtilPaooException;
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
     * @throws ProcesadorXMLPaooException
     */
    private static Resultado procesarProgramas(String ruta) throws ProcesadorXMLPaooException {
        try {
            Resultado resultado = new Resultado();
            IClienteDAO clienteDAO = Factory.getClienteDAO();
            IProgramaDAO programaDAO = Factory.getProgramaDAO();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            Programa prog;
            Pagina pag;

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
                            if (Utilidades.isValidName(n.getTextContent()) && programaDAO.getByPK(n.getTextContent()) == null) {
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
                if (!saltar) {
                    programaDAO.save(prog);
                }
                resultado.aumentarProcesados();
            }

            return resultado;
        } catch (SAXException | IOException | ParserConfigurationException | DatosPaooException ex) {
            throw new ProcesadorXMLPaooException(ex.getMessage());
        }
    }

    /**
     * Metodo para validar el XML de los Clientes.
     *
     * @param ruta path del archivo XML de clientes.
     * @return true si el XML es validado correctamente contra el XSD
     * @throws ProcesadorXMLPaooException
     */
    private static boolean validarClientesXSD(String rutaXML) throws ProcesadorXMLPaooException {

        try {
            String rutaXSD = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathXSDClientes");

            File xml = new File(rutaXML);
            File xsd = new File(rutaXSD);

            if (Utilidades.validarXMLContraXSD(xml, xsd)) {
                return true;
            }
            return false;
        } catch (UtilPaooException | PropiedadesPaooException ex) {
            throw new ProcesadorXMLPaooException(ex.getMessage());
        }
    }

    /**
     * Metodo encargado de parsear los Clientes, desde un archivo XML
     * convirtiendolos en Objetos clientes.
     *
     * @param ruta path del archivo XML de clientes.
     * @return
     * @throws ProcesadorXMLPaooException  
     */
    public static Resultado ingresarClientes(String ruta) throws ProcesadorXMLPaooException {

        JAXBContext context;
        Resultado res = new Resultado();

        try {
            if (validarClientesXSD(ruta)) {
                context = JAXBContext.newInstance(ClientesLista.class);
                Unmarshaller um = context.createUnmarshaller();
                clientes = (ClientesLista) um.unmarshal(new FileReader(ruta));
            } else {
                res = new Resultado();
                res.setTipo(Resultado.TIPO_RESULTADO.ERROR);
                res.setMensaje("Archivo xml no validado");
                return res;
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

        } catch (JAXBException | FileNotFoundException | DatosPaooException | ProcesadorXMLPaooException e) {
            throw new ProcesadorXMLPaooException(e.getMessage());
        }
    }

    /*
     * Metodo para cargar programas y validarlos
     * Antes de llamarlo deberia cargar los clientes
     */
    /**
     *
     * @param rutaXML
     * @return
     * @throws ProcesadorXMLPaooException  
     */
    public static Resultado cargarProgramas(String rutaXML) throws ProcesadorXMLPaooException {
        try {
            String rutaXSD = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathXSD");

            File xml = new File(rutaXML);
            File xsd = new File(rutaXSD);
        
            if (Utilidades.validarXMLContraXSD(xml, xsd)) {
                //Realizar el resto de las validaciones
                return procesarProgramas(rutaXML);
            }
            return null;
        } catch (UtilPaooException | PropiedadesPaooException ex) {
            throw new ProcesadorXMLPaooException(ex.getMessage());
        }
    }
}
