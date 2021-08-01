package modelos.repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.Usuario;

public class RepositorioDeUsuarios extends RepositorioBase {
    private static RepositorioDeUsuarios instance;
    
    //factory method para obtener el singleton
    public static RepositorioDeUsuarios getInstance() {
        if (instance == null) {
            instance = new RepositorioDeUsuarios();
        }
        
        return instance;
    }
    
    private RepositorioDeUsuarios() {
        super();
    }
    
    public Usuario obtenerUsuarioPorId(int id) throws SQLException {
        Usuario usuario = new Usuario();
        
        String sql = 
            "select id,nombre,apellidos,username,password,avatar,genero from usuarios where id = " + 
            Integer.toString(id) + ";";
        
        Statement query = connection.createStatement();

        ResultSet result =  query.executeQuery(sql);

        while (result.next()) {
            usuario.Cargar(
                result.getInt("id"), 
                result.getString("nombre"), 
                result.getString("apellidos"), 
                result.getString("username"), 
                result.getString("password"), 
                result.getString("genero"),
                result.getInt("avatar"));
        }
        
        return usuario;
    }
    
    public Usuario obtenerUsuarioPorUsername(String username) throws SQLException {
        Usuario usuario = new Usuario();
        
        String sql = 
            "select id,nombre,apellidos,username,password,avatar,genero from usuarios where username = '" + 
            username + "'";
        
        Statement query = connection.createStatement();

        ResultSet result =  query.executeQuery(sql);

        while (result.next()) {
            usuario.Cargar(
                result.getInt("id"), 
                result.getString("nombre"), 
                result.getString("apellidos"), 
                result.getString("username"), 
                result.getString("password"), 
                result.getString("genero"),
                result.getInt("avatar"));
        }
        
        return usuario;
    }
    
    public int Crear(Usuario usuario) throws SQLException {
        int id = 0;
        
        String sql = "insert into usuarios(username,password,nombre,apellidos,avatar,genero)" + 
                " values(?,?,?,?,?,?)";
        
        PreparedStatement command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        command.setString(1, usuario.getUsername());
        command.setString(2, usuario.getPassword());
        command.setString(3, usuario.getNombre());
        command.setString(4, usuario.getApellido());
        command.setInt(5, usuario.getAvatar());
        command.setString(6, usuario.getGenero());

        int affectedRows =  command.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Ocurrio un Error al intentar crear el usuario.");
            //ToDo agregar el mensaje de error original y stackstrace
        }

        try (ResultSet generatedKeys = command.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("El usuario no fue creado");
            }
        }
        
        return id;     
    }
}
