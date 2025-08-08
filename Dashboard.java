package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Dashboard extends JFrame {

    // Singleton instance
    private static Dashboard instance;

    // Public method to get the single instance
    public static Dashboard getInstance() {
        if (instance == null) {
            instance = new Dashboard();
        }
        return instance;
    }

    // Private constructor to prevent external instantiation
    private Dashboard() {
        super("GULYAM HOTEL MANAGEMENT SYSTEM");
        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen

        // Background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 1920, 1080);
        add(background);

        // Title
        JLabel title = new JLabel("WELCOME TO GULYAM HOTEL");
        title.setBounds(550, 80, 1000, 60);
        title.setFont(new Font("Serif", Font.BOLD, 45));
        title.setForeground(Color.WHITE);
        background.add(title);

        // Top-right mini menu bar
        JMenuBar topMenu = new JMenuBar();
        topMenu.setBounds(1550, 0, 400, 30);
        topMenu.setBackground(new Color(255, 255, 255, 150));

        JMenu aboutMenu = new JMenu("Options");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem about = new JMenuItem("About");

        exit.addActionListener(e -> System.exit(0));
        about.addActionListener(e -> JOptionPane.showMessageDialog(this, "GULYAM Hotel System\nVersion 1.0"));

        aboutMenu.add(about);
        aboutMenu.add(exit);
        topMenu.add(aboutMenu);
        background.add(topMenu);

        // Center panel with modern buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setBounds(700, 200, 500, 350);
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridLayout(4, 1, 25, 25));
        background.add(centerPanel);

        // Button: Reception
        JButton receptionBtn = createStyledButton("RECEPTION PANEL", "icons/reception.jpg", new Color(0, 123, 255));
        receptionBtn.addActionListener(e -> new Reception());
        centerPanel.add(receptionBtn);

        // Button: Add Employee
        JButton empBtn = createStyledButton("ADD EMPLOYEE", "icons/employee.jpg", new Color(40, 167, 69));
        empBtn.addActionListener(e -> {
            try {
                new AddEmployee().setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        centerPanel.add(empBtn);

        // Button: Add Room
        JButton roomBtn = createStyledButton("ADD ROOMS", "icons/room.jpg", new Color(255, 193, 7));
        roomBtn.addActionListener(e -> {
            try {
                new AddRoom().setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        centerPanel.add(roomBtn);

        // Button: Add Driver
        JButton driverBtn = createStyledButton("ADD DRIVERS", "icons/driver.jpg", new Color(220, 53, 69));
        driverBtn.addActionListener(e -> {
            try {
                new AddDrivers().setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        centerPanel.add(driverBtn);

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    // Utility to create a styled button with icon and hover effect
    private JButton createStyledButton(String text, String iconPath, Color bgColor) {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconPath));
        Image scaledIcon = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JButton button = new JButton(text, new ImageIcon(scaledIcon));

        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setHorizontalAlignment(SwingConstants.LEFT);

        // Hover effect
        Color originalColor = bgColor;
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(originalColor.darker());
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(originalColor);
            }
        });

        return button;
    }

    // Entry point of the application
    public static void main(String[] args) {
        Dashboard.getInstance().setVisible(true);
    }
}