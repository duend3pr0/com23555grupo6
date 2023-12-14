package data;

import static data.Conexion.*;
import java.sql.*;
import java.util.*;
import model.Book;

public class BookDAO {

    private static final String SQL_SELECT = "SELECT * FROM libros";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM libros WHERE idLibros = ?";
    private static final String SQL_INSERT = "INSERT INTO libros(nombre, autor, cantPaginas, precio, copias, imagen) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE libros SET nombre = ?, autor = ?, cantPaginas = ?, precio = ?, copias = ?, imagen = ? WHERE idLibros = ?";
    private static final String SQL_DELETE = "DELETE FROM libros WHERE idLibros = ?";

    public static List<Book> seleccionar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Book libro = null;
        List<Book> libros = new ArrayList();

        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idlibro = rs.getInt(1);
                String nombre = rs.getString("nombre");
                String autor = rs.getString("autor");
                int cantPag = rs.getInt("cantPaginas");
                double precio = rs.getDouble("precio");
                String sinopsis = rs.getString("sinopsis");
                int copias = rs.getInt("copias");

                Blob blob = rs.getBlob("imagen");
                byte[] imagenBytes = null;

                if (blob != null) {
                    imagenBytes = blob.getBytes(1, (int) blob.length());
                } else {
                    // Puedes asignar un valor predeterminado o manejarlo según tu lógica
                    imagenBytes = new byte[0]; // O cualquier otro valor predeterminado
                }

                libro = new Book(idlibro, nombre, autor, cantPag, precio, sinopsis, copias, imagenBytes);

                libros.add(libro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(conn, stmt, rs);
        }
        return libros;
    }

    public static int insertar(Book libro) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, libro.getNombre());
            stmt.setString(2, libro.getAutor());
            stmt.setInt(3, libro.getCantPaginas());
            stmt.setDouble(4, libro.getPrecio());
            stmt.setInt(5, libro.getCopias());
            stmt.setString(7, libro.getSinopsis());

            System.out.println("Sinopsis DAO" + libro.getSinopsis());
            Blob imagenBlob = conn.createBlob();
            imagenBlob.setBytes(1, libro.getImagen());
            stmt.setBlob(6, imagenBlob);

            registros = stmt.executeUpdate();
            if (registros > 0) {
                System.out.println("Inserción exitosa. Registros afectados: " + registros);
            } else {
                System.out.println("Error al insertar el libro.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(conn, stmt, rs);
        }
        return registros;
    }

    public static Book seleccionarPorId(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Book libro = null;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idlibro = rs.getInt(1);
                String nombre = rs.getString("nombre");
                String autor = rs.getString("autor");
                int cantPag = rs.getInt("cantPaginas");
                double precio = rs.getDouble("precio");
                String sinopsis = rs.getString("sinopsis");
                int copias = rs.getInt("copias");

                Blob blob = rs.getBlob("imagen");
                byte[] imagenBytes = blob.getBytes(1, (int) blob.length());

                libro = new Book(idlibro, nombre, autor, cantPag, precio, sinopsis, copias, imagenBytes);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(conn, stmt, rs);
        }
        return libro;
    }

    public static int eliminar(int id) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);

            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(conn, stmt, rs);
        }
        return registros;
    }
}
