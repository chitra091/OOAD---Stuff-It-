<%-- 
    Document   : userlist
    Created on : Apr 27, 2016, 11:04:22 PM
    Author     : Avaljot
--%>

<%@page import="DAO.UserDAO"%>
<%@page import="entity.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/StuffIt1/css/popular.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <title>Delete user</title>
    </head>
    <body>
        <% 
            User reg_user = (User)session.getAttribute("user");
            List<User> ulist=new ArrayList<User>();
        %>
        <a id="a11" href="">Hi! <%= reg_user.getFirstName() %> </a>
        <a id="a1" href="admin.jsp"> Back</a>
        <h1 id="h11">
            Delete User
        </h1>
        <%
            UserDAO usr=new UserDAO();
            ulist=usr.getAllUsers();
            if(ulist.isEmpty()){
                %>
                <div id="div2">
                    Sorry no users to delete!
                </div>
                <%
            }
            else {
                for(User r: ulist){
                %>
                    <div id="div1">
                    <p id="p1">
                        Username: <%= r.getUsername() %>
                    </p>
                    <p>
                        First Name: <%= r.getFirstName() %>
                    </p>
                    <button type="button" onclick="del(<%=r.getId()%>)" value="Delete" class="btn btn-default">Delete</button>
                    <script>
                       function del(userid){
                       window.location.href = "deleteuser?r="+ userid;
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
