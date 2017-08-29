package snippets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import snippets.bean.Teacher;
import snippets.db.connection.DatabaseConnectionFactory;

public class TeacherDAO {
    public static List<Teacher> getTeachers() throws SQLException {
        //get connection from connection pool
        Connection connection = DatabaseConnectionFactory.getConnectionFactory().getConnection();

        List<Teacher> teacherArrayList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();

            //create SQL statement
            StringBuilder stringBuilder = new StringBuilder("select teacher.id as teacherId, teacher.name as teacherName, teacher.designation as desination ")
                .append(" from teacher ")
                .append(" order by teacher.name");

            //execute the query
            resultSet = statement.executeQuery(stringBuilder.toString());

            //iterate over result set and create Teacher objects
            //add them to teacher list
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt("teacherId"));
                teacher.setName(resultSet.getString("teacherName"));
                teacher.setDesignation(resultSet.getString("desination"));
                teacherArrayList.add(teacher);
            }
            return teacherArrayList;
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (Exception e) {}
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {}
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {}
        }
    }
    
    public static void addTeacher(Teacher teacher) throws SQLException {
        //get connection from connection pool
        Connection connection = DatabaseConnectionFactory.getConnectionFactory().getConnection();
        try {
            final String sql = "insert into teacher(name, designation) values (?, ?);";
            //create the prepared statement with an option to get auto generated keys
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //set parameters
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getDesignation());

            preparedStatement.execute();

            //Get auto-generated keys
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                teacher.setId(resultSet.getInt(1));
            }

            resultSet.close();
            preparedStatement.close();
        } finally {
            connection.close();
        }
    }
}
