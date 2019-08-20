import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(){
        taskList = new ArrayList<>();
    }
    protected void Add(Task t){
        taskList.add(t);
        System.out.println(" Got it. I've added this task: " );
        System.out.println(taskList.get(taskList.size() - 1));
        if(taskList.size() == 1)
            System.out.println("Now you have 1 task in your list.");
        else
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    protected void MarkAsDone(int x){
        taskList.get(x).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println( taskList.get(x) );
    }
    protected void getList(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= taskList.size(); i+=1){
            System.out.println(i + ". " + taskList.get(i-1) );
        }
    }
}
