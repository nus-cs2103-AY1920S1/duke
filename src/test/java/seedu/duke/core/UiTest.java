package seedu.duke.core;

import org.junit.jupiter.api.Test;
import seedu.duke.model.dto.Deadline;
import seedu.duke.model.dto.Task;
import seedu.duke.model.dto.Todo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void displayTask() throws ParseException {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("buy bread"));
        taskList.add(new Deadline("submit CS2101 report", "20/09/19 23:59"));

        Ui ui = new Ui();
        assertEquals("", ui.displayTask("", taskList, 2));
    }

    //Discontinued due to UTF encoding issues.
    /*
    @Test
    public void displayList() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("buy bread"));
        Ui ui = new Ui();
        assertEquals("1.[T][✘] buy bread", ui.displayList("", taskList));
    }
    */
}
