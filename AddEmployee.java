package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AddEmployee extends JFrame {

    JTextField textField, textField_1, textField_3, textField_4, textField_5, textField_6;
    JComboBox<String> c1;
    JRadioButton maleRadio, femaleRadio;

    // Composite root to hold all added employees
    EmployeeComposite root = new EmployeeComposite();

    public AddEmployee() {
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("ADD EMPLOYEE DETAILS");

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(900, 600);
        setLocation(530, 200);
        setLayout(null);

        JLabel nameLabel = new JLabel("NAME");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        nameLabel.setBounds(60, 30, 150, 27);
        add(nameLabel);

        textField = new JTextField();
        textField.setBounds(200, 30, 150, 27);
        add(textField);

        JLabel ageLabel = new JLabel("AGE");
        ageLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        ageLabel.setBounds(60, 80, 150, 27);
        add(ageLabel);

        textField_1 = new JTextField();
        textField_1.setBounds(200, 80, 150, 27);
        add(textField_1);

        JLabel genderLabel = new JLabel("GENDER");
        genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        genderLabel.setBounds(60, 120, 150, 27);
        add(genderLabel);

        maleRadio = new JRadioButton("MALE");
        maleRadio.setBackground(Color.WHITE);
        maleRadio.setBounds(200, 120, 70, 27);
        add(maleRadio);

        femaleRadio = new JRadioButton("FEMALE");
        femaleRadio.setBackground(Color.WHITE);
        femaleRadio.setBounds(280, 120, 100, 27);
        add(femaleRadio);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        JLabel jobLabel = new JLabel("JOB");
        jobLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        jobLabel.setBounds(60, 170, 150, 27);
        add(jobLabel);

        String jobs[] = {
            "Front Desk Clerks", "Porters", "Housekeeping", "Kitchen Staff",
            "Room Service", "Waiter/Waitress", "Manager", "Accountant", "Chef"
        };
        c1 = new JComboBox<>(jobs);
        c1.setBackground(Color.WHITE);
        c1.setBounds(200, 170, 150, 30);
        add(c1);

        JLabel salaryLabel = new JLabel("SALARY");
        salaryLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        salaryLabel.setBounds(60, 220, 150, 27);
        add(salaryLabel);

        textField_3 = new JTextField();
        textField_3.setBounds(200, 220, 150, 27);
        add(textField_3);

        JLabel phoneLabel = new JLabel("PHONE");
        phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        phoneLabel.setBounds(60, 270, 150, 27);
        add(phoneLabel);

        textField_4 = new JTextField();
        textField_4.setBounds(200, 270, 150, 27);
        add(textField_4);

        JLabel cnicLabel = new JLabel("CNIC");
        cnicLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        cnicLabel.setBounds(60, 320, 150, 27);
        add(cnicLabel);

        textField_5 = new JTextField();
        textField_5.setBounds(200, 320, 150, 27);
        add(textField_5);

        JLabel emailLabel = new JLabel("EMAIL");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        emailLabel.setBounds(60, 370, 150, 27);
        add(emailLabel);

        textField_6 = new JTextField();
        textField_6.setBounds(200, 370, 150, 27);
        add(textField_6);

        JButton saveButton = new JButton("SAVE");
        saveButton.setBounds(200, 420, 150, 30);
        saveButton.setBackground(Color.BLACK);
        saveButton.setForeground(Color.WHITE);
        add(saveButton);

        JLabel heading = new JLabel("ADD EMPLOYEE DETAILS");
        heading.setForeground(Color.BLUE);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 31));
        heading.setBounds(450, 24, 442, 35);
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i3 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel image = new JLabel(i2);
        image.setBounds(410, 80, 480, 410);
        add(image);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String gender = maleRadio.isSelected() ? "male" : (femaleRadio.isSelected() ? "female" : "");

                // Create employee leaf and add to root composite
                EmployeeLeaf empLeaf = new EmployeeLeaf(
                        textField.getText(),
                        textField_1.getText(),
                        gender,
                        (String) c1.getSelectedItem(),
                        textField_3.getText(),
                        textField_4.getText(),
                        textField_6.getText()
                );
                root.add(empLeaf); // add to composite

                try {
                    conn c = conn.getInstance();
                    Statement s = c.getStatement();
                    String str = "INSERT INTO employee (name, age, gender, job, salary, phone, email) VALUES ('" 
                        + empLeaf.name + "', '" + empLeaf.age + "', '" + empLeaf.gender + "', '" + empLeaf.job + "', '" 
                        + empLeaf.salary + "', '" + empLeaf.phone + "', '" + empLeaf.email + "')";
                    s.executeUpdate(str);   
                    JOptionPane.showMessageDialog(null, "Employee Added");

                    // Show updated hierarchy in console
                    System.out.println("Current Employee Hierarchy:");
                    root.showEmployeeDetails();

                    setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
