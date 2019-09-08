package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.exception.DukeMissingDescriptionException;
import duke.exception.DukeUnknownInputException;
import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static duke.task.TaskType.DEADLINE;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    /**
     * Test general incorrect command parsing.
     */
    @Test
    void parseCommandIncorrectInputExceptionThrown() {
        String[] tests = {
                "", "      ", "blah", "/by", "fsdaf", "exit"
        };

        for (String command: tests) {
            assertThrows(DukeMissingDescriptionException.class, () -> Parser.parse(command));
        }
    }

    /**
     * Tests for parsing commands related to Deadline.
     */
    @Test
    void parseDeadlineCorrectInputCorrectOutput() {
        String[] fullCommandArr = {
                "deadline live /by 3/12/2019 1800",
                "deadline die /by 4/12/2019 1800",
        };
        String[][] outArr = {
                {"live", "/by", "3/12/2019", "1800"},  // live each day like it's your last
                {"die", "/by", "4/12/2019", "1800"}
        };

        for (int i = 0; i < fullCommandArr.length; i++) {
            assertArrayEquals(
                    ((AddCommand) Parser.parse(fullCommandArr[i])).getArgs(),
                    outArr[i]
            );
        }
    }

    @Test
    void parseDeadlineInsufficientArgsExceptionThrown() {
        String[] fullCommandArr = {
                "deadline ",
                "deadline live /by"
        };

        for (String command: fullCommandArr) {
            assertThrows(DukeUnknownInputException.class, () -> Parser.parse(command));
        }
    }

    @Test
    void parseDeadlineWrongDateFormatExceptionThrown() {
        String[] fullCommandArr = {
                "deadline live /by 3/12/2019",
                "deadline live /by 3/12 1800",
                "deadline live /by 3/12 9999"
        };

        for (String command: fullCommandArr) {
            assertThrows(DukeUnknownInputException.class, () -> Parser.parse(command));
        }
    }

    /**
     * Tests for commands related to Event.
     */
    @Test
    void parseEventCorrectInputCorrectOutput() {
        String[] in = {
                "event live and die /at 2pm - 4pm",
                "event revive /at 4.05pm"  // gamers never die
        };
        String[][] out = {
                {"live", "and", "die", "/at", "2pm", "-", "4pm"},
                {"revive", "/at", "4.05pm"}
        };

        for (int i = 0; i < in.length; i++) {
            assertArrayEquals(
                    out[i],
                    ((AddCommand) Parser.parse(in[i])).getArgs()
            );
        }
    }

    @Test
    void parseEventInsufficientArgsExceptionThrown() {
        String[] in = {
                "event live and die",
                "event live and die /at"
        };

        for (String command: in) {
            assertThrows(DukeUnknownInputException.class, () -> Parser.parse(command));
        }
    }

    /**
     * Tests for commands related to Todo.
     */
    @Test
    void parseTodoCorrectInputCorrectOutput() {
        String[] in = {
                "todo live",
        };
        String[][] out = {
                {"live"},
        };

        for (int i = 0; i < in.length; i++) {
            assertArrayEquals(
                out[i],
                ((AddCommand) Parser.parse(in[i])).getArgs()
            );
        }
    }
}