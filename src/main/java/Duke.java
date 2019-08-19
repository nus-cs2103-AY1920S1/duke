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
        String input = sc.next();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList();
            } else if (input.equals("done")) {
                int taskNum = sc.nextInt();
                doneTask(taskNum);
            } else if (input.equals("todo")) {
                String TodoTask = sc.nextLine();
                toDoTask(TodoTask);
                index++;
            } else if (input.equals("event")) {
                String EventTask = sc.nextLine();
                eventTask(EventTask);
                index++;
            } else if (input.equals("deadline")) {
                String DeadlineTask = sc.nextLine();
                deadlineTask(DeadlineTask);
                index++;
            }
            input = sc.next();
        }
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }

    /*
    This method marks the targeted task as done and provides confirmation to the user through output.
     */
    public static void doneTask(int taskNum) {
        Task target = memory[taskNum - 1];
        target.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + target.getStatusIcon() + "] " + target.description);
    }

    /*
    This method iterates through the data collected and prints them out in the desired format.
     */
    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index - 1; i++) {
            int id = i + 1;
            Task current = memory[i];
            System.out.println(id + "." + current.toString());
        }
    }

    /*
    This method creates a ToDo object and adds it into memory.
     */
    public static void toDoTask(String a) {
        ToDo newTodo = new ToDo(a);
        memory[index - 1] = newTodo;
        printTask(newTodo);
    }

    /*
    This method creates an Event object and adds it into memory.
     */
    public static void eventTask(String b) {
        String[] details = b.split("/at");
        Event newEvent = new Event(details[0], details[1]);
        memory[index - 1] = newEvent;
        printTask(newEvent);
    }

    /*
    This method creates a deadlineTask and adds it into memory.
     */
    public static void deadlineTask(String c) {
        String[] details = c.split("/by");
        Deadline newDeadline = new Deadline(details[0], details[1]);
        memory[index - 1] = newDeadline;
        printTask(newDeadline);
    }

    public static void printTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + index + " tasks in the list.");
    }
}

