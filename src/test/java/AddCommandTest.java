import duke.command.AddCommand;
import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AddCommandTest {

    @Test
    void newCommand_deadline_success() {
        try {
            Command c = new AddCommand("deadline", "test", "010119 1200");
            String output = c.execute(new TaskList(), new Ui(), new Storage("data/tasks.txt"));
            assertEquals("     Got it. I've added this task: \n"
                    + "       [D][✘] test (by: 2019-01-01T12:00)\n"
                    + "     Now you have 1 tasks in the list.", output);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void newCommand_eventWrongFormat_exceptionThrown() {
        try {
            Command c = new AddCommand("event", "test", "01012019 1200");
            c.execute(new TaskList(), new Ui(), new Storage("data/tasks.txt"));
        } catch (DukeException e) {
            assertEquals("Please enter your date/time format in \"dd/MM/yy HHmm\"", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }
}
