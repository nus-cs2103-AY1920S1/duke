import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void invalidCommand() {
        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse("not a valid command");
        });
    }

    @Test
    void byeInput() {
        String input = "bye";
        Class expected = ExitCommand.class;
        Command actual = Parser.parse(input);

        Assertions.assertSame(actual.getClass(), expected);
    }

    @Test
    void listInput() {
        String input = "list";
        Class expected = ListCommand.class;
        Command actual = Parser.parse(input);

        Assertions.assertSame(actual.getClass(), expected);
    }

    @Test
    void doneInputValid() {
        String input = "done 1";
        Class expected = DoneCommand.class;
        Command actual = Parser.parse(input);

        Assertions.assertSame(actual.getClass(), expected);
    }

    @Test
    void doneInputNonNumeric() {
        String input = "done clearlyNotANumber";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void doneInputMissingArg() {
        String input = "done";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void deleteInputValid() {
        String input = "delete 1";
        Class expected = DeleteCommand.class;
        Command actual = Parser.parse(input);

        Assertions.assertSame(actual.getClass(), expected);
    }

    @Test
    void deleteInputNonNumeric() {
        String input = "delete clearlyNotANumber";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void deleteInputMissingArg() {
        String input = "delete";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void todoInputValid() {
        String input = "todo a task that needs doing";
        Class expected = AddTodoTaskCommand.class;
        Command actual = Parser.parse(input);

        Assertions.assertSame(actual.getClass(), expected);
    }

    @Test
    void todoInputMissingArg() {
        String input = "todo";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void deadlineInputValid() {
        String input = "deadline return book /by 2/12/2019 1800";
        Class expected = AddDeadlineTaskCommand.class;
        Command actual = Parser.parse(input);

        Assertions.assertSame(actual.getClass(), expected);
    }

    @Test
    void deadlineInputMissingArg() {
        String input = "deadline";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void deadlineInputMissingDescription() {
        String input = "deadline /by 2/12/2019 1800";

        Assertions.assertThrows(EmptyTaskDescriptionException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void deadlineInputMissingDeadline() {
        String input = "deadline return book";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void deadlineInputDeadlineNonDateArg() {
        String input = "deadline return book /by not a date";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void deadlineInputDeadlineUnexpectedDateFormat() {
        String input = "deadline return book /by 12/31/2019 0000";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void eventInputValid() {
        String input = "event return book /at 6/8/2019 1400";
        Class expected = AddEventTaskCommand.class;
        Command actual = Parser.parse(input);

        Assertions.assertSame(actual.getClass(), expected);
    }

    @Test
    void eventInputMissingArg() {
        String input = "event";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void eventInputMissingDescription() {
        String input = "event /at 6/8/2019 1400";

        Assertions.assertThrows(EmptyTaskDescriptionException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void eventInputMissingDeadline() {
        String input = "event project meeting";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void eventInputDeadlineNonDateArg() {
        String input = "event project meeting /at not a date";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }

    @Test
    void eventInputDeadlineUnexpectedDateFormat() {
        String input = "event project meeting /at 12/31/2019 1400";

        Assertions.assertThrows(UnknownCommandException.class, () -> {
            Parser.parse(input);
        });
    }
}