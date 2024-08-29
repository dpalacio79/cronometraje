package Controller;

import Model.Competencia;  // Only include if used
import Model.Persona;    // Only include if used
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author daniel
 */
public class GestorBD {

    private static final String CONN = "jdbc:mysql://localhost:3306/cronoDB?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "root";

    private Connection con;

    public Connection conectar() throws SQLException, ClassNotFoundException {
        if (con == null || con.isClosed()) {
            Properties props = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASS);

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(CONN, props);
        }
        return con;
    }

    public void desconectar() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    public Persona cargarPersona(Persona p) throws SQLException, ClassNotFoundException {

        try {
            conectar();

            PreparedStatement ps = con.prepareStatement("INSERT INTO Personas (nombres,apellido,documento,genero, fechaNac, ciudad, categoria, equipo)"
                    + "VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setInt(3, p.getDocumento());
            ps.setString(4, p.getGenero());
            ps.setString(5, p.getFechaNac());
            ps.setString(6, p.getCiudad());
            ps.setString(7, p.getCategoria());
            ps.setString(8, p.getEquipo());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException exc) {
        } finally {
            desconectar();

        }
        return null;

    }

    public boolean cargarCompetencia(Competencia compe) throws SQLException, ClassNotFoundException {
        try {
            conectar();

            PreparedStatement ps = con.prepareStatement("INSERT INTO Competencias (nombre, lugar, descripcion, fecha)"
                    + "VALUES (?, ?, ?, ?)");
            ps.setString(1, compe.getNombre());
            ps.setString(2, compe.getLugar());
            ps.setString(3, compe.getDescripcion());
            ps.setString(4, compe.getFecha());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 1) {
                return true; // Inserción exitosa
            } else {
                // Manejar caso de error: no se insertó ningún registro
                throw new SQLException("Error al insertar competencia");
            }
        } catch (SQLException e) {
            // Registrar la excepción o mostrar un mensaje de error
            System.err.println("Error al cargar competencia: " + e.getMessage());
            throw e; // Reenvia la excepción para que pueda ser manejada en el nivel superior
        } finally {
            desconectar();
        }
    }

    public boolean buscarUsuario(String usuario, String pass) throws SQLException, ClassNotFoundException {
        boolean encontrado = false;
        conectar();
        String query = "SELECT id FROM UsuariosWeb WHERE nombreUsuario = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, usuario);
            ps.setString(2, pass);

            try (ResultSet rs = (ResultSet) ps.executeQuery()) {
                encontrado = rs.next();
            } catch (SQLException e) {
                System.err.println("Error al ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            }
        
        return encontrado;
    }

//    public ArrayList<Competencia> BuscarCompetencias() throws SQLException {
//        ArrayList<Competencia> lista = new ArrayList<>();
//        try {
//            conectar();
//            Statement st = con.createStatement();
//            String consulta = "SELECT * FROM Competencias c";
//            ResultSet rs = (ResultSet) st.executeQuery(consulta);
//            while (rs.next()) {
//                int id = rs.getInt(1);
//                String nombre = rs.getString(2);
//                String lugar = rs.getString(3);
//                String descripcion = rs.getString(4);
//                String flyer = rs.getString(5);
//                String fecha = rs.getString(6);
//
//                Competencia tmp = new Competencia(id, nombre, lugar, descripcion, flyer, fecha);
//                lista.add(tmp);
//
//            }
//            rs.close();
//
//        } catch (ClassNotFoundException | SQLException e) {
//        } finally {
//            desconectar();
//        }
//        return lista;
//
//    }
}
