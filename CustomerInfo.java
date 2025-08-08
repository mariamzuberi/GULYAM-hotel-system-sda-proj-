package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.sql.*;

// Observer interface
interface Observer {
    void update();
}

public class CustomerInfo extends JFrame implements Observer {

    private JPanel contentPane;
    private JTable table;
    private CustomerDataModel model;

    public CustomerInfo(CustomerDataModel model) {
        this.model = model;
        model.addObserver(this);  // Register this class as an observer

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 900, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
        btnBack.setBounds(450, 510, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        JButton btnLoadData = new JButton("Load Data");
        btnLoadData.addActionListener(e -> loadTableData());
        btnLoadData.setBounds(300, 510, 120, 30);
        btnLoadData.setBackground(Color.BLACK);
        btnLoadData.setForeground(Color.WHITE);
        contentPane.add(btnLoadData);

        contentPane.add(createLabel("ID", 31));
        contentPane.add(createLabel("Number", 150));
        contentPane.add(createLabel("Name", 270));
        contentPane.add(createLabel("Gender", 360));
        contentPane.add(createLabel("Country", 480));
        contentPane.add(createLabel("Room", 600));
        contentPane.add(createLabel("Check-in Status", 680));
        contentPane.add(createLabel("Deposit", 800));

        table = new JTable();
        table.setBounds(0, 40, 900, 450);
        contentPane.add(table);

        getContentPane().setBackground(Color.WHITE);

        loadTableData();  // Load data when form opens
    }

    private JLabel createLabel(String text, int x) {
        JLabel label = new JLabel(text);
        label.setBounds(x, 11, 120, 14);
        return label;
    }

    private void loadTableData() {
        try {
            conn c = conn.getInstance();                     //  Get singleton object
            Statement s = c.getStatement();                  //  Get Statement object
            String displayCustomersql = "SELECT * FROM Customer";
            ResultSet rs = s.executeQuery(displayCustomersql);  //  Run query
            table.setModel(DbUtils.resultSetToTableModel(rs)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Observer pattern method
    @Override
    public void update() {
        loadTableData();  // When model updates, refresh table
    }
}
