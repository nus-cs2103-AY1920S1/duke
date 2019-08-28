import java.util.ArrayList;
import java.util.Date;

public class TaskList {
	private ArrayList<Task> memory;
	
	public TaskList(ArrayList<Task> inputMemory) {
		memory = inputMemory;
	}
	
	public TaskList() {
		memory = new ArrayList<>();
	}
	
	public void list() {
		int counter = 1;
		for (Task t : memory) {
			System.out.println(" " + counter + "." + t.showTask());
			counter++;
		}
	}
	
	public String listLatest() {
		return memory.get(memory.size() - 1).showTask();
	}
	
	public void addDeadline(String desc, Date by) {
		Task newTask = new Deadline(desc, by);
		memory.add(newTask);
	}

	public void addEvent(String desc, Date at) {
		Task newTask = new Event(desc, at);
		memory.add(newTask);
	}

	public void addTodo(String desc) {
		Task newTask = new Todo(desc);
		memory.add(newTask);
	}

	public Task deleteTask(int index) throws DukeException {
		try {
			Task removed = memory.get(index);
			memory.remove(index);
			return removed;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new DukeException("Please indicate which task to delete.");
		}
	}

	public void doneTask(int index) throws DukeException {
		try {
			memory.get(index).markAsDone();
			System.out.println("   " + memory.get(index).showTask());
		} catch (IndexOutOfBoundsException e) {
			throw new DukeException("Indicated task does not exist.");
		}
		
	}

	public ArrayList<Task> getMemory() {
		return this.memory;
	}
}