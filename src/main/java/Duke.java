import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    /**
     * Main function for the class.
     * @param args Arguments passed when running the program.
     */
    public static void main(String[] args) {
     
        // constants and required objects
        Scanner sc = new Scanner(System.in);
        final String welcomeStr = "Hello! I'm Duke :)\n     What can I do for you?";
        final String endStr = "Bye. Hope to see you again soon!";

        // Print initial welcome string
        prettyPrint(welcomeStr);

        // run the tasks till user says bye
        try {
            TaskList tl = readTasksFromFile();
            String input = sc.nextLine();
            while (!input.equalsIgnoreCase("bye")) {
                try {
                    process(input, tl);
                } catch (DukeException e) {
                    prettyPrint(String.format("☹ OOPS!!! %s", e.getMessage()));
                } catch (Exception e) {
                    prettyPrint("☹ OOPS!!! An unknown error occurred. :(");
                }
                input = sc.nextLine();
            }
            sc.close();
        } catch (DukeException e) {
            prettyPrint(String.format("☹ OOPS!!! %s", e.getMessage()));
        }
        
        // Print exit string
        prettyPrint(endStr);
    }

    // pretty print a string
    private static void prettyPrint(String str) {
        System.out.println("    --------------------------------------------------");
        System.out.println("     " + str);
        System.out.println("    --------------------------------------------------");
    }

    // function to read tasks from a file
    private static TaskList readTasksFromFile() throws DukeException {
        TaskList tl = new TaskList();
        Path path = Paths.get("data/duke.txt");
        try {
            List<String> tasks = Files.readAllLines(path);
            for (String task : tasks) {
                tl.addTaskWithoutMessage(readTask(task));
            }
            tl.listTasks();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Was not able to read the file. :(");
        }

        return tl;
    }

    // function to write tasks to a file
    private static void writeTasksToFile(TaskList tl) throws DukeException {
        try {
            Path path = Paths.get("data/duke.txt");
            BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"));
            for (Task task : tl.getTasks()) {
                writer.write(writeTask(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Was not able to write to the file. :(");
        }
    }

    // function to read tasks from a single line
    private static Task readTask(String task) throws DukeException {
        String[] taskParams = task.split(" - ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Task returnTask = null;
        switch (taskParams[0]) {
        case "T":
            ToDo todo = new ToDo(taskParams[2]);
            if (Integer.parseInt(taskParams[1]) == 1) {
                todo.markDone();
            }
            returnTask = todo;
            break;
        case "E":
            try {
                Event event = new Event(taskParams[2], format.parse(taskParams[3]));
                if (Integer.parseInt(taskParams[1]) == 1) {
                    event.markDone();
                }
                returnTask = event;
                break;
            } catch (ParseException e) {
                throw new DukeException("Incorrect date format given. :(");
            }
        case "D":
            try {
                Deadline dl = new Deadline(taskParams[2], format.parse(taskParams[3]));
                if (Integer.parseInt(taskParams[1]) == 1) {
                    dl.markDone();
                }
                returnTask = dl;
                break;
            } catch (ParseException e) {
                throw new DukeException("Incorrect date format given. :(");
            }
        default:
            break;
        }
        return returnTask;
    }

    // generate string to represent task
    private static String writeTask(Task task) {
        String taskStr = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String doneStr = task.isTaskDone() ? "1" : "0";
        switch (task.getType()) {
        case TODO:
            taskStr = String.format("T - %s - %s", doneStr, task.getName());
            break;
        case EVENT:
            taskStr = String.format("E - %s - %s - %s", doneStr, task.getName(), format.format(task.getDate()));
            break;
        case DEADLINE:
            taskStr = String.format("D - %s - %s - %s", doneStr, task.getName(), format.format(task.getDate()));
            break;
        default:
            break;
        }
        return taskStr;
    }

    // run a process
    private static void process(String input, TaskList tl) throws DukeException {
        String command = input.split(" ")[0];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        switch (command) {
        case "list":
            tl.listTasks();
            break;
        case "done":
            if (input.split(" ").length <= 1) {
                throw new DukeException("Please enter an index to delete.");
            }
            tl.taskDone(Integer.parseInt(input.split(" ")[1]));
            writeTasksToFile(tl);
            break;
        case "todo":
            if (input.split(" ").length <= 1) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            ToDo todo = new ToDo(input.split(" ", 2)[1]);
            tl.addTask(todo);
            writeTasksToFile(tl);
            break;
        case "deadline":
            if (input.split(" ", 2).length <= 1) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String deadlineStr = input.split(" ", 2)[1];
            if (deadlineStr.split(" /by ").length <= 1) {
                throw new DukeException("The date of a deadline cannot be empty.");
            }
            String deadlineName = deadlineStr.split(" /by ")[0];
            try {
                Date deadlineDate = format.parse(deadlineStr.split(" /by ")[1]);
                Deadline deadline = new Deadline(deadlineName, deadlineDate);
                tl.addTask(deadline);
                writeTasksToFile(tl);
                break;
            } catch (ParseException e) {
                throw new DukeException("Please enter date in the correct format. :(");
            }
        case "event":
            if (input.split(" ").length <= 1) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            String eventStr = input.split(" ", 2)[1];
            if (eventStr.split(" /at ").length <= 1) {
                throw new DukeException("The date of an event cannot be empty.");
            }
            String eventName = eventStr.split(" /at ")[0];
            try {
                Date eventDate = format.parse(eventStr.split(" /at ")[1]);
                Event event = new Event(eventName, eventDate);
                tl.addTask(event);
                writeTasksToFile(tl);
                break;
            } catch (ParseException e) {
                throw new DukeException("Please enter date in the correct format. :(");
            }
        case "delete":
            if (input.split(" ").length <= 1) {
                throw new DukeException("Please provide an index to delete.");
            }
            tl.removeTask(Integer.parseInt(input.split(" ")[1]));
            writeTasksToFile(tl);
            break;
        default:
            prettyPrint("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
    }
}
