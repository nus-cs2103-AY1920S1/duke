import duke.Parser;
import duke.TaskList;
import duke.Storage;
import duke.exception.DukeException;
import duke.exception.MissingParameterException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parser_exceptionThrown() throws DukeException {
        Parser p = new Parser(new TaskList(new Storage()));

        //test case 1
        assertThrows(MissingParameterException.class, () -> {
            p.parse("todo").executeCommand();
        });

        //test case 2
        assertThrows(DukeException.class, () -> {
            p.parse("event").executeCommand();
        });

        //test case 3
        assertThrows(DukeException.class, () -> {
            p.parse("deadline").executeCommand();
        });
    }
}
