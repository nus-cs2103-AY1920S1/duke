package duke;

import duke.exception.*;
import duke.storage.Storage;
import duke.task.TaskList;

public class Duke {
//    private static final String TOP_SEPARATOR =
//            "\t____________________________________________________________\n";
//    private static final String BOTTOM_SEPARATOR =
//            "\t____________________________________________________________";
//    private static final String GREET_MESSAGE =
//            "    Hello! I'm duke.Duke. What can I do for you?\n";
//    private static final String EXIT_MESSAGE =
//            "    Bye. Hope to see you again soon!\n";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
//    private PrettyPrinter pp;

//    public Duke() {
//        this.storage = new Storage("data/duke.txt");
//        this.taskList = storage.readFromDisk(); // leave index 0 empty for clarity
//        this.pp = new PrettyPrinter();
//    }

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = storage.readFromDisk(); // leave index 0 empty for clarity
        } catch (DukeIOException e) {
            ui.showError(e);
            this.taskList = new TaskList(); // only load the taskList if no error
        }
    }

    public void run() {
//        System.out.println(TOP_SEPARATOR + GREET_MESSAGE + BOTTOM_SEPARATOR);
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//            String[] commands = sc.nextLine().split(" ");
//            String[] args = commands.length >= 2
//                                ? Arrays.copyOfRange(commands, 1, commands.length)
//                                : new String[0];
//            try {
//                switch (commands[0]) {
//                case "event":
//                    Task eventTask = taskList.add(TaskFactory.getTask(EVENT, args));
//                    System.out.println(pp.formatAddTask(eventTask, taskList));
//                    storage.writeToDisk(taskList);
//                    break;
//                case "deadline":
//                    Task deadlineTask = taskList.add(TaskFactory.getTask(DEADLINE, args));
//                    System.out.println(pp.formatAddTask(deadlineTask, taskList));
//                    storage.writeToDisk(taskList);
//                    break;
//                case "todo":
//                    if (args.length == 0) {
//                        throw new DukeMissingDescriptionException(
//                                ":'( OOPS!!! The description of a todo cannot be empty.");
//                    }
//                    Task todoTask = taskList.add(TaskFactory.getTask(TODO, args));
//                    System.out.println(pp.formatAddTask(todoTask, taskList));
//                    storage.writeToDisk(taskList);
//                    break;
//                case "done":
//                    int doneIdx = Integer.valueOf(commands[1]);
//                    Task task = taskList.markAsDone(doneIdx);
//                    System.out.println(pp.formatDoneTask(task));
//                    storage.writeToDisk(taskList);
//                    break;
//                case "delete":
//                    int deleteIdx = Integer.valueOf(commands[1]);
//                    Task deletedTask = taskList.delete(deleteIdx);
//                    System.out.println(pp.formatDeleteTask(deletedTask, taskList));
//                    storage.writeToDisk(taskList);
//                    break;
//                case "list":
//                    System.out.println(pp.formatTaskList(taskList));
//                    break;
//                case "bye":
//                    System.out.println(pp.addSeparators(EXIT_MESSAGE));
//                    return;
//                default:
//                    throw new DukeMissingDescriptionException(
//                            ":'( OOPS!!! I'm sorry, but I don't know what that means :-(");
//                }
//            } catch (DukeUnknownInputException
//                    | DukeMissingDescriptionException
//                    | DukeIndexOutOfBoundsException
//                    | DukeIllegalStateException e) {
//                System.out.println(pp.addSeparatorsAddIndent(e.getMessage()));
//            }
//        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
