package GUI;

import Folder.EmployeeManager;
import javax.swing.*;
import java.awt.*;

//==============================================================
// MainFrame  (the main menu window)
//--------------------------------------------------------------
// Layout:  BorderLayout
//   NORTH  -> Header (dark blue title bar)
//   CENTER -> 6 menu buttons
//==============================================================
public class MainFrame extends JFrame {

    //==========================
    // EmployeeManager object (holds all employee data)
    //==========================
    private EmployeeManager manager;

    //==========================
    // Colors and Fonts (used everywhere in this window)
    //==========================
    private final Color DARK  = new Color(30, 45, 75);
    private final Color WHITE = Color.WHITE;

    //==========================
    // Constructors
    //==========================
    public MainFrame() {
        this(new EmployeeManager()); // Default constructor creates new EmployeeManager
    }

    public MainFrame(EmployeeManager manager) {
        this.manager = manager;

        //==========================
        // Window settings
        //==========================
        setTitle("Employee Management System");
        setSize(460, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);   // open in the center of the screen
        setLayout(new BorderLayout());

        //==========================
        // Build the parts
        //==========================
        add(buildHeader(), BorderLayout.NORTH);
        add(buildMenu(),   BorderLayout.CENTER);

        //==========================
        // Show the window
        //==========================
        setVisible(true);
    }

    //==========================================================
    // HEADER PANEL
    //==========================================================
    private JPanel buildHeader() {
        JPanel header = new JPanel();
        header.setBackground(DARK);
        header.setPreferredSize(new Dimension(0, 80));
        header.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 22));

        JLabel title = new JLabel("👥  EMPLOYEE MANAGEMENT SYSTEM");
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
        title.setForeground(WHITE);

        header.add(title);
        return header;
    }

    //==========================================================
    // MENU PANEL (the 6 buttons)
    //==========================================================
    private JPanel buildMenu() {
        JPanel menu = new JPanel(new GridBagLayout());
        menu.setBackground(new Color(245, 247, 250));
        menu.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(8, 0, 8, 0);   // space between buttons
        gc.ipady = 12;                         // make buttons taller

        //==========================
        // Create the 6 buttons
        //==========================
        JButton addBtn    = makeMenuButton("➕  Add Employee");
        JButton searchBtn = makeMenuButton("🔍  Search Employee");
        JButton updateBtn = makeMenuButton("✏️  Update Employee");
        JButton viewBtn   = makeMenuButton("📋  View All Employees");
        JButton deleteBtn = makeMenuButton("🗑  Fired  Employee");
        JButton exitBtn   = makeMenuButton("🚪  Exit");

        //==========================
        // Button actions (open the other windows)
        //==========================
        addBtn.addActionListener(e -> new AddEmployeeFrame(manager).setVisible(true));
        searchBtn.addActionListener(e -> new SearchEmployeeFrame(manager).setVisible(true));
        updateBtn.addActionListener(e -> new UpdateEmployeeFrame(manager).setVisible(true));
        viewBtn.addActionListener(e -> new ViewEmployeeFrame(manager).setVisible(true));
        deleteBtn.addActionListener(e -> new DeleteEmployeeFrame(manager).setVisible(true));
        exitBtn.addActionListener(e -> System.exit(0));

        //==========================
        // Add the buttons one by one
        //==========================
        gc.gridy = 0; menu.add(addBtn, gc);
        gc.gridy = 1; menu.add(searchBtn, gc);
        gc.gridy = 2; menu.add(updateBtn, gc);
        gc.gridy = 3; menu.add(viewBtn, gc);
        gc.gridy = 4; menu.add(deleteBtn, gc);
        gc.gridy = 5; menu.add(exitBtn, gc);

        return menu;
    }

    //==========================================================
    // HELPER: make one nice menu button
    //==========================================================
    private JButton makeMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        button.setForeground(new Color(40, 40, 40));
        button.setBackground(WHITE);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 225), 1),
                BorderFactory.createEmptyBorder(6, 25, 6, 6)));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}