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
     * @return  A string of Duke's response.
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
            try {
                if (!tasks.getList().get(number - 1).isDone()) {
                    assert number - 1 <= tasks.size() && number >= 1 :
                            "Task number is not within task list";
                    tasks.markDone(number);
                    storage.updateDone(number, tasks);
                    return ui.printDone(number, tasks);
                } else {
                    assert tasks.getList().get(number - 1).isDone() :
                            "Task should already be done";
                    return "Task is already done.";
                }
            } catch (IndexOutOfBoundsException e) {
                return "Task number is invalid.";
            }
        case "todo":
            try {
                if (!sc.hasNextLine()) {
                    assert action.equals("todo") : "Command should be: todo";
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                String taskname = sc.nextLine().trim();
                if (taskname.equals("")) {
                    assert taskname.equals("") : " Task name should be empty";
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                assert !taskname.equals("") : "Task name should not be empty";
                Task t = new Todo(taskname);
                storage.addTask(storage.generateTaskHeader("Todo"),
                        taskname, "", tasks);
                tasks.add(t);
                return ui.printAdd(t, tasks);
            } catch (DukeException e) {
                return e.getMessage();
            }
        case "deadline":
            try {
                if (!sc.hasNextLine()) {
                    assert action.equals("deadline") : "Command should be: deadline";
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                String deadline = sc.nextLine().trim();
                if (deadline.equals("")) {
                    assert deadline.equals("") : " Task name should be empty";
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                assert !deadline.equals("") : "Task name should not be empty";
                String[] arrDeadline = deadline.split("/by");
                String timeDeadline = Parser.convertDateAndTime(arrDeadline[1].trim());
                Task taskDeadline = new Deadline(arrDeadline[0].trim(), timeDeadline);
                storage.addTask(storage.generateTaskHeader("Deadline"),
                        arrDeadline[0].trim(), arrDeadline[1].trim(), tasks);
                tasks.add(taskDeadline);
                return ui.printAdd(taskDeadline, tasks);
            } catch (DukeException e) {
                return e.getMessage();
            }
        case "event":
            try {
                if (!sc.hasNextLine()) {
                    assert action.equals("event") : "Command should be: event";
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }
                String event = sc.nextLine().trim();
                if (event.equals("")) {
                    assert event.equals("") : " Task name should be empty";
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }
                assert !event.equals("") : "Task name should not be empty";
                String[] arrEvent = event.split("/at");
                String time = Parser.convertDateAndTime(arrEvent[1].trim());
                Task taskEvent = new Event(arrEvent[0].trim(), time);
                storage.addTask(storage.generateTaskHeader("Event"),
                        arrEvent[0].trim(), arrEvent[1].trim(), tasks);
                tasks.add(taskEvent);
                return ui.printAdd(taskEvent, tasks);
            } catch (DukeException e) {
                return e.getMessage();
            }
        case "delete":
            int deletionNumber = sc.nextInt();
            try {
                storage.delete(deletionNumber, tasks);
                Task toDelete = tasks.getList().get(deletionNumber - 1);
                tasks.delete(deletionNumber);
                return ui.printDelete(toDelete, tasks);
            } catch (IndexOutOfBoundsException e) {
                return "Task number is invalid.";
            }
        case "find":
            String keyword = sc.nextLine().trim();
            return ui.printFind(keyword, tasks);
        default:
            try {
                if (!sc.hasNextLine()) {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                String input = action + sc.nextLine();
                if (!input.contains("/after")) {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                String[] doAfterArray = input.split("/after");
                if (doAfterArray.length != 2) {
                    throw new DukeException("Invalid DoAfter task format");
                }
                String after = Parser.convertDateAndTime(doAfterArray[1].trim());
                if (after.equals("")) {
                    throw new DukeException("Invalid DoAfter task format");
                }
                Task doAfter = new DoAfter(doAfterArray[0].trim(), after);
                storage.addTask(storage.generateTaskHeader("DoAfter"),
                        doAfterArray[0].trim(), doAfterArray[1].trim(), tasks);
                tasks.add(doAfter);
                return ui.printAdd(doAfter, tasks);
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    }
}