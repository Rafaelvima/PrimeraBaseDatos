<%-- 
    Document   : newjsp
    Created on : Feb 1, 2018, 7:29:33 PM
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
        <!--<link rel="stylesheet/css" type="text/css" href="css/estilo.css" />
        <link rel="stylesheet/less" type="text/css" href="css/estilo.less">!-->
        <script src="//cdnjs.cloudflare.com/ajax/libs/less.js/3.0.0/less.min.js" ></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <script>

            function cargarpag() {

            }
           
            function verMovimiento(){
                $.ajax({
                    type:"POST",
                    url:"http://localhost:8083/Banky/movimientos",
                    data:$("#form2").serialize(),
                        success : function (data){
                           $("#output").append(data);
                        }
                    
                })
            }
            function abrirCuenta(){
                $.ajax({
                    type:"POST",
                    url:"http://localhost:8083/Banky/cuentas",
                    data:$("#form3").serialize(),
                    success : function (data){
                        $("#output").append(data);
                        
                        }
                })
            }
            function crearCliente(){
                $.ajax({
                    type:"POST",
                    url:"http://localhost:8083/Banky/clientes",
                    data:$("#form1").serialize(),
                    success : function (data){
                           $("#output").append(data);
                        }
                })
            }
            function verCuenta(){
                $.ajax({
                    type:"POST",
                    url:"http://localhost:8083/Banky/movimientos",
                    data:$("#form2").serialize(),
                        success : function (data){
                           $("#output").append(data);
                        }
                    
                })
            }
            function ingresarono(){
                if(document.getElementById("ingresar").checked==checked){
                    $.ajax({
                    type:"POST",
                    url:"http://localhost:8083/Banky/movimientos",
                    data:{mo_ncu:document.getElementById("mo_nocu").value,
                           cu_sal:document.getElementById("cu_sal").value,
                            op:"ingresar"},
                        success : function (data){
                           $("#output").append(data);
                        }
                    
                })
                }else{if(document.getElementById("retirar").checked==checked){
                        $.ajax({
                    type:"POST",
                    url:"http://localhost:8083/Banky/movimientos",
                    data:{mo_ncu:document.getElementById("mo_nocu").value,
                           cu_sal:document.getElementById("cu_sal").value,
                            op:"retirar"},
                        success : function (data){
                           $("#output").append(data);
                        }
                    
                })
                }else{
                    alert("selecciona retirar o ingresar");
                }
                    
                }
                
                
            }
        </script>
    </head>
    <body>
        <h1>Clientes</h1>
        <form id="form1" action="clientes">
            <input type="hidden" value="iniciarS" id="op">
        Numero de cuenta<input id="num_cuenta" value="1234567890">
        DNI1<input id="dni1" value="12345678j">
        DNI2(opcional)<input id="dni2" value="12345678j">
        <button id="opcli" name ="op" value="all"> all clientes </button>
        <input type="button" value="añadir cliente" onclick="crearCliente();">
        </form>
         <h1>Cuentas</h1>
         <form id="form3" action="cuentas">
        <input type="hidden" value="abrirCu" name="op" id="opcu">
        nºcuenta<input id="cu_ncu" name="cu_ncu" value="0000000011">
        dn1 <input id="cu_dn1" name="cu_dn1" value="11111111A" type="text" >
        dn2 (opcional)<input id="c_dn2" name="cu_dn2" type="text">
        salario (min 1)<input type="number" id="cu_sal" name="cu_sal" min="1" value="1">
        <input type="button" value="abrir cuenta" onclick="abrirCuenta();">
        </form>
          <h1>Movimientos</h1>
        <form id="form2" action="movimientos">
        <input type="hidden" value="ver" name="op" id="opmovi">
      Numero de cuenta  <input id="mo_ncu" name="mo_ncu" value="0000000011">
        Fecha inicio<input id="fechaini" name="fechaini" type="date" >
       Fecha final <input id="fechafin" name="fechafin" type="date">
        <input type="button" value="ver movimientos" onclick="verMovimiento();">
        </form>
          <h1>Ingresar o reintegros</h1>
        <form id="form4" action="movimientos">
        <input type="hidden" value="ver" name="op" id="opmovi">
      Numero de cuenta  <input id="mo_ncu" name="mo_ncu" value="0000000011">
      Cantidad (min 1)<input type="number" id="cu_sal" name="cu_sal" min="1" value="1"><br>
      Ingresar <input type="radio" id="ingresar" name="tipo" > retirar  <input type="radio" id="retirar" name="tipo">
        <input type="button" value="ver movimientos" onclick="ingrsarono();">
        </form>
        <div id="output"></div> 
    </body>
</html>
