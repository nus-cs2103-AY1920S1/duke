package duke.parser;

import duke.commands.Command;
import duke.data.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private static final String[] DESCRIPTIONS = {"me", "you", "her", "i", "we"};
    private static final String[] VALID_DATES = {"01/01/2019", "02/02/2019", "03/03/2019", "04/04/2019",
            "05/05/2019", "06/06/2019", "07/07/2019", "08/08/2019", "09/09/2019", "10/10/2019",
            "11/11/2019", "12/12/2019"};
    private static final String[] VALID_TIMES = {"06:00:00", "12:00:00", "18:00:00", "23:59:59"};

    private class TaskListStub extends TaskList {
        @Override
        public int size() {
            return 1;
        }
    }

    /**
     * Tests the deadline command using invalid inputs.
     */
    @Test
    public void deadlineCommand_invalidInput_incorrectCommandResult() {

    }

    /**
     * Tests the deadline command using valid inputs.
     */
    @Test
    public void deadlineCommand_validInput_relevantCommandResult() {
        String successMessage = "Got it. I've added this task:\n"
                + "%s\nNow you have %d task(s) in the list.\n";
        for (int i = 0; i < DESCRIPTIONS.length; i++) {
            for (int j = 0; j < VALID_DATES.length; j++) {
                for (int k = 0; k < VALID_TIMES.length; k++) {
                    String description = DESCRIPTIONS[i];
                    String date = VALID_DATES[j];
                    String time = VALID_TIMES[k];
                    String commandString = String.format("deadline %s /by %s %s", description,
                            date, time);
                    Command command = Parser.parse(commandString);
                    command.setTaskListToExecuteOn(new TaskListStub());
                    String expectedCommandResult = String.format("[D][\u2718] %s (by: %s %s)",
                            description, date, time);
                    String expected = String.format(successMessage, expectedCommandResult, 1);
                    assertEquals(expected, command.execute().getMessage());
                }
            }
        }
    }

    /**
     * Tests the todo command using invalid inputs.
     */
    @Test
    public void todoCommand_invalidInput_incorrectCommandResult() {
        String[] invalidTodoInputs = {"todo"};
        String expected = "todo command format: todo <description>\n";
        for (int i = 0; i < invalidTodoInputs.length; i++) {
            String actual = Parser.parse(invalidTodoInputs[i]).execute().getMessage();
            assertEquals(expected, actual);
        }
    }

    /**
     * Tests the todo command using valid inputs.
     */
    @Test
    public void todoCommand_validInput_relevantCommandResult() {
        String successMessage = "Got it. I've added this task:\n"
                + "%s\nNow you have %d task(s) in the list.\n";
        for (int i = 0; i < DESCRIPTIONS.length; i++) {
            Command command = Parser.parse("todo " + DESCRIPTIONS[i]);
            command.setTaskListToExecuteOn(new TaskListStub());
            String expectedDescription = "[T][\u2718] " + DESCRIPTIONS[i];
            String expected = String.format(successMessage, expectedDescription, 1);
            assertEquals(expected, command.execute().getMessage());
        }
    }

}
