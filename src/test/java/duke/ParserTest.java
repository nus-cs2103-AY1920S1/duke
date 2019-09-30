package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import duke.command.Command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {
    private Parser parser;

    @BeforeEach
    void init() {
        parser = new Parser();
        parser.register("aaa", words -> List.of(words[0]));
        parser.register("bbb", words -> List.of(words[1]));
    }

    @Test
    void parse_validCommandA_success() throws DukeException, IOException {
        Command command = parser.parse(new String[]{"aaa"});
        assertEquals("aaa", command.run(new String[]{"aaa", "input"}).get(0));
    }

    @Test
    void parse_validCommandB_success() throws DukeException, IOException {
        Command command = parser.parse(new String[]{"bbb"});
        assertEquals("input", command.run(new String[]{"bbb", "input"}).get(0));
    }

    @Test
    void parse_invalidCommand_exceptionThrown() {
        try {
            parser.parse(new String[]{"ccc"});
            fail();
        } catch (DukeException e) {
            String message = "I'm sorry, but I don't know what that means :-(";
            assertEquals(new DukeException(message).getMessage(), e.getMessage());
        }
    }
}
