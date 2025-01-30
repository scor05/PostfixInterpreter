package postfix.interpreter;
/*
 * Clase principal del Stack realizada con nodos, esta es la que se impmenenta en la clase main.
 */

public class NodeStack<T> implements IStack<T> {
    private Node<T> first;
    private Node<T> last;

    public NodeStack() {
        this.first = new Node<T>(null);
        this.last = null;
    }

    public Node<T> getFirst() {
        return this.first;
    }

    public void setFirst(Node<T> first) {
        this.first = first;
    }

    public Node<T> getLast() {
        return this.last;
    }

    public void setLast(Node<T> last) {
        this.last = last;
    }

    public void push(T value) {
        Node<T> newNode = new Node<T>(value);
        Node<T> aux = null;
        aux = this.last;
        this.setLast(newNode);
        newNode.setPrevious(aux);
    }

    public T pop() {
        T value = this.last.getValue();
        this.last = this.last.getPrevious();
        this.last.setNext(null);
        return value;
    }

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
                    throw new ArithmeticException("División entre cero.");
                }
                result = value1 / value2;
                break;
            case '%':
                result = value1 % value2;
                break;
            default:
                throw new IllegalArgumentException("Operador ingresado inválido: " + operator);
        }
        return (T) Integer.valueOf(result);
    }
}