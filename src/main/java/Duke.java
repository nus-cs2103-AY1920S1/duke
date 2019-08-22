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
               int index = next.lastIndexOf("/");
               String time = "";
               if(index != -1) {
                   time = next.substring(index + 1);
               }
               String desc;
               if(command[0].equals("deadline")) {
                   desc = next.substring(9, index - 1) + " ";
               } else if(command[0].equals("event")) {
                   desc = next.substring(6, index - 1) + " ";
               } else {
                   desc = next.substring(5);
               }
               Tasks nextTask = new Tasks(desc, command[0], time); 
               list.add(nextTask);
               System.out.println("Got it. I've added this task:\n " + nextTask);
           }
        }
        
    }
}
