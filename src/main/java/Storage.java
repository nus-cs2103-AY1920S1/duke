import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the reading and writing of tasks from a specified file.
 */
public class Storage {
	private File file;
	private static final DateTimeFormatter dateTimeFormatter
		= DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

	/**
	 * Constructor for Storage object
	 * @param filePath the path of the archival file as a string
	 */
	public Storage(String filePath) {
		this.file = new File(filePath);
	}

	/**
	 * load, represents the reading of tasks from the archival file
	 * @return returns an arraylist of tasks that were archived from previous usage
	 * @throws DukeException throws DukeException for reading and formatting errors of the archival file
	 */
	public ArrayList<Task> load() throws DukeException {
		ArrayList<Task> output = new ArrayList<>();
		return readAndUpdateArchivalList(output, file);
	}

	/**
	 * saveTasks takes a TaskList object and saves all tasks contained in according to the specified format in each
	 * Task object. This formatting aids in the reading of tasks in the Load method as well.
	 * @param tasks
	 */
	public void saveTasks(TaskList tasks) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			for (Task task : tasks.getTaskArrayList()) {
				fileWriter.write(task.getArchivalText() + "\n");
			}
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}


	/**
	 * readAndUpdateArchivalList reads from the file and appends each task into the taskArrayList specified
	 * @param taskArrayList the arrayList of tasks to append to
	 * @param file the archival file of tasks
	 * @return the same taskArrayList passed into its argument
	 * @throws DukeException wraps FileNotFoundException and throws it upwards
	 */
	private static ArrayList<Task> readAndUpdateArchivalList(ArrayList<Task> taskArrayList, File file)
			throws DukeException{
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(file);
			while(fileScanner.hasNextLine()) {
				readAndAddArchivalTask(fileScanner.nextLine(), taskArrayList);
			}
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return taskArrayList;
	}

	/**
	 * readAndAddArchivalEvent reads a string representation of a Task and adds it to a specified list
	 * @param taskString the string representation of task. has to be in a specified format
	 * @param taskArrayList the arraylist to add the tasks to.
	 * @throws DukeException throws exception upwards that may derive from decryptTaskString
	 */
	private static void readAndAddArchivalTask(String taskString, ArrayList<Task> taskArrayList)
			throws DukeException{
		Task taskToBeAdded = decryptTaskString(taskString);
		taskArrayList.add(taskToBeAdded);
	}

	/**
	 * converts a taskString into a task object
	 * @param taskString the string representation of a task in the archival file
	 * @return a task object converted from the taskString
	 * @throws DukeException throws exception if the format of taskString is not recognised / expected
	 */
	private static Task decryptTaskString(String taskString) throws DukeException {
		String[] taskDetails = taskString.split(" \\| ");
		Task taskToReturn;
		String taskType = taskDetails[0];
		Boolean isCompleted = taskDetails[1].equals("1");
		switch(taskType) {
			case "T":
				taskToReturn = new ToDo(taskDetails[2].trim());
				updateTaskCompletionStatus(isCompleted, taskToReturn);
				return taskToReturn;
			case "E":
				taskToReturn = new Event(taskDetails[2].trim(),
					LocalDateTime.parse(taskDetails[3].trim(), dateTimeFormatter));
				updateTaskCompletionStatus(isCompleted, taskToReturn);
				return taskToReturn;
			case "D":
				taskToReturn = new DeadLine(taskDetails[2].trim(),
					LocalDateTime.parse(taskDetails[3].trim(), dateTimeFormatter));
				updateTaskCompletionStatus(isCompleted, taskToReturn);
				return taskToReturn;
			default:
				throw new DukeException("Event Type is not recognised");
		}
	}

	/**
	 * Update the completion status based on the archival details. Used in decryptingTaskString when converting
	 * a task string into task.
	 * @param status true for completed and false for not completed
	 * @param task returns the same task passed into it after setting its completion status correspondingly
	 */
	private static void updateTaskCompletionStatus(boolean status, Task task) {
		if (status) {
			task.markCompleted();
		} else {
			task.markIncomplete();
		}
	}
}
