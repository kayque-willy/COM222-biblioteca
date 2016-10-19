<%@page import="biblioteca.funcionario.Funcionario"%>
<%@page import="java.util.List"%>
<%@page import="biblioteca.publicacao.Publicacao"%>
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
                        <li class="active"><a href="publicacao.jsp">Publicações</a></li>
                        <li><a href="exemplar.jsp">Exemplares</a></li>
                        <li><a href="relatorio.jsp">Relatório</a></li>
                        <li><a href="/biblioteca/Logout">Sair</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <div class="section">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-3">
                                        <h3 class="text-center">Cadastrar publicação</h3>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3">
                                        <jsp:useBean id="publicacaoController" class="biblioteca.publicacao.PublicacaoController" scope="request" />  
                                        <%
                                            List<Publicacao> publicacaos;
                                            List<Funcionario> funcionarios;
                                            if (request.getParameter("filtro") != null && request.getParameter("filtro").equals("true")) {
                                                publicacaos = publicacaoController.filtrar(request.getParameter("filtro-isbn"), request.getParameter("filtro-titulo"));
                                            } else {
                                                publicacaos = publicacaoController.getPublicacaos();
                                            }
                                            funcionarios = publicacaoController.getFuncionarios();
                                        %>
                                        <form id="form-add" action="/biblioteca/PublicacaoCRUD">
                                            <input type="hidden" name="tipo" value="cadastro"/><br/>
                                            <div class="form-group">
                                                <label class="control-label" for="exampleInputEmail1">ISBN</label>
                                                <input class="form-control" type="text" placeholder="ISBN" name="isbn">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Título</label>
                                                <input class="form-control" type="text" placeholder="título" name="titulo">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Autor</label>
                                                <input class="form-control" type="text" placeholder="autor" name="autor">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Editora</label>
                                                <input class="form-control" type="text" placeholder="editora" name="editora">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Ano</label>
                                                <input class="form-control" type="text" placeholder="ano" name="ano">
                                            </div>
                                            <div class="form-group"><label class="control-label">Funcionário</label>
                                                <select name="funcionario" class="form-control">
                                                    <option value="">Selecione</option>
                                                    <% for (Funcionario f : funcionarios) {%>
                                                    <option value="<%=f.getCodigo()%>"><%=f.getNome()%></option>
                                                    <%}%>
                                                </select>
                                                <button type="submit" class="btn btn-success pull-left">Cadastrar</button>
                                        </form>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>ISBN</th>
                                                <th>Título</th>
                                                <th>Autor</th>
                                                <th>Editora</th>
                                                <th>Ano</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (Publicacao f : publicacaos) {%>
                                            <tr>
                                                <td><%=f.getIsbn()%></td>
                                                <td><%=f.getTitulo()%></td>
                                                <td><%=f.getAutor()%></td>
                                                <td><%=f.getEditora()%></td>
                                                <td><%=f.getAno()%></td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-md-2">
                                    <form role="form">
                                        <input type="hidden" name="filtro" value="true"/><br/>
                                        <div class="form-group">
                                            <label class="control-label" for="exampleInputEmail1">ISBN</label>
                                            <input name="filtro-isbn" class="form-control" id="exampleInputEmail1" placeholder="Filtrar por codigo" type="text">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="exampleInputPassword1">Titulo</label>
                                            <input name="filtro-titulo" class="form-control" id="exampleInputPassword1" placeholder="Filtrar por titulo" type="text">
                                        </div>

                                        <button type="submit" class="btn btn-info">Filtrar</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
        <div class="section" style="height: 140px; bottom: 0; background-image: url('../resources/image/rodape.jpg');"/>
    </body>
</html>
