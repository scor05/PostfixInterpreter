package postfix.interpreter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Calculadora {
    
    private NodeStack<String> stack;

    public Calculadora() {
        this.stack = new NodeStack<>();
    }

    public int interpret(String[] token) {
        for (int i = 0; i < token.length; i++){
            token[i] = token[i].trim(); // Quitar espacios innecesarios.

            if (token[i].matches("-?\\d+")) { // Verifica si es un número válido
                this.stack.push(token[i]);
            } else if (token[i].matches("[+\\-*/%]")) { // Es un operador
                if (this.stack.size() < 2) { // No hay suficientes operandos
                    throw new IllegalArgumentException("Error: Operador '" + token[i] + "' sin suficientes operandos.");
                }
                
                String B = this.stack.pop();
                String A = this.stack.pop();
                
                if ((token[i].equals("/") || token[i].equals("%")) && B.equals("0")) {
                    throw new ArithmeticException("Error: División o módulo entre cero.");
                }

                String result = this.stack.operation(token[i].charAt(0), A, B);
                this.stack.push(String.valueOf(result));

            } else { // Caso donde haya una letra o símbolo inválido
                throw new IllegalArgumentException("Error: Caracter no válido detectado -> '" + token[i] + "'");
            }
        }

        if (this.stack.size() != 1) { // Si la pila no tiene exactamente un resultado final
            throw new IllegalStateException("Error: Expresión mal formada, quedan elementos en la pila.");
        }

        return Integer.parseInt(this.stack.pop());
    }

    public ArrayList<String[]> readFile(String fileName) throws IOException {
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
