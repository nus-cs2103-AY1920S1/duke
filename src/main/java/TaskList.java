import java.util.ArrayList;

/**
 * TaskList is a class to process all the commands and adds them into
 * the tasklist. It is to split the Strings and allocate them accordingly
 * to the different Task classes.
 */
public class TaskList {
    ArrayList<Task> tasklist;

    /**
     * Instantiate the TaskList class by passing an Arraylist as a parameter.
     * @param tasklist A list to store all the tasks written by the User.
     */
    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * Instantiate the TaskList class when there are no existing tasks in the .txt file.
     * A new Arraylist is created to store the tasks.
     */
    public TaskList() {
        this.tasklist = new ArrayList<Task>();
    }

    /**
     * Add tasks to the tasks list
     * @param command The description of task and date/time(if applicable) from the User
     * @throws IllegalCommandException If the User inputs a wrong/invalid command.
     */
    public void addtask(String command) throws IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("The description of a todo cannot be empty.");
        } else if (command.contains("todo")) {
            String[] splitcommand = command.split(" ", 2);
            tasklist.add(new ToDos(splitcommand[1]));
        } else if (command.contains("event")) {
            String[] splitcommand = command.split(" ", 2);
            String[] splitevent = (splitcommand[1].split("/", 2));
            tasklist.add(new Events(splitevent[0], splitevent[1]));
        } else if (command.contains("deadline")) {
            String[] splitcommand = command.split(" ", 2);
            String[] splitdeadline = (splitcommand[1].split("/", 2));
            tasklist.add(new Deadline(splitdeadline[0], splitdeadline[1]));
        }
    }

    /**
     * Delete the task that the User inputs in number. Eg. (User types "delete 3" means to
     * delete the task 3 from the tasks list).
     * @param command The command delete and the number of the task User wants to delete
     * @return the updated tasks list after deletion
     * @throws IllegalCommandException if User did not enter a number after delete or invalid
     * command.
     */
    public String deletetask(String command) throws IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("Give a task to delete.");
        } else  {
            String[] splitcommand = command.split(" ");
            int taskdelete = Integer.valueOf(splitcommand[1]);
            String deleted = tasklist.get(taskdelete - 1).getDescription();
            tasklist.remove(taskdelete-1);
            return deleted;
        }
    }

    /**
     * Set the status of the selected task to be done. Eg. (User inputs "done 3" means User
     * wants to indicate that task 3 in the list is completed).
     * @param command The command done and the number of the task User set as done.
     * @return Updated tasks list with the selected task being marked done.
     * @throws IllegalCommandException if User did not enter a number after delete or invalid
     * command.
     */
    public String donetask(String command) throws IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("There must be a number after done.");
        } else {
            String splitstring[] = command.split(" ");
            int taskdone = Integer.valueOf(splitstring[1]);
            tasklist.get(taskdone - 1).markasdone();
            return tasklist.get(taskdone - 1).getDescription();
        }
    }

    /**
     * Other classes can retrieve the tasks list
     * @return The tasks list.
     */
    public ArrayList<Task> getTasklist() {
        return this.tasklist;
    }
}
