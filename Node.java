/**
 * Clase nodo que representará cada una de las casillas de la pila.
 */

public class Node<T> {
    public T value;
    public Node<T> next = null;
    public Node<T> previous;

    public Node(T value) {
        this.value = value;
    }


    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrevious() {
        return this.previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }
}
