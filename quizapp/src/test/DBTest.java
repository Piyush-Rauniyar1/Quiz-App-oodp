package test;

import databaseconfig.DBConnection;
import model.CompetitorList; // Changed Import
import model.PiyCompetitor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

public class DBTest {
    private int testUserId = -1;

    @AfterEach
    public void tearDown() {
        if (testUserId != -1) deleteTestUser(testUserId);
    }

    @Test
    @DisplayName("1. Test Database Connection")
    public void testConnection() throws SQLException {
        Connection conn = DBConnection.getConnection();
        assertNotNull(conn);
        assertFalse(conn.isClosed());
        conn.close();
    }

    @Test
    @DisplayName("2. Test Register and Login Workflow")
    public void testRegisterAndLogin() {
        CompetitorList list = new CompetitorList(); // Changed Class

        String uniqueName = "TestUser_" + System.currentTimeMillis();
        // Changed method to addCompetitor
        testUserId = list.addCompetitor(uniqueName, "Tester", "TestLand", "password123");

        assertTrue(testUserId > 0);

        // Changed method to getCompetitor
        PiyCompetitor user = list.getCompetitor(testUserId, "password123");
        assertNotNull(user);
        assertEquals(testUserId, user.getCompetitorId());
    }

    @Test
    @DisplayName("3. Test Saving and Retrieving Scores")
    public void testScoreUpdate() {
        CompetitorList list = new CompetitorList(); // Changed Class
        testUserId = list.addCompetitor("Score", "Tester", "TestLand", "pass");
        PiyCompetitor user = list.getCompetitor(testUserId, "pass");

        int[] newScores = {5, 5, 5, 5, 5};
        user.setScores(newScores);
        user.setLevel("Advanced");
        user.saveToDatabase();

        PiyCompetitor reloadedUser = list.getCompetitor(testUserId, "pass");

        assertArrayEquals(newScores, reloadedUser.getScoreArray());
        assertEquals("Advanced", reloadedUser.getLevel());
    }

    private void deleteTestUser(int id) {
        // ... (Keep existing delete logic) ...
        String sql = "DELETE FROM Competitors WHERE competitor_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}