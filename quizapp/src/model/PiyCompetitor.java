package model;

import databaseconfig.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
/**
 * Represents an individual competitor in the competition.
 * <p>
 * Stores details such as ID, name, level, country, and scores.
 * Includes functionality to calculate overall scores and save updates to the database.
 * </p>
 *
 * @author Piyush
 * @version 1.0
 */
public class PiyCompetitor {
    private int competitorId;
    private Name name;
    private String level;
    private String country;
    private int[] scores = new int[5];

    public PiyCompetitor(int competitorId, Name name, String level, String country) {
        this.competitorId = competitorId;
        this.name = name;
        this.level = level;
        this.country = country;
    }

    // --- NEW METHODS REQUIRED FOR ASSIGNMENT ---

    // Requirement: "return a String containing all the details" [cite: 64]
    public String getFullDetails() {
        return "Competitor number " + competitorId + ", name " + name.getFullName() + ", country " + country + ".\n" +
                name.getFirstName() + " is a " + level + " and has an overall score of " + String.format("%.1f", getOverallScore()) + ".";
    }

    // Requirement: "return a String containing ONLY competitor number, initials and overall score" [cite: 68]
    public String getShortDetails() {
        String initials = name.getFirstName().charAt(0) + "" + name.getLastName().charAt(0);
        return "CN " + competitorId + " (" + initials + ") has overall score " + String.format("%.1f", getOverallScore()) + ".";
    }

    // Requirement: "return the array of integer scores" [cite: 75]
    public int[] getScoreArray() {
        return scores;
    }

    public void saveToDatabase() {
        String sql = "UPDATE Competitors SET level=?, score1=?, score2=?, score3=?, score4=?, score5=? WHERE competitor_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, level);
            for(int i=0; i<5; i++) pstmt.setInt(2+i, scores[i]);
            pstmt.setInt(7, competitorId);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void setScores(int[] scores) { this.scores = scores; }
    public void setLevel(String level) { this.level = level; }

    // Getters
    public int[] getScores() { return scores; }
    public Name getName() { return name; }
    public String getLevel() { return level; }
    public int getCompetitorId() { return competitorId; }

    public double getOverallScore() {
        if (scores == null) return 0.0;
        int sum=0, max=Integer.MIN_VALUE, min=Integer.MAX_VALUE;
        for(int s : scores) { sum+=s; if(s>max) max=s; if(s<min) min=s; }
        return (sum-max-min)/3.0;
    }
}