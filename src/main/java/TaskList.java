import java.util.LinkedList;

public class TaskList {
    private LinkedList<Task> list;



    public TaskList() {
        list = new LinkedList<Task>();
    }

    public TaskList(LinkedList<Task> loadedList){
        list = loadedList;
    }


    public void printnewtask(){
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       " + list.getLast().getOverallStatus() + "\n" +
                "     Now you have " + list.size() + " tasks in the list.\n" +
                "    ____________________________________________________________");
    }

    public void listTasks() {
        int i = 0;
        System.out.println("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        while (i < list.size()) {
            System.out.println("     "+ (i+1) + ". " +list.get(i).getOverallStatus());
            i++;
        }
        System.out.println("    ____________________________________________________________");
    }
    public void completeTask(String userinput){
        int taskNumber = Integer.parseInt(userinput.split(" ")[1]);
        list.get(taskNumber-1).checkIfCompleted();
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done: \n" +
                "       "+ list.get(taskNumber-1).getOverallStatus() +"\n" +
                "    ____________________________________________________________");
    }


    public void addTodo(String toDoName , boolean completionStatus){
        list.addLast(new ToDos(toDoName));
        printnewtask();
    }
    public void addDeadline(String deadlineName , ) {
        list.addLast(new Deadlines((deadlineName.split("/")[0]), (deadlineName.split("/by")[1])));
        printnewtask();
    }

    public void addEvent(String eventName){
        list.addLast(new Events((eventName.split("/")[0]), (eventName.split("/at")[1])));
        printnewtask();
    }
    public void removeTask(String deletedEvent){
        int taskTodDelete = Integer.parseInt(deletedEvent.split(" ")[1]);
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task: \n" +
                "       "+ list.get(taskTodDelete-1).getOverallStatus() +"\n" +
                "     Now you have " + (list.size()-1) + " tasks in the list.\n" +
                "    ____________________________________________________________");
        list.remove(taskTodDelete-1);

    }


}