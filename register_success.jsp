<%-- 
    Document   : register_success
    Created on : Mar 18, 2016, 3:43:16 PM
    Author     : Chitra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="/StuffIt1/css/register.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <title>Register Success</title>
    </head>
    <body>
        <a id="a1" href="index.html">Back</a>
        <h2 id="h22">Register</h2>
        <%
        String msg = (String)session.getAttribute("message"); 
        %>
        <p style="text-align: center"> <%= msg %> </p>
        <div id="d1">
                   <a href="login.jsp" class="btn btn-default" role="button">Login</a>
            </div>
    </body>
</html>
