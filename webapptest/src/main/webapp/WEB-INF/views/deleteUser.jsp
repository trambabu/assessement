<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Userpage</title>
    </head>
    <body>
       
        <p align="center">Delete Page</p>
        <h3 align="center"><a href="<%=request.getContextPath()%>/" >Home</a></h3>
        
        <h3 align="center">Please provide username to delete the record</h3>
        <form name="deleteUser" action="/webapptest/deleteUserDetailsByUsername"  method="post">
        <table border="1" align="center" style = "width:50%">
        <thead>
        <tr><th colspan="2" align="center"> Enter username to delete</th></tr></thead>
        <tbody><tr><td>Enter username</td><td><input type ="text" name="username"></td></tr>
        		<tr><td colspan="2" align="center"><input type="submit" value="Delete"></td></tr>
        </tbody>
        </table>
        </form>
        <h3 align="center"><a href="<%=request.getContextPath()%>/" >Home</a></h3>
    </body>
</html>
