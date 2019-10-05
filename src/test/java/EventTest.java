import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void dummyTest() {
        try {
            Task task = new Event("read book", Parser.dateFormatter("6/6/2019 1900"));
            task.markedAsDone();
            assertEquals(task.isDone(), true);
        } catch (DukeException e) {
            System.err.println("Something went wrong: " + e.getMessage());
        }
    }
}