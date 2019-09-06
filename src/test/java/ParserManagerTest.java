import duke.command.AddCommand;
import duke.command.Command;
import duke.parser.ParserManager;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.ToDoTask;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserManagerTest {

    @Test
    public void testValidParsing() {
        String validInput1_1 = "todo Read book";
        String validInput1_2 = "TODO    Read book    ";
        String validInput2_1 = "deadline assignment /by 1/1/1991 1234";
        String validInput2_2 = "DEadLinE   assignment   /by    1/1/1991 1234";
        String validInput3_1 = "event sales /at 1/1/1991 1234";
        String validInput3_2 = "EvEnt    sales     /at  1/1/1991 1234";

        Command validOutput1 = new AddCommand(new ToDoTask("Read book"));
        Command validOutput2 = new AddCommand(new DeadlineTask("assignment", "1/1/1991 1234"));
        Command validOutput3 = new AddCommand(new EventTask("sales", "1/1/1991 1234"));

        ParserManager parserManager = new ParserManager();
        TaskList taskList = new TaskList();
        assertAll("valid",
                () -> assertEquals(validOutput1, parserManager.parseCommand(taskList, validInput1_1)),
                () -> assertEquals(validOutput1, parserManager.parseCommand(taskList, validInput1_2)),
                () -> assertEquals(validOutput2, parserManager.parseCommand(taskList, validInput2_1)),
                () -> assertEquals(validOutput2, parserManager.parseCommand(taskList, validInput2_2)),
                () -> assertEquals(validOutput3, parserManager.parseCommand(taskList, validInput3_1)),
                () -> assertEquals(validOutput3, parserManager.parseCommand(taskList, validInput3_2))
        );
    }
}
