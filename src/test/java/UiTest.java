import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class UiTest {
    private static final String HORIZONTAL_LINE = "\t" + "_".repeat(60) + "\n";
    private final OutputStream mockedSysOut = new ByteArrayOutputStream();
    private final OutputStream realSysOut = System.out;
    private Ui ui;

    static List<Arguments> getTasksWithExpectedStringDisplays() {
        return List.of(
                Arguments.of(
                        new Todo("not done todo", false),
                        "[T][✘] not done todo"
                ),
                Arguments.of(
                        new Todo("done todo", true),
                        "[T][✓] done todo"
                ),
                Arguments.of(
                        new Deadline("not done deadline", false, LocalDateTime.of(2019, 6, 6, 0, 0)),
                        "[D][✘] not done deadline (by: 6/6/2019 0000)"
                ),
                Arguments.of(
                        new Deadline("done deadline", true, LocalDateTime.of(2019, 6, 6, 0, 0)),
                        "[D][✓] done deadline (by: 6/6/2019 0000)"
                ),
                Arguments.of(
                        new Deadline("date format deadline", false, LocalDateTime.of(2019, 6, 6, 0, 0)),
                        "[D][✘] date format deadline (by: 6/6/2019 0000)"
                ),
                Arguments.of(
                        new Event("not done event", false, LocalDateTime.of(2019, 8, 6, 14, 0)),
                        "[E][✘] not done event (at: 6/8/2019 1400)"
                ),
                Arguments.of(
                        new Event("done event", true, LocalDateTime.of(2019, 8, 6, 14, 0)),
                        "[E][✓] done event (at: 6/8/2019 1400)"
                ),
                Arguments.of(
                        new Event("date format event", false, LocalDateTime.of(2019, 8, 6, 14, 0)),
                        "[E][✘] date format event (at: 6/8/2019 1400)"
                )
        );
    }

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(mockedSysOut));
        ui = new Ui();
    }

    @AfterEach
    void tearDown() {
        System.setOut(new PrintStream(realSysOut));
    }

    @Test
    void nextLine() {
    }

    @Test
    void printHorizontalLine() {
        ui.printHorizontalLine();
        Assertions.assertEquals(HORIZONTAL_LINE, mockedSysOut.toString());
    }

    @Test
    void println() {
        ui.println();
        Assertions.assertEquals("\n", mockedSysOut.toString());
    }

    @Test
    void printlnContent() {
        ui.println("Here's some text");
        Assertions.assertEquals("\tHere's some text\n", mockedSysOut.toString());
    }

    @Test
    void printBlock() {
        String expected = HORIZONTAL_LINE
                + "\tHere's some text\n"
                + HORIZONTAL_LINE;
        ui.printBlock("Here's some text");
        Assertions.assertEquals(expected, mockedSysOut.toString());
    }

    @Test
    void stringJoinerContent() {
        String expected = "Here's some text";
        String actual = Ui.createStringJoiner("Here's some text").toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void stringJoinerMultilineContent() {
        String expected = "Here's some text\nHere's more text";
        String actual = Ui.createStringJoiner("Here's some text").add("Here's more text").toString();
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("getTasksWithExpectedStringDisplays")
    void formatTask(Task t, String expectedTaskString) {
        Assertions.assertEquals(expectedTaskString, Ui.formatTask(t));
    }

    @Test
    void displayWelcome() {
        String expected = HORIZONTAL_LINE
                + "\tHello! I'm Duke"
                + "\n\tWhat can I do for you?\n"
                + HORIZONTAL_LINE
                + "\n";
        ui.displayWelcome();
        Assertions.assertEquals(expected, mockedSysOut.toString());
    }

    @Test
    void displayLoadingError() {
        String expected = HORIZONTAL_LINE
                + "\t ☹ OOPS!!! Couldn't load previously saved Tasks."
                + "\n\tDuke will start with an empty Task list."
                + "\n\t"
                + "\n\tMore details: how many layers of abstraction are you on?"
                + "\n" + HORIZONTAL_LINE
                + "\n";
        Throwable exc = new OutOfMemoryError("how many layers of abstraction are you on?");
        ui.displayLoadingError(exc);
        Assertions.assertEquals(expected, mockedSysOut.toString());
    }

    @Test
    void displayLoadingErrorWithCause() {
        String expected = HORIZONTAL_LINE
                + "\t ☹ OOPS!!! Couldn't load previously saved Tasks."
                + "\n\tDuke will start with an empty Task list."
                + "\n\t"
                + "\n\tMore details: watch this"
                + "\n\thow many layers of abstraction are you on?"
                + "\n" + HORIZONTAL_LINE
                + "\n";
        Throwable cause = new OutOfMemoryError("how many layers of abstraction are you on?");
        Throwable exc = new RuntimeException("watch this", cause);
        ui.displayLoadingError(exc);
        Assertions.assertEquals(expected, mockedSysOut.toString());
    }


    @Test
    void displayErrorException() {
        String expected = HORIZONTAL_LINE
                + "\t ☹ OOPS!!! egads!"
                + "\n" + HORIZONTAL_LINE
                + "\n";
        DukeException exc = new UnknownCommandException("egads!");
        ui.displayError(exc);
        Assertions.assertEquals(expected, mockedSysOut.toString());
    }

    @Test
    void displayErrorString() {
        String expected = HORIZONTAL_LINE
                + "\t ☹ OOPS!!! egads!"
                + "\n" + HORIZONTAL_LINE
                + "\n";
        ui.displayError("egads!");
        Assertions.assertEquals(expected, mockedSysOut.toString());
    }

    @Test
    void displayTasks() {
        String expected = HORIZONTAL_LINE;
        List<Task> tasks = new ArrayList<>();
        int idx = 1;
        for (Arguments argObj : getTasksWithExpectedStringDisplays()) {
            Object[] args = argObj.get();
            Task t = (Task) args[0];
            String expectedTaskString = (String) args[1];

            tasks.add(t);
            expected += String.format("\t%d.%s\n", idx, expectedTaskString);
            idx++;
        }
        expected += HORIZONTAL_LINE;

        ui.displayTasks(tasks);
        Assertions.assertEquals(expected, mockedSysOut.toString());
    }

    @Test
    void displayTasksWithTitle() {
        String expected = HORIZONTAL_LINE
                + "\tHere's a title!\n";
        List<Task> tasks = new ArrayList<>();
        int idx = 1;
        for (Arguments argObj : getTasksWithExpectedStringDisplays()) {
            Object[] args = argObj.get();
            Task t = (Task) args[0];
            String expectedTaskString = (String) args[1];

            tasks.add(t);
            expected += String.format("\t%d.%s\n", idx, expectedTaskString);
            idx++;
        }
        expected += HORIZONTAL_LINE;

        ui.displayTasks("Here's a title!", tasks);
        Assertions.assertEquals(expected, mockedSysOut.toString());
    }

    @ParameterizedTest
    @MethodSource("getTasksWithExpectedStringDisplays")
    void displaySuccessfullyDoneTask(Task t, String expectedTaskString) {
        String expected = HORIZONTAL_LINE
                + "\tHere's a title!\n"
                + "\t  " + expectedTaskString + "\n"
                + HORIZONTAL_LINE;

        ui.displaySuccessfullyDoneTask("Here's a title!", t);
        Assertions.assertEquals(expected, mockedSysOut.toString());
    }

    @ParameterizedTest
    @MethodSource("getTasksWithExpectedStringDisplays")
    void displaySuccessfullyRemovedTask(Task t, String expectedTaskString) {
        String expected = HORIZONTAL_LINE
                + "\tHere's a title!\n"
                + "\t  " + expectedTaskString + "\n"
                + "\tNow you have 100 tasks in the list.\n"
                + HORIZONTAL_LINE;

        ui.displaySuccessfullyRemovedTask("Here's a title!", t, 100);
        Assertions.assertEquals(expected, mockedSysOut.toString());
    }

    @ParameterizedTest
    @MethodSource("getTasksWithExpectedStringDisplays")
    void displaySuccessfullyAddedTask(Task t, String expectedTaskString) {
        String expected = HORIZONTAL_LINE
                + "\tHere's a title!\n"
                + "\t  " + expectedTaskString + "\n"
                + "\tNow you have 100 tasks in the list.\n"
                + HORIZONTAL_LINE + "\n";

        ui.displaySuccessfullyAddedTask("Here's a title!", t, 100);
        Assertions.assertEquals(expected, mockedSysOut.toString());
    }
}