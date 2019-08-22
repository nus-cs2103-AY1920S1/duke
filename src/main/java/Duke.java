import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner reader = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        StringBuilder lineBuilder = new StringBuilder("     ");
        for (int i = 0; i < 59; i++) {
            lineBuilder.append("_");
        }
        String horLine = lineBuilder.toString();
        while (reader.hasNext()) {
            String inputLine = reader.nextLine();
            if (inputLine.equals("bye")) {
                System.out.println(horLine);
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(horLine);
                break;
            } else if (inputLine.equals("list")) {
                System.out.println(horLine);
                if(taskList.isEmpty()) {
                    System.out.println("     Sorry, there are no tasks in the list");
                } else {
                    System.out.println("     Here are the tasks in your list:");
                    for (Task task : taskList) {
                        System.out.println("     " + task.getId() + "." + task);
                    }
                }
                System.out.println(horLine);
                System.out.println();
            } else if(((String)Array.get(inputLine.split(" "), 0)).equals("done")) {
                String number = inputLine.substring(5);
                //need to minus 1
                Task temp = taskList.get(Integer.parseInt(number) - 1);
                temp.setDone();
                System.out.println(horLine);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + temp);
                System.out.println(horLine);
                System.out.println();
            }

            else{  //all other commands
                taskList.add(new Task(taskList.size() + 1, inputLine));
                StringBuilder temp = new StringBuilder("     ");
                temp.append("added: ");
                temp.append(inputLine);
                System.out.println(horLine);
                System.out.println(temp.toString());
                System.out.println(horLine);
                System.out.println();
            }
        }
    }
}