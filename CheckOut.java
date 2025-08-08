package hotel.management.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class CheckOut extends JFrame {
    private JPanel contentPane;
    private JTextField t1;
    private Choice c1;
    private CustomerDatabaseAdapter dbAdapter;
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CheckOut frame = new CheckOut(new MySQLCustomerAdapter());
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public CheckOut(CustomerDatabaseAdapter adapter) {
        this.dbAdapter = adapter;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 800, 294);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        Image i3 = i1.getImage().getScaledInstance(400, 225, Image.SCALE_DEFAULT);
        JLabel l1 = new JLabel(new ImageIcon(i3));
        l1.setBounds(300, 0, 500, 225);
        add(l1);
        JLabel lblCheckOut = new JLabel("Check Out ");
        lblCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCheckOut.setBounds(70, 11, 140, 35);
        contentPane.add(lblCheckOut);
        JLabel lblName = new JLabel("Number :");
        lblName.setBounds(20, 85, 80, 14);
        contentPane.add(lblName);

        c1 = new Choice();
        List<String> customerNumbers = dbAdapter.getCustomerNumbers();
        for (String number : customerNumbers) {
            c1.add(number);
        }
        c1.setBounds(130, 82, 150, 20);
        contentPane.add(c1);
        JButton l2 = new JButton(new ImageIcon(
            new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"))
                .getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        l2.setBounds(290, 82, 20, 20);
        add(l2);
        l2.addActionListener(e -> {
            String number = c1.getSelectedItem();
            String room = dbAdapter.getRoomNumberByCustomerNumber(number);
            t1.setText(room);
        });
        JLabel lblRoomNumber = new JLabel("Room Number:");
        lblRoomNumber.setBounds(20, 132, 86, 20);
        contentPane.add(lblRoomNumber);
        t1 = new JTextField();
        t1.setBounds(130, 132, 150, 20);
        contentPane.add(t1);
        JButton btnCheckOut = new JButton("Check Out");
        btnCheckOut.addActionListener(e -> {
            String id = c1.getSelectedItem();
            String roomNumber = t1.getText();
            boolean success = dbAdapter.checkOutCustomer(id, roomNumber);
            if (success) {
                JOptionPane.showMessageDialog(null, "Check Out Successful");
                new Reception().setVisible(true);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Check Out Failed");
            }
        });
        btnCheckOut.setBounds(50, 200, 100, 25);
        btnCheckOut.setBackground(Color.BLACK);
        btnCheckOut.setForeground(Color.WHITE);
        contentPane.add(btnCheckOut);

        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
        btnExit.setBounds(160, 200, 100, 25);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        getContentPane().setBackground(Color.WHITE);
    }
}
