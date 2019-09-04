/**
 * Represents an adding to task list command.
 * Commands include adding Deadlines, Events and ToDos to the task list.
 */
public class AddCommand extends Command {

    protected static boolean isExit;

	public AddCommand(String type, String command) {
        super(type, command);
    }

    /**
     * Executes the AddCommand.
     * Adds Deadlines: "deadline".
     * Adds Events: "event".
     * Adds ToDos: "todo".
     *
     * @param ui       The Ui currently running.
     * @param taskList The TaskList Class containing the task list.
     * @param storage  The Storage class containing the name of file the be read.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        Task newTask;
        switch (this.type) {
            case "deadline":
                newTask = new Deadline(command);
                try {
                    newTask.understandDate();
                } catch (Exception e) {
                    System.out.println(e);
                }
	            TaskList.tasks.add(newTask);
                break;

            case "event":
                newTask = new Event(command);
                try {
                    newTask.understandDate();
                } catch (Exception e) {
                    System.out.println(e);
                }
	            TaskList.tasks.add(newTask);
                break;

            case "todo":
                newTask = new ToDo(command);
	            TaskList.tasks.add(newTask);
                break;

            default:
                newTask = new Task(command);
        }
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + newTask);
	    System.out.println("    Now you have " + TaskList.tasks.size() + " tasks in the list");
    }
}
