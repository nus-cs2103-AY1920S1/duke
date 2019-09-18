import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void readTaskTest() throws DukeException {
        String taskStr = "E - 0 - concert - 2019-08-31 21:00";
        assertEquals(Storage.readTask(taskStr).toString(),
                String.format("[E][%s] concert (at: 2019-08-31 21:00)", Unicode.CROSS));
    }

    @Test
    public void writeTaskTest() throws ParseException {
        Event event = new Event("concert", Storage.format.parse("2019-08-31 21:00"));
        assertEquals(Storage.writeTask(event), "E - 0 - concert - 2019-08-31 21:00");
    }
}
