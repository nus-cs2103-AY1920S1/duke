import java.util.Scanner;

public class Duke {

    public static Task[] tasks = new Task[100];
    public static int counter = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        sendLine();
        sendGreeting();
        sendLine();

        boolean hasTerminated = false;

        while (!hasTerminated) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                hasTerminated = true;
            } else if (input.equals("list")) {
                sendLine();
                sendTasks();
                sendLine();
            } else if (input.startsWith("done")) {
                String[] parsedData = input.split(" ");
                if (parsedData.length < 2) {
                    sendLine();
                    sendMessage("Error: Please key in the index of the task!");
                    sendLine();
                } else {
                    String todoIndex = parsedData[1];
                    sendLine();
                    completeTask(todoIndex);
                    sendLine();
                }
            } else {
                if (input.startsWith("todo")) {
                    sendLine();
                    addTodoTask(input.substring(5));
                    sendLine();
                } else if (input.startsWith("deadline")) {
                    sendLine();
                    addDeadlineTask(input.substring(9));
                    sendLine();
                } else if (input.startsWith("event")) {
                    sendLine();
                    addEventTask(input.substring(6));
                    sendLine();
                }
            }
        }

        sendLine();
        sendFarewell();;
        sendLine();

    }

    public static void addTask(String todo) {
        if (counter >= 100) {
            sendMessage("You only can add up to 100 tasks!");
        } else {
            tasks[counter] = new Task(todo);
            counter ++;
            sendMessage("Got it. I've added this task: ");
            sendMessage("  " + todo);
            sendMessage("Now you have " + counter + " tasks in the list");
        }
    }

    public static void addTodoTask(String todo) {
        if (counter >= 100) {
            sendMessage("You only can add up to 100 tasks!");
        } else {
            ToDoTask newTask = new ToDoTask(todo);
            tasks[counter] = newTask;
            counter ++;
            sendMessage("Got it. I've added this task: ");
            sendMessage("  " + newTask);
            sendMessage("Now you have " + counter + " tasks in the list");
        }
    }

    public static void addDeadlineTask(String todo) {
        if (counter >= 100) {
            sendMessage("You only can add up to 100 tasks!");
        } else {
            String[] taskData = todo.split(" /by ");
            DeadlineTask newTask = new DeadlineTask(taskData[0], taskData[1]);
            tasks[counter] = newTask;
            counter ++;
            sendMessage("Got it. I've added this task: ");
            sendMessage("  " + newTask);
            sendMessage("Now you have " + counter + " tasks in the list");
        }
    }

    public static void addEventTask(String todo) {
        if (counter >= 100) {
            sendMessage("You only can add up to 100 tasks!");
        } else {
            String[] taskData = todo.split(" /at ");
            EventTask newTask = new EventTask(taskData[0], taskData[1]);
            tasks[counter] = newTask;
            counter ++;
            sendMessage("Got it. I've added this task: ");
            sendMessage("  " + newTask);
            sendMessage("Now you have " + counter + " tasks in the list");
        }
    }

    public static void completeTask(String todoIndex) {
        int index = Integer.parseInt(todoIndex) - 1;
        if (index < 0 || index >= counter) {
            sendMessage("Error: Please key in a valid index!");
        } else {
            Task task = tasks[index];
            task.completed = true;
            sendMessage("Nice! I've marked this task as done:");
            sendMessage("  " + task.toString());
        }
    }

    public static void sendTasks() {
        sendMessage("Here are the tasks in your list:");
        for (int tasknum = 0; tasknum < counter; tasknum ++) {
            Task task = tasks[tasknum];
            String todo = task.toString();
            if (task.completed) {
                sendMessage((tasknum + 1) + "." + todo);
            } else {
                sendMessage((tasknum + 1) + "." + todo);
            }
        }
    }

    public static void sendLine() {
        System.out.println("____________________________________________________________");
    }

    public static void sendGreeting() {
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
    }

    public static void sendFarewell() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public static void sendMessage(String input) {
        System.out.println(" " + input);
    }
}
