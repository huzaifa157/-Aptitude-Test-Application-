# Aptitude Test Application 🎓⏱️

![Java](https://img.shields.io/badge/Java-blue)
![Swing](https://img.shields.io/badge/Java%20Swing-GUI-orange)


A Java-based desktop application designed to conduct **university aptitude tests** for students. Developed as part of my second-semester project, this application features shuffled questions, timed tests, subject-wise navigation, and result tracking. 

---

## 📌 Table of Contents
- [Project Overview](#-project-overview)
- [Features](#-features)
- [Technologies Used](#-technologies-used)
- [Installation](#-installation)
- [Usage](#-usage)
---

## 🚀 Project Overview

This application simulates a university aptitude test environment with **four core subjects**:  
🔹 Computer Science  
🔹 Physics  
🔹 Mathematics  
🔹 English  

Students can take the test within a time limit, navigate between subjects, and view their results with a detailed performance breakdown. The project emphasizes **user experience**, **data integrity**, and **efficient problem-solving**.

---

## 🌟 Features

### 1. **Question Shuffling**  
   - Questions and answers are shuffled within each subject to ensure a unique test experience every time.  
   - Maintains alignment between questions and their correct answers for accurate scoring.

### 2. **Timer-Based Test**  
   - A 120-second timer ensures students complete the test within the allocated time.  
   - Auto-submits the test when time expires.

### 3. **Subject-Wise Navigation**  
   - Navigate seamlessly between subjects using the **"Next Subject"** button.  
   - Track progress with a clear subject label.

### 4. **Student-Friendly Interface**  
   - Clean UI with radio buttons for answer selection.  
   - Buttons like **Next**, **Back**, and **Help** enhance usability.  

### 5. **Result Calculation & Database**  
   - Scores are calculated based on correct answers and saved to a file-based database.  
   - Results include a **subject-wise breakdown** (e.g., 5/6 in Computer Science).

### 6. **Controlled Submission**  
   - Tests can only be submitted via the **"Submit"** button or timer expiration.  
   - Prevents accidental submissions.

---

## 💻 Technologies Used
- **Java Swing**: For building the graphical user interface (GUI).  
- **Java AWT**: For event handling and layout management.  
- **File Handling**: To save and retrieve student results.  
- **OOP Principles**: For modular, maintainable, and scalable code.  

---

## 📥 Installation

1. **Prerequisites**:  
   - Ensure Java JDK 17+ is installed.  
   - Clone the repository:  
     ```bash
     git clone https://github.com/your-username/university-aptitude-test.git
     ```

2. **Compile & Run**:  
   - Navigate to the project directory.  
   - Compile the Java files:  
     ```bash
     javac Test.java
     ```
   - Run the application:  
     ```bash
     java Test
     ```

---

## 🖥️ Usage

1. **Start the Test**:  
   - Enter your name and roll number (dummy values for demonstration).  
2. **Navigate the Test**:  
   - Use **Next** and **Back** to move between questions.  
   - Switch subjects with **Next Subject**.  
3. **Submit**:  
   - Click **Submit** to end the test manually or wait for the timer.  
4. **View Results**:  
   - Results are displayed with a subject-wise scorecard and saved to the database.

