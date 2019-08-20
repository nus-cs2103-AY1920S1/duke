
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> list;
    public Duke(){}
    private void Greet(){
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }
    private void Add(Task t, ArrayList<Task> l){
        l.add(t);
        System.out.println(" Got it. I've added this task: " );
        System.out.println(l.get(l.size() - 1));
        if(l.size() == 1)
            System.out.println("Now you have 1 task in your list.");
        else
        System.out.println("Now you have " + l.size() + " tasks in the list.");
    }
    private void Echo(String a){
        System.out.println(a);
    }
    private void MarkAsDone(int x, ArrayList<Task> li){
        li.get(x).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println( li.get(x) );
    }
    private void getList(ArrayList<Task> lis){
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= lis.size(); i+=1){
            System.out.println(i + ". " + lis.get(i-1) );
        }
    }
    private void Exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Duke d = new Duke();
        Scanner scan = new Scanner(System.in);
        d.Greet();
        d.list = new ArrayList<>();
        while(scan.hasNextLine()){
            String a = scan.next();
            if(a.equals("bye")){
                d.Exit();
                break;
            }
            else if(a.equals("list")){
                d.getList(d.list);
            }
            else if(a.equals("done")){
                int num = scan.nextInt();
                d.MarkAsDone(num-1,d.list);
                String c = scan.nextLine();
            }
            else if(a.equals("event")){
               String b = scan.nextLine();
               int first = b.indexOf('/');
               String desc = b.substring(0,first-1);
               String byTime = b.substring(first+4);
               Task t = new Event(desc,byTime);
                d.Add(t,d.list);
            }
            else if(a.equals("deadline")){
                String det = scan.nextLine();
                int first = det.indexOf('/');
                String descr = det.substring(0,first-1);
                String byTime = det.substring(first+4);
                Task t = new Deadline(descr,byTime);
                d.Add(t,d.list);
            }
            else{
                String todoDetails = scan.nextLine();
                Task t = new ToDo(todoDetails);
                d.Add(t,d.list);
            }

        }

    }
}
