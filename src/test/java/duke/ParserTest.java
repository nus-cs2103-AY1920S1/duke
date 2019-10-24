package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import exception.DukeException;

class ParserTest {
    @Test
    void command_invalid_throwDukeException() {
        try {
            Parser.parse("trolling");
            fail();
        } catch (DukeException e) {
            assertEquals("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(",
                    e.getMessage());
        } catch (ParseException e) {
            System.out.println("not testing");
        }
    }
}
