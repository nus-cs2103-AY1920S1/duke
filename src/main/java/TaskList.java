import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list){
        this.list = list;
    }
    public TaskList(){
        this.list = new ArrayList<>();
    }
    public void printForList(){
        for(int i=1; i<=list.size(); i++){
            System.out.println(i + ". " + list.get(i-1).printer());
        }
    }

    public String printForOutput(){
        StringBuilder result= new StringBuilder();
        for(int i=1; i<=list.size(); i++){
            result.append(list.get(i - 1).printToOutput()).append("\n");
        }
        return result.toString();
    }

    public void taskDone(int x){
        list.get(x).taskDone();
    }

    public String taskPrint(int x){
        return list.get(x).printer();
    }

    public String printLatest(){
        return list.get(list.size()-1).printer();
    }

    public Integer size(){
        return list.size();
    }

    public void remove(int x){
        list.remove(x);
    }

    public void add(Task newTask){
        list.add(newTask);
    }
}
