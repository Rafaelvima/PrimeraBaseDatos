/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import servicios.UsersServicios;
import utils.Constantes;

/**
 *
 * @author Rafa
 */
@WebServlet(name = "Users", urlPatterns = {"/secure/users"})
public class Users extends HttpServlet {

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
            throws ServletException, IOException {
      String op=request.getParameter("op");
       String user=request.getParameter("user");
        String pass=request.getParameter("pass");
        User u = new User();
        UsersServicios us=new UsersServicios();
        switch(op){
            case"all":
                List<User> usuarios = us.getAllUsers();
                request.setAttribute("users", usuarios);
                 request.getRequestDispatcher("/PintarUsers.jsp").forward(request, response);
                break;
            case "insert": 
                 u.setUs_nom(user);
                 u.setUs_pass(pass);
                 us.addUser(u);
                break;
                case "update": 
                 u.setUs_nom(user);
                 u.setUs_pass(pass);
                 us.updateUser(u);
                break;
                case "delete": 
                 u.setUs_nom(user);
                 u.setUs_pass(pass);
                 us.deleteUser(u);
                break;
            
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
