<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if (request.getSession().getAttribute("usuario") == null){response.sendRedirect("/biblioteca/login.jsp");} %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bem Vindo <%=request.getSession().getAttribute("usuario") %>!</h1>
        
        <div>
            <ul>
                <li><a>Efetuar Empréstimos</a></li>
                <li><a href="pages/funcionario.jsp">Funcionários</a></li>
                <li><a href="pages/associado.jsp">Associados</a></li>
                <li><a>Publicações</a></li>
                <li><a>Exemplares</a></li>
                <li><a>Relatórios</a></li>
            </ul>
        </div>
        
    </body>
</html>
