<%@page import="java.text.SimpleDateFormat"%>
<%@page import="biblioteca.emprestimo.Emprestimo"%>
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
                        <li class="active"><a href="emprestimoAssociado.jsp">Consultar empréstimos</a></li>
                        <li><a href="/biblioteca/Logout">Sair</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="section">
            <div class="container">
                <!--<div class="row"><div class="col-md-12"><form class="form-horizontal" role="form"><div class="form-group"><div class="col-sm-2"><label for="inputEmail3" class="control-label">ISBN</label></div><div class="col-sm-10"><input type="email" class="form-control" id="inputEmail3" placeholder="Filtrar por ISBN"></div></div><div class="form-group"><div class="col-sm-2"><label for="inputPassword3" class="control-label">Número do exemplar</label></div><div class="col-sm-10"><input type="text" class="form-control" id="inputPassword3" placeholder="Filtrar por número do exemplar"></div></div><div class="form-group"><div class="col-sm-offset-2 col-sm-10"><button type="submit" class="btn btn-primary">Filtrar</button></div></div></form></div></div>-->
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="text-center">Empréstimos do usuário</h2>
                    </div>
                </div>
                <jsp:useBean id="emprestimoController" class="biblioteca.emprestimo.EmprestimoController" scope="request" />  
                <%
                    SimpleDateFormat dt = new SimpleDateFormat("dd/MM/YYYY");
                    List<Emprestimo> emprestimos;
                    emprestimos = emprestimoController.emprestimosAssociado(Integer.valueOf(request.getSession().getAttribute("codigo").toString()));
                %>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Codigo do empréstimo</th>
                                    <th>ISBN</th>
                                    <th>Exemplar</th>
                                    <th>Data de empréstimo</th>
                                    <th>Data de devolução</th>
                                    <th>Status</th>
                                    <th>Data máxima para devolução</th>
                                    <th>Dias em atraso</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%for (Emprestimo e : emprestimos) {%>
                                <tr>
                                    <td><%=e.getId()%></td>
                                    <td><%=e.getPublicacao_ISBN()%></td>
                                    <td><%=e.getExemplar_numero()%></td>
                                    <td><%=dt.format(e.getData_emprestimo())%></td>
                                    <td><%=e.getData_devolucao_view()%></td>
                                    <td><%=e.getStatus()%></td>
                                    <td><%=dt.format(e.getData_maxima())%></td>
                                    <td><%=e.getDias_atraso()%></td>
                                    <%}%>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="section" style="height: 140px; bottom: 0; background-image: url('../resources/image/rodape.jpg');"/>       
    </body>
</html>
