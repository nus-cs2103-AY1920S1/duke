package duke;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testLoadTasks() throws DukeException, FileNotFoundException, ParseException {
        Storage s = new Storage("C:/CS2103T/duke/src/test/java/TestFile.txt");
        List<Task> actual = s.loadTasks();
        assertEquals("[T][ ][L] read book", actual.get(0).toString());
        assertEquals("[D][+][L] borrow book (by: 06 Jun 2012, 06:00 PM)", actual.get(1).toString());
        assertEquals("[E][ ][L] meeting (at: 25 Jun 2016, 06:00 AM)", actual.get(2).toString());
    }

    @Test
    public void testSaveTasks() throws IOException, DukeException, ParseException {
        Storage s = new Storage("C:/CS2103T/duke/src/test/java/TestFile2.txt");
        List<Task> list = new ArrayList<>();
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        list.add(new ToDo("todo 1", false));
        list.add(new Deadline("deadline 2", dateTimeFormat.parse("09/08/1976 2300"), false));
        list.add(new Event("event 3", dateTimeFormat.parse("15/02/2109 0300"), false));
        TaskList t = new TaskList(list);
        s.saveTasks(t);
        File f = new File("C:/CS2103T/duke/src/test/java/TestFile2.txt");
        Scanner sc = new Scanner(f);
        assertEquals("T | 0 | L | todo 1", sc.nextLine());
        assertEquals("D | 0 | L | deadline 2 | 09 Aug 1976, 11:00 PM", sc.nextLine());
        assertEquals("E | 0 | L | event 3 | 15 Feb 2109, 03:00 AM", sc.nextLine());
    }
}
