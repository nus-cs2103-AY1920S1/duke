package utils;

import command.CommandCentre;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private Parser parser;

    ParserTest() {
        parser = new Parser();
        CommandCentre commandCentre = new CommandCentre();
        commandCentre.initializeDummyCommands();
        parser.setCommandCentre(commandCentre);
    }


    @Test
    public void parseEventDetail_withoutKeywordAt_null() {
        parser.getNextAction("event a string that does not contain the keyword");
        String[] taskInfo = parser.parseEventDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseEventDetail_withoutDescription_null() {
        parser.getNextAction("event      /at additionalInfo");
        String[] taskInfo = parser.parseEventDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseEventDetail_withoutAdditionalInfo_null() {
        parser.getNextAction("event event1 /at ");
        String[] taskInfo = parser.parseEventDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseEventDetail_withKeywordButWithoutDescAndInfo_null() {
        parser.getNextAction("event     /at  ");
        String[] taskInfo = parser.parseEventDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseEventDetail_withAllNecessaryInfo_correctTaskInfo() {
        String action = "event ";
        String taskName = "event1";
        String additionalInfo = "20/11/2019 2214";
        String input = action + taskName + " /at " + additionalInfo;
        parser.getNextAction(input);
        String[] expectedTaskInfo = new String[]{taskName, additionalInfo};
        String[] taskInfo = parser.parseEventDetail();

        for (int i = 0; i < taskInfo.length; i++) {
            assertEquals(expectedTaskInfo[i], taskInfo[i]);
        }
    }


    @Test
    public void parseDeadlineDetail_withoutKeywordBy_null() {
        parser.getNextAction("deadline a string that does not contain the keyword");
        String[] taskInfo = parser.parseDeadlineDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseDeadlineDetail_withoutDescription_null() {
        parser.getNextAction("deadline     /by 25/08/2019 2156");
        String[] taskInfo = parser.parseDeadlineDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseDeadlineDetail_withoutAdditionalInfo_null() {
        parser.getNextAction("deadline deadline1 /by ");
        String[] taskInfo = parser.parseDeadlineDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseDeadlineDetail_withKeywordButWithoutDescAndInfo_null() {
        parser.getNextAction("deadline     /by  ");
        String[] taskInfo = parser.parseDeadlineDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseDeadlineDetail_withInvalidDateFormat_null() {
        parser.getNextAction("deadline deadline2 /by 22nd June");
        String[] taskInfo = parser.parseDeadlineDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseDeadlineDetail_withNonexistentDate_null() {
        parser.getNextAction("deadline deadline2 /by 99/99/2019 5400");
        String[] taskInfo = parser.parseDeadlineDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseDeadlineDetail_withAllNecessaryInfo_correctTaskInfo() {
        String action = "deadline ";
        String taskName = "event1";
        String additionalInfo = "25/08/2019 2156";
        String input = action + taskName + " /by " + additionalInfo;
        parser.getNextAction(input);
        String[] expectedTaskInfo = new String[]{taskName, additionalInfo};
        String[] taskInfo = parser.parseDeadlineDetail();

        for (int i = 0; i < taskInfo.length; i++) {
            assertEquals(expectedTaskInfo[i], taskInfo[i]);
        }
    }
}
