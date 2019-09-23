package duke.logic;

import duke.exception.EmptyTaskDukeException;
import duke.exception.InvalidTaskDukeException;
import duke.exception.LoadingErrorDukeException;

import duke.task.Deadline;
import duke.task.DoAfter;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents Storage used to save/load data from Duke to disk.
 */
public class Storage {
	/**
	 * Represents file path of storage.
	 */
	private String filePath;

	/**
	 * Constructor of Storage. Save/load file to input path.
	 *
	 * @param filePath File path to save/load file.
	 */
	public Storage(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Loads TaskList data from disk.
	 *
	 * @return TaskList loaded from disk.
	 * @throws LoadingErrorDukeException If no file found.
	 */
	public List<Task> loadList() throws LoadingErrorDukeException {
		List<Task> loadedList = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String readerLine = bufferedReader.readLine(); //initialise first line

			while (readerLine != null) {
				String[] readArray = readerLine.split("\\s\\|\\s");
				Task task = createTaskFromInput(readArray);
				loadedList.add(task);
				readerLine = bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			throw new LoadingErrorDukeException();
		} catch (IOException | EmptyTaskDukeException | InvalidTaskDukeException e) {
			e.printStackTrace();
		}
		return loadedList;
	}

	/**
	 * Saves TaskList data from Duke to disk.
	 *
	 * @param tasks Takes Tasks from TaskList and saves to disk.
	 */
	public void saveList(TaskList tasks) {
		File file = new File(filePath);
		file.getParentFile().mkdirs();
		try {
			FileWriter fileWriter = new FileWriter(filePath);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			while (!tasks.isEmpty()) {
				Task task = tasks.remove(1);
				String line = generateLineFromTask(task);
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Abstraction used to create tasks from String in data.
	 *
	 * @param inputArray String from reader.
	 * @return Task created from String.
	 * @throws InvalidTaskDukeException If input is invalid.
	 * @throws EmptyTaskDukeException   If task is invalid.
	 */
	private static Task createTaskFromInput(String[] inputArray) throws InvalidTaskDukeException, EmptyTaskDukeException {
		Task createdTask = null;
		switch (inputArray[0]) {
		case "TD":
			createdTask = new ToDo(inputArray[2]);
			break;
		case "DA":
			createdTask = new DoAfter(inputArray[2], inputArray[3]);
			break;
		case "DL":
			createdTask = new Deadline(inputArray[2], inputArray[3]);
			break;
		case "E":
			createdTask = new Event(inputArray[2], inputArray[3]);
			break;
		}
		if (createdTask != null) {
			if (inputArray[1].equals("1")) {
				createdTask.setDone(true);
			} else {
				createdTask.setDone(false);
			}
		}
		return createdTask;
	}

	/**
	 * Abstraction used to generate String line from Task to save to disk.
	 *
	 * @param task Task from TaskList.
	 * @return String output of Task for saving to disk.
	 */
	private static String generateLineFromTask(Task task) {
		StringBuilder stringBuilder = new StringBuilder();

		// Determine type of Task and add to line
		if (task instanceof ToDo) {
			stringBuilder.append("TD");
		} else if (task instanceof Deadline) {
			stringBuilder.append("DL");
		} else if (task instanceof Event) {
			stringBuilder.append("E");
		} else if (task instanceof DoAfter) {
			stringBuilder.append("DA");
		}

		stringBuilder.append(" | ");

		// Determine if done and add to line
		if (task.isDone()) {
			stringBuilder.append("1");
		} else {
			stringBuilder.append("0");
		}

		stringBuilder.append(" | ");
		stringBuilder.append(task.getName());

		// Add DateTime
		if (task instanceof Event) {
			stringBuilder.append(" | ");
			Event event = (Event) task;
			stringBuilder.append(event.getAtTime());
		} else if (task instanceof Deadline) {
			stringBuilder.append(" | ");
			Deadline deadline = (Deadline) task;
			stringBuilder.append(deadline.getByWhen());
		} else if (task instanceof DoAfter) {
			stringBuilder.append(" | ");
			DoAfter doAfter = (DoAfter) task;
			stringBuilder.append(doAfter.getAfterWhen());
		}

		String lineToAdd = stringBuilder.toString();
		return lineToAdd;
	}
}
