import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadLineTest {

  @Test
  public void testStringConversion() {
    assertEquals("[D] [ ] return book (by:Sunday)", new DeadLine("return book", "Sunday").toString());
    assertEquals("[D] [ ] sign book (by:Monday)", new DeadLine("sign book", "Monday").toString());
  }

  @Test
  public void testSetAsDone() {
    DeadLine d = new DeadLine("return book", "Sunday");
    d.setAsDone();
    assertTrue(d.getBoolean());
  }

  @Test
  public void testGetStatusIcon() {
    DeadLine d = new DeadLine("return book", "Sunday");
    assertEquals(" ", d.getStatusIcon());
    d.setAsDone();
    assertEquals("X", d.getStatusIcon());
  }
}