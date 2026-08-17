========================================================
 Employee Management System (Java OOP + Swing)
========================================================

HOW TO OPEN THIS PROJECT IN ECLIPSE
------------------------------------
1. Open Eclipse.
2. Menu:  File  ->  Import...
3. Choose:  General  ->  Existing Projects into Workspace  ->  Next
4. "Select root directory"  ->  Browse  ->  pick this "EmployeeManagement" folder.
5. Make sure "EmployeeManagement" is ticked  ->  Finish.
6. In Package Explorer open:  src  ->  Folder  ->  Main.java
7. Right-click Main.java  ->  Run As  ->  Java Application.

That's it. The main window will open.


PROJECT STRUCTURE
------------------------------------
EmployeeManagement/
├── src/
│   ├── Folder/                 (Backend - business logic)
│   │   ├── Employee.java          -> abstract parent class
│   │   ├── PermanentEmployee.java -> bonus is a manual amount
│   │   ├── PartTimeEmployee.java  -> salary = hours * rate
│   │   ├── EmployeeManager.java   -> all the logic lives here
│   │   └── Main.java              -> has the one shared Main.manager
│   │
│   └── GUI/                     (Frontend - Java Swing windows)
│       ├── MainFrame.java
│       ├── AddEmployeeFrame.java
│       ├── SearchEmployeeFrame.java
│       ├── UpdateEmployeeFrame.java
│       ├── ViewEmployeeFrame.java
│       └── DeleteEmployeeFrame.java
│
├── .project      (Eclipse settings)
├── .classpath    (Eclipse settings)
└── README.txt    (this file)


NOTE
------------------------------------
- The program starts from:  src/Folder/Main.java
- Data is kept in memory only, so closing the app resets it.
- 4 demo employees are added inside Main.java (you can delete
  those 4 lines to start empty).


OOP CONCEPTS USED (for your assignment)
------------------------------------
- Encapsulation : private fields + getters/setters (Employee.java)
- Inheritance   : PermanentEmployee / PartTimeEmployee extend Employee
- Polymorphism  : getBaseSalary(), getBonus(), getType() are overridden
- Abstraction   : Employee is an abstract class
