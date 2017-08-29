package snippets.bean;

import java.sql.SQLException;
import java.util.List;

import snippets.dao.CourseDAO;

public class Course {
    private int id;
    private String name;
    private int credits;
    private Teacher teacher;
    private int teacherId;

    public List<Course> getCourses() throws SQLException {
        return CourseDAO.getCourses();
    }

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

    /**
     * @return the teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    public int getTeacherId() {
        return teacherId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Course [id=" + id + ", name=" + name + ", credits=" + credits + ", teacher=" + teacher + "]";
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
