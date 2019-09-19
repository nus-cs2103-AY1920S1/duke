package duke.parser;

import duke.exception.FailedToLoadIoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FileToTaskParserTest {

    @Test
    public void parse_success() {
        try {
            //todos
            final Task todo1 = new Todo("eat food");
            final Task todo2 = new Todo("swim");
            final Task todo3 = new Todo("swim");
            todo3.markAsDone();
            //deadline
            final Task deadline1 = new Deadline("hw",
                                          new SimpleDateFormat("dd/MM/yyyy HHmm").parse("15/03/2019 1200"));
            final Task deadline2 = new Deadline("hw",
                                          new SimpleDateFormat("dd/MM/yyyy HHmm").parse("01/01/1995 2356"));
            final Task deadline3 = new Deadline("assignment",
                                          new SimpleDateFormat("dd/MM/yyyy HHmm").parse("01/01/1995 2356"));
            deadline3.markAsDone();
            //event
            final Task event1 = new Event("meeting",
                                    new SimpleDateFormat("dd/MM/yyyy HHmm").parse("23/04/2031 1853"));
            event1.markAsDone();
            final Task event2 = new Event("meeting",
                                    new SimpleDateFormat("dd/MM/yyyy HHmm").parse("23/04/2031 1853"));
            assertEquals(todo1.toString(), FileToTaskParser.parse("todo,false,null,eat food,null").toString());
            assertEquals(todo2.toString(), FileToTaskParser.parse("todo,false,null,swim,null").toString());
            assertEquals(todo3.toString(), FileToTaskParser.parse("todo,true,null,swim,null").toString());
            assertEquals(deadline1.toString(),
                         FileToTaskParser.parse("deadline,false,null,hw,15/03/2019 1200").toString());
            assertEquals(deadline2.toString(),
                         FileToTaskParser.parse("deadline,false,null,hw,01/01/1995 2356").toString());
            assertEquals(deadline3.toString(),
                         FileToTaskParser.parse("deadline,true,null,assignment,01/01/1995 2356").toString());
            assertEquals(event1.toString(),
                         FileToTaskParser.parse("event,true,null,meeting,23/04/2031 1853").toString());
            assertEquals(event2.toString(),
                         FileToTaskParser.parse("event,false,null,meeting,23/04/2031 1853").toString());
        } catch (Exception pe) {
            fail();
        }
    }

    @Test
    public void parse_with_ExceptionThrown() {
        Assertions.assertThrows(FailedToLoadIoException.class, () -> FileToTaskParser
                                .parse("event,true,null,meeting,1834233"));
        Assertions.assertThrows(FailedToLoadIoException.class, () -> FileToTaskParser
                .parse("event,false,null,meeting,1834233"));
        Assertions.assertThrows(FailedToLoadIoException.class, () -> FileToTaskParser
                .parse("null,true,null,meeting,1834233"));
        Assertions.assertThrows(FailedToLoadIoException.class, () -> FileToTaskParser
                .parse("null,null,null,null,null"));
        Assertions.assertThrows(FailedToLoadIoException.class, () -> FileToTaskParser
                .parse("blahblah,true,null,meeting,null"));
        Assertions.assertThrows(NullPointerException.class, () -> FileToTaskParser.parse(null));
    }

    @Test
    public void parse_withAssertionError() {
        Assertions.assertThrows(AssertionError.class, () -> FileToTaskParser.parse("thisisainvalidargument"));
        Assertions.assertThrows(AssertionError.class, () -> FileToTaskParser.parse("event,sdfsdfgdfsgdf"));
        Assertions.assertThrows(AssertionError.class, () -> FileToTaskParser.parse("deadline,wasdwasdwasd"));
        Assertions.assertThrows(AssertionError.class, () -> FileToTaskParser.parse(""));
        Assertions.assertThrows(AssertionError.class, () -> FileToTaskParser.parse("deadline,"));
    }

}
