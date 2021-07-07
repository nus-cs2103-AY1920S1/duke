package duke.task;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void getDateString() {
        Deadline deadline = new Deadline("test", new Date(
                2019 - 1900, 11, 30, 10, 10
        ));
        assertEquals("30/12/2019 1010", deadline.getDateString());
    }

    @Test
    void getStorageSyntax() {
        Deadline deadline = new Deadline("test", new Date(
                2019 - 1900, 11, 30, 10, 10
        ));
        assertEquals("D | 0 | test | 30/12/2019 1010 | -1", deadline.getStorageSyntax());

    }
}