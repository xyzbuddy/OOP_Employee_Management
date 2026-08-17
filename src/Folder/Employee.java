package Folder;


public abstract class Employee implements Payable {

    //==========================
    // Fields (private = Encapsulation)
    //==========================
    private int id;
    private String name;
    private String gender;

    //==========================
    // Constructor
    //==========================
    public Employee(int id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    //==========================
    // Getters and Setters
    //==========================
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    // Base salary:
    //   Permanent  -> Basic Salary
    //   Part-Time  -> Working Hours * Rate Per Hour
    public abstract double getBaseSalary();

    // Bonus:
    //   Permanent  -> the amount the user typed (can be 0)
    //   Part-Time  -> always 0
    public abstract double getBonus();

    // Type name shown in the tables: "Permanent" or "Part-Time"
    public abstract String getType();

    @Override
    public double getTotalSalary() {
        return getBaseSalary() + getBonus();
    }
}
