package uy.edu.ort.paoo.negocio.procesadorxml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ProcesadorProgramas {
	public static void ejercicio2(String ruta){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        StringBuffer sb = new StringBuffer("<table  border='1'>");
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(ruta));
            NodeList stocks = document.getElementsByTagName("stock");
            for (int i = 0; i < stocks.getLength(); i++) {
                Node stock = stocks.item(i);
                NodeList hijos = stock.getChildNodes();
                for (int j = 0; j < hijos.getLength(); j++) {
                    if(hijos.item(j).getNodeType() == Node.ELEMENT_NODE){
                        traverseTree(hijos.item(j),sb);
                    }
                }
            }
            sb.append("</table>");
            crearArchivo(sb);
        } catch (SAXException ex) {
            //Logger.getLogger(PracticoDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException|ParserConfigurationException  ex) {
            //Logger.getLogger(PracticoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void traverseTree(Node node, StringBuffer sb){
        sb.append("<tr>");
        sb.append("<td>");
        sb.append(node.getFirstChild().getNodeValue());
        sb.append("</td>");
        sb.append("</tr>");
        
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
