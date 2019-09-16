package duke.parser;

import duke.exception.FailedToLoadIOException;
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
            Task todo1 = new Todo("eat food");
            Task todo2 = new Todo("swim");
            Task todo3 = new Todo("swim");
            todo3.markAsDone();
            //deadline
            Task deadline1 = new Deadline("hw",
                    new SimpleDateFormat("dd/MM/yyyy HHmm").parse("15/03/2019 1200"));
            Task deadline2 = new Deadline("hw",
                    new SimpleDateFormat("dd/MM/yyyy HHmm").parse("01/01/1632 2356"));
            Task deadline3 = new Deadline("assignment",
                    new SimpleDateFormat("dd/MM/yyyy HHmm").parse("01/01/1632 2356"));
            deadline3.markAsDone();
            //event
            Task event1 = new Event("meeting",
                    new SimpleDateFormat("dd/MM/yyyy HHmm").parse("23/04/2031 1853"));
            event1.markAsDone();
            Task event2 = new Event("meeting",
                    new SimpleDateFormat("dd/MM/yyyy HHmm").parse("23/04/2031 1853"));

            assertEquals(todo1.toString(), FileToTaskParser.parse("todo,false,null,eat food").toString());
            assertEquals(todo1.toString(), FileToTaskParser.parse("todo,false,null,eat food,null").toString());
            assertEquals(todo2.toString(), FileToTaskParser.parse("todo,false,null,swim").toString());
            assertEquals(todo3.toString(), FileToTaskParser.parse("todo,true,null,swim").toString());
            assertEquals(deadline1.toString(),
                    FileToTaskParser.parse("deadline,false,null,hw,15/03/2019 1200").toString());
            assertEquals(deadline2.toString(),
                         FileToTaskParser.parse("deadline,false,null,hw,01/01/1632 2356").toString());
            assertEquals(deadline3.toString(),
                         FileToTaskParser.parse("deadline,true,null,assignment,01/01/1632 2356").toString());
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

        Assertions.assertThrows(FailedToLoadIOException.class, () -> FileToTaskParser.parse("deadline,"));
        Assertions.assertThrows(FailedToLoadIOException.class, () -> FileToTaskParser
                                                                      .parse("event,true,null,meeting, 1834233"));
        Assertions.assertThrows(FailedToLoadIOException.class, () -> FileToTaskParser.parse("thisisainvalidargument"));
        Assertions.assertThrows(FailedToLoadIOException.class, () -> FileToTaskParser.parse("event,sdfsdfgdfsgdf"));
        Assertions.assertThrows(FailedToLoadIOException.class, () -> FileToTaskParser.parse("deadline,wasdwasdwasd"));
        Assertions.assertThrows(FailedToLoadIOException.class, () -> FileToTaskParser.parse(""));
        Assertions.assertThrows(NullPointerException.class, () -> FileToTaskParser.parse(null));
    }

}
