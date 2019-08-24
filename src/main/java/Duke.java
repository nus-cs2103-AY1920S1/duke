import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greeting);
        ArrayList<Task> tasks = new ArrayList<>();
        int index = 0;
        try {
            File f = new File("data/duke.txt");
            System.out.println("here");
            f.getParentFile().mkdirs();
            f.createNewFile();
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            System.out.println("here1");
            String line = null;
            while ((line = bfr.readLine()) != null) {
                System.out.println("heres");
                String[] stringArr = line.split(" | ");
                if (stringArr[0].equals("E")) {
                    Event event = new Event(stringArr[2], stringArr[3]);
                    if (stringArr[1] == "1") {
                        event.markAsDone();
                    }
                    tasks.add(event);
                } else if (stringArr[0].equals("T")) {
                    Todo td = new Todo(stringArr[2]);
                    if (stringArr[1] == "1") {
                        td.markAsDone();
                    }
                    tasks.add(td);
                } else {
                    Deadline dl = new Deadline(stringArr[2], stringArr[3]);
                    if (stringArr[1] == "1") {
                        dl.markAsDone();
                    }
                    tasks.add(dl);;
                }

            }
        } catch (Exception e) {
            System.out.println("Error with the data file initialization");
        }
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();


        while (!command.equals("bye")) {
            try {
                if (command.length() < 4) {
                    String error = "\u2639 OOPS!!! I'm sorry but I don't know what that means :-(\n";
                    throw new DukeException(error);
                }
                if (command.length() >= 6 && command.substring(0, 6).equals("delete")) {
                    //deleting task
                    if (command.contains(" ")) {
                        //throw exception for no task number and there is just trailing whitespaces
                        String res = command.replace(" ", "");
                        if (res.length() == 6) {
                            String error = "\u2639 OOPS!!! Please input the task number you would like to delete.\n";
                            throw new DukeException(error);
                        }
                    } else if (command.length() == 6) {
                        //throw exception for no task number
                        String error = "\u2639 OOPS!!! Please input the task number you would like to delete.\n";
                        throw new DukeException(error);
                    }
                    int curr = Integer.parseInt(command.substring(7));
                    if (index == 0) {
                        //check if list has no task to throw exception
                        String error = "\u2639 OOPS!!! You do not have any tasks in your list.\n";
                        throw new DukeException(error);
                    } else if (curr > index) {
                        //check if index is within list size or throw exception
                        String error = "\u2639 OOPS!!! You do not have that task in your list. "
                                + "Call 'list' to see all your tasks :-)\n";
                        throw new DukeException(error);
                    }
                    String markAsDoneMsg = "Noted. I've removed this task:\n" +
                            tasks.get(curr - 1);
                    tasks.remove(curr - 1);
                    System.out.println(markAsDoneMsg);
                    index--;
                    String statusOfList;
                    if (index == 1) {
                        statusOfList = "Now you have " + index + " task in the list.\n";
                    } else {
                        statusOfList = "Now you have " + index + " tasks in the list.\n";
                    }
                    System.out.println(statusOfList);
                    try {
                        FileWriter fw = new FileWriter("data/duke.txt");
                        String textFileMsg = "";
                        for (int i = 0; i < index; i++) {
                            if (i == 0) {
                                textFileMsg = tasks.get(i).toWriteIntoFile();
                            } else {
                                textFileMsg = System.lineSeparator() + tasks.get(i).toWriteIntoFile();
                            }
                        }
                        fw.write(textFileMsg);
                        fw.close();

                    } catch (Exception e) {
                        System.out.println("Unable to write to file");
                    }

                } else if (!command.equals("list") && !command.substring(0, 4).equals("done")) {
                    //adding new task of 1 of 3 types
                    if (command.length() >= 4 && command.substring(0, 4).equals("todo")) {
                        if (command.length() == 4) {
                            //throw exception for no description
                            String error = "\u2639 OOPS!!! The description of todo cannot be empty.\n";
                            throw new DukeException(error);
                        } else if (!command.substring(4,5).equals(" ")) {
                            //throw exception for no description and there is just trailing whitespaces
                            String error = "\u2639 OOPS!!! Please input a whitespace between the command 'todo' "
                                +"and your task description for me to keep track of it correctly :-)\n";
                            throw new DukeException(error);
                        } else if (command.contains(" ")) {
                            String res = command.replaceAll(" ", "");
                            if (res.length() == 4) {
                                String error = "\u2639 OOPS!!! The description of todo cannot be empty.\n";
                                throw new DukeException(error);
                            }
                        }
                        tasks.add(new Todo(command.substring(5)));
                    } else if (command.length() >= 5 && command.substring(0, 5).equals("event")) {
                       if (command.length() == 5) {
                           //throw exception for no description
                           String error = "\u2639 OOPS!!! The description of event cannot be empty.\n";
                           throw new DukeException(error);
                       } else if (!command.substring(5,6).equals(" ")) {
                           //throw exception for no whitespace after event
                           String error = "\u2639 OOPS!!! Please input a whitespace between the command 'event' "
                                   + "and your task description for me to keep track of it correctly :-)\n";
                           throw new DukeException(error);
                       } else if (command.contains(" ")) {
                           //throw exception for no description and there is just trailing whitespaces
                           String res = command.replaceAll(" ", "");
                           if (res.length() == 5) {
                               String error = "\u2639 OOPS!!! The description of event cannot be empty.\n";
                               throw new DukeException(error);
                           }
                       }
                        if (!command.contains(" /at ") && command.contains("/at")) {
                            //throw exception for wrong user input syntax for incorrect whitespaces for /at
                            String error = "\u2639 OOPS!!! Please input a whitespace before and after '/at' for me "
                                    + "to keep track of the date/time correctly :-)\n";
                            throw new DukeException(error);
                        } else if (!command.contains(" /at ")) {
                            //throw exception for no /at
                           String error = "\u2639 OOPS!!! You would need to schedule a date and time duration for this event using '/at'.\n";
                           throw new DukeException(error);
                       }
                        String[] arr = command.split(" /at ", 2);
                        tasks.add(new Event(arr[0].substring(6), arr[1]));
                    } else if (command.length() >= 8 && command.substring(0, 8).equals("deadline")) {
                        if (command.length() == 8) {
                            //throw exception for no description
                            String error = "\u2639 OOPS!!! The description of deadline cannot be empty.\n";
                            throw new DukeException(error);
                        } else if (!command.substring(8, 9).equals(" ")) {
                            //throw exception for no whitespace after deadline
                            String error = "\u2639 OOPS!!! Please input a whitespace between the command 'deadline' "
                                    + "and your task description for me to keep track of it correctly :-)\n";
                            throw new DukeException(error);
                        } else if (command.contains(" ")) {
                            // throw exception for no description and there is just trailing whitespaces
                            String res = command.replaceAll(" ", "");
                            if (res.length() == 8) {
                                String error = "\u2639 OOPS!!! The description of deadline cannot be empty.\n";
                                throw new DukeException(error);
                            }
                        }
                        if (!command.contains(" /by ") && command.contains("/by")) {
                            //throw exception for incorrect whitespaces for /by
                            String error = "\u2639 OOPS!!! Please input a whitespace before and after '/by' for me to "
                                    + "keep track of the date/time correctly :-)\n";
                            throw new DukeException(error);
                        } else if (!command.contains(" /by ")) {
                            //throw exception for no /by
                            String error = "\u2639 OOPS!!! You would need to schedule a date/time for this deadline using '/by'.\n";
                            throw new DukeException(error);
                        }
                        String[] arr = command.split(" /by ", 2);
                        tasks.add(new Deadline(arr[0].substring(9), arr[1]));
                    } else {
                        //throw exception for wrong command
                        String error = "\u2639 OOPS!!! I'm sorry but I don't know what that means :-(\n";
                        throw new DukeException(error);
                    }
                    String commandMsg = "Got it. I've added this task:\n"
                            + tasks.get(index);

                    index++;
                    System.out.println(commandMsg);
                    String statusOfList;
                    if (index == 1) {
                        statusOfList = "Now you have " + index + " task in the list.\n";
                    } else {
                        statusOfList = "Now you have " + index + " tasks in the list.\n";
                    }
                    System.out.println(statusOfList);
                } else if (command.substring(0, 4).equals("done")) {
                    //marking task as done
                    if (command.contains(" ")) {
                        //throw exception for no task number and there is just trailing whitespaces
                        String res = command.replace(" ", "");
                        if (res.length() == 4) {
                            String error = "\u2639 OOPS!!! Please input the task number you would like to mark as done.\n";
                            throw new DukeException(error);
                        }
                    } else if (command.length() == 4) {
                        //throw exception for no task number
                        String error = "\u2639 OOPS!!! Please input the task number you would like to mark as done.\n";
                        throw new DukeException(error);
                    }
                    int curr = Integer.parseInt(command.substring(5));
                    if (curr > index) {
                        //check if index is within list size or throw exception
                        String error = "\u2639 OOPS!!! You do not have that task in your list. " +
                                "Call 'list' to see all your tasks :-)\n";
                        throw new DukeException(error);
                    }
                    tasks.get(curr - 1).markAsDone();
                    String markAsDoneMsg = "Nice! I've marked this task as done:\n" +
                            "[" + tasks.get(curr - 1).getStatusIcon() + "] " + tasks.get(curr - 1).getDescription() + "\n";
                    System.out.println(markAsDoneMsg);
                } else {
                    //listing tasks out
                    String listMsg = "Here are the tasks in your list:";
                    System.out.println(listMsg);
                    for (int i = 0; i < index; i++) {
                        Task task = tasks.get(i);
                        String taskMsg = (i + 1) + ". " + task;
                        System.out.println(taskMsg);
                    }
                    System.out.println();
                }
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                command = sc.nextLine();
            }
        }

        String exitMsg = "Bye. Hope to see you again soon!\n";
        System.out.println(exitMsg);
    }
}
