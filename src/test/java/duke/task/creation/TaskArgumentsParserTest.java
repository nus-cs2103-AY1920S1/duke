package duke.task.creation;

import error.datetime.UnknownDateTimeException;
import error.task.TaskArgumentsException;
import error.task.TaskCreationException;
import org.junit.jupiter.api.Test;
import util.time.DateTime;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.*;

class TaskArgumentsParserTest {

    @Test
    void parse() throws TaskArgumentsException, UnknownDateTimeException {
        TaskArgumentsParser parserA = new TaskArgumentsParser("hello12345 fri to mon java", 2);
        TaskArguments argumentsA = parserA.parse();

        assertEquals(argumentsA.getDetails(), "hello12345");
        assertEquals(argumentsA.getDateTimes().size(), 2);

        LocalDateTime firstDateTime = argumentsA.getDateTimes().get(0);
        LocalDateTime secondDateTime = argumentsA.getDateTimes().get(1);

        assertEquals(firstDateTime, LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0));

        assertEquals(secondDateTime, LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0));


        TaskArgumentsParser parserB = new TaskArgumentsParser("hello12345 23/02/2020 0900", 1);
        TaskArguments argumentsB = parserB.parse();

        assertEquals(argumentsB.getDetails(), "hello12345");
        assertEquals(argumentsB.getDateTimes().size(), 1);
        assertEquals(argumentsB.getDateTimes().get(0), DateTime.parse("23/02/2020 0900"));

        TaskArgumentsParser parserC = new TaskArgumentsParser("abcde", 3);
        assertThrows(TaskArgumentsException.class, parserC::parse);

        TaskArgumentsParser parserD = new TaskArgumentsParser("abcde", 2);
        assertThrows(UnknownDateTimeException.class, parserD::parse);
    }
}