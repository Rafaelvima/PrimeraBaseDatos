/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.AsignaturasDAO;
import java.util.List;
import model.Asignatura;

/**
 *
 * @author Rafa
 */
public class AsignaturasServicios
{

    public List<Asignatura> getAllAsignaturas()
    {
        AsignaturasDAO dao = new AsignaturasDAO();

        return dao.getAllAsignaturas();
    }

    public Asignatura addAsig(Asignatura asignaturaNuevo)
    {
        AsignaturasDAO dao = new AsignaturasDAO();

        return dao.addAsig(asignaturaNuevo);
    }

    public void delAsig(Asignatura asignaturaNuevo)
    {
        AsignaturasDAO dao = new AsignaturasDAO();

        dao.delAsig(asignaturaNuevo);
    }

    public void updateAsig(Asignatura asignaturaNuevo)
    {
        AsignaturasDAO dao = new AsignaturasDAO();

        dao.updateAsig(asignaturaNuevo);
    }

}
