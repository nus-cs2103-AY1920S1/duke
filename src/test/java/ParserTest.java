import cs2103t.duke.parse.Parser;
import cs2103t.duke.task.TaskType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void checkFullCommandParsing_validCommand() {
        Parser ps = new Parser("todo something");
        try {
            TaskType t = ps.getInputTaskType();
            assertEquals(TaskType.T, t);
            assertEquals("something", ps.getInputEntireDescription());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void checkFullCommandParsing_invalidCommand() {
        Parser ps = new Parser("blahahs");
        try {
            ps.getInputTaskType();
            fail();
        } catch (Exception e) {
            assertEquals("blahahs", e.getMessage());
        }
    }

}
