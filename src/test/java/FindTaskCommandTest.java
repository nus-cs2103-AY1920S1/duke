import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class FindTaskCommandTest extends CommandTest {
    @Test
    void findTask() {
        List<Task> actualTasks = List.of(
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
        String expectedDisplay = "\t____________________________________________________________\n"
                + "\tHere are the matching tasks in your list:\n"
                + "\t1.[D][✘] short date format deadline (by: 6/6/2019 0000)\n"
                + "\t2.[D][✘] long date format deadline (by: 6/6/2019 0000)\n"
                + "\t3.[E][✘] short date format event (at: 6/8/2019 1400)\n"
                + "\t4.[E][✘] long date format event (at: 6/8/2019 1400)\n"
                + "\t____________________________________________________________\n";

        command = new FindTaskCommand("date");
        command.execute(actualTasks, ui, store);

        assertFileContents(null);
        assertStdOutContents(expectedDisplay);
        assertExit(false);
    }

    @Test
    void findTaskNonMatchingKeyword() {
        List<Task> actualTasks = List.of(
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
        String expectedDisplay = "\t____________________________________________________________\n"
                + "\tHere are the matching tasks in your list:\n"
                + "\t____________________________________________________________\n";

        command = new FindTaskCommand("x");
        command.execute(actualTasks, ui, store);

        assertFileContents(null);
        assertStdOutContents(expectedDisplay);
        assertExit(false);
    }

    @Test
    void findTaskEmptyTaskList() {
        List<Task> actualTasks = List.of();
        String expectedDisplay = "\t____________________________________________________________\n"
                + "\tHere are the matching tasks in your list:\n"
                + "\t____________________________________________________________\n";

        command = new FindTaskCommand("date");
        command.execute(actualTasks, ui, store);

        assertFileContents(null);
        assertStdOutContents(expectedDisplay);
        assertExit(false);
    }

    @Test
    void findTaskBlankKeyword() {
        List<Task> actualTasks = List.of(
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
        String expectedDisplay = "\t____________________________________________________________\n"
                + "\tHere are the matching tasks in your list:\n"
                + "\t1.[T][✘] not done todo\n"
                + "\t2.[T][✓] done todo\n"
                + "\t3.[D][✘] not done deadline (by: 6/6/2019 0000)\n"
                + "\t4.[D][✓] done deadline (by: 6/6/2019 0000)\n"
                + "\t5.[D][✘] short date format deadline (by: 6/6/2019 0000)\n"
                + "\t6.[D][✘] long date format deadline (by: 6/6/2019 0000)\n"
                + "\t7.[E][✘] not done event (at: 6/8/2019 1400)\n"
                + "\t8.[E][✓] done event (at: 6/8/2019 1400)\n"
                + "\t9.[E][✘] short date format event (at: 6/8/2019 1400)\n"
                + "\t10.[E][✘] long date format event (at: 6/8/2019 1400)\n"
                + "\t____________________________________________________________\n";

        command = new FindTaskCommand("");
        command.execute(actualTasks, ui, store);

        assertFileContents(null);
        assertStdOutContents(expectedDisplay);
        assertExit(false);
    }

}