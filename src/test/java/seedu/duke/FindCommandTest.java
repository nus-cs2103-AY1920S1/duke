package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {
    int asciiTick = 0x2713;
    int asciiCross = 0x2718;
    String tick = Character.toString((char)asciiTick);
    String cross = Character.toString((char) asciiCross);

    @Test
    public void test1() {
        FindCommand find = new FindCommand("find e");
        StorageStub st = new StorageStub("dummy");
        TaskList tl = new TaskList(st.loadTaskFile());
        ExpenseList el = new ExpenseList(st.loadExpenseFile(), st.loadIncomeFile());
        Ui ui = new Ui();

        try {
            assertEquals("Here are the matching tasks in your list:\n"
                    + "1. [T][" + cross + "] def\n", find.execute(tl, el, ui, st, st, st));
        } catch (Exception e) {
            assertEquals(2, 3);
        }
    }
}
