package duke.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TaskList {
    private static final String FILE_PATH = "data/duke.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yyyy HHmm");

    private ArrayList<Task> tasks;

    public  TaskList(int num) {
        this.tasks = new ArrayList<>(num);
    }

    public TaskList(int num, ArrayList<String> storage) {
        this.tasks = new ArrayList<>(num);
        for (String s : storage) {
            try {
                String[] args = s.split("\\|");
                switch (args[0]) {
                    case "T":
                        Task todo = new TodoTask(args[2]);
                        if (args[1].equals("1"))
                            todo.markDone();
                        tasks.add(todo);
                        break;
                    case "E":
                        Task event = new EventTask(args[2], args[3]);
                        if (args[1].equals("1"))
                            event.markDone();
                        tasks.add(event);
                        break;
                    case "D":
                        Task deadline = new DeadlineTask(args[2],
                                DATE_FORMAT.parse(args[3]));
                        if (args[1].equals("1"))
                            deadline.markDone();
                        tasks.add(deadline);
                        break;
                    default:
                        break;
                }
            } catch(ParseException e) {
                this.tasks = new ArrayList<>(num);
            }
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public int numTasks() {
        return tasks.size();
    }

    public void markDone(int index) {
        Task doneTask = tasks.get(index - 1);
        doneTask.markDone();
    }

    @Override
    public String toString() {
        int size = tasks.size();
        if(size == 0) {
            return "Empty list: no tasks added";
        } else {
            int index = 1;
            StringBuilder listOfTasks = new StringBuilder();
            for(Task task : tasks) {
                if(index == 1) {
                    listOfTasks.append(
                            String.format("%d.%s", index, task));
                    index++;
                } else {
                    listOfTasks.append(
                            String.format("\n%d.%s", index, task));
                    index++;
                }
            }
            return listOfTasks.toString();
        }
    }

    public ArrayList<String> getFormattedStrings() {
        ArrayList<String> al = new ArrayList<>();
        for (Task task : tasks) {
            al.add(task.formatString());
        }
        return al;
    }
}
