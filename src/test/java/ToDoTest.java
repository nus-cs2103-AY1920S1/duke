
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toStringTest() {
           ToDoStub testObject = new ToDoStub();
           String testObjectString = testObject.toString();
           assertEquals("[T][\u2718] read book", testObjectString);
    }

    @Test
    public void createTaskInFileFormatTest() {
        ToDoStub testObject = new ToDoStub();
        assertEquals("T 0 read book", testObject.createTaskInFileFormat());
    }

}
