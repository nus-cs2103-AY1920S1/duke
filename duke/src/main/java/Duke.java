import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

	// private static ArrayList<Task> list = new ArrayList<>();
	private Storage storage;
	private TaskList tasks;
	private Ui ui;

	private static String filename = "./data/duke.txt"; // todo isDone? description

	public Duke(String filename) {
		ui = new Ui();
		storage = new Storage(filename);

		try {
			tasks = TaskList(storage.load());
			// load returns a stream of string, form supplier scanner
			// TastList parses stream, forEach.loadLine()
		} catch (DukeException e) {
			ui.showLoadingError(); // i suppose this just says file is corrupted therefore creating new or sth
			tasks = new TaskList();
		}
	}

	/*
	private static void initialLoad() throws DukeException {
		String line = null; // in case file is empty

		try {
			File f = new File(filename);
			Scanner s = new Scanner(f);

			while (s.hasNext()) { // assumes duke.txt has correct format
				boolean isDone;
				String type = s.next();
				int binary = Integer.valueOf(s.next());
				if (binary == 1) {
					isDone = true;
				} else {
					isDone = false;
				}
				String description = s.nextLine().trim();
				loadLine(type, isDone, description);
			}

			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}

	private static void loadLine(String type, boolean isDone, String description) throws DukeException {
		switch(type) {
		case "todo":
		    loadTodo(isDone, description);
		    break;

		case "deadline":
		    loadDeadline(isDone, description);
		    break;

		case "event":
		    loadEvent(isDone, description);
		    break;
		}
	}

	private static Task loadTodo(boolean isDone, String description) {
		Todo todo = new Todo(isDone, description);
		list.add(todo);
		return todo;
	}

	private static Task loadDeadline (boolean isDone, String description) throws DukeException {
		int indexBy = description.indexOf("/"); // potential source of error
		if (indexBy == -1) {
			throw new DukeException("Put / before by!");
		}
		String taskDesc = description.substring(0, indexBy - 1); // start after space, end before space before /

		String dateInString = line.substring(indexBy + 4);
		Date date = formatterIn.parse(dateInString);
		String by = formatterOut.format(date);

		Deadline dead = new Deadline(isDone, taskDesc, by);
		list.add(dead);
		return dead;
	}

	private static Task loadEvent(boolean isDone, String description) throws DukeException {
		int indexAt = description.indexOf("/");
		if (indexAt == -1) {
			throw new DukeException("Put / before at!");
		}
		String taskDesc = description.substring(0, indexAt - 1); // start after space, end before space before /

		String dateInString = line.substring(indexAt + 4);
		Date date = formatterIn.parse(dateInString);
		String at = formatterOut.format(date);

		Event event = new Event(isDone, taskDesc, at);
		list.add(event);
		return event;
	}

	private static void update(ArrayList<Task> list, String filename) throws IOException {
		// writes into file
		FileWriter fw = new FileWriter(filename, false); // rewrites the entire doc
		for (Task task: list) {
			String type = task.getType();
			int binary = task.isDone ? 1 : 0;
			String toWrite = type + " " + binary + " " + task.getDescription();
			fw.write(toWrite + System.lineSeparator());
		}
		fw.close();
	}
	*/

	public void run() {
		ui.showWelcome();
		boolean isExit = false;
		while (!isExit) {
	        try {
	            String fullCommand = ui.readCommand();
	            ui.showLine(); // show the divider line ("_______")
	            Command c = Parser.parse(fullCommand);
	            c.execute(tasks, ui, storage);
	            isExit = c.isExit();
	        } catch (DukeException e) {
	            ui.showError(e.getMessage());
	        } finally {
	            ui.showLine();
	        }
	    }

		while (!next.equals("bye")) {
			try {
				if (next.equals("list")) {
					System.out.println("Here are the tasks in your list:");
					for (int i = 0; i < list.size(); i++) {
						System.out.println((i + 1) + "." + list.get(i).toString());
					}
				} else if (next.equals("done")) {
					if (list.isEmpty()) {
						scanner.nextLine(); // just to clear whatever's left on the line
						throw new DukeException("You have no task to do!");
					} else if (!scanner.hasNextInt()) {
						throw new DukeException("Do include the task number that you have done!");
					}
					int index = scanner.nextInt(); // since scanner only took in the word done
					Task task = list.get(index - 1);
					task.markAsDone();
					System.out.println("Nice! I've marked this task as done:\n  " + task.toString());
				} else if (next.equals("delete")) {
					if (list.isEmpty()) {
						scanner.nextLine(); // just to clear whatever's left on the line
						throw new DukeException("You have no task to delete!");
					} else if (!scanner.hasNextInt()) {
						throw new DukeException("Which task do you want to delete?");
					}
					int index = scanner.nextInt(); // since scanner only took in the word done
					Task task = list.get(index - 1);
					list.remove(task);
					System.out.println("Noted. I've removed this task:\n  " + task.toString());
					System.out.println("Now you have " + list.size() + " tasks in the list.");
				} else {
					Task task;
					String line = scanner.nextLine().trim(); // would be \n if incorrect input

					switch (next) {
					case "todo":
						if (line.isEmpty()) {
							throw new DukeException("☹ OOPS!!! The description of a " + next + " cannot be empty.");
						}
						task = loadTodo(false, line);
						break;

					case "deadline":
						if (line.isEmpty()) {
							throw new DukeException("☹ OOPS!!! The description of a " + next + " cannot be empty.");
						}
						task = loadDeadline(false, line);
						break;

					case "event":
						if (line.isEmpty()) {
							throw new DukeException("☹ OOPS!!! The description of a " + next + " cannot be empty.");
						}
						task = loadEvent(false, line);
						break;

					default:
						throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
					}

					if (task == null) {
						throw new DukeException("There is no new task!");
					}

					System.out.println("Got it. I've added this task:\n  " + task.toString());
					System.out.println("Now you have " + list.size() + " tasks in the list.");
				}
			} catch (DukeException de){
				System.out.println(de.toString());
			}
			next = scanner.next();
		}

		// bye invoked
		// update duke.txt with list

		try {
			update(list, filename);
		} catch (IOException e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}

		System.out.println("Bye. Hope to see you again soon!");
		
		scanner.close();
	}

    public static void main(String[] args) throws DukeException {
		new Duke(filename).run();
    }
}
