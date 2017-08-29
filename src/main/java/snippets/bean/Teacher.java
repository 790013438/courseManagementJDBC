package snippets.bean;

import java.sql.SQLException;

import snippets.dao.TeacherDAO;

public class Teacher extends Person {
    private String designation;

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
}
