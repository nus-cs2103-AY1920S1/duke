package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

  @Test
  void storageString_normalInput_success() {
    ToDo current = new ToDo("read book");
    assertEquals("T,0,read book", current.storageString());
    current.setDone();
    assertEquals("T,1,read book", current.storageString());
  }

  @Test
  void testToString_normalInput_success() {
    ToDo current = new ToDo("read book");
    assertEquals("[T][✗] read book", current.toString());
    current.setDone();
    assertEquals("[T][✓] read book", current.toString());
  }
}