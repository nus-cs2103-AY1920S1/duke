import java.util.ArrayList;
import java.util.Date;

/**
 * @author bakwxh
 * @version 0.1
 */
public class TaskList {
	private ArrayList<Task> memory;
	
	/** Constructor.
	 * @param inputMemory Previous memory.
	 */
	public TaskList(ArrayList<Task> inputMemory) {
		memory = inputMemory;
	}
	
	/**
	 * Constructor.
	 */
	public TaskList() {
		memory = new ArrayList<>();
	}
	
	/**
	 * Lists out tasks in memory.
	 */
	public void list() {
		int counter = 1;
		for (Task t : memory) {
			System.out.println(" " + counter + "." + t.showTask());
			counter++;
		}
	}
	
	/**
	 * Lists the newest task in memory.
	 * @return Newest task.
	 */
	public String listLatest() {
		return memory.get(memory.size() - 1).showTask();
	}
	
	/**
	 * Adds deadline task.
	 * @param desc Description of deadline.
	 * @param by By time of deadline.
	 */
	public void addDeadline(String desc, Date by) {
		Task newTask = new Deadline(desc, by);
		memory.add(newTask);
	}

	/**
	 * Adds event task.
	 * @param desc Description of event.
	 * @param by At time of event.
	 */
	public void addEvent(String desc, Date at) {
		Task newTask = new Event(desc, at);
		memory.add(newTask);
	}

	/**
	 * Adds to-do task.
	 * @param desc Description of to-do.
	 */
	public void addTodo(String desc) {
		Task newTask = new Todo(desc);
		memory.add(newTask);
	}

	/**
	 * Deletes task.
	 * @param index Index of task in memory.
	 * @return Deleted task.
	 * @throws DukeException Exceptions.
	 */
	public Task deleteTask(int index) throws DukeException {
		try {
			Task removed = memory.get(index);
			memory.remove(index);
			return removed;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new DukeException("Please indicate which task to delete.");
		}
	}

	/**
	 * Marks task as done.
	 * @param index Index of done task.
	 * @throws DukeException Exception.
	 */
	public void doneTask(int index) throws DukeException {
		try {
			memory.get(index).markAsDone();
			System.out.println("   " + memory.get(index).showTask());
		} catch (IndexOutOfBoundsException e) {
			throw new DukeException("Indicated task does not exist.");
		}
		
	}

	/**
	 * Retrieves memory array list.
	 * @return Memory array list.
	 */
	public ArrayList<Task> getMemory() {
		return this.memory;
	}
}