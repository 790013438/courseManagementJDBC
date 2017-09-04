package snippets.bean;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import snippets.dao.CourseDAO;

public class CourseTest {

    @Test
    public void testAddStudent() {
        //create course
        Course course = new Course();
        course.setId(1);
        course.setName("course1");
        course.setMaxStudents(2);
        //create student
        Student student = new Student();
        student.setName("Student1");
        student.setId(1);
        //now add student
        CourseDAO courseDAO = Mockito.mock(CourseDAO.class);
        try {
            Mockito.when(courseDAO.getNumStudentsInCourse(1)).thenReturn(60);
            Mockito.doNothing().when(courseDAO).enrollStudentInCourse(1, 1);
        } catch (SQLException e) {
            Assert.fail(e.getMessage());
        }
        try {
            course.addStudent(student);
        } catch (Exception e) {
            Assert.fail(e.getMessage() + "23:)");
        }
    }

    @Test
    public void testIsValidCourse() {
        Course course = new Course();
        //First validate without any values set
        Assert.assertFalse(course.isValidCourse());
        //set name
        course.setName("course1");
        Assert.assertFalse(course.isValidCourse());
        //set zero credits
        course.setCredits(0);
        Assert.assertFalse(course.isValidCourse());
        //now set valid credits
        course.setCredits(4);
        Assert.assertTrue(course.isValidCourse());
        //set empty course name
        course.setName("");
        Assert.assertFalse(course.isValidCourse());
    }

}
