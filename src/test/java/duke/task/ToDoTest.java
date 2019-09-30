import duke.task.ToDo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    void testToSaveFormat() {
        String test1String = "asfiuab9r3h83he9fudfeuifenifbewfiewbreirubewri";
        String test2String = "for3nrSOFBN#IR(FNOIFNjfb roOSNSDOISANOIRNRSDD";
        String test3String = "#UIN(#NEFUR(#*H#(*R(BN(FIKNFIFNEWIUEWBOFBWFOEWUF";
        ArrayList<String> testCasesString = new ArrayList<String>();
        testCasesString.add(test1String);
        testCasesString.add(test2String);
        testCasesString.add(test3String);
        ToDo test1 = new ToDo(test1String);
        ToDo test2 = new ToDo(test2String);
        ToDo test3 = new ToDo(test3String);
        test3.taskComplete();
        ArrayList<ToDo> testCases = new ArrayList<ToDo>();
        testCases.add(test1);
        testCases.add(test2);
        testCases.add(test3);
        for (int i = 0; i < testCases.size(); i ++) {
            ToDo testCase = testCases.get(i);
            String description =  testCasesString.get(i);
            String expected = "T | ";
            if (i == 2) {
                expected += "1";
            } else {
                expected += "0";
            }
            expected += " | " + description;
            assertEquals(expected, testCase.toSaveFormat());
        }

    }

    @Test
    void testToString() {
        String test1String = "asfiuab9r3h83he9fudfeuifenifbewfiewbreirubewri";
        String test2String = "for3nrSOFBN#IR(FNOIFNjfb roOSNSDOISANOIRNRSDD";
        String test3String = "#UIN(#NEFUR(#*H#(*R(BN(FIKNFIFNEWIUEWBOFBWFOEWUF";
        ArrayList<String> testCasesString = new ArrayList<String>();
        testCasesString.add(test1String);
        testCasesString.add(test2String);
        testCasesString.add(test3String);
        ToDo test1 = new ToDo(test1String);
        ToDo test2 = new ToDo(test2String);
        ToDo test3 = new ToDo(test3String);
        test3.taskComplete();
        ArrayList<ToDo> testCases = new ArrayList<ToDo>();
        testCases.add(test1);
        testCases.add(test2);
        testCases.add(test3);
        for (int i = 0; i < testCases.size(); i ++) {
            ToDo testCase = testCases.get(i);
            String description = testCasesString.get(i);
            String expected = "[T]";
            if (i == 2) {
                expected += "Complete " + testCasesString.get(i);
            } else {
                expected += "Incomplete " + testCasesString.get(i);
            }
            assertEquals(expected, testCase.toString());
        }
    }
}
