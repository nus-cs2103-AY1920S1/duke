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
        String testCase1 = "{ type: deadline, done: false, description: 2103ip, time: 01/12/1997 0000 }";
        Map<StorageKey, String> testCase1Expected = new EnumMap<StorageKey, String>(StorageKey.class);
        testCase1Expected.put(StorageKey.type, "deadline");
        testCase1Expected.put(StorageKey.done, "false");
        testCase1Expected.put(StorageKey.description, "2103ip");
        testCase1Expected.put(StorageKey.time, "01/12/1997 0000");

        String testCase2 = "{ type: todo, done: true, description: --129n8sk }";
        Map<StorageKey, String> testCase2Expected = new EnumMap<StorageKey, String>(StorageKey.class);
        testCase2Expected.put(StorageKey.type, "todo");
        testCase2Expected.put(StorageKey.done, "true");
        testCase2Expected.put(StorageKey.description, "--129n8sk");

        String testCase3 = "}";
        String testCase4 = "{ type: todo, invalidkey: true, description: placeholder description }";
        String testCase5 = "{ type: todo, done: true, description: --129n8sk";

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
