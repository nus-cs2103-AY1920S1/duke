import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 *  CS2103 iP Deliverable, Duke
 *  @author Ahmed Bahajjaj
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = storage.loadTasks();
    }

    public void run() {
        boolean isActive = true;
        Ui.out("What can I do for you?");
        while(isActive) {
            try{
                String input = ui.read();
                String task = input.split(" ")[0];
                int value;
                switch (task) {
                case "bye":
                    Ui.out("Bye. Hope to see you again soon!");
                    isActive = false;
                    break;
                case "list":
                    ui.list(taskList);
                    break;
                case "done":
                    value = index(input, taskList.size());
                    taskList.get(value - 1).setDone();
                    Ui.out("Nice! I've marked this task as done:");
                    Ui.out(taskList.get(value - 1));
                    break;
                case "delete":
                    value = index(input, taskList.size());
                    Ui.out("Noted. I've removed this task:");
                    Ui.out(taskList.get(value - 1));
                    taskList.remove(value - 1);
                    Ui.out("Now you have " + taskList.size() + " tasks in the list.");
                    break;
                default:
                    taskList.add(create(task, input));
                    Ui.out("Got it. I've added this task:");
                    Ui.out(taskList.get(taskList.size() - 1));
                    Ui.out("Now you have " + taskList.size() + " tasks in the list.");
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                Ui.out("OOPS!!! The details of an Event/Deadline cannot be empty! :(");
            } catch (UnsupportedOperationException ex) {
                Ui.out("OOPS!!! I'm sorry, but I don't know what that means! :(");
            } catch (NumberFormatException ex) {
                Ui.out("Please only complete/delete tasks on the list! D:");
            }
        }
        ui.close();
        storage.writeTasks(taskList);
    }

    /**
     * Returns the index of the list to be managed
     * @param input User command
     * @param size Size of task list
     * @return User value specified
     * @throws NumberFormatException If number out of range or invalid input
     */
    private int index(String input, int size) throws NumberFormatException {
        int value = Integer.parseInt(input.substring(input.length() - 1));
        if (value < 0 || value > size) {
            throw new NumberFormatException();
        }
        return value;
    }

    /**
     * Creates Task from input command and description
     * @param task User input separated by spaces
     * @param input User input unprocessed
     * @return Task
     * @throws UnsupportedOperationException Command not understood
     */
    public static Task create(String task, String input) throws UnsupportedOperationException {
        input = input.replace(task, "");
        switch (task) {
        case "todo":
            return new Todo(input);
        case "deadline":
            String[] dead = Parser.process(input);
            return new Deadline(dead[0], Parser.getDate(dead[1]));
        case "event":
            String[] event = Parser.process(input);
            return new Event(event[0], Parser.getDate(dead[1]));
        default:
            throw new UnsupportedOperationException();
        }
    }
}
