import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
	private File file;
	private static final DateTimeFormatter dateTimeFormatter
		= DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

	public Storage(String filePath) {
		this.file = new File(filePath);
	}

	public ArrayList<Task> load() throws DukeException {
		ArrayList<Task> output = new ArrayList<>();
		return readAndUpdateArchivalList(output, file);
	}

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

	private static void updateArchivalFile(File file, ArrayList<Task> taskArrayList) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			for (Task task : taskArrayList) {
				fileWriter.write(task.getArchivalText() + "\n");
			}
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static ArrayList<Task> readAndUpdateArchivalList(ArrayList<Task> taskArrayList, File file) throws DukeException{
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(file);
			while(fileScanner.hasNextLine()) {
				readAndAddArchivalEvent(fileScanner.nextLine(), taskArrayList);
			}
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return taskArrayList;
	}

	private static void readAndAddArchivalEvent(String eventString, ArrayList<Task> taskArrayList) throws DukeException{
		Task taskToBeAdded = decryptEventString(eventString);
		taskArrayList.add(taskToBeAdded);
	}

	private static Task decryptEventString(String eventString) throws DukeException {
		String[] taskDetails = eventString.split(" \\| ");
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

	private static void updateTaskCompletionStatus(boolean status, Task task) {
		if (status) {
			task.markCompleted();
		} else {
			task.markIncomplete();
		}
	}
}
