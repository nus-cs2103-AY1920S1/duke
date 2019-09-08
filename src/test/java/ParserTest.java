import duke.Parser;
import duke.command.EmptyTaskDescriptionException;
import duke.command.UnknownCommandException;
import duke.command.AddDeadlineTaskCommand;
import duke.command.AddEventTaskCommand;
import duke.command.AddTodoTaskCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void execute_invalidCommandText_exceptionThrown() {
        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse("not a valid command");
        });
    }

    @Test
    void execute_byeCommandText_byeCommandInstance() {
        String input = "bye";
        Class expected = ExitCommand.class;
        Command actual = Parser.parse(input);

        Assertions.assertSame(actual.getClass(), expected);
    }

    @Test
    void execute_listCommandText_listCommandInstance() {
        String input = "list";
        Class expected = ListCommand.class;
        Command actual = Parser.parse(input);

        Assertions.assertSame(actual.getClass(), expected);
    }

    @Test
    void execute_doneCommandTextNumericTaskNumber_doneCommandInstance() {
        String input = "done 1";
        Class expected = DoneCommand.class;
        Command actual = Parser.parse(input);

        Assertions.assertSame(actual.getClass(), expected);
    }

    @Test
    void execute_doneCommandTextNumericNonTaskNumber_exceptionThrown() {
        String input = "done clearlyNotANumber";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void execute_doneCommandTextMissingTaskNumber_exceptionThrown() {
        String input = "done";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void execute_deleteCommandTextNumericTaskNumber_deleteCommandInstance() {
        String input = "delete 1";
        Class expected = DeleteCommand.class;
        Command actual = Parser.parse(input);

        Assertions.assertSame(actual.getClass(), expected);
    }

    @Test
    void execute_deleteCommandTextNonNumericTaskNumber_exceptionThrown() {
        String input = "delete clearlyNotANumber";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void execute_deleteCommandTextMissingTaskNumber_exceptionThrown() {
        String input = "delete";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void execute_todoCommandTextValidDescription_addTodoCommandInstance() {
        String input = "todo a task that needs doing";
        Class expected = AddTodoTaskCommand.class;
        Command actual = Parser.parse(input);

        Assertions.assertSame(actual.getClass(), expected);
    }

    @Test
    void execute_todoCommandTextMissingDescription_exceptionThrown() {
        String input = "todo";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void execute_deadlineCommandTextValid_addDeadlineTaskInstance() {
        String input = "deadline return book /by 2/12/2019 1800";
        Class expected = AddDeadlineTaskCommand.class;
        Command actual = Parser.parse(input);

        Assertions.assertSame(actual.getClass(), expected);
    }

    @Test
    void execute_deadlineCommandTextMissingEverything_exceptionThrown() {
        String input = "deadline";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void execute_deadlineCommandTextMissingDescription_exceptionThrown() {
        String input = "deadline /by 2/12/2019 1800";

        Assertions.assertThrows(EmptyTaskDescriptionException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void execute_deadlineCommandTextMissingDeadline_exceptionThrown() {
        String input = "deadline return book";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void execute_deadlineCommandTextNonDateDeadline_exceptionThrown() {
        String input = "deadline return book /by not a date";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void execute_deadlineCommandTextIncorrectDateTimeFormatDeadline_exceptionThrown() {
        String input = "deadline return book /by 12/31/2019 0000";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void execute_eventCommandTextValid_addEventCommandInstance() {
        String input = "event return book /at 6/8/2019 1400";
        Class expected = AddEventTaskCommand.class;
        Command actual = Parser.parse(input);

        Assertions.assertSame(actual.getClass(), expected);
    }

    @Test
    void execute_eventCommandTextMissingEverything_exceptionThrown() {
        String input = "event";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void execute_eventCommandTextMissingDescription_exceptionThrown() {
        String input = "event /at 6/8/2019 1400";

        Assertions.assertThrows(EmptyTaskDescriptionException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void execute_eventCommandTextMissingDeadline_exceptionThrown() {
        String input = "event project meeting";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void execute_eventCommandTextNonDateDeadline_exceptionThrown() {
        String input = "event project meeting /at not a date";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void execute_eventCommandTextIncorrectDateTimeFormat_exceptionThrown() {
        String input = "event project meeting /at 12/31/2019 1400";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }
}