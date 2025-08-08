package hotel.management.system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Employee extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JLabel lblNewLabel, lblJob, lblName, lblDepartment;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Employee frame = new Employee();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Employee() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(430, 200, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        table = new JTable();
        table.setBounds(0, 34, 1000, 450);
        contentPane.add(table);

        JButton btnLoadData = new JButton("Load Data");
        btnLoadData.setBounds(350, 500, 120, 30);
        btnLoadData.setBackground(Color.BLACK);
        btnLoadData.setForeground(Color.WHITE);
        contentPane.add(btnLoadData);

        JButton btnExit = new JButton("Back");
        btnExit.setBounds(510, 500, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        // Table column labels
        lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(41, 11, 46, 14);
        contentPane.add(lblNewLabel);

        lblJob = new JLabel("Age");
        lblJob.setBounds(159, 11, 46, 14);
        contentPane.add(lblJob);

        lblName = new JLabel("Gender");
        lblName.setBounds(273, 11, 60, 14);
        contentPane.add(lblName);

        lblDepartment = new JLabel("Job");
        lblDepartment.setBounds(416, 11, 86, 14);
        contentPane.add(lblDepartment);

        JLabel l1 = new JLabel("Salary");
        l1.setBounds(536, 11, 86, 14);
        contentPane.add(l1);

        JLabel l2 = new JLabel("Phone");
        l2.setBounds(656, 11, 86, 14);
        contentPane.add(l2);

        JLabel l4 = new JLabel("Gmail");
        l4.setBounds(896, 11, 86, 14);
        contentPane.add(l4);

        getContentPane().setBackground(Color.WHITE);

        // Load Data Button
        btnLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = conn.getInstance();               // singleton usage
                    Statement s = c.getStatement();            // access to Statement
                    String query = "SELECT * FROM Employee";
                    ResultSet rs = s.executeQuery(query);      // correct way

                    // Adapter pattern usage
                    TableDataAdapter adapter = new ResultSetToTableModelAdapter(); //  your custom adapter
                    TableModel model = adapter.convert(rs);
                    table.setModel(model);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Back Button
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
    }
}
