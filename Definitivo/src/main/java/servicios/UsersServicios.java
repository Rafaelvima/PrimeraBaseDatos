/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.UsersDAO;
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

   

}
