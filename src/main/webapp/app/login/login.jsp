<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="login.css" rel="stylesheet">
        <link href="../../bootstrap/css/bootstrap.css" rel="stylesheet">

    </head>
    <div class = "container">
        <div class="wrapper">
            <form action="" method="post" name="formLogin" class="form-signin" action="login" method="post">

                <h3 class="form-signin-heading">Seja bem vindo!</h3>
                <hr class="colorgraph"><br>
                
                <input type="text" class="form-control" id="usuario" name="usuario" placeholder="Usuário" required="true" autofocus="true" maxlength="30"/>
                <input type="password" class="form-control" id="senha" name="senha" placeholder="Senha" required="true" maxlength="20"/>     		  
                
                <button id="entrar" name="entrar" class="btn btn-lg btn-primary btn-block">Entrar</button>
            </form>			
        </div>
    </div>
</html>