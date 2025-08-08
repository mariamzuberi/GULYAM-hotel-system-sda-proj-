package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Reception extends JFrame {
    private JPanel contentPane;

    public static void main(String[] args) {
        new Reception();
    }

    public Reception() {
        setBounds(530, 200, 850, 610); // Increased height
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
        Image i3 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        JLabel l1 = new JLabel(new ImageIcon(i3));
        l1.setBounds(250, 30, 500, 470);
        add(l1);

        JButton btnNewCustomerForm = new JButton("New Customer Form");
        btnNewCustomerForm.setBounds(10, 30, 200, 30);
        btnNewCustomerForm.setBackground(Color.BLACK);
        btnNewCustomerForm.setForeground(Color.WHITE);
        btnNewCustomerForm.addActionListener(e -> {
            try {
                new NewCustomer().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnNewCustomerForm);

        JButton btnRoom = new JButton("Room");
        btnRoom.setBounds(10, 70, 200, 30);
        btnRoom.setBackground(Color.BLACK);
        btnRoom.setForeground(Color.WHITE);
        btnRoom.addActionListener(e -> {
            try {
                new Room().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnRoom);

        JButton btnDepartment = new JButton("Department");
        btnDepartment.setBounds(10, 110, 200, 30);
        btnDepartment.setBackground(Color.BLACK);
        btnDepartment.setForeground(Color.WHITE);
        btnDepartment.addActionListener(e -> {
            try {
                DepartmentDataLoader loader = new SQLDepartmentDataLoader();
                new Department(loader).setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnDepartment);

        JButton btnEmployee = new JButton("All Employee Info");
        btnEmployee.setBounds(10, 150, 200, 30);
        btnEmployee.setBackground(Color.BLACK);
        btnEmployee.setForeground(Color.WHITE);
        btnEmployee.addActionListener(e -> {
            try {
                new Employee().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnEmployee);

        JButton btnCustomerInfo = new JButton("Customer Info");
        btnCustomerInfo.setBounds(10, 190, 200, 30);
        btnCustomerInfo.setBackground(Color.BLACK);
        btnCustomerInfo.setForeground(Color.WHITE);
        btnCustomerInfo.addActionListener(e -> {
            try {
                CustomerDataModel model = new CustomerDataModel();
                new CustomerInfo(model).setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnCustomerInfo);

        JButton btnManagerInfo = new JButton("Manager Info");
        btnManagerInfo.setBounds(10, 230, 200, 30);
        btnManagerInfo.setBackground(Color.BLACK);
        btnManagerInfo.setForeground(Color.WHITE);
        btnManagerInfo.addActionListener(e -> {
            try {
                new ManagerInfo().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnManagerInfo);

        JButton btnCheckOut = new JButton("Check Out");
        btnCheckOut.setBounds(10, 270, 200, 30);
        btnCheckOut.setBackground(Color.BLACK);
        btnCheckOut.setForeground(Color.WHITE);
        btnCheckOut.addActionListener(e -> {
            try {
                CustomerDatabaseAdapter adapter = new MySQLCustomerAdapter(); //adapter pattern
                new CheckOut(adapter).setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnCheckOut);

        JButton btnUpdateCheck = new JButton("Update Check Status");
        btnUpdateCheck.setBounds(10, 310, 200, 30);
        btnUpdateCheck.setBackground(Color.BLACK);
        btnUpdateCheck.setForeground(Color.WHITE);
        btnUpdateCheck.addActionListener(e -> {
            try {
                new UpdateCheck().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnUpdateCheck);

        JButton btnUpdateRoom = new JButton("Update Room Status");
        btnUpdateRoom.setBounds(10, 350, 200, 30);
        btnUpdateRoom.setBackground(Color.BLACK);
        btnUpdateRoom.setForeground(Color.WHITE);
        btnUpdateRoom.addActionListener(e -> {
            try {
                new UpdateRoom().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnUpdateRoom);

        JButton btnPickup = new JButton("Pick up Service");
        btnPickup.setBounds(10, 390, 200, 30);
        btnPickup.setBackground(Color.BLACK);
        btnPickup.setForeground(Color.WHITE);
        btnPickup.addActionListener(e -> {
            try {
                new PickUp().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnPickup);

        JButton btnSearchRoom = new JButton("Search Room");
        btnSearchRoom.setBounds(10, 430, 200, 30);
        btnSearchRoom.setBackground(Color.BLACK);
        btnSearchRoom.setForeground(Color.WHITE);
        btnSearchRoom.addActionListener(e -> {
            try {
                new SearchRoom().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnSearchRoom);

        JButton btnPayment = new JButton("Payment");
        btnPayment.setBounds(10, 470, 200, 30);
        btnPayment.setBackground(Color.BLACK);
        btnPayment.setForeground(Color.WHITE);
        btnPayment.addActionListener(e -> {
            try {
                new PaymentGUI().createGUI();
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnPayment);

        JButton btnLogout = new JButton("Log Out");
        btnLogout.setBounds(10, 510, 200, 30);
        btnLogout.setBackground(Color.BLACK);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.addActionListener(e -> {
            try {
                new Login().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnLogout);

        getContentPane().setBackground(Color.white);
        setVisible(true);
    }
}
