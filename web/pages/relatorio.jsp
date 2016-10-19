<%@page import="java.text.SimpleDateFormat"%>
<%@page import="biblioteca.emprestimo.Emprestimo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if (request.getSession().getAttribute("usuario") == null) {
        response.sendRedirect("/biblioteca/login.jsp");
    }
    if (request.getSession().getAttribute("tipo") != null && request.getSession().getAttribute("tipo").equals("associado")) {
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
                        <li><a href="index.jsp">Inicio</a></li>
                        <li><a href="emprestimo.jsp">Efetuar empréstimos</a></li>
                        <li><a href="funcionario.jsp">Funcionários</a></li>
                        <li><a href="associado.jsp">Associados</a></li>
                        <li><a href="publicacao.jsp">Publicações</a></li>
                        <li><a href="exemplar.jsp">Exemplares</a></li>
                        <li class="active"><a href="relatorio.jsp">Relatório</a></li>
                        <li><a href="/biblioteca/Logout">Sair</a></li>
                    </ul>
                </div>
            </div>
        </div><div class="col-md-12">
            <h2 class="text-center">Empréstimos em atraso</h2>
        </div><div class="col-md-12">
            <table class="table">
                <thead>
                    <tr>
                        <th>Codigo do empréstimo</th>
                        <th>ISBN</th>
                        <th>Exemplar</th>
                        <th>Código do associado</th>
                    </tr>
                </thead>
                <jsp:useBean id="emprestimoController" class="biblioteca.emprestimo.EmprestimoController" scope="request" />  
                <%
                    List<Emprestimo> emprestimos;
                    emprestimos = emprestimoController.emprestimoAtrasado();
                %>
                <tbody>
                    <%for (Emprestimo e : emprestimos) {%>
                    <tr>
                        <td><%=e.getId()%></td>
                        <td><%=e.getPublicacao_ISBN()%></td>
                        <td><%=e.getExemplar_numero()%></td>
                        <td><%=e.getAssociado_codigo()%></td>
                    </tr>            
                    <%}%>
                </tbody>
            </table>
        </div>
    </body>
</html>
