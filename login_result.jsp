<%-- 
    Document   : login_result
    Created on : Mar 18, 2016, 11:27:46 PM
    Author     : Chitra
--%>
<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="/StuffIt1/css/login_result.css">
         <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
         <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <title>Login Result</title>
    </head>
    <body>
        <% 
            String msg1 = (String)session.getAttribute("message");
            Boolean user_valid = false;
            if(!msg1.equals("Valid")){
                %>
                <a id="a1" href="index.html"> Back</a>
                <%
            }
            else{
                User reg_user = (User)session.getAttribute("user");
                user_valid = true;
                %>
                <a id="a11" href="">Hi! <%= reg_user.getFirstName() %> </a>
                <a id="a1" href="index.html"> Back</a>
                <%
            }
        %>
        <h1 id="h11">
            Stuff It!
        </h1>
        <%
            if(!user_valid){
                String msg = (String)session.getAttribute("message");
                if(msg.equals("Invalid")){
                   %>
                    <p style="text-align: center">
                        Invalid username!
                    </p>
                    <div style="text-align: center">
                        <a href="register.jsp" class="btn btn-default" role="button">Register</a>
                    </div>
                <% 
                }
                else {
                    %>
                    <p style="text-align: center">
                        Incorrect password! Try again!
                    </p>
                    <div style="text-align: center">
                        <a href="login.jsp" class="btn btn-default" role="button">Login</a>
                    </div>
                <%
                    }
                %>
                <%
            }
            else{
                %>
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
                    <a href="addrecipe.jsp" class="btn btn-default" role="button">Add Recipe</a>
                    <a href="editrecipe" class="btn btn-default" role="button">Edit Recipe</a>
                    </br></br>
                    <form method="get" action="deleterecipe">
                        <input type="submit" class="btn btn-default" value="Delete Recipes"/>
                    </form>
                    </br>
                    <a href="raterecipe.jsp" class="btn btn-default" role="button">Rate Recipes</a>
                    <a href="search.jsp" class="btn btn-default" role="button">Browse Recipes</a>
                    <a href="popular" class="btn btn-default" role="button">Popular Recipes</a>
                </div>
                <%
            }
        %>
    </body>
</html>
