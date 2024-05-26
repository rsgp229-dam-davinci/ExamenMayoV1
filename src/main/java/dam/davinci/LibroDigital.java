package dam.davinci;

import java.io.FileWriter;
import java.io.IOException;

public class LibroDigital extends Libro implements Guardable{

    private int tamanioArchivo;

    public LibroDigital (String title, String author, int tamanioArchivo){
        this.title = title;
        this.author = author;
        this.tamanioArchivo = tamanioArchivo;
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
