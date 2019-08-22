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
               StringBuilder sb = new StringBuilder();
               StringBuilder sb2 = new StringBuilder();
               int slash = -1;
               for(int i = 1; i < command.size; i++) {
                   if(!command[i].equals("/")) {
                       slash = i;
                       break;
                   } else {
                       sb.append(command[i]);
                       sb.append(" ");
                   }
               }
               if(slash != -1) {
                    sb2.append("( ");
                    sb2.append(command[slash + 1] + ":");
                    for(int j = slash + 2; j <= command.size; j++) {
                        sb2.append(" ");
                        sb2.append(command[j]);
                    }
                    sb2.append(")");
               }
               Task nextTask = new Tasks(sb.toString(), command[0], sb2.toString()); 
               list.add(nextTask);
               System.out.println("Got it. I've added this task:\n " + nextTask);
           }
        }
        
    }
}
