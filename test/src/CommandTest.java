import duke.Commands.*;
import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;
import duke.DukeException;
import duke.Tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CommandTest {

    TaskList tl = new TaskList();
    Ui ui = new Ui();

    @Test
    public void validAddCommandTest1() {
        AddCommand ac = new AddCommand("T", "Eat dinner");
        try {
            ac.execute(tl, ui);
            assertFalse(ac.isExit());
            assertEquals("1.[T][\u2715] Eat dinner", tl.listAllTask().get(0));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void validAddCommandTest2() {
        AddCommand ac1 = new AddCommand("D",
                "Software Engineering project", "19/08/2019 00:00:00");
        AddCommand ac2 = new AddCommand("E",
                "Drink beer", "19/08/2019 20:00:00");
        AddCommand ac3 = new AddCommand("T", "Eat dinner");
        try {
            ac1.execute(tl, ui);
            ac2.execute(tl, ui);
            ac3.execute(tl, ui);
            ArrayList<String> check = tl.listAllTask();
            assertEquals("1.[D][\u2715] Software Engineering project (by: 19/08/2019 00:00:00)",
                    check.get(0));
            assertEquals("2.[E][\u2715] Drink beer (at: 19/08/2019 20:00:00)", check.get(1));
            assertEquals("3.[T][\u2715] Eat dinner", check.get(2));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void invalidAddCommandTest1() {
        AddCommand ac1 = new AddCommand("D",
                "Software Engineering project", "19/08/2019 00:00:00");
        AddCommand ac2 = new AddCommand("E",
                "Drink beer", "Saturday");
        AddCommand ac3 = new AddCommand("T", "Eat dinner");
        try {
            ac1.execute(tl, ui);
            ac2.execute(tl, ui);
            ac3.execute(tl, ui);
            ArrayList<String> check = tl.listAllTask();
            assertEquals(1, 2);
        } catch (DukeException e) {
            assertEquals("The date input format is not correct, " +
                    "it should be in the form dd/MM/yyyy HH:mm:ss", e.getMessage());
        }
    }

    @Test
    public void invalidAddCommandTest2() {
        AddCommand ac1 = new AddCommand("D",
                "", "19/08/2019 00:00:00");
        AddCommand ac2 = new AddCommand("E",
                "Drink beer", "19/08/2019 20:00:00");
        AddCommand ac3 = new AddCommand("T", "Eat dinner");
        try {
            ac1.execute(tl, ui);
            ac2.execute(tl, ui);
            ac3.execute(tl, ui);
            ArrayList<String> check = tl.listAllTask();
            assertEquals(1, 2);
        } catch (DukeException e) {
            assertEquals("Input task name cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void validDeleteCommandTest() {
        AddCommand ac1 = new AddCommand("D",
                "Software Engineering project", "19/08/2019 00:00:00");
        AddCommand ac2 = new AddCommand("E",
                "Drink beer", "19/08/2019 20:00:00");
        AddCommand ac3 = new AddCommand("T", "Eat dinner");
        DeleteCommand dc1 = new DeleteCommand(1);
        try {
            ac1.execute(tl, ui);
            ac2.execute(tl, ui);
            ac3.execute(tl, ui);
            dc1.execute(tl, ui);
            ArrayList<String> check = tl.listAllTask();
            assertEquals("1.[E][\u2715] Drink beer (at: 19/08/2019 20:00:00)", check.get(0));
            assertEquals("2.[T][\u2715] Eat dinner", check.get(1));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void fakeCommandTest() {
        FakeCommand fc  = new FakeCommand();
        try {
            fc.execute(tl, ui);
            assertEquals(1, 2);
        } catch (DukeException e) {
            assertEquals("Please input a valid command.", e.getMessage());
        }
    }

    @Test
    public void validFinishCommandTest() {
        AddCommand ac1 = new AddCommand("D",
                "Software Engineering project", "19/08/2019 00:00:00");
        AddCommand ac2 = new AddCommand("E",
                "Drink beer", "19/08/2019 20:00:00");
        AddCommand ac3 = new AddCommand("T", "Eat dinner");
        FinishCommand fc = new FinishCommand(3);
        try {
            ac1.execute(tl, ui);
            ac2.execute(tl, ui);
            ac3.execute(tl, ui);
            fc.execute(tl, ui);
            ArrayList<String> check = tl.listAllTask();
            assertEquals("1.[D][\u2715] Software Engineering project (by: 19/08/2019 00:00:00)",
                    check.get(0));
            assertEquals("2.[E][\u2715] Drink beer (at: 19/08/2019 20:00:00)", check.get(1));
            assertEquals("3.[T][\u2713] Eat dinner", check.get(2));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void findCommandTest() {
        AddCommand ac1 = new AddCommand("D",
                "Software Engineering project", "19/08/2019 00:00:00");
        AddCommand ac2 = new AddCommand("E",
                "Drink beer", "19/08/2019 20:00:00");
        AddCommand ac3 = new AddCommand("T", "Eat dinner");
        FinishCommand fc = new FinishCommand(3);
        FindCommand fdc = new FindCommand("dinner");
        try {
            ac1.execute(tl, ui);
            ac2.execute(tl, ui);
            ac3.execute(tl, ui);
            fc.execute(tl, ui);
            fdc.execute(tl, ui);
            ArrayList<String> check = tl.listMatchTask("dinner");
            assertEquals("1.[T][\u2713] Eat dinner", check.get(0));
            assertEquals(1, check.size());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }
}
