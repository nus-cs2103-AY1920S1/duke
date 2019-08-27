import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Parses user input and carries out corresponding actions.
 * Creates, deletes, marks as done, or lists all tasks as per user input,
 */
public class Parser {

    private static Scanner sc;

    /**
     * Constructor method for parser.
     */
    public Parser() {
    }

    /**
     * Parses user input and carries out appropriate actions.
     *
     * @param inputString Line of input from user.
     * @param taskList Existing list of tasks to be modified.
     * @return "continue" or "exit", depending on whether the user quit the program.
     */
    public static String parseInput(String inputString, TaskList taskList) {
        sc = new Scanner(inputString);
        String command = sc.next();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            switch (command) {
            case ("bye"):
                return "exit";
            case ("list"):
                taskList.displayAllTasks();
                break;
            case ("done"):
                int taskNo = Integer.parseInt(sc.next());
                taskList.markTaskAsDone(taskNo);
                break;
            case ("todo"):
                try {
                    String description = sc.nextLine().trim();
                    if (description.equals("")) throw new EmptyDescriptionException(
                            "☹ OOPS!!! The description of a todo cannot be empty.");
                    ToDo newToDo = new ToDo(description);
                    taskList.addTask(newToDo);
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.toString());
                }
                break;
            case ("deadline"):
                String deadlineDesc = sc.nextLine().trim();
                Deadline newDeadline = new Deadline(deadlineDesc.split(" /by ")[0],
                        dateFormatter.parse(deadlineDesc.split(" /by ")[1]));
                taskList.addTask(newDeadline);
                break;
            case ("event"):
                String eventDesc = sc.nextLine().trim();
                Event newEvent = new Event(eventDesc.split(" /at ")[0],
                        dateFormatter.parse(eventDesc.split(" /by ")[1]));
                taskList.addTask(newEvent);
                break;
            case ("delete"):
                int index = Integer.parseInt(sc.nextLine().trim());
                taskList.deleteTask(index);
                break;
            default:
                throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            // write all tasks to file
            FileWriter fw;
            try {
                fw = new FileWriter("tasks.txt");
                String dataStr = "";
                for (Task task : taskList.tasks) {
                    String isDone = task.isDone
                            ? "1"
                            : "0";
                    dataStr += task.toStorageString() + System.lineSeparator();
                }
                fw.write(dataStr);
                fw.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }

            return "continue";
        } catch (InvalidCommandException | ParseException e) {
            System.out.println(e.toString());
            return "continue";
        }

    }
}
