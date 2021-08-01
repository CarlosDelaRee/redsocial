package controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.repositorios.RepositorioDeTemasYEtiquetas;
import utils.Globals;

public class ControladorDeTemasYEtiquetas {
    
    RepositorioDeTemasYEtiquetas repositorio;
    
    public ControladorDeTemasYEtiquetas() {
        repositorio = RepositorioDeTemasYEtiquetas.getInstance();
    }
    
    public void suscribirseATag(String tag) {
        try {
            repositorio.eliminarSuscripcionATag(tag, Globals.getUsuarioLogeado().getId());
            repositorio.crearSuscripcionATag(tag, Globals.getUsuarioLogeado().getId());

        } catch (SQLException ex) {
            Logger.getLogger(ControladorDeTemasYEtiquetas.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void suscribirseATemas(ArrayList<String> temas) {
        try {
            repositorio.eliminarSuscripcionesDeUsuario(Globals.getUsuarioLogeado().getId());
            
            for (String tema : temas) {
                repositorio.crearSuscripcion(tema, Globals.getUsuarioLogeado().getId());
            } 
        } catch (SQLException ex) {
            Logger.getLogger(ControladorDeTemasYEtiquetas.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void desuscribirseDeTag(String tag) {
        try {
            repositorio.eliminarSuscripcionATag(tag, Globals.getUsuarioLogeado().getId());
        } catch (SQLException ex) {
            Logger.getLogger(ControladorDeTemasYEtiquetas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> obtenerTemasConSuscripcion() {
        ArrayList<String> temas = new ArrayList<>();
        
        try {
            temas = 
                repositorio.obtenerSuscripcionesDeUsuario(
                    Globals.getUsuarioLogeado().getId()
                );
            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorDeTemasYEtiquetas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return temas;
    }
    
    public ArrayList<String> obtenerTagsConSuscripcion() {
        ArrayList<String> tags = new ArrayList<>();
        
        try {
            tags = 
                repositorio.obtenerSuscripcionesATagDeUsuario(
                    Globals.getUsuarioLogeado().getId()
                );
            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorDeTemasYEtiquetas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tags;
    }
    
    public ArrayList<String> buscarHashtags(String busqueda) {
        ArrayList<String> tags = new ArrayList<>();
        
        try {
            for (String tag : repositorio.obtenerHashtagsParaBusqueda(busqueda)) {
                tags.add(tag);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorDeTemasYEtiquetas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tags;
    }
    
}
