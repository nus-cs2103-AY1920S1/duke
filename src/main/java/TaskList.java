import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.stream.Stream;

public class TaskList {
    private ArrayList<Task> tasksArr = new ArrayList<Task>();
    BufferedReader bufferedReader;
    TaskList(BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
        populateTaskList();
    }

    public TaskList() {

    }

    public TaskList(ArrayList<Task> taskArr){
        this.tasksArr =  taskArr;
    }

    /**
     * Use the Buffered File provided in the constructor to read the file and populate the list with the stored
     * tasks
     */
    private void populateTaskList() {
        Stream<String> linesStream = bufferedReader.lines();
        linesStream.forEach(line -> {
            String[] lineSplit = line.split("//");
            String taskType = lineSplit[0];
            Task currTask = new Task("temp");
            if (taskType.equals("T")){ // Todo
                boolean isDone = lineSplit[1].equals("1");
                String taskName = lineSplit[2];
                currTask = new Todo(taskName);
                if (isDone){
                    currTask.done();
                }
            } else if (taskType.equals("D")) { // Deadline
                boolean isDone = lineSplit[1].equals("1");
                String taskName = lineSplit[2];
                String endDate = lineSplit[3];
                currTask = new Deadline(taskName, endDate);
                if (isDone){
                    currTask.done();
                }
            } else if (taskType.equals("E")) {
                boolean isDone = lineSplit[1].equals("1");
                String taskName = lineSplit[2];
                String eventDate = lineSplit[3];
                currTask = new Event(taskName, eventDate);
                if (isDone){
                    currTask.done();
                }
            } else {
                System.out.println("Invalid syntax in duke.txt for " + taskType);
            }
            tasksArr.add(currTask);
        });
    }

    /**
     * Get the string representation of the full list to write to the file and store the data
     * @return string
     */
    public String taskListString () {
        String dukeTxt = "";
        while(!tasksArr.isEmpty()){
            Task currTask = tasksArr.remove(0);
            String currTaskString = "";
            if (currTask instanceof Todo) {
                String taskTypeChar = "T";
                String isDone = "0"; // 0 is not done
                String taskName = currTask.getTaskName();
                if (currTask.isDone()) {
                    isDone = "1";
                }
                currTaskString = taskTypeChar + "//" + isDone + "//" + taskName;
            } else if (currTask instanceof Deadline) {
                String taskTypeChar = "D";
                String isDone = "0"; // 0 is not done
                String taskName = currTask.getTaskName();
                String endDate = ((Deadline) currTask).getEndDate();
                if (currTask.isDone()) {
                    isDone = "1";
                }
                currTaskString = taskTypeChar + "//" + isDone + "//" + taskName + "//" + endDate;
            } else if (currTask instanceof Event) {
                String taskTypeChar = "E";
                String isDone = "0"; // 0 is not done
                String taskName = currTask.getTaskName();
                String eventDate = ((Event) currTask).getEventDate();
                if (currTask.isDone()) {
                    isDone = "1";
                }
                currTaskString = taskTypeChar + "//" + isDone + "//" + taskName + "//" + eventDate;
            }
            dukeTxt = dukeTxt + currTaskString + "\n";
        }
        return dukeTxt;
    }


    /**
     * Make a particular task in the list done
     * @param index the index in the list
     * @return  the Task that was made done
     */
    public Task doneTask(int index) {
        Task currTask = tasksArr.get(index);
        currTask.done();
        return currTask;
    }

    /**
     * Add task to the list
     * @param task
     */
    public void addTask(Task task){
        tasksArr.add(task);
    }

    /**
     * delete a certain task in the list
     * @param i index
     * @return the task deleted
     */
    public Task deleteTask(int i){
        return tasksArr.remove(i);
    }

    /**
     * the general information of the list
     * @return String
     */
    public String listDetails () {
        return "Now you have " + tasksArr.size() + " tasks in the list.";
    }

    public TaskList find(String keyWord){
        ArrayList<Task> list = new ArrayList<Task>();
        for (int i = 0 ; i < tasksArr.size() ; i++){
            Task currTask = tasksArr.get(i);
            String currTaskName = currTask.getTaskDetails();
            if (currTaskName.contains(keyWord)) {
                list.add(currTask);
            }
        }
        return new TaskList(list);
    }

    /**
     * The string shown to the clients for the information in the list
     * @return the String representation of the list
     */
    @Override
    public String toString() {
        String string = "Here are the tasks in your list:\n";
        for (int i = 0 ; i < tasksArr.size() ; i++ ) {
            string = string + "    " + (i + 1) + ". " + tasksArr.get(i).getTaskDetails() + "\n";
        }
        return string;
    }

    public String toFoundListString(){
        String string = "Here are the matching in your list:\n";
        for (int i = 0 ; i < tasksArr.size() ; i++ ) {
            string = string + "    " + (i + 1) + ". " + tasksArr.get(i).getTaskDetails() + "\n";
        }
        return string;
    }
}
