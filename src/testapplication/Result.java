package testapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Result extends JFrame implements ActionListener {

    JLabel heading, scoreLabel, subHeading, imageLabel;
    JButton exit;
    String name;
    int finalScore;
    int[] subjectScores;


    Result(String name, int obtainedMarks, int[] subjectScores) {
        this.name = name;
        this.finalScore = obtainedMarks;
        this.subjectScores = subjectScores;

        setLayout(null);
        getContentPane().setBackground(new Color(240, 245, 250));
        setTitle("Exam Results - Karachi University");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1000, 120);
        headerPanel.setBackground(new Color(22, 99, 54));
        headerPanel.setLayout(null);
        add(headerPanel);

        // University Logo
        try {
            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/icons/ubit.jpg"));
            Image logoImage = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
            logoLabel.setBounds(30, 20, 80, 80);
            headerPanel.add(logoLabel);
        } catch (Exception e) {
            System.err.println("Logo not found!");
        }

        // Result Content
        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(50, 150, 900, 450);
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(null);
        add(contentPanel);

        // Main Heading
        heading = new JLabel("Examination Results");
        heading.setBounds(50, 30, 800, 40);
        heading.setFont(new Font("Arial", Font.BOLD, 32));
        heading.setForeground(new Color(22, 99, 54));
        contentPanel.add(heading);

        // Candidate Info
        JLabel nameLabel = new JLabel("Candidate: " + name);
        nameLabel.setBounds(50, 80, 800, 30);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        contentPanel.add(nameLabel);

        // Score Display
        JPanel scorePanel = new JPanel();
        scorePanel.setBounds(50, 130, 800, 100);
        scorePanel.setBackground(new Color(240, 245, 250));
        scorePanel.setLayout(new GridLayout(1, 2));

        JLabel scoreText = new JLabel("Final Score:", SwingConstants.CENTER);
        scoreText.setFont(new Font("Arial", Font.BOLD, 24));

        scoreLabel = new JLabel(finalScore + "/24", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 36));
        scoreLabel.setForeground(new Color(255, 153, 0));

        scorePanel.add(scoreText);
        scorePanel.add(scoreLabel);
        contentPanel.add(scorePanel);

        // Subject-wise Performance
        JPanel subjectPanel = new JPanel();
        subjectPanel.setBounds(50, 250, 800, 150);
        subjectPanel.setBackground(Color.WHITE);
        subjectPanel.setLayout(new GridLayout(2, 2, 20, 20));

        String[] subjects = {"Computer Science", "Physics", "Maths", "English"};
        for (int i = 0; i < subjects.length; i++) {
            JPanel subPanel = new JPanel();
            subPanel.setBackground(new Color(240, 245, 250));
            subPanel.setLayout(new BorderLayout());

            JLabel subjectLabel = new JLabel(subjects[i], SwingConstants.CENTER);
            subjectLabel.setFont(new Font("Arial", Font.BOLD, 18));
            subjectLabel.setForeground(new Color(22, 99, 54));

            JLabel score = new JLabel(subjectScores[i] + "/6", SwingConstants.CENTER);
            score.setFont(new Font("Arial", Font.BOLD, 24));
            score.setForeground(new Color(255, 153, 0));

            subPanel.add(subjectLabel, BorderLayout.NORTH);
            subPanel.add(score, BorderLayout.CENTER);
            subjectPanel.add(subPanel);
        }
        contentPanel.add(subjectPanel);

        // Exit Button
        exit = new JButton("Exit System");
        exit.setBounds(375, 410, 150, 40);
        exit.setFont(new Font("Arial", Font.BOLD, 16));
        exit.setForeground(Color.WHITE);
        exit.setBackground(new Color(22, 99, 54));
        exit.addActionListener(this);
        styleButton(exit);
        contentPanel.add(exit);

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(18, 80, 44));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(22, 99, 54));
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            System.exit(0);
        }
    }
}