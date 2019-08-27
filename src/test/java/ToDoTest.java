import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTest {

  @Test
  public void testStringConversion() {
    assertEquals("[T] [ ] borrow book", new ToDo("borrow book").toString());
    assertEquals("[T] [ ] return book", new ToDo("return book").toString());
  }

  @Test
  public void testSetAsDone() {
    ToDo t = new ToDo("borrow book");
    t.setAsDone();
    assertTrue(t.getBoolean());
  }

  @Test
  public void testGetStatusIcon() {
    ToDo t = new ToDo("borrow book");
    assertEquals(" ", t.getStatusIcon());
    t.setAsDone();
    assertEquals("X", t.getStatusIcon());
  }
}