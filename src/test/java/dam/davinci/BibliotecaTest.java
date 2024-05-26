package dam.davinci;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecaTest {

    @org.junit.jupiter.api.Test
    void guardarEnBaseDeDatos() {
        Biblioteca b = new Biblioteca();
        Libro l = new LibroImpreso("Los tres mosqueteros", "Alejandro Dumas", 267);
        assertDoesNotThrow(() -> b.guardarEnBaseDeDatos(l));
    }
}