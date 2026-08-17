# 👥 Employee Management System (Java Swing + OOP)

A desktop-based **Employee Management System** built using **Java (Swing GUI)** adhering strictly to **Object-Oriented Programming (OOP)** principles.

---

## 🌟 Key Features

* **🔐 Authentication System**:
  * Secure Admin Login (Default: `username: admin`, `password: 1234`).
  * *Remember Me* feature saved using Java Preferences API.
  * Show/Hide password toggle & field placeholders.
* **➕ Add Employee**:
  * Supports two employee categories: **Permanent** & **Part-Time**.
  * Strict validation for duplicate ID, negative values, and valid names (letters only).
* **🔍 Search Employee**:
  * Search employee details by unique ID.
  * Displays formatted formatted text output.
* **✏️ Update Employee**:
  * Dynamically load and modify existing employee records (Name, Gender, Type, Salary/Hours/Rate/Bonus).
* **📋 View All Employees**:
  * Non-editable `JTable` rendering all active employees and calculated total salaries.
* **🗑️ Delete Employee**:
  * Remove employee records with user confirmation.

---

## 📐 Object-Oriented Programming (OOP) Concepts

1. **Encapsulation**: Private fields with public getters and setters in [`Employee.java`](src/Folder/Employee.java).
2. **Inheritance**: [`PermanentEmployee.java`](src/Folder/PermanentEmployee.java) and [`PartTimeEmployee.java`](src/Folder/PartTimeEmployee.java) extend [`Employee.java`](src/Folder/Employee.java).
3. **Abstraction**: [`Employee.java`](src/Folder/Employee.java) is an abstract class implementing [`Payable.java`](src/Folder/Payable.java).
4. **Polymorphism**: Overridden methods (`getBaseSalary()`, `getBonus()`, `getType()`) calculate salaries dynamically based on employee type.

---

## 📁 Project Structure

```
EmployeeManagement/
├── src/
│   ├── Folder/                     # Business Logic & Backend
│   │   ├── Payable.java            # Interface
│   │   ├── Employee.java           # Abstract Parent Class
│   │   ├── PermanentEmployee.java    # Child Class (Permanent)
│   │   ├── PartTimeEmployee.java     # Child Class (Part-Time)
│   │   ├── EmployeeManager.java      # CRUD Operations Manager
│   │   └── Main.java                 # Entry Point
│   │
│   └── GUI/                        # Frontend Windows (Java Swing)
│       ├── LoginFrame.java
│       ├── MainFrame.java
│       ├── AddEmployeeFrame.java
│       ├── SearchEmployeeFrame.java
│       ├── UpdateEmployeeFrame.java
│       ├── ViewEmployeeFrame.java
│       └── DeleteEmployeeFrame.java
│
├── Project_Report.pdf              # Full Project Report & Documentation
├── README.md                       # Project Documentation
├── .project                        # Eclipse Metadata
└── .classpath                      # Eclipse Classpath Config
```

---

## 🚀 Getting Started

### Prerequisites
* **Java Development Kit (JDK 8 or higher)**
* **Eclipse IDE / VS Code / IntelliJ IDEA**

### Running the Application
1. Clone this repository:
   ```bash
   git clone <YOUR-REPOSITORY-URL>
   ```
2. Import the project into **Eclipse IDE**:
   * `File` ➔ `Import...` ➔ `Existing Projects into Workspace`
   * Select project directory and click `Finish`.
3. Run `src/Folder/Main.java` as a **Java Application**.

---

## 📄 Documentation & Lab Submission
This repository includes a full project report PDF (e.g. `Project_Report.pdf`). Students/Developers can refer to the report for complete system architecture, flowcharts, and lab submission guidelines.
