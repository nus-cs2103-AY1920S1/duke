import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TaskList class contains the task list, and has operations to
 * add/delete the tasks in the list.
 */
public class TaskList {
	protected static ArrayList<Task> tasks;
	
	/**
	 * Class constructor
	 *
	 * @param tasks an array list of tasks.
	 * @throws DukeException which includes any exceptions when operating on the task list.
	 */
	public TaskList(ArrayList<Task> tasks) throws DukeException {
		this.tasks = tasks;
	}
	
	/**
	 * This method retrieves the task list.
	 *
	 * @return the array list of tasks that keep tracks of the tasks.
	 */
	public ArrayList<Task> getTasks() {
		return this.tasks;
	}
	
	/**
	 * Add the to do task into the task list.
	 *
	 * @param input description of the to do task to be added into the task list.
	 */
	public void addTodo(String input){
		try {
			if (!input.substring(4).isEmpty()) {
				String description = input.substring(4);
				Todo todo = new Todo(description);
				tasks.add(todo);
				printOut(todo);
			} else {
				throw new EmptyDescriptionException("todo");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Add the deadline task into the task list.
	 *
	 * @param input description of the deadline task together with the deadline date to be added into the task list.
	 */
	public void addDeadline(String input){
		try {
			if (input.contains("/by")) {
				int index = input.lastIndexOf("/by");
				String description = input.substring(8, index);
				String by = input.substring(index + 3);
				Date byDeadline = convertStringToDate(by);
				if (description.isBlank()) {
					throw new EmptyDescriptionException("deadline");
				}
				if (by.isBlank()) {
					throw new InvalidDescriptionException("deadline");
				}
				Deadline deadline = new Deadline(description, byDeadline);
				tasks.add(deadline);
				printOut(deadline);
			} else {
				throw new InvalidDescriptionException("deadline");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Add the event task into the task list.
	 *
	 * @param input description of the event task together with the event date to be added into the task list.
	 */
	public void addEvent(String input) {
		try {
			if (input.contains("/at")) {
				int index = input.lastIndexOf("/at");
				String description = input.substring(5, index);
				String at = input.substring(index + 3);
				Date atEvent = convertStringToDate(at);
				if (description.isBlank()) {
					throw new EmptyDescriptionException("event");
				}
				if (at.isBlank()) {
					throw new InvalidDescriptionException("event");
				}
				Event event = new Event(description, atEvent);
				tasks.add(event);
				printOut(event);
			} else {
				throw new InvalidDescriptionException("event");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Deletes the task as requested by the user input and removes this task from the
	 * task list.
	 *
	 * @param input command of the user input which includes the index of the task that
	 *              user wants to delete.
	 */
	public void deleteTask(String input) {
		String[] inputs = input.split(" ");
		int index = Integer.parseInt(inputs[1]) - 1;
		Task removedTask = tasks.remove(index);
		System.out.println("Noted. I've removed this task:");
		System.out.println(removedTask);
		System.out.println("Now you have " + tasks.size() + " tasks in the list.");
	}
	
	/**
	 * Completes the task as requested by the user input.
	 *
	 * @param input command of the user input which includes the index of the task that
	 *              user wants to complete
	 */
	public void completeTask(String input){
		String[] inputs = input.split(" ");
		int index = Integer.parseInt(inputs[1]) - 1;
		tasks.get(index).complete();
		System.out.println("Nice! I've marked this task as done:");
		System.out.println(tasks.get(index));
	}
	
	/**
	 * Find the task according to the keyword given by the user input.
	 *
	 * @param input command of the user input which includes the keyword that the user wants to find.
	 */
	public void findTask(String input) {
		String keyword = input.substring(5);
		ArrayList<Task> filtered = copy(this.tasks);
		System.out.println("Here are the matching tasks in your list.");
		filtered.stream().filter(p -> p.description.contains(keyword)).forEach(System.out::println);
	}
	
	/**
	 * This method copies the content of an array list to another independent array list.
	 *
	 * @param tasks the source of content in which will be copied to the other array list.
	 * @return an array list which has the copied content.
	 */
	public ArrayList<Task> copy(ArrayList<Task> tasks) {
		ArrayList<Task> duplicate = new ArrayList<>();
		for (int i = 0; i < tasks.size(); i++) {
			duplicate.add(tasks.get(i));
		}
		return duplicate;
	}
	
	/**
	 * Static method which prints out the addition of task message each time
	 * a task is successfully added into the task list.
	 *
	 * @param task that has been successfully added into the task list.
	 */
	public static void printOut(Task task) {
		System.out.println("Got it. I've added this task:");
		System.out.println(task);
		System.out.println("Now you have " + tasks.size() + " tasks in the list.");
	}
	
	/**
	 * Static method which converts the user input date into a Date instead of a String value.
	 *
	 * @param input a string input to be converted into the date following a fixed format.
	 * @return Date value which has been converted from a string.
	 * @throws ParseException if user did not key in the date as the requested format.
	 */
	public static Date convertStringToDate(String input) throws ParseException {
		Date result = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(input);
		return result;
	}
}
