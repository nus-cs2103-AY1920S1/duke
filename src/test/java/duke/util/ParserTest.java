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
import java.util.Arrays;


public class ParserTest {
    private final Parser parser = new Parser();
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy HHmm");

    @Test
    void parseTaskString() throws Exception {
        String case1 = "Deadline|0|    submit due |02/12/2019 1800";
        String case2 = "ToDo|0|  hey yo   ";
        String case3 = "Event|1| orbital splashdown|02/12/2019 1800";
        Task deadlineExpected = new Deadline("submit due", FORMATTER.parse("02/12/2019 1800"));
        Task deadlineActual = parser.parseTaskString(case1);
        Task toDoExpected = new ToDo("hey yo");
        Task toDoActual = parser.parseTaskString(case2);
        Task eventExpected = new Event("orbital splashdown", FORMATTER.parse("02/12/2019 1800"));
        eventExpected.toggleState();
        Task eventActual = parser.parseTaskString(case3);
        assertEquals(deadlineExpected.getContent(), deadlineActual.getContent());
        assertEquals(deadlineExpected.getTime(), deadlineActual.getTime());
        assertEquals(deadlineExpected.isDone(), deadlineActual.isDone());
        assertEquals(toDoExpected.getContent(), toDoActual.getContent());
        assertEquals(toDoExpected.isDone(), toDoActual.isDone());
        assertEquals(eventExpected.getContent(), eventActual.getContent());
        assertEquals(eventExpected.getTime(), eventActual.getTime());
        assertEquals(eventExpected.isDone(), eventActual.isDone());
    }

    @Test
    void parseUserInput() throws Exception{
        String inputA = "done 2";
        String inputB = "delete 1";
        String inputC = "list";
        String inputD = "todo make coffee";
        String inputE = "deadline submit due /by 02/12/2019 1800";
        String inputF = "event orbital splashdown /at 02/12/2019 1800";
        String inputG = "   bye    ";
        String inputH = "ls"; //exception
        String inputI = "delete "; //exception
        String inputJ = "todo "; //exception
        String inputK = "deadline submit due /by 02122019 1800"; //exception
        String inputL = "event orbital splashdown at/ 02/12/2019 1800"; //exception
        Command commandExpectedA = new DoneCommand(inputA);
        Command commandExpectedB = new DeleteCommand(inputB);
        Command commandExpectedC = new ListCommand();
        Command commandExpectedD = new AddCommand(new String[]{"todo", "make coffee"});
        Command commandExpectedE = new AddCommand(new String[]{"deadline", "submit due"}, FORMATTER.parse("02/12/2019 1800"));
        Command commandExpectedF = new AddCommand(new String[]{"event", "orbital splashdown"}, FORMATTER.parse("02/12/2019 1800"));
        Command commandExpectedG = new ExitCommand();
        Command commandActualA = parser.parseUserInput(inputA);
        Command commandActualB = parser.parseUserInput(inputB);
        Command commandActualC = parser.parseUserInput(inputC);
        Command commandActualD = parser.parseUserInput(inputD);
        Command commandActualE = parser.parseUserInput(inputE);
        Command commandActualF = parser.parseUserInput(inputF);
        Command commandActualG = parser.parseUserInput(inputG);

        AddCommand test1 = (AddCommand) commandActualF;
        AddCommand test2 = (AddCommand) commandExpectedF;

        System.out.println(Arrays.toString(test1.getParsedString()));
        System.out.println(Arrays.toString(test2.getParsedString()));
        System.out.println(test1.getDate());
        System.out.println(test2.getDate());
        System.out.println(commandActualE.equals(commandExpectedE));

        /*
        assertEquals(commandExpectedA, (commandActualA));
        assertEquals(commandExpectedB, commandActualB);
        assertEquals(commandExpectedC, commandActualC);
        assertEquals(commandExpectedD, commandActualD);
        assertEquals(commandExpectedE, commandActualE);
        assertEquals(commandExpectedF, commandActualF);
        assertEquals(commandExpectedG, commandActualG);
        */

        assertTrue(commandExpectedA.equals(commandActualA));
        assertTrue(commandExpectedB.equals(commandActualB));
        assertTrue(commandExpectedC.equals(commandActualC));
        assertTrue(commandExpectedD.equals(commandActualD));
        assertTrue(commandExpectedE.equals(commandActualE));
        assertTrue(commandExpectedF.equals(commandActualF));
        assertTrue(commandExpectedG.equals(commandActualG));

        Exception exceptionH = assertThrows(DukeException.class, () ->
                parser.parseUserInput(inputH));
        assertEquals("\u2639 OOPS!!! I do not understand what did you just typed.", exceptionH.getMessage());

        Exception exceptionI = assertThrows(DukeException.class, () ->
                parser.parseUserInput(inputI));
        assertEquals("\u2639 OOPS!!! Please input a number for delete.", exceptionI.getMessage());

        Exception exceptionJ = assertThrows(DukeException.class, () ->
                parser.parseUserInput(inputJ));
        assertEquals("\u2639 OOPS!!! The description of a task cannot be empty.", exceptionJ.getMessage());

        Exception exceptionK = assertThrows(ParseException.class, () ->
                parser.parseUserInput(inputK));
        assertEquals("Unparseable date: \"02122019 1800\"", exceptionK.getMessage());

        Exception exceptionL = assertThrows(DukeException.class, () ->
                parser.parseUserInput(inputL));
        assertEquals("\u2639 OOPS!!! You need a /at to separate out the date time for this task.", exceptionL.getMessage());
    }



}
