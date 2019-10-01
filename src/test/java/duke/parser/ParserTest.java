import duke.parser.Parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void testParserDoneCommand() {
        Parser parser = new Parser();
        String[] test1 = {"oeeowirkejwokwefokwenfowe", "2", "er980r3jofnb3irAO"};
        String[] test2 = {"09j2e0inwqi3h", "1232", "n340239rjejfiwbow", "201j921jfklffwe", "1208hj10inef"};
        String[] test3 = {"2904912jffdasd", "22", "sjndq0irh3qr0rn", "qj0rj9qr0ods"};
        ArrayList<String[]> testCases = new ArrayList<String[]>();
        testCases.add(test1);
        testCases.add(test2);
        testCases.add(test3);
        for (String[] testCase : testCases) {
            assertEquals(Integer.parseInt(testCase[1]) - 1, parser.parseDoneCommand(testCase));
        }
    }
}
