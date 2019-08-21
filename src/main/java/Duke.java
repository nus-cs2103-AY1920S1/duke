import java.util.Scanner;

public class Duke {
    /**
     * The duke project.
     */

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "    __________________________________";

        System.out.println(line);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        String echo = "";
        int counter = 0;
        Task[] list = new Task[100];

        if (sc.hasNextLine()) {
            echo = sc.nextLine();
            while (!echo.equals("bye")) {
                String[] echoArr = echo.split(" ");
                System.out.println(line);
                if(echo.equals("list")) {
                    System.out.println("    Here are the tasks in your list:");
                    for(int i = 0; i < counter; i++) {
                        System.out.println("    " + (i + 1) + ". " + list[i]);
                    }
                } else if(echoArr[0].equals("done")) {
                    list[Integer.parseInt(echoArr[1]) - 1].markAsDone();
                    System.out.println("    Nice! I've marked this task as done: ");
                    System.out.println("     " + list[Integer.parseInt(echoArr[1]) - 1]);
                } else {
                    if(echoArr[0].equals("deadline")) {
                        list[counter] = new Deadline(echo.substring(echo.indexOf(" ") + 1, echo.indexOf("/") - 1),
                                                     echo.substring(echo.indexOf("/by") + 4));
                    } else if(echoArr[0].equals("todo")) {
                        list[counter] = new Todo(echo.substring(echo.indexOf(" ") + 1));
                    } else if(echoArr[0].equals("event")) {
                        list[counter] = new Event(echo.substring(echo.indexOf(" ") + 1, echo.indexOf("/") - 1),
                                echo.substring(echo.indexOf("/at") + 4));
                    }
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("     " + list[counter]);
                    counter++;
                    System.out.println("    Now you have " + counter + " in the list.");
                }
                System.out.println(line);
                if(sc.hasNextLine()) {
                    echo = sc.nextLine();
                } else {
                    break;
                }

            }

            if (echo.equals("bye")) {
                System.out.println(line);
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(line);
            }
        }

    }
}
