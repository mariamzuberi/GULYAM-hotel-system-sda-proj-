package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

// Step 1: Create the Bridge Interface
interface DepartmentDataLoader {
    ResultSet loadData() throws SQLException;
}

// Step 2: Create a Concrete Implementation
class SQLDepartmentDataLoader implements DepartmentDataLoader {
    public ResultSet loadData() throws SQLException {
        conn c = conn.getInstance();
        Statement s = c.getStatement();  // Get the Statement from conn properly
        String sql = "SELECT * FROM Department";
        return s.executeQuery(sql);     // Use the statement you got
    }
}


public class Department extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    
    // Step 3: Use the abstraction
    private DepartmentDataLoader dataLoader;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Department frame = new Department(new SQLDepartmentDataLoader());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Department(DepartmentDataLoader loader) throws SQLException {
        this.dataLoader = loader;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 200, 700, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        table = new JTable();
        table.setBounds(0, 40, 700, 350);
        contentPane.add(table);

        JButton btnNewButton = new JButton("Load Data");
        btnNewButton.setBounds(170, 410, 120, 30);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setForeground(Color.WHITE);
        contentPane.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs = dataLoader.loadData();
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to load data.");
                }
            }
        });

        JButton btnNewButton_1 = new JButton("Back");
        btnNewButton_1.setBounds(400, 410, 120, 30);
        btnNewButton_1.setBackground(Color.BLACK);
        btnNewButton_1.setForeground(Color.WHITE);
        contentPane.add(btnNewButton_1);

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });

        lblNewLabel = new JLabel("Department");
        lblNewLabel.setBounds(145, 11, 105, 14);
        contentPane.add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Budget");
        lblNewLabel_1.setBounds(431, 11, 75, 14);
        contentPane.add(lblNewLabel_1);

        getContentPane().setBackground(Color.WHITE);
    }
}
