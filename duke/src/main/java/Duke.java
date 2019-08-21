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
		  	switch(next) {
				case "todo":
					String desc = scanner.nextLine().trim(); // assuming it takes whatever remains on the line before crossing over \n
					task = new Todo(desc);
					break;

				case "deadline":
					String line = scanner.nextLine().trim();
					int indexStart = line.indexOf(" ");
					int indexBy = line.indexOf("/");
					String desc2 = line.substring(0, indexBy-1); // start after space, end before space before /
					String by = line.substring(indexBy+3);
					task = new Deadline(desc2, by);
					break;

				case "event":
					String line2 = scanner.nextLine().trim();
					int indexStart2 = line2.indexOf(" ");
					int indexAt = line2.indexOf("/");
					String desc3 = line2.substring(0, indexAt-1); // start after space, end before space before /
					String at = line2.substring(indexAt+3);
					task = new Event(desc3, at);
					break;
		  	}
		  	list.add(task);
		  	System.out.println("Got it. I've added this task:\n  " + task.toString()); // not overridden???
		  	System.out.println("Now you have " + list.size() + " tasks in the list.");
		  }
		  next = scanner.next();
		}

		System.out.println("Bye. Hope to see you again soon!");
    }
}
