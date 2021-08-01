package vistas;

import controladores.ControladorDePublicaciones;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import utils.ActionCallback;
import utils.Globals;

public class VistaEditorDePublicaciones extends javax.swing.JFrame {

    private ControladorDePublicaciones controlador;
    private ActionCallback nuevaPublicacionAction;
    
    public VistaEditorDePublicaciones() {
        initComponents();
        initView();
    }
    
    private void initView() {
        controlador = new ControladorDePublicaciones();
        
        BufferedImage image; 
        String parentDir = Paths.get("").toAbsolutePath().toString(); 
        String path;        
        
        try {
            path = parentDir + "/resources/hashtag2.png";
            image = ImageIO.read(new File(path));
            btnEtiqueta.setIcon(new ImageIcon(image));
            
            path = parentDir + "/resources/picture.png";
            image = ImageIO.read(new File(path));
            btnAgregarImagen.setIcon(new ImageIcon(image));
            
            path = parentDir + "/resources/tema.png";
            image = ImageIO.read(new File(path));
            btnTema.setIcon(new ImageIcon(image)); 
            
            for (String tema : Globals.obtenerTopicos()) {
                cbxTemas.addItem(tema);

                if (tema.equals("No Categorizado")) {
                    cbxTemas.setSelectedItem(tema);
            }
        }   
        } catch (IOException ex) {
            Logger
                .getLogger(VistaEditorDePublicaciones.class.getName())
                .log(Level.SEVERE, null, ex);
        }
        
        setSize(new java.awt.Dimension(600, 650));
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void agregarEtiqueta() {
        if (!edtEtiqueta.getText().isBlank()) {
            controlador.agregarEtiqueta(edtEtiqueta.getText());
            
            JLabel lbl = new JLabel();
            lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            lbl.setText("#" + edtEtiqueta.getText());

            pnlEtiquetas.add(lbl);
            pnlEtiquetas.revalidate();
            pnlEtiquetas.repaint();


            edtEtiqueta.setText("");
        }        
    }
    
    public void onNuevaPublicacion(ActionCallback callback) {
        nuevaPublicacionAction = callback;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAutor = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaPrincipal = new javax.swing.JTextArea();
        btnAgregarImagen = new javax.swing.JButton();
        btnEtiqueta = new javax.swing.JButton();
        pnlEtiquetas = new javax.swing.JPanel();
        pnlImagenes = new javax.swing.JPanel();
        btnPublicar = new javax.swing.JButton();
        edtEtiqueta = new javax.swing.JTextField();
        cbxTemas = new javax.swing.JComboBox<>();
        btnTema = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        lblAutor.setBackground(new java.awt.Color(255, 255, 255));
        lblAutor.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblAutor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAutor.setText("Crear Pubicación");

        jSeparator1.setBounds(new java.awt.Rectangle(0, 50, 50, 10));

        jScrollPane1.setBorder(null);

        txaPrincipal.setColumns(20);
        txaPrincipal.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        txaPrincipal.setRows(5);
        txaPrincipal.setBorder(null);
        jScrollPane1.setViewportView(txaPrincipal);

        btnAgregarImagen.setText("Imagen");
        btnAgregarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarImagenActionPerformed(evt);
            }
        });

        btnEtiqueta.setText("Etiqueta");
        btnEtiqueta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEtiquetaActionPerformed(evt);
            }
        });

        pnlEtiquetas.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout1.setAlignOnBaseline(true);
        pnlEtiquetas.setLayout(flowLayout1);

        pnlImagenes.setBackground(new java.awt.Color(255, 255, 255));
        pnlImagenes.setLayout(new javax.swing.BoxLayout(pnlImagenes, javax.swing.BoxLayout.Y_AXIS));

        btnPublicar.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        btnPublicar.setText("Publicar");
        btnPublicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublicarActionPerformed(evt);
            }
        });

        edtEtiqueta.setToolTipText("Escribe una etiqueta");
        edtEtiqueta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        edtEtiqueta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edtEtiquetaKeyTyped(evt);
            }
        });

        cbxTemas.setToolTipText("Selecciona el tema");

        btnTema.setText("Tema");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAutor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEtiquetas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlImagenes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPublicar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEtiqueta, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(btnAgregarImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxTemas, 0, 175, Short.MAX_VALUE)
                            .addComponent(edtEtiqueta))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblAutor)
                .addGap(1, 1, 1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(btnAgregarImagen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEtiqueta)
                    .addComponent(edtEtiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTema)
                    .addComponent(cbxTemas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEtiquetas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlImagenes, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(btnPublicar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edtEtiquetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtEtiquetaKeyTyped
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            agregarEtiqueta();
        }
    }//GEN-LAST:event_edtEtiquetaKeyTyped
 
    private void btnEtiquetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEtiquetaActionPerformed
        agregarEtiqueta();
    }//GEN-LAST:event_btnEtiquetaActionPerformed

    private void btnPublicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublicarActionPerformed
        controlador.publicar(txaPrincipal.getText(), (String)cbxTemas.getSelectedItem() );
        
        nuevaPublicacionAction.execute();
        
        dispose();
    }//GEN-LAST:event_btnPublicarActionPerformed

    private void btnAgregarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImagenActionPerformed
        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            controlador.agregarImagen(selectedFile.getAbsolutePath());

            JLabel lbl = new JLabel();
            lbl.setText(selectedFile.getAbsolutePath());

            pnlImagenes.add(lbl);
            pnlImagenes.revalidate();
            pnlImagenes.repaint();
        }
    }//GEN-LAST:event_btnAgregarImagenActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarImagen;
    private javax.swing.JButton btnEtiqueta;
    private javax.swing.JButton btnPublicar;
    private javax.swing.JButton btnTema;
    private javax.swing.JComboBox<String> cbxTemas;
    private javax.swing.JTextField edtEtiqueta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JPanel pnlEtiquetas;
    private javax.swing.JPanel pnlImagenes;
    private javax.swing.JTextArea txaPrincipal;
    // End of variables declaration//GEN-END:variables
}
