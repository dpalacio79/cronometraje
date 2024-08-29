<%-- 
    Document   : login
    Created on : 29 ago 2024, 9:32:27
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
<body class="login-img3-body">

        <div class="container">

            <form class="login-form" method="post" action="Menu" style= "margin-top:120px">
                <div class="login-wrap">
                    
                    <p class="login-img"><i class="icon_lock_alt"></i></p>
                    <% if ((request.getAttribute("mensajeError") != null) && (!request.getAttribute("mensajeError").equals(""))) { %>
                    <h4 style="align-content: center; color: black">${mensajeError}</h4>
                    <% }%>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="icon_profile"></i></span>
                        <input type="text" class="form-control" name="txtUsuario" placeholder="Usuario" autofocus>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="icon_key_alt"></i></span>
                        <input type="password" class="form-control" name="txtPass" placeholder="Password">
                    </div>
                    <input type="submit" class="btn btn-primary btn-lg btn-block" value="Iniciar Sesion">
                    <a href="menuPrincipal.jsp" class="btn btn-info btn-lg btn-block">Cancelar</a>

                </div>
            </form>

        </div>


    </body>        

</html>
