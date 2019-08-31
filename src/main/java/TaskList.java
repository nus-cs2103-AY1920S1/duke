import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TaskList {

    public ArrayList<Task> taskList;

    public TaskList(){
        taskList = new ArrayList<>();
    }
    protected void Add(Task t){
        taskList.add(t);
    }
    protected void addMessage(){
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
    protected void deleteTask(int y) {
        if (taskList.size() == 0)
            System.out.println("The task list is empty");
        else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(taskList.get(y - 1));
            taskList.remove(y - 1);
            if (taskList.size() == 1)
                System.out.println("Now you have 1 task in your list.");
            else
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }
    protected void Find(String s){
        int count = 0;
        System.out.println("Here are the matching tasks in your list:");
        for(Task t : taskList){
            if(t.description.contains(s)){
                System.out.println(t);
                count+=1;
            }
        }
        if(count == 0)
            System.out.println("There are no matching tasks in your list");
    }

    protected void getList(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= taskList.size(); i+=1){
            System.out.println(i + ". " + taskList.get(i-1) );
        }
    }
    protected void readEvent(String b) throws ParseException {

        if (b.length() == 0) {
            System.out.println("\u2639" + " OOPS!!! the description of a event cannot be empty. ");
        } else {
            int first = b.indexOf('/');
            String desc = b.substring(0, first - 1);
            String on = b.substring(first + 4);
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date at = df.parse(on);
            Task t1 = new Event(desc, at, false);
            this.Add(t1);
            this.addMessage();
        }
    }
    protected void readDeadline(String s){

        if (s.length() == 0) {
            System.out.println("\u2639" + " OOPS!!! the description of a deadline cannot be empty. ");
        } else {
            int first = s.indexOf('/');
            String descr = s.substring(0, first - 1);
            String byTime = s.substring(first + 4);
            Task t1 = new Deadline(descr, byTime, false);
            this.Add(t1);
            this.addMessage();
        }
    }
    protected void readTodo(String s){
        if (s.length() == 0)
            System.out.println("\u2639" + " OOPS!!! the description of a todo cannot be empty. ");
        else {
            Task t1 = new ToDo(s, false);
            this.Add(t1);
            this.addMessage();
        }
    }

}
