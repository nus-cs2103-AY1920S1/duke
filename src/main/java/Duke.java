import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    protected static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        taskList = FileSaver.loadTask();

        Scanner sc = new Scanner(System.in);
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
                        for (int i = 0; i < taskList.size(); i++) {
                            String num = "" + (i + 1);
                            str += "     " + num + ".[" + taskList.get(i).getStatusIcon() + "] "
                                    + taskList.get(i).toString() + "\n";
                        }
                        System.out.print(str + line);

                    } else if (command.equals("done")) {
                        int taskNum = sc.nextInt();
                        taskList.get(taskNum - 1).markAsDone();
                        System.out.println(line + "     Nice! I've marked this task as done:\n"
                                + "     [" + taskList.get(taskNum - 1).getStatusIcon() + "]"
                                + taskList.get(taskNum - 1).toString() + "\n" + line);
                        FileSaver.saveTaskList(taskList); //save file

                    } else if (command.equals("todo")) {
                        String wordsAfter = sc.nextLine();
                        if (wordsAfter.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        Task todoTask = new Todo(wordsAfter);
                        taskList.add(todoTask);
                        System.out.print(line + "     Got it. I've added this task: "
                                + "\n       " + todoTask.toString() + "\n");
                        System.out.print("     Now you have " + taskList.size() + " tasks in the list." + "\n" + line);
                        FileSaver.saveTaskList(taskList); //save file

                    } else if (command.equals("deadline")) {
                        String wordsAfter = sc.nextLine();
                        int pos = wordsAfter.indexOf("/");
                        Task dlTask = new Deadline(wordsAfter.substring(0, pos), wordsAfter.substring(pos + 3));
                        taskList.add(dlTask);
                        System.out.print(line + "     Got it. I've added this task: "
                                + "\n       " + dlTask.toString() + "\n");
                        System.out.print("     Now you have " + taskList.size() + " tasks in the list." + "\n" + line);
                        FileSaver.saveTaskList(taskList); //save file

                    } else if (command.equals("event")) {
                        String wordsAfter = sc.nextLine();
                        int pos = wordsAfter.indexOf("/");
                        Task eTask = new Event(wordsAfter.substring(0, pos), wordsAfter.substring(pos + 3));
                        taskList.add(eTask);
                        System.out.print(line + "     Got it. I've added this task: "
                                + "\n       " + eTask.toString() + "\n" + "     Now you have "
                                + taskList.size() + " tasks in the list." + "\n" + line);
                        FileSaver.saveTaskList(taskList); //save file

                    } else if (command.equals("delete")) {
                        int taskNum = sc.nextInt();
                        System.out.println(line + "     Noted. I've removed this task: ");
                        System.out.println("       " + taskList.get(taskNum - 1). toString());
                        taskList.remove(taskNum - 1);
                        System.out.print("     Now you have " + taskList.size() + "tasks in the list.\n" + line);
                        FileSaver.saveTaskList(taskList); //save file

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