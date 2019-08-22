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
				System.out.println(memory.get(listIndex).showTask());
				System.out.println(line);
				System.out.println();
			} else {
				System.out.println(line);
				System.out.println(" " + "added: " + rawInput);
				System.out.println(line);
				System.out.println();
				memory.add(new Task(rawInput));
			}
		}
		
		sc.close();
	}
	
	private static void list() {
		int counter = 1;
		for (Task t : memory) {
			System.out.println(t.showTask(counter));
			counter++;
		}
	}
}
