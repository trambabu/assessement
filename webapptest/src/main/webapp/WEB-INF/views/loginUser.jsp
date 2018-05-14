<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Userpage</title>
    </head>
    <body>
        
        <p align="center">Login page!</p>
        
       <p align="center"><c:if test="${status=='failure'}"> Username / Password are wrong please try again !</c:if></p>
       
        <form name="login" action="/webapptest/login" method="post">
        <table border="1" align="center" style = "width:50%">
        		<tr><th>Enter username</th><td><input type ="text" name="username" style="width: 140px; "></td></tr>
        		<tr><th>Enter password</th><td><input type ="password" name="password" style="width: 140px; "></td></tr>
        		<tr><td colspan="2" align="center"><input type="submit"></td></tr>
        		
        </table>           
        </form>
        <h3 align="center"><a href="<%=request.getContextPath()%>/" >Home</a></h3>
        
        
        
    </body>
</html>
