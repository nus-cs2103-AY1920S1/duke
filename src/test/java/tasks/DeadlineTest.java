package tasks;

import org.junit.jupiter.api.Test;

import utils.DateTime;

import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private Deadline deadline; 

    public DeadlineTest() throws ParseException {
        this.deadline = new Deadline("homework due", new DateTime("01-01-2019 15:34"));
    }

    @Test
    void storageStringTest() {
        assertEquals("D | false | homework due | 01-01-2019 15:34", deadline.getStorageString());
    }

    @Test
    void listStringTest() {
        assertEquals("[D][✘] homework due (by: 01-01-2019 15:34)", deadline.toString());
    }
}