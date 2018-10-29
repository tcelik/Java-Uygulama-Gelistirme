<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="org.csystem.simplebirthdateremainder.form.BirthDateFormHandler" %><%--
  Created by IntelliJ IDEA.
  User: tugberk
  Date: 29.10.2018
  Time: 00:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Simple Birthdate Remainder</title>
      <link rel="stylesheet" href="css/login.css">
  </head>
  <body>

      <h1>jsp - burada sınıf tasarlayacağız.</h1>

    <%
        BirthDateFormHandler handler = new BirthDateFormHandler(request, response);
        handler.doPost();
    %>

  </body>
</html>
