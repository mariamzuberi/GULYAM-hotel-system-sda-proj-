package hotel.management.system;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UpdateRoom extends JFrame {
    private JPanel contentPane;
    private JTextField txt_Ava, txt_Status, txt_Room;
    private Choice c1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UpdateRoom frame = new UpdateRoom();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UpdateRoom() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 1000, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i3 = i1.getImage().getScaledInstance(550, 250, Image.SCALE_DEFAULT);
        JLabel l1 = new JLabel(new ImageIcon(i3));
        l1.setBounds(400, 80, 600, 250);
        contentPane.add(l1);

        // Heading
        JLabel lblUpdateRoomStatus = new JLabel("Update Room Status");
        lblUpdateRoomStatus.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUpdateRoomStatus.setBounds(85, 11, 250, 34);
        contentPane.add(lblUpdateRoomStatus);

        // Guest ID
        JLabel lblGuestId = new JLabel("Guest ID:");
        lblGuestId.setBounds(27, 87, 90, 14);
        contentPane.add(lblGuestId);

        c1 = new Choice();
        c1.setBounds(160, 84, 140, 20);
        contentPane.add(c1);

        try {
            conn c = conn.getInstance();
            Statement s = c.getStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                c1.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Room Number
        JLabel lblRoomNumber = new JLabel("Room Number:");
        lblRoomNumber.setBounds(27, 133, 100, 14);
        contentPane.add(lblRoomNumber);

        txt_Room = new JTextField();
        txt_Room.setBounds(160, 130, 140, 20);
        contentPane.add(txt_Room);

        // Availability
        JLabel lblAvailability = new JLabel("Availability:");
        lblAvailability.setBounds(27, 187, 90, 14);
        contentPane.add(lblAvailability);

        txt_Ava = new JTextField();
        txt_Ava.setBounds(160, 184, 140, 20);
        contentPane.add(txt_Ava);

        // Clean Status
        JLabel lblCleanStatus = new JLabel("Clean Status:");
        lblCleanStatus.setBounds(27, 240, 90, 14);
        contentPane.add(lblCleanStatus);

        txt_Status = new JTextField();
        txt_Status.setBounds(160, 237, 140, 20);
        contentPane.add(txt_Status);

        // Button: Check
        JButton btnCheck = new JButton("Check");
        btnCheck.setBounds(120, 315, 89, 23);
        btnCheck.setBackground(Color.BLACK);
        btnCheck.setForeground(Color.WHITE);
        contentPane.add(btnCheck);

        btnCheck.addActionListener(e -> {
            String guestId = c1.getSelectedItem();
            try {
                conn c = conn.getInstance();
                Statement s = c.getStatement();

                ResultSet rs1 = s.executeQuery("SELECT room_number FROM customer WHERE number = '" + guestId + "'");
                if (rs1.next()) {
                    txt_Room.setText(rs1.getString("room_number"));
                }

                ResultSet rs2 = s.executeQuery("SELECT * FROM room WHERE room_number = '" + txt_Room.getText() + "'");
                if (rs2.next()) {
                    txt_Ava.setText(rs2.getString("availability"));
                    txt_Status.setText(rs2.getString("clean_status"));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Check failed!");
            }
        });

        // Button: Update
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(60, 355, 89, 23);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        contentPane.add(btnUpdate);

        btnUpdate.addActionListener(e -> {
            try {
                conn c = conn.getInstance();
                Statement s = c.getStatement();

                String updateQuery = "UPDATE room SET availability = '" + txt_Ava.getText()
                        + "', clean_status = '" + txt_Status.getText()
                        + "' WHERE room_number = '" + txt_Room.getText() + "'";

                s.executeUpdate(updateQuery);
                JOptionPane.showMessageDialog(null, "Room Updated Successfully");
                new Reception().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Update Failed");
            }
        });

        // Button: Back
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(180, 355, 89, 23);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });

        getContentPane().setBackground(Color.WHITE);
    }
}
