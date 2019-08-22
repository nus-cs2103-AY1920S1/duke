import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
	static ArrayList<String> memory;
	static String gap = "    ";
	static String line = "____________________________________________________________";
	
	public static void main(String[] args) {
		memory = new ArrayList<>();
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
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String rawInput = sc.nextLine();
			
			if (rawInput.toLowerCase().equals("bye")) {
				System.out.println(gap + line);
				System.out.println(gap + " " + "Bye. Hope to see you again soon!");
				System.out.println(gap + line);
				System.out.println();
				break;
			} else if (rawInput.toLowerCase().equals("list")) {
				System.out.println(gap + line);
				list();
				System.out.println(gap + line);
				System.out.println();
			} else {
				System.out.println(gap + line);
				System.out.println(gap + " " + "added: " + rawInput);
				System.out.println(gap + line);
				System.out.println();
				memory.add(rawInput);
			}
		}
		
		sc.close();
	}
	
	private static void list() {
		int counter = 1;
		for (String s : memory) {
			System.out.println(gap + " " + counter + ". " + s);
			counter++;
		}
	}
}
