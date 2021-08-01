package modelos.repositorios;

import modelos.Publicacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelos.Usuario;

public class RepositorioDePublicaciones extends RepositorioBase {
    
    private static RepositorioDePublicaciones instance;
    
    private RepositorioDeUsuarios repositorioUsuarios;
    private RepositorioDeTemasYEtiquetas repositorioSuscripciones;
    
    //factory method para obtener el singleton
    public static RepositorioDePublicaciones getInstance() {
        if (instance == null) {
            instance = new RepositorioDePublicaciones();
        }
        
        return instance;
    }
    
    private RepositorioDePublicaciones() {
        super();
        
        repositorioUsuarios = RepositorioDeUsuarios.getInstance();
        repositorioSuscripciones = RepositorioDeTemasYEtiquetas.getInstance();
    }
    
    private ArrayList<String> obtenerImagenes(int publicacionId) throws SQLException {
        ArrayList<String> imagenes = new ArrayList<>();
        
        String sql = 
            "select ruta from imagenes where publicacion = " + 
            String.valueOf(publicacionId);
        
        Statement query = connection.createStatement();

        ResultSet result =  query.executeQuery(sql);

        while (result.next()) {
            imagenes.add(result.getString("ruta"));
        }
        
        return imagenes;
    }
    
    private ArrayList<String> obtenerEtiquetas(int publicacionId) throws SQLException {
        ArrayList<String> etiquetas = new ArrayList<>();
        
        String sql = 
            "select etiqueta from etiquetas where publicacion = " + 
            String.valueOf(publicacionId);
        
        Statement query = connection.createStatement();

        ResultSet result =  query.executeQuery(sql);

        while (result.next()) {
            etiquetas.add(result.getString("etiqueta"));
        }
        
        return etiquetas;
    }
    
    private int obtenerLikes(int publicacionId) throws SQLException {
        int likes = 0;
        
        String sql = 
            "select count(publicacion) as likes from likes where publicacion = " + 
            String.valueOf(publicacionId);
        
        Statement query = connection.createStatement();

        ResultSet result =  query.executeQuery(sql);

        while (result.next()) {
            likes = result.getInt("likes");
        }
        
        return likes;
    }
    
    public ArrayList<Publicacion> obtenerTodas(int usuarioId) throws SQLException {
        ArrayList<Publicacion> publicaciones = new ArrayList<>();
        ArrayList<String> temas = new ArrayList<>();
        ArrayList<String> tags = new ArrayList<>();
        String temasStr;
        String tagsStr;
        
        String sql = " select tema from suscripciones where usuario = " + Integer.toString(usuarioId);
        
        Statement query = connection.createStatement();

        ResultSet result =  query.executeQuery(sql);

        while (result.next()) {            
            temas.add("'" + result.getString("tema") + "'");
        }
        
        sql = " select tag from suscripcionesTags where usuario = " + Integer.toString(usuarioId);
        
        query = connection.createStatement();

        result =  query.executeQuery(sql);

        while (result.next()) {            
            tags.add("'" + result.getString("tag") + "'");
        } 
        
        temasStr = String.join(",", temas);
        tagsStr = String.join(",", tags);
        
        sql = 
            "select id, contenido, fecha, autor, tema from publicaciones " +
            "where (autor = " + Integer.toString(usuarioId) + ") or (" +
            "tema in (" + temasStr + ")) or (id in (" + tagsStr + ")) ";
        
        publicaciones = obtenerPublicaciones(sql);
        
        return publicaciones;
    }
    
    public ArrayList<Publicacion> obtenerPublicacionesParaTema(String tema) throws SQLException {
        
        String sql = 
            "select id, contenido, fecha, autor, tema from publicaciones " +
            "where tema = '" + tema + "'";
        
        return obtenerPublicaciones(sql);
    }
    
    public ArrayList<Publicacion> obtenerPublicacionesParaTag(String tag) throws SQLException {       
        
        String sql = 
            "select distinct p.id as id, p.contenido, p.fecha, p.autor, p.tema from publicaciones p " +
            "join etiquetas e on e.publicacion = p.id " +
            "where e.etiqueta = '" + tag + "'";
        
        return obtenerPublicaciones(sql);
    }
    
    private ArrayList<Publicacion> obtenerPublicaciones(String sql) throws SQLException {
        ArrayList<Publicacion> publicaciones = new ArrayList<>();
        Publicacion publicacion;
        
        sql = sql + " order by id desc;";
        
        Statement query = connection.createStatement();

        ResultSet result =  query.executeQuery(sql);

        while (result.next()) {
            int autorId = result.getInt("autor");
            
            Usuario autor = repositorioUsuarios.obtenerUsuarioPorId(autorId);
            
            publicacion = new Publicacion();
            publicacion.Cargar(
                result.getInt("id"),
                autor, 
                result.getString("contenido"), 
                result.getDate("fecha"),
                obtenerEtiquetas(result.getInt("id")),
                obtenerImagenes(result.getInt("id")),
                obtenerLikes(result.getInt("id")),
                result.getString("tema")
            );
            
            publicaciones.add(publicacion);
        }
        
        return publicaciones;
    }
    
    public int Crear(Publicacion publicacion) throws SQLException {
        String sql = "insert into publicaciones(autor,contenido,fecha,likes, tema) " +
            " values(?,?,?, 0, ?)";
        
        int id = 0;
        
        PreparedStatement command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        command.setInt(1, publicacion.getAutor().getId());
        command.setString(2, publicacion.getContenido());
        command.setDate(3, publicacion.getFecha());
        command.setString(4, publicacion.getTema());
        
        int affectedRows =  command.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Ocurrio un Error al intentar crear la Publicación.");
            //ToDo agregar el mensaje de error original y stackstrace
        }

        try (ResultSet generatedKeys = command.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("La publicación no fue creada");
            }
        }
        
        agregarEtiquetas(publicacion.getEtiquetas(), id);
        agregarImagenes(publicacion.getImagenes(), id);
        
        return id;     
    }
    
    public Boolean agregarImagenes(ArrayList<String> imagenes, int publicacionId) throws SQLException {
        
        String sql = "insert into imagenes(ruta,publicacion) values(?,?)";
        
        PreparedStatement command = connection.prepareStatement(sql);

        for (String imagen : imagenes) {
            command.setString(1, imagen);
            command.setInt(2, publicacionId);

            command.executeUpdate();
        }
        
        return true;
    }
    
    public Boolean agregarEtiquetas(
        ArrayList<String> etiquetas, 
        int publicacionId) throws SQLException {
        
        String sql = "insert into etiquetas(etiqueta,publicacion) values(?,?)";
        
        PreparedStatement command = connection.prepareStatement(sql);

        for (String etiqueta : etiquetas) {
            command.setString(1, etiqueta);
            command.setInt(2, publicacionId);

            command.executeUpdate();
        }
        
        return true;
    }
    
    public void agregarLike(int publicacionId, int usuarioId) throws SQLException {
        String sql = "insert into likes(publicacion,usuario) values(?,?)";
        
        PreparedStatement command = connection.prepareStatement(sql);

        command.setInt(1, publicacionId);
        command.setInt(2, usuarioId);

        command.executeUpdate();
    }
}
