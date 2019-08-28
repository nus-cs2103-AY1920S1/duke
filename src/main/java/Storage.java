import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
	protected String filePath;

	/**
	 * Constructor.
	 * @param filePath Path to save file.
	 */
	public Storage(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Saves memory to file.
	 * @param tasks Task list.
	 * @throws DukeException Exceptions.
	 */
	public void saveMemory(TaskList tasks) throws DukeException {
		ArrayList<Task> memory = tasks.getMemory();
		String dir = System.getProperty("user.dir") + "/savedData.txt";
		for (Task t : memory) {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(dir));
				out.write(t.toSave() + "\n");
				out.flush();
				out.close();
			} catch (IOException e) {
				throw new DukeException("Error saving data.");
			}
		}
	}
	
	/**
	 * Loads from save file
	 * @return Returns array list with saved tasks.
	 * @throws DukeException Exceptions.
	 */
	public ArrayList<Task> load() throws DukeException {
		ArrayList<Task> TaskList = new ArrayList<>();
		try {
			FileInputStream test = new FileInputStream(filePath);
			Scanner sc = new Scanner(test);
			while (sc.hasNextLine()) {
				String rawInput = sc.nextLine();
				if (rawInput.toLowerCase().startsWith("done")) {
					String procInput = rawInput.substring(4);
					if (procInput.toLowerCase().startsWith("todo")) {
						TaskList.add(new Todo(procInput.substring(5)));
					} else if (procInput.toLowerCase().startsWith("deadline")) {
						String[] deadlineInput = procInput.substring(9).split("/by");
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
						TaskList.add(new Deadline(deadlineInput[0].trim(), sdf.parse(deadlineInput[1].trim())));
					} else if (procInput.toLowerCase().startsWith("event")) {
						String[] eventInput = procInput.substring(6).split("/at");
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
						TaskList.add(new Event(eventInput[0].trim(), sdf.parse(eventInput[1].trim())));
					}
					TaskList.get(TaskList.size() - 1).markAsDone();
				} else if (rawInput.toLowerCase().startsWith("todo")) {
					TaskList.add(new Todo(rawInput.substring(5)));
				} else if (rawInput.toLowerCase().startsWith("deadline")) {
					String[] deadlineInput = rawInput.substring(9).split("/by");
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
					TaskList.add(new Deadline(deadlineInput[0].trim(), sdf.parse(deadlineInput[1].trim())));
				} else if (rawInput.toLowerCase().startsWith("event")) {
					String[] eventInput = rawInput.substring(6).split("/at");
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
					TaskList.add(new Event(eventInput[0].trim(), sdf.parse(eventInput[1].trim())));
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			File newData = new File(filePath);
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(newData);
				fos.flush();
				fos.close();
			} catch (IOException e1) {
				throw new DukeException("Save file creation failed");
			}
		} catch (ParseException e) {
			throw new DukeException("Save file timings corrupted");
		}
		return TaskList;
	}
}