import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String hLine = "    ____________________________________________________________";

        System.out.println(hLine);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.printf("%s\n\n", hLine);

        while(true) {

            String command = scanner.nextLine();

            try {
                if (command.equals("bye")) {
                    System.out.println(hLine);
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.printf("%s\n\n", hLine);
                    break;
                } else {
                    System.out.println(hLine);
                    if (command.equals("list")) {
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 0; i < Task.itemsLst.size(); i++) {
                            System.out.printf("     %d.%s\n",
                                    i + 1, Task.itemsLst.get(i));
                        }
                    } else { // if command is not neither list nor bye
                        Scanner tempSc = new Scanner(command);
                        String s = "";
                        if (tempSc.hasNext())
                            s = tempSc.next();
                        if (s.equals("done")) { // if command is done
                            int doneInt = tempSc.nextInt() - 1;
                            Task.itemsLst.get(doneInt).markAsDone();
                            System.out.printf("     Nice! I've marked this task as done:\n       %s\n"
                                    , Task.itemsLst.get(doneInt));
                        } else if (s.equals("delete")) { // if command is delete
                            int delInt = tempSc.nextInt() - 1;
                            Task delTask = Task.itemsLst.remove(delInt);
                            System.out.printf("     Noted. I've removed this task:\n" +
                                    "       %s\n     Now you have %d tasks in the list.\n"
                                    , delTask, Task.itemsLst.size());
                        } else { // add a new task
                            if (command.startsWith("todo")) {
                                Scanner taskSc = new Scanner(command);
                                taskSc.next();
                                if (!taskSc.hasNextLine()) {
                                    throw new DukeException("     \u2639 OOPS!!! " +
                                            "The description of a todo cannot be empty.");
                                }
                                Task.itemsLst.add(new ToDo(taskSc.nextLine().substring(1)));
                            } else if (command.startsWith("deadline")) {
                                Scanner taskSc = new Scanner(command);
                                taskSc.next();
                                String by = taskSc.nextLine().substring(1);
                                String desc = "";
                                for (int i = 0; i < by.length(); i++) {
                                    char c = by.charAt(i);
                                    if (c != '/') {
                                        desc += c;
                                    } else {
                                        by = by.substring(i + 4);
                                        desc = desc.substring(0, desc.length() - 1); // get rid of space
                                        break;
                                    }
                                }
                                Task.itemsLst.add(new Deadline(desc,
                                    LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"))));
                            } else if (command.startsWith("event")) { // assume that task is an event
                                Scanner taskSc = new Scanner(command);
                                taskSc.next();
                                String at = taskSc.nextLine().substring(1);
                                String desc = "";
                                for (int i = 0; i < at.length(); i++) {
                                    char c = at.charAt(i);
                                    if (c != '/') {
                                        desc += c;
                                    } else {
                                        at = at.substring(i + 4);
                                        desc = desc.substring(0, desc.length() - 1); // get rid of space
                                        break;
                                    }
                                }
                                Task.itemsLst.add(new Event(desc,
                                    LocalDateTime.parse(at, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"))));
                            } else {
                                throw new DukeException("     \u2639 OOPS!!! I'm sorry, " +
                                        "but I don't know what that means :-(");
                            }
                            System.out.printf("     Got it. I've added this task:\n       %s\n" +
                                    "     Now you have %d tasks in the list.\n",
                                        Task.itemsLst.get(Task.itemsLst.size() - 1), Task.itemsLst.size());
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.printf("%s\n\n", hLine);

        }

    }
}
