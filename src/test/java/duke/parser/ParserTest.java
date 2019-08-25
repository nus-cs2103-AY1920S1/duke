package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

  @Test
  void parse_normalInputs_success() throws DukeException {
    assertEquals(new ListCommand(), Parser.parse("list"));
    assertEquals(new ByeCommand(), Parser.parse("bye"));
    assertEquals(new DoneCommand(1), Parser.parse("done 1"));
    assertEquals(new DeleteCommand(1), Parser.parse("delete 1"));
    assertEquals(new AddCommand("todo", "read book"), Parser.parse("todo read book"));
    assertEquals(new AddCommand("deadline", "finish reading", "23/11/2019 1800"),
            Parser.parse("deadline finish reading /by 23/11/2019 1800"));
    assertEquals(new AddCommand("event", "carnival", "12/9/2011 0012"),
            Parser.parse("event carnival /at 12/9/2011 0012"));
  }
}