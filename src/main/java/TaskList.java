import java.util.*;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds new task (without date) into list
     * @param taskName - Name of task
     */
    public void add(String taskName) {
        this.list.add(new ToDoTask(taskName));
        Message.successfulAddMessage(this.list.get(list.size()-1), this.size());
    }

    /**
     * Adds new task (without date) into list
     * @param taskType - Type description of task
     * @param taskName - Name of task
     * @param date - Date of which the task needs to be completed
     * @param taskStatement - statement required by the task. E.g deadline: by
     */
    public void add(String taskType, String taskName, String date, String taskStatement) throws IncorrectStatementException {
        switch (taskType) {
            case "deadline":
                DeadlineTask.verifyTaskStatement(taskStatement);
                this.list.add(new DeadlineTask(taskName, date));
                break;
            case "event":
                EventTask.verifyTaskStatement(taskStatement);
                this.list.add(new EventTask(taskName, date));
                break;
        }
        Message.successfulAddMessage(this.list.get(list.size()-1), this.size());
    }

    /**
     * Marks task at given index as done
     * @param idx - Index of task in list
     * @throws IndexOutOfBoundsException - Throws error if given index is out of list bounds
     */
    public void done(int idx) throws IndexOutOfBoundsException {
        this.list.get(idx).markDone();
    }

    /**
     * Marks task at given index as done
     * @param idx - Index of task in list
     * @throws IndexOutOfBoundsException - Throws error if given index is out of list bounds
     */
    public void delete(int idx) throws IndexOutOfBoundsException {
        Task task = this.list.get(idx);
        this.list.remove(idx);
        Message.deleteTaskMessage(task, this.size());
    }

    /**
     * Returns the current size of list
     * @return size of list
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Prints out contents of list
     */
    public void print() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println((i+1) + "." + this.list.get(i));
        }
    }
}
