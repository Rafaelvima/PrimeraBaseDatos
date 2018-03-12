<%-- 
    Document   : PintarClientes.jsp
    Created on : Mar 8, 2018, 6:42:11 PM
    Author     : Rafa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <h1>ALUMNOS</h1>
        
        <table border="1">
             <tr> 
                    <th>
                       DNI Cliente
                    </th>
                    <th>
                        Nombre Cliente
                    </th>
                        
                    </tr>
                    <tr>
                        ${users}
                    </tr>
            <c:forEach items="${users}" var="user">  
                <tr> 
                    <td>
                      ${user.us_nom}
                    </td>
                    <td>
                        ${user.us_pass}
                    </td>
                    
                       
                    </tr>
                    


            </c:forEach> 

        </table>
        <form action="clientes" method="GET">
            <input type="hidden" id="idalumno" name="idalumno"/>
            <input type="hidden" id="op" name="op" value="insertar"/>
            
            <input type="text" id="nombre" name="nombre" size="12"/>
            <button onclick="valor();" value="submit" >
                submit </button>
        </form>
        <script>
              function valor(){
                  document.getElementById("op").value="insertar";
              }  
                </script>
    </body>
</html>
