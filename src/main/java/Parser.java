import java.util.Arrays;
import java.util.Scanner;

public class Parser {

    public static Command parse(String fullCommand) throws InvalidInputDukeException, EmptyTaskDukeException, InvalidTaskDukeException {
        Scanner scanner = new Scanner(fullCommand);
        if (scanner.hasNext()) {
            String toProcess = scanner.next();
            switch (toProcess) {
            case "list":
                return new PrintListCommand();
            case "bye":
                return new ExitCommand();
            case "done":
                return new DoneCommand(scanner.nextInt());
            case "delete":
                return new DeleteCommand(scanner.nextInt());
            case "todo":
            case "deadline":
            case "event":
                return createAddCommand(fullCommand);
            default:
                throw new InvalidInputDukeException();
            }
        } else {
            throw new InvalidInputDukeException();
        }
    }

    private static Command createAddCommand(String fullCommand) throws EmptyTaskDukeException, InvalidTaskDukeException {
        Task taskToAdd = null;
        String checkType[] = Arrays.copyOf(fullCommand.split(" ", 2), 2);
        String typeOfTask = checkType[0];
        String theTask = checkType[1];
        // switch statement for todo, deadline, task
        switch(typeOfTask) {
        case "todo":
            taskToAdd = new ToDo(theTask);
            break;
        case "deadline":
            String taskByWhen[];
            // hardcode exception first
            if (theTask == null) {
                taskByWhen = new String[] {null, null};
            } else {
                taskByWhen = Arrays.copyOf(theTask.split(" /by ", 2), 2);
            }
            String deadlineTask = taskByWhen[0];
            String byWhen = taskByWhen[1];
            taskToAdd = new Deadline(deadlineTask, byWhen);
            break;
        case "event":
            String taskAtTime[];
            if (theTask == null) {
                taskAtTime = new String[] {null, null};
            } else {
                taskAtTime = Arrays.copyOf(theTask.split(" /at ", 2), 2);
            }
            String eventTask = taskAtTime[0];
            String atTime = taskAtTime[1];
            taskToAdd = new Event(eventTask, atTime);
            break;
        default:
            break;
        }
        return new AddCommand(taskToAdd);
    }

}
