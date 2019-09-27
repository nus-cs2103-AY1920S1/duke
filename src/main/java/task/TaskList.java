package task;
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
     * Adds task to list
     *
     * @param input Task to list
     * @return Task which was added
     */

    //Add and print the added notes
    public Task addTasks(Task input) {

        switch(input.getType()) {
            case "T": {
                todoTasks++;
            }

            case "D": {
                deadLineTasks++;
            }

            case "E": {
                eventTasks++;
            }
        }
            myTaskList.add(input);
            totalTasks++;
            return input;
        }


    /**
     * Marks indexed tasks as done
     *
     * @param index of task (in tasklist) which has been done
     * @return Task which has been done
     */

    public Task taskDone(int index) {
        assert index<=myTaskList.size()+1: "Cant do something that is bigger than the size of the taskList";
        assert index>0: "Cant do something that is smaller than the size of the taskList";


        Task curr = myTaskList.get(index).taskComplete();

        switch (curr.getType()) {
            case "T": {
                todoTasksDone++;
            }

            case "D": {
                deadLineTasksDone++;
            }

            case "E": {
                eventTasksDone++;
            }
        }
        totalTasksDone++;
        return curr;
    }


    /**
     * Deletes indexed task
     *
     * @param index of task (in tasklist) which has been deleted
     * @return Task which has been deleted
     */

    public Task deleteTask(int index) {
        assert index<=myTaskList.size()+1: "Cant delete something that is bigger than the size of the taskList";
        assert index>0: "Cant delete something that is smaller than the size of the taskList";

        Task removed = myTaskList.remove(index);
        totalTasks--;

        switch (removed.getType()) {
            case "T": {
                todoTasks--;
                if (removed.getDoneStatus()) {
                    todoTasksDone--;
                }
            }

            case "D": {
                deadLineTasks--;
                if (removed.getDoneStatus()) {
                    deadLineTasksDone--;
                }

            }

            case "E": {
                eventTasks--;
                if (removed.getDoneStatus()) {
                    eventTasksDone--;
                }
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




