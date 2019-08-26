import java.util.ArrayList;

public class TaskList{

    private ArrayList <Task> taskList;
    
    public TaskList (){
        taskList = new ArrayList <Task> ();
    }

    public int size(){
        return taskList.size();
    }

    public void fileAdd(Task newTask){
        taskList.add(newTask);
    }

    public void add(Task newTask){
        taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
        
    }

    public void deleteTask(int index){
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(index));
        taskList.remove(index);
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size() ));
    }

    public Task getTask(int index){
        return taskList.get(index);
    }

    public void doneTask(int index){
        Task currentTask = taskList.get(index);
        currentTask.setStatus(true);
        String message = "Nice! I've marked this task as done:\n" + currentTask;
        System.out.println(message);
    }

    public void printTaskList(){
        int size = taskList.size();

        for(int i = 0; i < size; i ++){
            Integer number = i + 1;
            String message = number + ". " + taskList.get(i);
            System.out.println(message);
        }
    }
} 