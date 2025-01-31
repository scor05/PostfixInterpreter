package postfix.interpreter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class CalculadoraTest {
    /**
     * Caso 1: Quedan números en la pila al final de la evaluación.
     * Esto sucede cuando hay más operandos que operadores, lo que indica una
     * expresión mal formada.
     */

    @Test
    void testQuedanNumerosEnLaPila() {
        Calculadora calculadora = new Calculadora();
        String[] input = {"5", "3", "+" , "2"}; // Expresión mal formada
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            calculadora.interpret(input);
        });
        assertEquals("Error: Expresión mal formada, quedan elementos en la pila.", exception.getMessage());
    }

    /**
     * Caso 2: Se ingresa una letra en la entrada.
     * Como las expresiones postfix solo aceptan números y operadores,
     * una letra debe provocar una excepción indicando un caracter inválido.
     */
    @Test
    void testUnaLetra() {
        Calculadora calculadora = new Calculadora();
        String[] input = {"5", "A", "+"}; // A no es un número válido
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.interpret(input);
        });
        assertTrue(exception.getMessage().contains("Error: Caracter no válido detectado"));
    }

     /**
     * Caso 3: Se ingresa un símbolo especial en la entrada.
     * Cualquier carácter que no sea un número o un operador válido (+, -, *, /, %)
     * debe generar una excepción.
     */
    @Test
    void testSimboloEnLaPila() {
        Calculadora calculadora = new Calculadora();
        String[] input = {"5", "&", "+"}; // & es un símbolo inválido
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.interpret(input);
        });
        assertTrue(exception.getMessage().contains("Error: Caracter no válido detectado"));
    }

     /**
     * Caso 4: División por cero.
     * Si el segundo operando es 0 y el operador es '/', se debe lanzar
     * una excepción de tipo ArithmeticException.
     */
    @Test
    void testDivisionPorCero() {
        Calculadora calculadora = new Calculadora();
        String[] input = {"10", "0", "/"}; // División entre 0
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculadora.interpret(input);
        });
        assertEquals("Error: División o módulo entre cero.", exception.getMessage());
    }

    /**
     * Caso 5: Módulo entre cero.
     * Similar a la división por cero, si el segundo operando es 0 y el operador es '%',
     * se debe lanzar una excepción de tipo ArithmeticException.
     */
    @Test
    void testModuloEntreCero() {
        Calculadora calculadora = new Calculadora();
        String[] input = {"10", "0", "%"}; // Módulo entre 0
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculadora.interpret(input);
        });
        assertEquals("Error: División o módulo entre cero.", exception.getMessage());
    }

    /**
     * Caso 6: Operación válida.
     * Se verifica que una operación bien formada como "5 3 +" se evalúe correctamente
     * y devuelva el resultado esperado (8).
     */
    @Test
    void testOperacionValida() {
        Calculadora calculadora = new Calculadora();
        String[] input = {"5", "3", "+"}; // Operación válida 5 + 3 = 8
        int resultado = calculadora.interpret(input);
        assertEquals(8, resultado);
    }
}
