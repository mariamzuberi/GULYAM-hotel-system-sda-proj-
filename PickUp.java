package hotel.management.system;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;

public class PickUp extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private Choice c1;

    public PickUp() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblPickUpService = new JLabel("Pick Up Service");
        lblPickUpService.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPickUpService.setBounds(90, 11, 200, 25);
        contentPane.add(lblPickUpService);

        JLabel lblTypeOfCar = new JLabel("Type of Car");
        lblTypeOfCar.setBounds(32, 97, 89, 14);
        contentPane.add(lblTypeOfCar);

        // Car brand selection dropdown
        c1 = new Choice();
        try {
            conn c = conn.getInstance();
            Statement s = c.getStatement();
            ResultSet rs = s.executeQuery("SELECT DISTINCT brand FROM driver");
            while (rs.next()) {
                c1.add(rs.getString("brand"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(123, 94, 150, 25);
        contentPane.add(c1);

        // Table to display drivers
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 233, 800, 250);
        contentPane.add(scrollPane);

        // Table column labels
        String[] labels = {"Name", "Age", "Gender", "Company", "Brand", "Available", "Location"};
        int[] positions = {24, 165, 264, 366, 486, 600, 700};

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setBounds(positions[i], 208, 80, 14);
            contentPane.add(lbl);
        }

        // Display button
        JButton btnDisplay = new JButton("Display");
        btnDisplay.setBounds(200, 500, 120, 30);
        btnDisplay.setBackground(Color.BLACK);
        btnDisplay.setForeground(Color.WHITE);
        contentPane.add(btnDisplay);

        // Back button
        JButton btnExit = new JButton("Back");
        btnExit.setBounds(420, 500, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        getContentPane().setBackground(Color.WHITE);

        // Command pattern: Display drivers by selected car brand
        Command displayCommand = new DisplayDriversCommand(table, c1);
        btnDisplay.addActionListener(e -> displayCommand.execute());

        btnExit.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PickUp frame = new PickUp();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
