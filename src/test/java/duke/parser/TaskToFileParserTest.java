package duke.parser;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TaskToFileParserTest {
    @Test
    public void parse_success() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            Task todo1  = new Todo("eat food");
            Task todo2  = new Todo("eat food");
            todo2.markAsDone();

            Task deadline1 = new Deadline("meeting", format.parse("10/10/2003 1100"));
            Task deadline2 = new Deadline("meeting", format.parse("10/10/2003 1100"));
            deadline2.markAsDone();

            Task event1 = new Event("dinner", format.parse("23/04/2008 1023"));
            Task event2 = new Event("dinner", format.parse("23/04/2008 1023"));
            event2.markAsDone();

            assertEquals(TaskToFileParser.parse(todo1), "todo,false,null,eat food,null\n");
            assertEquals(TaskToFileParser.parse(todo2), "todo,true,null,eat food,null\n");
            assertEquals(TaskToFileParser.parse(deadline1), "deadline,false,null,meeting,10/10/2003 1100\n");
            assertEquals(TaskToFileParser.parse(deadline2), "deadline,true,null,meeting,10/10/2003 1100\n");
            assertEquals(TaskToFileParser.parse(event2), "event,true,null,dinner,23/04/2008 1023\n");
            assertEquals(TaskToFileParser.parse(event1), "event,false,null,dinner,23/04/2008 1023\n");
        } catch (ParseException pe) {
            fail();
        }
    }

}
