/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Configuration;
import java.time.LocalDateTime;
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
}
