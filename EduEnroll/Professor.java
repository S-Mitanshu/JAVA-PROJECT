// Professor.java
import java.util.ArrayList;
import java.util.List;

public class Professor extends Person {
    private String department;
    private List<Course> assignedCourses;
    
    public Professor(String id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
        this.assignedCourses = new ArrayList<>();
    }
    
    public void assignCourse(Course course) {
        assignedCourses.add(course);
    }
    
    // Dummy method to assign a grade
    public void assignGrade(Student student, Course course, String grade) {
        System.out.println("Assigned grade " + grade + " to " + student.getName() + " for course " + course.getCourseName());
    }
    
    // Getters
    public String getDepartment() { return department; }
    public List<Course> getAssignedCourses() { return assignedCourses; }
}
