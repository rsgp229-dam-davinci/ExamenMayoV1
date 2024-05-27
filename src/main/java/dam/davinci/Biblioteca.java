package dam.davinci;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * <p>Representa el comportamiento básico de una biblioteca.</p>
 * <p>Permite guardar y eliminar libros, consultar todos los libros así
 * como buscar por título y por autor.</p>
 * <p>También permite guardar un libro en una base de datos.</p>
 */
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

    /**
     * <p>Busca un libro por título ignorando mayúsculas/minúsculas</p>
     * @param titulo Título del libro a buscar
     * @return El libro solicitado o null en caso negativo
     */
    public Libro searchByTitle(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitle().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    /**
     * <p>Busca libros por autor ignorando mayúsculas/minúsculas</p>
     * @param author Nombre del autor a buscar
     * @return Lista con las coincidencias encontradas
     */
    public List<Libro> searchByAuthor(String author) {
        ArrayList<Libro> output = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getAuthor().equalsIgnoreCase(author)) {
                output.add(libro);
            }
        }
        return output;
    }

    /* Método privado que establece la conexión con la base de datos.
    * Lanza las excepciones con las que se encuentre. */
    private Connection getConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/database.properties"));
        return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
    }

    /**
     * Método que guarda en base de datos un libro
     * @param libro El libro a guardar
     * @return Indica si se ha podido guardar o no el libro en la base de datos
     */
    public boolean guardarEnBaseDeDatos(Libro libro) {
        if (libro == null) return false;
        String statement = null;
        PreparedStatement ps = null;
        if (libro instanceof LibroDigital){
            statement = "INSERT INTO libros (author, title, mb_size) values (?,?,?)";
        } else if (libro instanceof LibroImpreso){
            statement = "INSERT INTO libros (author, title, pages) values (?,?,?)";
        } else {
            return false;
        }

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setString(1,libro.getAuthor());
            preparedStatement.setString(2, libro.getTitle());
            if (libro instanceof LibroDigital){
                preparedStatement.setInt(3, ((LibroDigital) libro).getTamanioArchivo());
            } else {
                preparedStatement.setInt(3,((LibroImpreso) libro).getNumeroPaginas());
            }
            preparedStatement.execute();
            return true;
        } catch (IOException e) {
            System.err.println("Ha ocurrido algún problema con el archivo de configuración");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Ha ocurrido algún problema con la inserción en la base de datos");
            e.printStackTrace();
        }
        return false;
    }
}
