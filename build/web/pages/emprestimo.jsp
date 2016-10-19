<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="biblioteca.emprestimo.Emprestimo"%>
<%@page import="biblioteca.exemplar.Exemplar"%>
<%@page import="java.util.List"%>
<%@page import="biblioteca.associado.Associado"%>
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
                        <li class="active"><a href="emprestimo.jsp">Efetuar empréstimos</a></li>
                        <li><a href="funcionario.jsp">Funcionários</a></li>
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
           <h1 class="text-center">Realizar empréstimos</h1>
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <div class="col-md-2">
                            <div class="section">
                                <div class="container">
                                    
                                    <div class="row">
                                        <div class="col-md-2">
                                            <h3 class="text-center">Realizar empréstimo</h3>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-2">
                                            <form role="form" action="/biblioteca/EmprestimoCRUD">
                                                <input type="hidden" name="tipo-operacao" value="cadastrar"/><br/>
                                                <div class="form-group">
                                                    <label class="control-label" for="exampleInputEmail1">Exemplar</label>
                                                    <input class="form-control" name="numero" type="text" placeholder="Número do exemplar">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Publicação</label>
                                                    <input class="form-control" name="isbn" type="text" placeholder="ISBN da publicação">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Data do empréstimo</label>
                                                    <input class="form-control" name="data_emprestimo" type="date">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Código do associado</label>
                                                    <input class="form-control" name="codigo_associado" type="text" placeholder="Código">
                                                </div>
                                                <button type="submit" class="btn btn-success pull-left">Emprestar</button>
                                            </form>
                                            <br>
                                            <hr>
                                            <h3 class="text-center">Devolver empréstimo</h3>
                                            <form role="form" action="/biblioteca/EmprestimoCRUD">
                                                <input type="hidden" name="tipo-operacao" value="atualizar"/><br/>
                                                <br>
                                                <div class="form-group">
                                                    <label class="control-label" for="exampleInputEmail1">ID do emprestimo</label>
                                                    <input class="form-control" name="numero" type="text" placeholder="Numero do emprestimo">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Data da devolução</label>
                                                    <input class="form-control" name="data_devolucao" type="date">
                                                </div>
                                                <button type="submit" class="btn btn-primary pull-left">Devolver</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7">
                        <table class="table">
                            <h3 class="text-center">Exemplares disponíveis para empréstimo</h3>
                            <thead>
                                <tr>
                                    <th>Numero</th>
                                    <th>ISBN</th>
                                    <th>Preço</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <jsp:useBean id="exemplarController" class="biblioteca.exemplar.ExemplarController" scope="request" />  
                                <%
                                    List<Exemplar> exemplares;
                                    if (request.getParameter("filtro") != null && request.getParameter("filtro").equals("true")) {
                                        exemplares = exemplarController.filtrar(Integer.valueOf(request.getParameter("filtro-exemplar")), request.getParameter("filtro-isbn"));
                                    } else {
                                        exemplares = exemplarController.getExemplars();
                                    }
                                %>
                                <% for (Exemplar f : exemplares) {%>
                                <tr>
                                    <td><%=f.getNumero()%></td>
                                    <td><%=f.getPublicacao_isbn()%></td>
                                    <td><%=NumberFormat.getCurrencyInstance().format(f.getPreco())%></td>
                                    <td
                                        <%if(f.getStatus().equals("Livre")){%>
                                            class="success"
                                        <%}else{%> 
                                            class="danger"
                                         <%}%>>
                                        <%=f.getStatus()%>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                        <hr>
                        <table class="table">
                            <h3 class="text-center">Histórico de empréstimos</h3>
                            <thead>
                                <tr>
                                    <th>ID do empréstimo</th>
                                    <th>ISBN</th>
                                    <th>Exemplar</th>
                                    <th>Data de empréstimo</th>
                                    <th>Data de devolução</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <jsp:useBean id="emprestimoController" class="biblioteca.emprestimo.EmprestimoController" scope="request" />  
                                <%
                                    List<Emprestimo> emprestimos;
                                    emprestimos = emprestimoController.getEmprestimos();
                                    SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
                               %>
                                <% for (Emprestimo f : emprestimos) {%>
                                <tr>
                                    <td><%=f.getId()%></td>
                                    <td><%=f.getPublicacao_ISBN()%></td>
                                    <td><%=f.getExemplar_numero()%></td>
                                    <td><%=data.format(f.getData_emprestimo())%></td>
                                    <%if(f.getData_devolucao()!=null){%>
                                        <td><%=data.format(f.getData_devolucao())%></td>
                                    <%}else{%>
                                        <td>-</td>
                                    <%}%>
                                    <td
                                         <%if(f.getStatus().equals("Devolvido")){%>
                                            class="success"
                                        <%}else{%> 
                                            class="danger"
                                         <%}%>>
                                        <%=f.getStatus()%>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>

                    </div>
                    <div class="col-md-2">
                        <form role="form" action="emprestimo.jsp">
                            <input type="hidden" name="filtro" value="true"/><br/>
                            <div class="form-group">
                                <label class="control-label" for="exampleInputEmail1">Exemplar</label>
                                <input name="filtro-exemplar" class="form-control" id="exampleInputEmail1" placeholder="Número do exemplar" type="text">
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="exampleInputPassword1">Publicação</label>
                                <input name="filtro-isbn" class="form-control" id="exampleInputPassword1" placeholder="ISBN da publicação" type="text">
                            </div>
                            <button type="submit" class="btn btn-default">Filtrar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="section" style="height: 140px; bottom: 0; background-image: url('../resources/image/rodape.jpg');"/>
    </body>
</html>
