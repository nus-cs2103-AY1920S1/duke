import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Tasks> list = new ArrayList<Tasks>();
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        while(sc.hasNext()) {
           String next = sc.nextLine(); 
           if(next.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
           } else if (next.equals("list")) {
               int numOfOp = list.size();
               for (int i = 1; i <= numOfOp; i++) {
                   System.out.println(i + ". " + list.get(i - 1));
               }
           } else {
               list.add(new Task(next));
               System.out.println("added: " + next);
           }
        }
        
    }
}
