package snippets.bean;

public class Teacher extends Person {
    private String designation;

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
