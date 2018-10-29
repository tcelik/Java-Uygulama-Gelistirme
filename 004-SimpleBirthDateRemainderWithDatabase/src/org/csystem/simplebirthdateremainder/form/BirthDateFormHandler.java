package org.csystem.simplebirthdateremainder.form;

import org.csystem.simplebirthdateremainder.dao.PersonDao;
import org.csystem.simplebirthdateremainder.entity.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class BirthDateFormHandler {
    private static final String URL = "jdbc:postgresql://localhost:5432/peopledb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345";

    private HttpServletRequest m_request;
    private HttpServletResponse m_response;
    private Person m_person;

    private void validate(String name, String dayStr, String monStr, String yearStr)
    {
        if (name == null || dayStr == null || monStr == null || yearStr == null)
            throw new SecurityException("Security Exception");

        int day = Integer.parseInt(dayStr);
        int mon = Integer.parseInt(monStr);
        int year = Integer.parseInt(yearStr);

        m_person = new Person(0, name, day, mon, year);
    }

    private  void listPeople() throws IOException
    {
        var people = PersonDao.getInstance(URL, USER, PASSWORD).getAll();

        StringBuilder str = new StringBuilder("<ol>");

        for (var p : people)
            str.append(String.format("<li>%s</li>", p));

        str.append("</ol>");

        m_response.getWriter().print(str);
    }


    private void printMessage() throws IOException
    {
        var bdate = m_person.getBirthDate();
        var now = LocalDate.now();
        var bday = bdate.withYear(now.getYear());

        var fmt = "<h2>%s</h2>";
        var msg = "";

        if (bday.isAfter(now))
            msg = String.format(fmt, "Doğum gününüzü şimdiden kutlarız");
        else if (bday.isEqual(now))
            msg = String.format(fmt, "Doğum gününüz kutlu olsun");
        else
            msg = String.format(fmt, "Geçmiş doğum gününüz kutlu olsun");

        m_response.getWriter().print(msg);
    }

    public BirthDateFormHandler(HttpServletRequest request, HttpServletResponse response)
    {
        m_request = request;
        m_response = response;
    }

    public HttpServletRequest getRequest()
    {
        return m_request;
    }

    public void setRequest(HttpServletRequest request)
    {
        m_request = request;
    }

    public HttpServletResponse getResponse()
    {
        return m_response;
    }

    public void setResponse(HttpServletResponse response)
    {
        m_response = response;
    }

    public void doPost()
    {
        PrintWriter out = null;

        try {
            out = m_response.getWriter();

            var name = m_request.getParameter("name");
            var dayStr = m_request.getParameter("day");
            var monStr = m_request.getParameter("mon");
            var yearStr = m_request.getParameter("year");

            this.validate(name, dayStr, monStr, yearStr);

            PersonDao.getInstance(URL, USER, PASSWORD).insert(m_person);
            printMessage();
            listPeople();
        }
        catch (Throwable ex) {
            out.printf("<h2>Exception:%s</h2>", ex.getMessage());
        }
    }
}
