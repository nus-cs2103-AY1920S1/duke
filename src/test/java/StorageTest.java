import duke.storage.Storage;
import duke.model.Deadline;
import duke.model.Event;
import duke.model.Task;
import duke.model.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

class StorageTest {
    static StringReader toStringReader(String s) {
        return new StringReader(s);
    }

    @Test
    void parse_notDoneTodoString_newTodoInstanceMarkedAsNotDone() {
        List<Task> expected = List.of(new Todo("not done todo", false));
        List<Task> actual = Storage.parse(toStringReader("T\u001F0\u001Fnot done todo"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parse_doneTodoString_newTodoInstanceMarkedAsDone() {
        List<Task> expected = List.of(new Todo("done todo", true));
        List<Task> actual = Storage.parse(toStringReader("T\u001F1\u001Fdone todo"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parse_notDoneDeadlineString_newDeadlineInstanceMarkedAsNotDone() {
        List<Task> expected = List.of(new Deadline("not done deadline", false, LocalDateTime.of(2019, 6, 6, 0, 0)));
        List<Task> actual = Storage.parse(toStringReader("D\u001F0\u001Fnot done deadline\u001F6/6/2019 0000"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parse_doneDeadlineString_newDeadlineInstanceMarkedAsDone() {
        List<Task> expected = List.of(new Deadline("done deadline", true, LocalDateTime.of(2019, 6, 6, 0, 0)));
        List<Task> actual = Storage.parse(toStringReader("D\u001F1\u001Fdone deadline\u001F6/6/2019 0000"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parse_shortDateFormatDeadlineString_newDeadlineInstance() {
        List<Task> expected = List.of(new Deadline("short date format", false, LocalDateTime.of(2019, 6, 6, 0, 0)));
        List<Task> actual = Storage.parse(toStringReader("D\u001F0\u001Fshort date format\u001F6/6/2019 0000"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parse_longDateFormatDeadlineString_newDeadlineInstance() {
        List<Task> expected = List.of(new Deadline("long date format", false, LocalDateTime.of(2019, 6, 6, 0, 0)));
        List<Task> actual = Storage.parse(toStringReader("D\u001F0\u001Flong date format\u001F06/06/2019 0000"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parse_notDoneEventString_newEventInstanceMarkedAsNotDone() {
        List<Task> expected = List.of(new Event("not done event", false, LocalDateTime.of(2019, 8, 6, 14, 0)));
        List<Task> actual = Storage.parse(toStringReader("E\u001F0\u001Fnot done event\u001F6/8/2019 1400"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parse_doneEventString_newEventInstanceMarkedAsDone() {
        List<Task> expected = List.of(new Event("done event", true, LocalDateTime.of(2019, 8, 6, 14, 0)));
        List<Task> actual = Storage.parse(toStringReader("E\u001F1\u001Fdone event\u001F6/8/2019 1400"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parse_shortDateFormatEventString_newEventInstance() {
        List<Task> expected = List.of(new Event("short date format", false, LocalDateTime.of(2019, 8, 6, 14, 0)));
        List<Task> actual = Storage.parse(toStringReader("E\u001F0\u001Fshort date format\u001F6/8/2019 1400"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parse_longDateFormatEventString_newEventInstance() {
        List<Task> expected = List.of(new Event("long date format", false, LocalDateTime.of(2019, 8, 6, 14, 0)));
        List<Task> actual = Storage.parse(toStringReader("E\u001F0\u001Flong date format\u001F06/08/2019 1400"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parseFromFile_multilineMixedTypedTasksFile_listOfMixedTypeTasks(@TempDir Path tempDir) throws IOException {
        Path path = tempDir.resolve("input.txt");

        String fileContents = "T\u001F0\u001Fnot done todo"
                + "\nT\u001F1\u001Fdone todo"
                + "\nD\u001F0\u001Fnot done deadline\u001F6/6/2019 0000"
                + "\nD\u001F1\u001Fdone deadline\u001F6/6/2019 0000"
                + "\nD\u001F0\u001Fshort date format deadline\u001F6/6/2019 0000"
                + "\nD\u001F0\u001Flong date format deadline\u001F06/06/2019 0000"
                + "\nE\u001F0\u001Fnot done event\u001F6/8/2019 1400"
                + "\nE\u001F1\u001Fdone event\u001F6/8/2019 1400"
                + "\nE\u001F0\u001Fshort date format event\u001F6/8/2019 1400"
                + "\nE\u001F0\u001Flong date format event\u001F06/08/2019 1400";
        List<Task> expected = List.of(
                new Todo("not done todo", false),
                new Todo("done todo", true),
                new Deadline("not done deadline", false, LocalDateTime.of(2019, 6, 6, 0, 0)),
                new Deadline("done deadline", true, LocalDateTime.of(2019, 6, 6, 0, 0)),
                new Deadline("short date format deadline", false, LocalDateTime.of(2019, 6, 6, 0, 0)),
                new Deadline("long date format deadline", false, LocalDateTime.of(2019, 6, 6, 0, 0)),
                new Event("not done event", false, LocalDateTime.of(2019, 8, 6, 14, 0)),
                new Event("done event", true, LocalDateTime.of(2019, 8, 6, 14, 0)),
                new Event("short date format event", false, LocalDateTime.of(2019, 8, 6, 14, 0)),
                new Event("long date format event", false, LocalDateTime.of(2019, 8, 6, 14, 0))
        );
        Files.writeString(path, fileContents);

        List<Task> actual = Storage.parseFromFile(path);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serialize_todoInstanceMarkedAsNotDone_notDoneTodoString() {
        List<Task> task = List.of(new Todo("not done todo", false));
        String expected = "T\u001F0\u001Fnot done todo";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serialize_todoInstanceMarkedAsDone_doneTodoString() {
        List<Task> task = List.of(new Todo("done todo", true));
        String expected = "T\u001F1\u001Fdone todo";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serialize_deadlineInstanceMarkedAsNotDone_notDoneDeadlineString() {
        List<Task> task = List.of(new Deadline("not done deadline", false, LocalDateTime.of(2019, 6, 6, 0, 0)));
        String expected = "D\u001F0\u001Fnot done deadline\u001F6/6/2019 0000";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serialize_deadlineInstanceMarkedAsDone_doneDeadlineString() {
        List<Task> task = List.of(new Deadline("done deadline", true, LocalDateTime.of(2019, 6, 6, 0, 0)));
        String expected = "D\u001F1\u001Fdone deadline\u001F6/6/2019 0000";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serialize_dateOfDeadlineInstance_deadlineExpectedDateFormatString() {
        List<Task> task = List.of(new Deadline("date format", false, LocalDateTime.of(2019, 6, 6, 0, 0)));
        String expected = "D\u001F0\u001Fdate format\u001F6/6/2019 0000";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serialize_eventInstanceMarkedAsNotDone_notDoneEventString() {
        List<Task> task = List.of(new Event("not done event", false, LocalDateTime.of(2019, 8, 6, 14, 0)));
        String expected = "E\u001F0\u001Fnot done event\u001F6/8/2019 1400";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serialize_eventInstanceMarkedAsDone_doneEventString() {
        List<Task> task = List.of(new Event("done event", true, LocalDateTime.of(2019, 8, 6, 14, 0)));
        String expected = "E\u001F1\u001Fdone event\u001F6/8/2019 1400";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serialize_dateOfEventInstance_eventExpectedDateFormatString() {
        List<Task> task = List.of(new Event("date format", false, LocalDateTime.of(2019, 8, 6, 14, 0)));
        String expected = "E\u001F0\u001Fdate format\u001F6/8/2019 1400";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serializeToFile_listOfMixedTypeTasks_multilineMixedTypedTasksFile(@TempDir Path tempDir) throws IOException {
        Path path = tempDir.resolve("output.txt");

        List<Task> tasks = List.of(
                new Todo("not done todo", false),
                new Todo("done todo", true),
                new Deadline("not done deadline", false, LocalDateTime.of(2019, 6, 6, 0, 0)),
                new Deadline("done deadline", true, LocalDateTime.of(2019, 6, 6, 0, 0)),
                new Deadline("date format deadline", false, LocalDateTime.of(2019, 6, 6, 0, 0)),
                new Event("not done event", false, LocalDateTime.of(2019, 8, 6, 14, 0)),
                new Event("done event", true, LocalDateTime.of(2019, 8, 6, 14, 0)),
                new Event("date format event", false, LocalDateTime.of(2019, 8, 6, 14, 0))
        );
        String expected = "T\u001F0\u001Fnot done todo"
                + "\nT\u001F1\u001Fdone todo"
                + "\nD\u001F0\u001Fnot done deadline\u001F6/6/2019 0000"
                + "\nD\u001F1\u001Fdone deadline\u001F6/6/2019 0000"
                + "\nD\u001F0\u001Fdate format deadline\u001F6/6/2019 0000"
                + "\nE\u001F0\u001Fnot done event\u001F6/8/2019 1400"
                + "\nE\u001F1\u001Fdone event\u001F6/8/2019 1400"
                + "\nE\u001F0\u001Fdate format event\u001F6/8/2019 1400";

        Storage.serializeToFile(path, tasks);

        String actual = Files.readString(path);
        Assertions.assertEquals(expected, actual);
    }

}