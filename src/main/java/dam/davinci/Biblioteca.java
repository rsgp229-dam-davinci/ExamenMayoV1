package dam.davinci;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Libro> libros;

    public Biblioteca() {
        libros = new ArrayList<Libro>();
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void addLibro(Libro libro) {
        libros.add(libro);
    }

    public void removeLibro(Libro libro) {
        libros.remove(libro);
    }

    public Libro searchByTitle(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitle().equals(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public Libro searchByAuthor(String author) {
        for (Libro libro : libros) {
            if (libro.getAuthor().equals(author)) {
                return libro;
            }
        }
        return null;
    }
}
