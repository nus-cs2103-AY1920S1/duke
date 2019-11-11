package duke.task;

import duke.ui.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void saveTask_Test_success() throws DukeException {
        assertEquals("D | 1 | play tennis | 02/02/2019 2000",
                new Deadline(true, "play tennis", "02/02/2019 2000").saveTask());
    }

    @Test
    public void toString_Test_success() throws DukeException {
        assertEquals(" [D][✘] report submission (by: 06 August 2008 11.00PM)",
                (new Deadline(false, "report submission", "06/08/2008 2300")).toString());
    }
}
