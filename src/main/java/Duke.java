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
import java.util.Date;
import java.util.Scanner;

public class Duke {
	static ArrayList<Task> memory;
	static String line = "____________________________________________________________";
	
	public static void main(String[] args) throws IOException, ParseException {
		memory = new ArrayList<>();
		
		loadSave();
		
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        
		System.out.println(line);
		System.out.println(" Hello! I'm Duke");
		System.out.println(" What can I do for you?");
		System.out.println(line);
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String rawInput = sc.nextLine();
			
			if (rawInput.toLowerCase().equals("bye")) {
				System.out.println(line);
				System.out.println(" " + "Bye. Hope to see you again soon!");
				System.out.println(line);
				System.out.println();
				break;
			} else if (rawInput.toLowerCase().equals("list")) {
				System.out.println(line);
				System.out.println(" " + "Here are the tasks in your list:");
				list();
				System.out.println(line);
				System.out.println();
			} else if (rawInput.toLowerCase().startsWith("done")) {
				doneTask(rawInput);
				saveMemory();
			} else if (rawInput.toLowerCase().startsWith("delete")) {
				deleteTask(rawInput);
				saveMemory();
			} else if (rawInput.toLowerCase().startsWith("todo")) {
				addTodo(rawInput);
				saveMemory();
			} else if (rawInput.toLowerCase().startsWith("deadline")) {
				addDeadline(rawInput);
				saveMemory();
			} else if (rawInput.toLowerCase().startsWith("event")) {
				addEvent(rawInput);
				saveMemory();
			} else {
				System.out.println(line);
				System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
				System.out.println(line);
				System.out.println();
			}
		}
		
