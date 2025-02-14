package testapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame implements ActionListener {

    String[][] questions = new String[24][5];
    String[][] answers = new String[24][2];
    String[][] userans = new String[24][1];

    String[] subjects = {"Computer Science", "Physics", "Maths", "English"};

    JButton next, submit, help, nextSubject, back;
    JLabel qno, q, timerLabel, subjectLabel;
    JRadioButton rb1, rb2, rb3, rb4;
    ButtonGroup group;

    String name; String rollNo;

    public static int timer = 120;
    public static int ans_given = 0;
    public static int count = 0;
    javax.swing.Timer overallTimer;

    Test(String name, String rollNo) {

        this.name = name;
        this.rollNo = rollNo;

        setSize(1440, 850);
        setLocation(50, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        ImageIcon front = new ImageIcon(getClass().getResource("/icons/ubit.jpg"));
        Image scaling = front.getImage().getScaledInstance(1440, 850, Image.SCALE_SMOOTH);
        ImageIcon scaledImage = new ImageIcon(scaling);
        JLabel image = new JLabel(scaledImage);
        image.setBounds(0, 0, 1440, 392);
        add(image);

        qno = new JLabel("Question 1");
        qno.setBounds(100, 450, 200, 30);
        qno.setBackground(Color.WHITE);
        qno.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(qno);

        q = new JLabel();
        q.setBounds(100, 500, 1200, 50);
        q.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(q);

        rb1 = new JRadioButton();
        rb1.setBounds(100, 580, 400, 30);
        rb1.setBackground(Color.WHITE);
        rb1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(rb1);


        rb2 = new JRadioButton();
        rb2.setBounds(100, 620, 400, 30);
        rb2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rb2.setBackground(Color.WHITE);
        add(rb2);


        rb3 = new JRadioButton();
        rb3.setBounds(100, 660, 400, 30);
        rb3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rb3.setBackground(Color.WHITE);
        add(rb3);


        rb4 = new JRadioButton();
        rb4.setBounds(100, 700, 400, 30);
        rb4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rb4.setBackground(Color.WHITE);
        add(rb4);

        group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);
        group.add(rb3);
        group.add(rb4);


        initQuestions();
        initAnswers();
        shuffleQuestions();

        next = new JButton("Next");
        next.setBounds(450, 750, 100, 30);
        next.setForeground(Color.WHITE);
        next.setBackground(new Color(22, 99, 54));
        next.setFont(new Font("Tahoma", Font.BOLD, 14));
        next.addActionListener(this);
        add(next);

        submit = new JButton("Submit");
        submit.setBounds(1200, 750, 200, 30);
        submit.setForeground(Color.BLACK);
        submit.setBackground(new Color(255, 215, 0));
        submit.setFont(new Font("Tahoma", Font.BOLD, 14));
        submit.addActionListener(this);
        add(submit);

        help = new JButton("Help");
        help.setBounds(600, 750, 100, 30);
        help.setForeground(Color.WHITE);
        help.setBackground(new Color(0, 102, 204));
        help.setFont(new Font("Tahoma", Font.BOLD, 14));
        help.addActionListener(this);
        add(help);

        back = new JButton("Back");
        back.setBounds(90, 750, 100, 30);
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(204, 0, 0));
        back.setFont(new Font("Tahoma", Font.BOLD, 14));
        back.addActionListener(this);
        add(back);

        timerLabel = new JLabel("Time Left: " + timer);
        timerLabel.setBounds(1200, 50, 200, 30);
        timerLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        timerLabel.setForeground(Color.RED);
        timerLabel.setOpaque(true);
        timerLabel.setBackground(Color.WHITE);
        add(timerLabel);

        subjectLabel = new JLabel("Subject: Computer Science");
        subjectLabel.setBounds(1000, 450, 300, 30);
        subjectLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        subjectLabel.setForeground(Color.BLACK);
        add(subjectLabel);

        nextSubject = new JButton("Next Subject");
        nextSubject.setBounds(800, 750, 200, 30);
        nextSubject.setForeground(Color.WHITE);
        nextSubject.setBackground(new Color(128, 0, 128));
        nextSubject.setFont(new Font("Tahoma", Font.BOLD, 14));
        nextSubject.addActionListener(this);
        add(nextSubject);

        overallTimer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer--;
                timerLabel.setText("Time Left: " + timer);
                if (timer <= 0) {
                    overallTimer.stop();
                    checkAnswer();
                    showFinalScore();
                }
            }
        });

        overallTimer.start();
        showQuestion(count);
        setVisible(true);
    }


    private void showFinalScore() {
        overallTimer.stop();
        int totalMarks = 24; // Total marks for the exam
        int obtainedMarks = 0;
        int[] subjectScores = new int[4]; // Computer Science, Physics, Maths, English

        for (int i = 0; i < 24; i++) {
            if (userans[i][0] != null && userans[i][0].equals(answers[i][1])) {
                obtainedMarks++;
                subjectScores[i / 6]++; // Increment subject score
            }
        }

        // Save to file
        new FileManager().saveResult(name, rollNo, totalMarks, obtainedMarks, subjectScores);

        // Show results
        new Result(name, obtainedMarks, subjectScores);
        this.dispose();
    }


    private void showQuestion(int count) {
        qno.setText("Question " + (count + 1));
        q.setText(questions[count][0]);
        rb1.setText(questions[count][1]);
        rb2.setText(questions[count][2]);
        rb3.setText(questions[count][3]);
        rb4.setText(questions[count][4]);
        group.clearSelection();


        String currentSubject = getCurrentSubject(count);
        subjectLabel.setText("Subject: " + currentSubject);


        String savedAnswer = userans[count][0];
        if (savedAnswer != null) {
            if (savedAnswer.equals(rb1.getText())) rb1.setSelected(true);
            else if (savedAnswer.equals(rb2.getText())) rb2.setSelected(true);
            else if (savedAnswer.equals(rb3.getText())) rb3.setSelected(true);
            else if (savedAnswer.equals(rb4.getText())) rb4.setSelected(true);
        }
    }

    private String getCurrentSubject(int count) {
        int subjectIndex = count / 6; // 6 question
        return subjects[subjectIndex];
    }

    private void initQuestions() {
        // Computer Science (0-5)
        questions[0][0] = "Number of primitive data types in Java are?";
        questions[0][1] = "6";
        questions[0][2] = "7";
        questions[0][3] = "8";
        questions[0][4] = "9";

        questions[1][0] = "What is the size of an int in Java?";
        questions[1][1] = "16";
        questions[1][2] = "32";
        questions[1][3] = "64";
        questions[1][4] = "128";

        questions[2][0] = "Which data type is used to represent floating-point numbers in Java?";
        questions[2][1] = "float"; questions[2][2] = "int"; questions[2][3] = "long"; questions[2][4] = "boolean";

        questions[3][0] = "What is the default value of a boolean in Java?";
        questions[3][1] = "true"; questions[3][2] = "false"; questions[3][3] = "null"; questions[3][4] = "0";

        questions[4][0] = "What is the keyword used for creating objects in Java?";
        questions[4][1] = "new"; questions[4][2] = "this"; questions[4][3] = "class"; questions[4][4] = "object";

        questions[5][0] = "Which of the following is a feature of Java?";
        questions[5][1] = "Platform Independent"; questions[5][2] = "Platform Dependent";
        questions[5][3] = "Non-Object Oriented"; questions[5][4] = "None of the above";

        // Physics (6-11)
        questions[6][0] = "What is the SI unit of force?";
        questions[6][1] = "Newton"; questions[6][2] = "Joule"; questions[6][3] = "Pascal"; questions[6][4] = "Watt";

        questions[7][0] = "Which of the following is a vector quantity?";
        questions[7][1] = "Speed"; questions[7][2] = "Velocity"; questions[7][3] = "Time"; questions[7][4] = "Mass";

        questions[8][0] = "Which of the following has the highest energy?";
        questions[8][1] = "Infrared"; questions[8][2] = "X-rays";
        questions[8][3] = "Ultraviolet"; questions[8][4] = "Microwaves";

        questions[9][0] = "The force required to move a body is directly proportional to its...";
        questions[9][1] = "Mass"; questions[9][2] = "Velocity";
        questions[9][3] = "Acceleration"; questions[9][4] = "Density";

        questions[10][0] = "What is the unit of electric current?";
        questions[10][1] = "Ampere"; questions[10][2] = "Volt";
        questions[10][3] = "Ohm"; questions[10][4] = "Coulomb";

        questions[11][0] = "What type of energy is stored in a battery?";
        questions[11][1] = "Kinetic Energy"; questions[11][2] = "Potential Energy";
        questions[11][3] = "Chemical Energy"; questions[11][4] = "Thermal Energy";

        // Maths (12-17)
        questions[12][0] = "What is the value of Pi?";
        questions[12][1] = "3.14"; questions[12][2] = "3.15";
        questions[12][3] = "3.16"; questions[12][4] = "3.13";

        questions[13][0] = "What is 5 + 3 * 2?";
        questions[13][1] = "11"; questions[13][2] = "16";
        questions[13][3] = "10"; questions[13][4] = "13";

        questions[14][0] = "Which of the following is an irrational number?";
        questions[14][1] = "√2"; questions[14][2] = "0.5";
        questions[14][3] = "1/3"; questions[14][4] = "4";

        questions[15][0] = "What is the sum of interior angles of a triangle?";
        questions[15][1] = "180°"; questions[15][2] = "90°";
        questions[15][3] = "360°"; questions[15][4] = "270°";

        questions[16][0] = "What is the derivative of sin(x)?";
        questions[16][1] = "cos(x)"; questions[16][2] = "sin(x)";
        questions[16][3] = "-cos(x)"; questions[16][4] = "-sin(x)";

        questions[17][0] = "What is the integral of x dx?";
        questions[17][1] = "x^2/2"; questions[17][2] = "x^2";
        questions[17][3] = "2x"; questions[17][4] = "x^3/3";

        // English (18-23)
        questions[18][0] = "Which word is a synonym for 'happy'?";
        questions[18][1] = "Joyful"; questions[18][2] = "Sad";
        questions[18][3] = "Angry"; questions[18][4] = "Bored";

        questions[19][0] = "What is the antonym of 'start'?";
        questions[19][1] = "Stop"; questions[19][2] = "Begin";
        questions[19][3] = "Continue"; questions[19][4] = "Start";

        questions[20][0] = "Which of the following is a preposition?";
        questions[20][1] = "In"; questions[20][2] = "Run";
        questions[20][3] = "Quickly"; questions[20][4] = "Sing";

        questions[21][0] = "Choose the correct form: She ____ to the store.";
        questions[21][1] = "went"; questions[21][2] = "go";
        questions[21][3] = "going"; questions[21][4] = "gone";

        questions[22][0] = "Which of the following is a verb?";
        questions[22][1] = "Jump"; questions[22][2] = "Beautiful";
        questions[22][3] = "Quickly"; questions[22][4] = "Strong";

        questions[23][0] = "Which sentence is in the passive voice?";
        questions[23][1] = "The book was read by the teacher."; questions[23][2] = "The teacher read the book.";
        questions[23][3] = "The teacher is reading the book."; questions[23][4] = "The book reads by the teacher.";
    }

    private void initAnswers() {
        // Computer Science Answers
        answers[0][0] = "3"; answers[0][1] = "8";
        answers[1][0] = "2"; answers[1][1] = "32";
        answers[2][0] = "1"; answers[2][1] = "float";
        answers[3][0] = "2"; answers[3][1] = "false";
        answers[4][0] = "1"; answers[4][1] = "new";
        answers[5][0] = "1"; answers[5][1] = "Platform Independent";

        // Physics Answers
        answers[6][0] = "1"; answers[6][1] = "Newton";
        answers[7][0] = "2"; answers[7][1] = "Velocity";
        answers[8][0] = "2"; answers[8][1] = "X-rays";
        answers[9][0] = "1"; answers[9][1] = "Mass";
        answers[10][0] = "1"; answers[10][1] = "Ampere";
        answers[11][0] = "3"; answers[11][1] = "Chemical Energy";

        // Maths Answers
        answers[12][0] = "1"; answers[12][1] = "3.14";
        answers[13][0] = "1"; answers[13][1] = "11";
        answers[14][0] = "1"; answers[14][1] = "√2";
        answers[15][0] = "1"; answers[15][1] = "180°";
        answers[16][0] = "1"; answers[16][1] = "cos(x)";
        answers[17][0] = "1"; answers[17][1] = "x^2/2";

        // English Answers
        answers[18][0] = "1"; answers[18][1] = "Joyful";
        answers[19][0] = "1"; answers[19][1] = "Stop";
        answers[20][0] = "1"; answers[20][1] = "In";
        answers[21][0] = "1"; answers[21][1] = "went";
        answers[22][0] = "1"; answers[22][1] = "Jump";
        answers[23][0] = "1"; answers[23][1] = "The book was read by the teacher.";
    }

    private void checkAnswer() {
        String selectedAnswer = "";
        if (rb1.isSelected()) selectedAnswer = rb1.getText();
        else if (rb2.isSelected()) selectedAnswer = rb2.getText();
        else if (rb3.isSelected()) selectedAnswer = rb3.getText();
        else if (rb4.isSelected()) selectedAnswer = rb4.getText();
        userans[count][0] = selectedAnswer;
    }
    private void nextQuestion() {
        if (count < 23) {
            count++;
            showQuestion(count);
        }
    }

    private void shuffleQuestions() {
        // Shuffle questions and answers within each subject
        for (int i = 0; i < 24; i += 6) {
            shuffleSubject(i, i + 6);
        }
    }

    private void shuffleSubject(int start, int end) {
        java.util.Random random = new java.util.Random();
        for (int currentIndex = start; currentIndex < end - 1; currentIndex++) {
            int swapIndex = random.nextInt(end - currentIndex) + currentIndex;
            swapQuestionsAndAnswers(currentIndex, swapIndex);
        }
    }

    private void swapQuestionsAndAnswers(int i, int j) {
        // Swap questions
        String[] tempQuestion = questions[i];
        questions[i] = questions[j];
        questions[j] = tempQuestion;

        // Swap answers
        String[] tempAnswer = answers[i];
        answers[i] = answers[j];
        answers[j] = tempAnswer;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            checkAnswer();
            nextQuestion();
        } else if (e.getSource() == submit) {

            // Show a warning dialog before submitting
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to submit the quiz?",
                    "Submit Quiz",
                    JOptionPane.YES_NO_OPTION
            );

            // If the user confirms, submit the quiz
            if (choice == JOptionPane.YES_OPTION) {
                checkAnswer();
                showFinalScore();
            }
        } else if (e.getSource() == help) {
            JOptionPane.showMessageDialog(this, "Select the correct answer and press Next.");

        } else if (e.getSource() == back) {
            if (count > 0) {
                checkAnswer();
                count--;
                showQuestion(count);
            } else {
                JOptionPane.showMessageDialog(this, "This is the first question.");
            }
        } else if (e.getSource() == nextSubject) {
            int currentSubject = count / 6;
            if (currentSubject < 3) {
                int nextSubjectStart = (currentSubject + 1) * 6;
                count = nextSubjectStart - 1;
                nextQuestion();
            } else {
                count = -1; // Restart from the first subject
                nextQuestion();
            }
        }
    }

    public static void main(String[] args) {
        new Test("user", "rollno");
    }
}