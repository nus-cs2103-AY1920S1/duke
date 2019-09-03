import java.util.DuplicateFormatFlagsException;

public class Parser {

    public static Command parse (String command) throws DukeException {

        String[] words = command.split(" ", 2);

        Task task = null;
        int index;

        switch (words[0]) {
        case "todo":
            task = Parser.parsesAdd(command, TaskType.TODO);
            return new AddCommand(task);
        case "deadline":
            task = Parser.parsesAdd(command, TaskType.DEADLINE);
            return new AddCommand(task);
        case "event":
            task = Parser.parsesAdd(command, TaskType.EVENT);
            return new AddCommand(task);
        case "done":
            index = Parser.parsesDone(command);
            return new DoneCommand(index);
        case "delete":
            index = Parser.parsesDone(command);
            return new DeleteCommand(index);
        }

        switch (command) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        }

        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

    }

    public static int parsesDone (String command) throws DukeException {

        String[] words = command.split(" ", 2);

        if (words.length == 1) {
            throw DukeException.emptyDescription();
        }

        if (words[1].isBlank()) {
            throw DukeException.emptyDescription();
        }

        int index = Integer.valueOf(words[1]) - 1;

        return index;

    }

    public static Task parsesAdd (String command, TaskType taskType) throws DukeException {

        String[] words = command.split(" ", 2);

        if (words.length == 1) {
            throw DukeException.emptyDescription();
        }

        Task task = null;

        String description;

        switch (taskType) {
        case TODO:
            description = words[1];

            if (description.isBlank()) {
                throw DukeException.emptyDescription();
            }

            task = new Todo(description);

            break;

        case DEADLINE:
            String[] split = words[1].split(" /by ", 2);

            if (split.length == 1) {
                throw DukeException.emptyDescription();
            }

            description = split[0];
            String by = split[1];

            if (description.isBlank() || by.isBlank()) {
                throw DukeException.emptyDescription();
            }

            task = new Deadline(description, by);

            break;

        case EVENT:
            split = words[1].split(" /at ", 2);

            if (split.length == 1) {
                throw DukeException.emptyDescription();
            }

            description = split[0];
            String at = split[1];

            if (description.isBlank() || at.isBlank()) {
                throw DukeException.emptyDescription();
            }

            task = new Event(description, at);

            break;

        }

        return task;

    }

    public static int parsesDelete (String command) throws DukeException {
        String[] words = command.split(" ", 2);

        if (words.length == 1)  {
            throw DukeException.emptyDescription();
        }

        if (words[1].isBlank()) {
            throw DukeException.emptyDescription();
        }

        System.out.println("Noted. I've removed this task:");

        int index = Integer.valueOf(words[1]) - 1;

        return index;
    }

}
