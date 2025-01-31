package postfix.interpreter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class CalculadoraTest {

    @Test
    void testQuedanNumerosEnLaPila() {
        Calculadora calculadora = new Calculadora();
        String[] input = {"5", "3", "+" , "2"}; // Expresión mal formada
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            calculadora.interpret(input);
        });
        assertEquals("Error: Expresión mal formada, quedan elementos en la pila.", exception.getMessage());
    }