import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>(); // changed data structure

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
            try {
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
                        throw new InvalidTaskIndexException();
                    } else {
                        String todoIndex = parsedData[1];
                        completeTask(todoIndex);
                    }
                } else {
                    if (input.startsWith("todo")) {
                        if (tasks.size() >= 100) {
                            throw new TooManyTasksException();
                        }
                        addTodoTask(input.substring(4));
                    } else if (input.startsWith("deadline")) {
                        if (tasks.size() >= 100) {
                            throw new TooManyTasksException();
                        }
                        addDeadlineTask(input.substring(8));
                    } else if (input.startsWith("event")) {
                        if (tasks.size() >= 100) {
                            throw new TooManyTasksException();
                        }
                        addEventTask(input.substring(5));
                    } else {
                        throw new UnknownCommandException();
                    }
                }
            } catch (Exception error) {
                sendLine();
                sendMessage(error.toString());
                sendLine();
            }
        }

        sendLine();
        sendFarewell();;
        sendLine();
    }

    public static void addTodoTask(String todo) throws InvalidToDoException {
        if (todo.trim().equals("")) {
            throw new InvalidToDoException();
        }
        ToDoTask newTask = new ToDoTask(todo.trim());
        tasks.add(newTask);
        sendLine();
        sendMessage("Got it. I've added this task: ");
        sendMessage("  " + newTask);
        sendMessage("Now you have " + tasks.size() + " tasks in the list");
        sendLine();
    }

    public static void addDeadlineTask(String todo) throws InvalidDeadlineException {
        if (todo.trim().equals("")) {
            throw new InvalidDeadlineException("The description of a deadline cannot be empty.");
        } else if (!todo.trim().contains("/by")) {
            throw new InvalidDeadlineException("Please include the time of a deadline.");
        }
        String[] taskData = todo.trim().split(" /by ");
        if (taskData.length == 1) {
            throw new InvalidDeadlineException("The time of a deadline cannot be empty.");
        }
        DeadlineTask newTask = new DeadlineTask(taskData[0], taskData[1]);
        tasks.add(newTask);
        sendLine();
        sendMessage("Got it. I've added this task: ");
        sendMessage("  " + newTask);
        sendMessage("Now you have " + tasks.size() + " tasks in the list");
        sendLine();
    }

    public static void addEventTask(String todo)throws InvalidEventException {
        if (todo.trim().equals("")) {
            throw new InvalidEventException("The description of an event cannot be empty.");
        } else if (!todo.trim().contains("/at")) {
            throw new InvalidEventException("Please include the time of an event.");
        }
        String[] taskData = todo.trim().split(" /at ");
        if (taskData.length == 1) {
            throw new InvalidEventException("The time of an event cannot be empty.");
        }
        EventTask newTask = new EventTask(taskData[0], taskData[1]);
        tasks.add(newTask);
        sendLine();
        sendMessage("Got it. I've added this task: ");
        sendMessage("  " + newTask);
        sendMessage("Now you have " + tasks.size() + " tasks in the list");
        sendLine();
    }

    public static void completeTask(String todoIndex) throws InvalidTaskIndexException {
        int index = Integer.parseInt(todoIndex) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskIndexException();
        } else {
            Task task = tasks.get(index);
            task.completed = true;
            sendLine();
            sendMessage("Nice! I've marked this task as done:");
            sendMessage("  " + task.toString());
            sendLine();
        }
    }

    public static void sendTasks() {
        sendMessage("Here are the tasks in your list:");
        for (int tasknum = 0; tasknum < tasks.size(); tasknum ++) {
            Task task = tasks.get(tasknum);
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
