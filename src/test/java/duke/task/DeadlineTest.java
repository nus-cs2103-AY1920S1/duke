package duke.task;

import duke.command.DukeException;
import duke.command.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void printTime() {
        try {
            assertEquals("11th January, 2000. 3:43pm",
                    new Deadline(new Parser("deaDLine finish job /by 11/1/2000 1543")).printTime());
            Deadline deadline = new Deadline(new Parser("deadline finish job 11/1/2000 /by 1543"));
        } catch (DukeException e) {
            assertEquals(" :( OOPS!!! Invalid format for date and time. Please enter 'DD/MM/YYYY HHMM'",
                    e.getMessage());
        }
    }

    @Test
    void testToString() throws DukeException {
        assertEquals("[D][-] sleep (by: 29th July, 2018. 11:00pm)",
                new Deadline(new Parser("deadline sleep /by 29/07/2018 2300")).toString());
        Deadline deadline = new Deadline(new Parser("DEadline sleep right now /by 21/12/2015 0846"));
        deadline.markAsDone();
        assertEquals("[D][+] sleep right now (by: 21st December, 2015. 8:46am)", deadline.toString());
    }
}