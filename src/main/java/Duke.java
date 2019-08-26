import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "    ____________________________________________________________\n";
    private static ArrayList<Task> tasks;

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        // initialise tasks list
        // tasks = new ArrayList<>();

        startDuke();
        greet();
        getUserInput();
    }

    private static void greet() {
        String greeting = LINE
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + LINE;

        System.out.println(greeting);
    }

    private static void getUserInput() {
        String addTaskMessage = "     Got it. I've added this task: ";
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String firstWord = userInput.split("\\s")[0];
        switch (firstWord) {
            case "bye":
                String farewellMessage = LINE
                        + "     Bye. Hope to see you again soon!\n"
                        + LINE;
                System.out.print(farewellMessage);
                exitDuke();
                break;
            case "list":
                System.out.print(LINE);
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task currentTask = tasks.get(i);
                    System.out.println("     " + Integer.toString(i + 1) + "." + currentTask.getTypeIcon() + currentTask.getStatusIcon() + " " + currentTask);
                }
                System.out.print(LINE);
                getUserInput();
                break;
            case "done":
                try {
                    int index = Integer.parseInt(userInput.split("\\s")[1]);
                    Task taskDone = tasks.get(index - 1);
                    taskDone.markAsDone();
                    System.out.print(LINE);
                    System.out.println("     Nice! I've marked this task as done: ");
                    System.out.println("       " + taskDone.getTypeIcon() + taskDone.getStatusIcon() + " " + taskDone);
                    System.out.print(LINE);
                    getUserInput();
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! The task you want to mark as done doesn't exist.");
                    System.out.print(LINE);
                    getUserInput();
                }
                break;
            case "todo":
                try {
                    String messageTodo = userInput.split("todo ")[1];
                    if (messageTodo.trim().length() == 0) {
                        throw new EmptyDescriptionException("The description of a todo cannot be empty.");
                    }
                    Task newTaskTodo = new Task(messageTodo, TaskType.TODO);
                    tasks.add(newTaskTodo);
                    System.out.print(LINE);
                    System.out.println(addTaskMessage);
                    System.out.println("       [T][✗] " + newTaskTodo);
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.print(LINE);
                    getUserInput();
                } catch (ArrayIndexOutOfBoundsException | EmptyDescriptionException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.print(LINE);
                    getUserInput();
                }
                break;
            case "deadline":
                try {
                    String[] deadlineMessageAndTime = userInput.split("deadline ")[1].split(" /by ");
                    String messageDeadline = deadlineMessageAndTime[0];
                    if (messageDeadline.trim().length() == 0) {
                        throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
                    }
                    if (!userInput.split("deadline ")[1].contains("/by")) {
                        throw new DukeException("A deadline requires the '/by' keyword.");
                    }
                    Task newTaskDeadline = new Task(messageDeadline, TaskType.DEADLINE);
                    newTaskDeadline.setTime(deadlineMessageAndTime[1]);
                    tasks.add(newTaskDeadline);
                    System.out.print(LINE);
                    System.out.println(addTaskMessage);
                    System.out.println("       [D][✗] " + newTaskDeadline);
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.print(LINE);
                    getUserInput();
                } catch (ArrayIndexOutOfBoundsException | EmptyDescriptionException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.print(LINE);
                    getUserInput();
                } catch (DukeException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! "  + e);
                    System.out.print(LINE);
                    getUserInput();
                }
                break;
            case "event":
                try {
                    String[] eventMessageAndTime = userInput.split("event ")[1].split(" /at ");
                    String messageEvent = eventMessageAndTime[0];
                    if (messageEvent.trim().length() == 0) {
                        throw new EmptyDescriptionException("The description of an event cannot be empty.");
                    }
                    if (!userInput.split("event ")[1].contains("/at")) {
                        throw new DukeException("An event requires the '/at' keyword.");
                    }
                    Task newTaskEvent = new Task(messageEvent, TaskType.EVENT);
                    newTaskEvent.setTime(eventMessageAndTime[1]);
                    tasks.add(newTaskEvent);
                    System.out.print(LINE);
                    System.out.println(addTaskMessage);
                    System.out.println("       [E][✗] " + newTaskEvent);
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.print(LINE);
                    getUserInput();
                } catch (ArrayIndexOutOfBoundsException | EmptyDescriptionException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! The description of an event cannot be empty.");
                    System.out.print(LINE);
                    getUserInput();
                } catch (DukeException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! " + e);
                    System.out.print(LINE);
                    getUserInput();
                }
                break;
            case "delete":
                try {
                    int index = Integer.parseInt(userInput.split("\\s")[1]);
                    Task taskRemoved = tasks.remove(index - 1);
                    System.out.print(LINE);
                    System.out.println("     Noted. I've removed this task: ");
                    System.out.println("       " + taskRemoved.getTypeIcon() + taskRemoved.getStatusIcon() + " " + taskRemoved);
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.print(LINE);
                    getUserInput();
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! The task you want to delete doesn't exist.");
                    System.out.print(LINE);
                    getUserInput();
                }
                break;
            default:
                try {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! " + e);
                    System.out.print(LINE);
                    getUserInput();
                }
        }
    }

    private static void exitDuke() {
        String toSave = convertTasksToString(tasks);
        try {
            writeToFile("/Users/liuzechu/Desktop/CS2103/project_duke/duke/data/duke.txt", toSave);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FILE NOT SAVED PROPERLY");
        }
    }
    
    private static ArrayList<Task> loadTasksFromFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner scanner = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> result = new ArrayList<>();
        while (scanner.hasNext()) {
            String keyword = scanner.next();
            switch (keyword) {
                case "T":
                    scanner.next(); // skip the pipe
                    boolean isDoneTodo = Boolean.parseBoolean(scanner.next());
                    scanner.next(); // skip the pipe
                    String todoDescription = scanner.next();
                    Task todoTask = new Task(todoDescription, TaskType.TODO);
                    if (isDoneTodo) {
                        todoTask.markAsDone();
                    }
                    result.add(todoTask);
                    break;
                case "D":
                    scanner.next(); // skip the pipe
                    boolean isDoneDeadline = Boolean.parseBoolean(scanner.next());
                    scanner.next(); // skip the pipe
                    String deadlineDescription = scanner.next();
                    scanner.next(); // skip the pipe
                    String deadlineTime = scanner.next();
                    Task deadlineTask = new Task(deadlineDescription, TaskType.DEADLINE);
                    deadlineTask.setTime(deadlineTime);
                    if (isDoneDeadline) {
                        deadlineTask.markAsDone();
                    }
                    result.add(deadlineTask);
                    break;
                case "E":
                    scanner.next(); // skip the pipe
                    boolean isDoneEvent = Boolean.parseBoolean(scanner.next());
                    scanner.next(); // skip the pipe
                    String eventDescription = scanner.next();
                    scanner.next(); // skip the pipe
                    String eventTime = scanner.next();
                    Task eventTask = new Task(eventDescription, TaskType.EVENT);
                    eventTask.setTime(eventTime);
                    if (isDoneEvent) {
                        eventTask.markAsDone();
                    }
                    result.add(eventTask);
                    break;
            }
        }

        return result;
    }

    private static void startDuke() {
        try {
            tasks = loadTasksFromFile("/Users/liuzechu/Desktop/CS2103/project_duke/duke/data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
            tasks = new ArrayList<>();
        }
    }

    private static String convertTasksToString(ArrayList<Task> tasks) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }

        StringBuilder result = new StringBuilder("");

        for (Task task : tasks) {
            TaskType type = task.getType();
            switch (type) {
                case TODO:
                    result.append("T | ");
                    result.append(task.isDone);
                    result.append(" | ");
                    result.append(task.getDescription());
                    result.append("\n");
                    break;
                case DEADLINE:
                    result.append("D | ");
                    result.append(task.isDone);
                    result.append(" | ");
                    result.append(task.getDescription());
                    result.append(" | ");
                    result.append(task.getTime());
                    result.append("\n");
                    break;
                case EVENT:
                    result.append("E | ");
                    result.append(task.isDone);
                    result.append(" | ");
                    result.append(task.getDescription());
                    result.append(" | ");
                    result.append(task.getTime());
                    result.append("\n");
                    break;
            }
        }

        return result.toString();
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}