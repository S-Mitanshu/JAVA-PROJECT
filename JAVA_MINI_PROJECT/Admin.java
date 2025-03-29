// Admin.java
import java.util.ArrayList;
import java.util.List;

public class Admin {
    private static Admin instance = null;
    private List<Student> students;
    private List<Course> courses;
    
    // Private constructor for Singleton Pattern
    private Admin() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }
    
    // Singleton instance
    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }
    
    public void addStudent(Student student) {
        students.add(student);
    }
    
    public void addCourse(Course course) {
        courses.add(course);
    }
    
    public List<Student> getStudents() {
        return students;
    }
    
    public List<Course> getCourses() {
        return courses;
    }
    
    // Retrieve courses available for a specific semester.
    public List<Course> getCoursesForSemester(String semester) {
        List<Course> semesterCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getSemester().equalsIgnoreCase(semester)) {
                semesterCourses.add(course);
            }
        }
        return semesterCourses;
    }
    
    // Generate a simple report.
    public void generateReport() {
        System.out.println("Students Registered: " + students.size());
        System.out.println("Courses Offered: " + courses.size());
    }
}
