package Controller;

import Model.Competencia;
import Model.Persona;
import jakarta.resource.cci.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author daniel
 */
public class GestorDB {

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

            PreparedStatement ps = con.prepareStatement("INSERT INTO Competencias (nombre,lugar,descripcion, fecha)"
                    + "VALUES (?,?,?,?)");
            ps.setString(1, compe.getNombre());
            ps.setString(2, compe.getLugar());
            ps.setString(3, compe.getDescripcion());
            ps.setString(4, compe.getFecha());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException exc) {
            return false;
        } finally {
            desconectar();

        }

        return true;
    }

}
