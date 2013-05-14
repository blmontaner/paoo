package uy.edu.ort.paoo.negocio.procesadorxml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import uy.edu.ort.paoo.datos.dao.IClienteDAO;
import uy.edu.ort.paoo.datos.dao.IProgramaDAO;
import uy.edu.ort.paoo.datos.dominio.Pagina;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.datos.factory.Factory;


public class ProcesadorProgramas {
	public static String NODO_PROGRAMA ="programa";
	public static String NODO_CLIENTE ="cliente";
	public static String NODO_NOMBRE ="nombre";
	public static String NODO_PAGINA ="pagina";
	public static String NODO_PAGINAS ="paginas";
	public static String NODO_HTMLDATA ="htmlData";
	
	//TODO: Unit test 
	public static void procesarProgramas(String ruta){
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

            for (int i = 0; i < programas.getLength(); i++) {
            	Node programaNode = programas.item(i);
            	prog = new Programa();            	
                NodeList hijos = programaNode.getChildNodes();
                for (int j = 0; j < hijos.getLength(); j++) {
                	Node n = hijos.item(j);
                    if(n.getNodeType() == Node.ELEMENT_NODE){
                    	if(n.getNodeName().equals(NODO_CLIENTE)){
                    		prog.setCliente(clienteDAO.getByPK(n.getNodeValue()));
                    	}
                    	if(n.getNodeName().equals(NODO_NOMBRE)){
                    		prog.setNombre(n.getNodeValue());
                    	}
                    	if(n.getNodeName().equals(NODO_PAGINAS)){
                    		NodeList pags = n.getChildNodes();
                            for (int p = 0; p < pags.getLength(); p++) {
                            	Node pagNode = pags.item(p);
                            	if(pagNode.getNodeType() == Node.ELEMENT_NODE){
                            		pag = new Pagina();
                            		if(pagNode.getNodeName().equals(NODO_NOMBRE)){
                                 		pag.setNombre(pagNode.getNodeValue());
                                 	}
                                 	if(pagNode.getNodeName().equals(NODO_HTMLDATA)){
                                 		pag.setBody(pagNode.getNodeValue());
                                 	}
                                 	prog.getPaginas().add(pag);
                            	}
                            }
                    	}
                    }
                }

                programaDAO.save(prog);
            }
        } catch (SAXException ex) {
        	//TODO: Capa Exceptions !!!!!!!!!!!!!
            //Logger.getLogger(PracticoDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException|ParserConfigurationException  ex) {
            //Logger.getLogger(PracticoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void crearArchivo(StringBuffer sb){
    	File f = new File("src/resources/porfolio.html");
    	try {
    		FileWriter fw = new FileWriter(f);
    		fw.write(sb.toString());
    		fw.flush();
    		fw.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}
