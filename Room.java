package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class Room extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JLabel lblAvailability, lblCleanStatus, lblNewLabel, lblNewLabel_1, lblRoomNumber;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Room frame = new Room();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Room() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 200, 1100, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i3 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel backgroundLabel = new JLabel(i2);
        backgroundLabel.setBounds(500, 0, 600, 600);
        add(backgroundLabel);

        // Table setup
        table = new JTable();
        table.setBounds(0, 40, 500, 400);
        contentPane.add(table);

        // Load Data Button
        JButton btnLoadData = new JButton("Load Data");
        btnLoadData.addActionListener(e -> {
            try {
                conn c = conn.getInstance();
                Statement s = c.getStatement();
                String query = "SELECT * FROM Room";
                ResultSet rs = s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btnLoadData.setBounds(100, 470, 120, 30);
        btnLoadData.setBackground(Color.BLACK);
        btnLoadData.setForeground(Color.WHITE);
        contentPane.add(btnLoadData);

        // Back Button
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
        btnBack.setBounds(290, 470, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        // Column Labels
        lblRoomNumber = new JLabel("Room Number");
        lblRoomNumber.setBounds(12, 15, 90, 14);
        contentPane.add(lblRoomNumber);

        lblAvailability = new JLabel("Availability");
        lblAvailability.setBounds(119, 15, 69, 14);
        contentPane.add(lblAvailability);

        lblCleanStatus = new JLabel("Clean Status");
        lblCleanStatus.setBounds(216, 15, 76, 14);
        contentPane.add(lblCleanStatus);

        lblNewLabel = new JLabel("Price");
        lblNewLabel.setBounds(330, 15, 46, 14);
        contentPane.add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Bed Type");
        lblNewLabel_1.setBounds(417, 15, 76, 14);
        contentPane.add(lblNewLabel_1);

        getContentPane().setBackground(Color.WHITE);
    }
}
