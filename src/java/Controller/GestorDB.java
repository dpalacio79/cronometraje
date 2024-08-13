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

    public Connection getConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            Properties props = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASS);

            con = DriverManager.getConnection(CONN, props);
        }
        return con;
    }

    public void closeConnection() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    public Persona buscarDocumento(String docum, int tipoDNI) throws SQLException {
        Persona persona = null;
        try (
                Connection con = getConnection(); 
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
}
