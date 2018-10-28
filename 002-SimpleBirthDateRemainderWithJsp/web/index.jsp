<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.time.LocalDate" %><%--
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
  </head>
  <body>
      <h1>jsp</h1>

    <%
        out.println("hoş geldiniz");

        try {
            //get
            String dayStr = request.getParameter("day");
            String monStr = request.getParameter("mon");
            String yearStr = request.getParameter("year");

            //toint
            int day = Integer.valueOf(dayStr);
            int mon= Integer.valueOf(monStr);
            int year = Integer.valueOf(yearStr);

            //Localdate
            LocalDate birthDate = LocalDate.of(year, mon, day);
            LocalDate now = LocalDate.now();

            LocalDate birthday = birthDate.withYear(now.getYear());

            if (birthday.equals(now))
                out.print("Doğum gününüz kutlu olsun");
            else if (birthday.isBefore(now))
                out.print("Gecmis doğum gününüz kutlu olsun");
            else
                out.print("Doğum gününüzü şimdiden kutlarız");
        }
        catch (Throwable ex) {
            out.println(ex.getMessage());
        }



    %>


  </body>
</html>
