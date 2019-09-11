import java.io.IOException;

/**
 * Represents a command.
 */
public class Command {

    /**
     * Default constructor for Command class.
     */
    public Command() {
    }

    /**
     * Executes the commands in the taskList and updates text file.
     * @param tasks TaskList object to store list of tasks.
     * @param ui Ui object to read the command.
     * @param storage Storage object to store and update text file.
     * @throws DukeException
     * @throws IOException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, String userInput) throws DukeException, IOException {
        storage.loadFile();
        String input = userInput;
        String response = "";
        Parser parser = new Parser();
        outerLoop:
        while (input != null) {
            String[] words = input.split(" ");
            switch (parser.parse(input)) {
            case TODO:
                if (input.length() == 4) {
                    ui.throwErrorMessage("todo");
                } else {
                    Task todo = new Todo(input.substring(5));
                    tasks.add(todo);
                    response = ui.printAddTask(todo, tasks.size());
                }
                break outerLoop;
            case EVENT:
                String[] parts = input.split("/at", 2);
                String part1 = parts[0];
                String part2 = parts[1];
                if (input.length() == 5) {
                    response = ui.throwErrorMessage("event");
                } else if (part2.matches(" \\d{2}/\\d{2}/\\d{4} \\d{4}")) {
                    int indexOfSlash = input.indexOf("/");
                    Task eventTask = new Event(input.substring(6, indexOfSlash - 1), input.substring(indexOfSlash + 4));
                    tasks.add(eventTask);
                    response = ui.printAddTask(eventTask, tasks.size());
                } else {
                    response = ui.printEventFormat();
                }
                break outerLoop;
            case DEADLINE:
                String[] segments = input.split("/by", 2);
                String segment1 = segments[0];
                String segment2 = segments[1];
                if (input.length() == 8) {
                    ui.throwErrorMessage("deadline");
                } else if (segment2.matches(" \\d{2}/\\d{2}/\\d{4} \\d{4}")) {
                    int indexOfSlash2 = input.indexOf("/");
                    Task deadlineTask = new Deadline(input.substring(9, indexOfSlash2 - 1), input.substring(indexOfSlash2 + 4));
                    tasks.add(deadlineTask);
                    response = ui.printAddTask(deadlineTask, tasks.size());
                } else {
                    response = ui.printDeadlineFormat();
                }
                break outerLoop;
            case LIST:
                response = ui.printList(tasks);
                break outerLoop;
            case DELETE:
                int taskNum = Integer.parseInt(input.substring(7));
                Task deletedTask = tasks.get(taskNum - 1);
                response = ui.printDelete(deletedTask, tasks.size());
                tasks.remove(taskNum - 1);
                break outerLoop;
            case DONE:
                int taskIndex = Integer.parseInt(input.substring(5));
                tasks.get(taskIndex - 1).markAsDone();
                response = ui.printDone(tasks.get(taskIndex - 1));
                break outerLoop;
            case BYE:
                response = ui.printBye();
                break outerLoop;
            case FIND:
                String keyword = input.substring(5);
                TaskList findList = new TaskList();
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i).getDescription().contains(keyword)) {
                        findList.add(tasks.get(i));
                    }
                }
                response = ui.printFind(findList);
                break outerLoop;
            case HELP:
                response = ui.printHelp();
                break outerLoop;
            default:
                response = ui.printOops();
                break outerLoop;
            }
        }
        storage.rewriteData();
        return response;
    }

}