// Transaction.java
public class Transaction {
    private String transactionId;
    private String studentId;
    private double amount;
    private String feeType; // e.g., "College", "Hostel", "Mess"
    private String semester;
    
    public Transaction(String transactionId, String studentId, double amount, String feeType, String semester) {
        this.transactionId = transactionId;
        this.studentId = studentId;
        this.amount = amount;
        this.feeType = feeType;
        this.semester = semester;
    }
    
    // Validate that the provided transaction ID, student ID, and semester are correct.
    public boolean validateTransaction(String providedId, String providedStudentId, String providedSemester) {
        return this.transactionId.equals(providedId) &&
               this.studentId.equals(providedStudentId) &&
               this.semester.equals(providedSemester);
    }
    
    public String getTransactionDetails() {
        return "Transaction ID: " + transactionId + ", Student ID: " + studentId 
                + ", Semester: " + semester + ", Amount: " + amount + ", Fee Type: " + feeType;
    }
}
