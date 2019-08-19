import java.util.Scanner;

public class Duke {
    static Task[] memory = new Task[100];
    static int index = 1;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        activeChat();
    }

    /*
    This method queries for user input and returns the desired result.
     */
    public static void activeChat() {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String[] splitInput = input.split(" ");
            if (input.equals("list")) {
                printList();
            } else if (splitInput[0].equals("done")) {
                int taskNum = Integer.parseInt(splitInput[1]);
                Task target = memory[taskNum - 1];
                target.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + target.getStatusIcon() + "] " + target.description);
            } else {
                Task a = new Task(input);
                memory[index - 1] = a;
                System.out.println("added: " + input);
                index++;
            }
            input = sc.nextLine();
        }
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }

    /*
    This method iterates through the data collected and prints them out in the desired format.
     */
    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index - 1; i++) {
            int id = i + 1;
            Task current = memory[i];
            System.out.println(id + ".[" + current.getStatusIcon() + "] " + current.description);
        }
    }
}

/*
A child class of Object which contains the description of the Task and whether the Task has been done.
 */
class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
