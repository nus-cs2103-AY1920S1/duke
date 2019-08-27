import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
public class ModifyTaskListTest {

    @Test
    public void modifyTest() throws IOException {

        ArrayList<Task> taskList = new ArrayList<>(100);
        InputParser inputParser = new InputParser(taskList);
        String input1 = "todo finish JUnit testing";
        String input2 = "todo to-be-removed";
        String input3 = "random nonsense";
        String input4 = "deadline finish Project /by 20/04/2019";
        String input5 = "event game event /at 23-DEC-2020";
        String input6 = "bye";
        String input7 = "deadline will never surface /by 22/11/2039";
        String input8 = "done 3";
        String input9 = "delete 5";
        String input10 = "delete 2";
        String expectedOutput1 = "[T][✘] finish JUnit testing";
        String expectedOutput2 = "[D][✓] finish Project (by: Sat Apr 20 00:00:00 SGT 2019)";
        String expectedOutput3 = "[E][✘] game event (at: Wed Dec 23 00:00:00 SGT 2020)";

        inputParser.actionDeterminer(input1);
        inputParser.actionDeterminer(input2);
        inputParser.actionDeterminer(input3);
        inputParser.actionDeterminer(input4);
        inputParser.actionDeterminer(input5);
        inputParser.actionDeterminer(input6);
        inputParser.actionDeterminer(input7);
        inputParser.actionDeterminer(input8);
        inputParser.actionDeterminer(input9);
        inputParser.actionDeterminer(input10);

        assertEquals(expectedOutput1, taskList.get(0).toString());
        assertEquals(expectedOutput2, taskList.get(1).toString());
        assertEquals(expectedOutput3, taskList.get(2).toString());
    }
}
