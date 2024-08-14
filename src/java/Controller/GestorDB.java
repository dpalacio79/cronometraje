/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

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

    private static final String CONN = "jdbc:mysql://localhost:3306/cronoDB";
    private static final String USER = "root";
    private static final String PASS = "root"; // Replace with a secure way to store credentials

    private Connection con;

    public Connection conectar() throws SQLException {
        if (con == null || con.isClosed()) {
            Properties props = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASS);

            con = DriverManager.getConnection(CONN, props);
        }
        return con;
    }

    public void desconectar() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    public Persona buscarDocumento(String docum, int tipoDNI) throws SQLException {
        Persona persona = null;
        try (
                Connection con = conectar(); 
                PreparedStatement ps = con.prepareStatement("SELECT p.idPersona, p.nombres, p.apellido, p.calle, p.numeroCalle FROM Personas p WHERE DNI=? AND tipoDNI=?")) {
            ps.setString(1, docum);
            ps.setInt(2, tipoDNI);
            try (ResultSet rs = (ResultSet) ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    String nombreP = rs.getString(2);
                    String apellidoP = rs.getString(3);
                    String calle = rs.getString(4);
                    String nro = rs.getString(5);
                    persona = new Persona();
                }
            }
        }
        return persona;
    }
    
    public Persona cargarPersona(Persona p) throws SQLException{
    
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
    
}
