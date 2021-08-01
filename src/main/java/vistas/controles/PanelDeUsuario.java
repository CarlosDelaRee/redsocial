
package vistas.controles;

import controladores.ControladorDeTemasYEtiquetas;
import controladores.ControladorDeUsuarios;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import modelos.Usuario;
import utils.ActionCallbackOneArg;
import utils.GeneradorDeAvatar;
import utils.Globals;

public class PanelDeUsuario extends javax.swing.JPanel implements MouseListener {

    ControladorDeUsuarios controladorUsuarios;
    ControladorDeTemasYEtiquetas controladorSuscripciones;
    
    ActionCallbackOneArg<String> temaSeleccionadoAction;
    ActionCallbackOneArg<String> etiquetaSeleccionadaAction;

    public PanelDeUsuario() {
        initComponents();        
        initView();
    }
    
    private void initView() {
        
        controladorUsuarios = new ControladorDeUsuarios();
        controladorSuscripciones = new ControladorDeTemasYEtiquetas();
        
        cargarUsuario();
        cargarTemas();
        cargarEtiquetas();
        
        setSize(new java.awt.Dimension(100, 300));
        setBackground(Color.WHITE);
        setVisible(true);
    }
    
    private void cargarUsuario() {
        Usuario usuario = Globals.getUsuarioLogeado();
        BufferedImage image;
        
        try {
            image = GeneradorDeAvatar.obtener(usuario.getAvatar(), usuario.getGenero());
            
            JLabel label = new JLabel(new ImageIcon(image));

            pnlAvatar.setSize(image.getWidth(), image.getHeight());
            pnlAvatar.add(label);
        } catch (IOException ex) {
            Logger.getLogger(PanelDeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        lblUser.setText(usuario.getNombre() + " " + usuario.getApellido());
    }
    
    public void cargarTemas() {        
        for (String tema : controladorSuscripciones.obtenerTemasConSuscripcion()) {
            JLabel label = new JLabel();            
            int fontSizeToUse = 14;
            label.setFont(new Font(label.getFont().getName(), Font.PLAIN, fontSizeToUse));            
            label.setText(tema);   
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));            
            label.addMouseListener(this);            
            
            pnlTemas.add(label);
            pnlTemas.add(Box.createRigidArea(new Dimension(5, 3)));
        }
    }
    
    public void cargarEtiquetas() {
        for (String tag : controladorSuscripciones.obtenerTagsConSuscripcion()) {
            JLabel label = new JLabel();
            
            int fontSizeToUse = 14;
            label.setFont(new Font(label.getFont().getName(), Font.PLAIN, fontSizeToUse));
            label.setText(tag);   
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));            
            label.addMouseListener(this);           
            
            pnlEtiquetas.add(label);
            pnlEtiquetas.add(Box.createRigidArea(new Dimension(5, 3)));
        }
    }
    
    public void onTemaSeleccionado(ActionCallbackOneArg<String> callback) {
        temaSeleccionadoAction = callback;
    }
    
    public void onEtiquetaSeleccionada(ActionCallbackOneArg<String> callback) {
        etiquetaSeleccionadaAction = callback;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAvatar = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        lblHeaderTemas = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblHeaderEtiquetas = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        pnlEtiquetas = new javax.swing.JPanel();
        pnlTemas = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlAvatar.setBackground(new java.awt.Color(255, 255, 255));
        pnlAvatar.setLayout(new javax.swing.BoxLayout(pnlAvatar, javax.swing.BoxLayout.Y_AXIS));

        lblUser.setText("jLabel1");

        lblHeaderTemas.setBackground(new java.awt.Color(255, 255, 255));
        lblHeaderTemas.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblHeaderTemas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHeaderTemas.setText("Temas");

        jSeparator1.setBounds(new java.awt.Rectangle(0, 50, 50, 10));

        lblHeaderEtiquetas.setBackground(new java.awt.Color(255, 255, 255));
        lblHeaderEtiquetas.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblHeaderEtiquetas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHeaderEtiquetas.setText("Etiquetas");

        jSeparator2.setBounds(new java.awt.Rectangle(0, 50, 50, 10));

        pnlEtiquetas.setBackground(new java.awt.Color(255, 255, 255));
        pnlEtiquetas.setName("pnlEtiquetas"); // NOI18N
        pnlEtiquetas.setLayout(new javax.swing.BoxLayout(pnlEtiquetas, javax.swing.BoxLayout.Y_AXIS));

        pnlTemas.setBackground(new java.awt.Color(255, 255, 255));
        pnlTemas.setName("pnlTemas"); // NOI18N
        pnlTemas.setLayout(new javax.swing.BoxLayout(pnlTemas, javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(pnlEtiquetas, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHeaderTemas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHeaderEtiquetas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(pnlTemas, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblUser)))
                .addGap(18, 18, 18)
                .addComponent(lblHeaderTemas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTemas, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHeaderEtiquetas)
                .addGap(1, 1, 1)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEtiquetas, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblHeaderEtiquetas;
    private javax.swing.JLabel lblHeaderTemas;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPanel pnlAvatar;
    private javax.swing.JPanel pnlEtiquetas;
    private javax.swing.JPanel pnlTemas;
    // End of variables declaration//GEN-END:variables

    //Estos metodos tienens que estar por que implementamos MouseListener
    //para poder manejar el click en las etiquetas generadas dinamicamente
    @Override
    public void mouseClicked(MouseEvent e) {
        // cargar solo las publicaciones 
        JLabel label = ((JLabel)e.getSource());
        String parent = label.getParent().getName();
        
        if (parent.equals("pnlTemas")) {
            temaSeleccionadoAction.execute(label.getText());
        } else if (parent.equals("pnlEtiquetas")) {
            etiquetaSeleccionadaAction.execute(label.getText());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
