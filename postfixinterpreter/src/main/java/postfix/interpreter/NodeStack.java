package postfix.interpreter;
public class NodeStack<T> implements IStack<T> {
    private Node<T> first;
    private Node<T> last;
    private int size; // Contador de elementos en la pila

    /**
     * Constructor de la pila.
     */

    public NodeStack() {
        this.first = new Node<T>(null);
        this.last = null;
        this.size = 0;
    }
        /**
     * Retorna la cantidad de elementos en la pila.
     */

    public int size() {
        return this.size;
    }

    public void push(T value) {
        Node<T> newNode = new Node<T>(value);
        if (this.last == null) {
            this.setFirst(newNode);
            this.setLast(newNode);
        } else {
            Node<T> aux = this.getLast();
            aux.setNext(newNode);
            newNode.setPrevious(aux);
            this.setLast(newNode);
        }
        this.size++;
    }

    public T pop() {
        if (this.last == null) { 
            throw new RuntimeException("Error: La pila ya está vacía."); 
        }
    
        T value = this.last.getValue();
        this.last = this.last.getPrevious();
    
        if (this.last != null) { 
            this.last.setNext(null);
        } else { 
            this.first = new Node<T>(null);
            this.last = this.first;
        }
        this.size--;
        return value;
    }

        /**
     * Realiza una operación matemática entre dos valores extraídos de la pila.
     * 
     * @param operator Operador aritmético a aplicar (+, -, *, /, %).
     * @param t1 Primer operando.
     * @param t2 Segundo operando.
     * @return Resultado de la operación como tipo T.
     * @throws ArithmeticException Si se intenta dividir o calcular el módulo entre cero.
     * @throws IllegalArgumentException Si el operador no es válido.
     */

    @SuppressWarnings("unchecked")
    public T operation(char operator, T t1, T t2){
        int value1 = Integer.parseInt(t1.toString());
        int value2 = Integer.parseInt(t2.toString());
        int result = 0;
        switch (operator) {
            case '+':
                result = value1 + value2;
                break;
            case '-':
                result = value1 - value2;
                break;
            case '*':
                result = value1 * value2;
                break;
            case '/':
                if (value2 == 0) {
                    throw new ArithmeticException("Error: División entre cero.");
                }
                result = value1 / value2;
                break;
            case '%':
                if (value2 == 0) {
                    throw new ArithmeticException("Error: Módulo entre cero.");
                }
                result = value1 % value2;
                break;
            default:
                throw new IllegalArgumentException("Error: Operador ingresado inválido -> " + operator);
        }
        return (T) String.valueOf(result);
    }
}
