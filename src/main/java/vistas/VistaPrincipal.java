package vistas;

import controladores.ControladorDePublicaciones;
import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import modelos.Publicacion;

import utils.ActionCallback;
import vistas.controles.NuevaPublicacionPlaceHolder;
import vistas.controles.PanelDeTemasYEtiquetas;
import vistas.controles.PanelDeUsuario;

public class VistaPrincipal extends javax.swing.JFrame {
    
    private ControladorDePublicaciones controlador;

    public VistaPrincipal() {
        controlador = new ControladorDePublicaciones();

        initView();
    }
    
    private void initView() {    
        getContentPane().setLayout(new java.awt.GridBagLayout());        
          
        initPanelPublicaciones();
        initPanelUsuario();
        initPanelEtiquetas();
        initPanelHeader(); 
        
        setSize(new java.awt.Dimension(1200, 700));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pantalla Principal");
        setLocationRelativeTo(null);
        setVisible(true); 
        
        cargarPublicaciones();
    }
    
    private void initPanelHeader() {
        GridBagConstraints constraints = new GridBagConstraints(); 
        
        pnlHeader.setBackground(Color.red);
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 0.05;
        constraints.fill = GridBagConstraints.BOTH;
        
        add(pnlHeader, constraints);
    }
    
    private void initPanelEtiquetas() {
        GridBagConstraints constraints = new GridBagConstraints(); 
        
        pnlEtiquetas.setLayout(new BoxLayout(pnlEtiquetas, BoxLayout.Y_AXIS));
        pnlEtiquetas.setBackground(Color.ORANGE);
        
        constraints.gridx = 4;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.10;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        
        PanelDeTemasYEtiquetas panelLateralDerecho = new PanelDeTemasYEtiquetas();   
        
        panelLateralDerecho.onChange(() -> { initPanelUsuario();});  
        
        pnlEtiquetas.add(panelLateralDerecho);
        
        add(pnlEtiquetas, constraints);
    }
    
    private void initPanelUsuario() {
        GridBagConstraints constraints = new GridBagConstraints(); 
        pnlUsuario.removeAll();
        
        pnlUsuario.setLayout(new BoxLayout(pnlUsuario, BoxLayout.Y_AXIS));
        pnlUsuario.setBackground(Color.BLUE); 
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.10;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        
        PanelDeUsuario panelUsuario = new PanelDeUsuario(); 
                
        panelUsuario.onTemaSeleccionado(
                (String tema) -> { cargarPublicacionesParaTema(tema); });
        
        panelUsuario.onEtiquetaSeleccionada(
                (String tag) -> { cargarPublicacionesParaEtiqueta(tag); });        
        
        panelUsuario.setSize(new java.awt.Dimension(100, 500));
        pnlUsuario.add(panelUsuario); 
        
        add(pnlUsuario, constraints);
        
        pnlUsuario.revalidate();
        pnlUsuario.repaint();
    }
    
    private void initPanelPublicaciones() {
        GridBagConstraints constraints = new GridBagConstraints(); 
        
        pnlPublicaciones.setLayout(new BoxLayout(pnlPublicaciones, BoxLayout.Y_AXIS));
        pnlPublicaciones.setBackground(Color.CYAN);
        
        crearBotonDePublicacion();
        
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.weightx = 0.8;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        
        JScrollPane jScrollPane1 = new JScrollPane();
        jScrollPane1.setBorder(null);
        jScrollPane1
            .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportView(pnlPublicaciones);
        
        add(jScrollPane1, constraints);
    }
    
    private void crearBotonDePublicacion() {
        NuevaPublicacionPlaceHolder placeHolder = new NuevaPublicacionPlaceHolder();        
        
        placeHolder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VistaEditorDePublicaciones editor = new VistaEditorDePublicaciones();

                ActionCallback callback = () -> cargarPublicaciones();

                editor.onNuevaPublicacion(callback);
            }
        });
        
        pnlPublicaciones.add(placeHolder);  
    }
    
    private void cargarPublicaciones(){
        pnlPublicaciones.removeAll();

        crearBotonDePublicacion();
        
        for (Publicacion publicacion : controlador.obtenerPublicaciones()) {
            
            VistaPublicacion pnlPub = new VistaPublicacion();
            pnlPub.cargar(publicacion);
            pnlPublicaciones.add(pnlPub);
           
            pnlPublicaciones.revalidate();
            pnlPublicaciones.repaint();
        }
    }
    
    private void cargarPublicacionesParaTema(String tema) {
        pnlPublicaciones.removeAll();
        
        crearBotonDePublicacion();
        
        for (Publicacion publicacion : controlador.obtenerPublicacionesParaTema(tema)) {
            
            VistaPublicacion pnlPub = new VistaPublicacion();
            pnlPub.cargar(publicacion);
            pnlPublicaciones.add(pnlPub);
           
            pnlPublicaciones.revalidate();
            pnlPublicaciones.repaint();
        }
    }
    
    private void cargarPublicacionesParaEtiqueta(String tag) {
        pnlPublicaciones.removeAll();
        
        crearBotonDePublicacion();
        
        for (Publicacion publicacion : controlador.obtenerPublicacionesParaTag(tag)) {
            
            VistaPublicacion pnlPub = new VistaPublicacion();
            pnlPub.cargar(publicacion);
            pnlPublicaciones.add(pnlPub);
           
            pnlPublicaciones.revalidate();
            pnlPublicaciones.repaint();
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pantalla Principal");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setSize(new java.awt.Dimension(1200, 700));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    JPanel pnlHeader = new javax.swing.JPanel();
    JPanel pnlUsuario = new javax.swing.JPanel();
    JPanel pnlPublicaciones = new javax.swing.JPanel();
    JPanel pnlEtiquetas = new javax.swing.JPanel();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
