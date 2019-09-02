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

        Optional<Command> validOutput1
                = Optional.of(new AddCommand(new ToDoTask("Read book")));
        Optional<Command> validOutput2
                = Optional.of(new AddCommand(new DeadlineTask("assignment", "1/1/1991 1234")));
        Optional<Command> validOutput3
                = Optional.of(new AddCommand(new EventTask("sales", "1/1/1991 1234")));

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

    @Test
    public void testInvalidParsing() {
        String invalidInput1 = "todo";
        String invalidInput2 = "deadline assignment";
        String invalidInput3 = "deadline assignment /by ";
        String invalidInput4 = "deadline assignment /at 1/1/1991 1234";
        String invalidInput5 = "deadline assignment /by abcd";
        String invalidInput6 = "event assignment";
        String invalidInput7 = "deadline assignment /at ";
        String invalidInput8 = "deadline assignment /by 1/1/1991 1234";
        String invalidInput9 = "deadline assignment /at abcd";

        ParserManager parserManager = new ParserManager();
        TaskList taskList = new TaskList();
        assertAll("valid",
                () -> parserManager.parseCommand(taskList, invalidInput1).isEmpty(),
                () -> parserManager.parseCommand(taskList, invalidInput2).isEmpty(),
                () -> parserManager.parseCommand(taskList, invalidInput3).isEmpty(),
                () -> parserManager.parseCommand(taskList, invalidInput4).isEmpty(),
                () -> parserManager.parseCommand(taskList, invalidInput5).isEmpty(),
                () -> parserManager.parseCommand(taskList, invalidInput6).isEmpty(),
                () -> parserManager.parseCommand(taskList, invalidInput7).isEmpty(),
                () -> parserManager.parseCommand(taskList, invalidInput8).isEmpty(),
                () -> parserManager.parseCommand(taskList, invalidInput9).isEmpty());
    }
}
