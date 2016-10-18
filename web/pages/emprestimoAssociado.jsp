<%@page import="java.util.List"%>
<%@page import="biblioteca.associado.Associado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if (request.getSession().getAttribute("usuario") == null) {
        response.sendRedirect("/biblioteca/login.jsp");
    }
    if (request.getSession().getAttribute("tipo") != null && request.getSession().getAttribute("tipo").equals("funcionario")) {
        response.sendRedirect("/biblioteca/login.jsp");
    }%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="navbar navbar-default navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#"><span>COM222 - Biblioteca</span></a>
                </div>
                <div class="collapse navbar-collapse" id="navbar-ex-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="indexAssociado.jsp">Inicio</a></li>
                        <li><a class="active" href="emprestimoAssociado.jsp">Concultar empréstimos</a></li>
                        <li><a href="/biblioteca/Logout">Sair</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h1 class="text-center">Bem Vindo <%=request.getSession().getAttribute("usuario")%>!</h1>
                    </div>
                </div>
            </div>
        </div><div class="section"><div class="container"><div class="row"><div class="col-md-3"><div class="col-md-2">
                            <div class="section">
                                <div class="container">
                                    <div class="row"><div class="col-md-12"><a class="btn btn-primary">Devolução</a></div></div><div class="row">
                                        <div class="col-md-2">
                                            <h3 class="text-center">Realizar empréstimo</h3>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-2">
                                            <form role="form">
                                                <div class="form-group">
                                                    <label class="control-label" for="exampleInputEmail1">Exemplar</label>
                                                    <input class="form-control" type="text" placeholder="Número do exemplar">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Publicação</label>
                                                    <input class="form-control" type="text" placeholder="ISBN da publicação">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Data do empréstimo</label>
                                                    <input class="form-control" type="text" placeholder="Data">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Código do associado</label>
                                                    <input class="form-control" type="text" placeholder="Código">
                                                </div>
                                                <button type="submit" class="btn btn-success pull-left">Submit</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div></div><div class="col-md-7"><table class="table">
                            <thead>
                                <tr>
                                    <th>Titulo</th>
                                    <th>Exemplar</th>
                                    <th>ISBN</th><th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>gg</td>
                                    <td>Mark</td>
                                    <td>Otto</td><td class="success">Otto</td></tr>
                                <tr>
                                    <td>ggg</td>
                                    <td>Jacob</td>
                                    <td>Thornton</td><td class="danger">Thornton</td>
                                </tr>
                                <tr>
                                    <td>gggg</td>
                                    <td>Larry</td>
                                    <td>the Bird</td><td class="success">the Bird</td>
                                </tr>
                            </tbody>
                        </table></div><div class="col-md-2"><form role="form"><div class="form-group"><label class="control-label" for="exampleInputEmail1">Exemplar</label><input class="form-control" id="exampleInputEmail1" placeholder="Exemplar" type="text"></div><div class="form-group"><label class="control-label" for="exampleInputPassword1">Publicação</label><input class="form-control" id="exampleInputPassword1" placeholder="Password" type="password"></div><div class="form-group"><label class="control-label" for="exampleInputPassword1">Código</label><input class="form-control" id="exampleInputPassword1" placeholder="Código do associado" type="password"></div><button type="submit" class="btn btn-default">Filtrar</button></form></div></div></div></div>


    </body></html>
