package duke.task;

import duke.ui.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void printDate_Test_success() {
        try {
            assertEquals("09 January 2019 06.00PM",
                    new Event(false, "read book", "09/01/2019 1800").printDate());
            assertEquals("31 December 2019 08.00AM",
                    new Event(false, "read book", "31/12/2019 0800").printDate());
        } catch (DukeException de) {
            assertEquals("Parsing error detected.", de.getMessage());
        }
    }
}
