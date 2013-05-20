package uy.edu.ort.paoo.presentacion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import uy.edu.ort.paoo.datos.dominio.Cliente;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.negocio.facade.NegocioFacade;
import uy.edu.ort.paoo.negocio.procesadorxml.ProcesadorProgramas;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/**
 *
 * @author Bruno Montaner
 * @author Victor Nessi
 *
 */
public class ConsolaUI {
	
	/**
	 * @param args
	 */
	public static final String COMANDO_CARGAR_PROGRAMAS= "loadprog";
	public static final String COMANDO_CARGAR_CLIENTES= "loadcli";
	public static final String COMANDO_PROGRAMAS_SOLICITADOS= "psol";
	public static final String COMANDO_LISTA_CLIENTES = "clst";
	public static final String COMANDO_PROGRAMAS_SOLICITADOS_CLIENTE= "pcli";
	public static final String COMANDO_TOP_PAGINAS= "topn";
	public static final String COMANDO_TOP_PESADOS= "topw";
	public static final String COMANDO_EXIT= "exit";
	public static final String NO_HAY_PROGRAMAS_EN_EL_SISTEMA = "No hay programas en el sistema";
	
    /**
     * @param args
     */
    public static final String COMANDO_CARGAR_PROGRAMAS = "loadprog";
    public static final String COMANDO_CARGAR_CLIENTES = "loadcli";
    public static final String COMANDO_PROGRAMAS_SOLICITADOS = "psol";
    public static final String COMANDO_LISTA_CLIENTES = "clst";
    public static final String COMANDO_PROGRAMAS_SOLICITADOS_CLIENTE = "pcli";
    public static final String COMANDO_TOP_PAGINAS = "topn";
    public static final String COMANDO_TOP_PESADOS = "topw";
    public static final String COMANDO_EXIT = "exit";

		try {
			printHeader();
			printCommands();
			boolean noSalir = true;
			while (noSalir) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				String line = bufferedReader.readLine();
				
				String[] read= line.trim().split(" ");
				String comando = read[0];
				String arg = read.length>1 ? read[1] : "";				
				String mensaje="";
				switch (comando) {
				case COMANDO_CARGAR_CLIENTES:
					System.out.println(">>Carga Clientes");
					try{
						System.out.println(NegocioFacade.cargarClientes(arg));
					}catch(PaooException ex){
						System.out.println("Lo siento a ocurrido un error, Error: "+ex.getMessage());
					}					
					break;
				case COMANDO_CARGAR_PROGRAMAS:
					System.out.println(">>Cargar Programas");
					try{
						System.out.println(NegocioFacade.cargarProgramas(arg));
					}catch(PaooException ex){
						System.out.println("Lo siento a ocurrido un error, Error: "+ex.getMessage());
					}
					break;
				case COMANDO_PROGRAMAS_SOLICITADOS:
					System.out.println(">>Programas Solicitados");
					mensaje = arg == null? NO_HAY_PROGRAMAS_EN_EL_SISTEMA : "No hay programas con ese nombre";
					imprimirLista(NegocioFacade.programasSolicitados(arg),NO_HAY_PROGRAMAS_EN_EL_SISTEMA);
					break;
				case COMANDO_LISTA_CLIENTES:
					System.out.println(">>Listado Clientes");
					mensaje = arg.isEmpty()? "No hay clientes en el sistema" : "No hay clientes con ese id";
					imprimirLista(NegocioFacade.listadoClientes(arg),mensaje);
					break;
				case COMANDO_PROGRAMAS_SOLICITADOS_CLIENTE:
					System.out.println(">>Programas Solicitados Cliente");
					break;
				case COMANDO_TOP_PAGINAS:
					System.out.println(">>Top 10 prog con mas paginas");
					imprimirLista(NegocioFacade.topProgramasNroPaginas(),NO_HAY_PROGRAMAS_EN_EL_SISTEMA);
					break;
				case COMANDO_TOP_PESADOS:
					System.out.println(">>Top 10 prog mas pesados");
					imprimirLista(NegocioFacade.topProgramasMasPesados(),NO_HAY_PROGRAMAS_EN_EL_SISTEMA);
					break;
				case COMANDO_EXIT:
					System.out.println(">>Salir");
					noSalir= false;
					break;
				default:
					System.out.println("No existe comando!");
					printCommands();
					break;
				}
			}
			System.exit(0);
			//
			ProcesadorProgramas.ingresarClientes("src/uy/edu/ort/paoo/recursos/Clientes.xml");
			// System.out.println(DB.getInstance().getClientes());
			// ProcesadorProgramas.cargarProgramas("src/uy/edu/ort/paoo/recursos/programas.xml",
			// "src/uy/edu/ort/paoo/recursos/validadorProgramasXSD.xsd");

