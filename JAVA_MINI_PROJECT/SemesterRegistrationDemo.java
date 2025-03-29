// SemesterRegistrationDemo.java
import java.util.List;
import java.util.Scanner;

public class SemesterRegistrationDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = Admin.getInstance();
        
        // Admin privately assigns courses for a specific semester (e.g., "Fall2025")
        String semester = "Fall2025";
        Professor prof1 = new Professor("P001", "Dr. Smith", "dr.smith@example.com", "Computer Science");
        Professor prof2 = new Professor("P002", "Dr. Jones", "dr.jones@example.com", "Mathematics");
        
        // Creating courses for the semester and assigning them to professors.
        Course course1 = new Course("CSE101", "Introduction to Computer Science", 3, semester);
        Course course2 = new Course("CSE102", "Data Structures", 4, semester);
        Course course3 = new Course("MAT101", "Calculus I", 3, semester);
        Course course4 = new Course("PHY101", "Physics I", 4, semester);
        Course course5 = new Course("ENG101", "English Composition", 2, semester);
        Course course6 = new Course("HIS101", "World History", 3, semester);
        Course course7 = new Course("CSE103", "Algorithms", 4, semester);
        
        // Assign courses to professors privately.
        prof1.assignCourse(course1);
        prof1.assignCourse(course2);
        prof1.assignCourse(course7);
        prof2.assignCourse(course3);
        prof2.assignCourse(course4);
        prof2.assignCourse(course5);
        prof2.assignCourse(course6);
        
        // Add courses to admin's available course list.
        admin.addCourse(course1);
        admin.addCourse(course2);
        admin.addCourse(course3);
        admin.addCourse(course4);
        admin.addCourse(course5);
        admin.addCourse(course6);
        admin.addCourse(course7);
        
        // Now a student registers for the new semester.
        System.out.println("Enter student details for registration in " + semester + ":");
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine().trim();
        System.out.print("Name: ");
        String studentName = scanner.nextLine().trim();
        System.out.print("Email: ");
        String studentEmail = scanner.nextLine().trim();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        
        Student student = new Student(studentId, studentName, studentEmail, age);
        admin.addStudent(student);
        
        // List available courses for the semester.
        List<Course> availableCourses = admin.getCoursesForSemester(semester);
        System.out.println("\nAvailable Courses for " + semester + ":");
        for (Course course : availableCourses) {
            System.out.println(course.getCourseId() + " - " + course.getCourseName() + " (" + course.getCredits() + " credits)");
        }
        
        // Student selects courses.
        System.out.println("\nSelect at least 5 courses by entering the course IDs separated by commas:");
        String[] selectedCourseIds = scanner.nextLine().trim().split("\\s*,\\s*");
        
        if (selectedCourseIds.length < 5) {
            System.out.println("Error: You must select at least 5 courses. Registration aborted.");
        } else {
            // Register the student for the selected courses if they exist in the available list.
            int registeredCount = 0;
            for (String courseId : selectedCourseIds) {
                for (Course course : availableCourses) {
                    if (course.getCourseId().equalsIgnoreCase(courseId)) {
                        student.registerCourse(course);
                        course.addStudent(student);
                        registeredCount++;
                        break;
                    }
                }
            }
            if (registeredCount < 5) {
                System.out.println("Error: Not all selected courses are valid. Registration aborted.");
            } else {
                System.out.println("Student " + student.getName() + " has been successfully registered for the following courses:");
                for (Course c : student.getEnrolledCourses()) {
                    System.out.println("- " + c.getCourseName() + " (" + c.getCourseId() + ")");
                }
            }
        }
        
        scanner.close();
    }
}
