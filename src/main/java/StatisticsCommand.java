/**
 * Tracks the number of tasks that has been completed/not completed and the number of tasks belonging to the
 * respective task type.
 */
public class StatisticsCommand extends Command {

	int taskDone;
	int taskNotDone;
	int deadlineTasks;
	int eventTasks;
	int todoTasks;

	public StatisticsCommand() {
		taskDone = 0;
		taskNotDone = 0;
		deadlineTasks = 0;
		eventTasks = 0;
		todoTasks = 0;
	}

	/**
	 * Executes the statistics command to calculate the number of task for the respective type of tasks.
	 * @param tasks Tasklist consisting of current tasks.
	 * @param storage Updating of storage with new tasks.
	 * @return A String to indicate the number of tasks for each type.
	 */
	public String execute(TaskList tasks, DukeWriteFile storage) {

		for (int i = 0; i < tasks.getCounter(); i++) {
			if (tasks.getTask(i) instanceof Todo) {
				todoTasks++;
			} else if (tasks.getTask(i) instanceof Deadline) {
				deadlineTasks++;
			} else if (tasks.getTask(i) instanceof Event) {
				eventTasks++;
			}
			if(tasks.getTask(i).getStatus() == 1) {
				taskDone++;
			} else {
				taskNotDone++;
			}
		}
		return toString();
	}

	@Override
	public String toString() {
		String numDeadline = "Number of deadline tasks: " + deadlineTasks + "\n";
		String numEvent = "Number of event tasks: " + eventTasks + "\n";
		String numToDo = "Number of todo tasks: " + todoTasks + "\n";
		String completed = "Number of tasks done: " + taskDone + "\n";
		String notCompleted = "Number of tasks not done: " + taskNotDone + "\n";
		return  Ui.BORDER + "\n" + numDeadline + numEvent + numToDo + completed + notCompleted + Ui.BORDER;
	}
}
