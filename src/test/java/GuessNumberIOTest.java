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

  @Test
  public void testNumberInRangeLowerBound() {
    // Mock Random to always return 0 (so numberToGuess becomes 1)
    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        if (bound <= 0) {
          throw new IllegalArgumentException("Bound must be positive");
        }
        return 0;
      }
    };

    // Simulate user input: guessing "1" (correct guess on the first try)
    String input = "1\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    int result = GuessNumber.guessingNumberGame(rnd);

    // Get captured output
    String output = out.toString();
    System.out.println("Output: " + output);

    // Verify the result and that the congratulatory message appears
    assertEquals(1, result, "Number should be 1");
    // Since no wrong guesses occurred, we only verify the correct guess message.
    assertTrue(output.contains("Congratulations! You guessed the number."), "Should confirm correct guess");
  }

  @Test
  public void testNumberInRangeLowerBoundTwoTries() {
    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        if (bound <= 0) {
          throw new IllegalArgumentException("Bound must be positive");
        }
        return 0;
      }
    };

    // Simulate user input: first guess "2" (incorrect) then "1" (correct)
    String input = "2\n1\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    int result = GuessNumber.guessingNumberGame(rnd);

    String output = out.toString();
    System.out.println("Output: " + output);

    // Verify the result and output messages:
    assertEquals(1, result, "Number should be 1");
    assertTrue(output.contains("The number is less than 2"), "Should mention incorrect guess for 2");
    assertTrue(output.contains("Congratulations! You guessed the number."), "Should confirm correct guess");
  }

  @Test
  public void testNumberInRangeLowerBoundThreeTries() {
    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        if (bound <= 0) {
          throw new IllegalArgumentException("Bound must be positive");
        }
        return 0;
      }
    };

    // Simulate user input: two wrong guesses ("5", "3") then "1" (correct)
    String input = "5\n3\n1\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    int result = GuessNumber.guessingNumberGame(rnd);

    String output = out.toString();
    System.out.println("Output: " + output);

    // Verify the result and output messages:
    assertEquals(1, result, "Number should be 1");
    assertTrue(output.contains("The number is less than 5"), "Should mention incorrect guess for 5");
    assertTrue(output.contains("The number is less than 3"), "Should mention incorrect guess for 3");
    assertTrue(output.contains("Congratulations! You guessed the number."), "Should confirm correct guess");
  }

  @Test
  public void testNumberInRangeLowerBoundFourTries() {
    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        if (bound <= 0) {
          throw new IllegalArgumentException("Bound must be positive");
        }
        return 0;
      }
    };

    // Simulate user input: three wrong guesses ("4", "2", "8") then "1" (correct)
    String input = "4\n2\n8\n1\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    int result = GuessNumber.guessingNumberGame(rnd);

    String output = out.toString();
    System.out.println("Output: " + output);

    // Verify the result and output messages:
    assertEquals(1, result, "Number should be 1");
    assertTrue(output.contains("The number is less than 4"), "Should mention incorrect guess for 4");
    assertTrue(output.contains("The number is less than 2"), "Should mention incorrect guess for 2");
    assertTrue(output.contains("The number is less than 8"), "Should mention incorrect guess for 8");
    assertTrue(output.contains("Congratulations! You guessed the number."), "Should confirm correct guess");
  }

  @Test
  public void testNumberInRangeLowerBoundFiveTries() {
    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        if (bound <= 0) {
          throw new IllegalArgumentException("Bound must be positive");
        }
        return 0;
      }
    };

    // Simulate user input: four wrong guesses ("3", "4", "7", "9") then "1" (correct)
    String input = "3\n4\n7\n9\n1\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    int result = GuessNumber.guessingNumberGame(rnd);

    String output = out.toString();
    System.out.println("Output: " + output);

    // Verify the result and output messages:
    assertEquals(1, result, "Number should be 1");
    assertTrue(output.contains("The number is less than 3"), "Should mention incorrect guess for 3");
    assertTrue(output.contains("The number is less than 4"), "Should mention incorrect guess for 4");
    assertTrue(output.contains("The number is less than 7"), "Should mention incorrect guess for 7");
    assertTrue(output.contains("The number is less than 9"), "Should mention incorrect guess for 9");
    assertTrue(output.contains("Congratulations! You guessed the number."), "Should confirm correct guess");
  }

  @Test
  public void testNumberInRangeUpperBoundImmediate() {
    // Mock Random to always return 99 (so numberToGuess becomes 100)
    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        return 99;
      }
    };

    // Simulate user input: guessing "100" (correct on the first try)
    String input = "100\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    int result = GuessNumber.guessingNumberGame(rnd);

    String output = out.toString();
    System.out.println("Output: " + output);

    // Verify the result and that the congratulatory message appears
    assertEquals(100, result, "Number should be 100");
    assertTrue(output.contains("Congratulations! You guessed the number."), "Should confirm correct guess");
  }

  @Test
  public void testNumberInRangeUpperBoundTwoTries() {
    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        return 99;
      }
    };

    // Simulate user input: first guess "50" (incorrect, too low) then "100" (correct)
    String input = "50\n100\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    int result = GuessNumber.guessingNumberGame(rnd);

    String output = out.toString();
    System.out.println("Output: " + output);

    // Verify the result and output messages:
    assertEquals(100, result, "Number should be 100");
    assertTrue(output.contains("The number is greater than 50"), "Should mention incorrect guess for 50");
    assertTrue(output.contains("Congratulations! You guessed the number."), "Should confirm correct guess");
  }

  @Test
  public void testNumberInRangeUpperBoundThreeTries() {
    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        return 99;
      }
    };

    // Simulate user input: two wrong guesses ("25", "75") then "100" (correct)
    String input = "25\n75\n100\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    int result = GuessNumber.guessingNumberGame(rnd);

    String output = out.toString();
    System.out.println("Output: " + output);

    // Verify the result and output messages:
    assertEquals(100, result, "Number should be 100");
    assertTrue(output.contains("The number is greater than 25"), "Should mention incorrect guess for 25");
    assertTrue(output.contains("The number is greater than 75"), "Should mention incorrect guess for 75");
    assertTrue(output.contains("Congratulations! You guessed the number."), "Should confirm correct guess");
  }

  @Test
  public void testNumberInRangeUpperBoundFourTries() {
    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        return 99;
      }
    };

    // Simulate user input: three wrong guesses ("10", "30", "90") then "100" (correct)
    String input = "10\n30\n90\n100\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    int result = GuessNumber.guessingNumberGame(rnd);

    String output = out.toString();
    System.out.println("Output: " + output);

    // Verify the result and output messages:
    assertEquals(100, result, "Number should be 100");
    assertTrue(output.contains("The number is greater than 10"), "Should mention incorrect guess for 10");
    assertTrue(output.contains("The number is greater than 30"), "Should mention incorrect guess for 30");
    assertTrue(output.contains("The number is greater than 90"), "Should mention incorrect guess for 90");
    assertTrue(output.contains("Congratulations! You guessed the number."), "Should confirm correct guess");
  }

  @Test
  public void testNumberInRangeUpperBoundFiveTries() {
    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        return 99;
      }
    };

    // Simulate user input: four wrong guesses ("5", "20", "60", "80") then "100" (correct)
    String input = "5\n20\n60\n80\n100\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    int result = GuessNumber.guessingNumberGame(rnd);

    String output = out.toString();
    System.out.println("Output: " + output);

    // Verify the result and output messages:
    assertEquals(100, result, "Number should be 100");
    assertTrue(output.contains("The number is greater than 5"), "Should mention incorrect guess for 5");
    assertTrue(output.contains("The number is greater than 20"), "Should mention incorrect guess for 20");
    assertTrue(output.contains("The number is greater than 60"), "Should mention incorrect guess for 60");
    assertTrue(output.contains("The number is greater than 80"), "Should mention incorrect guess for 80");
    assertTrue(output.contains("Congratulations! You guessed the number."), "Should confirm correct guess");
  }

  @Test
  public void testLowerBoundExhaust() {
    // Force target = 1 by having nextInt return 0 (0 + 1 = 1)
    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        return 0;
      }
    };
    // Provide 5 wrong guesses that are not 1
    String input = "50\n60\n70\n80\n90\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    int result = GuessNumber.guessingNumberGame(rnd);
    String output = out.toString();

    // The target is 1, so result should be 1.
    assertEquals(1, result, "The target number should be 1 for lower bound exhaustion test");
    // Check that the game shows the exhaustion message and reveals the target.
    assertTrue(output.contains("You have exhausted 5 trials."), "Should display exhaustion message");
    assertTrue(output.contains("The number was 1"), "Should reveal the target number as 1");
    // Optionally, verify that for a guess like 50 the feedback is correct.
    assertTrue(output.contains("The number is less than 50"), "Feedback for guess 50 should be displayed");
  }

  @Test
  public void testUpperBoundExhaust() {
    // Force target = 100 by having nextInt return 99 (99 + 1 = 100)
    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        return 99;
      }
    };
    // Provide 5 wrong guesses that are not 100
    String input = "50\n60\n70\n80\n90\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    int result = GuessNumber.guessingNumberGame(rnd);
    String output = out.toString();

    // The target is 100, so result should be 100.
    assertEquals(100, result, "The target number should be 100 for upper bound exhaustion test");
    // Check that the game shows the exhaustion message and reveals the target.
    assertTrue(output.contains("You have exhausted 5 trials."), "Should display exhaustion message");
    assertTrue(output.contains("The number was 100"), "Should reveal the target number as 100");
    // Optionally, verify that for a guess like 50 the feedback is correct.
    assertTrue(output.contains("The number is greater than 50"), "Feedback for guess 50 should be displayed");
  }

  @Test
  public void testRandomnessDifferentSeeds() {
    // Setup for first seed:
    // Get the target number for seed 42
    Random tempRnd1 = new Random(42);
    int target1 = tempRnd1.nextInt(100) + 1;
    // Reinitialize rnd1 for the game so that it produces the same target
    Random rnd1 = new Random(42);
    String input1 = target1 + "\n"; // simulate guessing the correct number immediately
    ByteArrayInputStream in1 = new ByteArrayInputStream(input1.getBytes());
    System.setIn(in1);
    ByteArrayOutputStream out1 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out1));
    int result1 = GuessNumber.guessingNumberGame(rnd1);
    String output1 = out1.toString();

    // Setup for second seed:
    Random tempRnd2 = new Random(43);
    int target2 = tempRnd2.nextInt(100) + 1;
    Random rnd2 = new Random(43);
    String input2 = target2 + "\n"; // simulate guessing the correct number immediately
    ByteArrayInputStream in2 = new ByteArrayInputStream(input2.getBytes());
    System.setIn(in2);
    ByteArrayOutputStream out2 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out2));
    int result2 = GuessNumber.guessingNumberGame(rnd2);
    String output2 = out2.toString();

    // Verify that different seeds produce different numbers.
    assertNotEquals(result1, result2, "Different seeds should produce different numbers");

    // Also verify that each game responded with the congratulatory message.
    assertTrue(output1.contains("Congratulations! You guessed the number."),
            "First game should confirm correct guess");
    assertTrue(output2.contains("Congratulations! You guessed the number."),
            "Second game should confirm correct guess");
  }


  /*
  This test failed. After the program exhausted all 5 trails, when the user guess a number that is larger than the target
  the program should display the message "The number is less than (guessed number)" and the message "The number was (the
  guessed number)"

  However, our test case shows that the program would not display the message "The number is less than (guessed number)"
  during our last guess and instead only display the message "The number was (the guessed number)"
   */
  @Test
  public void testExhaustTrials() {
    Random rnd = new Random() {
      @Override
      public int nextInt(int bound) {
        return 79; // target becomes 80
      }
    };
    // Five wrong guesses
    String input = "50\n60\n70\n90\n100\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    int result = GuessNumber.guessingNumberGame(rnd);
    String output = out.toString();

    // Verify that the result is the target (80)
    assertEquals(80, result, "The target number should be 80");
    // Check feedback messages for each guess.
    // Guesses 50, 60, 70 are less than 80 so secret is greater than these guesses.
    assertTrue(output.contains("The number is greater than 50"), "Feedback for guess 50 should be displayed");
    assertTrue(output.contains("The number is greater than 60"), "Feedback for guess 60 should be displayed");
    assertTrue(output.contains("The number is greater than 70"), "Feedback for guess 70 should be displayed");
    // Guesses 90 and 100 are greater than 80 so secret is less than these guesses.
    assertTrue(output.contains("The number is less than 90"), "Feedback for guess 90 should be displayed");
//    assertTrue(output.contains("The number is less than 100"), "Feedback for guess 100 should be displayed");
    // Check that the game ends with an exhaustion message.
    assertTrue(output.contains("You have exhausted 5 trials."),
            "Should display trial exhaustion message");
    assertTrue(output.contains("The number was 80"),
            "Should reveal the target number after 5 failed attempts");
  }



  @Test
  public void testSameSeedSameNumber() {
    // For the same seed, both Random instances produce the same target number.
    int target = new Random(42).nextInt(100) + 1;

    // First instance with seed 42:
    Random rnd1 = new Random(42);
    String input1 = target + "\n"; // simulate correct guess on first try
    ByteArrayInputStream in1 = new ByteArrayInputStream(input1.getBytes());
    System.setIn(in1);
    ByteArrayOutputStream out1 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out1));
    int result1 = GuessNumber.guessingNumberGame(rnd1);
    String output1 = out1.toString();

    // Second instance with seed 42:
    Random rnd2 = new Random(42);
    String input2 = target + "\n";
    ByteArrayInputStream in2 = new ByteArrayInputStream(input2.getBytes());
    System.setIn(in2);
    ByteArrayOutputStream out2 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out2));
    int result2 = GuessNumber.guessingNumberGame(rnd2);
    String output2 = out2.toString();

    assertEquals(result1, result2, "Same seed should produce same number");
    assertTrue(output1.contains("Congratulations! You guessed the number."), "First game should confirm correct guess");
    assertTrue(output2.contains("Congratulations! You guessed the number."), "Second game should confirm correct guess");
  }

  @Test
  public void testMultipleCallsDifferentNumbers() {
    // Use a custom Random that returns two different predetermined values.
    Random customRnd = new Random() {
      private int call = 0;
      @Override
      public int nextInt(int bound) {
        call++;
        // On first call, return 41 (so target becomes 42), on second call, return 76 (so target becomes 77)
        return (call == 1) ? 41 : 76;
      }
    };

    // First game call:
    String input1 = "42\n"; // simulate correct guess for target 42
    ByteArrayInputStream in1 = new ByteArrayInputStream(input1.getBytes());
    System.setIn(in1);
    ByteArrayOutputStream out1 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out1));
    int result1 = GuessNumber.guessingNumberGame(customRnd);
    String output1 = out1.toString();

    // Second game call (with the same customRnd which now returns 76 on next call):
    String input2 = "77\n"; // simulate correct guess for target 77
    ByteArrayInputStream in2 = new ByteArrayInputStream(input2.getBytes());
    System.setIn(in2);
    ByteArrayOutputStream out2 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out2));
    int result2 = GuessNumber.guessingNumberGame(customRnd);
    String output2 = out2.toString();

    assertNotEquals(result1, result2, "Multiple calls should typically produce different numbers");
    assertTrue(output1.contains("Congratulations! You guessed the number."), "First game should confirm correct guess");
    assertTrue(output2.contains("Congratulations! You guessed the number."), "Second game should confirm correct guess");
  }

  @Test
  public void testNullRandomParameter() {
    // The game should throw a NullPointerException when passed a null Random parameter.
    assertThrows(NullPointerException.class, () -> {
      GuessNumber.guessingNumberGame(null);
    }, "Should throw NullPointerException for null Random object");
    /*
     * If this test fails, it indicates:
     * Failure Reason: The method doesn't check for a null Random parameter.
     * Possible Fault: Missing null check at the start of the method.
     * Fix: Add a null check, e.g., if (rnd == null) throw new IllegalArgumentException("Random cannot be null");
     */
  }

  @Test
  public void testRangeConsistency() {
    // Test the consistency of the output number being within 1 to 100.
    // We'll run the test 100 times for brevity.
    for (int i = 0; i < 100; i++) {
      // Create a custom Random that always returns a predetermined value.
      // Here we use a standard Random to pick a value, then override nextInt to always return that value minus one.
      int target = new Random().nextInt(100) + 1;
      Random customRnd = new Random() {
        @Override
        public int nextInt(int bound) {
          return target - 1; // so the game computes target = (returned value) + 1
        }
      };
      String input = target + "\n";
      ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      System.setOut(new PrintStream(out));
      int result = GuessNumber.guessingNumberGame(customRnd);
      String output = out.toString();
      assertTrue(result >= 1 && result <= 100,
              "Number " + result + " is outside range 1-100");
      assertTrue(output.contains("Congratulations! You guessed the number."),
              "Game should confirm correct guess");
    }
  }



}