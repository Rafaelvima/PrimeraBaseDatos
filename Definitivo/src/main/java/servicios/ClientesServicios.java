/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.ClientesDAO;
import java.util.List;
import model.Cliente;

/**
 *
 * @author oscar
 */
public class ClientesServicios {

     public List<Cliente> getAllClientes() {
        ClientesDAO dao = new ClientesDAO();
        return dao.getAllClientesDBUils();
    }
     
     public Cliente existeCliente(String dni){
         ClientesDAO dao = new ClientesDAO();
         return dao.existeCliente(dni);
     }
     public boolean addCliente(Cliente c){
         ClientesDAO dao = new ClientesDAO();
         return dao.addCliente(c);
     }
     public boolean updateCliente(Cliente c){
         ClientesDAO dao = new ClientesDAO();
         return dao.updateCliente(c);
     }

    public boolean updatemasCliente(Cliente c, int sal) {
        ClientesDAO dao = new ClientesDAO();
         return dao.updatemasCliente(c, sal);
    }
    }
