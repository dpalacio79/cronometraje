<%-- 
    Document   : altaCompetencia
    Created on : 14 ago 2024, 13:07:12
    Author     : daniel
--%>
<%@page import="java.util.ArrayList"%>

<%@page import="Controller.GestorDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/index.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Edu+AU+VIC+WA+NT+Hand&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Raleway:ital@1&display=swap"
            rel="stylesheet">
        <title>Cronometrajes</title>
    </head>

    <body>
        <div class="container-fluid bg-dark">
            <div class="container-md" style="padding: 0px 50px;">

                <div class="header">

                </div>
                <div class="nav nav-pills botones d-flex flex-row">
                    <button type="button" class="btn p-2" disabled>Alta</button>
                    <button type="button" class="btn btn-outline-light p-2" disabled>Clasificaciones</button>
                    <button type="button" class="btn btn-outline-light p-2" disabled>Carga de Datos</button>
                </div>
                <div class="main"></div>
                <section class="wrapper">
                    <h3 class="page-header" style="margin-bottom: 5px; margin-top: 5px;"><i class="fa fa-file-text-o"></i> Nueva Competencia</h3>
                    <section class="layout">
                        <div class="grow1"></div>
                            <div class="grow1 text-light text-right">
                                <form class="form-horizontal " method="POST" action="Competencias" >
                                    <div class="form-group">
                                        <label class="control-label text-right">Nombre de Competencia</label>
                                        <input type="text" name="nombre" id="nombre" class="form-control">
                                        <label class="control-label text-right">Lugar</label>
                                        <input type="text" name="lugar" id="lugar" class="form-control">
                                        <label class="control-label text-right">Descripcion</label>
                                        <input type="text" name="descripcion" id="descripcion" class="form-control">
                                        <label class="control-label">Fecha</label>
                                        <input type="date" name="fecha" id="fecha" class="form-control" required>
                                    </div>
                                    <br>

                                    <input type="submit" class="btn btn-secondary" value="Cargar Competencia"  onclick="validar();" >
                                    <a href="Menu" class="btn btn-danger">Cancelar</a>
                                </form></div>
                        <div class="grow1"></div>
                    </section>

                </section>
                <div class="footer">Pie de pagina</div>

            </div>
        </div>

        <script>
            function validar() {
                var nombre = document.getElementById('nombre');
                var lugar = document.getElementById('lugar');

                if (nombre.value === "" || lugar.value === "")
                {
                    alert('falta ingresar datos');
                    nombre.focus();
                }
            }

        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>

    </body>
</html>
