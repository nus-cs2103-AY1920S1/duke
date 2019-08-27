package duke.storage;

import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StorageParserTest {

    @Test
    void parseJsonLine() {
        final String testCase1 = "{ type: deadline, done: false, description: 2103ip, time: 01/12/1997 0000 }";
        Map<StorageKey, String> testCase1Expected = new EnumMap<StorageKey, String>(StorageKey.class);
        testCase1Expected.put(StorageKey.TYPE, "deadline");
        testCase1Expected.put(StorageKey.DONE, "false");
        testCase1Expected.put(StorageKey.DESCRIPTION, "2103ip");
        testCase1Expected.put(StorageKey.TIME, "01/12/1997 0000");

        final String testCase2 = "{ type: todo, done: true, description: --129n8sk }";
        Map<StorageKey, String> testCase2Expected = new EnumMap<StorageKey, String>(StorageKey.class);
        testCase2Expected.put(StorageKey.TYPE, "todo");
        testCase2Expected.put(StorageKey.DONE, "true");
        testCase2Expected.put(StorageKey.DESCRIPTION, "--129n8sk");

        final String testCase3 = "}";
        final String testCase4 = "{ type: todo, invalidkey: true, description: placeholder description }";
        final String testCase5 = "{ type: todo, done: true, description: --129n8sk";

        assertAll("Valid Inputs",
                () -> parseJsonLine_success(testCase1, testCase1Expected),
                () -> parseJsonLine_success(testCase2, testCase2Expected));

        assertAll("Invalid Inputs",
                () -> parseJsonLine_dukeTaskFileParseException_thrown(testCase3),
                () -> parseJsonLine_dukeTaskFileParseException_thrown(testCase4),
                () -> parseJsonLine_dukeTaskFileParseException_thrown(testCase5));
    }

    private void parseJsonLine_success(String jsonLine, Map<StorageKey, String> expectedOutput)
        throws DukeTaskFileParseException {
        assertEquals(
                expectedOutput,
                StorageParser.parseJsonLine(jsonLine));
    }

    private void parseJsonLine_dukeTaskFileParseException_thrown(String jsonLine) {
        assertThrows(
                DukeTaskFileParseException.class,
                () -> StorageParser.parseJsonLine(jsonLine));
    }
}
