package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaymentGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentGUI().createGUI());
    }

    public void createGUI() {
        JFrame frame = new JFrame("Hotel Payment System");
        frame.setSize(650, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(240, 248, 255));

        // Add image to left side
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/payment.jpg"));
        Image scaled = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaled));
        imageLabel.setBounds(20, 50, 150, 150);
        frame.add(imageLabel);

        JLabel title = new JLabel("Hotel Payment Processing", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(200, 20, 400, 30);
        frame.add(title);

        JLabel lbl = new JLabel("Select Payment Method:");
        lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl.setBounds(220, 80, 200, 25);
        frame.add(lbl);

        String[] options = {"Cash", "Credit Card", "Online"};
        JComboBox<String> paymentBox = new JComboBox<>(options);
        paymentBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        paymentBox.setBounds(420, 80, 180, 25);
        frame.add(paymentBox);

        JLabel numberLabel = new JLabel("Amount");
        numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        numberLabel.setBounds(220, 120, 200, 25);
        frame.add(numberLabel);

        JTextField numberField = new JTextField();
        numberField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        numberField.setBounds(420, 120, 180, 25);
        frame.add(numberField);

        JButton btn = new JButton("Confirm Payment");
        btn.setBounds(300, 170, 180, 35);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(new Color(34, 139, 34));
        btn.setForeground(Color.WHITE);
        frame.add(btn);

        JTextArea result = new JTextArea();
        result.setBounds(200, 230, 400, 200);
        result.setFont(new Font("Monospaced", Font.PLAIN, 14));
        result.setEditable(false);
        result.setBorder(BorderFactory.createTitledBorder("Payment Summary"));
        frame.add(result);

        btn.addActionListener(e -> {
            String selected = paymentBox.getSelectedItem().toString();
            String paymentNumber = numberField.getText().trim();

            if (paymentNumber.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a payment number.");
                return;
            }

            ReceiptBuilder builder = new ReceiptBuilder();
            Receipt receipt;

            switch (selected) {
                case "Cash":
                    receipt = builder.buildCashReceipt();
                    break;
                case "Credit Card":
                    receipt = builder.buildCardReceipt();
                    break;
                default:
                    receipt = builder.buildOnlineReceipt();
                    break;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Payment No: ").append(paymentNumber).append("\n\n");
            for (PaymentMethod m : receipt.methods) {
                sb.append("Payment: ").append(m.name()).append("\n");
                sb.append("Type: ").append(m.type().type()).append("\n");
                sb.append("Amount: Rs.").append(m.amount()).append("\n\n");
            }
            sb.append("Total Paid: Rs.").append(receipt.getTotalAmount());

            result.setText(sb.toString());
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}