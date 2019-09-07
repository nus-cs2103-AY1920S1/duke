import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    void init() {
        storage = new Storage("");
        taskList = new TaskList();
    }

    @Test
    void testTodoExecute() {
        String input = "test ToDo";
        InputStream in = new ByteArrayInputStream((input.getBytes()));
        System.setIn(in);
        ui = new Ui();
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
        ui = new Ui();
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
        ui = new Ui();
        try {
            AddEventCommand ac = new AddEventCommand();
            ac.execute(storage, taskList, ui, input);
        } catch (DukeException e) {
            fail("failed to add item into list");
        }
    }
}
