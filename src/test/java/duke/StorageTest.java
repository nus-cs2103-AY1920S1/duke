package duke;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testLoadTasks() throws DukeException, FileNotFoundException {
        Storage s = new Storage("C:/CS2103T/duke/src/test/java/TestFile.txt");
        List<Task> actual = s.loadTasks();
        assertEquals("[T][✘] read book", actual.get(0).toString());
        assertEquals("[D][✓] borrow book (by: 06 Jun 2012, 6:00 pm)", actual.get(1).toString());
        assertEquals("[E][✘] meeting (at: 25 Jun 2016, 6:00 am)", actual.get(2).toString());
    }

    @Test
    public void testSaveTasks() throws IOException, DukeException {
        Storage s = new Storage("C:/CS2103T/duke/src/test/java/TestFile2.txt");
        List<Task> list = new ArrayList<>();
        list.add(new ToDo("todo 1"));
        list.add(new Deadline("deadline 2", "09/08/1976 2300"));
        list.add(new Event("event 3", "30/02/2109 0300"));
        TaskList t = new TaskList(list);
        s.saveTasks(t);
        File f = new File("C:/CS2103T/duke/src/test/java/TestFile2.txt");
        Scanner sc = new Scanner(f);
        assertEquals("T | 0 | todo 1", sc.nextLine());
        assertEquals("D | 0 | deadline 2 | 09/08/1976 2300", sc.nextLine());
        assertEquals("E | 0 | event 3 | 30/02/2109 0300", sc.nextLine());
    }
}
