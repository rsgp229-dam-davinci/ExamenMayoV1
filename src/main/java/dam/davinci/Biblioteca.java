package dam.davinci;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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

    private Connection getConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        FileReader reader = new FileReader("database.properties");
        properties.load(reader);
        return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
    }

    public boolean guardarEnBaseDeDatos(Libro libro) {
        String statement = "INSERT INTO persons (author, title) values (?,?)";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setString(1,libro.getAuthor());
            preparedStatement.setString(2, libro.getTitle());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            System.err.println("Ha ocurrido algún problema durante la inserción en la base de datos");
            System.err.println(e.getMessage());
        }
        return false;
    }
}
