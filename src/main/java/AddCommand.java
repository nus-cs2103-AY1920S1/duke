/**
 * Command corresponding to user input starting with either "todo", "deadline" or "event".
 * Executes the command input by using UI and adds the corresponding tasks to the tasklist.
 */
public class AddCommand extends Command {
    private TaskType enumType;
    private String taskDesc;
    private String timeDesc;

    /**
     * Constructs a AddCommand object.
     * @param enumType indicates the type of add command
     * @param taskDesc description of the task by user
     * @param timeDesc inputted time of the task
     */
    public AddCommand(TaskType enumType, String taskDesc, String timeDesc) {
        this.enumType = enumType;
        this.taskDesc = taskDesc;
        this.timeDesc = timeDesc;
    }

    @Override
    /**
     * Adds the program task inputted by the user to the task list.
     * and prints out corresponding response
     * @param tasks holds the list of tasks currently in the program
     * @param storage stores the added task to the specified file
     * @return String to be displayed as Duke response in GUI
     * @throws DukeException if task requirements is not met
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task newTask;
        switch (enumType) {
        case TODO:
            newTask = new Todo("T" , this.taskDesc);
            break;

        case DEADLINE:
            newTask = new Deadline("D", this.taskDesc, this.timeDesc);
            break;

        case EVENT:
            newTask = new Event("E", this.taskDesc, this.timeDesc.split(" ")[0], this.timeDesc.split(" ")[1]);
            break;

        case FIXED:
           newTask = new FixedDurationTask("F", this.taskDesc, this.timeDesc);
           break;

        default:
            assert false : "Program is not supposed to get here!";
            newTask = null;
        }
        return addTask(newTask, tasks, storage);
    }

    /**
     * Adds a task to the task list.
     * @param newTask the task to be added
     * @param tasks the existing taskList
     * @return String informing the user of the task addition
     */
    private String addTask(Task newTask, TaskList tasks, Storage storage) throws DukeException {
        tasks.add(newTask);
        int numTasks = tasks.size();
        storage.save(tasks.getTaskList());
        assert numTasks != 0 : "Tasks did not get added to the task list!";

        return "Got it. I've added this task:" + "\n" + newTask.toString() +
                "\n" + "Now you have " + numTasks + " tasks in the list.";
    }
}
