package testapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules extends JFrame implements ActionListener {

    JButton start, back;
    String name;
    String rollNo;

    Rules(String name , String rollNo) {

        this.rollNo = rollNo;
        this.name = name;

        setLayout(null);
        getContentPane().setBackground(new Color(240, 245, 250)); // Light blue background
        setTitle("Karachi University - Aptitude Test Protocol");

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 800, 120); // Increased height for better spacing
        headerPanel.setBackground(new Color(22, 99, 54)); // University theme color
        headerPanel.setLayout(null);
        add(headerPanel);

        // University Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/icons/ubit.jpg"));
        Image logoImage = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(30, 20, 80, 80); // Adjusted position
        headerPanel.add(logoLabel);

        // Header Text
        JLabel headerText = new JLabel("KARACHI UNIVERSITY APTITUDE EXAMINATION");
        headerText.setBounds(150, 30, 600, 40);
        headerText.setForeground(Color.WHITE);
        headerText.setFont(new Font("Arial", Font.BOLD, 22)); // Increased font size
        headerPanel.add(headerText);

        // Rules Container
        JPanel rulesPanel = new JPanel();
        rulesPanel.setBounds(10, 140, 765, 450); // Adjusted position
        rulesPanel.setBackground(Color.WHITE);
        rulesPanel.setBorder(BorderFactory.createLineBorder(new Color(22, 99, 54), 2));
        rulesPanel.setLayout(null);
        add(rulesPanel);

        // Rules Content
        JTextPane rulesContent = new JTextPane();
        rulesContent.setContentType("text/html");
        rulesContent.setEditable(false);
        rulesContent.setOpaque(false);
        rulesContent.setBounds(20, 20, 660, 430);
        rulesContent.setFont(new Font("Arial", Font.PLAIN, 16)); // Increased font size
        rulesContent.setText(
                "<html><div style='line-height:1.6;'>" +
                        "<h3 style='color:#166336; margin-bottom:20px; font-size:18px;'>Examination Protocol</h3>" +
                        "<ol style='margin-left:20px; padding-left:10px;'>" +
                        "<li style='margin-bottom:15px;'><b style='font-size:16px;'>Time Allocation:</b><br>" +
                        " • 120 seconds total duration<br>" +
                        " • 15 seconds per question</li>" +
                        "<li style='margin-bottom:15px;'><b style='font-size:16px;'>Electronic Devices Policy:</b><br>" +
                        " • All devices must be powered off<br>" +
                        " • No secondary devices in examination area</li>" +
                        "<li style='margin-bottom:15px;'><b style='font-size:16px;'>Examination Environment:</b><br>" +
                        " • Maintain absolute silence<br>" +
                        " • Private, distraction-free space<br>" +
                        " • No third-party assistance</li>" +
                        "<li style='margin-bottom:15px;'><b style='font-size:16px;'>Question Navigation:</b><br>" +
                        " • Use 'Next' to progress<br>" +
                        " • Use 'Back' to review</li>" +
                        "<li style='margin-bottom:15px;'><b style='font-size:16px;'>Submission Policy:</b><br>" +
                        " • Final answers cannot be revised<br>" +
                        " • Automatic submission at timeout</li>" +
                        "<li style='margin-bottom:15px;'><b style='font-size:16px;'>Technical Requirements:</b><br>" +
                        " • Stable internet connection required<br>" +
                        " • Supported browsers: Chrome/Edge</li>" +
                        "<li style='margin-bottom:15px;'><b style='font-size:16px;'>Academic Integrity:</b><br>" +
                        " • Zero tolerance for cheating<br>" +
                        " • Automated plagiarism detection</li>" +
                        "<li style='margin-bottom:15px;'><b style='font-size:16px;'>Accessibility:</b><br>" +
                        " • Request accommodations in advance<br>" +
                        " • Documented requirements only</li>" +
                        "<li style='margin-bottom:15px;'><b style='font-size:16px;'>Results Protocol:</b><br>" +
                        " • Scores final and binding<br>" +
                        " • Review requests within 72 hours</li>" +
                        "</ol>" +
                        "<div style='margin-top:20px; padding:15px; background:#fff3cd; border-radius:5px;'>" +
                        "<p style='color:#856404; margin:0; font-size:14px;'>" +
                        "⚠ By proceeding, you agree to uphold academic integrity and accept all examination regulations." +
                        "</p></div>" +
                        "</div></html>"
        );

        // Add scroll functionality
        JScrollPane scrollPane = new JScrollPane(rulesContent);
        scrollPane.setBounds(10, 10, 760, 420);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling
        rulesPanel.add(scrollPane);

        // Control Buttons
        back = new JButton("Back");
        back.setBounds(200, 600, 150, 40); // Adjusted position and size
        styleButton(back, new Color(22, 99, 54));

        start = new JButton("Start");
        start.setBounds(450, 600, 150, 40); // Adjusted position and size
        styleButton(start, new Color(22, 99, 54));

        add(back);
        add(start);

        setSize(800, 700); // Adjusted window size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.addActionListener(this);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
                button.setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            setVisible(false);
            new Test(name, rollNo); // Pass both name and roll to Test

        } else {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Rules("Candidate", "rollno");
    }
}