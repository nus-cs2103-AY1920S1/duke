/*
package parser;

import org.junit.jupiter.api.Test;
import tasklist.TaskList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private Parser parser = new Parser();

    @Test
    public void parse_correctlyParseCommand_sucess() {
        TaskList testlist = new TaskList();
        parser.parse("todo just a test",testlist,true);
        assertEquals("todo",parser.command);
        assertEquals("just a test",parser.description);
    }

    @Test
    public void parse_correctlyPrintAddStatement() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TaskList testlist = new TaskList();
        String correctOutput = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [D][✗] this is a test input(by:02 February 0800 PM)\n"
                + "     Now you have 1 tasks in the list.\n"
                + "    ____________________________________________________________\r\n";
        parser.parse("deadline this is a test input /by 2/2/2019 2000",testlist,false);
        assertEquals(correctOutput.toString(), outContent.toString());
    }
}
*/
