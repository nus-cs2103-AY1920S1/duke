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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (command) {
        case "bye":
            ui.showBye();
            break;
        case "list":
            ui.listTask(tasks);
            break;
        case "done":
            int number = ui.readNumber();
            if (!tasks.getList().get(number - 1).isDone()) {
                tasks.markDone(number);
                ui.printDone(number, tasks);
                storage.updateDone(number, tasks);
            } else {
                System.out.println("Task is already done.");
            }
            break;
        case "todo":
            String taskname = ui.readLine().trim();
            if (ui.checkValidity(taskname)) {
                Task t = new Todo(taskname);
                storage.addTodo(taskname, tasks);
                tasks.add(t);
                ui.printAdd(t, tasks);
            }
            break;
        case "deadline":
            String deadline = ui.readLine().trim();
            if (ui.checkValidity(deadline)) {
                String[] arrDeadline = deadline.split("/by");
                String timeDeadline = Parser.convertDateAndTime(arrDeadline[1].trim());
                Task taskDeadline = new Deadline(arrDeadline[0].trim(), timeDeadline);
                storage.addDeadline(deadline, tasks);
                tasks.add(taskDeadline);
                ui.printAdd(taskDeadline, tasks);
            }
            break;
        case "event":
            String event = ui.readLine().trim();
            if (ui.checkValidity(event)) {
                String[] arrEvent = event.split("/at");
                String time = Parser.convertDateAndTime(arrEvent[1].trim());
                Task taskEvent = new Event(arrEvent[0].trim(), time);
                storage.addEvent(event, tasks);
                tasks.add(taskEvent);
                ui.printAdd(taskEvent, tasks);
            }
            break;
        case "delete":
            int deletionNumber = ui.readNumber();
            storage.delete(deletionNumber, tasks);
            Task toDelete = tasks.getList().get(deletionNumber - 1);
            tasks.delete(deletionNumber);
            ui.printDelete(toDelete, tasks);
            break;
        case "find":
            String keyword = ui.readLine().trim();
            ui.printFind(keyword, tasks);
            break;
        default:
            try {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
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