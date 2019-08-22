import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
	static ArrayList<Task> memory;
	static String line = "____________________________________________________________";
	
	public static void main(String[] args) {
		memory = new ArrayList<>();
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
				int listIndex = Integer.parseInt(rawInput.split(" ")[1]) - 1;
				memory.get(listIndex).markAsDone();
				System.out.println(line);
				System.out.println(" " + "Nice! I've marked this task as done:");
				System.out.println("   " + memory.get(listIndex).showTask());
				System.out.println(line);
				System.out.println();
			} else if (rawInput.toLowerCase().startsWith("todo")) {
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
			} else if (rawInput.toLowerCase().startsWith("deadline")) {
				String[] deadlineInput = rawInput.substring(9).split("/by");
				String description = deadlineInput[0].trim();
				String by = deadlineInput[1].trim();
				Task newTask = new Deadline(description, by);
				memory.add(newTask);
				
				System.out.println(line);
				System.out.println(" " + "Got it. I've added this task:");
				System.out.println("   " + newTask.showTask());
				if (memory.size() == 1) System.out.println(" " + "Now you have 1 task in your list");
				else System.out.println(" " + "Now you have " + memory.size() + " tasks in your list.");
				System.out.println(line);
				System.out.println();
			} else if (rawInput.toLowerCase().startsWith("event")) {
				String[] eventInput = rawInput.substring(6).split("/at");
				String description = eventInput[0].trim();
				String at = eventInput[1].trim();
				Task newTask = new Event(description, at);
				memory.add(newTask);
				
				System.out.println(line);
				System.out.println(" " + "Got it. I've added this task:");
				System.out.println("   " + newTask.showTask());
				if (memory.size() == 1) System.out.println(" " + "Now you have 1 task in your list");
				else System.out.println(" " + "Now you have " + memory.size() + " tasks in your list.");
				System.out.println(line);
				System.out.println();
			} else {
				memory.add(new Task(rawInput));
				
				System.out.println(line);
				System.out.println(" " + "Got it. I've added this task:");
				System.out.println(" " + "added: " + rawInput);
				if (memory.size() == 1) System.out.println(" " + "Now you have 1 task in your list");
				else System.out.println(" " + "Now you have " + memory.size() + " tasks in your list.");
				System.out.println(line);
				System.out.println();
			}
		}
		
		sc.close();
	}
	
	private static void list() {
		int counter = 1;
		for (Task t : memory) {
			System.out.println(" " + counter + "." + t.showTask());
			counter++;
		}
	}
}
