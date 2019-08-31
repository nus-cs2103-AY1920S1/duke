package duke.parser;

import duke.exception.LineInFileParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FileLineParserTest {

    @Test
    public void parse_success() {
        try {
            assertEquals(new Todo("eat food").toString(),
                         FileLineParser.parse("todo,false,eat food").toString());
            assertEquals(new Todo("eat food").toString(),
                         FileLineParser.parse("todo,false,eat food,null").toString());
            assertEquals(new Todo("swim").toString(),
                         FileLineParser.parse("todo,false,swim").toString());
            assertEquals(new Todo("swim", true).toString(),
                         FileLineParser.parse("todo,true,swim").toString());
            assertEquals(new Deadline("hw",
                                      new SimpleDateFormat("dd/MM/yyyy HHmm")
                                      .parse("15/03/2019 1200"),false)
                         .toString(),
                         FileLineParser.parse("deadline,false,hw,15/03/2019 1200").toString());
            assertEquals(new Deadline("hw",
                                      new SimpleDateFormat("dd/MM/yyyy HHmm")
                                      .parse("01/01/1632 2356"),false)
                         .toString(),
                         FileLineParser.parse("deadline,false,hw,01/01/1632 2356").toString());
            assertEquals(new Deadline("assignment",
                                      new SimpleDateFormat("dd/MM/yyyy HHmm")
                                      .parse("01/01/1632 2356"),true)
                         .toString(),
                         FileLineParser.parse("deadline,true,assignment,01/01/1632 2356").toString());
            assertEquals(new Event("meeting",
                                   new SimpleDateFormat("dd/MM/yyyy HHmm")
                                   .parse("23/04/2031 1853"),true)
                         .toString(),
                         FileLineParser.parse("event,true,meeting,23/04/2031 1853").toString());
            assertEquals(new Event("meeting",
                                   new SimpleDateFormat("dd/MM/yyyy HHmm")
                                   .parse("23/04/2031 1853"),true)
                         .toString(),
                         FileLineParser.parse("event,true,meeting,23/04/2031 1853").toString());
        } catch (Exception pe) {
            fail();
        }
    }

    @Test
    public void parse_with_ExceptionThrown() {

        Assertions.assertThrows(LineInFileParseException.class, () -> FileLineParser.parse("deadline,"));
        Assertions.assertThrows(LineInFileParseException.class, () -> FileLineParser
                                                                      .parse("event,true,meeting, 1834233"));
        Assertions.assertThrows(LineInFileParseException.class, () -> FileLineParser.parse("thisisainvalidargument"));
        Assertions.assertThrows(LineInFileParseException.class, () -> FileLineParser.parse("event,sdfsdfgdfsgdf"));
        Assertions.assertThrows(LineInFileParseException.class, () -> FileLineParser.parse("deadline,wasdwasdwasd"));
        Assertions.assertThrows(LineInFileParseException.class, () -> FileLineParser.parse(""));
        Assertions.assertThrows(NullPointerException.class, () -> FileLineParser.parse(null));
    }

}
