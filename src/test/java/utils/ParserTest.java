package utils;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private Parser parser = new Parser();

    @Test
    public void parseEventDetail_withoutKeywordAt_null() {
        parser.setScanner(new Scanner("a string that does not contain the keyword"));
        String[] taskInfo = parser.parseEventDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseEventDetail_withoutDescription_null() {
        parser.setScanner(new Scanner("     /at additionalInfo"));
        String[] taskInfo = parser.parseEventDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseEventDetail_withoutAdditionalInfo_null() {
        parser.setScanner(new Scanner("event1 /at "));
        String[] taskInfo = parser.parseEventDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseEventDetail_withKeywordButWithoutDescAndInfo_null() {
        parser.setScanner(new Scanner("     /at  "));
        String[] taskInfo = parser.parseEventDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseEventDetail_withAllNecessaryInfo_correctInfo() {
        String taskName = "event1";
        String additionalInfo = "nus";
        String input = taskName + " /at " + additionalInfo;
        parser.setScanner(new Scanner(input));
        String[] expectedTaskInfo = new String[]{taskName, additionalInfo};
        String[] taskInfo = parser.parseEventDetail();

        for (int i = 0; i < taskInfo.length; i++) {
            assertEquals(expectedTaskInfo[i], taskInfo[i]);
        }
    }


    @Test
    public void parseDeadlineDetail_withoutKeywordBy_null() {
        parser.setScanner(new Scanner("a string that does not contain the keyword"));
        String[] taskInfo = parser.parseDeadlineDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseDeadlineDetail_withoutDescription_null() {
        parser.setScanner(new Scanner("     /by 25/08/2019 2156"));
        String[] taskInfo = parser.parseDeadlineDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseDeadlineDetail_withoutAdditionalInfo_null() {
        parser.setScanner(new Scanner("deadline1 /by "));
        String[] taskInfo = parser.parseDeadlineDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseDeadlineDetail_withKeywordButWithoutDescAndInfo_null() {
        parser.setScanner(new Scanner("     /by  "));
        String[] taskInfo = parser.parseDeadlineDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseDeadlineDetail_withInvalidDateFormat_null() {
        parser.setScanner(new Scanner("deadline2 /by 22nd June"));
        String[] taskInfo = parser.parseDeadlineDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseDeadlineDetail_withNonexistentDate_null() {
        parser.setScanner(new Scanner("deadline2 /by 99/99/2019 5400"));
        String[] taskInfo = parser.parseDeadlineDetail();
        assertEquals(null, taskInfo);
    }

    @Test
    public void parseDeadlineDetail_withAllNecessaryInfo_correctInfo() {
        String taskName = "event1";
        String additionalInfo = "25/08/2019 2156";
        String input = taskName + " /by " + additionalInfo;
        parser.setScanner(new Scanner(input));
        String[] expectedTaskInfo = new String[]{taskName, additionalInfo};
        String[] taskInfo = parser.parseDeadlineDetail();

        for (int i = 0; i < taskInfo.length; i++) {
            assertEquals(expectedTaskInfo[i], taskInfo[i]);
        }
    }
}
