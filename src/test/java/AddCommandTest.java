import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    void init() {
        storage = new Storage("");
        ui = new Ui();
        taskList = new TaskList(ui);
    }

    @Test
    void testTodoExecute() {
        String input = "test ToDo";
        InputStream in = new ByteArrayInputStream((input.getBytes()));
        System.setIn(in);
        try {
            AddToDoCommand ac = new AddToDoCommand();
            ac.execute(storage, taskList, ui, input);
        } catch (DukeException e) {
            fail("failed to add item into list");
        }
    }

    @Test
    void testDeadlineExecute() {
        String input = "test Deadline /by 08/08/2022 2359";
        InputStream in = new ByteArrayInputStream((input.getBytes()));
        System.setIn(in);
        try {
            AddDeadlineCommand ac = new AddDeadlineCommand();
            ac.execute(storage, taskList, ui, input);
        } catch (DukeException e) {
            fail("failed to add item into list");
        }
    }

    @Test
    void testEventExecute() {
        String input = "party /at a certain place";
        InputStream in = new ByteArrayInputStream((input.getBytes()));
        System.setIn(in);
        try {
            AddEventCommand ac = new AddEventCommand();
            ac.execute(storage, taskList, ui, input);
        } catch (DukeException e) {
            fail("failed to add item into list");
        }
    }
}
