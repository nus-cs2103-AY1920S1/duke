import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

public class TaskList {

    private ArrayList<Task> taskArr = new ArrayList<>();
    private HashSet<String> taskSet = new HashSet<>();

    public void TaskList() {
    }
    public ArrayList<Task> getList() {
        return taskArr;
    }

    public ToDo addTodo(String taskInfo, int isDone) throws ParseException, DukeException {
        /**
         *  creates new To Do, add to tasklist
         *  prints confirmation message
         *  and prints formatted to do string
         *  then total num of current tasks
         *  @params String that describes task
         *  @return none
         *  @throws DukeException if taskInfo is empty
         */
        if (taskInfo.equals("")) {
            throw new DukeException("Comrade, you must still be hungover, " +
                    "a task description cannot be empty!");
        } else if (taskSet.contains(taskInfo)) {
            throw new DukeException("Comrade, you already have this task!");
        }
        ToDo newToDo = new ToDo(taskInfo,"T","");
        taskSet.add(taskInfo);
        if (isDone == 1) {
            newToDo.markDone();
        }
        taskArr.add(newToDo);
        return newToDo;
    }
    public Deadline addDeadline(String taskInfo, String by,int isDone) throws ParseException, DukeException {
        /**
         *  creates new Deadline, add to tasklist
         *  prints confirmation message
         *  and prints formatted deadline string
         *  then total num of current tasks
         *  @param String that describes task
         *  @return none
         *  @throws DukeException if taskInfo is empty
         */
        if (taskInfo.equals("")) {
            throw new DukeException("Comrade, you must still be hungover, " +
                    "a task description cannot be empty!");
        } else if (taskSet.contains(taskInfo)) {
            throw new DukeException("Comrade, you already have this task!");
        }
        Deadline newDeadline = new Deadline(taskInfo,"D",by);
        if (isDone == 1) {
            newDeadline.markDone();
        }
        taskSet.add(taskInfo);
        taskArr.add(newDeadline);
        return newDeadline;
    }
    public Event addEvent(String taskInfo, String by, int isDone) throws ParseException, DukeException {
        /**
         *  creates new event, add to tasklist
         *  prints confirmation message
         *  and prints formatted event string
         *  then total num of current tasks
         *  @params String that describes task
         *  @return none
         *  @throws DukeException if taskInfo is empty
         */
        if (taskInfo.equals("")) {
            throw new DukeException("Comrade, you must still be hungover, " +
                    "a task description cannot be empty!");
        } else if (taskSet.contains(taskInfo)) {
            throw new DukeException("Comrade, you already have this task!");
        }
        Event newEvent = new Event(taskInfo,"E",by);
        taskSet.add(taskInfo);
        if (isDone == 1) {
            newEvent.markDone();
        }
        taskArr.add(newEvent);
        return newEvent;
    }
    public Task delete(int taskNum) throws DukeException{
        /**
         *  helper function, deletes task
         *  based on number provided
         *  prints confirmation and formatted task
         *  as per specified within task printTask method
         *  then prints number of remaining tasks
         * @param integer indicating task number to delete
         *  @return none
         * @throws DukeException if integer given > than current size of array
         */
        int currentNumOfTasks = taskArr.size();
        if (taskNum > currentNumOfTasks) {
            throw new DukeException("Comrade, don't flatter yourself." +
                    " You don't have that many chores, you only have "
                    + currentNumOfTasks + " chores now.");
        }
        Task doneTask = taskArr.get(taskNum-1);
        String taskToRemove = doneTask.getTaskInfo();
        taskSet.remove(taskToRemove);
        taskArr.remove(taskNum-1);

        return doneTask;
    }
    public Task done(int taskNum) throws DukeException {
        /**
         *  helper function, marks tasks as done
         *  then prints confirmation,
         *  then prints the done task in formatted string
         *  (formatted string from task itself)
         *  @return Task that is done, handed to UI for printing to GUI
         *  @throws DukeException if integer given > than current size of array
         */
        int currentNumOfTasks = taskArr.size();
        if (taskNum > currentNumOfTasks) {
            throw new DukeException("Comrade, don't flatter yourself." +
                    " You don't have that many chores, you only have "
                    + currentNumOfTasks + " chores now.");
        }
        Task doneTask = taskArr.get(taskNum-1);
        if (doneTask.isDone) {
            throw new DukeException("Comrade, that task is already done!");
        }
        doneTask.markDone();
        return doneTask;
    }
    public ArrayList<Task> findTasks(String keyWord) {
        /**
         * iterates through taskarr to find
         * tasks that have keyword inside the task description
         * uses contains method to find matching tasks
         * @params String which is the keyword to search for
         * @return arraylist of tasks whose descriptions match
         */
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: taskArr) {
            String taskInfo = task.getTaskInfo();
            if (taskInfo.contains(keyWord) || taskInfo.equalsIgnoreCase(keyWord)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
