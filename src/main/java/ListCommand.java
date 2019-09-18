/**
 * A command to list out all the current tasks.
 */
public class ListCommand extends Command{

	private String input;

	public ListCommand(String input) {

		this.input = input;
	}

	/**
	 * Executes the list command which will list out all current tasks.
	 * @param tasks Tasklist consisting of current tasks.
	 * @param storage Updating of storage with new tasks.
	 * @return A string which contains all of the current tasks with their descriptions.
	 */
	public String execute(TaskList tasks, DukeWriteFile storage) {
		int itemNumber = 1;
		int numberOfTask = tasks.getCounter();
		StringBuilder listOfTask = new StringBuilder();
		if (numberOfTask == 0) {
			listOfTask.append(Ui.NO_TASK);
		} else {
			listOfTask.append(Ui.OUTPUT_TASK_LIST);
			for (int i = 0; i < numberOfTask; i++) {
				listOfTask.append(itemNumber + "." + tasks.getTask(i).toString() + "\n");
				itemNumber++;
			}
			listOfTask.append(Ui.BORDER);
		}
		return listOfTask.toString();
	}
}
