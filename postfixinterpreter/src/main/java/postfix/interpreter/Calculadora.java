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
            if (!token[i].equals("+") && !token[i].equals("-") && !token[i].equals("*") && !token[i].equals("/") && !token[i].equals("%")) {
                this.stack.push(token[i]);
            } else {
                String operator = token[i];
                String B = this.stack.pop();
                String A = this.stack.pop();
                String result = this.stack.operation(operator.charAt(0), A, B);
                this.stack.push(String.valueOf(result));
            }
        }
        // TODO: Implementar programación defensiva: casos de división entre cero, módulos entre cero, operaciones inválidas, etc.
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
