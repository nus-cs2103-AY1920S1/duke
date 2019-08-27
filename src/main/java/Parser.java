import java.util.ArrayList;

public class Parser {

    public static void parse(String command, TaskList tasks, Storage storage, Ui ui) {
        String[] currArray = command.split("\\s+");
        try {
            checkValidCommand(currArray[0]);
            checkEmptyDesc(currArray);
            if (currArray[0].equals("bye")) {
                ui.showBye();
                System.exit(0);
            } else if (currArray[0].equals("list")) {
                tasks.printTasks();
            } else if (currArray[0].equals("done")) {
                tasks.printDone(command, storage);
            } else if (currArray[0].equals("delete")) {
                tasks.handleDelete(command, storage);
            } else {
                tasks.handleTask(command, storage);
            }
        } catch (DukeException error) {
            System.err.println(error.getMessage());
        }
    }

    public static void checkValidCommand(String command) throws DukeException {
        ArrayList<String> validCommands = new ArrayList<>();
        validCommands.add("todo");
        validCommands.add("deadline");
        validCommands.add("event");
        validCommands.add("done");
        validCommands.add("list");
        validCommands.add("bye");
        validCommands.add("delete");
        if (!validCommands.contains(command)) {
            throw new DukeException("    ____________________________________________________________\n" + "     OOPS!!! I'm sorry, but I don't know what that means :-(\n" + "    ____________________________________________________________\n" + "\n");
        }
    }

    public static void checkEmptyDesc(String[] currArray) throws DukeException {
        ArrayList<String> taskTypes = new ArrayList<>();
        taskTypes.add("todo");
        taskTypes.add("deadline");
        taskTypes.add("event");
        if (taskTypes.contains(currArray[0]) && currArray.length == 1) {
            throw new DukeException("    ____________________________________________________________\n" + "     OOPS!!! The description of a " + currArray[0] + " cannot be empty.\n" + "    ____________________________________________________________\n" +"\n");
        }
    }
}