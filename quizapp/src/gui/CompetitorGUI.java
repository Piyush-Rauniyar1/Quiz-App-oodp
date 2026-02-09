package gui;

import model.Name;
import model.PiyCompetitor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI for Competitor Registration.
 * Allows users to enter their personal details and select difficulty level.
 * @author Piyush
 * @version 2.0
 */
public class CompetitorGUI extends JFrame {

    // Input fields
    private JTextField txtId, txtFirstName, txtMiddleName, txtLastName, txtCountry;

    // CHANGED: Use JComboBox for the drop-down menu instead of JTextField
    private JComboBox<String> cboLevel;

    private JButton btnPlay;

    public CompetitorGUI() {
        // Window Setup
        setTitle("Quiz Competitor Registration");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10)); // 7 rows to fit all fields

        // 1. Add Components
        add(new JLabel("  Competitor ID:"));
        txtId = new JTextField();
        add(txtId);

        add(new JLabel("  First Name:"));
        txtFirstName = new JTextField();
        add(txtFirstName);

        add(new JLabel("  Middle Name (Optional):"));
        txtMiddleName = new JTextField();
        add(txtMiddleName);

        add(new JLabel("  Last Name:"));
        txtLastName = new JTextField();
        add(txtLastName);

        // --- UPDATED LEVEL SECTION ---
        add(new JLabel("  Level:"));

        // Define the specific options that match your QuizGUI logic
        String[] levels = {"Beginner", "Intermediate", "Advanced"};
        cboLevel = new JComboBox<>(levels);
        add(cboLevel);
        // -----------------------------

        add(new JLabel("  Country:"));
        txtCountry = new JTextField();
        add(txtCountry);

        // 2. Start Button
        btnPlay = new JButton("Start Quiz");
        add(new JLabel("")); // Empty placeholder for layout
        add(btnPlay);

        // 3. Button Action
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startQuiz();
            }
        });

        setLocationRelativeTo(null); // Center window on screen
        setVisible(true);
    }

    /**
     * Reads input, creates the Competitor object, and launches the Quiz GUI.
     */
    private void startQuiz() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String fName = txtFirstName.getText();
            String mName = txtMiddleName.getText();
            String lName = txtLastName.getText();

            // GET LEVEL FROM DROP DOWN
            String level = (String) cboLevel.getSelectedItem();

            String country = txtCountry.getText();

            // Handle optional middle name
            Name nameObj;
            if (mName.trim().isEmpty()) {
                nameObj = new Name(fName, lName);
            } else {
                nameObj = new Name(fName, mName, lName);
            }

            // Create object and open Quiz Window
            PiyCompetitor player = new PiyCompetitor(id, nameObj, level, country);

            this.dispose(); // Close this form
            new QuizGUI(player); // Open Quiz

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Competitor ID must be a number!");
        }
    }
}