		sc.close();
	}
	
	private static void addEvent(String rawInput) {
		try {
			String[] eventInput = rawInput.substring(6).split("/at");
			String description = eventInput[0].trim();
			String atRaw = eventInput[1].trim();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
			Date at = sdf.parse(atRaw);
			Task newTask = new Event(description, at);
			memory.add(newTask);
			
			System.out.println(line);
			System.out.println(" " + "Got it. I've added this task:");
			System.out.println("   " + newTask.showTask());
			if (memory.size() == 1) System.out.println(" " + "Now you have 1 task in your list");
			else System.out.println(" " + "Now you have " + memory.size() + " tasks in your list.");
			System.out.println(line);
			System.out.println();
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println(line);
			System.out.println(" ☹ OOPS!!! The description of a event cannot be empty.");
			System.out.println(line);
			System.out.println();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(line);
			System.out.println(" ☹ OOPS!!! The timing of a event cannot be empty.");
			System.out.println(line);
			System.out.println();
		} catch (ParseException e) {
			System.out.println(line);
			System.out.println(" ☹ OOPS!!! The timing of the event is invalid.");
			System.out.println(line);
			System.out.println();
		}
	}

	private static void addDeadline(String rawInput) {
		try {
			String[] deadlineInput = rawInput.substring(9).split("/by");
			String description = deadlineInput[0].trim();
			String byRaw = deadlineInput[1].trim();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
			Date by = sdf.parse(byRaw);
			Task newTask = new Deadline(description, by);
			memory.add(newTask);
			
			System.out.println(line);
			System.out.println(" " + "Got it. I've added this task:");
			System.out.println("   " + newTask.showTask());
			if (memory.size() == 1) System.out.println(" " + "Now you have 1 task in your list");
			else System.out.println(" " + "Now you have " + memory.size() + " tasks in your list.");
			System.out.println(line);
			System.out.println();
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println(line);
			System.out.println(" ☹ OOPS!!! The description of a deadline cannot be empty.");
			System.out.println(line);
			System.out.println();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(line);
			System.out.println(" ☹ OOPS!!! The timing of a deadline cannot be empty.");
			System.out.println(line);
			System.out.println();
		} catch (ParseException e) {
			System.out.println(line);
			System.out.println(" ☹ OOPS!!! The timing of the event is invalid.");
			System.out.println(line);
			System.out.println();
		}
	}

	private static void addTodo(String rawInput) {
		try {
			String description = rawInput.substring(5);
			Task newTask = new Todo(description);
			memory.add(newTask);
			
			System.out.println(line);
			System.out.println(" " + "Got it. I've added this task:");
			System.out.println("   " + newTask.showTask());
			if (memory.size() == 1) System.out.println(" " + "Now you have 1 task in your list");
			else System.out.println(" " + "Now you have " + memory.size() + " tasks in your list.");
			System.out.println(line);
			System.out.println();
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println(line);
			System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
			System.out.println(line);
			System.out.println();
		}
	}

	private static void deleteTask(String rawInput) {
		try {
			int listIndex = Integer.parseInt(rawInput.split(" ")[1]) - 1;
			Task removed = memory.get(listIndex);
			memory.remove(listIndex);
			System.out.println(line);
			System.out.println(" " + "Noted. I've removed this task:");
			System.out.println("   " + removed.showTask());
			if (memory.size() == 1) System.out.println(" " + "Now you have 1 task in your list");
			else System.out.println(" " + "Now you have " + memory.size() + " tasks in your list.");
			System.out.println(line);
			System.out.println();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(line);
			System.out.println(" ☹ OOPS!!! Please indicate which task to delete.");
			System.out.println(line);
			System.out.println();
		}
	}

	private static void doneTask(String rawInput) {
		try {
			int listIndex = Integer.parseInt(rawInput.split(" ")[1]) - 1;
			memory.get(listIndex).markAsDone();
			System.out.println(line);
			System.out.println(" " + "Nice! I've marked this task as done:");
			System.out.println("   " + memory.get(listIndex).showTask());
			System.out.println(line);
			System.out.println();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(line);
			System.out.println(" ☹ OOPS!!! Please indicate which task is done.");
			System.out.println(line);
			System.out.println();
		}
		
	}

	private static void saveMemory() throws IOException {
		String dir = System.getProperty("user.dir") + "/savedData.txt";
		BufferedWriter out = new BufferedWriter(new FileWriter(dir));
		for (Task t : memory) {
			out.write(t.toSave() + "\n");
		}
		out.flush();
		out.close();
	}

	private static void loadSave() throws IOException, ParseException {
		String dir = System.getProperty("user.dir") + "/savedData.txt";
		
		try {
			FileInputStream test = new FileInputStream(dir);
			Scanner sc = new Scanner(test);
			while (sc.hasNextLine()) {
				String rawInput = sc.nextLine();
				if (rawInput.toLowerCase().startsWith("done")) {
					String procInput = rawInput.substring(4);
					if (procInput.toLowerCase().startsWith("todo")) {
						String description = procInput.substring(5);
						Task newTask = new Todo(description);
						memory.add(newTask);
					} else if (procInput.toLowerCase().startsWith("deadline")) {
						String[] deadlineInput = procInput.substring(9).split("/by");
						String description = deadlineInput[0].trim();
						String byRaw = deadlineInput[1].trim();
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
						Date by = sdf.parse(byRaw);
						Task newTask = new Deadline(description, by);
						memory.add(newTask);
					} else if (procInput.toLowerCase().startsWith("event")) {
						String[] eventInput = procInput.substring(6).split("/at");
						String description = eventInput[0].trim();
						String atRaw = eventInput[1].trim();
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
						Date at = sdf.parse(atRaw);
						Task newTask = new Event(description, at);
						memory.add(newTask);
					}
					memory.get(memory.size() - 1).markAsDone();
				} else if (rawInput.toLowerCase().startsWith("todo")) {
					String description = rawInput.substring(5);
					Task newTask = new Todo(description);
					memory.add(newTask);
				} else if (rawInput.toLowerCase().startsWith("deadline")) {
					String[] deadlineInput = rawInput.substring(9).split("/by");
					String description = deadlineInput[0].trim();
					String byRaw = deadlineInput[1].trim();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
					Date by = sdf.parse(byRaw);
					Task newTask = new Deadline(description, by);
					memory.add(newTask);
				} else if (rawInput.toLowerCase().startsWith("event")) {
					String[] eventInput = rawInput.substring(6).split("/at");
					String description = eventInput[0].trim();
					String atRaw = eventInput[1].trim();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
					Date at = sdf.parse(atRaw);
					Task newTask = new Event(description, at);
					memory.add(newTask);
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			File newData = new File(dir);
			FileOutputStream fos = new FileOutputStream(newData);
			fos.flush();
			fos.close();
		}
		
		
	}
	
	private static void list() {
		int counter = 1;
		for (Task t : memory) {
			System.out.println(" " + counter + "." + t.showTask());
			counter++;
		}
	}
}
