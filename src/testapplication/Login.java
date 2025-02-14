package testapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    JTextField nameField, rollField;
    JButton startButton;

    Login() {
        setLayout(null);

        // Background color for left panel
//        getContentPane().setBackground(new Color(240, 240, 240));
//        getContentPane().setBackground(Color.WHITE);
        getContentPane().setBackground(new Color(230, 240, 255));
        setTitle("YOUR APTITUDE TEST");





        // Load and scale image
        ImageIcon front = new ImageIcon(getClass().getResource("/icons/ubit2.jpg"));
        Image scaling = front.getImage().getScaledInstance(550, 500, Image.SCALE_SMOOTH);
        ImageIcon frontpush = new ImageIcon(scaling);
        JLabel image = new JLabel(frontpush);
        image.setBounds(450, 0, 550, 500);
        add(image);

        // ** Title Heading **
        JLabel heading = new JLabel("University Entrance Test", SwingConstants.CENTER);
        heading.setFont(new Font("Serif", Font.BOLD, 28));
        heading.setForeground(new Color(22, 99, 54));
        heading.setBounds(50, 40, 350, 45);
        add(heading);

        // Name label and text field
        JLabel nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setBounds(80, 130, 200, 25);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(new Color(22, 99, 54));
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(80, 160, 270, 30);
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        add(nameField);

        // Roll Number label and text field
        JLabel rollLabel = new JLabel("Enter Your Roll No:");
        rollLabel.setBounds(80, 210, 200, 25);
        rollLabel.setFont(new Font("Arial", Font.BOLD, 16));
        rollLabel.setForeground(new Color(22, 99, 54));
        add(rollLabel);

        rollField = new JTextField();
        rollField.setBounds(80, 240, 270, 30);
        rollField.setFont(new Font("Arial", Font.PLAIN, 16));
        add(rollField);

        // Start Test Button
        startButton = new JButton("Start Test");
        startButton.setBounds(130, 300, 170, 40);
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(22, 99, 54));
        startButton.addActionListener(this);
        add(startButton);

        // Hover effect for button
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(18, 80, 44)); // Darker green
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(22, 99, 54)); // Original green
            }
        });

        // Set JFrame properties
        setSize(1000, 500);
        setLocation(200, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) {
                String name = nameField.getText().trim();
                String roll = rollField.getText().trim();

                if (name.isEmpty() || roll.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter both name and roll number");
                    return;
                }

                setVisible(false);
                new Rules(name, roll); // Pass roll number to Rules
            }
        }




    public static void main(String[] args) {
        new Login();
    }
}
