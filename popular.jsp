<%-- 
    Document   : popular
    Created on : Feb 12, 2016, 11:15:14 PM
    Author     : Chitra
--%>


<%@page import="entity.Recipe"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Collections"%>
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
        <title>Popular Recipes</title>
    </head>
    <body>
        <% 
            User reg_user = (User)session.getAttribute("user"); 
            if(reg_user!=null){
                %> 
                    <a id="a11" href="">Hi! <%= reg_user.getFirstName() %> </a>
                    <a id="a1" href="login_result.jsp"> Back</a>
                <%
            }
            else{
                %>
                <a id="a1" href="index.html"> Back</a>
                <%
            }
        %>
        <h1 id="h11">
            Popular Recipes
        </h1>
        <%
            List<Recipe> rp = (List<Recipe>)session.getAttribute("popular_recipes");
            Map<String, String> newMap = new TreeMap(Collections.reverseOrder());
            for(Recipe r: rp){
                String key = r.getRating() + "," + r.getId();
                String value = r.getName()+","+  r.getType()+"," +  r.getProcess();
                newMap.put(key, value);
            }
            int count=0;
            for(Map.Entry<String, String> entry: newMap.entrySet()){
                count++;
                %>
                <div id="div1">
                    <% 
                        String data = entry.getValue(); 
                        String[] arrdata = data.split(",");
                        String data1 = entry.getKey();
                        String[] arrdata1 = data1.split(",");
                    %>
                    <p id="p1">
                        Recipe: <%= arrdata[0] %>
                    </p>
                    <p>
                        Rating: <%= arrdata1[0] %>
                    </p>
                    <p>
                        Type: <%= arrdata[1] %>
                    </p>
                    <p>
                        Directions: <%= arrdata[2] %>
                    </p>
                </div>
                <br/>
                <%
            }
        %>
    </body>
</html>
