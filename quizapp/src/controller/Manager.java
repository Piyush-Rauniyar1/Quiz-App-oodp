package controller;

import model.CompetitorList;
import model.PiyCompetitor;

/**
 * The Manager class controls the flow of the application.
 * It generates reports and manages the competitor list.
 * <p>
 * Developed for 5CS019 Object Oriented Design and Programming.
 * </p>
 * @author Piyush
 * @version 1.0
 */
public class Manager {

    /** The list of competitors managed by this system. */
    private CompetitorList list;

    /**
     * Constructs the Manager and initializes the list.
     */
    public Manager() {
        this.list = new CompetitorList();
    }

    /**
     * Runs the manager system: loads data and prints reports.
     */
    public void run() {
        System.out.println("--- LOADING DATA ---");
        list.loadFromDatabase();

        if (list.getAllCompetitors().isEmpty()) {
            System.out.println("No data found in database.");
            return;
        }

        printFullReport();
        printTopPerformer();
        printStatistics();
    }

    /**
     * Prints a formatted table of all competitors.
     */
    private void printFullReport() {
        System.out.println("\n==========================================================================");
        System.out.println("                       COMPETITION RESULT REPORT                          ");
        System.out.println("==========================================================================");
        System.out.printf("%-5s %-20s %-15s %-20s %-10s\n", "ID", "Name", "Level", "Scores", "Overall");
        System.out.println("--------------------------------------------------------------------------");

        for (PiyCompetitor c : list.getAllCompetitors()) {
            System.out.printf("%-5d %-20s %-15s %-20s %-10.1f\n",
                    c.getCompetitorId(),
                    c.getName().getFullName(),
                    c.getLevel(),
                    java.util.Arrays.toString(c.getScores()),
                    c.getOverallScore());
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    /**
     * Prints the details of the top performer.
     */
    private void printTopPerformer() {
        PiyCompetitor winner = list.getTopPerformer();
        if (winner != null) {
            System.out.println("\n*** TOP PERFORMER ***");
            System.out.println("Name: " + winner.getName().getFullName());
            System.out.println("Score: " + String.format("%.1f", winner.getOverallScore()));
        }
    }

    /**
     * Prints statistical summary of scores.
     */
    private void printStatistics() {
        System.out.println("\n*** STATISTICAL SUMMARY ***");
        System.out.println(list.getScoreFrequencyReport());
        System.out.println("==========================================================================\n");
    }
}