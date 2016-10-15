<%@page import="java.util.List"%>
<%@page import="biblioteca.exemplar.Exemplar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if (request.getSession().getAttribute("usuario") == null) {
        response.sendRedirect("/biblioteca/login.jsp");
    }%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exemplares</title>
    </head>
    <body>
        <h1>Bem Vindo <%=request.getSession().getAttribute("usuario")%>!</h1>

        <div>
            <ul>
                <li><a>Efetuar Empréstimos</a></li>
                <li><a href="funcionario.jsp">Funcionários</a></li>
                <li><a href="associado.jsp">Associados</a></li>
                <li><a href="publicacao.jsp">Publicações</a></li>
                <li><a href="exemplar.jsp">Exemplares</a></li>
                <li><a>Relatórios</a></li>
            </ul>
        </div>
        <jsp:useBean id="exemplarController" class="biblioteca.exemplar.ExemplarController" scope="request" />  
        <%
            List<Exemplar> exemplars;
            if (request.getParameter("filtro") != null && request.getParameter("filtro").equals("true")) {
                exemplars = exemplarController.filtrar(Integer.valueOf(request.getParameter("filtro-codigo")), request.getParameter("filtro-nome"), request.getParameter("filtro-email"));
            } else {
                exemplars = exemplarController.getExemplars();
            }
        %>
        <div>
            <h1>Adicionar:</h1>
            <form id="form-add" action="/biblioteca/ExemplarCRUD">
                Numero:<input type="text" name="numero"/><br/>
                Preço:<input type="text" name="preco"/><br/>
                Publicação(ISBN):<input type="text" name="publicacao"/><br/>
                <input type="hidden" name="tipo" value="cadastro"/><br/>
                <button type="submmit">Cadastrar</button>
            </form> 
        </div>
        <div>
            <h1>Filtro:</h1>
            <form action="exemplar.jsp">
                Código:<input type="text" name="filtro-codigo"/><br/>
                Nome:<input type="text" name="filtro-nome"/><br/>
                E-mail:<input type="text" name="filtro-email"/><br/>
                <input type="hidden" name="filtro" value="true"/><br/>
                <button type="submmit">Filtrar</button>
            </form>
        </div>
        <div>
            <h1>Exemplares:</h1>
            <table>
                <tr>
                    <td>Numero</td>
                    <td>Publicacao</td>
                    <td>Preço</td>
                </tr>
                <% for (Exemplar f : exemplars) {%>
                <tr>
                    <td><%=f.getNumero()%></td>
                    <td><%=f.getPublicacao_isbn()%></td>
                    <td><%=f.getPreco()%></td>
                </tr>
                <%}%>
            </table>
        </div>
    </body>
</html>
