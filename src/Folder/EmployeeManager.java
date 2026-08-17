package Folder;

import java.util.ArrayList;

//==============================================================
public class EmployeeManager {

    
    private ArrayList<Employee> employees = new ArrayList<>();

    //==========================================================
    // CHECK IF AN EMPLOYEE ID ALREADY EXISTS
    //==========================================================
    public boolean employeeExists(int id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }

    //==========================================================
    // ADD A PERMANENT EMPLOYEE
    // returns true  -> added successfully
    // returns false -> id already exists
    //==========================================================
    public boolean addPermanentEmployee(int id, String name, String gender,
                                        double basicSalary, double bonus) {
        if (employeeExists(id)) {
            return false;
        }
        PermanentEmployee p = new PermanentEmployee(id, name, gender, basicSalary, bonus);
        employees.add(p);
        return true;
    }

    //==========================================================
    // ADD A PART-TIME EMPLOYEE
    //==========================================================
    public boolean addPartTimeEmployee(int id, String name, String gender,
                                       double workingHours, double ratePerHour) {
        if (employeeExists(id)) {
            return false;
        }
        PartTimeEmployee pt = new PartTimeEmployee(id, name, gender, workingHours, ratePerHour);
        employees.add(pt);
        return true;
    }

    //==========================================================
    // SEARCH AN EMPLOYEE BY ID
    // returns the Employee object, or null if not found
    //==========================================================
    public Employee searchEmployee(int id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                return employees.get(i);
            }
        }
        return null;
    }

    //==========================================================
    // UPDATE A PERMANENT EMPLOYEE
    // returns false if the id is missing or is not a Permanent one
    //==========================================================
    public boolean updatePermanentEmployee(int id, String name, String gender,
                                           double basicSalary, double bonus) {
        Employee e = searchEmployee(id);
        if (e == null) {
            return false;
        }
        if (!(e instanceof PermanentEmployee)) {
            return false;
        }
        PermanentEmployee p = (PermanentEmployee) e;
        p.setName(name);
        p.setGender(gender);
        p.setBasicSalary(basicSalary);
        p.setBonus(bonus);
        return true;
    }

    //==========================================================
    // UPDATE A PART-TIME EMPLOYEE
    //==========================================================
    public boolean updatePartTimeEmployee(int id, String name, String gender,
                                          double workingHours, double ratePerHour) {
        Employee e = searchEmployee(id);
        if (e == null) {
            return false;
        }
        if (!(e instanceof PartTimeEmployee)) {
            return false;
        }
        PartTimeEmployee pt = (PartTimeEmployee) e;
        pt.setName(name);
        pt.setGender(gender);
        pt.setWorkingHours(workingHours);
        pt.setRatePerHour(ratePerHour);
        return true;
    }

    //==========================================================
    // DELETE AN EMPLOYEE BY ID
    //==========================================================
    public boolean deleteEmployee(int id) {
        Employee e = searchEmployee(id);
        if (e == null) {
            return false;
        }
        employees.remove(e);
        return true;
    }

    //==========================================================
    // GET FULL DETAILS OF ONE EMPLOYEE AS TEXT
    // (used where we want to show a plain text block)
    //==========================================================
    public String getEmployeeDetails(int id) {
        Employee e = searchEmployee(id);
        if (e == null) {
            return "Employee Not Found";
        }

        String details = "";
        details += "ID           : " + e.getId() + "\n";
        details += "Name         : " + e.getName() + "\n";
        details += "Gender       : " + e.getGender() + "\n";
        details += "Type         : " + e.getType() + " Employee\n";

        if (e instanceof PermanentEmployee) {
            details += "Basic Salary : " + String.format("%.2f", e.getBaseSalary()) + "\n";
            details += "Bonus        : " + String.format("%.2f", e.getBonus()) + "\n";
        } else {
            PartTimeEmployee pt = (PartTimeEmployee) e;
            details += "Working Hours: " + String.format("%.2f", pt.getWorkingHours()) + "\n";
            details += "Rate Per Hour: " + String.format("%.2f", pt.getRatePerHour()) + "\n";
            details += "Salary       : " + String.format("%.2f", pt.getBaseSalary()) + "\n";
        }

        details += "Total Salary : " + String.format("%.2f", e.getTotalSalary());
        return details;
    }

    //==========================================================
    // GET THE WHOLE LIST (used by the View screen)
    //==========================================================
    public ArrayList<Employee> getEmployeeList() {
        return employees;
    }

    //==========================================================
    // HOW MANY EMPLOYEES DO WE HAVE
    //==========================================================
    public int getTotalEmployee() {
        return employees.size();
    }
}
