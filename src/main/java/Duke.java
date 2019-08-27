import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws Exception {

        ArrayList<Task> storinglist;

        String logo = "  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n";

        String welcome = "____________________________________________________________\n"
                + logo
                + "\n Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________";

        System.out.println(welcome);

        Scanner scanner = new Scanner(System.in);

        storinglist = new ArrayList<>();


        while (true) {
            try {
                String command = scanner.nextLine();

                if (command.equals("list")) {
                    System.out.println("____________________________________________________________");
                    for (int x = 0; x < storinglist.size(); x++) {
                        System.out.println(x + 1 + ". " + storinglist.get(x));
                    }
                    System.out.println("\n____________________________________________________________");
                } else if (command.contains("done")) {
                    if (!command.contains(" ")) {
                        throw new IllegalCommandException("There must be a number after done.");
                    }
                    String splitstring[] = command.split(" ");
                    int taskdone = Integer.valueOf(splitstring[1]);
                    storinglist.get(taskdone - 1).markasdone();
                    System.out.println("____________________________________________________________\n"
                            + " Nice! I've marked this tasked as done:\n"
                            + storinglist.get(taskdone - 1)
                            + "\n\n____________________________________________________________");
                } else if (command.equals("bye")) {
                    System.out.println("____________________________________________________________\n"
                            + " Bye. Hope to see you again soon!\n\n"
                            + "____________________________________________________________");
                    break;
                } else if (command.contains("todo")) {
                    if (!command.contains(" ")){
                        throw new IllegalCommandException("The description of a todo cannot be empty.");
                    }
                    String splitcommand[] = command.split(" ", 2);
                    storinglist.add(new ToDos(splitcommand[1]));
                    System.out.println("____________________________________________________________\n"
                            + " Got it. I've added this task:\n" + "   " + storinglist.get(storinglist.size()-1)
                            + "\n Now you have " + storinglist.size() + " tasks in the list."
                            + "\n____________________________________________________________");

                } else if (command.contains("deadline")) {
                    if (!command.contains(" ")) {
                        throw new IllegalCommandException("The description of a deadline cannot be empty.");
                    }
                    String splitcommand[] = command.split(" ", 2);
                    String splitdeadline[] = (splitcommand[1].split("/"));
                    storinglist.add(new Deadline(splitdeadline[0], splitdeadline[1]));
                    System.out.println("____________________________________________________________\n"
                            + " Got it. I've added this task:\n" + "   " + storinglist.get(storinglist.size()-1)
                            + "\n Now you have " + storinglist.size() + " tasks in the list."
                            + "\n____________________________________________________________");

                } else if (command.contains("event")) {
                    if (!command.contains(" ")) {
                        throw new IllegalCommandException("The description of an event cannot be empty.");
                    }
                    String splitcommand[] = command.split(" ", 2);
                    String splitevent[] = (splitcommand[1].split("/"));
                    storinglist.add(new Events(splitevent[0], splitevent[1]));
                    System.out.println("____________________________________________________________\n"
                            + " Got it. I've added this task:\n" + "   " + storinglist.get(storinglist.size() - 1)
                            + "\n Now you have " + storinglist.size() + " tasks in the list."
                            + "\n____________________________________________________________");
                } else if (command.contains("delete")) {
                    if (!command.contains(" ")) {
                        throw new IllegalCommandException("Give a task to delete.");
                    }
                    String splitcommand[] = command.split(" ");
                    int taskdelete = Integer.valueOf(splitcommand[1]);
                    System.out.println("____________________________________________________________\n"
                            + "Noted. I've removed this task: \n"
                            + "  " + storinglist.get(taskdelete - 1));
                    storinglist.remove(taskdelete - 1);
                    System.out.println("Now you have " + storinglist.size()
                            + " tasks in the list "
                            + "\n____________________________________________________________");

                }
                    else {
                    throw new IllegalCommandException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (IllegalCommandException errormsg) {
                System.err.println(errormsg);
            }

        }
    }
}
