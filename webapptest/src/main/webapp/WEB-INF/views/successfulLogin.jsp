<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Successful Login</title>
    </head>
    <body>
        <p><c:out value="${addstatus}"></c:out></p>
        <p align="center"><c:out value="${username}"/> Successfully Logged in!</p>
        
       
        <h3 align="center"><a href="<%=request.getContextPath()%>/updateUser" >UpdateUser</a></h3>
        <h3 align="center"><a href="<%=request.getContextPath()%>/deleteUser" >DeleteUser</a></h3>
         <h3 align="center"><a href="<%=request.getContextPath()%>/" >Home</a></h3>
        
        
        
    </body>
</html>
