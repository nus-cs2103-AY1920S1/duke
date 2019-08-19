import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task[] itemsLst = new Task[100];
        int currentIndex = 0;

        String hLine = "    ____________________________________________________________";

        System.out.println(hLine);
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.printf("%s\n\n", hLine);

        while(true) {

            String command = scanner.nextLine();

            try {
                if (command.equals("bye")) {
                    System.out.println(hLine);
                    System.out.println("    Bye. Hope to see you again soon!");
                    System.out.printf("%s\n\n", hLine);
                    break;
                } else {
                    System.out.println(hLine);
                    if (command.equals("list")) {
                        System.out.println("    Here are the tasks in your list:");
                        for (int i = 0; i < currentIndex; i++) {
                            System.out.printf("    %d.%s\n",
                                    i + 1, itemsLst[i]);
                        }
                    } else { // if command is neither list nor bye
                        Scanner tempSc = new Scanner(command);
                        // if command is done
                        if (tempSc.hasNext() && tempSc.next().equals("done")) {
                            int tempInt = tempSc.nextInt() - 1;
                            itemsLst[tempInt].markAsDone();
                            System.out.printf("    Nice! I've marked this task as done:\n      %s\n"
                                    , itemsLst[tempInt]);
                        } else { // add a new task
                            if (command.startsWith("todo")) {
                                Scanner taskSc = new Scanner(command);
                                taskSc.next();
                                if (!taskSc.hasNextLine()) {
                                    throw new DukeException("     \u2639 OOPS!!! " +
                                            "The description of a todo cannot be empty.");
                                }
                                itemsLst[currentIndex] = new ToDo(taskSc.nextLine().substring(1));
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
                                itemsLst[currentIndex] = new Deadline(desc, by);
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
                                itemsLst[currentIndex] = new Event(desc, at);
                            } else {
                                throw new DukeException("     \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                            }
                            System.out.printf("    Got it. I've added this task:\n      %s\n" +
                                    "    Now you have %d tasks in the list.\n", itemsLst[currentIndex], ++currentIndex);
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
