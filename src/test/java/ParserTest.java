import static jermi.misc.Constant.FORMATTER_COMMAND_DETAILS_DELIMITER;
import static jermi.misc.Constant.FORMATTER_COMMAND_DETAILS_SPLIT_LIMIT;
import static jermi.misc.Constant.FORMATTER_COMMAND_INDEX;
import static jermi.misc.Constant.FORMATTER_DEFAULT_DETAILS;
import static jermi.misc.Constant.FORMATTER_DETAILS_INDEX;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jermi.command.AddCommand;
import jermi.command.ClearCommand;
import jermi.command.Command;
import jermi.command.DeleteCommand;
import jermi.command.DoneCommand;
import jermi.command.ExitCommand;
import jermi.command.FindCommand;
import jermi.command.HelpCommand;
import jermi.command.ListCommand;
import jermi.component.Parser;
import jermi.exception.EmptyDescriptionException;
import jermi.exception.JermiException;
import jermi.misc.TaskType;

public class ParserTest {
    private static Parser parser = new Parser();

    @Test
    public void parse_validInput_correctCommand() {
        try {
            String[] userInputs = {"todo read book",
                    "event meeting /at 28/11/2019",
                    "deadline homework /by 29 Aug",
                    "done 2",
                    "delete 3",
                    "find book meeting",
                    "list",
                    "bye",
                    "clear",
                    "help"};
            Command[] expectedCommands = {new AddCommand(TaskType.TO_DO, "read book"),
                    new AddCommand(TaskType.EVENT, "meeting /at 28/11/2019"),
                    new AddCommand(TaskType.DEADLINE, "homework /by 29 Aug"),
                    new DoneCommand("2"),
                    new DeleteCommand("3"),
                    new FindCommand("book meeting"),
                    new ListCommand(),
                    new ExitCommand(),
                    new ClearCommand(),
                    new HelpCommand()};

            assertEquals(expectedCommands[0], parser.parse(FormatterStub.readCommand(userInputs[0]),
                    FormatterStub.readDetails(userInputs[0])));
            assertEquals(expectedCommands[1], parser.parse(FormatterStub.readCommand(userInputs[1]),
                    FormatterStub.readDetails(userInputs[1])));
            assertEquals(expectedCommands[2], parser.parse(FormatterStub.readCommand(userInputs[2]),
                    FormatterStub.readDetails(userInputs[2])));
            assertEquals(expectedCommands[3], parser.parse(FormatterStub.readCommand(userInputs[3]),
                    FormatterStub.readDetails(userInputs[3])));
            assertEquals(expectedCommands[3], parser.parse(FormatterStub.readCommand(userInputs[3]),
                    FormatterStub.readDetails(userInputs[3])));
            assertEquals(expectedCommands[4], parser.parse(FormatterStub.readCommand(userInputs[4]),
                    FormatterStub.readDetails(userInputs[4])));
            assertEquals(expectedCommands[5], parser.parse(FormatterStub.readCommand(userInputs[5]),
                    FormatterStub.readDetails(userInputs[5])));
            assertEquals(expectedCommands[6], parser.parse(FormatterStub.readCommand(userInputs[6]),
                    FormatterStub.readDetails(userInputs[6])));
            assertEquals(expectedCommands[7], parser.parse(FormatterStub.readCommand(userInputs[7]),
                    FormatterStub.readDetails(userInputs[7])));
            assertEquals(expectedCommands[8], parser.parse(FormatterStub.readCommand(userInputs[8]),
                    FormatterStub.readDetails(userInputs[8])));
            assertEquals(expectedCommands[9], parser.parse(FormatterStub.readCommand(userInputs[9]),
                    FormatterStub.readDetails(userInputs[9])));

        } catch (JermiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_emptyDescription_emptyDescriptionException() {
        String[] userInputs = {"todo",
                "event",
                "deadline",
                "done",
                "delete",
                "find",
                "list",
                "bye",
                "clear",
                "help"
        };

        int expected = 6;
        int numEmptyDescriptionExceptionThrown = 0;
        for (String input : userInputs) {
            try {
                parser.parse(FormatterStub.readCommand(input), FormatterStub.readDetails(input));
            } catch (JermiException e) {
                if (e instanceof EmptyDescriptionException) {
                    numEmptyDescriptionExceptionThrown += 1;
                }
            }
        }

        assertEquals(expected, numEmptyDescriptionExceptionThrown);
    }


    /**
     * A stub class to read user input and return input command and input details.
     */
    static class FormatterStub {
        /**
         * Reads the entire user input and returns the input command.
         *
         * @param input User input.
         * @return Input command.
         */
        static String readCommand(String input) {
            return input.split(FORMATTER_COMMAND_DETAILS_DELIMITER,
                    FORMATTER_COMMAND_DETAILS_SPLIT_LIMIT)[FORMATTER_COMMAND_INDEX];
        }

        /**
         * Reads the entire user input and returns the input details.
         *
         * @param input User input.
         * @return Input details.
         */
        static String readDetails(String input) {
            try {
                return input.split(FORMATTER_COMMAND_DETAILS_DELIMITER,
                        FORMATTER_COMMAND_DETAILS_SPLIT_LIMIT)[FORMATTER_DETAILS_INDEX];
            } catch (ArrayIndexOutOfBoundsException e) {
                return FORMATTER_DEFAULT_DETAILS;
            }
        }
    }
}
