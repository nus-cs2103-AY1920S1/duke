import java.util.Scanner;

/**
 * The Command class makes use of multiple classes to drive the program.
 */
public class Command {

    private String command;

    /**
     * Creates a Command object.
     *
     * @param command A string representation of the user's command.
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Responds accordingly to the user's command.
     *
     * @param tasks A TaskList object containing the list of tasks.
     * @param ui An Ui object that helps to interact with the user.
     * @param storage A Storage object that contains a file with the tasks in it.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Scanner sc = new Scanner(command);
        String action = sc.next();
        switch (action) {
        case "bye":
            return ui.showBye();
        case "list":
            return ui.listTask(tasks);
        case "done":
            int number = sc.nextInt();
            if (!tasks.getList().get(number - 1).isDone()) {
                tasks.markDone(number);
                storage.updateDone(number, tasks);
                return ui.printDone(number, tasks);
            } else {
                return "Task is already done.";
            }
        case "todo":
            try {
                if (sc.hasNextLine()) {
                    String taskname = sc.nextLine().trim();
                    if (!taskname.equals("")) {
                        Task t = new Todo(taskname);
                        storage.addTodo(taskname, tasks);
                        tasks.add(t);
                        return ui.printAdd(t, tasks);
                    } else {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                } else {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
            } catch (DukeException e) {
                return e.getMessage();
            }
        case "deadline":
            try {
                if (sc.hasNextLine()) {
                    String deadline = sc.nextLine().trim();
                    if (!deadline.equals("")) {
                        String[] arrDeadline = deadline.split("/by");
                        String timeDeadline = Parser.convertDateAndTime(arrDeadline[1].trim());
                        Task taskDeadline = new Deadline(arrDeadline[0].trim(), timeDeadline);
                        storage.addDeadline(deadline, tasks);
                        tasks.add(taskDeadline);
                        return ui.printAdd(taskDeadline, tasks);
                    } else {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                } else {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
            } catch (DukeException e) {
                return e.getMessage();
            }
            case "event":
            try {
                if (sc.hasNextLine()) {
                    String event = sc.nextLine().trim();
                    if (!event.equals("")) {
                        String[] arrEvent = event.split("/at");
                        String time = Parser.convertDateAndTime(arrEvent[1].trim());
                        Task taskEvent = new Event(arrEvent[0].trim(), time);
                        storage.addEvent(event, tasks);
                        tasks.add(taskEvent);
                        return ui.printAdd(taskEvent, tasks);
                    } else {
                        throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                    }
                } else {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }
            } catch (DukeException e) {
                return e.getMessage();
            }
        case "delete":
            int deletionNumber = sc.nextInt();
            storage.delete(deletionNumber, tasks);
            Task toDelete = tasks.getList().get(deletionNumber - 1);
            tasks.delete(deletionNumber);
            return ui.printDelete(toDelete, tasks);
        case "find":
            String keyword = sc.nextLine().trim();
            return ui.printFind(keyword, tasks);
        default:
            try {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    }

    /**
     * Gets a boolean true or false depending on the user's command.
     * @return true if user's command is bye.
     */
    public boolean isExit() {
        boolean flag = false;
        if (command.equals("bye")) {
            flag = true;
        }
        return flag;
    }

}