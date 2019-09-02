import java.io.IOException;

public class Parser {

    public static TaskType returnsTaskType (String command) throws DukeException {

        String[] words = command.split(" ", 2);


        switch (words[0]) {
        case "todo":
            return TaskType.TODO;
        case "deadline":
            return TaskType.DEADLINE;
        case "event":
            return TaskType.EVENT;
        case "done":
            return TaskType.DONE;
        case "delete":
            return TaskType.DELETE;
        }

        switch (command) {
        case "list":
            return Bot.TaskType.LIST;
        case "bye":
            return Bot.TaskType.BYE;
        }

        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

    }
}
