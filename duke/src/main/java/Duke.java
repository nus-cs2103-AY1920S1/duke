import java.util.*;

public class Duke {
    public static void main(String[] args) {
	  ArrayList<String> list = new ArrayList<>();
      /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
	*/
		System.out.println("Hello! I'm Duke\n What can I do for you?");

		Scanner scanner = new Scanner(System.in);
		String next = scanner.nextLine();

		while (!next.equals("bye")) {
		  if (next.equals("list")) {
			for (int i = 0; i < list.size(); i++) {
			  System.out.println((i+1) + ". " + list.get(i));
			}
		  } else {
			list.add(next);
			System.out.println("added: " + next);
		  }
		  next = scanner.nextLine();
		}

		System.out.println("Bye. Hope to see you again soon!");
    }
}
