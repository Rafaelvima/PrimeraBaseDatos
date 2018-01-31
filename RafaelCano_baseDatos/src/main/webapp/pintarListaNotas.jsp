<%-- 
    Document   : pintarNotas
    Created on : Nov 10, 2017, 10:28:37 AM
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
        <title>JSP Page</title>
        <script>
            var arrays = new Array(600);
            for (var j = 0; j < 600; j++) {
                arrays[j] = new Array();
            }
            <c:forEach items="${notas}" var="nota">
            arrays[${nota.id_alumno}][${nota.id_asignatura}] =${nota.nota};
            </c:forEach>

            function cargarAlumno() {
                var coger1 = document.getElementById("id_alumnoS");
                var idalum = coger1.options[coger1.selectedIndex].value;
                document.getElementById("id_alumno").value = idalum;
            }
            function cargarAsignatura() {
                var coger2 = document.getElementById("id_asignaturaS");
                var idasig = coger2.options[coger2.selectedIndex].value;
                document.getElementById("id_asignatura").value = idasig;
            }
            function cargarNota() {
                var idAL = document.getElementById("id_alumno").value;
                var idAS = document.getElementById("id_asignatura").value;
                document.getElementById("nota").value = arrays[idAL][idAS];
                alert(arrays[idAL][idAS]);
            }



        </script>
    </head>
    <body>
        <h1>ALUMNOS</h1>
        pruebaCTE: <%= Constantes.PRUEBA%> <br>
        <table border="1">
            <tr>
                <td>
                    <select id="id_alumnoS" form="">
                        <c:forEach items="${alumnos}" var="alumno">  
                            <option value='${alumno.id}'>
                                ${alumno.nombre}
                            </option>
                        </c:forEach> 
                    </select>
                </td>
                <td>
                    <button value="seleccionar" onclick="cargarAlumno();"> seleccionar</button>

                </td>
            </tr> 

        </table>
        <h1>ASIGNATURAS</h1>
        <table border="1">
            <tr>
                <td>
                    <select id="id_asignaturaS" form="hola">
                        <c:forEach items="${asignaturas}" var="asignatura">  
                            <option value='${asignatura.id}' >
                                ${asignatura.nombre}
                            </option>
                        </c:forEach> 
                    </select>
                </td>
                <td>
                    <button value="seleccionar" onclick="cargarAsignatura();"> seleccionar</button>

                </td>
            </tr>
        </table>
        <button value="ver" onclick="cargarNota();" > cargar nota </button> 
        <form action ="notas?op=" method="get">
            <input type="hidden" id="nombre" name="nombre" size="12"/>
            id_alumno: <input type="text" id="id_alumno" name="id_alumno" size="12"/>
            id_asignatura: <input type="text" id="id_asignatura" name="id_asignatura" s="12"/>
            nota: <input type="text" id="nota" name="nota" value="">
            <input type="hidden" id="todo" name="todo" value="todo">

            <input type="hidden" id="op" name="op"/>
            <button value="update" onclick="updForm();"> guardar</button>
            <button value="deleteAll" onclick="delFormAll();"> delete ALL(include ALUMNOS y Assignaturas)</button>
            <button value="delete" onclick="delForm();"> delete solo Notas </button>
        </form>
        <script>

            function updForm() {
                document.getElementById("op").value = "update";
            }
            function delForm() {
                document.getElementById("op").value = "delete";

            }
            function delFormAll() {
                document.getElementById("op").value = "deleteAll";
                // var delT = prompt("queires todo o nada","");
                // document.getElementById("todo").value=delT;
            }
            function verNota() {
                document.getElementById("op").value = "ver";
            }
        </script>
    </body>
</html>
