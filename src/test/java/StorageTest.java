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
    void parseTodoNotDone() {
        List<Task> expected = List.of(new Todo("not done todo", false));
        List<Task> actual = Storage.parse(toStringReader("T\u001F0\u001Fnot done todo"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parseTodoDone() {
        List<Task> expected = List.of(new Todo("done todo", true));
        List<Task> actual = Storage.parse(toStringReader("T\u001F1\u001Fdone todo"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parseDeadlineNotDone() {
        List<Task> expected = List.of(new Deadline("not done deadline", false, LocalDateTime.of(2019, 6, 6, 0, 0)));
        List<Task> actual = Storage.parse(toStringReader("D\u001F0\u001Fnot done deadline\u001F6/6/2019 0000"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parseDeadlineDone() {
        List<Task> expected = List.of(new Deadline("done deadline", true, LocalDateTime.of(2019, 6, 6, 0, 0)));
        List<Task> actual = Storage.parse(toStringReader("D\u001F1\u001Fdone deadline\u001F6/6/2019 0000"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parseDeadlineShortDate() {
        List<Task> expected = List.of(new Deadline("short date format", false, LocalDateTime.of(2019, 6, 6, 0, 0)));
        List<Task> actual = Storage.parse(toStringReader("D\u001F0\u001Fshort date format\u001F6/6/2019 0000"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parseDeadlineLongDate() {
        List<Task> expected = List.of(new Deadline("long date format", false, LocalDateTime.of(2019, 6, 6, 0, 0)));
        List<Task> actual = Storage.parse(toStringReader("D\u001F0\u001Flong date format\u001F06/06/2019 0000"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parseEventNotDone() {
        List<Task> expected = List.of(new Event("not done event", false, LocalDateTime.of(2019, 8, 6, 14, 0)));
        List<Task> actual = Storage.parse(toStringReader("E\u001F0\u001Fnot done event\u001F6/8/2019 1400"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parseEventDone() {
        List<Task> expected = List.of(new Event("done event", true, LocalDateTime.of(2019, 8, 6, 14, 0)));
        List<Task> actual = Storage.parse(toStringReader("E\u001F1\u001Fdone event\u001F6/8/2019 1400"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parseEventShortDate() {
        List<Task> expected = List.of(new Event("short date format", false, LocalDateTime.of(2019, 8, 6, 14, 0)));
        List<Task> actual = Storage.parse(toStringReader("E\u001F0\u001Fshort date format\u001F6/8/2019 1400"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parseEventLongDate() {
        List<Task> expected = List.of(new Event("long date format", false, LocalDateTime.of(2019, 8, 6, 14, 0)));
        List<Task> actual = Storage.parse(toStringReader("E\u001F0\u001Flong date format\u001F06/08/2019 1400"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parseFile(@TempDir Path tempDir) throws IOException {
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
    void serializeTodoNotDone() {
        List<Task> task = List.of(new Todo("not done todo", false));
        String expected = "T\u001F0\u001Fnot done todo";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serializeTodoDone() {
        List<Task> task = List.of(new Todo("done todo", true));
        String expected = "T\u001F1\u001Fdone todo";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serializeDeadlineNotDone() {
        List<Task> task = List.of(new Deadline("not done deadline", false, LocalDateTime.of(2019, 6, 6, 0, 0)));
        String expected = "D\u001F0\u001Fnot done deadline\u001F6/6/2019 0000";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serializeDeadlineDone() {
        List<Task> task = List.of(new Deadline("done deadline", true, LocalDateTime.of(2019, 6, 6, 0, 0)));
        String expected = "D\u001F1\u001Fdone deadline\u001F6/6/2019 0000";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serializeDeadlineDate() {
        List<Task> task = List.of(new Deadline("date format", false, LocalDateTime.of(2019, 6, 6, 0, 0)));
        String expected = "D\u001F0\u001Fdate format\u001F6/6/2019 0000";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serializeEventNotDone() {
        List<Task> task = List.of(new Event("not done event", false, LocalDateTime.of(2019, 8, 6, 14, 0)));
        String expected = "E\u001F0\u001Fnot done event\u001F6/8/2019 1400";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serializeEventDone() {
        List<Task> task = List.of(new Event("done event", true, LocalDateTime.of(2019, 8, 6, 14, 0)));
        String expected = "E\u001F1\u001Fdone event\u001F6/8/2019 1400";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serializeEventDate() {
        List<Task> task = List.of(new Event("date format", false, LocalDateTime.of(2019, 8, 6, 14, 0)));
        String expected = "E\u001F0\u001Fdate format\u001F6/8/2019 1400";
        String actual = Storage.serialize(task);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void serializeToFile(@TempDir Path tempDir) throws IOException {
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