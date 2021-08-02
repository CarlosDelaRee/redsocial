package vistas.controles;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import utils.ActionCallback;
import vistas.VistaEditorDePublicaciones;
import vistas.VistaSeguirTags;
import vistas.VistaSuscripcionTemas;

public class PanelDeTemasYEtiquetas extends javax.swing.JPanel {

    ArrayList<JCheckBox> temas;
    ActionCallback changeAction;
    
    public PanelDeTemasYEtiquetas() {
        initComponents();
        initView();
    }
    
    private void initView() { 
        temas = new ArrayList<>();
        
        BufferedImage image;        
        
        Path parentDir = Paths.get(""); 
        String pathStr;
        
        pathStr = parentDir.toAbsolutePath().toString() + "/resources/tema.png";
        
        try {
            image = ImageIO.read(new File(pathStr));
            btnSuscripcionTemas.setIcon(new ImageIcon(image)); 
        } catch (IOException ex) {
            Logger.getLogger(VistaEditorDePublicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pathStr = parentDir.toAbsolutePath().toString() + "/resources/hashtag2.png";
        
        try {
            image = ImageIO.read(new File(pathStr));
            btnBuscarEtiquetas.setIcon(new ImageIcon(image)); 
        } catch (IOException ex) {
            Logger.getLogger(VistaEditorDePublicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setSize(new java.awt.Dimension(100, 300));
        setBackground(Color.WHITE);
        setVisible(true);
    }
    
    public void onChange(ActionCallback callback) {
        changeAction = callback;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSuscripcionTemas = new javax.swing.JButton();
        btnBuscarEtiquetas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlTrending = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        btnSuscripcionTemas.setText("Suscribete a un tema");
        btnSuscripcionTemas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuscripcionTemasMouseClicked(evt);
            }
        });

        btnBuscarEtiquetas.setText("Sigue tus tags favoritos");
        btnBuscarEtiquetas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarEtiquetasMouseClicked(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Trending");

        pnlTrending.setBackground(new java.awt.Color(255, 255, 255));
        pnlTrending.setLayout(new javax.swing.BoxLayout(pnlTrending, javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuscripcionTemas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscarEtiquetas, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(pnlTrending, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnSuscripcionTemas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarEtiquetas)
                .addGap(57, 57, 57)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTrending, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(663, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuscripcionTemasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuscripcionTemasMouseClicked
        VistaSuscripcionTemas vista = new VistaSuscripcionTemas();
        vista.onChange(changeAction);
    }//GEN-LAST:event_btnSuscripcionTemasMouseClicked

    private void btnBuscarEtiquetasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarEtiquetasMouseClicked
        VistaSeguirTags vista = new VistaSeguirTags();
        vista.onChange(changeAction);
    }//GEN-LAST:event_btnBuscarEtiquetasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarEtiquetas;
    private javax.swing.JButton btnSuscripcionTemas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pnlTrending;
    // End of variables declaration//GEN-END:variables
}
