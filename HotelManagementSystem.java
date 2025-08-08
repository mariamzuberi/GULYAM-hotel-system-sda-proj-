package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class HotelManagementSystem extends JFrame implements ActionListener {

    JLabel l1;
    JButton b1;

    public HotelManagementSystem() {
        setSize(800, 400);
        setLayout(null);
        setLocationRelativeTo(null); // Center the window

        b1 = new JButton("Next");
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.BLACK);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        Image i3 = i1.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH);
        ImageIcon i2 = new ImageIcon(i3);
        l1 = new JLabel(i2);
        l1.setBounds(0, 0, 800, 400);
        l1.setLayout(null);

        JLabel lid = new JLabel("");
        lid.setBounds(30, 300, 700, 50);
        lid.setFont(new Font("serif", Font.BOLD, 30));
        lid.setForeground(Color.PINK);
        l1.add(lid);

        b1.setBounds(620, 300, 100, 40);
        l1.add(b1);
        add(l1);

        b1.addActionListener(this);
        setVisible(true);

        // Blinking animation
        new Thread(() -> {
            try {
                while (true) {
                    lid.setVisible(false);
                    Thread.sleep(500);
                    lid.setVisible(true);
                    Thread.sleep(500);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void actionPerformed(ActionEvent ae) {
        new Login(); // Open Login window
        this.setVisible(false);
    }

    public static void main(String[] args) {
        new HotelManagementSystem();
    }
}
