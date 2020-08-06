import static jermi.misc.Constant.FORMATTER_BORDER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jermi.component.Formatter;

public class FormatterTest {
    private static final Formatter FORMATTER = new Formatter();

    @Test
    public void greet_welcomeMessage() {
        String expected = FORMATTER_BORDER
                + "  Hello! I'm Jermi\n"
                + "  What can I do for you?\n"
                + "  \n"
                + "  Type 'help' to view the list of commands.\n"
                + FORMATTER_BORDER;

        assertEquals(expected,
                FORMATTER.greet());
    }

    @Test
    public void exit_exitMessage() {
        String expected = FORMATTER_BORDER
                + "  Bye. Hope to see you again soon!\n"
                + FORMATTER_BORDER;

        assertEquals(expected,
                FORMATTER.exit());
    }

    @Test
    public void echo_noString_onlyBorders() {
        String expected = FORMATTER_BORDER + FORMATTER_BORDER;

        assertEquals(expected, FORMATTER.echo());
    }

    @Test
    public void echo_singleEmptyString_singleBlankLineBetweenBorders() {
        String expected = FORMATTER_BORDER + "  \n" + FORMATTER_BORDER;

        assertEquals(expected, FORMATTER.echo(""));
    }

    @Test
    public void echo_multipleEmptyStrings_multipleBlankLinesBetweenBorders() {
        String expected = FORMATTER_BORDER
                + "  \n"
                + "  \n"
                + "  \n"
                + "  \n"
                + "  \n"
                + FORMATTER_BORDER;

        assertEquals(expected, FORMATTER.echo("", "", "", "", ""));
    }

    @Test
    public void echo_singleNonEmptyString_singleNonEmptyStringBetweenBorders() {
        String expected = FORMATTER_BORDER + "  Test string\n" + FORMATTER_BORDER;

        assertEquals(expected, FORMATTER.echo("Test string"));
    }

    @Test
    public void echo_multipleNonEmptyStrings_multipleNonEmptyStringsBetweenBorders() {
        String[] testStrings = {"Test string 1",
                "Test string 2",
                "Test string 3",
                "Test string 4"};
        String expected = FORMATTER_BORDER
                + "  " + testStrings[0] + "\n"
                + "  " + testStrings[1] + "\n"
                + "  " + testStrings[2] + "\n"
                + "  " + testStrings[3] + "\n"
                + FORMATTER_BORDER;

        assertEquals(expected, FORMATTER.echo(testStrings[0],
                testStrings[1],
                testStrings[2],
                testStrings[3]));
        assertEquals(expected, FORMATTER.echo(testStrings));
    }

    @Test
    public void readCommand_emptyInput_emptyCommand() {
        String expected = "";

        assertEquals(expected, FORMATTER.readCommand(""));
    }

    @Test
    public void readCommand_inputCommand_inputCommand() {
        String[] inputCommands = {"list",
                "todo",
                "event",
                "deadline",
                "delete",
                "find",
                "bye",
                "done",
                "clear"};

        assertEquals(inputCommands[0], FORMATTER.readCommand(inputCommands[0]));
        assertEquals(inputCommands[1], FORMATTER.readCommand(inputCommands[1]));
        assertEquals(inputCommands[2], FORMATTER.readCommand(inputCommands[2]));
        assertEquals(inputCommands[3], FORMATTER.readCommand(inputCommands[3]));
        assertEquals(inputCommands[4], FORMATTER.readCommand(inputCommands[4]));
        assertEquals(inputCommands[5], FORMATTER.readCommand(inputCommands[5]));
        assertEquals(inputCommands[6], FORMATTER.readCommand(inputCommands[6]));
        assertEquals(inputCommands[7], FORMATTER.readCommand(inputCommands[7]));
        assertEquals(inputCommands[8], FORMATTER.readCommand(inputCommands[8]));
    }

    @Test
    public void readCommand_userInput_inputCommand() {
        String[] userInputs = {"todo read book",
                "event meeting /at 28/11/2019",
                "deadline homework /by 29 Aug",
                "done 2",
                "delete 3",
                "find book meeting"};
        String[] expectedOutputs = {"todo",
                "event",
                "deadline",
                "done",
                "delete",
                "find"};

        assertEquals(expectedOutputs[0], FORMATTER.readCommand(userInputs[0]));
        assertEquals(expectedOutputs[1], FORMATTER.readCommand(userInputs[1]));
        assertEquals(expectedOutputs[2], FORMATTER.readCommand(userInputs[2]));
        assertEquals(expectedOutputs[3], FORMATTER.readCommand(userInputs[3]));
        assertEquals(expectedOutputs[4], FORMATTER.readCommand(userInputs[4]));
        assertEquals(expectedOutputs[5], FORMATTER.readCommand(userInputs[5]));
    }

    @Test
    public void readDetails_emptyInput_emptyDetails() {
        String expected = "";

        assertEquals(expected, FORMATTER.readDetails(""));
    }

    @Test
    public void readDetails_userInputWithoutDetails_emptyDetails() {
        String[] userInputsWithoutDetails = {"list",
                "todo",
                "event",
                "deadline",
                "delete",
                "find",
                "bye",
                "done",
                "clear"};
        String[] expectedOutputs = {"",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""};

        assertEquals(expectedOutputs[0], FORMATTER.readDetails(userInputsWithoutDetails[0]));
        assertEquals(expectedOutputs[1], FORMATTER.readDetails(userInputsWithoutDetails[1]));
        assertEquals(expectedOutputs[2], FORMATTER.readDetails(userInputsWithoutDetails[2]));
        assertEquals(expectedOutputs[3], FORMATTER.readDetails(userInputsWithoutDetails[3]));
        assertEquals(expectedOutputs[4], FORMATTER.readDetails(userInputsWithoutDetails[4]));
        assertEquals(expectedOutputs[5], FORMATTER.readDetails(userInputsWithoutDetails[5]));
        assertEquals(expectedOutputs[6], FORMATTER.readDetails(userInputsWithoutDetails[6]));
        assertEquals(expectedOutputs[7], FORMATTER.readDetails(userInputsWithoutDetails[7]));
        assertEquals(expectedOutputs[8], FORMATTER.readDetails(userInputsWithoutDetails[8]));
    }

    @Test
    public void readDetails_userInput_details() {
        String[] userInputs = {"todo read book",
                "event meeting /at 28/11/2019",
                "deadline homework /by 29 Aug",
                "done 2",
                "delete 3",
                "find book meeting"};
        String[] expectedOutputs = {"read book",
                "meeting /at 28/11/2019",
                "homework /by 29 Aug",
                "2",
                "3",
                "book meeting"};

        assertEquals(expectedOutputs[0], FORMATTER.readDetails(userInputs[0]));
        assertEquals(expectedOutputs[1], FORMATTER.readDetails(userInputs[1]));
        assertEquals(expectedOutputs[2], FORMATTER.readDetails(userInputs[2]));
        assertEquals(expectedOutputs[3], FORMATTER.readDetails(userInputs[3]));
        assertEquals(expectedOutputs[4], FORMATTER.readDetails(userInputs[4]));
        assertEquals(expectedOutputs[5], FORMATTER.readDetails(userInputs[5]));
    }
}
