package duke;

import duke.command.Command;
import org.junit.jupiter.api.Test;

public class ParserTest {

    public Command cmd;

    public void assertError(String parseInput, String message) {
        try {
            cmd = Parser.parse(parseInput);
            throw new Error(message);
        } catch (DukeException ignored) {}
    }

    @Test
    public void testParseBye() throws DukeException {
        String message = "Should not parse 'bye' with additional arguments.";
        cmd = Parser.parse("bye");
        cmd = Parser.parse("  bye    ");
        assertError("bye bye", message);
        assertError("bye /bye", message);
        assertError("bye /at", message);
    }

    @Test
    public void testParseList() throws DukeException {
        cmd = Parser.parse("list");
        cmd = Parser.parse("  list    ");

        String message = "Should not parse 'list' with additional arguments.";
        assertError("list 123123", message);
        assertError("list /at", message);
        assertError("list /at 5", message);
    }

    @Test
    public void testParseDone() throws DukeException {
        cmd = Parser.parse("done 1");
        cmd = Parser.parse("   done   219789723  ");

        String message = "Should not parse 'done' with non-integer positions.";
        assertError("done A", message);
        assertError("done A", message);
        assertError("done /at", message);
        assertError("done /by 2", message);
        assertError("done 3.14", message);
        assertError("done -1", message);
    }

    @Test
    public void testParseDelete() throws DukeException {
        cmd = Parser.parse("delete 1");
        cmd = Parser.parse("   delete   219789723  ");

        String message = "Should not parse 'delete' with non-integer positions.";
        assertError("delete A", message);
        assertError("delete A", message);
        assertError("delete /at", message);
        assertError("delete /by 2", message);
        assertError("delete 3.14", message);
        assertError("delete -1", message);
    }

    @Test
    public void testParseTodo() throws DukeException {
        cmd = Parser.parse("todo a b c");
        cmd = Parser.parse(" todo a /by ");
        cmd = Parser.parse(" todo a321 /at c123 ");

        String message = "Should not parse 'todo' without a description.";
        assertError("todo", message);
    }

    @Test
    public void testParseEvent() throws DukeException {
        cmd = Parser.parse("event do stuff /at tonight");
        cmd = Parser.parse("event party /at 12/12/2019 0123");

        String messageNoDescription = "Should not parse 'event' without a description";
        assertError("event", messageNoDescription);
        assertError("event /at 12/12/2019 0123", messageNoDescription);

        String messageNoAt = "Should not parse 'event' without an 'at' keyword";
        assertError("event foo", messageNoAt);
        assertError("event bar/at ", messageNoAt);

        String messageNoAtTime = "Should not parse 'event' without an 'at' time";
        assertError("event foo bar /at ", messageNoAtTime);
    }

    @Test
    public void testParseDeadline() throws DukeException {
        cmd = Parser.parse("deadline a /by b");
        cmd = Parser.parse("deadline return book /by 2/12/2019 1800");

        String messageNoDescription = "Should not parse 'deadline' without a description";
        assertError("deadline", messageNoDescription);
        assertError("deadline /by 12/12/2019 0123", messageNoDescription);

        String messageNoAt = "Should not parse 'deadline' without a 'by' keyword";
        assertError("deadline foo", messageNoAt);
        assertError("deadline bar/by ", messageNoAt);

        String messageNoAtTime = "Should not parse 'deadline' without a 'by' time";
        assertError("deadline foo bar /by ", messageNoAtTime);
    }

    @Test
    public void testShouldNotParseGibberish() {
        String message = "Should not parse gibberish.";
        assertError("", message);
        assertError("   ", message);
        assertError("  \n \n\t", message);
        assertError("null", message);
        assertError("lajsdlaskj dl2j1 oalsd", message);
        assertError("<>!)@#/>", message);
        assertError("/", message);
        assertError("/////", message);
        assertError("\\", message);
        assertError("\\\\\\\\", message);
    }
}
