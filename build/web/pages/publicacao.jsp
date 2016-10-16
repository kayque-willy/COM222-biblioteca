<%@page import="biblioteca.funcionario.Funcionario"%>
<%@page import="java.util.List"%>
<%@page import="biblioteca.publicacao.Publicacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if (request.getSession().getAttribute("usuario") == null) {
        response.sendRedirect("/biblioteca/login.jsp");
    }%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Publicacaos</title>
    </head>
    <body>
        <h1>Bem Vindo <%=request.getSession().getAttribute("usuario")%>!</h1>

        <div>
            <ul>
                <li><a>Efetuar Empréstimos</a></li>
                <li><a href="funcionario.jsp">Funcionários</a></li>
                <li><a href="associado.jsp">Publicacaos</a></li>
                <li><a href="publicacao.jsp">Publicações</a></li>
                <li><a href="exemplar.jsp">Exemplares</a></li>
                <li><a>Relatórios</a></li>
            </ul>
        </div>
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
        <div>
            <h1>Adicionar:</h1>
            <form id="form-add" action="/biblioteca/PublicacaoCRUD">
                ISBN:<input type="text" name="isbn"/><br/>
                Titulo:<input type="text" name="titulo"/><br/>
                Autor:<input type="text" name="autor"/><br/>
                Editora:<input type="text" name="editora"/><br/>
                Ano:<input type="text" name="ano"/><br/>
                Funcionário:
                <select name="funcionario">
                    <option value="">Selecione</option>
                    <% for (Funcionario f : funcionarios) {%>
                    <option value="<%=f.getCodigo()%>"><%=f.getNome() %></option>
                   <%}%>
                </select><br/>
                <input type="hidden" name="tipo" value="cadastro"/><br/>
                <button type="submmit">Cadastrar</button>
            </form> 
        </div>
        <div>
            <h1>Filtro:</h1>
            <form action="publicacao.jsp">
                ISBN:<input type="text" name="filtro-isbn"/><br/>
                Título:<input type="text" name="filtro-titulo"/><br/>
                <input type="hidden" name="filtro" value="true"/><br/>
                <button type="submmit">Filtrar</button>
            </form>
        </div>
        <div>
            <h1>Publicacões:</h1>
            <table>
                <tr>
                    <td>ISBN</td>
                    <td>Título</td>
                    <td>Autor</td>
                    <td>Editora</td>
                    <td>Ano</td>
                </tr>
                <% for (Publicacao f : publicacaos) {%>
                <tr>
                    <td><%=f.getIsbn()%></td>
                    <td><%=f.getTitulo()%></td>
                    <td><%=f.getAutor()%></td>
                    <td><%=f.getEditora()%></td>
                    <td><%=f.getAno()%></td>
                </tr>
                <%}%>
            </table>
        </div>
    </body>
</html>
