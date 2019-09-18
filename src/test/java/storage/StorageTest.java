/*
package storage;

import org.junit.jupiter.api.Test;
import tasklist.TaskList;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StorageTest {

    private Storage testfile;

    @Test
    public void loadData_incorrectDataFormat_errormsg() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        testfile = new Storage("src\\test\\resources\\testfile.txt");
        TaskList testlist = new TaskList(testfile.loadData());
        String expectedOutput = "    ____________________________________________________________\n"
                + "     ☹ OOPS!!! I'm sorry, but I don't know that command :-(\n"
                + "    ____________________________________________________________\r\n"
                + "    ____________________________________________________________\n"
                + "     ☹ OOPS!!! I'm sorry, but loading task 1 has failed, it will be removed\n"
                + "    ____________________________________________________________\r\n";
        assertEquals(expectedOutput,outContent.toString());
    }
}
*/
