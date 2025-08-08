package hotel.management.system;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import net.proteanit.sql.DbUtils;

public class SearchRoom extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private Choice c1;
    private JCheckBox checkRoom;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SearchRoom frame = new SearchRoom();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SearchRoom() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 700, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSearchForRoom = new JLabel("Search For Room");
        lblSearchForRoom.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblSearchForRoom.setBounds(250, 11, 186, 31);
        contentPane.add(lblSearchForRoom);

        JLabel lblRoomAvailable = new JLabel("Room Bed Type:");
        lblRoomAvailable.setBounds(50, 73, 100, 14);
        contentPane.add(lblRoomAvailable);

        checkRoom = new JCheckBox("Only display Available");
        checkRoom.setBounds(400, 69, 205, 23);
        checkRoom.setBackground(Color.WHITE);
        contentPane.add(checkRoom);

        c1 = new Choice();
        c1.add("Single Bed");
        c1.add("Double Bed");
        c1.setBounds(160, 70, 120, 20);
        contentPane.add(c1);

        JLabel lblRoomType = new JLabel("Room Number");
        lblRoomType.setBounds(23, 162, 96, 14);
        contentPane.add(lblRoomType);

        JLabel lblAvailability = new JLabel("Availability");
        lblAvailability.setBounds(175, 162, 120, 14);
        contentPane.add(lblAvailability);

        JLabel lblCleanStatus = new JLabel("Clean Status");
        lblCleanStatus.setBounds(306, 162, 96, 14);
        contentPane.add(lblCleanStatus);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(458, 162, 46, 14);
        contentPane.add(lblPrice);

        JLabel lblBedType = new JLabel("Bed Type");
        lblBedType.setBounds(580, 162, 96, 14);
        contentPane.add(lblBedType);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 187, 700, 200);
        contentPane.add(scrollPane);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(200, 400, 120, 30);
        btnSearch.setBackground(Color.BLACK);
        btnSearch.setForeground(Color.WHITE);
        contentPane.add(btnSearch);

        JButton btnExit = new JButton("Back");
        btnExit.setBounds(380, 400, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        getContentPane().setBackground(Color.WHITE);

        // Button Actions
        btnSearch.addActionListener(e -> searchRooms());
        btnExit.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
    }

    private void searchRooms() {
        String selectedBed = c1.getSelectedItem();
        boolean onlyAvailable = checkRoom.isSelected();

        String query = "SELECT * FROM Room WHERE bed_type = '" + selectedBed + "'";
        if (onlyAvailable) {
            query += " AND availability = 'Available'";
        }

        System.out.println("Query: " + query); // Debug

        try {
            conn c = conn.getInstance();
            ResultSet rs = c.getStatement().executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data!");
        }
    }
}
