
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
                    int counter = 1;
                    for (Task task : taskList) {
                        System.out.println("     " + counter + "." + task);
                        counter++;
                    }
                }
                System.out.println(horLine + "\n");

            } else if(input.equals("done")) {
                int number = reader.nextInt();
                Task temp = taskList.get(number - 1);
                temp.setDone();
                System.out.println(horLine);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + temp);
                System.out.println(horLine + "\n");
            } else if(input.equals("delete")) {
                int number = reader.nextInt();
                Task temp = taskList.get(number - 1);
                taskList.remove(number - 1);
                System.out.println(horLine);
                System.out.println("     Nice! I've removed this task:");
                System.out.println("       " + temp);
                System.out.println("      Now you have " + taskList.size() + " tasks in the list.");
                System.out.println(horLine + "\n");
            } else{  //all other commands
                try {
                    if (input.equals("todo")) {
                            String tempString = reader.nextLine();
                            if (tempString.equals("")) {
                                throw new DukeException("      ☹ OOPS!!! The description of a todo cannot be empty.");
                            }
                            taskList.add(new Todo(tempString));
                    } else if (input.equals("deadline") || input.equals("event")) {
                        String tempString = reader.nextLine();
                        if(tempString.equals("")) {
                            throw new DukeException("      ☹ OOPS!!! The description of a " +
                                    input + " cannot be empty.");
                        }
                        String[] tempStringArr = tempString.split("/");
                        String description = (String) Array.get(tempStringArr, 0);
                        String secondString = ((String) Array.get(tempStringArr, 1)).substring(3);
                        if (input.equals("deadline")) {
                            taskList.add(new Deadline(description, secondString));
                        } else {
                            taskList.add(new Event(description, secondString));
                        }
                    } else {//all other keywords not part of Duke's task handling schedule
                        throw new DukeException("      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException de) {
                    System.out.println(horLine);
                    System.err.println(de.getMessage());
                    System.out.println(horLine + "\n");
                    continue;  //to prevent printing of below mentioned lines
                } catch (ArrayIndexOutOfBoundsException ae) {
                    System.out.println(horLine);
                    System.err.println("      ☹ OOPS!!! You need to specify the " + input +
                            " time through a /by (deadline) and /at (event)");
                    System.out.println(horLine + "\n");
                    continue;
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