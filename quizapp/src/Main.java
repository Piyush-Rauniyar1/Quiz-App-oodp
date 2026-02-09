import gui.LoginGUI;
import gui.ManagerReportGUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Main Class for Console Reporting.
 * Generates the "Example Report" exactly as shown in the assignment brief.
 */
//public class Main {
//
//    public static void main(String[] args) {
//        // 1. Setup Data (Matches the table in your image)
//        ArrayList<PiyCompetitor> competitors = new ArrayList<>();
//
//        // Competitor 200: Alice Green (Beginner) - Scores: 4, 3, 5, 2, 4
//        PiyCompetitor c1 = new PiyCompetitor(200, new Name("Alice", "Green"), "Beginner", "UK");
//        c1.setScores(new int[]{4, 3, 5, 2, 4});
//        competitors.add(c1);
//
//        // Competitor 201: Bob Brown (Intermediate) - Scores: 3, 4, 4, 5, 4
//        PiyCompetitor c2 = new PiyCompetitor(201, new Name("Bob", "Brown"), "Intermediate", "USA");
//        c2.setScores(new int[]{3, 4, 4, 5, 4});
//        competitors.add(c2);
//
//        // Competitor 202: Carol White (Advanced) - Scores: 5, 5, 4, 4, 5
//        PiyCompetitor c3 = new PiyCompetitor(202, new Name("Carol", "White"), "Advanced", "Canada");
//        c3.setScores(new int[]{5, 5, 4, 4, 5});
//        competitors.add(c3);
//
//        // Competitor 203: David Black (Advanced) - Scores: 4, 4, 5, 5, 4
//        PiyCompetitor c4 = new PiyCompetitor(203, new Name("David", "Black"), "Advanced", "Australia");
//        c4.setScores(new int[]{4, 4, 5, 5, 4});
//        competitors.add(c4);
//
//
//
//        for (PiyCompetitor c : competitors) {
//            String scoreStr = "";
//            for (int s : c.getScoreArray()) scoreStr += s + " ";
//
//            System.out.printf("%-15d %-15s %-15s %-15s %-10.1f%n",
//                    c.getCompetitorId(),
//                    c.getName().getFullName(),
//                    c.getLevel(),
//                    scoreStr.trim(),
//                    c.getOverallScore());
//        }
//        System.out.println();
//
//

//        System.out.println("Full Details for CompetitorID 200:");
//        if (competitors.size() > 0) {
//            System.out.println(competitors.get(0).getFullDetails());
//        }
//        System.out.println();
//
//        System.out.println("Short Details for CompetitorID 202:");
//        if (competitors.size() > 2) {
//            System.out.println(competitors.get(2).getShortDetails());
//        }
//        System.out.println();
//
//
//
//        System.out.println("Statistical Summary:");
//
//
//        // 1. Total Competitors
//        System.out.println("• Total number of competitors: " + competitors.size());
//
//        // 2. Top Performer
//        PiyCompetitor winner = competitors.get(0);
//        for (PiyCompetitor c : competitors) {
//            if (c.getOverallScore() > winner.getOverallScore()) {
//                winner = c;
//            }
//        }
//        System.out.printf("• Competitor with the highest score: %s with an overall score of %.1f%n",
//                winner.getName().getFullName(), winner.getOverallScore());
//
//
//        int[] frequency = new int[6]; // Scores 0-5
//        for (PiyCompetitor c : competitors) {
//            for (int s : c.getScoreArray()) {
//                if (s >= 0 && s <= 5) frequency[s]++;
//            }
//        }
//
//        System.out.println("• Frequency of individual scores:");
//        System.out.print("Score:     ");
//        for (int i = 0; i <= 5; i++) {
//            if (frequency[i] > 0) System.out.printf("%-4d", i);
//        }
//        System.out.println();
//
//        System.out.print("Frequency: ");
//        for (int i = 0; i <= 5; i++) {
//            if (frequency[i] > 0) System.out.printf("%-4d", frequency[i]);
//        }
//        System.out.println("\n------------------------------------------------");
//    }
//}
//


public class Main extends JFrame {

    public Main() {
        setTitle("Quiz System - Main Menu");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 20, 20));
        panel.setBorder(new EmptyBorder(40, 40, 40, 40));
        // Removed custom background color

        // Header
        JLabel lblTitle = new JLabel("Welcome to QuizMaster", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24)); // Standard font
        panel.add(lblTitle);

        // Button 1: Player
        JButton btnPlayer = new JButton("I am a Player (Login/Play)");
        // Removed styleButton call -> Uses default Java look
        btnPlayer.addActionListener(e -> {
            dispose();
            new LoginGUI();
        });
        panel.add(btnPlayer);

        // Button 2: Manager
        JButton btnManager = new JButton("I am the Manager (View Reports)");
        // Removed styleButton call -> Uses default Java look
        btnManager.addActionListener(e -> {
            String pass = JOptionPane.showInputDialog(this, "Enter Admin Password:");
            if (pass != null && pass.equals("admin123")) {
                dispose();
                new ManagerReportGUI();
            } else if (pass != null) {
                JOptionPane.showMessageDialog(this, "Access Denied.");
            }
        });
        panel.add(btnManager);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}