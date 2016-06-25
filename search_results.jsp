<%-- 
    Document   : search_results
    Created on : Feb 8, 2016, 8:06:27 PM
    Author     : Avaljot
--%>
<%@page import="entity.User"%>
<%@page import="com.stuffit.www.data.objects.Recipe"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.stuffit.www.data.objects.Ingredient"%>
<%@page import="java.util.List"%>
<%@page import="com.stuffit.www.data.DataManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="search.jsp"  %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recipes</title>
    </head>
    <body>
        <div class="col-sm-9" style="background-image: url('/StuffIt1/img/back1.png'); color: #ccff33; text-align:center; font-size:60px;">
            [Search result by ingredients]
        </div>
       
        <div style="text-align: left;padding-left: 40%; padding-right: 20%;font-family: monospace">
            <%
            List<Recipe> rp = (List<Recipe>)session.getAttribute("recipeList");
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
                    <p style="font-weight: bold; font-size: 20px;">
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
        </div>       
    </body>
</html>
