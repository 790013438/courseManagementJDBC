package snippets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import snippets.bean.Course;
import snippets.bean.Teacher;
import snippets.db.connection.DatabaseConnectionFactory;

public class CourseDAO {
    public static List<Course> getCourses() throws SQLException {
        //get connection from connection pool
        Connection connection = DatabaseConnectionFactory.getConnectionFactory().getConnection();

        List<Course> courseArrayList = new ArrayList<Course>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            
            //create SQL statement using left outer join
           StringBuilder stringBuilder = new StringBuilder("select course.id as courseId, course.name as courseName,")
               .append("course.credits as credits, Teacher.id as teacherId, Teacher.name as name,")
               .append("Teacher.designation as designation ") 
               .append(" from Course left outer join Teacher on ")
               .append("course.Teacher_id = Teacher.id ")
               .append("order by course.name");

           //execute the query
           resultSet = statement.executeQuery(stringBuilder.toString());

           //iterate over result set and create Course objects
           //add them to course list
           while (resultSet.next()) {
               Course course = new Course();
               course.setId(resultSet.getInt("courseId"));
               course.setName(resultSet.getString("courseName"));
               course.setCredits(resultSet.getInt("credits"));
               courseArrayList.add(course);

               int teacherId = resultSet.getInt("teacherId");
               //check whether teacher id was null in the table
               if (resultSet.wasNull()) {
                   //no teacher set for this course.
                   continue;
               }
               Teacher teacher = new Teacher();
               teacher.setId(teacherId);
               teacher.setName(resultSet.getString("name"));
               teacher.setDesignation(resultSet.getString("designation"));
               course.setTeacher(teacher);
           }
           
           return courseArrayList;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
            }
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {}
            try {
                connection.close();
            } catch (SQLException e) {}
        }
    }
    public static void addCourse(Course course) throws SQLException {
        //get connection from connection pool
        Connection connection = DatabaseConnectionFactory.getConnectionFactory().getConnection();
        try {
            final String sql = "insert into Course (name, credits, Teacher_id) values(?, ?, ?)";
            //create the prepared statement with an option to get auto generated keys
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //set parameters
            preparedStatement.setString(1, course.getName());
            preparedStatement.setInt(2, course.getCredits());
            if (course.getTeacherId() == 0) {
                preparedStatement.setNull(3, Types.INTEGER);
            } else {
                preparedStatement.setInt(3, course.getTeacherId());
            }

            preparedStatement.execute();

            //Get auto-generated keys
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                course.setId(resultSet.getInt(1));
            }

            resultSet.close();
            preparedStatement.close();
        } finally {
            connection.close();
        }
    }
}
