package GUI;

import Folder.Employee;
import Folder.EmployeeManager;
import Folder.PermanentEmployee;
import Folder.PartTimeEmployee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewEmployeeFrame extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton closeButton;

    public ViewEmployeeFrame(EmployeeManager manager) {
        setTitle("View All Employees");
        setSize(650, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Table Columns Header
        String[] columns = {"ID", "Name", "Gender", "Type", "Base Salary / Rate", "Bonus / Hours", "Total Salary"};
        
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // টেবিল এডিটেবল না রাখার জন্য
            }
        };

        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.setRowHeight(25);

        // EmployeeManager থেকে সব তথ্য নিয়ে টেবিলে যোগ করা
        for (Employee emp : manager.getEmployeeList()) {
            String salaryOrRate = "";
            String bonusOrHours = "";

            if (emp instanceof PermanentEmployee) {
                PermanentEmployee pe = (PermanentEmployee) emp;
                salaryOrRate = String.format("%.2f", pe.getBaseSalary());
                bonusOrHours = String.format("%.2f", pe.getBonus());
            } else if (emp instanceof PartTimeEmployee) {
                PartTimeEmployee pte = (PartTimeEmployee) emp;
                salaryOrRate = String.format("%.2f", pte.getRatePerHour());
                bonusOrHours = String.format("%.2f", pte.getWorkingHours());
            }

            Object[] row = {
                    emp.getId(),
                    emp.getName(),
                    emp.getGender(),
                    emp.getType(),
                    salaryOrRate,
                    bonusOrHours,
                    String.format("%.2f", emp.getTotalSalary())
            };
            tableModel.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Close Button
        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        closeButton.setPreferredSize(new Dimension(100, 35));
        closeButton.setFocusPainted(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(closeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // ==========================================
        // ENTER KEY FEATURE (DEFAULT BUTTON SETTING)
        // ==========================================
        this.getRootPane().setDefaultButton(closeButton);

        // Close Button Action (Enter চাপলে উইন্ডো বন্ধ হবে)
        closeButton.addActionListener(e -> dispose());
    }
}