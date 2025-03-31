import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class GuessNumberIOTest {

  static BufferedInputStream in;
  static PrintStream out;

  @BeforeEach
  public void setup(){
    byte[] data = "software testing".getBytes(StandardCharsets.UTF_8);
    in = new BufferedInputStream(new ByteArrayInputStream(data));
    out = new PrintStream(new ByteArrayOutputStream());

  }


  //Test the
  @Test
  public void testNumberInRangeLowerBound() {

    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        // Always return 1, but you should also handle "bound = 0" to avoid an error.
        if (bound == 0) {
          throw new IllegalArgumentException("Bound must be positive");
        }
        return 1; //always returns 1
      }
    };

    System.setIn(in);
    System.setOut(out);

    GuessNumber.guessingNumberGame(rnd);

    String string = out.toString();
    System.out.println(string);

    System.out.println();



    assertEquals(1, result, "Number should be at least 1");
  }

  @Test
  public void testNumberInRangeUpperBound() {
    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        return 99; // Force highest possible value before adding 1
      }
    };
    int result = GuessNumber.guessingNumberGame(rnd);
    assertEquals(100, result, "Number should not exceed 100");
  }

  @Test
  public void testRandomnessDifferentSeeds() {
    Random rnd1 = new Random(42);
    Random rnd2 = new Random(43);
    int result1 = GuessNumber.guessingNumberGame(rnd1);
    int result2 = GuessNumber.guessingNumberGame(rnd2);
    assertNotEquals(result1, result2, "Different seeds should produce different numbers");
  }

  @Test
  public void testSameSeedSameNumber() {
    Random rnd1 = new Random(42);
    Random rnd2 = new Random(42);
    int result1 = GuessNumber.guessingNumberGame(rnd1);
    int result2 = GuessNumber.guessingNumberGame(rnd2);
    assertEquals(result1, result2, "Same seed should produce same number");
  }

  @Test
  public void testMultipleCallsDifferentNumbers() {
    Random rnd = new Random();
    int result1 = GuessNumber.guessingNumberGame(rnd);
    int result2 = GuessNumber.guessingNumberGame(rnd);
    // This might occasionally fail due to random chance
    assertNotEquals(result1, result2, "Multiple calls should typically produce different numbers");
  }

  // This test might fail depending on actual implementation
  @Test
  public void testNullRandomParameter() {
    assertThrows(NullPointerException.class, () -> {
      GuessNumber.guessingNumberGame(null);
    }, "Should throw NullPointerException for null Random object");
    /*
     * If this test fails, it means:
     * Failure Reason: The method doesn't check for null Random parameter
     * Possible Fault: Missing null check at the start of the method
     * Fix: Add if (rnd == null) throw new IllegalArgumentException("Random cannot be null");
     */
  }

  @Test
  public void testRangeConsistency() {
    Random rnd = new Random();
    for (int i = 0; i < 1000; i++) {
      int result = GuessNumber.guessingNumberGame(rnd);
      assertTrue(result >= 1 && result <= 100,
              "Number " + result + " is outside range 1-100");
    }
  }

  // This test might fail if implementation doesn't handle negative bounds
  @Test
  public void testNegativeRandomValues() {
    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        return -1; // Force negative value
      }
    };
    int result = GuessNumber.guessingNumberGame(rnd);
    assertTrue(result >= 1, "Result should never be less than 1");
    /*
     * If this test fails, it means:
     * Failure Reason: The method doesn't properly handle negative random values
     * Possible Fault: Implementation might be using raw Random.nextInt() without proper adjustment
     * Fix: Ensure result is calculated as Math.abs(rnd.nextInt(100)) + 1 or similar
     */
  }
}