import java.util.ArrayList;

public class TaskList{
    public ArrayList<Task> list;
    public TaskList(){
        list = new ArrayList<>();
    }
    public void add(Task t){
        list.add(t);
    }
    public Task remove(int i){
        return list.remove(i);
    }
    public Task get(int i){
        return list.get(i);
    }
    public String toString(){
        String res = "";
        for (int i = 0; i < list.size(); i++) {
           res += (i + 1) + "." + list.get(i) +"\n";
        }
        return res;
    }
}