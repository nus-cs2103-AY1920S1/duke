import duke.Parser;
import duke.Storage;
import duke.ui.Ui;
import duke.TaskList;
import duke.command.Command;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
//@@adapted from author: CarbonGrid for formatting
    private StringBuilder tests = new StringBuilder().append("todo borrow book\n")
            .append("deadline to homework /by: 2019-12-12T10:10:00\n");
            /*.append("event watch movie /at: 2019-10-10T20:30:00\n")
            .append("done 3\n")
            .append("list\n")
            .append("bye\n");*/

    private String [] expected = {
        "Got it. I've added this task:\n        [T][0] borrow book\n      Now you have 1 tasks in the list.\n",
            "Got it. I've added this task:\n        [D][0] to homework (at: 2019-12-12T10:10)\n      Now you have 2 tasks in the list."

    };
//@@author
    @Test
    public void runTest() {
        System.setIn(new ByteArrayInputStream(tests.toString()
                .getBytes()));

        int i = 0;

        ui = new UiTest();
        storage = new StorageTest();
        tasks = new TaskList();
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(fullCommand);
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.show(e.getMessage());
            } finally {
                assertEquals(expected[i++], ui.toString());
            }
        }
    }
}
