<%-- 
    Document   : search
    Created on : Feb 8, 2016, 3:44:36 PM
    Author     : Avaljot
--%>

<%@page import="entity.User"%>
<%@page import="com.stuffit.www.data.objects.Ingredient"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.stuffit.www.data.DataManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body>
        <% 
            User reg_user = (User)session.getAttribute("user"); 
            if(reg_user!=null){
                %> 
                    <a id="a11" href="">Hi! <%= reg_user.getFirstName() %> </a>
                <%
            }
        %>
        <div>
        <div class="col-md-3" style="background-color: greenyellow; font-family: monospace" float:left>
            <center> <h2>Search Your Recipe!!</h2>
                <br><br><br>
            </center>
            <h3>Add Ingredients:</h3>
            
            <div style="width:60%;align-items: center; box-shadow:  olive"> <!-- t was 20 % -->
                       
                <b><p id="demo" >
                          
                    </p></b>
            </div>
           
               <div style="width: 60%"><!-- t was 60 % -->
                   <% List<Ingredient> Ing=new ArrayList<Ingredient> ();
                       DataManager x=new DataManager();
                       Ing=x.getAllIngredients();
                   %>
                   
                   <select class="form-control" onchange="list_of_ingr()" id="ingr">
                       <option>Choose Ingredients</option>
                       <% 
                           for(Ingredient i : Ing)
                           {
                               %> 
                       <option value="<%=i.getIngredientId()%>"><%=i.getName()%></option>
                       <%}
                       %>
                   </select>
                   <script>
                       var list=[];
                       var listofId=[]
                       function list_of_ingr(){
                       var e = document.getElementById("ingr");
                       var strIng = e.options[e.selectedIndex].text;
                       var strId = e.options[e.selectedIndex].value;
                       if(strIng!=="Choose Ingredients"){
                       list.push(strIng);
                       listofId.push(strId);
                   }
                       var text="";
                       for(i=0;i<list.length;i++)
                       {
                           text += list[i] + "<br>";
                       }
                       document.getElementById("demo").innerHTML = text;
                   }
                   
                   var list2=[];
                       function list_of_restrict(){
                       var e = document.getElementById("restrict");
                       var strIng = e.options[e.selectedIndex].value;
                       if(strIng!=="Choose Restrictions"){
                       list2.push(strIng);
                   }
                       var text="";
                       for(i=0;i<list2.length;i++)
                       {
                           text += list2[i] + "<br>";
                       }
                       document.getElementById("demo1").innerHTML = text;
                   }
                   
                   function srch(){
                       window.location.href = "searchservlet?l="+listofId;
                       //window.location.replace("search_serv?l="+list);
                   }
                   </script>
                           
            </div>
           
        <br>
            <h3>Restrictions:</h3>
            
               <b><p id="demo1" >
                          
                    </p></b>
                 <div style="width: 60%;">
                    <select class="form-control" onchange="list_of_restrict()" id="restrict">
                        <option>Choose Restrictions</option>
                        <option>Meat</option>
                        <option>Poultry</option>
                        <option>Beef</option>
                        <option>Fish</option>
  
</select>
                </div>
            <br><br><br>
            <% 
                reg_user = (User)session.getAttribute("user"); 
            if(reg_user!=null){
                %> 
                    <a href="login_result.jsp" class="btn btn-success" role="button">Back</a>
                <%
            }
            else{
                %> 
                <a href="index.html" class="btn btn-success" role="button">Back</a>
                <%
            }
            %>
            <button onclick="srch()" type="button" class="btn btn-success">Search</button>
        </div>
        </div>
    </body>
</html>
