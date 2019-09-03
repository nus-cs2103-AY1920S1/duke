package command;

import duke.command.FindCommand;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static duke.util.ObjectsForTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FindCommandTest {

    private Ui ui = new Ui();
    private static final PrintStream originOut = System.out;

    @AfterAll
    public static void restoreStream() {
        System.setOut(originOut);
    }

    @Test
    void execute_normal() {

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
    }

    @Test
    void execute_emptyList() {

        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        ByteArrayOutputStream out2 = new ByteArrayOutputStream();

        TaskList fullList = new TaskList();
        TaskList resultList = new TaskList();

        System.setOut(new PrintStream(out1));
        new FindCommand("early").execute(fullList, ui, new Storage(filePath));
        String actualResult = out1.toString();

        System.setOut(new PrintStream(out2));
        ui.showFullList(resultList);
        String expectedResult = out2.toString();

        assertEquals(expectedResult, actualResult);
    }
}

