package test;

import model.Name;
import model.PiyCompetitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 Tests for the PiyCompetitor class.
 * Covers requirements from Part One and Part Two of the assessment.
 */
public class CompetitorTest {

    private PiyCompetitor competitor;
    private int[] validScores;

    @BeforeEach
    public void setUp() {
        // Initialize a standard competitor before each test
        Name name = new Name("Alice", "Green");
        // ID: 200, Level: Beginner, Country: UK
        competitor = new PiyCompetitor(200, name, "Beginner", "UK");

        // standard scores: 4, 3, 5, 2, 4
        validScores = new int[]{4, 3, 5, 2, 4};
        competitor.setScores(validScores);
    }

    // --- 1. Test Overall Score Calculation ---
    @Test
    public void testGetOverallScore() {
        // Logic: (Sum - Max - Min) / 3.0
        // (18 - 5 - 2) / 3.0 = 11 / 3.0 = 3.666...

        // Syntax: assertEquals(expected, actual, delta, message)
        assertEquals(3.66, competitor.getOverallScore(), 0.1, "Overall score calculation is incorrect");
    }

    @Test
    public void testGetOverallScoreAllSame() {
        // Edge Case: All scores are 5
        competitor.setScores(new int[]{5, 5, 5, 5, 5});
        assertEquals(5.0, competitor.getOverallScore(), 0.0, "Score should be 5.0 if all scores are 5");
    }

    // --- 2. Test Full Details ---
    @Test
    public void testGetFullDetails() {
        String details = competitor.getFullDetails();

        // Syntax: assertTrue(condition, message)
        assertTrue(details.contains("200"), "Details should contain ID");
        assertTrue(details.contains("Alice Green"), "Details should contain Name");
        assertTrue(details.contains("Beginner"), "Details should contain Level");
        assertTrue(details.contains("UK"), "Details should contain Country");
    }

    // --- 3. Test Short Details ---
    @Test
    public void testGetShortDetails() {
        // Requirement format: CN 200 (AG) has overall score 3.7
        String shortDetails = competitor.getShortDetails();

        assertTrue(shortDetails.startsWith("CN 200"), "Short details must start with CN ID");
        assertTrue(shortDetails.contains("(AG)"), "Short details must contain Initials");
    }

    // --- 4. Test Get Score Array ---
    @Test
    public void testGetScoreArray() {
        // Syntax: assertArrayEquals(expected, actual, message)
        assertArrayEquals(validScores, competitor.getScoreArray(), "Score array returned should match set scores");
    }

    // --- 5. Test Set Level ---
    @Test
    public void testSetLevel() {
        competitor.setLevel("Advanced");
        assertEquals("Advanced", competitor.getLevel(), "Level setter failed");
    }
}