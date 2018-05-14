<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        
        <p align="center">This is the UserManagement Test App !</p>
        
        <h3 align="center"><a href="<%=request.getContextPath()%>/newUser" >CreateUser</a></h3>
        <h3 align="center"><a href="<%=request.getContextPath()%>/loginUser" >LoginUser</a></h3>
        <p align="center"><a href="<%=request.getContextPath()%>/getAllUserDetails">AllDetails</a></p>
        <c:if test="${not empty users}">
        <table border="1" align="center" style = "width:50%">
        <thead><tr><th>id</th><th>username</th><!-- <th>password</th> --></tr></thead>
        <tbody>
        <c:forEach var="users" items="${users}">
        <tr>
        	<td>${users.id}</td>
        	<td>${users.username}</td>
        	<!-- <td>${users.password}</td> -->
        </tr>
        </c:forEach>
        </tbody>
        </table>
          </c:if>
    </body>
</html>
