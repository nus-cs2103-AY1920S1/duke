import java.util.ArrayList;
import java.io.*;

public class TaskList {
	private ArrayList<Task> taskList;
	private String fileDestination;

	public TaskList(String fileDestination) {
		taskList = new ArrayList<Task>();
		this.fileDestination = fileDestination;
		try{
			this.load();
		} catch (IOException e) {
			//if no file is originally there, TaskList save() will write the file
			try {
				new FileWriter(fileDestination).append("0").close();
			} catch (Exception e2) {
			}
		}
	}

	public Task addTask(Task t) {
		taskList.add(t);
		return t;
	}

	public ArrayList<Task> list() throws DukeException {
		if (taskList.size() > 0) {
			return new ArrayList<Task>(taskList);
		} else {
			throw new DukeEmptyListException();
		}
	}

	public int size() {
		return taskList.size();
	}

	public Task delete(int id) throws DukeException {
		try {
			return taskList.remove(id - 1);
		} catch (IndexOutOfBoundsException ex) {
			// task id does not correspond to task in list
			throw new DukeNoCorrespondingTaskException(id);
		}
	}

	public Task delete(String id) throws DukeException {
		try {
			return delete(Integer.parseInt(id));
		} catch (NumberFormatException ex) {
			throw new DukeIncorrectParameterTypeException(
					"\"" + id + "\" cannot be converted to an integer. Please enter a valid integer as a parameter.");
		}
	}

	public Task complete(int id) throws DukeException {
		try {
			return taskList.get(id - 1).complete();
		} catch (IndexOutOfBoundsException ex) {
			//task id does not correspond to task in the list
			throw new DukeNoCorrespondingTaskException(id);
		}
	}

	public Task complete(String id) throws DukeException {
		try {
			return complete(Integer.parseInt(id));
		} catch (NumberFormatException ex) {
			throw new DukeIncorrectParameterTypeException(
					"\"" + id + "\" cannot be converted to an integer. Please enter a valid integer as a parameter.");
		}
	}

	private void writeCompletion(FileWriter fw, Task t) throws IOException {
		if (t.isComplete()) {
			fw.append("1");
			fw.append(System.lineSeparator());
		} else {
			fw.append("0");
			fw.append(System.lineSeparator());
		}
	}

	public void save() throws IOException {
		FileWriter saved = new FileWriter(fileDestination);
		saved.append(Integer.toString(taskList.size()));
		saved.append(System.lineSeparator());
		for (Task task : taskList) {

			switch (task.getClass().getSimpleName().toString()) {
				case "ToDo":
					saved.append("T");
					saved.append(System.lineSeparator());
					try {
						writeCompletion(saved, task);
					} catch (IOException e) {
						System.out.println(e);
					}
					saved.append(task.getDescription());
					saved.append(System.lineSeparator());
					break;
				case "Deadline":
					saved.append("D");
					saved.append(System.lineSeparator());
					try {
						writeCompletion(saved, task);
					} catch (IOException e) {
						System.out.println(e);
					}
					saved.append(task.getDescription());
					saved.append(System.lineSeparator());
					saved.append(((Deadline) task).by);
					saved.append(System.lineSeparator());
					break;
				case "Event":
					saved.append("E");
					saved.append(System.lineSeparator());
					try {
						writeCompletion(saved, task);
					} catch (IOException e) {
						System.out.println(e);
					}
					saved.append(task.getDescription());
					saved.append(System.lineSeparator());
					saved.append(((Event)task).at);
					saved.append(System.lineSeparator());
					break;
				default:
					break;
			}
		}
		saved.flush();
		saved.close();
	}

	public void load() throws IOException {
		FileReader load = new FileReader(fileDestination);
		BufferedReader reader = new BufferedReader(load);
		int taskCount = Integer.parseInt(reader.readLine());
		ArrayList<Task> newList = new ArrayList<Task>();
		while (taskCount > 0) {
			taskCount--;
			switch (reader.readLine()) {
				case "T":
					if (Integer.parseInt(reader.readLine()) == 1) {
						newList.add(new ToDo(reader.readLine()).complete()); } else {
						newList.add(new ToDo(reader.readLine()));
					}
					break;
				case "D":
					if (Integer.parseInt(reader.readLine()) == 1) {
						newList.add(new Deadline(reader.readLine(), reader.readLine()).complete());
					} else {
						newList.add(new Deadline(reader.readLine(), reader.readLine()));
					}
					break;
				case "E":
					if (Integer.parseInt(reader.readLine()) == 1) {
						newList.add(new Event(reader.readLine(), reader.readLine()).complete());
					} else {
						newList.add(new Event(reader.readLine(), reader.readLine()));
					}
					break;
				default:
					break;
			}
		}
		load.close();
		taskList = newList;
	}
}
