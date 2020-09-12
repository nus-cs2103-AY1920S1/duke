/**
 * Encapsulates a user command to add a to-do task to the task list.
 */
public class AddTodoCommand extends Command {

    String inputTodo = "";

    public void setInputTodo(String inputTodo) {
        this.inputTodo = inputTodo;
    }

    /**
     * Overridden method. Executes the add todo command.
     * @param tasks list of tasks
     * @param ui user interface
     * @param storage storage file
     * @throws DukeException exception specific to Duke application
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputTodo.trim().length() == 4) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            String todoDescription = inputTodo.substring(5);
            Todo t = new Todo(todoDescription);
            tasks.addTask(t);
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
            storage.updateChanges(tasks.getDukeTaskList());
        }
    }

    @Override
    public String executeForGui(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputTodo.trim().length() == 4) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            String todoDescription = inputTodo.substring(5);
            Todo t = new Todo(todoDescription);
            tasks.addTask(t);
            StringBuilder sb = new StringBuilder();
            sb.append("Got it. I've added this task:");
            sb.append("\n");
            sb.append(t);
            sb.append("\n");
            sb.append("Now you have " + tasks.getSize() + " tasks in the list.");
            storage.updateChanges(tasks.getDukeTaskList());
            return sb.toString();
        }
    }

}
