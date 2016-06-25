<%-- 
    Document   : addrecipe
    Created on : Mar 19, 2016, 12:52:20 AM
    Author     : Chitra
--%>

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
        <title>Add recipe</title>
    </head>
    <body>
        <% 
            User reg_user = (User)session.getAttribute("user");
        %>
        <a id="a11" href="">Hi! <%= reg_user.getFirstName() %> </a>
        <a id="a1" href="login_result.jsp"> Back</a>
        <h1 id="h11">
            Add Recipe
        </h1>
        <div id="div1" class="container">
            <form role="form" method="post" action="addrecipe">
          <div class="form-group">
            <label for="recipename">Recipe Name:</label>
            <input type="text" class="form-control" name="recipename" placeholder="Enter recipe name">
          </div>
          <div class="form-group">
            <label for="type">Type:</label>
            <input type="text" class="form-control" name="type" placeholder="Enter type">
          </div>
          <div class="form-group">
            <label for="rating">Rating:</label>
            <input type="text" class="form-control" name="rating" placeholder="Enter Rating">
          </div>
          <div class="form-group">
            <label for="process">Process:</label>
            <textarea class="form-control" rows="5" name="process" placeholder="Enter process"></textarea>
          </div>
          <div id="ingadd" class="form-group">
            <label for="ing1">Ingredient:</label>
          </div>
          <input type="button" id="ingr" value="Add Ingredient" onclick="addrow()" class="btn btn-default">
          <button type="submit" class="btn btn-default">Submit</button>
        </form>
      </div>
        <script type="text/javascript">
            function addrow() {
				//var ingrd = document.getElementById("ingadd");
				//ingrd.innerHTML  += '<input type="text" class="form-control" name="ing" placeholder="Enter Ingredient">';
                                var tag = document.createElement('input'); // Create a `input` element,
                                tag.setAttribute('type', 'text');          // Set it's `type` attribute,
                                tag.setAttribute('name', 'ing');               // Set it's `name` attribute,
                                tag.setAttribute('class','form-control');
                                var br = document.createElement('br');     // Create a `br` element,

                                var y = document.getElementById("ingadd");      // "Get" the `y` element,
                                y.appendChild(tag);                        // Append the input to `y`,
                                y.appendChild(br);                         // Append the br to `y`.
                                //i++;
			}
        </script>
    </body>
</html>
