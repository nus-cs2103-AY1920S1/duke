import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    public void add(Task task){
        tasks.add(task);
    }
    public int size(){
        return tasks.size();
    }
    public Task get(int i){
        return tasks.get(i);
    }
    public void remove(int i){
        tasks.remove(i);
    }
    @Override
    public String toString(){
        String output = ""; 
        for(int i = 0; i < tasks.size(); i++){
            output = output + "     " +  (i+1) + ": " + tasks.get(i).toString();
            if(i < tasks.size() - 1){
                output = output + "\n";
            }
        }
        return output;
    }
}