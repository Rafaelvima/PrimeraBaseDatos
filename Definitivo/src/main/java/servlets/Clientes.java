/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import servicios.ClientesServicios;

/**
 *
 * @author rafa
 */
@WebServlet(name = "Clientes", urlPatterns = {"/clientes"})
public class Clientes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        Clientes m = new Clientes();
        LocalDate local = LocalDate.of(1910, Month.MARCH, 12);
        ClientesServicios cls = new ClientesServicios();
        String op = request.getParameter("op");
        String dni1 = request.getParameter("dni1");
        String dni2 = request.getParameter("dni2");
        String num_cuenta = request.getParameter("num_cuenta");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        List<Cliente> clientes =cls.getAllClientes();

        
        switch (op) {
            case "all":
               
                request.setAttribute("clientes", clientes);
                request.getRequestDispatcher("PintarClientes.jsp").forward(request, response);
                break;
            case "iniciarS":
                
                break;
            case "insert":

                break;

            case "delete":

                break;
            case "update":

                break;
            default:
                //request.setAttribute("alumnos", ms.getAllMovimientos());
                request.getRequestDispatcher("pintarListaAlumnos.jsp").forward(request, response);

        }
        request.setAttribute("clientes", clientes);
                request.getRequestDispatcher("PintarClientes.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
