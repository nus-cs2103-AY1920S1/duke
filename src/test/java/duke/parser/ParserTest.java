package duke.parser;

import duke.commands.Command;
import duke.data.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private class TaskListStub extends TaskList {
        @Override
        public int size() {
            return 1;
        }
    }

    @Test
    public void todoCommand_validInput_correctCommandResult() {
        String[] todoDescriptions = {"me", "you", "her", "i", "we"};
        String successMessage = "Got it. I've added this task:\n"
                + "%s\nNow you have %d task(s) in the list.\n";
        TaskListStub taskListStub = new TaskListStub();
        for (int i = 0; i < todoDescriptions.length; i++) {
            Command command = Parser.parse("todo " + todoDescriptions[i]);
            command.setTaskListToExecuteOn(taskListStub);
            String expectedDescription = "[T][\u2718] " + todoDescriptions[i];
            String expected = String.format(successMessage, expectedDescription, 1);
            assertEquals(expected, command.execute().getMessage());
        }
    }

    @Test
    public void todoCommand_invalidInput_incorrectCommandResult() {
        String[] invalidTodoInputs = {"todo"};
        String expected = "todo command format: todo <description>\n";
        for (int i = 0; i < invalidTodoInputs.length; i++) {
            String actual = Parser.parse(invalidTodoInputs[i]).execute().getMessage();
            assertEquals(expected, actual);
        }
    }

}
