import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * The duke project.
     */

    public static void main(String[] args) throws IOException {
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
        ArrayList<Task> list = new ArrayList<>();

        if (sc.hasNextLine()) {
            echo = sc.nextLine();
            while (!echo.equals("bye")) {
                String[] echoArr = echo.split(" ");
                System.out.println(line);
                try {
                    if (echo.equals("list")) {
                        System.out.println("    Here are the tasks in your list:");
                        for (int i = 0; i < counter; i++) {
                            System.out.println("    " + (i + 1) + ". " + list.get(i));
                        }
                    } else if (echoArr[0].equals("done")) {
                        list.get(Integer.parseInt(echoArr[1]) - 1).markAsDone();
                        System.out.println("    Nice! I've marked this task as done: ");
                        System.out.println("     " + list.get(Integer.parseInt(echoArr[1]) - 1));
                    } else if (echoArr[0].equals("deadline") || echoArr[0].equals("todo") || echoArr[0].equals("event")) {
                        if (echoArr[0].equals("deadline")) {
                            try {
                                if (echoArr.length == 1) {
                                    throw new DukeException("☹ OOPS!!! The description of deadline cannot be empty");
                                } else if (!echo.contains("/by")) {
                                    throw new DukeException("☹ OOPS!!! The deadline must be completed by a certain date");
                                } else {
                                    list.add(new Deadline(echo.substring(echo.indexOf(" ") + 1, echo.indexOf("/") - 1),
                                            echo.substring(echo.indexOf("/by") + 4)));
                                }
                            } catch (DukeException exp) {
                                System.out.println("    " + exp.getMessage());
                            }
                        } else if (echoArr[0].equals("todo")) {
                            try {
                                if (echoArr.length == 1) {
                                    throw new DukeException("☹ OOPS!!! The description of deadline cannot be empty");
                                } else {
                                    list.add(new Todo(echo.substring(echo.indexOf(" ") + 1)));
                                }
                            } catch (DukeException exp) {
                                System.out.println("    " + exp.getMessage());
                            }
                        } else if (echoArr[0].equals("event")) {
                            try {
                                if (echoArr.length == 1) {
                                    throw new DukeException("☹ OOPS!!! The description of event cannot be empty");
                                } else if (!echo.contains("/at")) {
                                    throw new DukeException("☹ OOPS!!! The event must be at by a certain date");
                                } else {
                                    list.add(new Event(echo.substring(echo.indexOf(" ") + 1, echo.indexOf("/") - 1),
                                            echo.substring(echo.indexOf("/at") + 4)));
                                }
                            } catch (DukeException exp) {
                                System.out.println("    " + exp.getMessage());
                            }
                        }

                        if (list.size() > counter) {
                            System.out.println("    Got it. I've added this task:");
                            System.out.println("     " + list.get(counter));
                            counter++;
                            System.out.println("    Now you have " + counter + " in the list.");
                        }
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException exp) {
                    System.out.println("    " + exp.getMessage());
                }

                System.out.println(line);
                if (sc.hasNextLine()) {
                    echo = sc.nextLine();
                } else {
                    break;
                }
            }

        }

        if (echo.equals("bye")) {
            System.out.println(line);
            System.out.println("    Bye. Hope to see you again soon!");
            System.out.println(line);
        }
    }
}

