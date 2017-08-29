package snippets.bean;

import java.sql.SQLException;
import java.util.List;

import snippets.dao.TeacherDAO;

public class Teacher extends Person {
    private String designation;

    public List<Teacher> getTeachers() throws SQLException {
        return TeacherDAO.getTeachers();
    }
    public void addTeacher() throws SQLException {
        TeacherDAO.addTeacher(this);
    }

    public boolean isValidTeacher() {
        return name != null;
    }
    
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "Teacher [designation=" + designation + ", id=" + id + ", name=" + name + "]";
    }
}
