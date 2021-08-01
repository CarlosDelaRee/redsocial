package utils;

import java.util.ArrayList;
import java.util.Collections;
import modelos.Usuario;

public class Globals {
    static Usuario usuarioLogeado;
    static ArrayList<String> temas;
    
    public static void setUsuarioLogeado(Usuario value) {
        if (value != null) {
            usuarioLogeado = value;
        }
    }
    
    public static Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }
    
    //Por ahora los temas estan hardcodeados y no es posible agregar mas dinamicamente.
    public static ArrayList<String> obtenerTopicos() {
        if (temas == null) {
            temas = new ArrayList<>();
            
            temas.add("Tecnología");
            temas.add("Programación");
            temas.add("Computación");
            temas.add("Entretenimiento");
            temas.add("Ciencia");
            temas.add("Cine");
            temas.add("Televisión");
            temas.add("Teatro");
            temas.add("Literatura");
            temas.add("Big Data");
            temas.add("Ciencia de Datos");
            temas.add("IoT");
            temas.add("Deportes");
            temas.add("Noticias");
            temas.add("Música");
            temas.add("Amor");
            temas.add("Autos");
            temas.add("No Categorizado");

            Collections.sort(temas); 
        } 
        
        return temas;
    }
    
}
