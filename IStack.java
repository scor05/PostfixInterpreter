/**
 * Interfaz realizada en conjunto con todos los grupos para el Stack.
 */

public interface IStack {
    void push(int value);
    int pop();
    int operation(char operator, int value1, int value2);
}
