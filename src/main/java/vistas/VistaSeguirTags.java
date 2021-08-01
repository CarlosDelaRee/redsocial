package vistas;

import controladores.ControladorDeTemasYEtiquetas;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JCheckBox;

public class VistaSeguirTags extends javax.swing.JFrame {
    
    ControladorDeTemasYEtiquetas controlador;
    ArrayList<String> tagsConSuscripcion;
    
    public VistaSeguirTags() {
        initComponents();
        initView();
    }
    
    private void initView() {
        controlador = new ControladorDeTemasYEtiquetas();
        tagsConSuscripcion = new ArrayList<>();
        
        getContentPane().setBackground(Color.WHITE);
        setSize(new java.awt.Dimension(400, 580));
        setLocationRelativeTo(null);
        setVisible(true);
        
        obtenerSuscripciones();
    }
    
    private void obtenerSuscripciones(){
        tagsConSuscripcion = controlador.obtenerTagsConSuscripcion();
    }
    
    private void buscarHashTags(){
        if (!edtBuscar.getText().isBlank()) {
            pnlTags.removeAll();           
            
            for (String tag : controlador.buscarHashtags(edtBuscar.getText())) {
                JCheckBox checkbox = new JCheckBox();
                checkbox.setText(tag);
                
                for (String suscripcion : tagsConSuscripcion) {
                    if (suscripcion.equals(tag)) {
                        checkbox.setSelected(true);
                    }
                }
                
                checkbox.addItemListener((ItemEvent e) -> {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        controlador.suscribirseATag(((JCheckBox)e.getSource()).getText());
                        obtenerSuscripciones();
                    } else {
                        controlador.desuscribirseDeTag(((JCheckBox)e.getSource()).getText());
                        obtenerSuscripciones();
                    }
                });
                
                pnlTags.add(checkbox);
            }

            pnlTags.revalidate();
            pnlTags.repaint();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        edtBuscar = new javax.swing.JTextField();
        pnlTags = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Introduce una palabra para buscar hashtags relacionados");

        edtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtBuscarKeyPressed(evt);
            }
        });

        pnlTags.setBackground(new java.awt.Color(255, 255, 255));
        pnlTags.setLayout(new javax.swing.BoxLayout(pnlTags, javax.swing.BoxLayout.Y_AXIS));

        btnSalir.setText("Salir");
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtBuscar)
                    .addComponent(pnlTags, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 28, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTags, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtBuscarKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            buscarHashTags();
        }
    }//GEN-LAST:event_edtBuscarKeyPressed

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        dispose();
    }//GEN-LAST:event_btnSalirMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JTextField edtBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnlTags;
    // End of variables declaration//GEN-END:variables
}
