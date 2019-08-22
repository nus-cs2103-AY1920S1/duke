import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Tasks> list = new ArrayList<Tasks>();
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        while(sc.hasNext()) {
           String next = sc.nextLine(); 
           String[] command = next.split(" ");
           if(command[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
           } else if (command[0].equals("list")) {
               int numOfOp = list.size();
               for (int i = 1; i <= numOfOp; i++) {
                   System.out.println(i + "." + list.get(i - 1));
               }
           } else if (command[0].equals("done")) {
               Tasks done = list.get(Integer.parseInt(command[1]) - 1);
               done.finishTask();
               System.out.println("Nice! I've marked this task as done:\n " + done);
           } else {
               list.add(new Tasks(next, command[0]));
               System.out.println("added: " + next);
           }
        }
        
    }
}
