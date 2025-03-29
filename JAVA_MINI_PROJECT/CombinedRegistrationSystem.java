// CombinedRegistrationSystem.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CombinedRegistrationSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = Admin.getInstance();
        String semester = "Fall2025";

        // ---------------------------
        // ADMIN PHASE: Professor Assignment & Course Setup
        // ---------------------------
        // Create professors
        Professor prof1 = new Professor("P001", "Dr. Smith", "dr.smith@example.com", "Computer Science");
        Professor prof2 = new Professor("P002", "Dr. Jones", "dr.jones@example.com", "Mathematics");

        // Create courses for the semester
        Course course1 = new Course("CSE101", "Introduction to Computer Science", 3, semester);
        Course course2 = new Course("CSE102", "Data Structures", 4, semester);
        Course course3 = new Course("MAT101", "Calculus I", 3, semester);
        Course course4 = new Course("PHY101", "Physics I", 4, semester);
        Course course5 = new Course("ENG101", "English Composition", 2, semester);
        Course course6 = new Course("HIS101", "World History", 3, semester);
        Course course7 = new Course("CSE103", "Algorithms", 4, semester);
        
        // Add courses to admin's available course list.
        admin.addCourse(course1);
        admin.addCourse(course2);
        admin.addCourse(course3);
        admin.addCourse(course4);
        admin.addCourse(course5);
        admin.addCourse(course6);
        admin.addCourse(course7);

        // Create lists for professor assignments.
        List<Course> coursesForProf1 = new ArrayList<>();
        coursesForProf1.add(course1);
        coursesForProf1.add(course2);
        coursesForProf1.add(course7);
        
        List<Course> coursesForProf2 = new ArrayList<>();
        coursesForProf2.add(course3);
        coursesForProf2.add(course4);
        coursesForProf2.add(course5);
        coursesForProf2.add(course6);
        
        // Use the ProfessorAssignment class to assign courses privately.
        ProfessorAssignment pa1 = new ProfessorAssignment(prof1, coursesForProf1, semester);
        ProfessorAssignment pa2 = new ProfessorAssignment(prof2, coursesForProf2, semester);
        pa1.assignCourses();
        pa2.assignCourses();

        // ---------------------------
        // STUDENT REGISTRATION PHASE - Transaction Check First
        // ---------------------------
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

        // Define a fee structure (example values) and calculate fee.
        FeeStructure feeStructure = new FeeStructure(5000, 2000, 1500);
        // For this example, assume no hostel and mess fees.
        double totalFee = feeStructure.calculateTotalFee(false, false);
        System.out.println("\nTotal fee to be paid for " + semester + ": " + totalFee);

        // Simulate transaction checking.
        String validTransactionId = UUID.randomUUID().toString();
        System.out.println("Valid Transaction ID (for testing): " + validTransactionId);
        
        System.out.print("Enter the Transaction ID: ");
        String providedTransactionId = scanner.nextLine().trim();
        
        System.out.print("Confirm the Semester for the transaction (e.g., Fall2025): ");
        String providedSemester = scanner.nextLine().trim();
        
        // Create a transaction record.
        Transaction transaction = new Transaction(validTransactionId, student.getId(), totalFee, "Fee Payment", providedSemester);
        
        if (transaction.validateTransaction(providedTransactionId, student.getId(), providedSemester)) {
            student.addTransaction(transaction);
            System.out.println("Transaction validated successfully!");
        } else {
            System.out.println("Transaction validation failed! Registration aborted.");
            scanner.close();
            return;
        }
        
        // ---------------------------
        // STUDENT COURSE SELECTION PHASE
        // ---------------------------
        // List available courses for the semester.
        List<Course> availableCourses = admin.getCoursesForSemester(semester);
        System.out.println("\nAvailable Courses for " + semester + ":");
        for (Course course : availableCourses) {
            System.out.println(course.getCourseId() + " - " + course.getCourseName() +
                               " (" + course.getCredits() + " credits)");
        }
        
        // Student selects courses.
        System.out.println("\nSelect at least 5 courses by entering the course IDs separated by commas:");
        String[] selectedCourseIds = scanner.nextLine().trim().split("\\s*,\\s*");
        
        if (selectedCourseIds.length < 5) {
            System.out.println("Error: You must select at least 5 courses. Registration aborted.");
            scanner.close();
            return;
        }
        
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
            System.out.println("Student Registration Successfull.");
            System.out.println("Student " + student.getName() + " has been successfully registered for the following courses:");
            for (Course c : student.getEnrolledCourses()) {
                System.out.println("- " + c.getCourseName() + " (" + c.getCourseId() + ")");
            }
        }
        
        scanner.close();
    }
}
