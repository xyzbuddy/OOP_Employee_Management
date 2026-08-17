package GUI;

import Folder.Employee;
import Folder.EmployeeManager;
import Folder.PermanentEmployee;
import Folder.PartTimeEmployee;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SearchEmployeeFrame extends JFrame {

    private JTextField idField;
    private JTextArea resultArea;
    private JButton searchButton, clearButton, backButton;
    private JLabel statusLabel;

    private final String ID_PH = "e.g., 101";

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

    public SearchEmployeeFrame(EmployeeManager manager) {
        setTitle("Search Employee");
        setSize(430, 530);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // 1. HEADER PANEL
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(PRIMARY_BLUE);
        headerPanel.setPreferredSize(new Dimension(430, 60));

        JLabel headerLabel = new JLabel("SEARCH EMPLOYEE");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        add(headerPanel, BorderLayout.NORTH);

        // 2. CENTER PANEL
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 10, 25));
        centerPanel.setBackground(new Color(245, 247, 250));

        // Top Search Input Panel
        JPanel topSearchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        topSearchPanel.setBackground(new Color(245, 247, 250));

        JLabel idLabel = new JLabel("Enter ID:");
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));

        idField = createStyledTextField();
        idField.setPreferredSize(new Dimension(140, 32));
        addPlaceholder(idField, ID_PH);

        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        searchButton.setBackground(PRIMARY_BLUE);
        searchButton.setForeground(Color.WHITE);
        searchButton.setPreferredSize(new Dimension(85, 32));
        searchButton.setFocusPainted(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        topSearchPanel.add(idLabel);
        topSearchPanel.add(idField);
        topSearchPanel.add(searchButton);

        centerPanel.add(topSearchPanel);
        centerPanel.add(Box.createVerticalStrut(8));

        // Inline Status Label
        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        statusLabel.setForeground(ERROR_COLOR);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(statusLabel);
        centerPanel.add(Box.createVerticalStrut(10));

        // JTextArea Result Output (Monospaced Consolas Font for 100% Perfect Colon Alignment)
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        resultArea.setMargin(new Insets(12, 15, 12, 15));
        resultArea.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(new LineBorder(DEFAULT_BORDER_COLOR, 1));
        centerPanel.add(scrollPane);

        add(centerPanel, BorderLayout.CENTER);

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

        buttonPanel.add(backButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Default Button for Enter Key
        this.getRootPane().setDefaultButton(searchButton);

        // Action Listeners
        searchButton.addActionListener(e -> performSearch(manager));
        clearButton.addActionListener(e -> resetForm());
        backButton.addActionListener(e -> dispose());
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
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
    }

    private void performSearch(EmployeeManager manager) {
        clearWarning();
        String idText = idField.getText().trim();

        if (idText.isEmpty() || idText.equals(ID_PH)) {
            idField.setBorder(errorBorder);
            statusLabel.setText("[!] Please enter an Employee ID!");
            resultArea.setText("");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            Employee emp = manager.searchEmployee(id);

            if (emp != null) {
                // Formatting with %-15s guarantees 100% strict vertical colon alignment
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%-15s : %s\n", "ID", emp.getId()));
                sb.append(String.format("%-15s : %s\n", "Name", emp.getName()));
                sb.append(String.format("%-15s : %s\n", "Gender", emp.getGender()));
                sb.append(String.format("%-15s : %s\n", "Type", emp.getType() + " Employee"));

                if (emp instanceof PermanentEmployee) {
                    PermanentEmployee pe = (PermanentEmployee) emp;
                    sb.append(String.format("%-15s : %.2f\n", "Basic Salary", pe.getBaseSalary()));
                    sb.append(String.format("%-15s : %.2f\n", "Bonus", pe.getBonus()));
                } else if (emp instanceof PartTimeEmployee) {
                    PartTimeEmployee pte = (PartTimeEmployee) emp;
                    sb.append(String.format("%-15s : %.2f\n", "Working Hours", pte.getWorkingHours()));
                    sb.append(String.format("%-15s : %.2f\n", "Rate Per Hour", pte.getRatePerHour()));
                    sb.append(String.format("%-15s : %.2f\n", "Base Salary", pte.getBaseSalary()));
                }

                sb.append(String.format("%-15s : %.2f", "Total Salary", emp.getTotalSalary()));

                resultArea.setText(sb.toString());
            } else {
                idField.setBorder(errorBorder);
                statusLabel.setText("[!] Employee ID: " + id + " not found!");
                resultArea.setText("");
            }

        } catch (NumberFormatException ex) {
            idField.setBorder(errorBorder);
            statusLabel.setText("[!] ID must be a valid number!");
            resultArea.setText("");
        }
    }

    private void resetForm() {
        clearWarning();
        addPlaceholder(idField, ID_PH);
        resultArea.setText("");
    }
}