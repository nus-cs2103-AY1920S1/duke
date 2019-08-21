import java.util.Scanner;

public class Duke {

    static String[] taskList = new String[100];
    static int number = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        addList(sc);
    }

    public static void addList(Scanner sc) {
        while(sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                return;
            } else if (command.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < number; i++) {
                    int index = i + 1;
                    System.out.println("     " + index + ". " + taskList[i]);
                }
                System.out.println("    ____________________________________________________________");
            } else {
                taskList[number] = command;
                number++;
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + command);
                System.out.println("    ____________________________________________________________");
            }
        }
    }

}
