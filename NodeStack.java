/*
 * Clase principal del Stack realizada con nodos, esta es la que se impmenenta en la clase main.
 */

public class NodeStack<T> implements IStack {
    // TODO: Implementar los métodos de pop, push y operation.
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

}