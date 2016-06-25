<%-- 
    Document   : admin
    Created on : Apr 14, 2016, 9:39:35 PM
    Author     : Avaljot
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="stylesheet" type="text/css" href="/StuffIt1/css/login_result.css">
         <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
         <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Portal</title>
    </head>
    <body>
        <% 
            User reg_user = (User)session.getAttribute("user");
        %>
        <a id="a11" href="">Hi! <%= reg_user.getFirstName() %> </a>
        <a id="a1" href="index.html">Back</a>
        <h1 id="h11">
            Admin Portal
        </h1>
                <div>
                <form method="get" action="basicsearch">
                <div style="text-align: center" class="control-group">
                    <input type="search" name="searchtext" />
                    <input type="submit" value="Search" class="btn btn-default" />
                </div>
                </form>
                </div>
                <br/><br/><br/><br/>
                <div style="text-align: center">
                    <form method="get" action="deleterecipe_admin.jsp">
                        <input type="submit" class="btn btn-default" value="Delete Recipes"/>
                    </form>
                    </br>
                    <a href="userlist.jsp" class="btn btn-default" role="button">Delete User</a>
                </div>
    </body>
</html>
