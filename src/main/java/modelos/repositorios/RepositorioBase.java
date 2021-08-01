package modelos.repositorios;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  

public class RepositorioBase {
    protected Connection connection;
    
    public RepositorioBase(){
        connect();
    }
    
    public void connect() {          
        try {  
            String url = "jdbc:sqlite:redsocial.db";  
            connection = DriverManager.getConnection(url);
            System.out.println("Conectado a la base de datos.");  
              
        } catch (SQLException e) {  
            System.out.println(e.getMessage());
            
            try {  
                if (connection != null) {  
                    connection.close();  
                }  
            } catch (SQLException ex) {  
                System.out.println(ex.getMessage());  
            }  
        } 
    }  
}

    