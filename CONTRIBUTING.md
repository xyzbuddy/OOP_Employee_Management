# 🤝 Contributing to Employee Management System

Thank you for considering contributing to the **Employee Management System**! We welcome contributions from developers of all skill levels, whether you are fixing a bug, adding a new feature, improving documentation, or optimizing code performance.

---

## 🚀 How to Contribute

### 1. Fork the Repository
Click the **Fork** button at the top right of the GitHub repository page to create your own copy of the project.

### 2. Clone Your Fork
Clone your fork locally to your computer:
```bash
git clone https://github.com/YOUR-USERNAME/OOP_Employee_Management.git
cd OOP_Employee_Management
```

### 3. Create a Feature Branch
Create a new descriptive branch for your work:
```bash
# For a new feature
git checkout -b feature/add-department-management

# For a bug fix
git checkout -b fix/correct-salary-rounding
```

### 4. Make Your Changes
* Write clean, readable Java code adhering to Object-Oriented Programming (OOP) principles.
* Ensure GUI layout changes in Swing look clean and consistent with existing frames.
* Keep existing methods and API contracts intact.

### 5. Commit Your Changes
Write clear, concise commit messages describing what you changed and why:
```bash
git add .
git commit -m "Add department field validation in AddEmployeeFrame"
```

### 6. Push to Your Fork
Push your branch to your GitHub fork:
```bash
git push origin feature/add-department-management
```

### 7. Submit a Pull Request (PR)
1. Navigate to the main repository on GitHub.
2. Click **Pull Requests** ➔ **New Pull Request**.
3. Select your branch and click **Create Pull Request**.
4. Provide a clear description of your changes, what problem it solves, and how to test it.

---

## 🐛 Reporting Bugs & Requesting Features

* **Found a Bug?** Open a new [GitHub Issue](https://github.com/xyzbuddy/OOP_Employee_Management/issues) detailing:
  * Expected vs actual behavior.
  * Steps to reproduce the issue.
  * Screenshot or log output if available.
* **Want a Feature?** Submit an Issue tagged with `feature-request` explaining the feature idea and benefits.

---

## 📐 Coding Guidelines

1. **OOP Rules**: Respect Encapsulation (private attributes, public getters/setters), Abstraction, and Polymorphism.
2. **Naming Conventions**:
   * Class Names: `PascalCase` (e.g. `DepartmentManager.java`)
   * Variable & Method Names: `camelCase` (e.g. `calculateTax()`)
   * Constants: `UPPER_SNAKE_CASE` (e.g. `MAX_EMPLOYEES`)
3. **No Breaking Changes**: Ensure existing features (Login, Search, Update, Delete, View Table) compile and execute without errors.

---

## 📄 Code of Conduct

Please treat all maintainers and contributors with respect and professionalism.

Thank you for helping make this project better! 🌟
