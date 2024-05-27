package dam.davinci;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Implementación concreta de un libro en formato digital
 */
public class LibroDigital extends Libro implements Guardable{

    private int tamanioArchivo;

    public LibroDigital (String title, String author, int tamanioArchivo){
        this.title = title;
        this.author = author;
        this.tamanioArchivo = tamanioArchivo;
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
     * <p>Muestra la información concreta del libro digital en una única línea
     * de texto según lo indicado en la clase abstracta Libro</p>
     * @return La información del libro en una única línea de texto
     */
    @Override
    public String mostrarInformacion() {
        StringBuilder sb = new StringBuilder();
        sb.append("Titulo: " + this.title);
        sb.append(", Autor: " + this.author);
        sb.append(", Tamaño archivo (MB): " + this.tamanioArchivo);
        return sb.toString();
    }

    public int getTamanioArchivo() {
        return tamanioArchivo;
    }

    public LibroDigital setTamanioArchivo(int tamanioArchivo) {
        this.tamanioArchivo = tamanioArchivo;
        return this;
    }
}
