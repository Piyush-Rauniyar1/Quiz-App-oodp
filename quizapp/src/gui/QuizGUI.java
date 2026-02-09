package gui;

import model.PiyCompetitor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class QuizGUI extends JFrame {

    // Simple Data Class for Questions
    private class Question {
        String text;
        String[] options;
        int correctIndex;
        String difficulty;

        public Question(String text, String o1, String o2, String o3, String o4, int correct, String difficulty) {
            this.text = text;
            this.options = new String[]{o1, o2, o3, o4};
            this.correctIndex = correct;
            this.difficulty = difficulty;
        }
    }

    //  Instance Variables
    private PiyCompetitor competitor;
    private ArrayList<Question> quizQuestions;
    private int currentQuestionIndex = 0;
    private int[] scores = new int[5];
    private int[] userAnswers = new int[5];

    // UI Components
    private JLabel lblProgress, lblQuestion;
    private JToggleButton[] optionButtons = new JToggleButton[4]; // Array for A, B, C, D
    private ButtonGroup optionsGroup;

    public QuizGUI(PiyCompetitor competitor) {
        this.competitor = competitor;
        generateQuiz(competitor.getLevel());

        setTitle("Quiz - " + competitor.getName().getFirstName());
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Layout (Vertical Stack)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        setContentPane(mainPanel);

        // 1. Header (Progress)
        lblProgress = new JLabel("Question 1 / 5");
        lblProgress.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblProgress.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(lblProgress);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // 2. Question Text (Wrapped)
        lblQuestion = new JLabel("Question text goes here...");
        lblQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblQuestion.setFont(new Font("Arial", Font.PLAIN, 18));
        mainPanel.add(lblQuestion);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // 3. Option Buttons (Structure maintained!)
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // 4 rows, 10px gap
        optionsGroup = new ButtonGroup();

        char[] letters = {'A', 'B', 'C', 'D'};
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JToggleButton();
            optionButtons[i].setText(letters[i] + ". ");
            optionButtons[i].setBackground(Color.WHITE);
            optionButtons[i].setHorizontalAlignment(SwingConstants.LEFT); // Text on left
            optionButtons[i].setFocusPainted(false);

            optionsGroup.add(optionButtons[i]); // Group prevents multiple selections
            optionsPanel.add(optionButtons[i]);
        }

        // Limit height of options area
        optionsPanel.setMaximumSize(new Dimension(2000, 250));
        mainPanel.add(optionsPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // 4. Next Button
        JButton btnNext = new JButton("Next Question");
        btnNext.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNext.setBackground(new Color(59, 130, 246)); // Blue
        btnNext.setForeground(Color.black);
        btnNext.setPreferredSize(new Dimension(150, 40));

        btnNext.addActionListener(e -> handleNext());

        mainPanel.add(btnNext);

        loadQuestion();
        setVisible(true);
    }

    // --- Logic ---

    private void loadQuestion() {
        Question q = quizQuestions.get(currentQuestionIndex);

        lblProgress.setText("Question " + (currentQuestionIndex + 1) + " / 5");
        // HTML allows the text to wrap automatically
        lblQuestion.setText("<html><center>" + q.text + "</center></html>");

        for(int i=0; i<4; i++) {
            optionButtons[i].setText(((char)('A'+i)) + ". " + q.options[i]);
        }

        optionsGroup.clearSelection();
    }

    private void handleNext() {
        if (optionsGroup.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "Please select an answer!");
            return;
        }

        // 1. Save Answer
        Question q = quizQuestions.get(currentQuestionIndex);
        int selected = -1;
        for(int i=0; i<4; i++) {
            if(optionButtons[i].isSelected()) selected = i;
        }

        userAnswers[currentQuestionIndex] = selected;
        scores[currentQuestionIndex] = (selected == q.correctIndex) ? 5 : 0;

        // 2. Move to Next
        currentQuestionIndex++;
        if (currentQuestionIndex < quizQuestions.size()) {
            loadQuestion();
        } else {
            showResultScreen();
        }
    }

    private void showResultScreen() {
        competitor.setScores(scores);
        competitor.saveToDatabase();

        int correctCount = 0;
        for (int s : scores) if (s > 0) correctCount++;

        // --- Simple Result Dialog ---
        JDialog d = new JDialog(this, "Quiz Results", true);
        d.setSize(600, 500);
        d.setLayout(new BorderLayout());
        d.setLocationRelativeTo(this);

        // Header
        JPanel header = new JPanel(new GridLayout(2, 1));
        JLabel lblScore = new JLabel(correctCount + " / 5 Correct", SwingConstants.CENTER);
        lblScore.setFont(new Font("Arial", Font.BOLD, 30));
        lblScore.setForeground(new Color(59, 130, 246));

        JLabel lblMsg = new JLabel((correctCount >= 2 ? "Pass!" : "Fail!"), SwingConstants.CENTER);
        header.add(lblScore);
        header.add(lblMsg);
        d.add(header, BorderLayout.NORTH);

        // Table
        String[] columns = {"#", "Your Answer", "Correct Answer", "Result"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (int i = 0; i < 5; i++) {
            Question q = quizQuestions.get(i);
            String uAns = q.options[userAnswers[i]];
            String cAns = q.options[q.correctIndex];
            String res = (scores[i] > 0) ? "Correct" : "Wrong";
            model.addRow(new Object[]{ (i+1), uAns, cAns, res });
        }

        d.add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);

        // Close Button
        JButton btnClose = new JButton("Close Application");
        btnClose.addActionListener(e -> System.exit(0));
        d.add(btnClose, BorderLayout.SOUTH);

        d.setVisible(true);
    }

    private void generateQuiz(String level) {
        ArrayList<Question> allQuestions = new ArrayList<>();
        // Beginner
        allQuestions.add(new Question("Largest continent?", "Asia", "Africa", "Europe", "Australia", 0, "Beginner"));
        allQuestions.add(new Question("Capital of Nepal?", "Kathmandu", "Pokhara", "Biratnagar", "Lalitpur", 0, "Beginner"));
        allQuestions.add(new Question("Currency of Japan?", "Yen", "Won", "Dollar", "Yuan", 0, "Beginner"));
        allQuestions.add(new Question("Capital of China?", "Beijing", "Shanghai", "Wuhan", "Guangzhou", 0, "Beginner"));
        allQuestions.add(new Question("Primary color?", "Red", "Green", "Purple", "Orange", 0, "Beginner"));
        allQuestions.add(new Question("Sun rises in?", "East", "West", "North", "South", 0, "Beginner"));
        // Intermediate
        allQuestions.add(new Question("Old name of Myanmar?", "Burma", "Siam", "Ceylon", "Malaya", 0, "Intermediate"));
        allQuestions.add(new Question("Most populated in Asia?", "India", "China", "Indonesia", "Pakistan", 1, "Intermediate"));
        allQuestions.add(new Question("K-Pop country?", "South Korea", "Japan", "China", "Thailand", 0, "Intermediate"));
        allQuestions.add(new Question("Thailand currency?", "Baht", "Ringgit", "Rupee", "Yen", 0, "Intermediate"));
        allQuestions.add(new Question("Symbol for Gold?", "Au", "Ag", "Fe", "Pb", 0, "Intermediate"));
        allQuestions.add(new Question("Hardest substance?", "Diamond", "Gold", "Iron", "Platinum", 0, "Intermediate"));
        // Advanced
        allQuestions.add(new Question("Highest HDI in Asia?", "Singapore", "Japan", "South Korea", "China", 0, "Advanced"));
        allQuestions.add(new Question("Indian & Pacific Ocean?", "Malacca", "Hormuz", "Bosporus", "Bering", 0, "Advanced"));
        allQuestions.add(new Question("Largest hot desert Asia?", "Arabian", "Gobi", "Thar", "Karakum", 0, "Advanced"));
        allQuestions.add(new Question("Saltiest sea?", "Dead Sea", "Red Sea", "Black Sea", "Caspian", 0, "Advanced"));
        allQuestions.add(new Question("Java parent class?", "Object", "Class", "System", "String", 0, "Advanced"));
        allQuestions.add(new Question("Binary Search Time?", "O(log n)", "O(n)", "O(n^2)", "O(1)", 0, "Advanced"));

        ArrayList<Question> filtered = new ArrayList<>();
        for (Question q : allQuestions) {
            if (q.difficulty.equalsIgnoreCase(level)) filtered.add(q);
        }
        if (filtered.size() < 5) filtered = allQuestions;
        Collections.shuffle(filtered);

        quizQuestions = new ArrayList<>();
        for (int i = 0; i < 5; i++) quizQuestions.add(filtered.get(i));
    }
}