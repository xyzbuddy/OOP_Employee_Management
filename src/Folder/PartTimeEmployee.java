package Folder;

//==============================================================
// PartTimeEmployee (child of Employee)
//--------------------------------------------------------------
// Fields:
//   Working Hours
//   Rate Per Hour
//
// Rules:
//   Base Salary  = Working Hours * Rate Per Hour
//   Bonus        = 0  (part-time employees get no bonus)
//   Total Salary = Base Salary + 0   (from parent getTotalSalary)
//==============================================================
public class PartTimeEmployee extends Employee {

    //==========================
    // Extra fields for Part-Time
    //==========================
    private double workingHours;
    private double ratePerHour;

    //==========================
    // Constructor
    //==========================
    public PartTimeEmployee(int id, String name, String gender,
                            double workingHours, double ratePerHour) {
        super(id, name, gender);   // send common fields to parent
        this.workingHours = workingHours;
        this.ratePerHour = ratePerHour;
    }

    //==========================
    // Getters and Setters
    //==========================
    public double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
    }

    public double getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    //==========================
    // Overridden methods (Polymorphism)
    //==========================
    @Override
    public double getBaseSalary() {
        return workingHours * ratePerHour;   // salary rule
    }

    @Override
    public double getBonus() {
        return 0;   // part-time = no bonus
    }

    @Override
    public String getType() {
        return "Part-Time";
    }
}
