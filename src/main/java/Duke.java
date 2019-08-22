import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    //protected static String[] list = new String[100];
    protected static ArrayList<Task> arrayList = new ArrayList<Task>();
    // Counter to count total number of items in array.
    protected static int counter = 0;
    protected static Task[] tasking = new Task[100];

    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";

        // Prints out greeting of the chatbot.
        printLine();
        printIndent();
        System.out.println("Hello! I'm\n" + logo + "\n" + "    What can I do for you?");
        printLine();

        Scanner scan = new Scanner(System.in);
        // String array to store text entered by user.
        //String[] list = new String[100];

        while (scan.hasNext()) {
            try {
                String text = scan.nextLine().trim();
                if (text.equals("bye")) {
                    printBye();
                    break;

                } else if (text.equals("list")) {
                    printList();

                } else if (text.indexOf(" ") > -1) {

                    String[] splittedText = text.split(" ");

                    if (splittedText[0].equals("done")) {
                        int num = text.indexOf(" ");
                        printDone(Integer.parseInt(text.substring(num + 1, num + 2)));
                        tasking[num] = arrayList.get(num - 1);  // MIGHT BE WRONG NUM

                    } else {
                        Task.printGI();
                        printIndent();
                        counter++;


                        if (splittedText[0].equals("todo")) {


                            int num = text.indexOf(" ");
                            Task task = new Todo(text.substring(num + 1));

                            System.out.println("  " + task.toString());
                            Task.printNumOfTasks();
                            tasking[counter] = task;
                            arrayList.add(task);


                        } else if (splittedText[0].equals("deadline")) {
                            int num = text.indexOf("/");
                            int num1 = text.indexOf(" ");
                            Task task = new Deadline(text.substring(num1, num - 1), text.substring(num + 4));
                            System.out.println("  " + task.toString());
                            Task.printNumOfTasks();
                            tasking[counter] = task;
                            arrayList.add(task);


                        } else if (splittedText[0].equals("event")) {
                            int num = text.indexOf("/");
                            int num1 = text.indexOf(" ");
                            Task task = new Event(text.substring(num1, num - 1), text.substring(num + 4));
                            System.out.println("  " + task.toString());
                            Task.printNumOfTasks();
                            tasking[counter] = task;
                            arrayList.add(task);
                        }

                        //counter++;
                        //arrayList.add(task);
                    }
                    //tasking[counter] = task;

                } else {
                    printLine();
                    printIndent();
                    if (text.equals("todo")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty. It must be in proper format (i.e. todo clean table).");
                    } else if (text.equals("deadline")) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty. It must be in proper format (i.e. deadline return book /by 23 Aug).");
                    } else if (text.equals("event")) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty. It must be in proper format (i.e. event Don's birthday /at 15 Jan 3pm).");
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                }
            } catch (DukeException e) {

                System.out.println(e);
                printLine();
            }
        }
    }




    // Prints Indentation.
    public static void printIndent() {
        System.out.print("    ");
    }

    // Prints the line. For better organisation.
    public static void printLine() {
        printIndent();
        System.out.println("___________________________________________________________________");
    }

    // Ends the chatbot.
    private static void printBye() {
        printLine();
        printIndent();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    // Shows users what has been added into the list/string array.
    private static void printAdds(String str) {
        printLine();
        printIndent();
        System.out.println("added: " + str);
        printLine();
    }

    private static void printDone(int i) {
        arrayList.get(i-1).markAsDone();
        printLine();
        printIndent();
        System.out.println("Nice! I've marked this task as done:");
        printIndent();
        System.out.println(tasking[i].toString());
        printLine();
    }

    private static void printList() {
        printLine();
        printIndent();
        System.out.println("Here are the tasks in your list:");
        if (counter == 0) {
            printIndent();
            System.out.println("List is empty!");
        } else {
            for (int i = 1; i <= counter; i++) {
                printIndent();
                System.out.println(i + "." + tasking[i].toString());
            }
        }
        printLine();
    }

}
