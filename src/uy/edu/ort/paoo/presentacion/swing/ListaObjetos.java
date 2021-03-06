package uy.edu.ort.paoo.presentacion.swing;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import uy.edu.ort.paoo.datos.DatosPaooException;
import uy.edu.ort.paoo.datos.dominio.Pagina;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.datos.factory.Factory;
import uy.edu.ort.paoo.negocio.NegocioPaooException;
import uy.edu.ort.paoo.negocio.facade.NegocioFacade;
import uy.edu.ort.paoo.negocio.procesadorxml.Resultado;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ListaObjetos extends javax.swing.JDialog {

    public AbstractTableModel model;
    public static final String LISTA_CLIENTES = "Clientes";
    public static final String LISTA_PROGRAMAS = "Programas";
    public static final String LISTA_PROGRAMAS_GEN_HTML = "Programas HTML";
    public static final String LISTA_PROGRAMAS_GEN_PDF = "Programas PDF";
    public static final String LISTA_PROGRAMAS_CLIENTE = "Programas Cliente";
    public static final String LISTA_TOP_PESADOS = "Top programas mas pesados";
    public static final String LISTA_TOP_PAGINAS = "Top programas con mas paginas";
    public static final String LISTA_PAGINAS = "Paginas";
    
    private java.awt.Frame parent;
    Resultado resultado;
    private String tipoLista;
    LoadingCaller worker;

    /**
     * Creates new form ListaObjetos
     */
    public ListaObjetos(java.awt.Frame parent, boolean modal, String tipo) {
        super(parent, modal);
        initComponents();
        jButton2.setVisible(false);
        this.parent = parent;
        worker = new LoadingCaller(parent);
        this.setLocationRelativeTo(null);
        tipoLista = tipo;
        jLabel1.setText(tipo);
        this.setTitle("Lista " + tipo);
        
        cargarTable();

    }
    private void cargarTable(){
        try {
            if(tipoLista.equals(LISTA_CLIENTES)){
                jButton2.setText("Ver Programas");
                jButton2.setVisible(true);
                model = new ClienteTableModel(Factory.getClienteDAO().getAll());
            }            
        
            if(tipoLista.equals(LISTA_TOP_PESADOS)){
                model = new ProgramaTableModel(Factory.getProgramaDAO().getTop10MasPesados());
            }
            
            if(tipoLista.equals(LISTA_TOP_PAGINAS)){
                model = new ProgramaTableModel(Factory.getProgramaDAO().getTop10MasPaginas());
            }
            if(tipoLista.equals(LISTA_PROGRAMAS) || tipoLista.equals(LISTA_PROGRAMAS_GEN_HTML) || tipoLista.equals(LISTA_PROGRAMAS_GEN_PDF)){
                model = new ProgramaTableModel(Factory.getProgramaDAO().getAll());
                jButton2.setVisible(true);
            }
            if(tipoLista.equals(LISTA_PROGRAMAS)){
                jButton2.setVisible(true);
                jButton2.setText("Ver Paginas");
            }
        } catch (DatosPaooException ex) {
            mostrarException("Inicializar ventana", "Ocurrio un problema al inicializar lista de objetos");
        }
        jTable1.setModel(model);
        if(model.getRowCount()>0){
            jTable1.getSelectionModel().setSelectionInterval(0,0);
        }else{
            jButton2.setVisible(false);
            resultado = new Resultado("No hay datos para mostrar");
            resultado.setTipo(Resultado.TIPO_RESULTADO.ERROR);
            DisplayResultado.showResultado(getFrame(), "Inicializar ventana", resultado);
        }
        
    }

    public ListaObjetos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            model = tipoLista.equals(LISTA_CLIENTES) ? new ClienteTableModel(Factory.getClienteDAO().getAll()) : new ProgramaTableModel(Factory.getProgramaDAO().getAll());
        } catch (DatosPaooException ex) {
            mostrarException("Inicializar ventana", "Ocurrio un problema al inicializar lista de objetos");
        }
        jTable1.setModel(model);

    }

    public <T> ListaObjetos(java.awt.Frame parent, boolean modal, List<T> lista, String tipo) {
        super(parent, modal);
        initComponents();
        jButton2.setVisible(false);
        this.tipoLista = tipo;
        if (tipoLista.equals(LISTA_PROGRAMAS_CLIENTE)) {
            List<Programa> l = ((List<Programa>) lista);
            model = new ProgramaTableModel(l);
            this.setTitle("Programas Solicitados");
            jLabel1.setText("Programas");
        }
        
        if (tipoLista.equals(LISTA_PAGINAS)) {
            List<Pagina> l = ((List<Pagina>) lista);
            model = new PaginaTableModel(l);
            this.setTitle("Paginas Programa");
            jLabel1.setText("Paginas");
        }
        
        jTable1.setModel(model);
        this.setLocationRelativeTo(null);
    }

    public String getTipoLista() {
        return tipoLista;
    }

    public void setTipoLista(String tipoLista) {
        this.tipoLista = tipoLista;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Clientes");

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Seleccionar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.setVisible(false);
        this.dispose();
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        final String nomb = (String) model.getValueAt(jTable1.getSelectedRow(), 0);
        if (tipoLista.equals(LISTA_CLIENTES)) {
            List<Programa> lista = null;
            try {
                String idCli = Factory.getClienteDAO().getByPK(nomb).getId().toString();
                lista = NegocioFacade.programasSolicitadosCliente(idCli);
                ListaObjetos lo = new ListaObjetos(getFrame(), true, lista, LISTA_PROGRAMAS_CLIENTE);
                lo.setVisible(true);
            } catch (NegocioPaooException | DatosPaooException ex) {
                mostrarException("Programas Solicitados Cliente", "Ocurrio un problema al obtener los programas");
            }
        }

        if (tipoLista.equals(LISTA_PROGRAMAS_GEN_HTML)) {

            worker.execute();
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        NegocioFacade.generarHTML(nomb);
                        worker.done();
                        JOptionPane.showMessageDialog(getFrame(), "html generado", "Finalizo", 1);
                        cargarTable();
                    } catch (NegocioPaooException ex) {
                        
                        mostrarException("Generar html", "Ocurrio un problema al generar html");
                    }
                }
            };
            t.start();

        }
        if (tipoLista.equals(LISTA_PROGRAMAS_GEN_PDF)) {
            worker.execute();
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        NegocioFacade.generarPDF(nomb);
                        worker.done();
                        JOptionPane.showMessageDialog(getFrame(), "pdf generado", "Finalizo", 1);
                    } catch (NegocioPaooException ex) {
                        mostrarException("Generar pdf", "Ocurrio un problema al generar pdf");
                    }
                }
            };
            t.start();
        }
        if (tipoLista.equals(LISTA_PROGRAMAS)) {
            List<Pagina> lista = null;
            try {
                long idProg = Factory.getProgramaDAO().getByPK(nomb).getId();
                lista = Factory.getProgramaDAO().getPaginasPrograma(idProg);
                ListaObjetos lo = new ListaObjetos(getFrame(), true, lista, LISTA_PAGINAS);
                lo.setVisible(true);
            } catch (DatosPaooException ex) {
                mostrarException("Paginas Programa", "Ocurrio un problema al obtener las paginas");
            }
        }
        
    }                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListaObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListaObjetos dialog = new ListaObjetos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private void mostrarException(String titulo, String mensaje) {
        resultado = new Resultado(mensaje);
        resultado.setTipo(Resultado.TIPO_RESULTADO.EXCEPTION);
        DisplayResultado.showResultado(getFrame(), titulo, resultado);
    }

    public java.awt.Frame getFrame() {
        return parent;
    }

    public void setFrame(java.awt.Frame parent) {
        this.parent = parent;
    }
    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration                   
}