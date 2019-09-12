package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ParserTest {
    private final Parser parser = new Parser();
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy HHmm");

    @Test
    void parseTaskString() throws Exception {
        String case1 = "Deadline|0|    submit due |02/12/2019 1800";
        Task deadlineExpected = new Deadline("submit due", FORMATTER.parse("02/12/2019 1800"));
        Task deadlineActual = parser.parseTaskString(case1);
        assertEquals(deadlineExpected.getContent(), deadlineActual.getContent());
        assertEquals(deadlineExpected.getTime(), deadlineActual.getTime());
        assertEquals(deadlineExpected.isDone(), deadlineActual.isDone());

        String case2 = "ToDo|0|  hey yo   ";
        Task toDoExpected = new ToDo("hey yo");
        Task toDoActual = parser.parseTaskString(case2);
        assertEquals(toDoExpected.getContent(), toDoActual.getContent());
        assertEquals(toDoExpected.isDone(), toDoActual.isDone());

        String case3 = "Event|1| orbital splashdown|02/12/2019 1800";
        Task eventExpected = new Event("orbital splashdown", FORMATTER.parse("02/12/2019 1800"));
        eventExpected.toggleState();
        Task eventActual = parser.parseTaskString(case3);
        assertEquals(eventExpected.getContent(), eventActual.getContent());
        assertEquals(eventExpected.getTime(), eventActual.getTime());
        assertEquals(eventExpected.isDone(), eventActual.isDone());
    }

    @Test
    void parseUserInput() throws Exception {
        String inputA = "done 2";
        Command commandExpectedA = new DoneCommand("2");
        Command commandActualA = parser.parseUserInput(inputA);
        System.out.println(commandActualA.getCommand());
        System.out.println(commandExpectedA.getCommand());
        assertTrue(commandExpectedA.equals(commandActualA));

        String inputB = "delete 1";
        Command commandExpectedB = new DeleteCommand("1");
        Command commandActualB = parser.parseUserInput(inputB);
        assertTrue(commandExpectedB.equals(commandActualB));

        String inputC = "list";
        Command commandExpectedC = new ListCommand();
        Command commandActualC = parser.parseUserInput(inputC);
        assertTrue(commandExpectedC.equals(commandActualC));

        String inputD = "todo make coffee";
        Command commandExpectedD = new AddCommand(new String[]{"todo", "make coffee"});
        Command commandActualD = parser.parseUserInput(inputD);
        assertTrue(commandExpectedD.equals(commandActualD));

        String inputE = "deadline submit due /by 02/12/2019 1800";
        Command commandExpectedE = new AddCommand(new String[]{"deadline", "submit due"},
                FORMATTER.parse("02/12/2019 1800"));
        Command commandActualE = parser.parseUserInput(inputE);
        assertTrue(commandExpectedE.equals(commandActualE));


        String inputF = "event orbital splashdown /at 02/12/2019 1800";
        Command commandExpectedF = new AddCommand(new String[]{"event", "orbital splashdown"},
                FORMATTER.parse("02/12/2019 1800"));
        Command commandActualF = parser.parseUserInput(inputF);
        assertTrue(commandExpectedF.equals(commandActualF));

        String inputG = "   bye    ";
        Command commandExpectedG = new ExitCommand();
        Command commandActualG = parser.parseUserInput(inputG);
        assertTrue(commandExpectedG.equals(commandActualG));

        String inputH = "ls"; //exception
        Exception exceptionH = assertThrows(DukeException.class, () ->
                parser.parseUserInput(inputH));
        assertEquals("☹ OOPS!!! I do not understand what did you just typed.", exceptionH.getMessage());

        String inputI = "delete "; //exception
        Exception exceptionI = assertThrows(DukeException.class, () ->
                parser.parseUserInput(inputI));
        assertEquals("☹ OOPS!!! Please input a number after delete and nothing else.", exceptionI.getMessage());

        String inputJ = "todo "; //exception
        Exception exceptionJ = assertThrows(DukeException.class, () ->
                parser.parseUserInput(inputJ));
        assertEquals("☹ OOPS!!! The description of a task cannot be empty.", exceptionJ.getMessage());

        String inputK = "deadline submit due /by 02122019 1800"; //exception
        Exception exceptionK = assertThrows(ParseException.class, () ->
                parser.parseUserInput(inputK));
        assertEquals("Unparseable date: \"02122019 1800\"", exceptionK.getMessage());

        String inputL = "event orbital splashdown at/ 02/12/2019 1800"; //exception
        Exception exceptionL = assertThrows(DukeException.class, () ->
                parser.parseUserInput(inputL));
        assertEquals("☹ OOPS!!! You need a /at to separate out the date time for this task.", exceptionL.getMessage());
    }
}
