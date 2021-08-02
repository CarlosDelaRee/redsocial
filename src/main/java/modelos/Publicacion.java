package modelos;

import java.sql.Date;
import modelos.repositorios.RepositorioDePublicaciones;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Globals;

public class Publicacion {
    private int id;
    private int likes;
    private Usuario autor;
    private String contenido;
    private String tema;
    private ArrayList<String> etiquetas;
    private ArrayList<String> imagenes;
    private Date fecha; 
    
    private RepositorioDePublicaciones repositorio;
    
    public Publicacion() {
        etiquetas = new ArrayList<>();
        imagenes = new ArrayList<>();
        repositorio = RepositorioDePublicaciones.getInstance();
    }
    
    
    public void Cargar(
            int id,
            Usuario autor,
            String contenido, 
            Date fecha, 
            ArrayList<String> etiquetas,
            ArrayList<String> imagenes,
            int likes,
            String tema) {        
        setContenido(contenido);
        this.autor = autor;
        this.fecha = fecha;
        this.etiquetas = etiquetas;
        this.imagenes = imagenes;
        this.id = id;
        this.likes = likes;
        this.tema = tema;
    }
    
    public void Crear() {         
        this.fecha = Date.valueOf(LocalDate.now());
        this.autor = Globals.getUsuarioLogeado();
        
        try {
            id = repositorio.Crear(this);
        } catch (SQLException ex) {
            Logger.getLogger(Publicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarLike() {
        try {
            repositorio.agregarLike(id, 0);
            likes++;
        } catch (SQLException ex) {
            Logger.getLogger(Publicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarEtiqueta(String etiqueta) {
        if (!etiquetas.contains(etiqueta)) {
            etiquetas.add(etiqueta);
        }
    }
    
    public void agregarImagen(String ruta) {
        if (!imagenes.contains(ruta)) {
            imagenes.add(ruta);
        }
    }
    
    
    //Getters
    public Usuario getAutor() {
        return autor;
    }
    
    public String getContenido() {
        return contenido;
    }
    
    public String getTema() {
        return tema;
    }
    
    public Date getFecha() {
        return fecha;
    } 
    
    public ArrayList<String> getEtiquetas() {
        return etiquetas;
    }
    
    public ArrayList<String> getImagenes() {
        return imagenes;
    }
    
    public int getId() {
        return id;
    }
    
    public int getLikes() {
        return likes;
    }
    
    //Setters
    public void setContenido(String value) {
        if (!value.isBlank()) {
            this.contenido = value;
        } else {
            throw new IllegalArgumentException("El contenido no puede estar vacio");
        }
    }
    
    public void setTema(String value) {
        if (!value.isBlank()) {
            this.tema = value;
        } else {
            throw new IllegalArgumentException("Debes seleccionar un tema");
        }
    }
    
    
    
}
