package uy.edu.ort.paoo.presentacion.swing;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import uy.edu.ort.paoo.negocio.NegocioPaooException;
import uy.edu.ort.paoo.negocio.facade.NegocioFacade;
import uy.edu.ort.paoo.negocio.procesadorxml.Resultado;
import uy.edu.ort.paoo.propiedades.PropiedadesPaooException;

/**
 *
 * @author Bruno
 */
public class MenuPrincipal extends javax.swing.JFrame {
    
    private Resultado resultado;
    private LoadingCaller worker;

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
        worker = new LoadingCaller(this);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            resultado = new Resultado("Ocurrio un problema al iniciar el programa");
            resultado.setTipo(Resultado.TIPO_RESULTADO.EXCEPTION);
            DisplayResultado.showResultado(getThisFrame(),"Inicio",resultado);
        } 
        this.setIconImage((new javax.swing.ImageIcon(getClass().getResource("/uy/edu/ort/paoo/presentacion/swing/img/ico.png"))).getImage());
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Generador Paginas");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uy/edu/ort/paoo/presentacion/swing/img/gears-big.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
        );

        jMenu1.setLabel("Clientes");

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uy/edu/ort/paoo/presentacion/swing/img/cli lst.png"))); // NOI18N
        jMenuItem4.setText("Ver Lista ");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uy/edu/ort/paoo/presentacion/swing/img/xml.png"))); // NOI18N
        jMenuItem1.setLabel("Cargar de Xml");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Programas");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uy/edu/ort/paoo/presentacion/swing/img/prog lst.png"))); // NOI18N
        jMenuItem2.setText("Ver Lista");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uy/edu/ort/paoo/presentacion/swing/img/xml.png"))); // NOI18N
        jMenuItem3.setText("Cargar de Xml");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uy/edu/ort/paoo/presentacion/swing/img/gen.png"))); // NOI18N
        jMenu3.setText("Generar");

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uy/edu/ort/paoo/presentacion/swing/img/HTML.png"))); // NOI18N
        jMenuItem5.setText("Html de Paginas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uy/edu/ort/paoo/presentacion/swing/img/pdf.png"))); // NOI18N
        jMenuItem6.setText("Pdf de paginas");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenu2.add(jMenu3);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uy/edu/ort/paoo/presentacion/swing/img/moreLines.png"))); // NOI18N
        jMenuItem7.setText("Top 10 Mas Pesados");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uy/edu/ort/paoo/presentacion/swing/img/morePages.png"))); // NOI18N
        jMenuItem8.setText("Top 10 Mas Paginas");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        ListaObjetos lc = new ListaObjetos(this, rootPaneCheckingEnabled, ListaObjetos.LISTA_CLIENTES);
        lc.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            
            FileChooser fc = new FileChooser("Seleccione un archivo xml con Programas", this);

            final String nombreFile = fc.showChooser();
            if(!nombreFile.isEmpty()){
                worker.execute();
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            resultado = NegocioFacade.cargarProgramas(nombreFile);
                            worker.done();
                            DisplayResultado.showResultado(getThisFrame(),"Carga Programas",resultado);
                        } catch (NegocioPaooException ex) {
                            worker.done();
                            resultado = new Resultado("Ocurrio un problema al cargar los datos");
                            resultado.setTipo(Resultado.TIPO_RESULTADO.EXCEPTION);
                            DisplayResultado.showResultado(getThisFrame(),"Carga Programas",resultado);
                        }
                    }
                };
                t.start();
            }
        } catch (PropiedadesPaooException ex) {
            worker.done();
            resultado = new Resultado("Ocurrio un problema al cargar los datos");
            resultado.setTipo(Resultado.TIPO_RESULTADO.EXCEPTION);
            DisplayResultado.showResultado(getThisFrame(),"Carga Programas",resultado);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    private JFrame getThisFrame(){
        return this;
    }
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        ListaObjetos lc = new ListaObjetos(this, rootPaneCheckingEnabled, ListaObjetos.LISTA_PROGRAMAS);
        lc.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
        try {
            
            FileChooser fc = new FileChooser("Seleccione un archivo xml con Clientes", this);
        
            final String nombreFile = fc.showChooser();
            if(!nombreFile.isEmpty()){
                worker.execute();
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            resultado = NegocioFacade.cargarClientes(nombreFile);
                            worker.done();
                            DisplayResultado.showResultado(getThisFrame(),"Carga Clientes",resultado);
                        } catch (NegocioPaooException ex) {
                            worker.done();
                            resultado = new Resultado("Ocurrio un problema al cargar los datos");
                            resultado.setTipo(Resultado.TIPO_RESULTADO.EXCEPTION);
                            DisplayResultado.showResultado(getThisFrame(),"Carga Clientes",resultado);
                        }
                    }
                };
                t.start();
            }
            
        } catch (PropiedadesPaooException ex) {
            worker.done();
            resultado = new Resultado("Ocurrio un problema al cargar los datos");
            resultado.setTipo(Resultado.TIPO_RESULTADO.EXCEPTION);
            DisplayResultado.showResultado(getThisFrame(),"Carga Clientes",resultado);
        } 
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        ListaObjetos ls = new ListaObjetos(this, rootPaneCheckingEnabled, ListaObjetos.LISTA_PROGRAMAS_GEN_HTML);
        ls.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        ListaObjetos ls = new ListaObjetos(this, rootPaneCheckingEnabled, ListaObjetos.LISTA_PROGRAMAS_GEN_PDF);
        ls.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        ListaObjetos ls = new ListaObjetos(this, true, ListaObjetos.LISTA_TOP_PESADOS);
        ls.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        ListaObjetos ls = new ListaObjetos(this, true, ListaObjetos.LISTA_TOP_PAGINAS);
        ls.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
