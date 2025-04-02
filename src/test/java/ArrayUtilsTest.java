import org.example.ArrayUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayUtilsTest {

  @Test
  public void testFindLastNull() {
    // Do not execute fault
    assertThrows(NullPointerException.class, () -> {
      ArrayUtils.findLast(null, 3);});
  }


  @Test
  public void testFindLastEmpty() {
    // For any input where y appears in the second or later position, there is no error. Also,
    // if x is empty, there is no error.
    assertEquals(-1, ArrayUtils.findLast(new int[]{}, 3));
  }

  @Test
  public void testFindLastNonExistent() {
    assertEquals(-1, ArrayUtils.findLast(new int[]{1, 2}, 3));
  }

  @Test
  public void testFindLastExistsFirstElement() {
    assertEquals(0, ArrayUtils.findLast(new int[]{2, 3, 5}, 2));
  }

  @Test
  public void testOddOrPosNull() {
    // Do not execute fault
    assertThrows(NullPointerException.class, () -> {ArrayUtils.oddOrPos(null);});
  }


  @Test
  public void testOddOrPosAllPositives() {
    // Any nonempty x with only non-negative elements works, because the first part of the
    // compound if-test is not necessary unless the value is negative.
    assertEquals(3, ArrayUtils.oddOrPos(new int[]{1, 2, 3}));
  }

  @Test
  public void testOddOrPositiveBothPositivesAndNegatives() {
    assertEquals(3, ArrayUtils.oddOrPos(new int[]{-3, -2, 0, 1, 4}));
  }

  // Test when the array is empty.
  @Test
  public void testEmptyArray() {
    int[] arr = {};
    int target = 5;
    assertEquals(0, ArrayUtils.countOf(arr, target));
  }

  // Test when there are no occurrences of the target in the array.
  @Test
  public void testNoOccurrence() {
    int[] arr = {1, 2, 3, 4};
    int target = 5;
    assertEquals(0, ArrayUtils.countOf(arr, target));
  }

  // Test when all elements in the array match the target.
  @Test
  public void testAllOccurrence() {
    int[] arr = {5, 5, 5};
    int target = 5;
    assertEquals(3, ArrayUtils.countOf(arr, target));
  }

  // Test a mix of matching and non-matching elements.
  @Test
  public void testMixedOccurrence() {
    int[] arr = {5, 2, 5, 3, 4, 5, 6};
    int target = 5;
    assertEquals(3, ArrayUtils.countOf(arr, target));
  }

  // Test a single element array where the element matches the target.
  @Test
  public void testSingleElementMatch() {
    int[] arr = {5};
    int target = 5;
    assertEquals(1, ArrayUtils.countOf(arr, target));
  }

  // Test a single element array where the element does not match the target.
  @Test
  public void testSingleElementNoMatch() {
    int[] arr = {7};
    int target = 5;
    assertEquals(0, ArrayUtils.countOf(arr, target));
  }

  // Test with negative numbers.
  @Test
  public void testNegativeNumbers() {
    int[] arr = {-5, -5, 0, -5};
    int target = -5;
    assertEquals(3, ArrayUtils.countOf(arr, target));
  }

  // Test that a null array throws a NullPointerException.
//  @Test
//  public void testNullArrayThrowsException() {
//    int target = 5;
//    assertThrows(NullPointerException.class, () -> ArrayUtils.countOf(null, target));
//  }
}