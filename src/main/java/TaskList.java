import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import com.google.gson.Gson;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    public enum TaskType {
        DEADLINE,
        EVENT,
        TODO
    }

    public void addToList(TaskType taskType, String description, String deadline)
            throws IllegalArgumentException {
        Task newTask = null; // task to be added

        // based on task type, create corresponding subclass object
        switch (taskType) {
        case DEADLINE:
            newTask = new Deadline(description, deadline);
            break;
        case EVENT:
            newTask = new Event(description, deadline);
            break;
        case TODO:
            newTask = new Todo(description);
            break;
        default:
            // this should not happen; invalid task type should be caught in caller method already
            System.out.println("Something went wrong!");
            break;
        }

        // add newTask to taskList
        tasks.add(newTask);
        System.out.println("Okay! I've added: " + description
                + ". Use list to see all your tasks!");
        // todo: description has trailing space due to using /at or /by as delimiter. how?
    }

    public void printList() {
        int i = 1;
        for (Iterator<Task> iterator = this.tasks.iterator(); iterator.hasNext(); i++) {
            String s = iterator.next().toString();
            System.out.println(i + ". " + s);
        }
    }

    public Task getTask(int taskIndex) throws IndexOutOfBoundsException {
        return tasks.get(taskIndex - 1);
    }

    private void deleteTask(Task taskToDelete) {
        String taskDescription = taskToDelete.toString();
        tasks.remove(taskToDelete);
        System.out.println("Noted. I've removed this task: " + taskDescription);
        System.out.println("Now you have " + tasks.size() + "items in this list.");
    }

    public void saveToDisk() {
        // create gson to serialise taskList
        Gson gson = new Gson();
        String taskListGson = gson.toJson(this);

        // write taskList to txt file
    }

    public void readFromFile() {
        // read json from file
        // TODO

        // create type token to deal with Arraylist
        // todo: why can't I use Type?????
        Type taskListType = new TypeToken<ArrayList<Task>>(){}.getType();
        this.tasks = gson.fromJson(json, taskListType);
    }
    /**
     * Processes the given input string.
     *
     * @param input input string given by user.
     */
    public void processInput(String input) throws InputMismatchException {
        Scanner inputReader  = new Scanner(input);
        String command = inputReader.next();
        String description;
        String deadline;

        switch (command) {
        case "list":
            // if "list", print list
            this.printList();
            break;
        case "done":
            try {
                int taskId = inputReader.nextInt(); // extract the task ID entered by user
                this.getTask(taskId).markAsDone(); // mark task as done
            } catch (InputMismatchException e) {
                // user input after "done" is not an int
                System.out.println("Oops! You entered an invalid task ID!");
            } catch (NoSuchElementException e) {
                // user input after "done" is blank
                System.out.println("Oops! You did not enter a task ID!");
            } catch (IndexOutOfBoundsException e) {
                // user input after "done" is an invalid task ID
                System.out.println("Oops! You entered an invalid task ID!");
            }
            break;
        case "delete":
            try {
                int taskId = inputReader.nextInt(); // extract the task ID entered by user
                this.deleteTask(getTask(taskId)); // mark task as done
            } catch (InputMismatchException e) {
                // user input after "done" is not an int
                System.out.println("Oops! You entered an invalid task ID!");
            } catch (NoSuchElementException e) {
                // user input after "done" is blank
                System.out.println("Oops! You did not enter a task ID!");
            } catch (IndexOutOfBoundsException e) {
                // user input after "done" is an invalid task ID
                System.out.println("Oops! You entered an invalid task ID!");
            }
            break;
        case "todo":
            try {
                description = inputReader.nextLine().strip();
                deadline = "no deadline";
                this.addToList(TaskList.TaskType.TODO, description, deadline);
            } catch (NoSuchElementException e) {
                // user imput after task type is blank
                System.out.println("Oops! You did not enter a description!");
            }
            break;
        case "event":
            inputReader.useDelimiter("/at");
            try {
                description = inputReader.next().strip();
                deadline = inputReader.next().strip();
                this.addToList(TaskList.TaskType.EVENT, description, deadline);
            } catch (NoSuchElementException e) {
                // user imput after task type is blank
                System.out.println("Oops! You did not enter a description or deadline!");
            }
            break;
        case "deadline":
            inputReader.useDelimiter("/by");
            try {
                description = inputReader.next().strip();
                deadline = inputReader.next().strip();
                this.addToList(TaskList.TaskType.DEADLINE, description, deadline);
            } catch (NoSuchElementException e) {
                // user imput after task type is blank
                System.out.println("Oops! You did not enter a description or deadline!");
            }
            break;
        default:
            throw new InputMismatchException();
        }
    }
}
