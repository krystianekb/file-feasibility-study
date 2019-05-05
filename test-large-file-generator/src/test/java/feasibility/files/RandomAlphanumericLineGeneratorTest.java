package feasibility.files;

import org.junit.jupiter.api.Test;
import org.krystianekb.feasibility.files.RandomAlphanumericLineGenerator;

import static org.junit.jupiter.api.Assertions.*;

public class RandomAlphanumericLineGeneratorTest {

  @Test
  public void testGeneratedLine() {
    // GIVEN
    int size = 2048;
    RandomAlphanumericLineGenerator generator = new RandomAlphanumericLineGenerator(size);
    // WHEN
    String line = generator.generateLine();
    // THEN
    assertEquals(size, line.length(), size);
    assertTrue(line.matches("^[a-zA-Z0-9 ]+$"));
  }

  @Test
  public void testUniqueLineGeneration() {
    // GIVEN
    int size = 2048;
    RandomAlphanumericLineGenerator generator = new RandomAlphanumericLineGenerator(size);
    // WHEN
    String line1 = generator.generateLine();
    String line2 = generator.generateLine();
    // THEN
    assertNotEquals(line1, line2);
  }

}
