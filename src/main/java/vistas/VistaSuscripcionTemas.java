package vistas;

import controladores.ControladorDeTemasYEtiquetas;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import utils.Globals;

public class VistaSuscripcionTemas extends javax.swing.JFrame {

    ArrayList<JCheckBox> temas;
    ControladorDeTemasYEtiquetas controlador;
    
    public VistaSuscripcionTemas() {
        initComponents();
        
        initView();
    }
    
    private void initView() { 
        temas = new ArrayList<>();
        
        controlador = new ControladorDeTemasYEtiquetas(); 
        
        getContentPane().setBackground(Color.WHITE);
        setSize(new java.awt.Dimension(300, 580));
        setLocationRelativeTo(null);
        setVisible(true);
        cargarTemas();
        obtenerSuscripciones();
    }
    
    private void cargarTemas() {
        for (String tema : Globals.obtenerTopicos()) {
            JCheckBox checkbox = new JCheckBox();
            checkbox.setText(tema);
            
            temas.add(checkbox);
            pnlTemas.add(checkbox); 
        }
        
        pnlTemas.revalidate();
        pnlTemas.repaint();
    } 
    
    private void obtenerSuscripciones() {
        for (String suscripcion : controlador.obtenerTemasConSuscripcion()) {
            for (JCheckBox tema : temas) {
                if (tema.getText().equals(suscripcion)) {
                    tema.setSelected(true);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAutor = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlTemas = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnSuscribirse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblAutor.setBackground(new java.awt.Color(255, 255, 255));
        lblAutor.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblAutor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAutor.setText("Selecciona los temas");

        pnlTemas.setBackground(new java.awt.Color(255, 255, 255));
        pnlTemas.setLayout(new javax.swing.BoxLayout(pnlTemas, javax.swing.BoxLayout.Y_AXIS));

        btnSalir.setText("Salir");
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });

        btnSuscribirse.setText("Suscribirse");
        btnSuscribirse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuscribirseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAutor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTemas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSuscribirse, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 51, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAutor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTemas, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuscribirse, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        dispose();
    }//GEN-LAST:event_btnSalirMouseClicked

    private void btnSuscribirseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuscribirseMouseClicked
        ArrayList<String> temasSeleccionados = new ArrayList<>();
        
        for (JCheckBox checkboxTema : temas) {
            if (checkboxTema.isSelected()) {
                temasSeleccionados.add(checkboxTema.getText());
            }
        }
        
        controlador.suscribirseATemas(temasSeleccionados);
        
        dispose();
    }//GEN-LAST:event_btnSuscribirseMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSuscribirse;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JPanel pnlTemas;
    // End of variables declaration//GEN-END:variables
}
