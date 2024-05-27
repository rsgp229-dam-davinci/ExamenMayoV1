package dam.davinci;

/**
 * Clase abstracta que representa un Libro
 *
 * @author Rafael
 * @version 0.1
 */
public abstract class Libro {
    protected String title;
    protected String author;

    /**
     * Debe mostrar la información del libro en una única línea de texto
     * @return
     */
    public abstract String mostrarInformacion();

    public String getTitle() {
        return title;
    }

    public Libro setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Libro setAuthor(String author) {
        this.author = author;
        return this;
    }
}
