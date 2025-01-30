package postfix.interpreter;

/**
 * Clase principal que ejecutará el código del interprete.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;


public class Driver {
    public static void main(String[] args) {
        ArrayList<String[]> tokens = new ArrayList<>();
        NodeStack<String> stack = new NodeStack<>();
        try {
            tokens = readFile("datos.txt");
        } catch (IOException IOE) {
            System.out.println("Error al leer el archivo: " + IOE.getMessage());
            return;
        }
        if(tokens.size() == 0) {
            System.out.println("El archivo está vacío.");
            return;
        }
    }

    public static ArrayList<String[]> readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.FileReader(fileName));
        String line;
        ArrayList<String[]> tokens = new ArrayList<>(); // Es una matriz de String[] con todas las líneas del archivo.
        while((line = br.readLine()) != null) {
            String[] lines = line.split(" ");
            tokens.add(lines);
        }
        br.close();
        return tokens;
    }
}
