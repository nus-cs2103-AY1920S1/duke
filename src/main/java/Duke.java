
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
        System.out.println("Hello! I'm Duke\nWhat can I do for you?" );

        Scanner reader = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        StringBuilder lineBuilder = new StringBuilder("     ");
        for(int i = 0; i < 59; i++) {
            lineBuilder.append("_");
        }
        String horLine = lineBuilder.toString();
        while(reader.hasNextLine()) {
            String input = reader.next();
            if(input.equals("bye")) {
                System.out.println(horLine);
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(horLine);
                break;
            } else if(input.equals("list")){
                System.out.println(horLine);
                if(taskList.isEmpty()) {
                    System.out.println("Sorry, there are no tasks in the list");
                } else {
                    System.out.println("     Here are the tasks in your list:");
                    for (Task task : taskList) {
                        System.out.println("     " + task.getId() + "." + task);
                    }
                }
                System.out.println(horLine);
                System.out.println();
            } else if(input.equals("done")) {
                int number = reader.nextInt();
                Task temp = taskList.get(number);
                temp.setDone();
                System.out.println(horLine);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + temp);
                System.out.println(horLine);
                System.out.println();
            }

            else{  //all other commands
                if(input.equals("todo")) {
                    String temp = reader.nextLine();
                    System.out.println(temp);
                    temp.toString();
                    taskList.add(new Todo(taskList.size() + 1, temp));

                } else{
                    String tempString = reader.nextLine();
                    String[] tempStringArr = tempString.split("/");
                    String description = (String) Array.get(tempStringArr, 0);
                    String secondString = ((String)Array.get(tempStringArr, 1)).substring(3);
                    if(input.equals("deadline")) {
                        taskList.add(new Deadline(taskList.size() + 1, description, secondString));
                    } else {
                        taskList.add(new Event(taskList.size() + 1, description, secondString));
                    }
                }
                System.out.println(horLine);
                System.out.println("      Got it. I've added this task:");
                System.out.println("       " + taskList.get(taskList.size() - 1));
                System.out.println("      Now you have " + taskList.size() + " tasks in the list.");
                System.out.println(horLine);
                System.out.println();
            }
        }
    }

}