package snippets.bean;

import java.sql.SQLException;

import snippets.dao.CourseDAO;

public class Course {
    private int id;
    private String name;
    private int credits;

    public void addCourse() throws SQLException {
        CourseDAO.addCourse(this);
    }
    
    public boolean isValidCourse() {
        return name != null && credits !=0;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCredits() {
        return credits;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
}
