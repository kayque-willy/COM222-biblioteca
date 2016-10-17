<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if (request.getSession().getAttribute("usuario") != null){response.sendRedirect("/biblioteca/pages/index.jsp");} %>
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
            <a class="navbar-brand" href="#">
                <span>COM222 - Biblioteca</span>
            </a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-ex-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="#">Login Funcionário</a></li>
            </ul>
        </div>
    </div>
</div>
        <div class="section"> 
            <div class="container"> 
                <div class="row"> 
                    <div class="col-md-12"> 
                        <h1 class="text-center">Login do funcionário</h1> 
                    </div>
                </div>
                <div class="row"> 
                        <div class="col-md-offset-3 col-md-6">
                                    </div>
                            <form role="form" class="text-center" action="/biblioteca/Login">
                                <div class="form-group">
                                   <label class="control-label" for="exampleInputPassword1">Usuário</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-addon"></span>
                                        <input type="text" class="form-control" name="login" value="" placeholder="Usuário">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="exampleInputPassword1">Senha</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-addon"></span>
                                        <input type="password"  name="senha" class="form-control" placeholder="Senha">
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-lg btn-primary">Entrar</button>
                            </form> 
                        </div>
                    </div>
            </div>
        </div>
    </body>
</html>
