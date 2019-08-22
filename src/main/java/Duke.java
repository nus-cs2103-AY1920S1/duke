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
        Task taskList[] = new Task[100];

        while(!input.equals("bye")) {
            if(input.matches("done\\s+\\d+")) {
                System.out.println("Nice! I've marked this task as done:");
                Task doneTask = taskList[Integer.valueOf(input.replaceAll("done\\s+", "")) - 1];
                doneTask.markAsDone();
                System.out.println(doneTask + "\n");
            } else if(!input.equals("list")) {
                taskList[numTask] = new Task(input);
                numTask++;
                System.out.println(String.format("added: %s\n", input));
            } else {
                System.out.println("Here are the tasks in your list:");
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