import java.util.*;

public class Duke {
    public static void main(String[] args) {
		ArrayList<Task> list = new ArrayList<>();
		/*
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println("Hello from\n" + logo);
		*/
		System.out.println("Hello! I'm Duke\nWhat can I do for you?");

		Scanner scanner = new Scanner(System.in);
		String next = scanner.next(); // no longer need nextline because adding comes with type of task

		while (!next.equals("bye")) {
			if (next.equals("list")) {
				System.out.println("Here are the tasks in your list:");
				for (int i = 0; i < list.size(); i++) {
					System.out.println((i+1) + "." + list.get(i).toString());
				}
			} else if (next.equals("done")) {
				int index = scanner.nextInt(); // since scanner only took in the word done
				Task task = list.get(index-1);
				task.markAsDone();
				System.out.println("Nice! I've marked this task as done:\n  " + task.toString());
			} else {
				Task task = new Task("");
				String desc = "";
				try {
					String line = scanner.nextLine().trim(); // would be \n if incorrect input
					switch (next) {
						case "todo":
							if (line.isEmpty()) throw new DukeException("☹ OOPS!!! The description of a " + next + " cannot be empty.");
							desc = line; // assuming it takes whatever remains on the line before crossing over \n
							task = new Todo(desc);
							break;

						case "deadline":
							if (line.isEmpty()) throw new DukeException("☹ OOPS!!! The description of a " + next + " cannot be empty.");
							int indexBy = line.indexOf("/"); // potential source of error
							desc = line.substring(0, indexBy - 1); // start after space, end before space before /
							String by = line.substring(indexBy + 3);
							task = new Deadline(desc, by);
							break;

						case "event":
							if (line.isEmpty()) throw new DukeException("☹ OOPS!!! The description of a " + next + " cannot be empty.");
							int indexAt = line.indexOf("/");
							desc = line.substring(0, indexAt - 1); // start after space, end before space before /
							String at = line.substring(indexAt + 3);
							task = new Event(desc, at);
							break;

						default:
							throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
					}
					list.add(task);
					System.out.println("Got it. I've added this task:\n  " + task.toString()); // not overridden???
					System.out.println("Now you have " + list.size() + " tasks in the list.");
				} catch (DukeException de) {
					System.out.println(de.toString());
				}
			}
			next = scanner.next();
		}

		System.out.println("Bye. Hope to see you again soon!");
    }
}
