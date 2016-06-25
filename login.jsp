<%-- 
    Document   : login
    Created on : Feb 12, 2016, 11:14:27 PM
    Author     : Chitra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="/StuffIt1/css/login.css">
         <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
         <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
         <title>Login</title>
    </head>
    <body>
       <a id="a1" href="index.html">Back</a>
       <h2 id="h22">Login</h2>
       <div id="div1" class="container">
           <form role="form" method="post" action="login">
          <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" name="username" placeholder="Enter username">
          </div>
          <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" name="pwd" placeholder="Enter password">
          </div>
          <button type="submit" class="btn btn-default">Submit</button>
        </form>
      </div>
    </body>
</html>
