import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greet);

        String input = "";
        ArrayList<Task> lst = new ArrayList();
        while (!input.equals("bye")) {
            try {
                input = scanner.nextLine();

                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < lst.size(); i++) {
                        System.out.println(i + 1 + ". " + lst.get(i));
                    }
                } else if (input.equals("bye")) {
                    break;
                } else {
                    String[] inputArr = input.split(" ", 2);
                    String command = inputArr[0];
                    if (command.equals("done")) {
                        int i = Integer.parseInt(inputArr[1]) - 1;
                        lst.get(i).done();
                        String nice = "Nice! I've marked this task as done:";
                        System.out.println(nice + "\n\t" + lst.get(i));
                    } else {
                        Task task = new Task("");
                        DukeEnum de = null;
                        try {
//                                switch (command) {
//                                    case "todo":
//                                        String todoDesc = inputArr[1];
//                                        task = new Todo(todoDesc);
//
//                                    case "deadline":
//                                        String deadlineDesc = inputArr[1];
//                                        String[] deadlineArr = deadlineDesc.split("/", 2);
//                                        String at = deadlineArr[1];
//                                        task = new Deadline(deadlineArr[0], at.split(" ", 2)[1]);
//                                        break;
//                                    case "event":
//                                        String evenDesc = inputArr[1];
//                                        String[] eventArr = evenDesc.split("/", 2);
//                                        String by = eventArr[1];
//                                        task = new Event(eventArr[0], by.split(" ", 2)[1]);
//                                        break;
//                                }
                            switch (command) {
                                case "todo":
                                    de = DukeEnum.TODO;
                                    break;
                                case "deadline":
                                    de = DukeEnum.DEADLINE;
                                    break;
                                case "event":
                                    de = DukeEnum.EVENT;
                                    break;
                            }
                            boolean desc = false;
                            boolean time = false;
                            try {
                                if (de.equals(DukeEnum.TODO)) {
                                    String todoDesc = inputArr[1];
                                    desc = true;
                                    task = new Todo(todoDesc);
                                } else if (de.equals(DukeEnum.DEADLINE)) {
                                    String deadlineDesc = inputArr[1];
                                    desc = true;
                                    String[] deadlineArr = deadlineDesc.split("/", 2);
                                    String at = deadlineArr[1];
                                    time = true;
                                    task = new Deadline(deadlineArr[0], at.split(" ", 2)[1]);
                                } else if (de.equals(DukeEnum.EVENT)) {
                                    String evenDesc = inputArr[1];
                                    desc = true;
                                    String[] eventArr = evenDesc.split("/", 2);
                                    String by = eventArr[1];
                                    time = true;
                                    task = new Event(eventArr[0], by.split(" ", 2)[1]);
                                }

                                lst.add(task);
                                String gotit = "Got it. I've added this task:\n\t" + task + "\nNow you have " + lst.size() + " tasks in the list.";
                                System.out.println(gotit);
                            } catch (ArrayIndexOutOfBoundsException ex) {
                                if (de.equals(DukeEnum.TODO)) {
                                    throw new TodoException("", desc);
                                } else if (de.equals(DukeEnum.DEADLINE)) {
                                    throw new DeadlineException("", desc, time);
                                } else {
                                    throw new EventException("", desc, time);
                                }
                            }

                        } catch (TaskException ex) {
                            System.out.println(ex);
                        }


                    }
                }
            } catch (Exception ex) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }


        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }
}
