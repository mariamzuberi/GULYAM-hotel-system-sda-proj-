package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class NewCustomer extends JFrame {
    private JPanel contentPane;
    private JTextField t1, t2, t3, t5, t6;
    JComboBox<String> comboBox;
    JRadioButton r1, r2;
    Choice c1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                NewCustomer frame = new NewCustomer();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public NewCustomer() {
        setBounds(530, 200, 850, 550);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i3 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(480, 10, 300, 500);
        add(l1);

        JLabel lblName = new JLabel("NEW CUSTOMER FORM");
        lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblName.setBounds(118, 11, 260, 53);
        contentPane.add(lblName);

        JLabel lblId = new JLabel("ID :");
        lblId.setBounds(35, 76, 200, 14);
        contentPane.add(lblId);

        comboBox = new JComboBox<>(new String[]{"Passport", "CNIC", "Voter Id", "Driving license"});
        comboBox.setBounds(271, 73, 150, 20);
        contentPane.add(comboBox);

        JLabel l2 = new JLabel("Number :");
        l2.setBounds(35, 111, 200, 14);
        contentPane.add(l2);

        t1 = new JTextField();
        t1.setBounds(271, 111, 150, 20);
        contentPane.add(t1);

        JLabel lblName_1 = new JLabel("Name :");
        lblName_1.setBounds(35, 151, 200, 14);
        contentPane.add(lblName_1);

        t2 = new JTextField();
        t2.setBounds(271, 151, 150, 20);
        contentPane.add(t2);

        JLabel lblGender = new JLabel("Gender :");
        lblGender.setBounds(35, 191, 200, 14);
        contentPane.add(lblGender);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        r1.setBackground(Color.WHITE);
        r1.setBounds(271, 191, 80, 12);
        contentPane.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        r2.setBackground(Color.WHITE);
        r2.setBounds(350, 191, 100, 12);
        contentPane.add(r2);

        JLabel lblCountry = new JLabel("Country :");
        lblCountry.setBounds(35, 231, 200, 14);
        contentPane.add(lblCountry);

        JLabel lblReserveRoomNumber = new JLabel("Allocated Room Number :");
        lblReserveRoomNumber.setBounds(35, 274, 200, 14);
        contentPane.add(lblReserveRoomNumber);

        c1 = new Choice();
        c1.setBounds(271, 274, 150, 20);
        contentPane.add(c1);

        try {
            conn c = conn.getInstance();
            Statement s = c.getStatement();
ResultSet rs = s.executeQuery("SELECT roomnumber FROM room WHERE availability = 'Available'");
            while (rs.next()) {
                c1.add(rs.getString("roomnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblCheckInStatus = new JLabel("Checked-In :");
        lblCheckInStatus.setBounds(35, 316, 200, 14);
        contentPane.add(lblCheckInStatus);

        JLabel lblDeposite = new JLabel("Deposit :");
        lblDeposite.setBounds(35, 359, 200, 14);
        contentPane.add(lblDeposite);

        t3 = new JTextField(); // Country
        t3.setBounds(271, 231, 150, 20);
        contentPane.add(t3);

        t5 = new JTextField(); // Check-In
        t5.setBounds(271, 316, 150, 20);
        contentPane.add(t5);

        t6 = new JTextField(); // Deposit
        t6.setBounds(271, 359, 150, 20);
        contentPane.add(t6);

        JButton btnNewButton = new JButton("Add");
        btnNewButton.setBounds(100, 430, 120, 30);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setForeground(Color.WHITE);
        contentPane.add(btnNewButton);

        btnNewButton.addActionListener(e -> {
            try {
                conn c = conn.getInstance();
                Statement s = c.getStatement();

                String radio = null;
                if (r1.isSelected()) {
                    radio = "Male";
                } else if (r2.isSelected()) {
                    radio = "Female";
                }

                String s1 = (String) comboBox.getSelectedItem(); // ID type
                String s2 = t1.getText(); // Number
                String s3 = t2.getText(); // Name
                String s4 = radio;        // Gender
                String s5 = t3.getText(); // Country
                String s6 = c1.getSelectedItem(); // Room No
                String s7 = t5.getText(); // Checked-In
                String s8 = t6.getText(); // Deposit

                String q1 = "INSERT INTO customer VALUES('" + s1 + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "','" + s8 + "')";
                String q2 = "UPDATE room SET availability = 'Occupied' WHERE roomnumber = '" + s6 + "'";

                s.executeUpdate(q1);
                s.executeUpdate(q2);

                JOptionPane.showMessageDialog(null, "Customer Added Successfully");
                new Reception().setVisible(true);
                setVisible(false);

            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "SQL Error: " + e1.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number");
            }
        });

        JButton btnExit = new JButton("Back");
        btnExit.setBounds(260, 430, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        btnExit.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });

        getContentPane().setBackground(Color.WHITE);
    }
}
