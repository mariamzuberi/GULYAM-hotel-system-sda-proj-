package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import net.proteanit.sql.DbUtils;
import java.sql.*;

public class ManagerInfo extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JLabel lblNewLabel, lblJob, lblName, lblDepartment;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ManagerInfo frame = new ManagerInfo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ManagerInfo() {
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
        btnLoadData.addActionListener(e -> {
            try {
                conn c = conn.getInstance();             // ✅ Use singleton
                Statement s = c.getStatement();          // ✅ Get Statement
                String query = "SELECT * FROM Employee WHERE job = 'Manager'";
                ResultSet rs = s.executeQuery(query);    // ✅ Execute query
                table.setModel(DbUtils.resultSetToTableModel(rs)); // ✅ Fill table
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        btnLoadData.setBounds(350, 500, 120, 30);
        btnLoadData.setBackground(Color.BLACK);
        btnLoadData.setForeground(Color.WHITE);
        contentPane.add(btnLoadData);

        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
        btnExit.setBounds(510, 500, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

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
    }
}
