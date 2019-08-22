import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> commandList = new ArrayList<>();
        String line = "    ____________________________________________________________\n";
        System.out.println(line +
                "     Hello! I'm Duke\n" + //duke greeting
                "     What can I do for you?\n" + line);
        int x = 1; //switch
        do {        //to recycle the try catch block
            try {
                while (sc.hasNext()) {
                    String command = sc.next();

                    if (command.equals("bye")) {
                        System.out.println(line + "     Bye. Hope to see you again soon!\n" + line);
                        x = 2; //break the do while loop
                        break;
                    } else if (command.equals("list")) {
                        String str = line + "     Here are the tasks in your list:\n";
                        for (int i = 0; i < commandList.size(); i++) {
                            String num = "" + (i + 1);
                            str += "     " + num + ".[" + commandList.get(i).getStatusIcon() + "] "
                                    + commandList.get(i).toString() + "\n";
                        }
                        System.out.print(str + line);
                    } else if (command.equals("done")) {
                        int taskNum = sc.nextInt();
                        commandList.get(taskNum - 1).markAsDone();
                        System.out.println(line + "     Nice! I've marked this task as done:\n"
                                + "     [" + commandList.get(taskNum - 1).getStatusIcon() + "]"
                                + commandList.get(taskNum - 1).toString() + "\n" + line);
                    } else if (command.equals("todo")) {
                        String wordsAfter = sc.nextLine();
                        if (wordsAfter.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        Task todoTask = new Todo(wordsAfter);
                        commandList.add(todoTask);
                        System.out.print(line + "     Got it. I've added this task: "
                                + "\n       " + todoTask.toString() + "\n");
                        System.out.print("     Now you have " + commandList.size() + " tasks in the list." + "\n" + line);
                    } else if (command.equals("deadline")) {
                        String wordsAfter = sc.nextLine();
                        int pos = wordsAfter.indexOf("/");
                        Task dlTask = new Deadline(wordsAfter.substring(0, pos), wordsAfter.substring(pos + 3));
                        commandList.add(dlTask);
                        System.out.print(line + "     Got it. I've added this task: "
                                + "\n       " + dlTask.toString() + "\n");
                        System.out.print("     Now you have " + commandList.size() + " tasks in the list." + "\n" + line);
                    } else if (command.equals("event")) {
                        String wordsAfter = sc.nextLine();
                        int pos = wordsAfter.indexOf("/");
                        Task eTask = new Event(wordsAfter.substring(0, pos), wordsAfter.substring(pos + 3));
                        commandList.add(eTask);
                        System.out.print(line + "     Got it. I've added this task: "
                                + "\n       " + eTask.toString() + "\n" + "     Now you have "
                                + commandList.size() + " tasks in the list." + "\n" + line);
                    } else {
                        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException error) {
                System.out.print(line + "     " + error.getMessage() + "\n" + line);
            }

        } while (x==1);
    }
}