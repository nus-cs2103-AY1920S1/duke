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
        String response;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            switch (command) {
            case ("bye"):
                response = "exit";
                break;
            case ("list"):
                response = taskList.displayAllTasks();
                break;
            case ("done"):
                int taskNo = Integer.parseInt(sc.next());
                response = taskList.markTaskAsDone(taskNo);
                break;
            case ("todo"):
                String description = sc.nextLine().trim();
                if (description.equals("")) {
                    response = "Oops! The description of a todo cannot be empty.";
                } else {
                    ToDo newToDo = new ToDo(description);
                    response = taskList.addTask(newToDo);
                }
                break;
            case ("deadline"):
                String deadlineDesc = sc.nextLine().trim();
                Deadline newDeadline = new Deadline(deadlineDesc.split(" /by ")[0],
                        dateFormatter.parse(deadlineDesc.split(" /by ")[1]));
                response = taskList.addTask(newDeadline);
                break;
            case ("event"):
                String eventDesc = sc.nextLine().trim();
                Event newEvent = new Event(eventDesc.split(" /at ")[0],
                        dateFormatter.parse(eventDesc.split(" /by ")[1]));
                response = taskList.addTask(newEvent);
                break;
            case ("delete"):
                int index = Integer.parseInt(sc.nextLine().trim());
                response = taskList.deleteTask(index);
                break;
            case ("find"):
                String keyword = sc.nextLine().trim();
                response = taskList.findTasks(keyword);
                break;
            case ("joke"):
                response = Joke.getJoke();
                break;
            default:
                response = "Oops! I'm sorry, but I don't know what that means :-(";
            }

            // write all tasks to file
            FileWriter fw;
            try {
                fw = new FileWriter("tasks.txt");
                String dataStr = "";
                for (Task task : taskList.getTasks()) {
                    dataStr += task.toStorageString() + System.lineSeparator();
                }
                fw.write(dataStr);
                fw.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }

            return response;
        } catch (ParseException e) {
            return "Error!";
        }

    }
}
