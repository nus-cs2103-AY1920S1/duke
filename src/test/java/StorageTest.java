import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Storage Test class to test the functionality of the Storage class.
 */
public class StorageTest {

    /** Absolute file path used to test loading and saving functionality. */
    private static final String FILEPATH = "data" + File.separator + "testSave.txt";

    /**
     * Tests the success of formatting a Todo into the format used for saving / loading.
     */
    @Test
    public void formatData_todo_success() {
        Task todoStub = new Todo(" testString");
        assertEquals("T|0| testString", new Storage(FILEPATH).formatData(todoStub));
    }

    /**
     * Tests the success of formatting a Deadline into the format used for saving / loading.
     */
    @Test
    public void formatData_deadline_success() {
        Task deadlineStub = new Deadline(" testString ", dateStub(), timeStub());
        assertEquals("D|0| testString |26/11/1996|1234", new Storage(FILEPATH).formatData(deadlineStub));
    }

    /**
     * Tests the success of formatting a Event into the format used for saving / loading.
     */
    @Test
    public void formatData_event_success() {
        Task eventStub = new Event(" testString ", dateStub(), timeStub(), timeStub());
        assertEquals("E|0| testString |26/11/1996|1234|1234", new Storage(FILEPATH).formatData(eventStub));
    }

    /**
     * Generates a LocalDate constant which can be used for testing.
     * @return LocalDate stub used for testing.
     */
    public static LocalDate dateStub() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        return LocalDate.parse("26/11/1996", formatter);
    }

    /**
     * Generates a LocalTime constant which can be used for testing.
     * @return LocalTime stub used for testing.
     */
    public static LocalTime timeStub() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm", Locale.ENGLISH);
        return LocalTime.parse("1234", formatter);
    }

}
