package dam.davinci;

public abstract class Libro {
    protected String title;
    protected String author;

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
