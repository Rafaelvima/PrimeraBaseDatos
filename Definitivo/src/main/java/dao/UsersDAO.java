/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Configuration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Yo
 */
public class UsersDAO {

  
    
    public User getPassByNombre(String nombre) {
        User u;
        try {
            JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            u =(User) jtm.queryForObject("select * from users where us_nom=?", new Object[]{nombre}, new BeanPropertyRowMapper(User.class));
        } catch (Exception ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            u = null;
        }
        return u;
    }

    public List<User> getAllUsers() {
        List<User> lu=null;
        try {
            JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            lu =jtm.query("select * from users",  new BeanPropertyRowMapper(User.class));
        } catch (Exception ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            lu = null;
        }
        return lu;
    }

    public int addUser(User u) {
         int a;
        try {
            JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            a = jtm.update("insert users (us_nom,us_pass) values(?,?)",u.getUs_nom(),u.getUs_pass() );
        } catch (Exception ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            a = 0;
        }
        return a;
    }
    public int deleteUser(User u) {
         int a;
        try {
            JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            a = jtm.update("delete from users where us_nom=?",u.getUs_nom());
        } catch (Exception ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            a = 0;
        }
        return a;
    }
    public int updateUser(User u) {
         int a;
        try {
            JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            a = jtm.update("update users set us_pass=? where us_nom=?",u.getUs_pass(),u.getUs_nom());
        } catch (Exception ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            a = 0;
        }
        return a;
    }
}
