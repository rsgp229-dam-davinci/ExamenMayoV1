package dam.davinci;

import java.io.FileWriter;
import java.io.IOException;

public class LibroImpreso extends Libro implements Guardable{

    private int numeroPaginas;

    public LibroImpreso(String title, String author, int numeroPaginas) {
        this.title = title;
        this.author = author;
        this.numeroPaginas = numeroPaginas;
    }

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
