package duck.command;

<<<<<<< HEAD
=======
import duck.util.DukeException;
>>>>>>> branch-A-JavaDoc
import duck.util.Storage;
import duck.util.TaskList;
import duck.util.Ui;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static duck.util.ObjectsForTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
<<<<<<< HEAD
=======
import static org.junit.jupiter.api.Assertions.fail;
>>>>>>> branch-A-JavaDoc

class FindCommandTest {

    private Ui ui = new Ui();
    private static final PrintStream originOut = System.out;

    @AfterAll
    public static void restoreStream() {
        System.setOut(originOut);
    }

    @Test
    void execute_normal() {
<<<<<<< HEAD
=======
        try {
>>>>>>> branch-A-JavaDoc
            ByteArrayOutputStream out1 = new ByteArrayOutputStream();
            ByteArrayOutputStream out2 = new ByteArrayOutputStream();

            TaskList fullList = new TaskList(Arrays.asList(tasks));
            TaskList resultList = new TaskList();
            resultList.add(todo);

            System.setOut(new PrintStream(out1));
            new FindCommand("early").execute(fullList, ui, new Storage(filePath));
            String actualResult = out1.toString();

            System.setOut(new PrintStream(out2));
            ui.showFullList(resultList);
            String expectedResult = out2.toString();

            assertEquals(expectedResult, actualResult);
<<<<<<< HEAD
=======
        } catch (DukeException e) {
            fail();
        }
>>>>>>> branch-A-JavaDoc
    }

    @Test
    void execute_emptyList() {

        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        ByteArrayOutputStream out2 = new ByteArrayOutputStream();

        TaskList fullList = new TaskList();
        TaskList resultList = new TaskList();

<<<<<<< HEAD
=======
        try {
>>>>>>> branch-A-JavaDoc
            System.setOut(new PrintStream(out1));
            new FindCommand("early").execute(fullList, ui, new Storage(filePath));
            String actualResult = out1.toString();

            System.setOut(new PrintStream(out2));
            ui.showFullList(resultList);
            String expectedResult = out2.toString();

            assertEquals(expectedResult, actualResult);
<<<<<<< HEAD

    }
}
=======
        } catch (DukeException e) {
            fail();
        }

    }
}
>>>>>>> branch-A-JavaDoc
