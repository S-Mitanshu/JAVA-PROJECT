// Student.java
import java.util.ArrayList;
import java.util.List;


public class Student extends Person {
    private int age;
    private List<Course> enrolledCourses;
    private List<Transaction> transactionHistory;
    
    public Student(String id, String name, String email, int age) {
        super(id, name, email);
        this.age = age;
        this.enrolledCourses = new ArrayList<>();
        this.transactionHistory = new ArrayList<>();
    }
    
    // Methods for course registration
    public void registerCourse(Course course) {
        enrolledCourses.add(course);
    }
    
    public void dropCourse(Course course) {
        enrolledCourses.remove(course);
    }
    
    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
    
    // Transaction history management
    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }
    
    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
    
    public int getAge() {
        return age;
    }
}
