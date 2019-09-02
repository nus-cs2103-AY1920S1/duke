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

    public String add(Task newTask){
        taskList.add(newTask);

        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:" +"\n" );
        sb.append(newTask.toString() +"\n");
        sb.append(String.format("Now you have %d tasks in the list.", taskList.size()));
        
        return sb.toString();
    }

    public String deleteTask(int index){
        taskList.remove(index);

        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:" +"\n");
        sb.append(taskList.get(index) +"\n");
        sb.append(String.format("Now you have %d tasks in the list.", taskList.size() ));
        
        return sb.toString();
    }

    public Task getTask(int index){
        return taskList.get(index);
    }

    public String doneTask(int index){
        Task currentTask = taskList.get(index);
        currentTask.setStatus(true);
        String message = "Nice! I've marked this task as done:\n" + currentTask;
        return message;
    }

    public ArrayList <Task> getTaskList(){
        return taskList;
    }

    public void printTaskList(){
        int size = taskList.size();

        for(int i = 0; i < size; i ++){
            Integer number = i + 1;
            String message = number + ". " + taskList.get(i);
            System.out.println(message);
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        int size = taskList.size();
        for(int i = 0; i < size; i ++){
            Integer number = i + 1;
            String message = number + ". " + taskList.get(i);
            if(i < size - 1){
                sb.append(message + "\n");
            }else{
                sb.append(message);
            }
        }

        return sb.toString();
    }
} 