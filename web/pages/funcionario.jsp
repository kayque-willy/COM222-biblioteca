<%@page import="java.util.List"%>
<%@page import="biblioteca.funcionario.Funcionario"%>
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
                        <li class="active"><a href="funcionario.jsp">Funcionários</a></li>
                        <li><a href="associado.jsp">Associados</a></li>
                        <li><a href="publicacao.jsp">Publicações</a></li>
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
                                        <h3 class="text-center">Cadastrar funcionário</h3>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3">
                                        <jsp:useBean id="funcionarioController" class="biblioteca.funcionario.FuncionarioController" scope="request" />  
                                        <%
                                            List<Funcionario> funcionarios;
                                            if (request.getParameter("filtro") != null && request.getParameter("filtro").equals("true")) {
                                                funcionarios = funcionarioController.filtrar(Integer.valueOf(request.getParameter("filtro-codigo")), request.getParameter("filtro-nome"));
                                            } else {
                                                funcionarios = funcionarioController.getFuncionarios();
                                            }
                                        %>
                                        <form role="form" action="/biblioteca/FuncionarioCRUD">
                                            <input type="hidden" name="tipo" value="cadastro"/><br/>
                                            <div class="form-group">
                                                <label class="control-label" for="exampleInputEmail1">Codigo</label>
                                                <input class="form-control" type="text" placeholder="código" name="codigo">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Nome</label>
                                                <input class="form-control" type="text" placeholder="nome"  name="nome">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Endereço</label>
                                                <input class="form-control" type="text" placeholder="endereço" name="endereco">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Email</label>
                                                <input class="form-control" type="text" placeholder="email" name="email">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Senha</label>
                                                <input class="form-control" type="text" placeholder="senha" name="senha">
                                            </div>
                                            <button type="submit" class="btn btn-success pull-left">Submit</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nome</th>
                                    <th>Email</th>
                                    <th>Endereço</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Funcionario f : funcionarios) {%>
                                <tr>
                                    <td><%=f.getCodigo()%></td>
                                    <td><%=f.getNome()%></td>
                                    <td><%=f.getEmail()%></td>
                                    <td><%=f.getEndereco()%></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-2">
                        <form role="form" action="funcionario.jsp">
                            <input type="hidden" name="filtro" value="true"/><br/>
                            <div class="form-group">
                                <label class="control-label" for="exampleInputEmail1">Código</label>
                                <input name="filtro-codigo" class="form-control" id="exampleInputEmail1" placeholder="Filtrar por codigo" type="text">
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="exampleInputPassword1">Nome</label>
                                <input name="filtro-nome" class="form-control" id="exampleInputPassword1" placeholder="Filtrar por nome" type="text">
                            </div>
                            <div class="form-group">
                                <label class="control-label">E-mail</label>
                                <input class="form-control" type="text" placeholder="Filtrar por email">
                            </div>
                            <button type="submit" class="btn btn-info">Filtrar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="section" style="height: 140px; bottom: 0; background-image: url('../resources/image/rodape.jpg');"/>
    </body>
</html>
