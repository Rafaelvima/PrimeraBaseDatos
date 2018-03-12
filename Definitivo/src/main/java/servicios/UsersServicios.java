/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.UsersDAO;
import java.util.List;
import model.User;


/**
 *
 * @author yo
 */
public class UsersServicios {

  

   public User getPassByNombre(String nombre) {
            UsersDAO dao = new UsersDAO();
           return dao.getPassByNombre(nombre);
      
    }

    public List<User> getAllUsers() {
        UsersDAO dao =new UsersDAO();
        return dao.getAllUsers();
        
    }
    public int addUser(User u){
        UsersDAO dao = new UsersDAO();
        return dao.addUser(u);
    }
     public int updateUser(User u){
        UsersDAO dao = new UsersDAO();
        return dao.updateUser(u);
    }
      public int deleteUser(User u){
        UsersDAO dao = new UsersDAO();
        return dao.deleteUser(u);
    }

   

}
