import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException{
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        Greet();
        Detecting();
    }

    public static void Greet(){
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void Detecting() {
        Scanner sc = new Scanner(System.in);
        Path file = Paths.get("save.txt");
        List<Task> tasks = new LinkedList<>();
        try {
            Scanner fileSc = new Scanner(file).useDelimiter("\\||\\n");
            while (fileSc.hasNext()) {
                String line = fileSc.nextLine();
                String[] lineBreakUp = line.split(" \\| ");
                switch (lineBreakUp[0]) {
                    case "T":
                        tasks.add(new toDo(lineBreakUp[2], lineBreakUp[1].equals("1")));
                        break;
                    case "D":
                        tasks.add(new Deadline(lineBreakUp[2], lineBreakUp[3], lineBreakUp[1].equals("1")));
                        break;
                    case "E":
                        tasks.add(new Event(lineBreakUp[2], lineBreakUp[3], lineBreakUp[1].equals("1")));
                        break;
                    default:
                        System.out.println("wrong input from file");
                }
            }
        } catch (IOException exp) {
            System.out.println("ioExeption caught!");
        }
        System.out.println("Here are your current tasks:");
        for (Task t : tasks) {
            System.out.println(t);
        }
        while(true) {
            String cmd = sc.nextLine();
            Scanner cmdSc = new Scanner(cmd);
            if (cmd.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                String cmdWord = cmdSc.next().toLowerCase();
                switch (cmdWord) {
                    case "todo":
                        try {
                            if (cmdSc.hasNext()) {
                                String toDoTsk = cmdSc.nextLine();
                                tasks.add(new toDo(toDoTsk));
                                System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
                                System.out.println("Now you have " + tasks.size() + " tasks in the list");
                            } else {
                                throw new DukeException("The description of a todo cannot be empty.");
                            }
                        }
                        catch (DukeException exp) {
                            System.out.println("OOPS!!! " + exp.getMessage());
                        }
                        break;
                    //list
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 1; i <= tasks.size(); i++) {
                            Task tsk = tasks.get(i-1);
                            System.out.println(i + ". " + tsk);
                        }
                        break;
                    //deadline
                    case "deadline":
                        try {
                            if (cmdSc.hasNext()) {
                                String tskBy = cmdSc.nextLine();
                                Scanner ddlSc = new Scanner(tskBy).useDelimiter("\\s*/by\\s*");
                                String ddlTsk = ddlSc.next();
                                String ddlBy = ddlSc.next();
                                if (Date.isDate(ddlBy)) {
                                    tasks.add(new Deadline(ddlTsk, new Date(ddlBy)));
                                } else {
                                    tasks.add(new Deadline(ddlTsk, ddlBy));
                                }
                                System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
                                System.out.println("Now you have " + tasks.size() + " tasks in the list");
                            } else {
                                throw new DukeException("The description of a deadline cannot be empty.");
                            }
                        }
                        catch (DukeException exp) {
                            System.out.println("OOPS!!! " + exp.getMessage());
                        }
                        break;
                    //event
                    case "event":
                        try {
                            if (cmdSc.hasNext()) {
                                String tskAt = cmdSc.nextLine();
                                Scanner evtSc = new Scanner(tskAt).useDelimiter("\\s*/at\\s*");
                                String evtTsk = evtSc.next();
                                String evtAt = evtSc.next();
                                if (Date.isDate(evtAt)) {
                                    tasks.add(new Event(evtTsk, new Date(evtAt)));
                                } else {
                                    tasks.add(new Event(evtTsk, evtAt));
                                }
                                System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
                                System.out.println("Now you have " + tasks.size() + " tasks in the list");
                            } else {
                                throw new DukeException("The description of an event cannot be empty.");
                            }
                        }
                        catch (DukeException exp) {
                            System.out.println("OOPS!!! " + exp.getMessage());
                        }
                        break;
                    //done
                    case "done":
                        int numDone = Integer.parseInt(cmdSc.next()) - 1;
                        Task itemDone = tasks.get(numDone);
                        itemDone.mardAsDone();
                        System.out.println("Nice! I've marked this task as done:\n  " + itemDone);
                        break;
                    //delete
                    case "delete":
                        int numDelete = Integer.parseInt(cmdSc.next()) - 1;
                        Task itemDelete = tasks.remove(numDelete);
                        System.out.println("Noted. I've removed the task:\n  " + itemDelete);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        break;

                    default:
                        try {
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        }
                        catch (DukeException exp) {
                            System.out.println("OOPS!!!" + exp.getMessage());
                        }
                }
            }
        }
        Save(tasks);
    }

    private static void Save(List<Task> tasks) {
        Path file = Paths.get("save.txt");
        try {
            List<String> lines = new LinkedList<>();
            for (Task t : tasks) {
                System.out.println("Saving " + t);
                lines.add(t.toSave());
            }
            Files.write(file, lines, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println("error when saving");
        }
    }
}

