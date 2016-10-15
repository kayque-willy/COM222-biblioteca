<%@page import="java.util.List"%>
<%@page import="biblioteca.funcionario.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if (request.getSession().getAttribute("usuario") == null) {
        response.sendRedirect("/biblioteca/login.jsp");
    }%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Funcionários</title>
    </head>
    <body>
        <h1>Bem Vindo <%=request.getSession().getAttribute("usuario")%>!</h1>

        <div>
            <ul>
                <li><a>Efetuar Empréstimos</a></li>
                <li><a href="funcionario.jsp">Funcionários</a></li>
                <li><a href="associado.jsp">Associados</a></li>
                <li><a>Publicações</a></li>
                <li><a href="exemplar.jsp">Exemplares</a></li>
                <li><a>Relatórios</a></li>
            </ul>
        </div>
        <jsp:useBean id="funcionarioController" class="biblioteca.funcionario.FuncionarioController" scope="request" />  
        <%
            List<Funcionario> funcionarios;
            if (request.getParameter("filtro") != null && request.getParameter("filtro").equals("true")) {
                funcionarios = funcionarioController.filtrar(Integer.valueOf(request.getParameter("filtro-codigo")), request.getParameter("filtro-nome"), request.getParameter("filtro-email"));
            } else {
                funcionarios = funcionarioController.getFuncionarios();
            }
        %>
        <div>
            <h1>Adicionar:</h1>
            <form id="form-add" action="/biblioteca/FuncionarioCRUD">
                Código:<input type="text" name="codigo"/><br/>
                Nome:<input type="text" name="nome"/><br/>
                Endereço:<input type="text" name="endereco"/><br/>
                E-mail:<input type="text" name="email"/><br/>
                Senha:<input type="text" name="senha"/><br/>
                <input type="hidden" name="tipo" value="cadastro"/><br/>
                <button type="submmit">Cadastrar</button>
            </form> 
        </div>
        <div>
            <h1>Filtro:</h1>
            <form action="funcionario.jsp">
                Código:<input type="text" name="filtro-codigo"/><br/>
                Nome:<input type="text" name="filtro-nome"/><br/>
                E-mail:<input type="text" name="filtro-email"/><br/>
                <input type="hidden" name="filtro" value="true"/><br/>
                <button type="submmit">Filtrar</button>
            </form>
        </div>
        <div>
            <h1>Funcionários:</h1>
            <table>
                <tr>
                    <td>Codigo</td>
                    <td>Nome</td>
                    <td>E-mail</td>
                    <td>Endereço</td>
                </tr>
                <% for (Funcionario f : funcionarios) {%>
                <tr>
                    <td><%=f.getCodigo()%></td>
                    <td><%=f.getNome()%></td>
                    <td><%=f.getEmail()%></td>
                    <td><%=f.getEndereco()%></td>
                </tr>
                <%}%>
            </table>
        </div>
    </body>
</html>