        try {
            printHeader();
            printCommands();
            boolean noSalir = true;
            while (noSalir) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String line = bufferedReader.readLine();

                String[] read = line.trim().split(" ");
                String comando = read[0];
                String arg = read.length > 1 ? read[1] : "";

                switch (comando) {
                    case COMANDO_CARGAR_CLIENTES:
                        System.out.println(">>Carga Clientes");
                        try {
                            if (!arg.isEmpty()) {
                                System.out.println(NegocioFacade.cargarClientes(arg));
                            } else {
                                throw new PaooException("Se esperaba un argumento al intentar cargar clientes");
                            }
                        } catch (PaooException ex) {
                            System.out.println("Lo siento ha ocurrido un error, Error: " + ex.getMessage());
                        }
                        break;
                    case COMANDO_CARGAR_PROGRAMAS:
                        System.out.println(">>Cargar Programas");
                        try {
                            System.out.println(NegocioFacade.cargarProgramas(arg));
                        } catch (PaooException ex) {
                            System.out.println("Lo siento a ocurrido un error, Error: " + ex.getMessage());
                        }
                        break;
                    case COMANDO_PROGRAMAS_SOLICITADOS:
                        System.out.println(">>Programas Solicitados");
                        NegocioFacade.programasSolicitados(arg);
                        break;
                    case COMANDO_LISTA_CLIENTES:
                        System.out.println(">>Listado Clientes");
                        List<Cliente> clst = NegocioFacade.listadoClientes(arg);
                        if (clst.size() > 0) {
                            for (Cliente c : clst) {
                                System.out.println(c);
                            }
                        } else {
                            System.out.println("No hay clientes en el sistema");
                        }

                        break;
                    case COMANDO_PROGRAMAS_SOLICITADOS_CLIENTE:
                        System.out.println(">>Programas Solicitados Cliente");
                        NegocioFacade.programasSolicitadosCliente(arg);
                        break;
                    case COMANDO_TOP_PAGINAS:
                        System.out.println(">>Top 10 prog con mas paginas");
                        NegocioFacade.topProgramasNroPaginas();
                        break;
                    case COMANDO_TOP_PESADOS:
                        System.out.println(">>Top 10 prog mas pesados");
                        NegocioFacade.topProgramasMasPesados();
                        break;
                    case COMANDO_EXIT:
                        System.out.println(">>Salir");
                        noSalir = false;
                        break;
                    default:
                        System.out.println("No existe comando!");
                        printCommands();
                        break;
                }
            }
            System.exit(0);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void printHeader() {
        System.out.println("=====================================================================");
        System.out.println("=====================================================================");
        System.out.println("==============Programacion Avanzada orientada a objetos==============");
        System.out.println("==============================Obligatorio 1==========================");
        System.out.println("=====================================================================");
        System.out.println("================ Bruno Montaner ========= Victor Nessi ==============\n");
    }

    private static void printCommands() {
        System.out.println("Ingrese la opcion deseada");
        System.out.println("Comandos:");
        System.out.println(">> comando -\tDescripcion ");
        System.out.println(">> loadcli -\tCargar Clientes");
        System.out.println(">> loadprog -\tCargar Programas");
        System.out.println(">> psol -\tProgramas Solicitados");
        System.out.println(">> clst -\tListado Clientes");
        System.out.println(">> pcli -\tProgramas Solicitados Cliente");
        System.out.println(">> topn -\tTop 10 prog con mas paginas");
        System.out.println(">> topw -\tTop 10 prog mas pesados");
        System.out.println(">> exit -\tSalir");
    }
	
	private static <O> void imprimirLista(List<O> lst,String mensaje){
		if(!lst.isEmpty() && lst.get(0)!=null && lst.size()>0){
			for(O o : lst){
				System.out.println(o);
			}
		}else{
			System.out.println(mensaje);
		}
	}
	
	private static <O> void imprimirLista(List<O> lst,String mensaje){
		if(!lst.isEmpty() && lst.get(0)!=null && lst.size()>0){
			for(O o : lst){
				System.out.println(o);
			}
		}else{
			System.out.println(mensaje);
		}
	}
}
