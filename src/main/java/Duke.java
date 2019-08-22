import java.util.Scanner;

public class Duke {
	
	public static void main(String[] args) {
		String gap = "    ";
		String line = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        
		System.out.println(gap + line);
		System.out.println(gap + " Hello! I'm Duke");
		System.out.println(gap + " What can I do for you?");
		System.out.println(gap + line);
		
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String command = sc.nextLine();
			if (!command.toLowerCase().equals("bye")) {
				System.out.println(gap + line);
				System.out.println(gap + " " + command);
				System.out.println(gap + line);
			} else {
				System.out.println(gap + line);
				System.out.println(gap + " " + "Bye. Hope to see you again soon!");
				System.out.println(gap + line);
				break;
			}
		}
		
		sc.close();
	}
}
