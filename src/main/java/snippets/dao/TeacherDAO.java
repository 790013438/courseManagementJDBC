package snippets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import snippets.bean.Teacher;
import snippets.db.connection.DatabaseConnectionFactory;

public class TeacherDAO {
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
