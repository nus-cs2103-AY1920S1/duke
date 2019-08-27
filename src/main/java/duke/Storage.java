package duke;

import duke.calendar.Date;
import duke.calendar.Time;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage in the hard disk.
 * A <code>Storage</code> object corresponds to a specific file path that dictates the file that information about the
 * tasks in the <code>TaskList</code> should be loaded from and written to every time a change occurs.
 */
public class Storage {

	protected String filePath;

	/**
	 * Constructor for <code>Storage</code>.
	 * @param filePath Path to file that should be written to and loaded from.
	 */
	public Storage(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Loads the tasks from the file given by the file path.
	 * Stores and returns the tasks within an <code>ArrayList</code>.
	 * @return An <code>ArrayList</code> containing every <code>Task</code> written in the file.
	 * @throws DukeException If the file path is invalid.
	 */
	public ArrayList<Task> load() throws DukeException {
		try {
			ArrayList<Task> tasks = new ArrayList<Task>();
			Scanner scanner = new Scanner(new File(filePath));
			while (scanner.hasNextLine()) {
				String taskLine = scanner.nextLine();
				String[] taskDetails = taskLine.split(" \\| ");
				String taskType = taskDetails[0].trim();
				String taskDoneStatus = taskDetails[1].trim();
				String taskDescription = taskDetails[2].trim();
				Task task;
				if (taskType.equals("T")) {
					task = new Todo(taskDescription);
				} else if (taskType.equals("D")) {
					String taskDate = taskDetails[3].trim();
					String taskTime = null;
					if (taskDetails.length > 4) {
						taskTime = taskDetails[4].trim();
					}
					task = new Deadline(taskDescription, new Date(taskDate), new Time(taskTime));
				} else {
					String taskStartDate = taskDetails[3].trim();
					String taskStartTime = null;
					String taskEndDate = null;
					String taskEndTime = null;
					if (taskDetails.length > 4) {
						taskStartTime = taskDetails[4].trim();
						if (taskDetails.length > 5) {
							taskEndDate = taskDetails[5].trim();
							if (taskDetails.length > 6) {
								taskEndTime = taskDetails[6].trim();
							}
						}
					}
					task = new Event(taskDescription, new Date(taskStartDate), new Time(taskStartTime),
							new Date(taskEndDate), new Time(taskEndTime));
				}
				if (taskDoneStatus.equals("1")) {
					task.markAsDone();
				}
				tasks.add(task);
			}
			return tasks;
		} catch (FileNotFoundException exception) {
			throw new DukeException("\u2639 OOPS!!! Please specify a valid file path.");
		}
	}

	/**
	 * Writes the tasks in the <code>TaskList</code> to the file given by the file path.
	 * @param taskList <code>TaskList</code> containing all the tasks.
	 * @throws DukeException If the file cannot be written to.
	 */
	public void writeToFile(TaskList taskList) throws DukeException {
		try {
			FileWriter writer = new FileWriter(filePath);
			ArrayList<Task> tasks = taskList.getList();
			for (Task task : tasks) {
				StringBuilder builder = new StringBuilder();
				builder.append(task.getType() + " | ");
				if (task.isDone()) {
					builder.append("1 | ");
				} else {
					builder.append("0 | ");
				}
				builder.append(task.getDescription());
				if (task.getType().equals("D")) {
					Deadline deadline = (Deadline) task;
					String rawDate = deadline.getDate().getRawDate();
					String rawTime = deadline.getTime().getRawTime();
					builder.append(" | " + rawDate);
					if (rawTime != null) {
						builder.append(" | " + rawTime);
					}
				} else if (task.getType().equals("E")) {
					Event event = (Event) task;
					String rawStartDate = event.getStartDate().getRawDate();
					String rawStartTime = event.getStartTime().getRawTime();
					String rawEndDate = event.getEndDate().getRawDate();
					String rawEndTime = event.getEndTime().getRawTime();
					builder.append(" | " + rawStartDate);
					if (rawStartTime != null) {
						builder.append(" | " + rawStartTime);
					}
					builder.append(" | ");
					if (rawEndDate != null) {
						builder.append(rawEndDate);
					}
					if (rawEndTime != null) {
						if (rawEndDate != null) {
							builder.append(" | ");
						}
						builder.append(" " + rawEndTime);
					}
				}
				writer.write(builder.toString() + "\n");
			}
			writer.close();
		} catch (IOException exception) {
			throw new DukeException("\u2639 OOPS!!! Something went wrong: " + exception.getMessage());
		}
	}

}
