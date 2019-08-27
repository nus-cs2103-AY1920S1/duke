import DukePkg.Task;
import DukePkg.Deadline;
import DukePkg.Event;
import DukePkg.Todo;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String greeting = "Hello! I'm Duke\n" +
                          "What can I do for you?";
        System.out.println(greeting);

        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            tasks = loadList();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<String> modifyingCommands = new ArrayList<>(){{add("done"); add("delete"); add("todo");add("deadline");add("event");}};

        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()){
            String command = input.nextLine();
            String arr[] = command.split(" ", 2);
            try{
                switch(arr[0]) {
                    case "list":
                        if (arr.length > 1) {
                            throw new FormatException("☹ OOPS!!! The list command should just be \"list\".");
                        }
                        System.out.println("Here are the tasks in your list:");
                        int counter = 0;
                        for (Task t : tasks) {
                            counter++;
                            System.out.println(counter + ". " + t.toString());
                        }
                        break;
                    case "bye":
                        if (arr.length > 1) {
                            throw new FormatException("☹ OOPS!!! The bye command should just be \"bye\".");
                        }
                        System.out.println("Bye. Hope to see you again soon!");
                        System.exit(0);
                    case "done":
                    case "delete":
                        if (arr.length == 1 || arr.length > 2) {
                            throw new FormatException("☹ OOPS!!! The " + arr[0] + " command should be " + arr[0] + " + task No.");
                        }
                        if (!arr[1].matches("(?<=\\s|^)\\d+(?=\\s|$)")){
                            throw new FormatException("☹ OOPS!!! The " + arr[0] + " command should be followed by a integer.");
                        }
                        int index = Integer.parseInt(arr[1]) - 1;
                        if(index >= tasks.size() || index < 0) {
                            throw new FormatException("☹ OOPS!!! The task No. you refer to is non-existent. Try another one.");
                        }
                        String prompt;
                        if(arr[0].equals("done")){
                            tasks.get(index).markDone();
                            prompt = "Nice! I've marked this task as done:\n" +
                                    "    " + tasks.get(index).toString();
                        } else {
                            Task t = tasks.get(index);
                            tasks.remove(index);
                            prompt = "Noted. I've removed this task:\n" +
                                            "    " + t.toString() + "\n" +
                                            "Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.";
                        }
                        System.out.println(prompt);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        if (arr.length == 1) {
                            throw new FormatException("☹ OOPS!!! The description of a " + arr[0] + " cannot be empty.");
                        }
                        Task t = new Task("");
                        t = new Todo(arr[1]);
                        if (!arr[0].equals("todo")) {
                            if(arr[0].equals("deadline")) {
                                String ddl[] = arr[1].trim().split("/by", 2);
                                if(ddl.length < 2) {
                                    throw new FormatException("☹ OOPS!!! Format of " + arr[0] + " should be deadline description /by time.");
                                } else if(arr[1].trim().matches("/by.*")) {
                                    throw new FormatException("☹ oops!!! you forget to add description for the " + arr[0] + " command.");
                                } else if(arr[1].trim().matches(".*/by")) {
                                    throw new FormatException("☹ oops!!! you forget to add time for the " + arr[0] + " command.");
                                }
                                t = new Deadline(ddl[0].trim(), ddl[1].trim());
                            } else {
                                String evt[] = arr[1].trim().split("/at", 2);
                                if(evt.length < 2) {
                                    throw new FormatException("☹ OOPS!!! Format of " + arr[0] + " should be event description /at time.");
                                } else if(arr[1].trim().matches("/at.*")) {
                                    throw new FormatException("☹ oops!!! you forget to add description for the " + arr[0] + " command.");
                                } else if(arr[1].trim().matches(".*/at")) {
                                    throw new FormatException("☹ oops!!! you forget to add time for the " + arr[0] + " command.");
                                }
                                t = new Event(evt[0].trim(), evt[1].trim());
                            }
                        }
                        tasks.add(t);
                        String output = "Got it. I've added this task:\n" +
                                "    " + t.toString() + "\n" +
                                "Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the list.";
                        System.out.println(output);
                        break;
                    default:
                        throw new UnrecognizedException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                if(modifyingCommands.contains(arr[0])) {
                    try {
                        saveList(tasks);
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                }
            } catch(FormatException e) {
                System.out.println(e);
            } catch(UnrecognizedException e) {
                System.out.println(e);
            }
        }
    }

    static ArrayList<Task> loadList() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File("data/duke.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String arr[] = line.split(" # ");
            Task t;
            if (arr.length == 3) {
                t = new Todo(arr[2]);
            } else if (arr[0].equals("D")) {
                t = new Deadline(arr[2], arr[3]);
            } else {
                t = new Event(arr[2], arr[3]);
            }
            if (Integer.parseInt(arr[1]) == 1) {
                t.markDone();
            }
            tasks.add(t);
        }
        return tasks;
    }

    static void saveList(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt", false);
        for(Task t : tasks) {
            String s = (t instanceof Todo) ? "T # " : ((t instanceof Event) ? "E # " : "D # ");
            s += t.isDone() ? "1 # " : "0 # " ;
            s += t.getTask();
            s += (t instanceof Todo) ? "" : ((t instanceof Event) ? " # " + ((Event) t).getAt() : " # " + ((Deadline) t).getBy());

            fw.write(s + "\n");
        }
        fw.close();
    }
}
