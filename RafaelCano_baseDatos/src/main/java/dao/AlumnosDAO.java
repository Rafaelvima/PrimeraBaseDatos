/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Alumno;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Rafa
 */
public class AlumnosDAO
{
//Select JDBC
     public List<Alumno> getAllAlumnosJDBCTemplate() {
     
        JdbcTemplate jtm = new JdbcTemplate(
          DBConnection.getInstance().getDataSource());
        List<Alumno> alumnos = jtm.query("Select * from ALUMNOS",
          new BeanPropertyRowMapper(Alumno.class));
        
        
        return alumnos;
    }

    public Alumno insertAlumnoJDBC(Alumno a)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        try
        {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO ALUMNOS (NOMBRE,FECHA_NACIMIENTO,"
                    + "MAYOR_EDAD) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, a.getNombre());
            stmt.setDate(2, new java.sql.Date(a.getFecha_nacimiento().getTime()));
            stmt.setBoolean(3, a.getMayor_edad());

            int filas = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next())
            {
                a.setId(rs.getInt(1));
            }

        } catch (Exception ex)
        {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            db.cerrarConexion(con);
        }

        return a;
    }

    public void delUser(Alumno u)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        try
        {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

            int filas = qr.update(con,
                    "DELETE FROM ALUMNOS WHERE ID=?",
                    u.getId());

        } catch (Exception ex)
        {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            db.cerrarConexion(con);
        }
    }

    public void updateUser(Alumno u)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        try
        {
            con = db.getConnection();
            //QueryRunner qr = new QueryRunner();

            /* int filas = qr.update(con,
                    "UPDATE ALUMNOS SET NOMBRE=?,FECHA_NACIMIENTO=?"
                    + ", MAYOR_EDAD=? WHERE ID=?",
                    u.getNombre(), "");*/
            PreparedStatement stmt = con.prepareStatement("UPDATE ALUMNOS SET NOMBRE=?,FECHA_NACIMIENTO=?"
                    + ", MAYOR_EDAD=? WHERE ID=?");
            stmt.setString(1, u.getNombre());
            stmt.setDate(2, new java.sql.Date(u.getFecha_nacimiento().getTime()));
            stmt.setBoolean(3, u.getMayor_edad());
            stmt.setLong(4, u.getId());
            stmt.executeUpdate();
        } catch (Exception ex)
        {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            db.cerrarConexion(con);
        }
    }

}
