package postfix.interpreter;
import java.io.IOException;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        Calculadora calcu = new Calculadora();
        ArrayList<String[]> tokens = new ArrayList<>();
        // Leer el archivo de entrada
        try{
            tokens = calcu.readFile("datos.txt");
        }catch(IOException e){
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }
        // Verificar si el archivo estaba vacío
        if (tokens.size() == 0) {
            System.out.println("No hay ninguna operación en el archivo.");
            return;
        }
        // Evaluar la expresión 
        int[] resultados = new int[tokens.size()];
        for (int i = 0; i < tokens.size(); i++) {
            resultados[i] = calcu.interpret(tokens.get(i));
            System.out.println("El resultado de la operación en la línea #" + (i + 1) + " es: " + resultados[i]);
        }
        
    }
}
