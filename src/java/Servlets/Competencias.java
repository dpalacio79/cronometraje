package Servlets;

import Controller.GestorDB;
import Model.Competencia;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
@WebServlet(name = "Competencias", urlPatterns = {"/Competencias"})
public class Competencias extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Competencias</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Competencias at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String lugar = request.getParameter("lugar");
        String descrip = request.getParameter("descripcion");
        String fecha = request.getParameter("fecha");

        GestorDB gestor = new GestorDB();
        Competencia comp = new Competencia(nombre, lugar, descrip, "", fecha);
        boolean respuesta=true;
        try {
            respuesta = gestor.cargarCompetencia(comp);
        } catch (SQLException ex) {
            Logger.getLogger(Competencias.class.getName()).log(Level.SEVERE, null, ex);
        }
// request.setAttribute("listaVencidos", lista); para enviar cosas desde el servlet
//
        
        
        if (respuesta) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/altaCompetencia.jsp");
            rd.forward(request, response);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
        rd.forward(request, response);
    }

}
