package GUI;

import Folder.EmployeeManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class AddEmployeeFrame extends JFrame {

    private JTextField idField, nameField, salaryField, hoursField, rateField, bonusField;
    private JComboBox<String> genderBox, typeBox;

    private JPanel dynamicPanel;
    private CardLayout cardLayout;

    private JButton addButton, clearButton, backButton;
    private JLabel statusLabel;

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

    public AddEmployeeFrame(EmployeeManager manager) {
        setTitle("Add Employee");
        setSize(430, 530);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // 1. HEADER PANEL
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(PRIMARY_BLUE);
        headerPanel.setPreferredSize(new Dimension(430, 60));

        JLabel headerLabel = new JLabel("+  ADD EMPLOYEE");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        add(headerPanel, BorderLayout.NORTH);

        // 2. CENTER FORM PANEL
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 10, 25));
        centerPanel.setBackground(new Color(245, 247, 250));

        // Fixed Top Form Layout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 247, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 0);

        // ID
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        formPanel.add(createBoldLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        idField = createStyledTextField();
        addPlaceholder(idField, ID_PH);
        formPanel.add(idField, gbc);

        // Name
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        formPanel.add(createBoldLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        nameField = createStyledTextField();
        addPlaceholder(nameField, NAME_PH);
        formPanel.add(nameField, gbc);

        // Gender
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        formPanel.add(createBoldLabel("Gender:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        genderBox.setPreferredSize(new Dimension(200, 30));
        formPanel.add(genderBox, gbc);

        // Employee Type
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.0;
        formPanel.add(createBoldLabel("Employee Type:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        typeBox = new JComboBox<>(new String[]{"Permanent", "Part-Time"});
        typeBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        typeBox.setPreferredSize(new Dimension(200, 30));
        formPanel.add(typeBox, gbc);

        centerPanel.add(formPanel);

        // Dynamic Panel (Card Layout)
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

        centerPanel.add(dynamicPanel);

        // Inline Status / Warning Label
        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        statusLabel.setForeground(ERROR_COLOR);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(statusLabel);

        add(centerPanel, BorderLayout.CENTER);

        typeBox.addActionListener(e -> {
            String selected = (String) typeBox.getSelectedItem();
            cardLayout.show(dynamicPanel, selected);
            clearWarning();
        });

        // 3. BOTTOM BUTTONS PANEL (Back, Clear, Add)
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

        addButton = new JButton("Add Employee");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        addButton.setBackground(PRIMARY_BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.setPreferredSize(new Dimension(125, 36));
        addButton.setFocusPainted(false);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buttonPanel.add(backButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(addButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Default Button for Enter Key
        this.getRootPane().setDefaultButton(addButton);

        // Action Listeners
        backButton.addActionListener(e -> dispose());
        clearButton.addActionListener(e -> resetForm());
        addButton.addActionListener(e -> performAdd(manager));
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

    private void performAdd(EmployeeManager manager) {
        clearWarning();

        String idText = idField.getText().trim();
        String nameText = nameField.getText().trim();
        String type = (String) typeBox.getSelectedItem();
        String gender = (String) genderBox.getSelectedItem();

        // 1. VALIDATION: ID Field
        if (idText.isEmpty() || idText.equals(ID_PH)) {
            idField.setBorder(errorBorder);
            statusLabel.setText("[!] Please enter an Employee ID!");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
            if (id <= 0) {
                idField.setBorder(errorBorder);
                statusLabel.setText("[!] ID must be a positive number!");
                return;
            }
        } catch (NumberFormatException ex) {
            idField.setBorder(errorBorder);
            statusLabel.setText("[!] ID must be a valid number!");
            return;
        }

        if (manager.employeeExists(id)) {
            idField.setBorder(errorBorder);
            statusLabel.setText("[!] Employee ID: " + id + " already exists!");
            return;
        }

        // 2. VALIDATION: Name Field (Strict Letter Check)
        if (nameText.isEmpty() || nameText.equals(NAME_PH)) {
            nameField.setBorder(errorBorder);
            statusLabel.setText("[!] Name field cannot be empty!");
            return;
        }

        // Regex: Only letters, spaces, dots, and hyphens allowed (No numbers!)
        if (!nameText.matches("^[a-zA-Z\\s.\\-]+$")) {
            nameField.setBorder(errorBorder);
            statusLabel.setText("[!] Name must contain letters only (no numbers)!");
            return;
        }

        boolean isAdded = false;

        try {
            if ("Permanent".equals(type)) {
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

                isAdded = manager.addPermanentEmployee(id, nameText, gender, salary, bonus);
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

                isAdded = manager.addPartTimeEmployee(id, nameText, gender, hours, rate);
            }

            if (isAdded) {
                JOptionPane.showMessageDialog(this, "Employee Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                resetForm();
            } else {
                statusLabel.setText("[!] Failed to add employee!");
            }

        } catch (NumberFormatException ex) {
            statusLabel.setText("[!] Please enter valid numeric values!");
        }
    }

    private void resetForm() {
        clearWarning();
        addPlaceholder(idField, ID_PH);
        addPlaceholder(nameField, NAME_PH);
        addPlaceholder(salaryField, SALARY_PH);
        addPlaceholder(bonusField, BONUS_PH);
        addPlaceholder(hoursField, HOURS_PH);
        addPlaceholder(rateField, RATE_PH);
        genderBox.setSelectedIndex(0);
        typeBox.setSelectedIndex(0);
    }
}