package duke;

import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;
import duke.commands.AddCommand;
import duke.commands.DeleteCommand;
import duke.commands.FakeCommand;
import duke.commands.FindCommand;
import duke.commands.FinishCommand;
import duke.tasks.TaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CommandTest {

    TaskList tl = new TaskList();
    Ui ui = new Ui();

    @Test
    public void validAddCommandTest1() {
        AddCommand ac = new AddCommand(TaskType.T, "Eat dinner");
        try {
            ac.execute(tl, ui);
            Assertions.assertFalse(ac.isExit());
            Assertions.assertEquals("1.[T][X] Eat dinner", tl.listAllTask().get(0));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals(1, 2);
        }
    }

    @Test
    public void validAddCommandTest2() {
        AddCommand ac1 = new AddCommand(TaskType.D,
                "Software Engineering project", "19/08/2019 00:00:00");
        AddCommand ac2 = new AddCommand(TaskType.E,
                "Drink beer", "19/08/2019 20:00:00");
        AddCommand ac3 = new AddCommand(TaskType.T, "Eat dinner");
        try {
            ac1.execute(tl, ui);
            ac2.execute(tl, ui);
            ac3.execute(tl, ui);
            ArrayList<String> check = tl.listAllTask();
            Assertions.assertEquals("1.[D][X] Software Engineering project (by: \n  19/08/2019 00:00:00)",
                    check.get(0));
            Assertions.assertEquals("2.[E][X] Drink beer (at: \n  19/08/2019 20:00:00)", check.get(1));
            Assertions.assertEquals("3.[T][X] Eat dinner", check.get(2));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals(1, 2);
        }
    }

    @Test
    public void invalidAddCommandTest1() {
        AddCommand ac1 = new AddCommand(TaskType.D,
                "Software Engineering project", "19/08/2019 00:00:00");
        AddCommand ac2 = new AddCommand(TaskType.E,
                "Drink beer", "Saturday");
        AddCommand ac3 = new AddCommand(TaskType.T, "Eat dinner");
        try {
            ac1.execute(tl, ui);
            ac2.execute(tl, ui);
            ac3.execute(tl, ui);
            ArrayList<String> check = tl.listAllTask();
            Assertions.assertEquals(1, 2);
        } catch (DukeException e) {
            Assertions.assertEquals("The date input format is not correct, "
                    + "it should be in the form dd/MM/yyyy HH:mm:ss", e.getMessage());
        }
    }

    @Test
    public void invalidAddCommandTest2() {
        AddCommand ac1 = new AddCommand(TaskType.D,
                "", "19/08/2019 00:00:00");
        AddCommand ac2 = new AddCommand(TaskType.E,
                "Drink beer", "19/08/2019 20:00:00");
        AddCommand ac3 = new AddCommand(TaskType.T, "Eat dinner");
        try {
            ac1.execute(tl, ui);
            ac2.execute(tl, ui);
            ac3.execute(tl, ui);
            ArrayList<String> check = tl.listAllTask();
            Assertions.assertEquals(1, 2);
        } catch (DukeException e) {
            Assertions.assertEquals("Input task name cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void validDeleteCommandTest() {
        AddCommand ac1 = new AddCommand(TaskType.D,
                "Software Engineering project", "19/08/2019 00:00:00");
        AddCommand ac2 = new AddCommand(TaskType.E,
                "Drink beer", "19/08/2019 20:00:00");
        AddCommand ac3 = new AddCommand(TaskType.T, "Eat dinner");
        DeleteCommand dc1 = new DeleteCommand(1);
        try {
            ac1.execute(tl, ui);
            ac2.execute(tl, ui);
            ac3.execute(tl, ui);
            dc1.execute(tl, ui);
            ArrayList<String> check = tl.listAllTask();
            Assertions.assertEquals("1.[E][X] Drink beer (at: \n  19/08/2019 20:00:00)", check.get(0));
            Assertions.assertEquals("2.[T][X] Eat dinner", check.get(1));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals(1, 2);
        }
    }

    @Test
    public void fakeCommandTest() {
        FakeCommand fc  = new FakeCommand();
        try {
            fc.execute(tl, ui);
            Assertions.assertEquals(1, 2);
        } catch (DukeException e) {
            Assertions.assertEquals("Please input a valid command.", e.getMessage());
        }
    }

    @Test
    public void validFinishCommandTest() {
        AddCommand ac1 = new AddCommand(TaskType.D,
                "Software Engineering project", "19/08/2019 00:00:00");
        AddCommand ac2 = new AddCommand(TaskType.E,
                "Drink beer", "19/08/2019 20:00:00");
        AddCommand ac3 = new AddCommand(TaskType.T, "Eat dinner");
        FinishCommand fc = new FinishCommand(3);
        try {
            ac1.execute(tl, ui);
            ac2.execute(tl, ui);
            ac3.execute(tl, ui);
            fc.execute(tl, ui);
            ArrayList<String> check = tl.listAllTask();
            Assertions.assertEquals("1.[D][X] Software Engineering project (by: \n  19/08/2019 00:00:00)",
                    check.get(0));
            Assertions.assertEquals("2.[E][X] Drink beer (at: \n  19/08/2019 20:00:00)", check.get(1));
            Assertions.assertEquals("3.[T][V] Eat dinner", check.get(2));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals(1, 2);
        }
    }

    @Test
    public void findCommandTest() {
        AddCommand ac1 = new AddCommand(TaskType.D,
                "Software Engineering project", "19/08/2019 00:00:00");
        AddCommand ac2 = new AddCommand(TaskType.E,
                "Drink beer", "19/08/2019 20:00:00");
        AddCommand ac3 = new AddCommand(TaskType.T, "Eat dinner");
        FinishCommand fc = new FinishCommand(3);
        FindCommand fdc = new FindCommand("dinner");
        try {
            ac1.execute(tl, ui);
            ac2.execute(tl, ui);
            ac3.execute(tl, ui);
            fc.execute(tl, ui);
            fdc.execute(tl, ui);
            ArrayList<String> check = tl.listMatchTask("dinner");
            Assertions.assertEquals("1.[T][V] Eat dinner", check.get(0));
            Assertions.assertEquals(1, check.size());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals(1, 2);
        }
    }
}
