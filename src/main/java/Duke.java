import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String command;
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();

        while(!(command = sc.nextLine()).equals("bye")) {
            if(!command.equals("list")) {
                taskList.add(command);
                System.out.println("added: " + command);
            } else {
                int num = 1;
                for(int i=0; i<taskList.size(); i++) {
                    System.out.println(num + ". " + taskList.get(i));
                    num++;
                }
            }

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
