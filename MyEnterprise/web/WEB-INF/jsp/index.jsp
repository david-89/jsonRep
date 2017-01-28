<%-- 
    Document   : index
    Created on : Jan 1, 2017, 3:30:22 PM
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create employee!</h1>
        <form:form action="create" commandName="employeeModel" method="POST"> 
            First name: <input type="text" path="firstname"/><br>
            Lat name: <input type="text" path="lastname"/><br>
            Age: <input type="text" path="age"/><br>
            Personal number: <input type="text" path="personalNumber"/><br>
            Position: <input type="text" path="position"/><br>
            <input type="submit" value="send data"/>            
        </form:form>
            
        
    </body>
</html>
