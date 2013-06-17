/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.presentacion.swing;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import uy.edu.ort.paoo.datos.dominio.Pagina;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.datos.factory.Factory;
import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.negocio.facade.NegocioFacade;
import uy.edu.ort.paoo.negocio.procesadorxml.Resultado;

/**
 *
 * @author Bruno
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
        this.parent = parent;
        worker = new LoadingCaller(parent);
        jButton2.setVisible(tipo.equals(LISTA_PROGRAMAS_GEN_PDF) || tipo.equals(LISTA_PROGRAMAS_GEN_HTML) || tipo.equals(LISTA_CLIENTES));
        if (tipo.equals(LISTA_CLIENTES)) {
            jButton2.setText("Ver Programas");
        }
        if (tipo.equals(LISTA_PROGRAMAS)) {
            jButton2.setText("Ver Paginas");
        }
        try {
            this.setLocationRelativeTo(null);
            tipoLista = tipo;
            jLabel1.setText(tipo);
            this.setTitle("Lista " + tipo);
            
            if(tipo.equals(LISTA_CLIENTES)){
                model = new ClienteTableModel(Factory.getClienteDAO().getAll());
            }            
        
            if(tipo.equals(LISTA_TOP_PESADOS)){
                model = new ProgramaTableModel(Factory.getProgramaDAO().getTop10MasPesados());
            }
            
            if(tipo.equals(LISTA_TOP_PAGINAS)){
                model = new ProgramaTableModel(Factory.getProgramaDAO().getTop10MasPaginas());
            }
            if(tipo.equals(LISTA_PROGRAMAS) || tipo.equals(LISTA_PROGRAMAS_GEN_HTML) || tipo.equals(LISTA_PROGRAMAS_GEN_PDF)){
                model = new ProgramaTableModel(Factory.getProgramaDAO().getAll());
            }
            
        
        } catch (PaooException ex) {
            mostrarException("Inicializar ventana", "Ocurrio un problema al inicializar lista de objetos");
        }
        
        jTable1.setModel(model);

    }

    public ListaObjetos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            model = tipoLista.equals(LISTA_CLIENTES) ? new ClienteTableModel(Factory.getClienteDAO().getAll()) : new ProgramaTableModel(Factory.getProgramaDAO().getAll());
        } catch (PaooException ex) {
            mostrarException("Inicializar ventana", "Ocurrio un problema al inicializar lista de objetos");
        }
        jTable1.setModel(model);

    }

    public <T> ListaObjetos(java.awt.Frame parent, boolean modal, List<T> lista, String tipo) {
        super(parent, modal);
        initComponents();
        this.tipoLista = tipo;
        if (tipoLista.equals(LISTA_PROGRAMAS_CLIENTE)) {
            List<Programa> l = ((List<Programa>) lista);
            model = new ProgramaTableModel(l);
        }
        jTable1.setModel(model);
        this.setTitle("Programas Solicitados");
        jLabel1.setText("Programas");
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        final String nomb = (String) model.getValueAt(jTable1.getSelectedRow(), 0);
        if (tipoLista.equals(LISTA_CLIENTES)) {
            List<Programa> lista = null;
            try {
                String idCli = Factory.getClienteDAO().getByPK(nomb).getId().toString();
                lista = NegocioFacade.programasSolicitadosCliente(idCli);
                ListaObjetos lo = new ListaObjetos(getFrame(), true, lista, LISTA_PROGRAMAS_CLIENTE);
                lo.setVisible(true);
            } catch (PaooException ex) {
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
                    } catch (PaooException ex) {
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
                    } catch (PaooException ex) {
                        mostrarException("Generar pdf", "Ocurrio un problema al generar pdf");
                    }
                }
            };
            t.start();
        }
        if (tipoLista.equals(LISTA_PROGRAMAS)) {
            List<Pagina> lista = null;
            try {
                long idProg = Factory.getPaginaDAO().getByPK(nomb).getId();
                lista = Factory.getProgramaDAO().getPaginasPrograma(idProg);
                ListaObjetos lo = new ListaObjetos(getFrame(), true, lista, LISTA_PROGRAMAS_CLIENTE);
                lo.setVisible(true);
            } catch (PaooException ex) {
                mostrarException("Paginas Programa", "Ocurrio un problema al obtener las paginas");
            }
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
