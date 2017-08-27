package snippets.bean;

import java.io.UnsupportedEncodingException;
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
        try {
            this.name = new String(name.getBytes("ISO8859_1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public int getCredits() {
        return credits;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
}
