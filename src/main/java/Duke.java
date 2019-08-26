import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Duke {
    public static void main(String[] args) throws ParseException, IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String filePath = "Duke_List.txt";
        try {
            loadFileContents(filePath, list);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        String[] arr = sc.nextLine().split(" ");
        String next = arr[0];
        int count = list.size();
        File f = new File(filePath);

        while (!next.equals("bye")) {
            boolean changed = true;
            if (next.equals("list")) {
                changed = false;
                System.out.println("Here are the tasks in your list:");
                int listCount = 1;
                for (Task task : list) {
                    System.out.println(listCount + "." + task);
                    listCount++;
                }
            } else if (next.equals("done")) {
                int number = Integer.parseInt(arr[1]) - 1;
                Task taskDone = list.get(number);
                taskDone.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + taskDone);
            } else if(next.equals("delete")) {
                int number = Integer.parseInt(arr[1]) - 1;
                Task taskRemoved = list.remove(number);
                System.out.println("Noted. I've removed this task:");
                printTask(--count, taskRemoved);
            } else {
                String description = "";
                Task t;
                try {
                    if (next.equals("todo") || next.equals("deadline") || next.equals("event")) {
                        if (arr.length == 1) {
                            throw new EmptyDescriptionException("☹ OOPS!!! The description of a " + next + " cannot be empty.");
                        } else {
                            for (int i = 1; i < arr.length; i++) {
                                description += " " + arr[i];
                            }
                        }
                    } else {
                        throw new UnknownTaskException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("Got it. I've added this task:");
                    count++;
                    if (next.equals("todo")) {
                        t = new Todos(description.trim());
                    } else if (next.equals("deadline")) {
                        int index = description.indexOf("/");
                        String byWhen = description.substring(index + 4);
                        String desc = description.substring(1, index - 1);
                        t = new Deadline(desc, byWhen);
                    } else {
                        int index = description.indexOf("/");
                        String at = description.substring(index + 4);
                        String desc = description.substring(1, index - 1);
                        t = new Event(desc, at);
                    }
                    list.add(t);
                    printTask(count, t);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            arr = sc.nextLine().split(" ");
            next = arr[0];
            FileWriter fw = new FileWriter(filePath);
            String textToAdd = "";
            for (Task task : list) {
                textToAdd += task.print() + "\n";
            }
            fw.write(textToAdd);
            fw.close();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printTask(int count, Task t) {
        if (count == 1) {
            System.out.println("  " + t + "\nNow you have " + count + " task in the list.");
        } else {
            System.out.println("  " + t + "\nNow you have " + count + " tasks in the list.");
        }
    }

    private static void loadFileContents(String filePath, ArrayList<Task> list) throws FileNotFoundException, ParseException {
        File f = new File(filePath); // create a File for the given file path
        Scanner sc = new Scanner(f); // create a Scanner using the File as the source
        Task t;
        while (sc.hasNext()) {
            String next = sc.nextLine();
            String[] arr = next.split("@");
            boolean isDone = arr[1].equals("1");
            String description = arr[2];
            if (arr[0].contains("T")) {
                t = new Todos(description.trim(), isDone);
            } else if (arr[0].contains("D")) {
                t = new Deadline(description.trim(), isDone, arr[3].trim());
            } else {
                t = new Event(description.trim(), isDone, arr[3].trim());
            }

            list.add(t);
        }
    }

}