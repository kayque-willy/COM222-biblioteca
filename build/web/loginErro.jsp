<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <div>
            Usuário ou senha inválidos!
        </div>
        <form action="/biblioteca/Login">
            Login:<input type="text" name="login" value=""/><br/>
            Senha<input type="password" name="senha" value=""/><br/>
            <button type="submmit" value="Entrar">Entrar</button>
        </form>
    </body>
</html>
