import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = String.format("\n%s\n%s\n", "Hello! I'm Duke", "What can I do for you?");
        System.out.println(logo + greeting);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int numTask = 0;
        String taskList[] = new String[100];

        while(!input.equals("bye")) {
            if(!input.equals("list")) {
                taskList[numTask] = input;
                numTask++;
                System.out.println(String.format("added: %s\n", input));
            } else {
                for (int i = 0; i < numTask; i++) {
                    System.out.println(String.format("%d. %s", i + 1, taskList[i]));
                }
                System.out.println();
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}