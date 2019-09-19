package duke.task;

import duke.command.DukeException;
import duke.command.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    @Test
    void testToString() {
        try {
            assertEquals("[T][-] 2103 project work",
                    new ToDo(new Parser("TODO 2103 project work")).toString());
            ToDo toDo = new ToDo(new Parser("todo"));
        } catch (DukeException e) {
            assertEquals(e.getMessage(), " :( OOPS!!! The description of todos cannot be empty.");
        }
    }
}