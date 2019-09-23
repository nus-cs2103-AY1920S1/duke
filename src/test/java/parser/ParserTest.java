package parser;

import org.junit.jupiter.api.Test;
import tasklist.TaskList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private Parser parser = new Parser();

    @Test
    public void parseTask_correctlyParseTaskCommand_success() {
        TaskList testlist = new TaskList();
        parser.parseTasks("todo just a test",testlist);
        assertEquals("todo",parser.getCommand());
        assertEquals("just a test",parser.getDescription());
    }

    @Test
    public void splitNotesCommand_correctlyParseNotesCommand_success() {
        TaskList testlist = new TaskList();
        parser.splitNotesCommand("category | description");
        assertEquals("category",parser.getCategory());
        assertEquals("description",parser.getNoteDescription());
    }
}
