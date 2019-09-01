import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasklist;

    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    public TaskList() {
        this.tasklist = new ArrayList<Task>();
    }

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

    protected ArrayList<Task> findTasks(String command) throws IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("There must be an input to find.");
        } else {
            String[] splitString = command.split(" ",  2);
            ArrayList<Task> foundTasks = new ArrayList<>();
            for (Task task : this.tasklist) {
                if (task.toString().contains(splitString[1])) {
                    foundTasks.add(task);
                }
            }
            return foundTasks;
        }
    }

    public ArrayList<Task> getTasklist() {
        return this.tasklist;
    }
}
