package gui;

import databaseconfig.DBConnection;
import model.CompetitorList;
import model.PiyCompetitor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class ManagerReportGUI extends JFrame {

    // Keep a reference to the list so we can search it easily
    private CompetitorList compList;

    public ManagerReportGUI() {
        setTitle("Manager Report");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize List
        compList = new CompetitorList();

        // --- 1. Top Panel (Stats & Top Performer) ---
        JPanel topPanel = new JPanel(new GridLayout(1, 2));

        // Left: Summary Stats
        JTextArea txtStats = new JTextArea();
        txtStats.setEditable(false);
        txtStats.setBorder(BorderFactory.createTitledBorder("Score Frequency"));
        topPanel.add(new JScrollPane(txtStats));

        // Right: Top Performer
        JPanel pnlWinner = new JPanel(new BorderLayout());
        pnlWinner.setBorder(BorderFactory.createTitledBorder("Top Performer"));

        JLabel lblWinner = new JLabel("Loading...", SwingConstants.CENTER);
        lblWinner.setFont(new Font("Arial", Font.BOLD, 14));
        pnlWinner.add(lblWinner, BorderLayout.CENTER);

        topPanel.add(pnlWinner);
        add(topPanel, BorderLayout.NORTH);

        // --- 2. Center Panel (The Table) ---
        String[] columns = {"ID", "Name", "Level", "Scores", "Overall Score"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- 3. Bottom Panel (Search & Navigation) ---
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // A. Search Section (The Missing Requirement!)
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Enter Competitor ID: "));
        JTextField txtSearchId = new JTextField(10);
        searchPanel.add(txtSearchId);
        JButton btnSearch = new JButton("View Short Details");
        searchPanel.add(btnSearch);

        // B. Navigation Section
        JPanel navPanel = new JPanel();
        JButton btnClose = new JButton("Back to Main Menu");
        navPanel.add(btnClose);

        bottomPanel.add(searchPanel, BorderLayout.NORTH);
        bottomPanel.add(navPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- 4. Event Listeners ---

        // Search Button Logic
        btnSearch.addActionListener(e -> {
            String idStr = txtSearchId.getText().trim();
            if (idStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an ID.");
                return;
            }

            try {
                int searchId = Integer.parseInt(idStr);
                boolean found = false;

                // Search inside the list we loaded
                for (PiyCompetitor c : compList.getAllCompetitors()) {
                    if (c.getCompetitorId() == searchId) {
                        // FOUND! Show the specific "Short Details" format
                        JOptionPane.showMessageDialog(this, c.getShortDetails(), "Short Details", JOptionPane.INFORMATION_MESSAGE);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(this, "Competitor with ID " + searchId + " not found.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID must be a number.");
            }
        });

        // Close Button Logic
        btnClose.addActionListener(e -> {
            dispose();
            try {
                Class.forName("Main").getDeclaredConstructor().newInstance();
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        // --- 5. Load Data ---
        loadData(model, txtStats, lblWinner);

        setVisible(true);
    }

    private void loadData(DefaultTableModel model, JTextArea txtStats, JLabel lblWinner) {
        // Use your existing CompetitorList class to fetch data
        // This ensures the "compList" variable is populated for the Search button to use
        compList.loadFromDatabase();

        if (compList.getAllCompetitors().isEmpty()) {
            lblWinner.setText("No data found.");
            return;
        }

        // 1. Populate Table
        for (PiyCompetitor c : compList.getAllCompetitors()) {
            Vector<Object> row = new Vector<>();
            row.add(c.getCompetitorId());
            row.add(c.getName().getFullName());
            row.add(c.getLevel());
            row.add(java.util.Arrays.toString(c.getScores()));
            row.add(String.format("%.1f", c.getOverallScore()));
            model.addRow(row);
        }

        // 2. Set Top Performer
        PiyCompetitor top = compList.getTopPerformer();
        if (top != null) {
            lblWinner.setText("<html><center>" + top.getName().getFullName() + "<br>Score: " + String.format("%.1f", top.getOverallScore()) + "</center></html>");
        }

        // 3. Set Stats
        txtStats.setText(compList.getScoreFrequencyReport());
    }
}