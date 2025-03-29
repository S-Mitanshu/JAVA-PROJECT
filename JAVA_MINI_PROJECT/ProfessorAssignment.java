// ProfessorAssignment.java
import java.util.List;

public class ProfessorAssignment {
    private Professor professor;
    private List<Course> courses;
    private String semester;
    
    public ProfessorAssignment(Professor professor, List<Course> courses, String semester) {
        this.professor = professor;
        this.courses = courses;
        this.semester = semester;
    }
    
    // Assigns all courses to the professor (private assignment)
    public void assignCourses() {
        for (Course course : courses) {
            // Only assign if the course belongs to the specified semester.
            if(course.getSemester().equalsIgnoreCase(semester)) {
                professor.assignCourse(course);
            }
        }
    }
    
    public Professor getProfessor() {
        return professor;
    }
    
    public List<Course> getCourses() {
        return courses;
    }
    
    public String getSemester() {
        return semester;
    }
}
