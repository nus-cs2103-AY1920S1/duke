import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

	protected String filePath;

	public Storage(String filePath) {
		this.filePath = filePath;
	}

	public ArrayList<Task> load() throws DukeException {
		try {
			ArrayList<Task> tasks = new ArrayList<Task>();
			Scanner scanner = new Scanner(new File(filePath));
			while (scanner.hasNextLine()) {
				String taskLine = scanner.nextLine();
				String[] taskDetails = taskLine.split(" | ");
				String taskType = taskDetails[0];
				String taskDoneStatus = taskDetails[1];
				String taskDescription = taskDetails[2];
				Task task;
				if (taskType.equals("T")) {
					task = new Todo(taskDescription);
				} else if (taskType.equals("D")) {
					String taskDate = taskDetails[3];
					String taskTime = null;
					if (taskDetails.length > 4) {
						taskTime = taskDetails[4];
					}
					task = new Deadline(taskDescription, new Date(taskDate), new Time(taskTime));
				} else {
					String taskStartDate = taskDetails[3];
					String taskStartTime = null;
					String taskEndDate = null;
					String taskEndTime = null;
					if (taskDetails.length > 4) {
						taskStartTime = taskDetails[4];
						if (taskDetails.length > 5) {
							taskEndDate = taskDetails[5];
							if (taskDetails.length > 6) {
								taskEndTime = taskDetails[6];
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
