<%-- 
    Document   : basicsearch
    Created on : Feb 21, 2016, 4:48:22 PM
    Author     : Chitra
--%>

<%@page import="com.stuffit.www.data.objects.Recipe"%>
<%@page import="java.util.List"%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/StuffIt1/css/basicsearch.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <title>Basic Search</title>
    </head>
    <body>
        <% 
            User reg_user = (User)session.getAttribute("user"); 
            if(reg_user!=null){
                %> 
                    <a id="a11" href="">Hi! <%= reg_user.getFirstName() %> </a>
                    <a id="a1" href="login_result.jsp">Back</a>
                <%
            }
            else{
                %> 
                    <a id="a1" href="index.html">Back</a>
                <%
            }
        %>
        <h1 id="h11">
            Recipes
        </h1>
        <p id="p2">
            Results for - <%= session.getAttribute("keyword") %>
        </p>
        <%
            List<Recipe> rp = (List<Recipe>)session.getAttribute("recipes");
            if(rp.isEmpty()){
                %>
                <div id="div2">
                    Sorry no recipes matching your search!
                </div>
                <%
            }
            else {
                for(Recipe r: rp){
                %>
                    <div id="div1">
                    <p id="p1">
                        Recipe: <%= r.getName() %>
                    </p>
                    <p>
                        Type: <%= r.getType() %>
                    </p>
                    <p>
                        Directions: <%= r.getProcedure()%>
                    </p>
                    <p>
                        Rating: <%= r.getRating()%>
                    </p>
                    </div>
                    <br/>
                <%
                }
            }
        %>
    </body>
</html>
