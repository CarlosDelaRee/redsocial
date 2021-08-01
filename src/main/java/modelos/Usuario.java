package modelos;

import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.repositorios.RepositorioDeUsuarios;

public class Usuario {
    private int id;
    private String nombre;
    private String apellidos;
    private String username;
    private String password;
    private String genero;
    private int avatar;
    
    private RepositorioDeUsuarios repositorio;
    
    public Usuario() {
        repositorio = RepositorioDeUsuarios.getInstance();
        
        this.nombre = "";
        this.apellidos = "";
        this.username = "";
        this.password = "";
        this.genero = "";
        this.avatar = 0;
        this.id = 0;
    }
    
    public void Cargar(
            int id,
            String nombre,
            String apellidos,
            String username,
            String password,
            String genero,
            int avatar) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.genero = genero;
        this.avatar = avatar;        
    }
    
    public void Crear(
            String nombre, 
            String apellido, 
            String username, 
            String password, 
            String genero) {
        
        setNombre(nombre);
        setApellido(apellido);
        setUsername(username);
        setPassword(password);        
        this.genero = genero;
        
        Random rand = new Random();
        
        //tenemos 2 avatares diferentes para mujer
        if (genero.equals("Mujer")) {
            int upperbound = 2;
            this.avatar = rand.nextInt(upperbound); 
        }
        
        //y 4 avatares para hombre
        if (genero.equals("Hombre")) {
            int upperbound = 4;
            this.avatar = rand.nextInt(upperbound); 
        }
        
        try {
            id = repositorio.Crear(this);
        } catch (SQLException ex) {
            Logger.getLogger(Publicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Boolean Login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }
    
    //Setters
    
    private void setNombre(String value) {
        if (!value.isBlank()) {
            nombre = value;
        } else {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }            
    }
    
    private void setApellido(String value) {
        if (!value.isBlank()) {
            apellidos = value;
        } else {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }            
    }
    
    private void setUsername(String value) {
        if (!value.isBlank()) {
            username = value;
        } else {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }            
    }
    
    private void setPassword(String value) {        
        if (value.isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        
        if (value.length() < 5) {
            throw new IllegalArgumentException("La contraseña debe contener al menos 4 caracteres");
        }
        
        password = value;
    }
    
    //Getters
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
        return apellidos;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public int getAvatar() {
        return avatar;
    }
}
