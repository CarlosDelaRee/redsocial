package modelos.repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RepositorioDeTemasYEtiquetas extends RepositorioBase {
    
    private static RepositorioDeTemasYEtiquetas instance;
    
    //factory method para obtener el singleton
    public static RepositorioDeTemasYEtiquetas getInstance() {
        if (instance == null) {
            instance = new RepositorioDeTemasYEtiquetas();
        }
        
        return instance;
    }
    
    private RepositorioDeTemasYEtiquetas() {
        super();
    }
    
    public ArrayList<String> obtenerHashtagsParaBusqueda(String busqueda) throws SQLException {
        ArrayList<String> tags = new ArrayList<>();
        
        String sql = 
            "select distinct etiqueta from etiquetas where etiqueta like '%" + 
            busqueda + "%'";
        
        Statement query = connection.createStatement();

        ResultSet result =  query.executeQuery(sql);

        while (result.next()) {
            tags.add(result.getString("etiqueta"));
        }
        
        return tags;
    }
    
    public void eliminarSuscripcionATag(String tag, int usuarioId) throws SQLException {
        String sql = "delete from suscripcionesTags where usuario = ? and tag = ?";
        
        PreparedStatement command = connection.prepareStatement(sql);
        
        command.setInt(1, usuarioId);
        command.setString(2, tag);
        
        command.executeUpdate(); 
    }
    
    public void eliminarSuscripcionesDeUsuario(int usuarioId) throws SQLException {
        String sql = "delete from suscripciones where usuario = ?";
        
        PreparedStatement command = connection.prepareStatement(sql);
        
        command.setInt(1, usuarioId);
        
        int affectedRows =  command.executeUpdate(); 
    }
    
    public void eliminarSuscripcionesATagsDeUsuario(int usuarioId) throws SQLException {
        String sql = "delete from suscripcionesTags where usuario = ?";
        
        PreparedStatement command = connection.prepareStatement(sql);
        
        command.setInt(1, usuarioId);
        
        command.executeUpdate(); 
    }
            
            
    
    public void crearSuscripcion(String tema, int usuarioId) throws SQLException {        
        String sql = "insert into suscripciones(tema,usuario) values(?,?)";
        
        PreparedStatement command = connection.prepareStatement(sql);
        
        command.setString(1, tema);
        command.setInt(2, usuarioId);

        int affectedRows =  command.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Ocurrio un Error al intentar crear la suscripción.");
            //ToDo agregar el mensaje de error original y stackstrace
        }   
    } 
    
    public void crearSuscripcionATag(String tag, int usuarioId) throws SQLException {
        
        String sql = "insert into suscripcionesTags(tag,usuario) values(?,?)";
        
        PreparedStatement command = connection.prepareStatement(sql);
        
        command.setString(1, tag);
        command.setInt(2, usuarioId);

        int affectedRows =  command.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Ocurrio un Error al intentar crear la suscripción.");
            //ToDo agregar el mensaje de error original y stackstrace
        }   
    } 
    
    public ArrayList<String> obtenerSuscripcionesDeUsuario(int usuarioId) throws SQLException {
        ArrayList<String> temas = new ArrayList<>();
        
        String sql = "select distinct tema from suscripciones where usuario = " + Integer.toString(usuarioId);
        
        Statement query = connection.createStatement();

        ResultSet result =  query.executeQuery(sql);

        while (result.next()) {
            temas.add(result.getString("tema"));
        }
        
        return temas;
    }
    
    public ArrayList<String> obtenerSuscripcionesATagDeUsuario(int usuarioId) throws SQLException {
        ArrayList<String> tags = new ArrayList<>();
        
        String sql = "select distinct tag from suscripcionesTags where usuario = " + Integer.toString(usuarioId);
        
        Statement query = connection.createStatement();

        ResultSet result =  query.executeQuery(sql);

        while (result.next()) {
            tags.add(result.getString("tag"));
        }
        
        return tags;
    }
    
    public ArrayList<Integer> obtenerPublicacionesParaTag(String tag) throws SQLException {
        ArrayList<Integer> publicaciones = new ArrayList<>();
        
        String sql = "select distinct publicacion from etiquetas where etiqueta = '" + tag + "';";
        
        Statement query = connection.createStatement();

        ResultSet result =  query.executeQuery(sql);

        while (result.next()) {
            publicaciones.add(result.getInt("publicacion"));
        }
        
        return publicaciones;
    } 
}
