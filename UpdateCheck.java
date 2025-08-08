package hotel.management.system;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class UpdateCheck extends JFrame {
    private JPanel contentPane;
    private JTextField txt_ID, txt_Room, txt_Status, txt_Date, txt_Time, txt_Payment;
    private Choice c1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UpdateCheck frame = new UpdateCheck();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UpdateCheck() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 950, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Check-In Details");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitle.setBounds(124, 11, 222, 25);
        contentPane.add(lblTitle);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel l1 = new JLabel(i1);
        l1.setBounds(450, 70, 476, 270);
        contentPane.add(l1);

        JLabel lblID = new JLabel("ID:");
        lblID.setBounds(25, 88, 46, 14);
        contentPane.add(lblID);

        c1 = new Choice();
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
        c1.setBounds(248, 85, 140, 20);
        contentPane.add(c1);

        JLabel lblRoom = new JLabel("Room Number:");
        lblRoom.setBounds(25, 129, 107, 14);
        contentPane.add(lblRoom);

        txt_ID = new JTextField();
        txt_ID.setBounds(248, 126, 140, 20);
        contentPane.add(txt_ID);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(25, 174, 97, 14);
        contentPane.add(lblName);

        txt_Status = new JTextField();
        txt_Status.setBounds(248, 171, 140, 20);
        contentPane.add(txt_Status);

        JLabel lblCheckIn = new JLabel("Checked-in:");
        lblCheckIn.setBounds(25, 216, 107, 14);
        contentPane.add(lblCheckIn);

        txt_Date = new JTextField();
        txt_Date.setBounds(248, 216, 140, 20);
        contentPane.add(txt_Date);

        JLabel lblPaid = new JLabel("Amount Paid (Rs):");
        lblPaid.setBounds(25, 261, 107, 14);
        contentPane.add(lblPaid);

        txt_Time = new JTextField();
        txt_Time.setBounds(248, 258, 140, 20);
        contentPane.add(txt_Time);

        JLabel lblPending = new JLabel("Pending Amount (Rs):");
        lblPending.setBounds(25, 302, 150, 14);
        contentPane.add(lblPending);

        txt_Payment = new JTextField();
        txt_Payment.setBounds(248, 299, 140, 20);
        contentPane.add(txt_Payment);

        JButton btnCheck = new JButton("Check");
        btnCheck.setBounds(56, 378, 89, 23);
        btnCheck.setBackground(Color.BLACK);
        btnCheck.setForeground(Color.WHITE);
        contentPane.add(btnCheck);

        btnCheck.addActionListener(e -> {
            try {
                String id = c1.getSelectedItem();
                conn c = conn.getInstance();
                Statement s = c.getStatement();

                ResultSet rs1 = s.executeQuery("SELECT * FROM customer WHERE number = '" + id + "'");
                if (rs1.next()) {
                    txt_ID.setText(rs1.getString("room_number"));
                    txt_Status.setText(rs1.getString("name"));
                    txt_Date.setText(rs1.getString("status"));
                    txt_Time.setText(rs1.getString("deposit"));
                }

                String roomNumber = txt_ID.getText();
                ResultSet rs2 = s.executeQuery("SELECT * FROM room WHERE room_number = '" + roomNumber + "'");
                if (rs2.next()) {
                    int total = rs2.getInt("price");
                    int paid = Integer.parseInt(txt_Time.getText());
                    txt_Payment.setText(String.valueOf(total - paid));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Check failed.");
            }
        });

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(168, 378, 89, 23);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        contentPane.add(btnUpdate);

        btnUpdate.addActionListener(e -> {
            try {
                conn c = conn.getInstance();
                Statement s = c.getStatement();

                String id = c1.getSelectedItem();
                String room = txt_ID.getText();
                String name = txt_Status.getText();
                String status = txt_Date.getText();
                String deposit = txt_Time.getText();

                String query = "UPDATE customer SET room_number = '" + room + 
                        "', name = '" + name + 
                        "', status = '" + status + 
                        "', deposit = '" + deposit + 
                        "' WHERE number = '" + id + "'";

                s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                new Reception().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Update Failed");
            }
        });

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(281, 378, 89, 23);
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
