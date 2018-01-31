/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.NotasDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Integer.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import model.*;
import servicios.NotasServicios;
import servicios.AlumnosServicios;
import servicios.AsignaturasServicios;

/**
 *
 * @author oscar
 */
@WebServlet(name = "Notas", urlPatterns =
{
    "/notas"
})
public class Notas extends HttpServlet
{

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
            throws ServletException, IOException, ParseException, SQLException
    {

        Nota a = new Nota();
        Alumno alu = new Alumno();
        Asignatura asi = new Asignatura();
        NotasServicios ns = new NotasServicios();
        AsignaturasServicios ass = new AsignaturasServicios();
        AlumnosServicios als = new AlumnosServicios();

        String op = request.getParameter("op");
        String id_alumno = request.getParameter("id_alumno");
        String id_asignatura = request.getParameter("id_asignatura");
        String nota = request.getParameter("nota");
        switch (op)
        {
            case "insert":
                a.setId_alumno(Long.parseLong(id_alumno));
                a.setId_asignatura(Long.parseLong(id_asignatura));
                a.setNota(Integer.parseInt(nota));
                ns.addNota(a);
                break;
            case "delete":

                a.setId_alumno(Long.parseLong(id_alumno));
                a.setId_asignatura(Long.parseLong(id_asignatura));
                ns.delNota(a);

                break;
            case "deleteAll":
                alu.setId(Long.parseLong(id_alumno));
                als.delAlumno(alu);
                asi.setId(Long.parseLong(id_asignatura));
                ass.delAsig(asi);
                break;
            case "update":
                a.setId_alumno(Long.parseLong(id_alumno));
                a.setId_asignatura(Long.parseLong(id_asignatura));
                a.setNota(Integer.parseInt(nota));
                ns.updateNota(a);
                break;
            default:
                request.setAttribute("alumnos", als.getAllAlumnos());
                request.setAttribute("asignaturas", ass.getAllAsignaturas());
                request.setAttribute("notas", ns.getAllNotas());
                request.getRequestDispatcher("pintarListaNotas.jsp").forward(request, response);

        }
        request.setAttribute("alumnos", als.getAllAlumnos());
        request.setAttribute("asignaturas", ass.getAllAsignaturas());
        request.setAttribute("notas", ns.getAllNotas());
        request.getRequestDispatcher("pintarListaNotas.jsp").forward(request, response);
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
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        } catch (ParseException ex)
        {
            Logger.getLogger(Notas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(Notas.class.getName()).log(Level.SEVERE, null, ex);
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
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        } catch (ParseException ex)
        {
            Logger.getLogger(Notas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(Notas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
