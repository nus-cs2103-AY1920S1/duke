import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {

  @Test
  public void testStringConversion() {
    assertEquals("[E] [ ] project meeting (at:Sunday)", new Event("project meeting", "Sunday").toString());
    assertEquals("[E] [ ] CS2103 discussion (at:2pm)", new Event("CS2103 discussion", "2pm").toString());
  }

  @Test
  public void testSetAsDone() {
    Event e = new Event("project meeting", "Sunday");
    e.setAsDone();
    assertTrue(e.getBoolean());
  }

  @Test
  public void testGetStatusIcon() {
    Event e = new Event("project meeting", "Sunday");
    assertEquals(" ", e.getStatusIcon());
    e.setAsDone();
    assertEquals("X", e.getStatusIcon());
  }
}