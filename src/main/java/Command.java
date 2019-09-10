import java.io.IOException;

/**
 * Represents a command.
 */
public class Command {

    public Command() {
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        storage.loadFile();
        ui.printGreeting();
        String taskLine = ui.readCommand();
        Parser parser = new Parser();
        outerLoop:
        while (taskLine != null) {
            String[] words = taskLine.split(" ");
            switch (parser.parse(taskLine)) {
            case TODO:
                if (taskLine.length() == 4) {
                    ui.throwErrorMessage("todo");
                } else {
                    Task todo = new Todo(taskLine.substring(5));
                    tasks.add(todo);
                    ui.printAddTask(todo, tasks.size());
                }
                taskLine = ui.readCommand();
                break;
            case EVENT:
                String[] parts = taskLine.split("/at", 2);
                String part1 = parts[0];
                String part2 = parts[1];
                if (taskLine.length() == 5) {
                    ui.throwErrorMessage("event");
                } else if (part2.matches(" \\d{2}/\\d{2}/\\d{4} \\d{4}")) {
                    int indexOfSlash = taskLine.indexOf("/");
                    Task eventTask = new Event(taskLine.substring(6, indexOfSlash - 1), taskLine.substring(indexOfSlash + 4));
                    tasks.add(eventTask);
                    ui.printAddTask(eventTask, tasks.size());
                } else {
                    ui.printEventFormat();
                }
                taskLine = ui.readCommand();
                break;
            case DEADLINE:
                String[] segments = taskLine.split("/by", 2);
                String segment1 = segments[0];
                String segment2 = segments[1];
                if (taskLine.length() == 8) {
                    ui.throwErrorMessage("deadline");
                } else if (segment2.matches(" \\d{2}/\\d{2}/\\d{4} \\d{4}")) {
                    int indexOfSlash2 = taskLine.indexOf("/");
                    Task deadlineTask = new Deadline(taskLine.substring(9, indexOfSlash2 - 1), taskLine.substring(indexOfSlash2 + 4));
                    tasks.add(deadlineTask);
                    ui.printAddTask(deadlineTask, tasks.size());
                } else {
                    ui.printDeadlineFormat();
                }
                taskLine = ui.readCommand();
                break;
            case LIST:
                ui.printList(tasks);
                taskLine = ui.readCommand();
                break;
            case DELETE:
                int taskNum = Integer.parseInt(taskLine.substring(7));
                Task deletedTask = tasks.get(taskNum - 1);
                ui.printDelete(deletedTask, tasks.size());
                tasks.remove(taskNum - 1);
                taskLine = ui.readCommand();
                break;
            case DONE:
                int taskIndex = Integer.parseInt(taskLine.substring(5));
                tasks.get(taskIndex - 1).markAsDone();
                ui.printDone(tasks.get(taskIndex - 1));
                taskLine = ui.readCommand();
                break;
            case BYE:
                ui.printBye();
                break outerLoop;
            case FIND:
                String keyword = taskLine.substring(5);
                TaskList findList = new TaskList();
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i).getDescription().contains(keyword)) {
                        findList.add(tasks.get(i));
                    }
                }
                ui.printFind(findList);
                taskLine = ui.readCommand();
                break;
            default:
                ui.printOops();
                taskLine = ui.readCommand();
                break;
            }
        }
        storage.rewriteData();
    }

}