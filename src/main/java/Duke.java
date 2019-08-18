import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String command;
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        int taskID = 1;
        while(!(command = sc.nextLine()).equals("bye")) {
            if(command.equals("list")) {
                for(int i=0; i<taskList.size(); i++) {
                    System.out.println(taskList.get(i).printTask());
                }
            } else if((command.split(" ")[0]).equals("done")) {
                int numChange = Integer.parseInt(command.split(" ")[1]) - 1;
                taskList.get(numChange).markAsDone();
                System.out.println("Nice! I've marked this task as done:" + "\n" +
                        "[✓] " + taskList.get(numChange).taskDesc);
            } else {
                Task newTask = new Task(command, taskID);
                taskList.add(newTask);
                System.out.println("added: " + command);
                taskID++;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }


}


