import java.util.Scanner;

public class Duke {
	static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        */
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLineS();
        Task[] cmd = new Task[100];
        int count = 0;

        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLineS();
                break;
            } else if (command.equals("list")) {
                printLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i ++) {
                    int num = i + 1;
                    System.out.println(num + ". " + cmd[i]);
                }
                printLineS();
            } else {
            	if (command.substring(0, 4).equals("done")) {
            		String num = command.substring(5, 6);
            		int res = Integer.parseInt(num);
            		Task t = cmd[res - 1];
            		t.markAsDone();
            		printLine();
            		System.out.println("Nice! I've marked this task as done: ");
            		System.out.println(t);
            		printLineS();
            	} else {
	            	cmd[count] = new Task(command);
	                printLine();
	                System.out.println("added: " + command);
	                printLineS();
	                count++;
            	}
            }
        }
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printLineS() {
        System.out.println("____________________________________________________________\n");

    }
}
