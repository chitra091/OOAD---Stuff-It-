<%-- 
    Document   : editrecipe
    Created on : Mar 21, 2016, 12:27:31 AM
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
        <link rel="stylesheet" type="text/css" href="/StuffIt1/css/popular.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <title>Edit Recipe</title>
    </head>
    <body>
        <% 
            User reg_user = (User)session.getAttribute("user");
        %>
        <a id="a11" href="">Hi! <%= reg_user.getFirstName() %> </a>
        <a id="a1" href="login_result.jsp"> Back</a>
        <h1 id="h11">
            Edit Recipe
        </h1>
        <%
            List<Recipe> rp = (List<Recipe>)session.getAttribute("editrecipes");
            if(rp.isEmpty()){
                %>
                <div id="div2">
                    Sorry no recipes to edit!
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
                    <button type="button" onclick="edit('<%=r.getName()%>')" value="Edit" class="btn btn-default">Edit</button>
                    <script>
                       function edit(recipe){
                       window.location.href = "editdisp?r="+recipe;
                       //window.location.replace("search_serv?l="+list);
                        }
                     </script>
                    </div>
                    <br/>
                <%
                }
            }
        %>
    </body>
</html>
