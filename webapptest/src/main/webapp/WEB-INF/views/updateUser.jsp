<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update UserPage</title>
    </head>
    <body>
       
        <p align="center">Update Password of the User!</p>
        <h3 align="center">Please provide username and new Password to reset</h3>
        <form name="updateUserPassword" action="/webapptest/updateUserDetailsByUsername"  method="post">
        <table border="1" align="center" style = "width:50%">
        <thead><tr><th colspan="2" align="center">Enter username and password for reset</th></tr></thead>
        <tbody><tr><td>Enter username</td><td><input type ="text" name="username" style="width: 140px; "></td></tr>
        		<tr><td>Enter new Password</td><td><input type ="password" name="password" style="width: 139px; "></td></tr>
        		<tr><td colspan="2" align="center"><input type="submit" value="Update"></td></tr>
        </tbody>
        </table>
        <p align="center"><c:out value="${updateStatus}"></c:out></p>
        </form>
			<h3 align="center"><a href="<%=request.getContextPath()%>/" >Home</a></h3>        
    </body>
</html>
