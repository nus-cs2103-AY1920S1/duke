package tasklist;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class TaskList {
    private LinkedList<Task> taskList;

    public TaskList(){
        taskList = new LinkedList<Task>();
    }


    public TaskList(LinkedList<Task> loadedList){
        taskList = loadedList;
    }


    public void printnewtask(){
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       " + taskList.getLast().getOverallStatus() + "\n" +
                "     Now you have " + taskList.size() + " tasks in the list.\n" +
                "    ____________________________________________________________");
    }

    public void listTasks() {
        int i = 0;
        System.out.println("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        while (i < taskList.size()) {
            System.out.println("     "+ (i+1) + ". " + taskList.get(i).getOverallStatus());
            i++;
        }
        System.out.println("    ____________________________________________________________");
    }
    public void completeTask(String completedtask){
        int taskNumber = Integer.parseInt(completedtask);
        taskList.get(taskNumber-1).CompleteTask();
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done: \n" +
                "       "+ taskList.get(taskNumber-1).getOverallStatus() +"\n" +
                "    ____________________________________________________________");
    }

    public void addTask(String taskType , boolean completionStatus , LocalDateTime date ) {
        switch (taskType) {
        case "todo":
            taskList.addLast(new Todo(taskType,completionStatus));
        case "deadline":
            taskList.addLast(new Deadline(taskType, completionStatus, date));
            break;
        case "event":
            taskList.addLast(new Event(taskType, completionStatus, date));
            break;
        }
        printnewtask();
    }


    public void removeTask(String deletedEvent){
        int taskTodDelete = Integer.parseInt(deletedEvent);
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task: \n" +
                "       "+ taskList.get(taskTodDelete-1).getOverallStatus() +"\n" +
                "     Now you have " + (taskList.size()-1) + " tasks in the list.\n" +
                "    ____________________________________________________________");
        taskList.remove(taskTodDelete-1);
    }

    public LinkedList<Task> getTaskList(){
        return taskList;
    }

}
