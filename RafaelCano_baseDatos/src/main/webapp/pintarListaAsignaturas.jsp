<%-- 
    Document   : pintarListaAsignaturas
    Created on : Oct 28, 2017, 8:02:42 PM
    Author     : Rafa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="utils.Constantes" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asignaturas</title>
        <script>

            function cargarAsignatura(id, nombre, curso, ciclo) {
                document.getElementById("nombre").value = nombre;
                document.getElementById("idasignatura").value = id;
                document.getElementById("curso").value = curso;
                document.getElementById("ciclo").value = ciclo;

            }
        </script>
        <script>
            function updForm() {
                document.getElementById("op").value = "update";
            }
            function delForm() {
                document.getElementById("op").value = "delete";
            }
            function insForm() {
                document.getElementById("op").value = "insert";
            }
        </script>
    </head>
    <body>
        <h1>ASIGNATURAS</h1>
        <br>
        <table border="1">
            <c:forEach items="${asignaturas}" var="asignatura">  
                <tr>
                    <td>
                        <input type="button" value="cargar ${asignatura.id}" 
                               onclick="cargarAsignatura('${asignatura.id}', '${asignatura.nombre}',
                                               '${asignatura.curso}',
                                               '${asignatura.ciclo}');"/>
                    </td> 
                    <td>
                        ${asignatura.nombre}
                    </td>

                    <td>
                        ${asignatura.curso}
                    </td>
                    <td>
                        ${asignatura.ciclo}
                    </td>
                </tr>


            </c:forEach> 

        </table>
        <form action ="asignaturas?op=" method="get">
            <input type="hidden" id="idasignatura" name="id" />
            <input type="text" id="nombre" name="nombre" size="12"/>
            <input type="text" id="curso" name="curso" size="12"/>
            <input type="text"  name="ciclo" id="ciclo"/>
            <input type="hidden" id="op" name="op"/>
            <button value="update" onclick="updForm();"> actualizar</button>
            <button value="delete" onclick="delForm();"> delete</button>
            <button value="insert" onclick="insForm();"> insertar</button>
        </form>

    </body>
</html>