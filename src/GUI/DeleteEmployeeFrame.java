package GUI;

import Folder.EmployeeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteEmployeeFrame extends JFrame {

    private JTextField idField;
    private JButton deleteButton;

    public DeleteEmployeeFrame(EmployeeManager manager) {
        setTitle("Delete Employee");
        setSize(360, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 25));

        add(new JLabel("Enter Employee ID:"));
        idField = new JTextField(10);
        add(idField);

        deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(200, 50, 50));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        add(deleteButton);

        // ==========================================
        // ENTER KEY FEATURE (DEFAULT BUTTON SETTING)
        // ==========================================
        this.getRootPane().setDefaultButton(deleteButton);

        // Delete Button Action
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText().trim());

                    if (!manager.employeeExists(id)) {
                        JOptionPane.showMessageDialog(DeleteEmployeeFrame.this, "Employee ID not found!", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    int confirm = JOptionPane.showConfirmDialog(
                            DeleteEmployeeFrame.this,
                            "Are you sure you want to delete Employee ID: " + id + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        // আপনার EmployeeManager এর deleteEmployee মেথড কল করা হচ্ছে
                        boolean isDeleted = manager.deleteEmployee(id);
                        
                        if (isDeleted) {
                            JOptionPane.showMessageDialog(DeleteEmployeeFrame.this, "Employee Deleted Successfully!");
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(DeleteEmployeeFrame.this, "Failed to delete employee!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DeleteEmployeeFrame.this, "Please enter a valid numeric ID!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}