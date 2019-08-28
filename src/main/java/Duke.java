import java.text.ParseException;
import java.util.Scanner;
import java.util.List;


public class Duke {
    private static List<Task> tasks;

    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\\\___|\n";

        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        Storage storage = new Storage();

        tasks = storage.readFromFile();

        int counter = 0;

        greeting();

        while (sc.hasNextLine()) {
            try {
                String command = sc.nextLine();
                String[] commandArr = command.split(" ");
                String[] keywords;
                int i; // skip the task type
                int taskId;

                switch (commandArr[0].toLowerCase()) {

                case "todo":
                    String name = "";
                    checkCommand(commandArr, "todo");
                    for (i = 1; i < commandArr.length; i++) {
                        name += commandArr[i] + " ";
                    }
                    addTask(new Todo(name.trim()));
                    storage.writeToFile();
                    counter++;
                    break;

                case "deadline":
                    keywords = splitCommands(commandArr, "/by", "deadline");
                    addTask(new Deadline(keywords[0], new StringToDate(keywords[1])));
                    storage.writeToFile();
                    counter++;
                    break;

                case "event":
                    keywords = splitCommands(commandArr, "/at", "event");
                    addTask(new Event(keywords[0], new StringToDate(keywords[1])));
                    storage.writeToFile();
                    counter++;
                    break;

                case "list":
                    printList();
                    break;

                case "done":
                    taskId = Integer.parseInt(commandArr[1]);
                    handleDone(taskId);
                    storage.writeToFile();
                    break;

                case "delete":
                    taskId = Integer.parseInt(commandArr[1]);
                    handleDelete(taskId);
                    storage.writeToFile();
                    break;

                case "bye":
                    storage.writeToFile();
                    handleExit();
                    return;

                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                addBorder(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) { // incase of empty input
                addBorder("Input cannot be empty!");
            } catch (ParseException e) {
                addBorder("Please enter the date in the format dd-MMM-yyyy HH:mm");
            }
        }
        sc.close();
    }

    public static void addBorder (String input){

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
    public static String[] splitCommands (String[]commandArr, String keyword, String taskType) throws DukeException {
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
            String[] output = {name.trim(), time.trim()};
            return output;
        }
    }

    public static void addTask(Task task) {
        tasks.add(task);
        int taskNum = tasks.size();
        String feedback = "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + taskNum
                + " tasks in the list.";
        addBorder(feedback);
    }

    public static void handleDone (int taskId) throws DukeException {
        String str;
        if (taskId > tasks.size()) {
            throw new DukeException("Please choose a task within the list");
        } else {
            Task doneTask = tasks.get(taskId - 1);
            doneTask.markAsDone();
            str = "Nice! I've marked this task as done:\n" + " " + doneTask.toString();
            addBorder(str);
        }
    }

    public static void handleDelete (int taskId) throws DukeException {
        String str = "";
        if (taskId > tasks.size()) {
            throw new DukeException("Please choose a task within the list");
        } else {
            Task toDelete = tasks.remove(taskId - 1);
            str = "Noted. I've removed this task:\n" + " " + toDelete.toString() + "\nNow you have " + tasks.size()
                    + " tasks in the list.";
            addBorder(str);
        }
    }

    public static void handleExit () {
        addBorder("Bye. Hope to see you again soon!");
    }

    public static void printList () {
        String str = "Here are the tasks in your list:\n";

        for (int i = 1; i < tasks.size() + 1; i++) {
            if (i == tasks.size()) {
                str += i + "." + tasks.get(i - 1);
            } else {
                str += i + "." + tasks.get(i - 1) + "\n";
            }
        }

        addBorder(str);
    }
}
