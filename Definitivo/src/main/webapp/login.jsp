<%-- 
    Document   : login
    Created on : Mar 10, 2018, 5:20:59 AM
    Author     : Rafa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
     
        <form id="form1" action="users">
            <input type="hidden" value="iniciarS" id="op">
        Usuario<input id="users" value="yo">
       pass<input id="pass" value="yo">
        <button id="opcli" name ="op" value="entrar"> Login </button>
       
        </form>
    </body>
</html>
