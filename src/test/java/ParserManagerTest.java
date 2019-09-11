import duke.command.AddCommand;
import duke.command.Command;
import duke.parser.ParserManager;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.ToDoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserManagerTest {

    @Test
    public void testValidParsing() {
        String validInput11 = "todo Read book";
        String validInput12 = "TODO    Read book    ";
        String validInput21 = "deadline assignment /by 1/1/1991 1234";
        String validInput22 = "DEadLinE   assignment   /by    1/1/1991 1234";
        String validInput31 = "event sales /at 1/1/1991 1234";
        String validInput32 = "EvEnt    sales     /at  1/1/1991 1234";

        Command validOutput1 = new AddCommand(new ToDoTask("Read book"));
        Command validOutput2 = new AddCommand(new DeadlineTask("assignment", "1/1/1991 1234"));
        Command validOutput3 = new AddCommand(new EventTask("sales", "1/1/1991 1234"));

        ParserManager parserManager = new ParserManager();
        TaskList taskList = new TaskList();
        assertAll("valid",
            () -> assertEquals(validOutput1, parserManager.parseCommand(taskList, validInput11)),
            () -> assertEquals(validOutput1, parserManager.parseCommand(taskList, validInput12)),
            () -> assertEquals(validOutput2, parserManager.parseCommand(taskList, validInput21)),
            () -> assertEquals(validOutput2, parserManager.parseCommand(taskList, validInput22)),
            () -> assertEquals(validOutput3, parserManager.parseCommand(taskList, validInput31)),
            () -> assertEquals(validOutput3, parserManager.parseCommand(taskList, validInput32))
        );
    }
}
