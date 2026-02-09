package gui;

import model.PiyCompetitor;
import javax.swing.*;
import java.awt.*;

public class PlayerDashboardGUI extends JFrame {

    public PlayerDashboardGUI(PiyCompetitor user) {
        // Window Setup
        setTitle("Dashboard");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout: GridBagLayout centers everything
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        // 1. Welcome Message
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Welcome, " + user.getName().getFirstName() + "!"), gbc);

        // 2. Subtitle
        gbc.gridy = 1;
        add(new JLabel("Ready to test your knowledge?"), gbc);

        // 3. Level Selection Label
        gbc.gridy = 2;
        gbc.insets = new Insets(30, 10, 5, 10); // Extra top space
        add(new JLabel("Select Difficulty Level:"), gbc);

        // 4. Level ComboBox
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 10, 10, 10); // Reset padding
        String[] levels = {"Beginner", "Intermediate", "Advanced"};
        JComboBox<String> cboLevel = new JComboBox<>(levels);
        cboLevel.setPreferredSize(new Dimension(200, 30));

        // Pre-select previous level if available
        if (user.getLevel() != null) {
            cboLevel.setSelectedItem(user.getLevel());
        }
        add(cboLevel, gbc);

        // 5. Start Button
        gbc.gridy = 4;
        gbc.insets = new Insets(20, 10, 10, 10);
        JButton btnStart = new JButton("Start Quiz");
        btnStart.setPreferredSize(new Dimension(150, 35));

        btnStart.addActionListener(e -> {
            String selectedLevel = (String) cboLevel.getSelectedItem();
            user.setLevel(selectedLevel);

            dispose(); // Close Dashboard
            new QuizGUI(user); // Launch Quiz
        });
        add(btnStart, gbc);

        // 6. Logout Button
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 10, 10, 10);
        JButton btnLogout = new JButton("Logout");
        btnLogout.setPreferredSize(new Dimension(100, 30));

        btnLogout.addActionListener(e -> {
            dispose();
            new LoginGUI();
        });
        add(btnLogout, gbc);

        setVisible(true);
    }
}