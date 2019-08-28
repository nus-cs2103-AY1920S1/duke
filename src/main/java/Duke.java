import java.io.File;
import java.io.IOException;
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

        try {
            String filePath = "data/duke.txt";
            DukeFile dk = new DukeFile(filePath);

            String input = "";
            ArrayList<Task> lst = dk.getFileContents();
            while (!input.equals("bye")) {
                try {
                    input = scanner.nextLine();

                    if (input.equals("list")) {
                        lst = dk.getFileContents();
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < lst.size(); i++) {
//                            System.out.println(lst.get(i).getStatusIcon());
                            System.out.println(i + 1 + ". " + lst.get(i));
                        }
                    } else if (input.equals("bye")) {
                        break;
                    } else {
                        String[] inputArr = input.split(" ", 2);
                        String command = inputArr[0];
                        if (command.equals("done")) {
                            try {
                                int i = Integer.parseInt(inputArr[1]) - 1;
                                lst.get(i).done();
                                String nice = "Nice! I've marked this task as done:";
                                System.out.println(nice + "\n\t" + lst.get(i));
                            } catch (IndexOutOfBoundsException ex) {
                                System.out.println("\\u2639 OOPS!!! I'm sorry, that task does not exist");
                            }
                        } else if (command.equals("delete")) {
                            try {
                                int i = Integer.parseInt(inputArr[1]) - 1;
                                Task task = lst.get(i);
                                lst.remove(i);
                                String noted = "Noted. I've removed this task:\n\t" + task + "\nNow you have " + lst.size() + " tasks in the list.";
                                System.out.println(noted);
                            } catch (IndexOutOfBoundsException ex) {
                                System.out.println("\\u2639 OOPS!!! I'm sorry, that task does not exist");
                            }
                        } else {
                            Task task = new Task("");
                            DukeEnum de = null;
                            try {
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
                                boolean hasDescription = false;
                                boolean hasTime = false;
                                try {
                                    if (de.equals(DukeEnum.TODO)) {
                                        String todoDesc = inputArr[1];
                                        hasDescription = true;
                                        task = new Todo(todoDesc);
                                    } else if (de.equals(DukeEnum.DEADLINE) || de.equals(DukeEnum.EVENT)) {
                                        String description = inputArr[1];
                                        hasDescription = true;
                                        String[] taskArr = description.split(" /", 2);
                                        String atBy = taskArr[1];
                                        hasTime = true;
                                        if(de.equals(DukeEnum.DEADLINE)){
                                            String byString = atBy.split(" ", 2)[1];
                                            String dt = DateTimeHandler.getDateTime(byString);
                                            task = new Deadline(taskArr[0], dt);
                                        } else {
                                            String atString = atBy.split(" ", 2)[1];
                                            DateTimeRangeHelper dt = DateTimeHandler.getDateTimeRange(atString);
                                            task = new Event(taskArr[0], dt, atString);
                                        }
                                    }

                                    lst.add(task);
                                    String gotIt = "Got it. I've added this task:\n\t" + task + "\nNow you have " + lst.size() + " tasks in the list.";
                                    System.out.println(gotIt);
                                } catch (ArrayIndexOutOfBoundsException ex) {
                                    if (de.equals(DukeEnum.TODO)) {
                                        throw new TodoException("", hasDescription);
                                    } else if (de.equals(DukeEnum.DEADLINE)) {
                                        throw new DeadlineException("", hasDescription, hasTime);
                                    } else {
                                        throw new EventException("", hasDescription, hasTime);
                                    }
                                }

                            } catch (TaskException ex) {
                                System.out.println(ex);
                            }


                        }
                        dk.writeToFile(lst); //save everything in the txt file
                    }
                } catch (Exception ex) {
                    System.out.println("\\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (Exception ex) {
            System.out.println("\\u2639 OOPS!!! I'm sorry, but I could not load your saved task list ");
        }


        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }
}
