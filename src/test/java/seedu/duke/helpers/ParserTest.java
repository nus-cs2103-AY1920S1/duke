package seedu.duke.helpers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.TodoCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"list", "add", "done", "todo", "event", "deadline", "delete", "bye", "add something",
                            "done 1, todo something else"})

    void identifyCommandName_ShouldReturnCommandName(String input) {
        assertEquals(Parser.CommandName.valueOf(input.split(" ")[0].toUpperCase()), Parser.identifyCommandName(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"remove", "create", "something", "else", "not", "command"})

    void identifyCommandName_ShouldReturnCommandNameError(String input) {
        assertEquals(Parser.CommandName.ERROR, Parser.identifyCommandName(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"list", "add something", "todo something", "event sometthing123* /at 21/12/2019 2345",
        "deadline something456% /by 23/12/2019 1234", "done 3", "delete 5", "bye"})

    void parseCommand_ShouldReturnCommand(String input) {
        assertEquals(Command.class, Parser.parseCommand(input).getClass().getSuperclass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"list"})

    void parseCommand_ShouldReturnListCommand(String input) {
        assertEquals(ListCommand.class, Parser.parseCommand(input).getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"add something", "add something123", "add something else 123"})

    void parseCommand_ShouldReturnAddCommand(String input) {
        assertEquals(AddCommand.class, Parser.parseCommand(input).getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"todo something", "todo something123", "todo something else 123"})

    void parseCommand_ShouldReturnTodoCommand(String input) {
        assertEquals(TodoCommand.class, Parser.parseCommand(input).getClass());
    }
}
