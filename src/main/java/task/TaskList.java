package task;
import formatter.TimeFormatter;
import java.util.ArrayList;


/**
 * Maintains an arraylist of all the stored tasks
 * Maintains an array of the stats of the tasks
 */

public class TaskList {
    ArrayList<Task> myTaskList;
    int totalTasks;
    int totalTasksDone;
    int todoTasks;
    int todoTasksDone;
    int deadLineTasks;
    int deadLineTasksDone;
    int eventTasks;
    int eventTasksDone;


    public TaskList() {
        myTaskList = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return myTaskList;
    }

    /**
     * Searches for tasks which match the input String
     *
     * @param input String input by user which is being searched for
     * @return ArrayList of the tasks that matched the input string
     */

    public ArrayList<Task> searchTasks(String input) {
        ArrayList<Task> answer = new ArrayList<>();

        for (Task temp : myTaskList) {
            if (temp.getName().contains(input)) {
                answer.add(temp);
            }
        }
        return answer;
    }


    /**
     * Searches for tasks which match the input String
     *
     * @param addedTask String input by user which is being searched for
     * @return ArrayList of the tasks that matched the input string
     */

    //Add and print the added notes
    public Task addTasks(String addedTask) {
        Task temp;
        if (addedTask.contains("todo")) {
            String replaced = addedTask.replace("todo ", "");
            temp = new TodoTask(replaced, false);
            myTaskList.add(temp);
            todoTasks++;
            totalTasks++;
            return temp;
        } else if (addedTask.contains("deadline")) {
            String replaced = addedTask.replace("deadline ", "");
            String[] deadLines = replaced.split("by");
            String endTime = deadLines[1];
            temp = new DeadlineTask(deadLines[0].replace("/", ""), false, TimeFormatter.convertToDate(endTime));
            myTaskList.add(temp);
            deadLineTasks++;
            totalTasks++;
            return temp;
        } else if (addedTask.contains("event")) {
            String replaced = addedTask.replace("event ", "");
            String[] events = replaced.split("at");
            String eventTime = events[1];
            temp = new EventTask(events[0].replace("/", ""), false, TimeFormatter.convertToDate(eventTime));
            myTaskList.add(temp);
            eventTasks++;
            totalTasks++;
            return temp;
        }
        assert false : "No possible tasks";
        return null;

}


    /**
     * Marks indexed tasks as done
     *
     * @param index of task (in tasklist) which has been done
     * @return Task which has been done
     */

    public Task taskDone(int index) {
        assert index>0: "You cant do this guy";
        Task curr =  myTaskList.get(index).taskComplete();
        if(curr.getType().equalsIgnoreCase("T")) {
            totalTasksDone++;
            todoTasksDone++;
        } else if (curr.getType().equalsIgnoreCase("D")) {
            totalTasksDone++;
            deadLineTasksDone++;
        } else if (curr.getType().equalsIgnoreCase("E")) {
            totalTasksDone++;
            eventTasksDone++;
        }
        return curr;
    }


    /**
     * Deletes indexed task
     *
     * @param index of task (in tasklist) which has been deleted
     * @return Task which has been deleted
     */

    public Task deleteTask(int index) {
        assert index>0: "You cant delete this guy";

        Task removed = myTaskList.remove(index);
        totalTasks--;
        if(removed.getType().equalsIgnoreCase("T")) {
            todoTasks--;
            if(removed.getDoneStatus()) {
                todoTasksDone--;
            }
        } else if (removed.getType().equalsIgnoreCase("D")) {
            deadLineTasks--;
            if(removed.getDoneStatus()) {
                deadLineTasksDone--;
            }

        } else if (removed.getType().equalsIgnoreCase("E")) {
            deadLineTasks--;
            if(removed.getDoneStatus()) {
                eventTasksDone--;
            }
        }

        return removed;
    }

    /**
     * Gives the stats of all the tasks
     *
     * @return int[] of the numbers of different tasks and their completion status
     */

    public int[] getStats() {
        int[] myScores = new int[8];
        myScores[0] = totalTasks;
        myScores[1] = totalTasksDone;
        myScores[2] = todoTasks;
        myScores[3] = todoTasksDone;
        myScores[4] = deadLineTasks;
        myScores[5] = deadLineTasksDone;
        myScores[6] = eventTasks;
        myScores[7] = eventTasksDone;
        return myScores;
    }

    public int getSize() {
        return myTaskList.size();
    }

    }




