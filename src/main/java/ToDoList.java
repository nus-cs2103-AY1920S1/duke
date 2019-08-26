import java.util.Scanner;
import java.util.ArrayList;

public class ToDoList {

    public void run() {
        Scanner sc = new Scanner(System.in);
        String border = "    ____________________________________________________________";
        ArrayList<Task> arr = new ArrayList<Task>();
        int counter = 0;

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(border);
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.println("     " + (i + 1) + "." + arr.get(i));
                }
                System.out.println(border);
            } else {
                String[] temp = input.split(" ");
                if (temp.length == 0) {
                    System.out.println(border);
                    System.out.println("     Please input something :(.");
                    System.out.println(border);
                } else if (temp[0].equals("done")) {
                    try {
                        int done = Integer.parseInt(temp[1]) - 1;
                        arr.get(done).markAsDone();
                        System.out.println(border);
                        System.out.println("     Nice! I've marked this task as done: ");
                        System.out.println("       " + arr.get(done));
                        System.out.println(border);
                    } catch (NullPointerException e) {
                        System.out.println(border);
                        System.out.println("     Please input a valid task number.");
                        System.out.println(border);
                    }

                } else if (temp[0].equals("delete")) { //command to delete task
                    try {
                        Task toRemove = arr.get(Integer.parseInt(temp[1]) - 1);
                        counter--;
                        System.out.println(border);
                        System.out.println("     Noted. I've removed this task:");
                        System.out.println("       " + toRemove);
                        System.out.printf("     Now you have %s tasks in the list.\n", counter);
                        System.out.println(border);
                        arr.remove(Integer.parseInt(temp[1]) - 1);

                    } catch (NullPointerException e) {
                        System.out.println(border);
                        System.out.println("     Please input a valid task number to delete.");
                        System.out.println(border);
                    }
                } else { //command to add task to list

                    DateTime date;
                    String message;
                    boolean added = false;

                    try {
                        switch (temp[0]) {
                        case "deadline":
                            message = input.substring(input.indexOf(' ') + 1, input.indexOf("/") - 1);
                            date = new DateTime(input.substring(input.indexOf("/") + 4));
                            arr.add(new Deadlines(message, date));
                            added = true;
                            break;
                        case "event":
                            message = input.substring(input.indexOf(' ') + 1, input.indexOf("/") - 1);
                            date = new DateTime(input.substring(input.indexOf("/") + 4));
                            arr.add(new Events(message, date));
                            added = true;
                            break;
                        case "todo":
                            if (temp.length < 2) {
                                throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
                            }
                            message = input.substring(input.indexOf(' ') + 1);
                            arr.add(new ToDos(message));
                            added = true;
                            break;
                        }

                        if (added) {
                            counter++;
                            System.out.println(border);
                            System.out.println("     Got it. I've added this task: ");
                            System.out.println("       " + arr.get(counter - 1));
                            System.out.printf("     Now you have %s tasks in the list.\n", counter);
                            System.out.println(border);
                        } else {
                            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                    } catch (DukeException e) {
                        System.out.println(e);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(border);
                        System.out.println("     Please input a task and date.");
                        System.out.println(border);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(border);
                        System.out.println("     Please input a valid date and time.");
                        System.out.println(border);
                    }

                }
            }

            input = sc.nextLine();
        }
        if (input.equals("bye")) {
            System.out.println(border + "\n" + "     Bye. Hope to see you again soon!" + "\n" + border);
        }
    }
}
