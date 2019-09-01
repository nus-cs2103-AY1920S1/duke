import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    protected TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    protected TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    protected void addTask(String command) throws IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("The description of a todo cannot be empty.");
        } else if (command.contains("todo")) {
            String[] splitCommand = command.split(" ", 2);
            taskList.add(new ToDos(splitCommand[1]));
        } else if (command.contains("event")) {
            String[] splitCommand = command.split(" ", 2);
            String[] splitEvent = (splitCommand[1].split("/", 2));
            taskList.add(new Events(splitEvent[0], splitEvent[1]));
        } else if (command.contains("deadline")) {
            String[] splitCommand = command.split(" ", 2);
            String[] splitDeadline = (splitCommand[1].split("/", 2));
            taskList.add(new Deadline(splitDeadline[0], splitDeadline[1]));
        }
    }

    protected String deleteTask(String command) throws IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("Give a task to delete.");
        } else  {
            String[] splitCommand = command.split(" ");
            int taskDelete = Integer.valueOf(splitCommand[1]);
            String deleted = taskList.get(taskDelete - 1).getDescription();
            taskList.remove(taskDelete-1);
            return deleted;
        }
    }

    protected String doneTask(String command) throws IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("There must be a number after done.");
        } else {
            String[] splitString = command.split(" ");
            int taskDone = Integer.valueOf(splitString[1]);
            taskList.get(taskDone - 1).markAsDone();
            return taskList.get(taskDone - 1).getDescription();
        }
    }

    protected ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
