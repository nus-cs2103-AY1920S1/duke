import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String command;
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();
        ArrayList<String> checkList = new ArrayList<>();

        while(!(command = sc.nextLine()).equals("bye")) {
            if(command.equals("list")) {
                for(int i=0; i<taskList.size(); i++) {
                    System.out.println((i+1) + ".[" + checkList.get(i) + "] " + taskList.get(i));
                }
            } else if((command.split(" ")[0]).equals("done")) {
                int numChange = Integer.parseInt(command.split(" ")[1]) - 1;
                checkList.set(numChange, "✓");
                System.out.println("Nice! I've marked this task as done:" + "\n" +
                        "[✓] " + taskList.get(numChange));
            } else {
                taskList.add(command);
                checkList.add("✗");
                System.out.println("added: " + command);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
