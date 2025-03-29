// FeeStructure.java
public class FeeStructure {
    private double collegeFee;
    private double hostelFee;
    private double messFee;
    
    public FeeStructure(double collegeFee, double hostelFee, double messFee) {
        this.collegeFee = collegeFee;
        this.hostelFee = hostelFee;
        this.messFee = messFee;
    }
    
    // Calculate total fee based on optional selections for hostel and mess
    public double calculateTotalFee(boolean includeHostel, boolean includeMess) {
        double total = collegeFee;
        if (includeHostel) {
            total += hostelFee;
        }
        if (includeMess) {
            total += messFee;
        }
        return total;
    }
    
    // Getters
    public double getCollegeFee() { return collegeFee; }
    public double getHostelFee() { return hostelFee; }
    public double getMessFee() { return messFee; }
}
