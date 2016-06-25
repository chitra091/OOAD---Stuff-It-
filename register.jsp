<%-- 
    Document   : register
    Created on : Feb 12, 2016, 11:13:36 PM
    Author     : Chitra
--%>

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
        <title>Register</title>
    </head>
    <body>
        <a id="a1" href="index.html">Back</a>
        <h2 id="h22">Register</h2>
        <div id="div1" class="container">
            <form role="form" method="post" action="register">
          <div class="form-group">
            <label for="firstname">First Name:</label>
            <input type="text" class="form-control" name="firstname" placeholder="Enter first name">
          </div>
          <div class="form-group">
            <label for="lastname">Last Name:</label>
            <input type="text" class="form-control" name="lastname" placeholder="Enter last name">
          </div>
          <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" name="username" placeholder="Enter username">
          </div>
          <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" name="pwd" placeholder="Enter password">
          </div>
          <div class="form-group">
            <label for="emailid">Email ID:</label>
            <input type="email" class="form-control" name="emailid" placeholder="Enter email id">
          </div>
          <div class="form-group">
            <label for="address1">Address Line 1:</label>
            <input type="text" class="form-control" name="address1" placeholder="Enter address line 1">
          </div>
          <div class="form-group">
            <label for="address2">Address Line 2:</label>
            <input type="text" class="form-control" name="address2" placeholder="Enter address line 2">
          </div>
          <div class="form-group">
            <label for="street">Street:</label>
            <input type="text" class="form-control" name="street" placeholder="Enter street">
          </div>
          <div class="form-group">
            <label for="apt">Apartment:</label>
            <input type="text" class="form-control" name="apt" placeholder="Enter apartment number">
          </div>
          <div class="form-group">
            <label for="zipcode">Zipcode:</label>
            <input type="text" class="form-control" name="zipcode" placeholder="Enter zipcode">
          </div>
          <div class="form-group">
            <label for="city">City:</label>
            <input type="text" class="form-control" name="city" placeholder="Enter city">
          </div>
          <div class="form-group">
            <label for="state">State:</label>
            <input type="text" class="form-control" name="state" placeholder="Enter state">
          </div>
          <div class="form-group">
            <label for="country">Country:</label>
            <input type="text" class="form-control" name="country" placeholder="Enter Country">
          </div>
          <div class="form-group">
            <label for="phno">Phone number:</label>
            <input type="text" class="form-control" name="phno" placeholder="Enter phone number">
          </div>
          <button type="submit" class="btn btn-default">Submit</button>
        </form>
      </div>
    </body>
</html>
