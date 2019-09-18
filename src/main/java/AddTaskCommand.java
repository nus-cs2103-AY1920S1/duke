public class AddTaskCommand extends Command {
    Task currTask;
    String taskDetailsString;

    /**
     * Constructor of AddTaskCommand.
     * @param taskDetailsString the full input of the user for add task command
     * @throws DukeException
     */
    AddTaskCommand(String taskDetailsString) throws DukeException {
        super(0);
        this.taskDetailsString = taskDetailsString;
        getTask();
    }

    /**
     * Helper function that maps the input to the relevant task
     * @throws DukeException
     */
    private void getTask() throws DukeException {
        //Task Details is all the details for the task
        try {
            String taskCat = taskDetailsString.split(" ")[0];
            ;
            if (taskDetailsString.replaceAll(taskCat, "").equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a " + taskCat + " cannot be empty.");
            }
            if (taskCat.equals("deadline")) {
                String endDate = "";
                if (taskDetailsString.split("/").length == 3) {
                    endDate = taskDetailsString.split("/")[1].split(" ")[1];
                } else {
                    endDate = taskDetailsString.split("/by ")[1];
                }
                String taskName = taskDetailsString.split("/")[0].split("deadline ")[1];
                currTask = new Deadline(taskName, endDate);
            } else if (taskCat.equals("event")) {
                String eventDate = taskDetailsString.split("/")[1].split("at ")[1];
                String taskName = taskDetailsString.split("/")[0].split("event ")[1];
                currTask = new Event(taskName, eventDate);
            } else if (taskCat.equals("todo")) {
                String taskName = taskDetailsString.split("todo ")[1];
                currTask = new Todo(taskName);
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * This method does all the necessary actions needed. Printing the messages, adding the tasks to the list
     * and getting the list details
     * @param tasks
     * @param ui
     * @param storage
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(currTask);
        ui.printString("Got it. I've added this task:\n" + "  " + currTask.getTaskDetails() + "\n" + tasks.listDetails());
        return "Got it. I've added this task:\n" + "  " + currTask.getTaskDetails() + "\n" + tasks.listDetails();
    };
}
