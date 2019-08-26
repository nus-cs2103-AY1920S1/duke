import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    private StringBuilder tests = new StringBuilder()
            .append("todo borrow book\n")
            .append("list\n")
            .append("deadline D1 Project Assignment /by 2dect1900\n")
            .append("done 0\n")
            .append("done 3\n")
            .append("event music festival\n")
            .append("bye\n");
    private String[] expected = {
            "Got it. I've added this task:\n" +
                    "  [T][\u2718] borrow book\n" +
                    "Now you have 1 tasks in the list.\n",

            "Here are the tasks in your list:\n" +
                    "1.[T][\u2718] borrow book\n",

            "Got it. I've added this task:\n" +
                    "  [D][\u2718] D1 Project Assignment (by: December 2 2019, 7:00 PM)\n" +
                    "Now you have 2 tasks in the list.\n",

            "Index given is out of bound.\nUse from 1 to last index of list only.\n",

            "Index given is out of bound.\nUse from 1 to last index of list only.\n",

            "\u2639 OOPS!!! The time of a event cannot be empty.\n",

            "Bye. Hope to see you again soon!\n"
    };

    @Test
    public void fixedTest() {
        assertThrows(IndexFormatDukeException.class, () -> Parser.parse("done 3k"));
        assertThrows(IndexFormatDukeException.class, () -> Parser.parse("  delete   1.0 "));
        assertDoesNotThrow(() -> Parser.parse(" list 0"));
        assertDoesNotThrow(() -> Parser.parse("  delete   -10 "));
        assertDoesNotThrow(() -> Parser.parse("  event  music festival  /at dec2t2019 "));
        assertDoesNotThrow(() -> Parser.parse("event golf /at 2 December"));
        assertDoesNotThrow(() -> Parser.parse("event bowling /at 2/12 T2019"));
        assertDoesNotThrow(() -> Parser.parse("  deadline D1 project assignment   /by t2019 "));
        assertThrows(DateTimeParseDukeException.class, () -> Parser.parse("event running /at 2019"));
        assertThrows(LoadFileFailDukeException.class, () -> new Storage("doesNotExist.txt").load());
    }

    @Test
    public void runTest() {
        System.setIn(new ByteArrayInputStream(tests.toString().getBytes()));
        boolean isExit = false;
        int i = 0;

        ui = new TestUi();
        storage = new TestStorage();
        tasks = new TaskList();

        ui.showWelcome();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(fullCommand);
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.show(e.getMessage());
            } finally {
                assertEquals(expected[i++], ui.toString());
            }
        }
    }
}

class TestUi extends Ui {
    private String output;

    @Override
    public void show(String str) {
        if (!str.endsWith("\n"))
            str += '\n';
        output = str;
        super.show(str);
    }

    @Override
    public String toString() {
        String buf = output;
        output = "";
        return buf;
    }
}

class TestStorage extends Storage {
    public TestStorage() {
        super("");
    }

    @Override
    public void rewrite(String content) {}
}