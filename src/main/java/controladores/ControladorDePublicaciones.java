package controladores;

import java.sql.SQLException;
import modelos.Publicacion;
import modelos.repositorios.RepositorioDePublicaciones;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Globals;

public class ControladorDePublicaciones {

    private Publicacion publicacion;
    private RepositorioDePublicaciones repositorio;
    
    public ControladorDePublicaciones() {
        publicacion = new Publicacion();
        repositorio = RepositorioDePublicaciones.getInstance();
    }
    
    public void publicar(String contenido, String tema) {
        publicacion.setContenido(contenido);
        publicacion.setTema(tema);
        publicacion.Crear();
    }
    
    public void agregarEtiqueta(String etiqueta) {
        publicacion.agregarEtiqueta(etiqueta);
    }
    
    public void agregarImagen(String ruta) {
        publicacion.agregarImagen(ruta);
    }
    
    public ArrayList<Publicacion> obtenerPublicaciones() {
        ArrayList<Publicacion> publicaciones = new ArrayList<>();
        
        try {
            publicaciones = repositorio.obtenerTodas(Globals.getUsuarioLogeado().getId());
        } catch (SQLException ex) {
            Logger.getLogger(ControladorDePublicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return publicaciones;
    };
    
    public ArrayList<Publicacion> obtenerPublicacionesParaTema(String tema) {
        ArrayList<Publicacion> publicaciones = new ArrayList<>();
        
        try {
            publicaciones = repositorio.obtenerPublicacionesParaTema(tema);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorDePublicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return publicaciones;
    };
    
    public ArrayList<Publicacion> obtenerPublicacionesParaTag(String tag) {
        ArrayList<Publicacion> publicaciones = new ArrayList<>();
        
        try {
            publicaciones = repositorio.obtenerPublicacionesParaTag(tag);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorDePublicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return publicaciones;
    };
}
