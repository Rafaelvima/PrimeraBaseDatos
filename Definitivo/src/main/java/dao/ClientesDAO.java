/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author rafa
 */
public class ClientesDAO {

    public List<Cliente> getAllClientesDBUils() {
        List<Cliente> ce = new ArrayList<>();
          try {
              JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
        ce = jtm.query("Select * from clientes",new BeanPropertyRowMapper(Cliente.class));
            
        } catch (Exception ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ce;
    }

    public Cliente existeCliente(String dni) {
        Cliente ce =null;
          try {
              JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
         ce =  (Cliente) jtm.queryForObject("select * from clientes where cl_dni=? ",new Object[]{dni},new BeanPropertyRowMapper(Cliente.class));
            
        } catch (Exception ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return ce;
    }

    public boolean addCliente(Cliente c) {
       int filas;
        try {
            String sql = "insert into clientes (cl_dni,cl_nom,cl_tel) VALUES(?,?,?)";
            JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            filas = jtm.update(sql, c.getCl_dni(),c.getCl_nom(),c.getCl_tel());
        } catch (DataAccessException e) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        if(filas>0){
            return true;
        }else
        return false;
    }
     public boolean updateCliente(Cliente c) {
       int filas;
        try {
            String sql = "update clientes set cl_ncu =? and cl_sal=? where cl_dni=?";
            JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            filas = jtm.update(sql, c.getCl_ncu(),c.getCl_sal(),c.getCl_dni());
        } catch (DataAccessException e) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        if(filas>0){
            return true;
        }else
        return false;
    }

    public boolean updatemasCliente(Cliente c, int sal) {
         int filas;
        try {
            String sql = "update clientes set cl_ncu=? and cl_sal=? ";
            JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            filas = jtm.update(sql, c.getCl_ncu()+1, c.getCl_sal()+sal);
        } catch (DataAccessException e) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        if(filas>0){
            return true;
        }else
        return false;
    }
}
