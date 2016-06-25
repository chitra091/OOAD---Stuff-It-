<%-- 
    Document   : rate
    Created on : Apr 28, 2016, 3:46:49 PM
    Author     : Avaljot
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="/StuffIt/css/register.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <title>Rating</title>
    </head>
    <body>
       <% String recipeid=(String)request.getParameter("recipeid");
       
            User reg_user = (User)session.getAttribute("user");
        %>
        <a id="a11" href="">Hi! <%= reg_user.getFirstName() %> </a>
        <a id="a1" href=""> Back</a>
        <h1 id="h11">
            Delete Recipe
        </h1>
       
        Rating: <select name="rate" id="rating" onchange="ratefun()">
           <option>1</option>
           <option>2</option>
           <option>3</option>
           <option>4</option>
           <option>5</option>
           <option>6</option>
           <option>7</option>
           <option>8</option>
           <option>9</option>
           <option>10</option>
       </select>
        
        Comment: 
       
       <script>
           function funrate(){
               var e = document.getElementById("rating");
               var str = e.options[e.selectedIndex].text;
               window.location.href = "rate?rate="+ str;
               
           }
           </script>
           
       
      
    </body>
</html>
