// Course.java
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseId;
    private String courseName;
    private int credits;
    private String semester;  // New field to indicate which semester this course belongs to.
    private List<Student> enrolledStudents;
    
    public Course(String courseId, String courseName, int credits, String semester) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.semester = semester;
        this.enrolledStudents = new ArrayList<>();
    }
    
    public void addStudent(Student student) {
        enrolledStudents.add(student);
    }
    
    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }
    
    // Getters
    public String getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public int getCredits() { return credits; }
    public String getSemester() { return semester; }
    public List<Student> getEnrolledStudents() { return enrolledStudents; }
}
