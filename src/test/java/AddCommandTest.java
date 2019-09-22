import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {
    @Test
    public void testExecute() {
        try {
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            AddCommand lc = new AddCommand("deadline", "/by now");
            lc.execute(tasks, ui);
        } catch (DukeException e) {
            assertEquals("Please include task description!\n", e.getMessage());
        } catch (ParseException pe) {
            fail();
        }
    }
}
