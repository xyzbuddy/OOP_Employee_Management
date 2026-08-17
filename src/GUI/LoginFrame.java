package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.prefs.Preferences;

public class LoginFrame extends JFrame {

    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton clearButton;
    private JCheckBox showPassCheckBox;
    private JCheckBox rememberMeCheckBox;

    // Credentials
    private final String CORRECT_USERNAME = "admin";
    private final String CORRECT_PASSWORD = "1234";

    // Placeholders
    private final String USER_PLACEHOLDER = "Enter your username";
    private final String PASS_PLACEHOLDER = "Enter your password";

    // Remember Me Preferences Key
    private final Preferences prefs = Preferences.userNodeForPackage(LoginFrame.class);
    private final String PREF_REMEMBERED_USER = "remembered_username";

    public LoginFrame() {
        setTitle("Employee Management System");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // 1. HEADER PANEL
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 50, 92)); // Dark Blue (#1E325C)
        headerPanel.setPreferredSize(new Dimension(500, 100));
        headerPanel.setLayout(new GridBagLayout());

        JLabel headerLabel = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        add(headerPanel, BorderLayout.NORTH);

        // 2. CENTER LOGIN FORM PANEL
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(new Color(245, 247, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel subTitle = new JLabel("SYSTEM LOGIN", SwingConstants.CENTER);
        subTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        subTitle.setForeground(new Color(30, 50, 92));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 20, 10);
        centerPanel.add(subTitle, gbc);

        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.gridwidth = 1;

        // Username Label & Text Field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(userLabel, gbc);

        String savedUser = prefs.get(PREF_REMEMBERED_USER, "");
        if (!savedUser.isEmpty()) {
            userField = new JTextField(savedUser, 15);
            userField.setForeground(Color.BLACK);
        } else {
            userField = new JTextField(USER_PLACEHOLDER, 15);
            userField.setForeground(Color.GRAY);
        }

        userField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userField.setPreferredSize(new Dimension(200, 35));

        userField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userField.getText().equals(USER_PLACEHOLDER)) {
                    userField.setText("");
                    userField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userField.getText().isEmpty()) {
                    userField.setText(USER_PLACEHOLDER);
                    userField.setForeground(Color.GRAY);
                }
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 1;
        centerPanel.add(userField, gbc);

        // Password Label & Password Field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(passLabel, gbc);

        passField = new JPasswordField(15);
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passField.setEchoChar((char) 0);
        passField.setText(PASS_PLACEHOLDER);
        passField.setForeground(Color.GRAY);
        passField.setPreferredSize(new Dimension(200, 35));

        passField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String currentPass = new String(passField.getPassword());
                if (currentPass.equals(PASS_PLACEHOLDER)) {
                    passField.setText("");
                    if (!showPassCheckBox.isSelected()) {
                        passField.setEchoChar('•');
                    }
                    passField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String currentPass = new String(passField.getPassword());
                if (currentPass.isEmpty()) {
                    passField.setEchoChar((char) 0);
                    passField.setText(PASS_PLACEHOLDER);
                    passField.setForeground(Color.GRAY);
                }
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 2;
        centerPanel.add(passField, gbc);

        // Show Password Checkbox
        showPassCheckBox = new JCheckBox("Show Password");
        showPassCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        showPassCheckBox.setBackground(new Color(245, 247, 250));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 5, 10);
        centerPanel.add(showPassCheckBox, gbc);

        showPassCheckBox.addActionListener(e -> {
            String currentPass = new String(passField.getPassword());
            if (!currentPass.equals(PASS_PLACEHOLDER)) {
                if (showPassCheckBox.isSelected()) {
                    passField.setEchoChar((char) 0);
                } else {
                    passField.setEchoChar('•');
                }
            }
        });

        // Remember Me Checkbox
        rememberMeCheckBox = new JCheckBox("Remember Me");
        rememberMeCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        rememberMeCheckBox.setBackground(new Color(245, 247, 250));
        if (!savedUser.isEmpty()) {
            rememberMeCheckBox.setSelected(true);
        }
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 10, 10, 10);
        centerPanel.add(rememberMeCheckBox, gbc);

        // Buttons Panel (Login & Clear)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(new Color(245, 247, 250));

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(new Color(30, 50, 92));
        loginButton.setPreferredSize(new Dimension(100, 38));
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        clearButton.setBackground(Color.WHITE);
        clearButton.setForeground(new Color(180, 40, 40)); // Red tint for clear
        clearButton.setPreferredSize(new Dimension(100, 38));
        clearButton.setFocusPainted(false);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buttonPanel.add(loginButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        centerPanel.add(buttonPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Enter Key Action Listener for both fields
        KeyAdapter enterKeyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        };
        userField.addKeyListener(enterKeyAdapter);
        passField.addKeyListener(enterKeyAdapter);

        // Login Button Click Listener
        loginButton.addActionListener(e -> performLogin());

        // Clear Button Click Listener
        clearButton.addActionListener(e -> resetFields());
    }

    private void performLogin() {
        String inputUser = userField.getText().trim();
        String inputPass = new String(passField.getPassword());

        if (inputUser.equals(USER_PLACEHOLDER)) inputUser = "";
        if (inputPass.equals(PASS_PLACEHOLDER)) inputPass = "";

        if (inputUser.equals(CORRECT_USERNAME) && inputPass.equals(CORRECT_PASSWORD)) {
            // Handle Remember Me
            if (rememberMeCheckBox.isSelected()) {
                prefs.put(PREF_REMEMBERED_USER, inputUser);
            } else {
                prefs.remove(PREF_REMEMBERED_USER);
            }

            JOptionPane.showMessageDialog(LoginFrame.this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new MainFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(LoginFrame.this, "Invalid Username or Password!", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        userField.setText(USER_PLACEHOLDER);
        userField.setForeground(Color.GRAY);

        passField.setEchoChar((char) 0);
        passField.setText(PASS_PLACEHOLDER);
        passField.setForeground(Color.GRAY);

        showPassCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}