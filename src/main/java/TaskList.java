import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArr = taskArr = new ArrayList<>();

    public void TaskList() {
    }
    public ArrayList<Task> getList() {
        return taskArr;
    }
    public ToDo addTodo(String taskInfo) {
        // TODO add DukeException for this
        /**
         *  creates new To Do, add to tasklist
         *  prints confirmation message
         *  and prints formatted to do string
         *  then total num of current tasks
         *  @params String that describes task
         *  @return none
         *  @throws DukeException if taskInfo is empty
         */
        ToDo newToDo = new ToDo(taskInfo,"T","");
        taskArr.add(newToDo);
        return newToDo;
    }
    public Deadline addDeadline(String taskInfo, String by) {
        // TODO add DukeException for this
        /**
         *  creates new Deadline, add to tasklist
         *  prints confirmation message
         *  and prints formatted deadline string
         *  then total num of current tasks
         *  @param String that describes task
         *  @return none
         *  @throws DukeException if taskInfo is empty
         */
        Deadline newDeadline = new Deadline(taskInfo,"D",by);
        taskArr.add(newDeadline);
        return newDeadline;
    }
    public Event addEvent(String taskInfo, String by) {
        // TODO add DukeException for this
        /**
         *  creates new event, add to tasklist
         *  prints confirmation message
         *  and prints formatted event string
         *  then total num of current tasks
         *  @params String that describes task
         *  @return none
         *  @throws DukeException if taskInfo is empty
         */
        Event newEvent = new Event(taskInfo,"E",by);
        taskArr.add(newEvent);
        return newEvent;
    }
    public Task delete(int taskNum) {
        /**
         *  helper function, deletes task
         *  based on number provided
         *  prints confirmation and formatted task
         *  as per specified within task printTask method
         *  then prints number of remaining tasks
         *
         *  @return none
         */
        Task doneTask = taskArr.get(taskNum-1);
        taskArr.remove(taskNum-1);
        return doneTask;
    }
    public Task done(int t) {
        /**
         *  helper function, marks tasks as done
         *  then prints confirmation,
         *  then prints the done task in formatted string
         *  (formatted string from task itself)
         *  @return none
         */
        Task doneTask = taskArr.get(t-1);
        doneTask.markDone();
        return doneTask;
    }
}
