package Folder;


//==============================================================
public class PermanentEmployee extends Employee {

    //==========================
    // Extra fields for Permanent
    //==========================
    private double basicSalary;
    private double bonus;

    //==========================
    // Constructor
    //==========================
    public PermanentEmployee(int id, String name, String gender,
                             double basicSalary, double bonus) {
        super(id, name, gender);   // send common fields to parent
        this.basicSalary = basicSalary;
        this.bonus = bonus;
    }

    //==========================
    // Getters and Setters
    //==========================
    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    //==========================
    // Overridden methods (Polymorphism)
    //==========================
    @Override
    public double getBaseSalary() {
        return basicSalary;
    }

    @Override
    public double getBonus() {
        return bonus;   // whatever amount the user gave (0 if none)
    }

    @Override
    public String getType() {
        return "Permanent";
    }
}
