/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.NotasDAO;
import java.sql.SQLException;
import java.util.List;
import model.Nota;

/**
 *
 * @author Rafa
 */
public class NotasServicios
{

    public List<Nota> getAllNotas()
    {
        NotasDAO dao = new NotasDAO();

        return dao.getAllNotas();
    }

    public Nota addNota(Nota notaNuevo) throws SQLException
    {
        NotasDAO dao = new NotasDAO();

        return dao.addNota(notaNuevo);
    }

    public void delNota(Nota notaNuevo)
    {
        NotasDAO dao = new NotasDAO();

        dao.delNota(notaNuevo);
    }

    public void updateNota(Nota notaNuevo)
    {
        NotasDAO dao = new NotasDAO();

        dao.updateNota(notaNuevo);
    }
}
