/**
 * Command corresponding to user input starting with either "todo", "deadline" or "event".
 * Executes the command input by using UI and adds the corresponding tasks to the tasklist.
 */
public class AddCommand extends Command {
    protected Type enumType;
    protected String taskDesc;
    protected String timeDesc;

    /**
     * Constructs a AddCommand object.
     * @param enumType indicates the type of add command
     * @param taskDesc description of the task by user
     * @param timeDesc inputted time of the task
     */
    public AddCommand(Type enumType, String taskDesc, String timeDesc) {
        this.enumType = enumType;
        this.taskDesc = taskDesc;
        this.timeDesc = timeDesc;
    }

    /**
     * Adds the program task inputted by the user to the task list.
     * and prints out corresponding response
     * @param tasks holds the list of tasks currently in the program
     * @param ui displays the output from execution
     * @param storage stores the added task to the specified file
     * @throws DukeException if task requirements is not met
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (enumType) {
            case TODO: {
                Task newTask = new Todo(this.taskDesc);
                tasks.add(newTask);
                int numTasks = tasks.size();
                ui.showLine("Got it. I've added this task:" + "\n" + newTask.toString() +
                            "\n" + "Now you have " + numTasks + " tasks in the list.");
            }
            break;

            case DEADLINE: {
                Task newTask = new Deadline(this.taskDesc, this.timeDesc);
                tasks.add(newTask);
                int numTasks = tasks.size();
                ui.showLine("Got it. I've added this task:" + "\n" + newTask.toString() +
                            "\n" + "Now you have " + numTasks + " tasks in the list.");

            }
            break;

            case EVENT:
                Task newTask = new Events(this.taskDesc, this.timeDesc);
                tasks.add(newTask);
                int numTasks = tasks.size();
                System.out.println("Got it. I've added this task:" + "\n" + newTask.toString() +
                        "\n" + "Now you have " + numTasks + " tasks in the list.");

                break;

            default:
                throw new DukeException("This doesn't make sense!");
        }

        storage.save(tasks.getTaskList());
    }

    /**
     * Determines whether the program stops running.
     * @return boolean value false so the program continues to run
     */
    public boolean isExit() {
        return false;
    }
}
