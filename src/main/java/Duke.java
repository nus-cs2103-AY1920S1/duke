import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File("data/duke.txt");
        try {
            Scanner sc2 = new Scanner(f);
            while (sc2.hasNext()) {
                String type = sc2.next();
                switch (type) {
                case "T":
                    String todo = sc2.nextLine().trim();
                    String[] todoArray = todo.split("\\|");
                    Task todoTask = new Todo(todoArray[2].trim());
                    if (todoArray[1].trim().equals("1")) {
                        todoTask.markAsDone();
                    }
                    tasks.add(todoTask);
                    break;
                case "D":
                    String deadline = sc2.nextLine().trim();
                    String[] deadlineArray = deadline.split("\\|");
                    Task deadlineTask = new Deadline(deadlineArray[2].trim(), deadlineArray[3].trim());
                    if (deadlineArray[1].trim().equals("1")) {
                        deadlineTask.markAsDone();
                    }
                    tasks.add(deadlineTask);
                    break;
                case "E":
                    String event = sc2.nextLine().trim();
                    String[] eventArray = event.split("\\|");
                    Task eventTask = new Event(eventArray[2].trim(), eventArray[3].trim());
                    if (eventArray[1].trim().equals("1")) {
                        eventTask.markAsDone();
                    }
                    tasks.add(eventTask);
                    break;
                default:
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }

        while (sc.hasNext()) {
            String command = sc.next();
            switch (command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                return;
            case "list":
                System.out.println("Here are the " + (tasks.size() == 1 ? "task" : "tasks") + " in your list:");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + "." + tasks.get(i - 1));
                }
                break;
            case "done":
                try {
                    int number = sc.nextInt();
                    tasks.get(number - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(number - 1));
                    String s = "";
                    Scanner scanFile = new Scanner(f);
                    for (int i = 1; i <= tasks.size(); i++) {
                        if (i == number) {
                            if (i == tasks.size()) {
                                s = s + scanFile.nextLine().replaceFirst("0", "1");
                            } else {
                                s = s + scanFile.nextLine().replaceFirst("0", "1") + System.lineSeparator();
                            }
                        } else {
                            if (i == tasks.size()) {
                                s = s + scanFile.nextLine();
                            } else {
                                s = s + scanFile.nextLine() + System.lineSeparator();
                            }
                        }
                    }
                    FileWriter fw = new FileWriter("data/duke.txt");
                    fw.write(s);
                    fw.close();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid task name.");
                    sc.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid task number.");
                } catch (FileNotFoundException e) {
                    System.out.println("File does not exist");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "todo":
                try {
                    String taskname = sc.nextLine().trim();
                    if (taskname.equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task t = new Todo(taskname);
                    FileWriter append = new FileWriter("data/duke.txt", true);
                    if (tasks.size() == 0) {
                        append.write("T | 0 | " + taskname);
                    } else {
                        append.write("\n" + "T | 0 | " + taskname);
                    }
                    append.close();
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "deadline":
                try {
                    String deadline = sc.nextLine().trim();
                    if (deadline.equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] arrDeadline = deadline.split("/by");
                    Task t = new Deadline(arrDeadline[0].trim(), arrDeadline[1].trim());
                    FileWriter append = new FileWriter("data/duke.txt", true);
                    if (tasks.size() == 0) {
                        append.write("D | 0 | " + arrDeadline[0].trim() + " | " + arrDeadline[1].trim());
                    } else {
                        append.write("\n" + "D | 0 | " + arrDeadline[0].trim() + " | " + arrDeadline[1].trim());
                    }
                    append.close();
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "event":
                try {
                    String event = sc.nextLine().trim();
                    if (event.equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    String[] arrEvent = event.split("/at");
                    Task task = new Event(arrEvent[0].trim(), arrEvent[1].trim());
                    FileWriter append = new FileWriter("data/duke.txt", true);
                    if (tasks.size() == 0) {
                        append.write("E | 0 | " + arrEvent[0].trim() + " | " + arrEvent[1].trim());
                    } else {
                        append.write("\n" + "E | 0 | " + arrEvent[0].trim() + " | " + arrEvent[1].trim());
                    }
                    append.close();
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "delete":
                try {
                    int deletionNumber = sc.nextInt();
                    String s = "";
                    Scanner scanFile = new Scanner(f);
                    for (int i = 1; i <= tasks.size(); i++) {
                        if (i == deletionNumber) {
                            scanFile.nextLine();
                        } else if (i == tasks.size()) {
                            s = s + scanFile.nextLine();
                        } else if (i == tasks.size() - 1 && tasks.size() == deletionNumber) {
                            s = s + scanFile.nextLine();
                        } else {
                            s = s + scanFile.nextLine() + System.lineSeparator();
                        }
                    }

                    FileWriter fw = new FileWriter("data/duke.txt");
                    fw.write(s);
                    fw.close();
                    Task toDelete = tasks.get(deletionNumber - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + toDelete);
                    tasks.remove(deletionNumber - 1);
                    System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task does not exist.");
                } catch (FileNotFoundException e) {
                    System.out.println("File does not exist");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
