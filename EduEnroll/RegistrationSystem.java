// package JAVA MINI PROJECT.COLLEGE REGISTRATION SYSTEM;

// RegistrationSystem.java
import java.util.Scanner;
import java.util.UUID;

public class RegistrationSystem {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = Admin.getInstance();
        
        // Create fee structure with sample fee values.
        FeeStructure feeStructure = new FeeStructure(5000, 2000, 1500);
        
        // For demonstration, create a sample student using user input.
        System.out.println("Enter student details:");
        System.out.print("ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        
        Student student = new Student(id, name, email, age);
        admin.addStudent(student);
        
        // Ask the student if they opt for hostel and mess.
        System.out.print("Do you want to opt for hostel? (yes/no): ");
        boolean includeHostel = scanner.nextLine().trim().equalsIgnoreCase("yes");
        System.out.print("Do you want to opt for mess? (yes/no): ");
        boolean includeMess = scanner.nextLine().trim().equalsIgnoreCase("yes");
        
        // Calculate the total fee based on the fee structure and selections.
        double totalFee = feeStructure.calculateTotalFee(includeHostel, includeMess);
        System.out.println("Total fee to be paid: " + totalFee);
        
        // Simulate the transaction validation:
        // In a real-world scenario, the valid transaction details would come from an external system.
        // Here, we generate a valid transaction ID for testing purposes.
        String validTransactionId = UUID.randomUUID().toString();
        System.out.println("Valid Transaction ID (for testing purposes): " + validTransactionId);
        
        System.out.print("Enter the Transaction ID: ");
        String providedTransactionId = scanner.nextLine().trim();
        
        System.out.print("Enter the Semester (e.g., Fall2025): ");
        String providedSemester = scanner.nextLine().trim();
        
        // Create a transaction record with the generated valid transaction ID.
        Transaction transaction = new Transaction(validTransactionId, student.getId(), totalFee, "Fee Payment", providedSemester);
        
        // Validate the transaction using the provided details.
        if(transaction.validateTransaction(providedTransactionId, student.getId(), providedSemester)) {
            student.addTransaction(transaction);
            System.out.println("Transaction validated successfully!");
            System.out.println("Student " + student.getName() + " has been successfully registered.");
        } else {
            System.out.println("Transaction validation failed! Registration aborted.");
        }
        
        scanner.close();
    }
}
