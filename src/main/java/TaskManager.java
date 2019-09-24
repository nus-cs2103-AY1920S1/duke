import java.util.Date;

public class TaskManager {

    /**
     * Performs the operation of creating a Task (Todo, Deadline, Task).
     * It also writes the list of tasks to the local storage.
     *
     * @param storage         The local storage for the Tasks as a Storage object.
     * @param ui              The User Interface object that performs all interactions with the user.
     * @param tasks           The list of Task that are temporary stored with the bot.
     * @return Gives a feedback to the user on the operation has performed after a command is given.
     * @throws EmptyToDoDescriptionException Indicates an empty description that should not be left empty for Todo.
     * @throws EmptyDescriptionException     Indicates an empty description that should not be left empty.
     * @throws UnknownCommandException       Indicates the inability of the bot to read the command that is given by the user.
     */
    public static String createTask(Parser parser, Storage storage, Ui ui, TaskList tasks)
            throws EmptyToDoDescriptionException, EmptyDescriptionException,
            UnknownCommandException {

        String userCommand = parser.getUserCommand();
        String due = parser.getDue();
        String taskDescription = parser.getTaskDescription();
        Task t;
        String typeOfTask = "";
        if (userCommand.equals("todo")) {
            if (taskDescription.equals("dummy")) {
                throw new EmptyToDoDescriptionException("The description of a todo cannot be empty.");
            }
            t = new Todo(taskDescription);
            if (tasks.checkForSameTask(t)) {
                return ui.announceExisted();
            } else {
                typeOfTask = "T";
                tasks.add(t);
                storage.writeToFile(typeOfTask, "0", taskDescription, t);
            }
        } else if (userCommand.equals("deadline")) {
            Date dateDue = storage.convertStringToDate(due);
            if (taskDescription.equals("dummy")) {
                throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
            }
            t = new Deadline(taskDescription, dateDue);
            if (tasks.checkForSameTask(t)) {
                return ui.announceExisted();
            } else {
                tasks.add(t);
                typeOfTask = "D";
                storage.writeToFile(typeOfTask, "0", taskDescription, t);
            }
        } else if (userCommand.equals("event")) {
            if (taskDescription.equals("dummy")) {
                throw new EmptyDescriptionException("The description of a event cannot be empty.");
            }
            String[] eventStartEnd = due.split("-", 2);
            Date start = storage.convertStringToDate(eventStartEnd[0]);
            Date end = storage.convertStringToDate(eventStartEnd[1]);

            t = new Event(taskDescription, start, end);
            if (tasks.checkForSameTask(t)) {
                return ui.announceExisted();
            } else {
                typeOfTask = "E";
                tasks.add(t);
                storage.writeToFile(typeOfTask, "0", taskDescription, t);
            }
        } else if (userCommand.equals("clear")) {
            storage.clear();
            tasks.clear();
            assert tasks.size() == 0;
            return ui.announceCleared();
        } else {
            throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
        }

        assert tasks.size() > 0 : "List of tasks should not be empty.";
        int taskCount = tasks.size();
        return ui.newTaskAdded(t, taskCount);
    }

}
