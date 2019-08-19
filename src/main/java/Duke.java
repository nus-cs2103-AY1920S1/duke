
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
        System.out.println("added: " + t.getDescription());
    }
    private void Echo(String a){
        System.out.println(a);
    }
    private void MarkAsDone(int x, ArrayList<Task> li){
        li.get(x).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + li.get(x).getStatusIcon() + "] " + li.get(x).description);
    }
    private void getList(ArrayList<Task> lis){
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= lis.size(); i+=1){
            System.out.println(i + ".[" + lis.get(i-1).getStatusIcon() + "] " + lis.get(i-1).description);
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
            else{
               String b = a + scan.nextLine();
                Task t = new Task(b);
                d.Add(t,d.list);

            }

        }

    }
}
