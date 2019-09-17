import java.util.*;

public class TaskList extends ArrayList <Task> {

    protected ArrayList<Task > taskCollection= new ArrayList<>();

    public TaskList() {
    }

    public ArrayList<Task> getTasks() {
        return taskCollection;
    }

    //add
    public  void addTaskIn(Task task,boolean isDisplay) {

        if(task == null ){
            throw new StringIndexOutOfBoundsException();
        }
        if (isDisplay){
            System.out.println("    ____________________________________________________________");
            System.out.println("     Got it. I've added this task:");
            System.out.print("       ["+task.getType()+"]"+"["+ task.getStatus()+"] "+task.getTaskName());
            switch (task.getType()) {
                case 'T':
                    System.out.println();
                    break;
                case 'D':
                    Deadline dtask = (Deadline) task;
                    System.out.println(" by "+ dtask.getBy());
                    break;
                case 'E':
                    Event etask = (Event)task;
                    System.out.println(" start "+ etask.getStart());
                    break;
            }
            System.out.println("     Now you have "+(taskCollection.size()+1)+" tasks in the list");
            System.out.println("    ____________________________________________________________");
        }
        taskCollection.add(task);

    }

    //find
    public void find(String string) {
        ArrayList <Integer> searchResult = new ArrayList<>();
        for (int i = 0; i< taskCollection.size(); i++) {
            if (taskCollection.get(i).getTaskName().contains(string)) {
                //System.out.print(i+ " ---  ");
                System.out.print("     "+(i+1)+".");
                //System.out.print(taskCollection.get(i).getTaskName()+" ");
                System.out.print("["+taskCollection.get(i).getType()+"]"+"["+ taskCollection.get(i).getStatus()+"] "+taskCollection.get(i).getTaskName()+" ");
                switch (taskCollection.get(i).getType()){
                    case 'T':
                        System.out.println();
                        break;
                    case 'D':
                        //Forced type casting
                        Deadline dtask = (Deadline) taskCollection.get(i);
                        System.out.println("(by: "+dtask.getBy()+")");
                        break;
                    case 'E':
                        Event etask = (Event) taskCollection.get(i);
                        System.out.println("(by: "+etask.getStart()+")");
                        break;
                }
            }
        }
    }

    //delete
    public void deleteTask(int index) {
        if (index > taskCollection.size()-1) {
            throw new IndexOutOfBoundsException();
        }
        Task task = taskCollection.remove(index);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       ["+task.getType()+"]"+"["+ task.getStatus()+"] "+task.getTaskName());
        System.out.println("     Now you have "+taskCollection.size()+" tasks in the list");
        System.out.println("    ____________________________________________________________");
    }

    //list
    public void list (){
        if(taskCollection.size() == 0){
            //System.out.println("but size is zero");
            throw new RuntimeException();
        }
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < taskCollection.size(); i++) {
            System.out.print("     "+(i+1)+"."+"["+taskCollection.get(i).getType()+"]"+"["+ taskCollection.get(i).getStatus()+"] "+taskCollection.get(i).getTaskName()+" ");
            switch (taskCollection.get(i).getType()){
                case 'T':
                    System.out.println();
                    break;
                case 'D':
                    //Forced type casting
                    Deadline dtask = (Deadline) taskCollection.get(i);
                    System.out.println("(by: "+dtask.getBy()+")");
                    break;
                case 'E':
                    Event etask = (Event) taskCollection.get(i);
                    System.out.println("(by: "+etask.getStart()+")");
                    break;
            }

        }
        System.out.println("    ____________________________________________________________");
    }
}
