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
        String[] cmd = new String[100];
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
                for (int i = 0; i < count; i ++) {
                    int num = i + 1;
                    System.out.println(num + ". " + cmd[i]);
                }
                printLineS();
            } else {
                cmd[count] = command;
                printLine();
                System.out.println("added: " + command);
                printLineS();
                count++;
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
