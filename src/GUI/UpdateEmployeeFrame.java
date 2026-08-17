package GUI;

import Folder.Employee;
import Folder.EmployeeManager;
import Folder.PermanentEmployee;
import Folder.PartTimeEmployee;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class UpdateEmployeeFrame extends JFrame {

    private JTextField idField, nameField, salaryField, hoursField, rateField, bonusField;
    private JComboBox<String> genderBox, typeBox;
    private JButton loadButton, updateButton, clearButton, backButton;
    private JPanel detailsPanel, dynamicPanel;
    private CardLayout cardLayout;
    private JLabel statusLabel;
    private Employee currentEmp;

    // Placeholders
    private final String ID_PH = "e.g., 101";
    private final String NAME_PH = "e.g., John Doe";
    private final String SALARY_PH = "e.g., 50000";
    private final String BONUS_PH = "e.g., 5000";
    private final String HOURS_PH = "e.g., 40";
    private final String RATE_PH = "e.g., 500";

    // Colors & Borders
    private final Color PRIMARY_BLUE = new Color(30, 50, 92);
    private final Color DEFAULT_BORDER_COLOR = new Color(200, 205, 215);
    private final Color ERROR_COLOR = new Color(220, 50, 50);

    private final Border defaultBorder = BorderFactory.createCompoundBorder(
            new LineBorder(DEFAULT_BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
    );
    private final Border errorBorder = BorderFactory.createCompoundBorder(
            new LineBorder(ERROR_COLOR, 2),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
    );

    public UpdateEmployeeFrame(EmployeeManager manager) {
        setTitle("Update Employee");
        setSize(430, 530);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // 1. HEADER PANEL
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(PRIMARY_BLUE);
        headerPanel.setPreferredSize(new Dimension(430, 60));

        JLabel headerLabel = new JLabel("EDIT  UPDATE EMPLOYEE");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        add(headerPanel, BorderLayout.NORTH);

        // 2. CENTER PANEL
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 10, 25));
        centerPanel.setBackground(new Color(245, 247, 250));

        // Top Search ID Section
        JPanel loadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        loadPanel.setBackground(new Color(245, 247, 250));
        loadPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel idLabel = new JLabel("Enter ID:");
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));

        idField = createStyledTextField();
        idField.setPreferredSize(new Dimension(130, 30));
        addPlaceholder(idField, ID_PH);

        loadButton = new JButton("Load");
        loadButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        loadButton.setBackground(PRIMARY_BLUE);
        loadButton.setForeground(Color.WHITE);
        loadButton.setPreferredSize(new Dimension(80, 30));
        loadButton.setFocusPainted(false);
        loadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loadPanel.add(idLabel);
        loadPanel.add(idField);
        loadPanel.add(loadButton);

        centerPanel.add(loadPanel);
        centerPanel.add(Box.createVerticalStrut(10));

        // Form Container (Hidden until ID is loaded)
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(new Color(245, 247, 250));
        detailsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.setVisible(false);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 247, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 0);

        // Name
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        formPanel.add(createBoldLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        nameField = createStyledTextField();
        addPlaceholder(nameField, NAME_PH);
        formPanel.add(nameField, gbc);

        // Gender
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        formPanel.add(createBoldLabel("Gender:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        genderBox.setPreferredSize(new Dimension(200, 30));
        formPanel.add(genderBox, gbc);

        // Employee Type
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        formPanel.add(createBoldLabel("Employee Type:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        typeBox = new JComboBox<>(new String[]{"Permanent", "Part-Time"});
        typeBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        typeBox.setPreferredSize(new Dimension(200, 30));
        formPanel.add(typeBox, gbc);

        detailsPanel.add(formPanel);

        // Dynamic Panel (Permanent / Part-Time)
        cardLayout = new CardLayout();
        dynamicPanel = new JPanel(cardLayout);
        dynamicPanel.setBackground(new Color(245, 247, 250));

        // Permanent Panel
        JPanel permPanel = new JPanel(new GridBagLayout());
        permPanel.setBackground(new Color(245, 247, 250));
        GridBagConstraints pGbc = new GridBagConstraints();
        pGbc.fill = GridBagConstraints.HORIZONTAL;
        pGbc.insets = new Insets(6, 0, 6, 0);

        pGbc.gridx = 0; pGbc.gridy = 0; pGbc.weightx = 0.0;
        permPanel.add(createBoldLabel("Basic Salary:"), pGbc);
        pGbc.gridx = 1; pGbc.weightx = 1.0;
        salaryField = createStyledTextField();
        addPlaceholder(salaryField, SALARY_PH);
        permPanel.add(salaryField, pGbc);

        pGbc.gridx = 0; pGbc.gridy = 1; pGbc.weightx = 0.0;
        permPanel.add(createBoldLabel("Bonus:"), pGbc);
        pGbc.gridx = 1; pGbc.weightx = 1.0;
        bonusField = createStyledTextField();
        addPlaceholder(bonusField, BONUS_PH);
        permPanel.add(bonusField, pGbc);

        // Part-Time Panel
        JPanel partPanel = new JPanel(new GridBagLayout());
        partPanel.setBackground(new Color(245, 247, 250));
        GridBagConstraints ptGbc = new GridBagConstraints();
        ptGbc.fill = GridBagConstraints.HORIZONTAL;
        ptGbc.insets = new Insets(6, 0, 6, 0);

        ptGbc.gridx = 0; ptGbc.gridy = 0; ptGbc.weightx = 0.0;
        partPanel.add(createBoldLabel("Hours Worked:"), ptGbc);
        ptGbc.gridx = 1; ptGbc.weightx = 1.0;
        hoursField = createStyledTextField();
        addPlaceholder(hoursField, HOURS_PH);
        partPanel.add(hoursField, ptGbc);

        ptGbc.gridx = 0; ptGbc.gridy = 1; ptGbc.weightx = 0.0;
        partPanel.add(createBoldLabel("Rate Per Hour:"), ptGbc);
        ptGbc.gridx = 1; ptGbc.weightx = 1.0;
        rateField = createStyledTextField();
        addPlaceholder(rateField, RATE_PH);
        partPanel.add(rateField, ptGbc);

        dynamicPanel.add(permPanel, "Permanent");
        dynamicPanel.add(partPanel, "Part-Time");

        detailsPanel.add(dynamicPanel);
        centerPanel.add(detailsPanel);

        // Inline Status / Warning Label
        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        statusLabel.setForeground(ERROR_COLOR);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(statusLabel);

        // Vertical Glue pushes everything tightly to the top
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);

        // Type Box Listener -> Flips fields between Permanent & Part-Time
        typeBox.addActionListener(e -> {
            String selected = (String) typeBox.getSelectedItem();
            cardLayout.show(dynamicPanel, selected);
            clearWarning();
        });

        // 3. BOTTOM BUTTONS PANEL
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        buttonPanel.setBackground(new Color(245, 247, 250));

        backButton = new JButton("<- Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(PRIMARY_BLUE);
        backButton.setPreferredSize(new Dimension(90, 36));
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        clearButton.setBackground(Color.WHITE);
        clearButton.setForeground(new Color(80, 80, 80));
        clearButton.setPreferredSize(new Dimension(85, 36));
        clearButton.setFocusPainted(false);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        updateButton = new JButton("Update Employee");
        updateButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        updateButton.setBackground(PRIMARY_BLUE);
        updateButton.setForeground(Color.WHITE);
        updateButton.setPreferredSize(new Dimension(140, 36));
        updateButton.setFocusPainted(false);
        updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateButton.setEnabled(false);

        buttonPanel.add(backButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(updateButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Enter Key Action (Initially Load button)
        this.getRootPane().setDefaultButton(loadButton);

        // Actions
        loadButton.addActionListener(e -> performLoad(manager));
        updateButton.addActionListener(e -> performUpdate(manager));
        clearButton.addActionListener(e -> resetAll());
        backButton.addActionListener(e -> dispose());
    }

    // Helper Methods
    private JLabel createBoldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setPreferredSize(new Dimension(125, 25));
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setPreferredSize(new Dimension(200, 30));
        field.setBorder(defaultBorder);
        return field;
    }

    private void addPlaceholder(JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    field.setBorder(defaultBorder);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().trim().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void clearWarning() {
        statusLabel.setText(" ");
        idField.setBorder(defaultBorder);
        nameField.setBorder(defaultBorder);
        salaryField.setBorder(defaultBorder);
        bonusField.setBorder(defaultBorder);
        hoursField.setBorder(defaultBorder);
        rateField.setBorder(defaultBorder);
    }

    private void performLoad(EmployeeManager manager) {
        clearWarning();
        String idText = idField.getText().trim();

        if (idText.isEmpty() || idText.equals(ID_PH)) {
            idField.setBorder(errorBorder);
            statusLabel.setText("[!] Please enter an Employee ID!");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            currentEmp = manager.searchEmployee(id);

            if (currentEmp != null) {
                nameField.setText(currentEmp.getName());
                nameField.setForeground(Color.BLACK);
                genderBox.setSelectedItem(currentEmp.getGender());

                if (currentEmp instanceof PermanentEmployee) {
                    typeBox.setSelectedItem("Permanent");
                    PermanentEmployee pe = (PermanentEmployee) currentEmp;
                    salaryField.setText(String.valueOf(pe.getBaseSalary()));
                    salaryField.setForeground(Color.BLACK);
                    bonusField.setText(String.valueOf(pe.getBonus()));
                    bonusField.setForeground(Color.BLACK);
                    cardLayout.show(dynamicPanel, "Permanent");
                } else if (currentEmp instanceof PartTimeEmployee) {
                    typeBox.setSelectedItem("Part-Time");
                    PartTimeEmployee pte = (PartTimeEmployee) currentEmp;
                    hoursField.setText(String.valueOf(pte.getWorkingHours()));
                    hoursField.setForeground(Color.BLACK);
                    rateField.setText(String.valueOf(pte.getRatePerHour()));
                    rateField.setForeground(Color.BLACK);
                    cardLayout.show(dynamicPanel, "Part-Time");
                }

                detailsPanel.setVisible(true);
                updateButton.setEnabled(true);
                this.getRootPane().setDefaultButton(updateButton);
                revalidate();
                repaint();
            } else {
                detailsPanel.setVisible(false);
                updateButton.setEnabled(false);
                idField.setBorder(errorBorder);
                statusLabel.setText("[!] Employee ID: " + id + " not found!");
            }
        } catch (NumberFormatException ex) {
            idField.setBorder(errorBorder);
            statusLabel.setText("[!] ID must be a valid number!");
        }
    }

    private void performUpdate(EmployeeManager manager) {
        clearWarning();

        if (currentEmp == null) {
            statusLabel.setText("[!] Load an employee first!");
            return;
        }

        String nameText = nameField.getText().trim();
        String gender = (String) genderBox.getSelectedItem();
        String selectedType = (String) typeBox.getSelectedItem();
        int id = currentEmp.getId();

        // Strict Name Validation
        if (nameText.isEmpty() || nameText.equals(NAME_PH)) {
            nameField.setBorder(errorBorder);
            statusLabel.setText("[!] Name field cannot be empty!");
            return;
        }

        if (!nameText.matches("^[a-zA-Z\\s.\\-]+$")) {
            nameField.setBorder(errorBorder);
            statusLabel.setText("[!] Name must contain letters only (no numbers)!");
            return;
        }

        boolean isUpdated = false;

        try {
            if ("Permanent".equals(selectedType)) {
                String salText = salaryField.getText().trim();
                String bonText = bonusField.getText().trim();

                if (salText.isEmpty() || salText.equals(SALARY_PH)) {
                    salaryField.setBorder(errorBorder);
                    statusLabel.setText("[!] Basic Salary is required!");
                    return;
                }

                double salary = Double.parseDouble(salText);
                if (salary < 0) {
                    salaryField.setBorder(errorBorder);
                    statusLabel.setText("[!] Basic Salary cannot be negative!");
                    return;
                }

                double bonus = (bonText.isEmpty() || bonText.equals(BONUS_PH)) ? 0 : Double.parseDouble(bonText);
                if (bonus < 0) {
                    bonusField.setBorder(errorBorder);
                    statusLabel.setText("[!] Bonus cannot be negative!");
                    return;
                }

                if (!(currentEmp instanceof PermanentEmployee)) {
                    manager.deleteEmployee(id);
                    isUpdated = manager.addPermanentEmployee(id, nameText, gender, salary, bonus);
                } else {
                    isUpdated = manager.updatePermanentEmployee(id, nameText, gender, salary, bonus);
                }

            } else {
                String hrsText = hoursField.getText().trim();
                String rteText = rateField.getText().trim();

                if (hrsText.isEmpty() || hrsText.equals(HOURS_PH)) {
                    hoursField.setBorder(errorBorder);
                    statusLabel.setText("[!] Hours Worked is required!");
                    return;
                }
                if (rteText.isEmpty() || rteText.equals(RATE_PH)) {
                    rateField.setBorder(errorBorder);
                    statusLabel.setText("[!] Rate Per Hour is required!");
                    return;
                }

                double hours = Double.parseDouble(hrsText);
                if (hours < 0) {
                    hoursField.setBorder(errorBorder);
                    statusLabel.setText("[!] Hours worked cannot be negative!");
                    return;
                }

                double rate = Double.parseDouble(rteText);
                if (rate < 0) {
                    rateField.setBorder(errorBorder);
                    statusLabel.setText("[!] Rate per hour cannot be negative!");
                    return;
                }

                if (!(currentEmp instanceof PartTimeEmployee)) {
                    manager.deleteEmployee(id);
                    isUpdated = manager.addPartTimeEmployee(id, nameText, gender, hours, rate);
                } else {
                    isUpdated = manager.updatePartTimeEmployee(id, nameText, gender, hours, rate);
                }
            }

            if (isUpdated) {
                JOptionPane.showMessageDialog(this, "Employee Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                resetAll();
            } else {
                statusLabel.setText("[!] Failed to update employee!");
            }

        } catch (NumberFormatException ex) {
            statusLabel.setText("[!] Please enter valid numeric values!");
        }
    }

    private void resetAll() {
        clearWarning();
        addPlaceholder(idField, ID_PH);
        addPlaceholder(nameField, NAME_PH);
        addPlaceholder(salaryField, SALARY_PH);
        addPlaceholder(bonusField, BONUS_PH);
        addPlaceholder(hoursField, HOURS_PH);
        addPlaceholder(rateField, RATE_PH);
        genderBox.setSelectedIndex(0);
        typeBox.setSelectedIndex(0);
        detailsPanel.setVisible(false);
        updateButton.setEnabled(false);
        currentEmp = null;
        this.getRootPane().setDefaultButton(loadButton);
        revalidate();
        repaint();
    }
}