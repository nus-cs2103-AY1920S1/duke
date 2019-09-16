package duke.parser;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TaskToFileParserTest {
    @Test
    public void parse_success() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
            assertEquals(TaskToFileParser.parse(new Todo("eat food")), "todo,false,null,eat food\n");
            assertEquals(TaskToFileParser.parse(new Todo("eat food", true)),
                         "todo,true,null,eat food\n");
            assertEquals(TaskToFileParser.parse(new Deadline("meeting", format.parse("10/10/2003 1100"),
                                                       false)), "deadline,false,null,meeting,10/10/2003 1100\n");
            assertEquals(TaskToFileParser.parse(new Deadline("meeting", format.parse("10/10/2003 1100"),
                                                       true)),
                         "deadline,true,null,meeting,10/10/2003 1100\n");
            assertEquals(TaskToFileParser.parse(new Event("dinner", format.parse("23/04/2008 1023"),
                                                    true)), "event,true,null,dinner,23/04/2008 1023\n");
            assertEquals(TaskToFileParser.parse(new Event("dinner", format.parse("23/04/2008 1023"),
                                                    false)),
                         "event,false,null,dinner,23/04/2008 1023\n");
        } catch (ParseException pe) {
            fail();
        }
    }

}
