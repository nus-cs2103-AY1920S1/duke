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
                continue;
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
                   if(time.equals("")) {
                       System.out.println("☹ OOPS!!! The time cannot be empty.");
                       break;
                   }
               }
               String desc;
               if(command[0].equals("deadline")) {
                   desc = next.substring(9, index - 1) + " ";
                   if(desc.equals(" ")) {
                       System.out.println("☹ OOPS!!! The description for deadline cannot be empty.");
                       continue;
                   }
               } else if(command[0].equals("event")) {
                   desc = next.substring(6, index - 1) + " ";
                   if(desc.equals(" ")) {
                        System.out.println("☹ OOPS!!! The description for event cannot be empty.");
                        continue;
                   }
               } else if(command[0].equals("todo")) {
                   desc = next.substring(4);
                   if (desc.equals("")) {
                       System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                   } else {
                       desc = desc.substring(1);
                   }
               } else {
                   System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                   continue;
               }
               Tasks nextTask = new Tasks(desc, command[0], time); 
               list.add(nextTask);
               System.out.println("Got it. I've added this task:\n " + nextTask);
           }
        }
        
    }
}
