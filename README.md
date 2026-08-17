# 👥 Employee Management System (Java Swing + OOP)

A desktop-based **Employee Management System** built using **Java (Swing GUI)** adhering strictly to **Object-Oriented Programming (OOP)** principles.

---

## 📸 User Interface Showcase

| Login Screen | System Dashboard |
| :---: | :---: |
| ![Login Screen](images/Loginframe.png) | ![System Menu](images/SystemMenu.png) |
| *Secure Login with Remember Me & Password Visibility* | *Main Menu Navigation Dashboard* |

| Add Employee | Search Employee |
| :---: | :---: |
| ![Add Employee](images/AddEmployeeFrame.png) | ![Search Employee](images/SearchemployeeFrame.png) |
| *Add Permanent or Part-Time Employee with Validation* | *Search Employee by ID with Formatted Output* |

| Update Employee | View All Employees |
| :---: | :---: |
| ![Update Employee](images/UpdateEmployeeframe.png) | ![View All Employees](images/AllEmployeeFrame.png) |
| *Load and Edit Existing Employee Records* | *Interactive JTable Displaying All Records* |

---

## 🌟 Key Features

* **🔐 Authentication System**:
  * Secure Admin Login (Default Username: `admin`, Default Password: `1234`).
  * *Remember Me* feature saved using Java Preferences API.
  * Show/Hide password toggle & field placeholders.
* **➕ Add Employee**:
  * Supports two employee categories: **Permanent** & **Part-Time**.
  * Strict validation for duplicate ID, negative values, and valid names (letters only).
* **🔍 Search Employee**:
  * Search employee details by unique ID.
  * Displays formatted monospaced text output.
* **✏️ Update Employee**:
  * Dynamically load and modify existing employee records (Name, Gender, Type, Salary/Hours/Rate/Bonus).
* **📋 View All Employees**:
  * Non-editable `JTable` rendering all active employees and calculated total salaries.
* **🗑️ Delete Employee**:
  * Remove employee records with user confirmation.

---

## 🔑 Changing Default Credentials

By default, the system uses the following login credentials:
* **Username**: `admin`
* **Password**: `1234`

To change the default username or password, edit the constants in [`src/GUI/LoginFrame.java`](src/GUI/LoginFrame.java):

```java
// Credentials inside LoginFrame.java
private final String CORRECT_USERNAME = "admin";  // Change your username here
private final String CORRECT_PASSWORD = "1234";   // Change your password here
```

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
├── images/                         # GUI Screenshots & Interface Showcase
│   ├── Loginframe.png
│   ├── SystemMenu.png
│   ├── AddEmployeeFrame.png
│   ├── SearchemployeeFrame.png
│   ├── UpdateEmployeeframe.png
│   └── AllEmployeeFrame.png
│
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

### 💻 How to Run the Application

1. **Clone this repository**:
   ```bash
   git clone https://github.com/xyzbuddy/OOP_Employee_Management.git
   ```

#### 🔹 Option 1: Eclipse IDE
1. Open Eclipse and select `File` ➔ `Import...`.
2. Choose `General` ➔ `Existing Projects into Workspace` ➔ Click `Next`.
3. Select the `Employee_Management` directory and click `Finish`.
4. In Package Explorer, open `src/Folder/Main.java` ➔ Right-click ➔ `Run As` ➔ `Java Application`.

#### 🔹 Option 2: IntelliJ IDEA
1. Open IntelliJ IDEA and select `File` ➔ `Open...`.
2. Select the `Employee_Management` folder and click `OK`.
3. If prompted, set JDK version (Java 8 or higher).
4. Navigate to `src/Folder/Main.java` ➔ Click the green ▶️ **Run** button.

#### 🔹 Option 3: Visual Studio Code (VS Code)
1. Open VS Code and click `File` ➔ `Open Folder...`.
2. Select the `Employee_Management` folder.
3. Ensure the **Extension Pack for Java** is installed.
4. Open `src/Folder/Main.java` and click **Run** above `public static void main`.

#### 🔹 Option 4: Command Line / Terminal (CLI)
1. Open your terminal in the project root directory.
2. Compile the source code:
   ```bash
   javac -d bin src/Folder/*.java src/GUI/*.java
   ```
3. Run the application:
   ```bash
   java -cp bin Folder.Main
   ```

---

## 📄 Documentation & Lab Submission
This repository includes a full project report PDF ([`Project_Report.pdf`](Project_Report.pdf)). Students/Developers can refer to the report for complete system architecture, flowcharts, and lab submission guidelines.
