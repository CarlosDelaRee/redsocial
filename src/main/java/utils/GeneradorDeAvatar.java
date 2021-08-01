package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class GeneradorDeAvatar {
    
    static ArrayList<String> rutasMujeres;
    static ArrayList<String> rutasHombres;
    
    public static BufferedImage obtener(int indice, String genero) throws IOException {
        
        if (rutasMujeres == null) {
            rutasMujeres = new ArrayList<>();
            rutasHombres = new ArrayList<>();
            llenarRutas();
        }
        
        if (genero.equals("Mujer")) {
            return ImageIO.read(new File(rutasMujeres.get(indice)));
        } else {
            return ImageIO.read(new File(rutasHombres.get(indice)));
        }
    
    }
    
    private static void llenarRutas() {
        String basePath = Paths.get("").toAbsolutePath().toString() + "/resources/avatar/";
        
        rutasMujeres.add(basePath + "Mujer/0.png");
        rutasMujeres.add(basePath + "Mujer/1.png");
        
        rutasHombres.add(basePath + "Hombre/0.jpg");
        rutasHombres.add(basePath + "Hombre/1.png");
        rutasHombres.add(basePath + "Hombre/2.png");
        rutasHombres.add(basePath + "Hombre/3.png");
        
    }
}
