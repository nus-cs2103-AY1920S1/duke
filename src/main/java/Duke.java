import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];

    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        int counter = 0;

        greeting();

        while (sc.hasNextLine()) {
            try {
                String command = sc.nextLine();
                String[] commandArr = command.split(" ");
                String[] keywords = {};
                int i = 1; // skip the task type

                switch (commandArr[0].toLowerCase()) {

                case "todo":
                    String name = "";
                    checkCommand(commandArr, "todo");
                    for (i = 1; i < commandArr.length; i++) {
                        name += commandArr[i] + " ";
                    }
                    handleTodo(name.trim(), counter);
                    counter++;
                    break;

                case "deadline":
                    keywords = splitCommands(commandArr, "/by", "deadline");
                    handleDeadline(keywords[0], keywords[1], counter);
                    counter++;
                    break;

                case "event":
                    keywords = splitCommands(commandArr, "/at", "event");
                    handleEvent(keywords[0], keywords[1], counter);
                    counter++;
                    break;

                case "list":
                    printList(counter);
                    break;

                case "done":
                    int taskId = Integer.parseInt(commandArr[1]);
                    handleDone(taskId);
                    break;

                case "bye":
                    handleExit();
                    return;

                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                addBorder(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) { // incase of empty input
                addBorder("Input cannot be empty!");
            }
        }

        sc.close();
    }

    public static void addBorder(String input) {

        String border = "____________________________________________________________";

        System.out.println(border + "\n\n" + input + "\n" + border + "\n");
    }

    public static void greeting() {
        addBorder("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public static void checkCommand(String[] commandArr, String keyword) throws DukeException {
        if (commandArr.length < 2) { 
            throw new DukeException("☹ OOPS!!! The description of a " + keyword + " cannot be empty.");
        } 
    }

    /* Parse the commands to obtain task name and time for deadline/event tasks */
    public static String[] splitCommands(String[] commandArr, String keyword, String taskType) throws DukeException {
        String name = "";
        String time = "";
        boolean flag = false;
        int i = 1; // skip the task type
        for (i = 1; i < commandArr.length; i++) {
            if (commandArr[i].equals(keyword)) {
                flag = true;
            } else if (flag) {
                time += commandArr[i] + " ";
            } else {
                name += commandArr[i] + " ";
            }
        }

        // error handling 
        if (name.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
        } else if (!flag) {
            throw new DukeException("Please remember to put " + keyword + " before the timing.");
        } else if (time.isEmpty()) {
            throw new DukeException("Please input the time as well!");
        } else {
            String[] output =  { name.trim(), time.trim() };
            return output;
        }
    }

    public static void addTask(int index, Task task) {
        tasks[index] = task;
        int taskNum = index + 1;
        String feedback = "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + taskNum
                + " tasks in the list.";
        addBorder(feedback);
    }

    public static void handleTodo(String name, int index) {
        addTask(index, new Todo(name));
    }

    public static void handleDeadline(String name, String time, int index) {
        addTask(index, new Deadline(name, time));
    }

    public static void handleEvent(String name, String time, int index) {
        addTask(index, new Event(name, time));
    }

    public static void handleDone(int taskId) {
        Task doneTask = tasks[taskId - 1];
        doneTask.markAsDone();
        String str = "Nice! I've marked this task as done:\n" + " " + doneTask.toString();

        addBorder(str);
    }

    public static void handleExit() {
        addBorder("Bye. Hope to see you again soon!");
    }

    public static void printList(int index) {
        String str = "Here are the tasks in your list:\n";

        for (int i = 1; i < index + 1; i++) {
            if (i == index) {
                str += i + "." + tasks[i - 1];
            } else {
                str += i + "." + tasks[i - 1] + "\n";
            }
        }

        addBorder(str);
    }

}
