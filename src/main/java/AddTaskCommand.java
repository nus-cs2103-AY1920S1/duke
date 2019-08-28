public class AddTaskCommand extends Command {
    Task currTask;
    String taskDetailsString;
    AddTaskCommand(String taskDetailsString) throws DukeException {
        super(0);
        this.taskDetailsString = taskDetailsString;
        getTask();
    }

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

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Got it. I've added this task:");
        tasks.addTask(currTask);
        ui.printString("  " + currTask.getTaskDetails());
        ui.printString(tasks.listDetails());
    };
}
