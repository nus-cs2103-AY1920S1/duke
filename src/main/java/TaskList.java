import java.io.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
	protected static ArrayList<Task> tasks;
	
	public TaskList(ArrayList<Task> tasks) throws DukeException {
		this.tasks = tasks;
	}
	
	public ArrayList<Task> getTasks() {
		return this.tasks;
	}
	
	public void addTodo(String input) throws EmptyDescriptionException {
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
	
	public void addDeadline(String input) throws EmptyDescriptionException, InvalidDescriptionException, ParseException {
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
	
	public void addEvent(String input) throws EmptyDescriptionException, InvalidDescriptionException, ParseException {
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
	
	public void deleteTask(String input) {
		String[] inputs = input.split(" ");
		int index = Integer.parseInt(inputs[1]) - 1;
		Task removedTask = tasks.remove(index);
		System.out.println("Noted. I've removed this task:");
		System.out.println(removedTask);
		System.out.println("Now you have " + tasks.size() + " tasks in the list.");
	}
	
	public void completeTask(String input) throws IOException {
		String[] inputs = input.split(" ");
		int index = Integer.parseInt(inputs[1]) - 1;
		tasks.get(index).complete();
		System.out.println("Nice! I've marked this task as done:");
		System.out.println(tasks.get(index));
	}
	
	public void findTask(String input) {
		String keyword = input.substring(5);
		ArrayList<Task> filtered = copy(this.tasks);
		System.out.println("Here are the matching tasks in your list.");
		filtered.stream().filter(p -> p.description.contains(keyword)).forEach(System.out::println);
	}
	
	public ArrayList<Task> copy(ArrayList<Task> tasks) {
		ArrayList<Task> duplicate = new ArrayList<>();
		for (int i = 0; i < tasks.size(); i++) {
			duplicate.add(tasks.get(i));
		}
		return duplicate;
	}
	public static void printOut(Task task) {
		System.out.println("Got it. I've added this task:");
		System.out.println(task);
		System.out.println("Now you have " + tasks.size() + " tasks in the list.");
	}
	
	public static Date convertStringToDate(String input) throws ParseException {
		Date result = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(input);
		return result;
	}
}
