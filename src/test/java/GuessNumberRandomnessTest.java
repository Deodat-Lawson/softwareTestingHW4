import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class GuessNumberRandomnessTest {

  @Test
  public void testUniformDistribution() {
    // Fix seed at 0 for reproducibility
    Random rnd = new Random(0);

    // Map to store frequency of each number (1 to 100)
    Map<Integer, Integer> frequencyMap = new HashMap<>();

    // Initialize map with 0 counts for all numbers 1 to 100
    for (int i = 1; i <= 100; i++) {
      frequencyMap.put(i, 0);
    }

    // Run the function 30,000 times
    int totalRuns = 30000;
    for (int i = 0; i < totalRuns; i++) {
      int result = GuessNumber.guessingNumberGame(rnd);
      frequencyMap.put(result, frequencyMap.get(result) + 1);
    }

    // Calculate min and max frequencies observed
    int minFrequency = Integer.MAX_VALUE;
    int maxFrequency = Integer.MIN_VALUE;

    for (int i = 1; i <= 100; i++) {
      int freq = frequencyMap.get(i);
      minFrequency = Math.min(minFrequency, freq);
      maxFrequency = Math.max(maxFrequency, freq);

      // Verify each number appears at least once
      assertTrue(freq > 0, "Number " + i + " was never generated");
    }

    // Check if all frequencies are within ±50% of each other
    // ±50% of 300 = 150 to 450
    double lowerBound = 150;
    double upperBound = 450;

    for (int i = 1; i <= 100; i++) {
      int freq = frequencyMap.get(i);
      assertTrue(freq >= lowerBound && freq <= upperBound,
              String.format("Number %d has frequency %d, outside range [%.0f, %.0f]",
                      i, freq, lowerBound, upperBound));
    }

    // Alternative check: max frequency should not be more than 1.5x min frequency
    assertTrue(maxFrequency <= minFrequency * 1.5,
            String.format("Distribution not uniform: min frequency = %d, max frequency = %d",
                    minFrequency, maxFrequency));
  }
}