
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> list;
    public Duke(){}
    private void Greet(){
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    private void Echo(String a){
        System.out.println(a);
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
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i <= d.list.size(); i+=1){
                    System.out.println(i + ".[" + d.list.get(i-1).getStatusIcon() + "] " + d.list.get(i-1).description);
                }

            }
            else if(a.equals("done")){
                int num = scan.nextInt();
                d.list.get(num-1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + d.list.get(num-1).getStatusIcon() + "] " + d.list.get(num-1).description);
                String c = scan.nextLine();
            }
            else{
               String b = a + scan.nextLine();
                d.list.add(new Task(b));
               System.out.println("added: " + b);
            }

        }

    }
}
