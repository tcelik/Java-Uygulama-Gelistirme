package org.csystem.simplebirthdateremainder.dao;

import org.csystem.simplebirthdateremainder.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {
    private static final PersonDao m_instance = new PersonDao();
    private String m_url, m_user, m_password;

    private PersonDao() {}

    private static Person getPerson(ResultSet rs) throws SQLException
    {
        var id = rs.getInt(1);
        var name = rs.getString(2);
        var bdate = rs.getDate(4).toLocalDate();

        return new Person(id, name, bdate);
    }

    public static PersonDao getInstance(String url, String user, String password)
    {
        m_instance.m_url = url;
        m_instance.m_user = user;
        m_instance.m_password = password;
        return m_instance;
    }

    public boolean insert(Person p)
    {

        var sqlCmd = "insert into people(name, surname, birthdate) values (?, '', ?)";

        try (Connection con = DriverManager.getConnection(m_url, m_user, m_password);
             PreparedStatement stmt = con.prepareStatement(sqlCmd)) {
            stmt.setString(1, p.getName());
            stmt.setDate(2, Date.valueOf(p.getBirthDate()));

            return stmt.executeUpdate() > 0;
        }
        catch (Throwable ex) {
            throw new RuntimeException("insert", ex);
        }
    }

    public List<Person> getAll()
    {
        var sqlCmd = "select * from people";
        var people = new ArrayList<Person>();

        try (Connection con = DriverManager.getConnection(m_url, m_user, m_password);
             PreparedStatement stmt = con.prepareStatement(sqlCmd)) {

            var rs = stmt.executeQuery();

            while (rs.next())
                people.add(getPerson(rs));
        }
        catch (Throwable ex) {
            throw new RuntimeException("insert", ex);
        }

        return people;
    }
}
