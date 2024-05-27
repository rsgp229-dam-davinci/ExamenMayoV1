package dam.davinci;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Implementación de un libro en formato papel
 */
public class LibroImpreso extends Libro implements Guardable{

    private int numeroPaginas;

    public LibroImpreso(String title, String author, int numeroPaginas) {
        this.title = title;
        this.author = author;
        this.numeroPaginas = numeroPaginas;
    }

    /**
     * <p>Guarda el libro en un archivo de texto plano con extensión '.txt' en el
     * directorio raiz del ejecutable. La información que guarda en el archivo
     * es la que se obtenga del método 'mostrarInformacion()'</p>
     * <p>En caso de error se aborta la operación. El método controla la
     * excepción e informa por consola de la causa del error</p>
     *
     * @see Guardable
     * @see #mostrarInformacion()
     */
    @Override
    public void guardar() {
        String pathFile = getTitle()+".txt";
        try (FileWriter fw = new FileWriter(pathFile)){
            fw.write(mostrarInformacion());
        } catch (IOException e) {
            System.err.println("Ha ocurrido un error al guardar el archivo");
            System.err.println(e.getMessage());
        }
    }

    /**
     * <p>Muestra la información concreta del libro impreso en una única línea
     * de texto según lo indicado en la clase abstracta Libro</p>
     * @return La información del libro en una única línea de texto
     */
    @Override
    public String mostrarInformacion() {
        StringBuilder sb = new StringBuilder();
        sb.append("Título: " + this.title);
        sb.append(", Autor: " + this.author);
        sb.append(", Número de páginas: " + this.numeroPaginas);
        return sb.toString();
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public LibroImpreso setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
        return this;
    }
}
