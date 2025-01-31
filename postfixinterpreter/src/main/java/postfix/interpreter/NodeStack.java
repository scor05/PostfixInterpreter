package postfix.interpreter;
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

        if (this.last == null) {
            this.setFirst(newNode);
            this.setLast(newNode);
        } else {
            Node<T> aux = this.getLast();
            aux.setNext(newNode);
            newNode.setPrevious(aux);
            this.setLast(newNode);
        }
    }

    public T pop() {
        if (this.last == null) { 
            throw new RuntimeException("La pila ya está vacía."); 
        }
    
        T value = this.last.getValue();
        this.last = this.last.getPrevious();
    
        if (this.last != null) { 
            this.last.setNext(null);
        } else { 
            this.first = new Node<T>(null);
            this.last = this.first;
        }
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
        return (T) String.valueOf(result);
    }
}