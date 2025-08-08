package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    JTextField t1;
    JPasswordField t2;
    JButton b1, b2;
    LoginHandler handlerChain;

    public Login() {
        super("GULYAM HOTEL - Login");
        setLayout(new BorderLayout());

        // Left Panel
        JPanel leftPanel = new JPanel(null);
        leftPanel.setPreferredSize(new Dimension(500, 600));
        leftPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Login Portal");
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        title.setBounds(160, 30, 200, 40);
        title.setForeground(new Color(0, 102, 204));
        leftPanel.add(title);

        JLabel l1 = new JLabel("Username:");
        l1.setBounds(80, 100, 100, 25);
        l1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        leftPanel.add(l1);

        t1 = new JTextField();
        t1.setBounds(180, 100, 200, 30);
        leftPanel.add(t1);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(80, 160, 100, 25);
        l2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        leftPanel.add(l2);

        t2 = new JPasswordField();
        t2.setBounds(180, 160, 200, 30);
        leftPanel.add(t2);

        b1 = new JButton("Login");
        b1.setBounds(80, 230, 130, 40);
        b1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b1.setBackground(new Color(0, 123, 255));
        b1.setForeground(Color.WHITE);
        leftPanel.add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(250, 230, 130, 40);
        b2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b2.setBackground(new Color(220, 53, 69));
        b2.setForeground(Color.WHITE);
        leftPanel.add(b2);

        // Right Panel (Image)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(i2));

        add(leftPanel, BorderLayout.WEST);
        add(imageLabel, BorderLayout.CENTER);

        b1.addActionListener(this);
        b2.addActionListener(this);

        // Set up the Chain of Responsibility
        handlerChain = new UsernameExistsHandler();
        LoginHandler passwordHandler = new PasswordValidHandler();
        LoginHandler successHandler = new LoginSuccessHandler();

        handlerChain.setNext(passwordHandler);
        passwordHandler.setNext(successHandler);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            String username = t1.getText();
            String password = new String(t2.getPassword());
            LoginRequest request = new LoginRequest(username, password, this);
            try {
                handlerChain.handle(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == b2) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
