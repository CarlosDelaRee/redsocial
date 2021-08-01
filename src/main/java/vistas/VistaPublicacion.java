package vistas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import modelos.Publicacion;
import modelos.Usuario;
import utils.GeneradorDeAvatar;

public class VistaPublicacion extends javax.swing.JPanel {
    private Publicacion publicacion;
    
    public VistaPublicacion() {
        initComponents();
    }
    
    public void cargar(Publicacion publicacion) {
        this.publicacion = publicacion;
        
        cargarEtiquetas();
        cargarImagenes();
        
        Usuario autor = publicacion.getAutor();
        
        lblContenido.setText(convertToMultiline(publicacion.getContenido()));        
        lblAutor.setText(autor.getNombre() + " " + autor.getApellido());
        lblFecha.setText(publicacion.getFecha().toString());
        lblLikes.setText(Integer.toString(publicacion.getLikes()));
        
        revalidate();
        repaint();
    }
    
    private void cargarEtiquetas() {
        for (String etiqueta : publicacion.getEtiquetas()) {
            JLabel lbl = new JLabel();
            lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            lbl.setText("#" + etiqueta);
            lbl.setForeground(Color.BLUE);
            
            pnlEtiquetas.add(lbl);
            pnlEtiquetas.revalidate();
            pnlEtiquetas.repaint();
        }
    }
    
    private void cargarImagenes() {
        BufferedImage image;
        
        try {
            Path parentDir = Paths.get(""); 
            String pathStr = "";
            JLabel label;
            
            //Por lo pronto solo cargaremos la primera imagen, si el tiempo lo
            //permite agregaremos manejo para mas
            if (publicacion.getImagenes().size() > 0) {
                image = ImageIO.read(new File(publicacion.getImagenes().get(0)));
                
                if (image != null) {
                    if (image.getWidth() > 550) {
                        label = new JLabel(new ImageIcon(image.getScaledInstance(500, 500, Image.SCALE_DEFAULT)));
                    } else {
                        label = new JLabel(new ImageIcon(image));
                    }
                    
                    pnlImage.setSize(image.getWidth(), image.getHeight());
                    pnlImage.add(label);
                }                
            }
            
            image = 
                GeneradorDeAvatar.obtener(
                    publicacion.getAutor().getAvatar(), 
                    publicacion.getAutor().getGenero()
                );
            
            label = new JLabel(new ImageIcon(image));
            
            pnlAvatar.setSize(image.getWidth(), image.getHeight());
            pnlAvatar.add(label);    
            
            pathStr = parentDir.toAbsolutePath().toString() + "/resources/like.jpg";
            image = ImageIO.read(new File(pathStr));
            label = new JLabel(new ImageIcon(image));
            
            pnlLike.setSize(image.getWidth(), image.getHeight());
            pnlLike.add(label);
        } catch (IOException ex) {
            Logger.getLogger(VistaPublicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAutor = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        pnlEtiquetas = new javax.swing.JPanel();
        pnlImage = new javax.swing.JPanel();
        lblContenido = new javax.swing.JLabel();
        pnlAvatar = new javax.swing.JPanel();
        pnlLike = new javax.swing.JPanel();
        lblLikes = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        lblAutor.setText("jLabel1");

        lblFecha.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        lblFecha.setText("jLabel2");

        pnlEtiquetas.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout2.setAlignOnBaseline(true);
        pnlEtiquetas.setLayout(flowLayout2);

        pnlImage.setBackground(new java.awt.Color(255, 255, 255));
        pnlImage.setLayout(new javax.swing.BoxLayout(pnlImage, javax.swing.BoxLayout.Y_AXIS));

        lblContenido.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblContenido.setText("jLabel1");

        pnlAvatar.setBackground(new java.awt.Color(255, 255, 255));
        pnlAvatar.setLayout(new javax.swing.BoxLayout(pnlAvatar, javax.swing.BoxLayout.Y_AXIS));

        pnlLike.setBackground(new java.awt.Color(255, 255, 255));
        pnlLike.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlLike.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlLikeMouseClicked(evt);
            }
        });
        pnlLike.setLayout(new javax.swing.BoxLayout(pnlLike, javax.swing.BoxLayout.X_AXIS));

        lblLikes.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblLikes.setText("0");
        pnlLike.add(lblLikes);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 2));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLike, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(pnlImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAutor)
                                    .addComponent(lblFecha)))
                            .addComponent(pnlEtiquetas, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAutor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFecha))
                    .addComponent(pnlAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblContenido, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEtiquetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlImage, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnlLike, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pnlLikeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLikeMouseClicked
        publicacion.agregarLike();
        lblLikes.setText(Integer.toString(publicacion.getLikes()));
    }//GEN-LAST:event_pnlLikeMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblContenido;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblLikes;
    private javax.swing.JPanel pnlAvatar;
    private javax.swing.JPanel pnlEtiquetas;
    private javax.swing.JPanel pnlImage;
    private javax.swing.JPanel pnlLike;
    // End of variables declaration//GEN-END:variables

}
