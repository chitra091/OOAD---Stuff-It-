

<%@page import="java.util.ArrayList"%>
<%@page import="com.stuffit.www.data.DataManager"%>
<%@page import="com.stuffit.www.data.objects.Recipe"%>
<%@page import="java.util.List"%>
<%@page import="entity.User"%>
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
        <title>Delete</title>
    </head>
    <body>
        <% 
            User reg_user = (User)session.getAttribute("user");
        %>
        <a id="a11" href="">Hi! <%= reg_user.getFirstName() %> </a>
        <a id="a1" href="index.html"> Back</a>
        <h1 id="h11">
            Delete Recipe
        </h1>
        <%
            List<Recipe> rp = new ArrayList<Recipe>();
            DataManager x=new DataManager();
            rp=x.getRecipes();
            if(rp.isEmpty()){
                %>
                <div id="div2">
                    Sorry no recipes to delete!
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
                    <button type="button" onclick="del('<%=r.getName()%>')" value="Delete" class="btn btn-default">Delete</button>
                    <script>
                       function del(recipe){
                       window.location.href = "delete?r="+recipe;
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
