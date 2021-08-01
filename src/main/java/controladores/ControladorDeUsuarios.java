package controladores;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelos.Usuario;
import modelos.repositorios.RepositorioDeUsuarios;
import utils.Globals;
import vistas.VistaPrincipal;

public class ControladorDeUsuarios {
    RepositorioDeUsuarios repositorio;
    
    public ControladorDeUsuarios() {
        repositorio = RepositorioDeUsuarios.getInstance();
    }
    
    public Boolean Login(String username, String password) {
        try {
            Usuario usuario = repositorio.obtenerUsuarioPorUsername(username);
            
            if (usuario.Login(username, password)) {
                Globals.setUsuarioLogeado(usuario);
                
                new VistaPrincipal();
                
                return true;
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "Los datos son incorrectos.\nPor favor intenta de nuevo\n\n" +
                    "Si eres un usuario nuevo por favor registrate.",
                    "Datos Incorrectos",
                    1
                );
                
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorDeUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public void Registrar(
            String nombre, 
            String apellido, 
            String username, 
            String password,
            String genero) {
        
        Usuario nuevoUsuario = new Usuario();
        
        try {
            nuevoUsuario.Crear(nombre, apellido, username, password, genero);            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Ocurrio un Error",1);
        }
        
    } 
}
