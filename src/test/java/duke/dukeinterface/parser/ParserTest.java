package duke.dukeinterface.parser;

import duke.dukeinterface.DukeException;
import duke.dukeinterface.Parser;
import org.junit.jupiter.api.Assertions;

class ParserTest {
    private Parser parser = new Parser();

    //Throws an exception
    @org.junit.jupiter.api.Test
    void checkCommandTest1() {
        String[] test = {"Wrong", "command", "exception", "expected"};
        Assertions.assertThrows(DukeException.class, () -> parser.checkCommand(test));
    }

    //Throws an exception
    @org.junit.jupiter.api.Test
    void checkTimeTest1() {
        String test = "30/2/2019";
        Assertions.assertThrows(DukeException.class,
            () ->  parser.checkTime(test));
    }

    @org.junit.jupiter.api.Test
    void checkTimeTest2() {
        String test = "12/2/2019 2360";
        Assertions.assertThrows(DukeException.class,
            () ->  parser.checkTime(test));
    }

    //Gives the timing
    @org.junit.jupiter.api.Test
    void checkTimeTest3() throws DukeException {
        String test = "deadline return books /by 12/02/2019 1800";
        Assertions.assertEquals("deadline return books /by 12nd of February 2019, 6:00pm",
                parser.checkTime(test));
    }
}