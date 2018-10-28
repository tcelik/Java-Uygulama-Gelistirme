package org.csystem.simplebirthdateremainder.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet("/BirthDateRemainderServlet")
public class BirthDateRemainderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();

        //get
        String dayStr = request.getParameter("day");
        String monthStr = request.getParameter("mon");
        String yearStr = request.getParameter("year");

        //to int
        int day = Integer.valueOf(dayStr);
        int month = Integer.valueOf(monthStr);
        int year = Integer.valueOf(yearStr);

        //birthdate
        LocalDate birthDate = LocalDate.of(year, month, day);

        //LocalDateTime alıyorum
        LocalDate now = LocalDate.now();


        LocalDate birthday = birthDate.withYear(now.getYear());

        if (birthday.equals(now))
            out.print("Doğum gününüz kutlu olsun");
        else if (birthday.isBefore(now))
            out.print("Gecmis doğum gününüz kutlu olsun");
        else
            out.print("Doğum gününüzü şimdiden kutlarız");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}
