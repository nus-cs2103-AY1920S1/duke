import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        String filePath = "../../../data/duke.txt";
        File listFile = new File(filePath);
        listFile.createNewFile(); // Directory data needs to exist already
        Scanner scan = new Scanner(System.in);
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        String separationLine = "    ____________________________________________________________";
        System.out.println(separationLine + "\n" + logo + "\n     Hello! I'm Duke\n     What can I do for you?\n"
                + separationLine + "\n");

        ArrayList<Task> taskStore = new ArrayList<>(100);
        Scanner fileScanner = new Scanner(listFile);
        while (fileScanner.hasNext()) {
            String type = fileScanner.next();
            int isDone = Integer.parseInt(fileScanner.next());
            String remaining = fileScanner.nextLine().trim();
            String[] remainingSplit = remaining.split(" \\| ");
            switch (type) {
            case "T":
                taskStore.add(new ToDo(remaining, isDone));
                break;
            case "D":
                taskStore.add(new Deadline(remainingSplit[0], isDone, remainingSplit[1]));
                break;
            case "E":
                taskStore.add(new Event(remainingSplit[0], isDone, remainingSplit[1]));
                break;
            default:
                break;
            }
        }
        String userInput = scan.nextLine();
        while (!"bye".equals(userInput)) {
            boolean shouldOverwriteFile = false;
            try {
                String[] inputSplit = userInput.split(" ");
                switch (inputSplit[0]) {
                case "list":
                    System.out.println(separationLine + "\n     Here are the tasks in your list:");
                    for (Task task : taskStore) {
                        System.out.println("     " + (taskStore.indexOf(task) + 1) + "." + task.toString());
                    }
                    System.out.println(separationLine + "\n");
                    break;
                case "done":
                    if (inputSplit.length != 2) {
                        // Exception if there is no task number or multiple words after "done"
                        throw new DukeException(separationLine
                                + "\n     \u2639 OOPS!!! Please specify number of a single task to mark as done.\n"
                                + separationLine + "\n");
                    }
                    int specifiedDone = Integer.parseInt(inputSplit[1]); // throws NumberFormatException if not int
                    if (specifiedDone < 1 || specifiedDone > taskStore.size()) {
                        // Exception if task number is beyond current number of tasks
                        throw new DukeException(separationLine
                                + "\n     \u2639 OOPS!!! Please specify valid task number.\n" + separationLine + "\n");
                    }
                    Task doneTask = taskStore.get(specifiedDone - 1);
                    doneTask.setDone();
                    shouldOverwriteFile = true;
                    System.out.println(separationLine + "\n     Nice! I've marked this task as done:\n       "
                            + doneTask + "\n" + separationLine + "\n");
                    break;
                case "todo":
                    if (inputSplit.length == 1) {
                        // Exception if no description after "todo"
                        throw new DukeException(separationLine
                                + "\n     \u2639 OOPS!!! The description of a todo cannot be empty.\n" + separationLine + "\n");
                    }
                    ToDo todo = new ToDo(userInput.replace("todo ", ""), 0);
                    taskStore.add(todo);
                    String writeStringT = todo.type + " " + 0 + " " + todo.description + "\n";
                    writeToFile(filePath, writeStringT, true);
                    System.out.println(separationLine + "\n     Got it. I've added this task:\n       " + todo
                            + "\n     Now you have " + taskStore.size() + " tasks in the list." + "\n"
                            + separationLine + "\n");
                    break;
                case "deadline":
                    if (!userInput.contains(" /by ")) {
                        // Exception for invalid deadline format
                        throw new DukeException(separationLine
                                + "\n     \u2639 OOPS!!! For deadline please use the format\n"
                                + "               \"deadline description /by end time\"\n"
                                + separationLine + "\n");
                    }
                    String[] splitStringD = userInput.split(" /by ");
                    Deadline deadline = new Deadline(splitStringD[0].replace("deadline ", ""), 0,
                            splitStringD[1]);
                    taskStore.add(deadline);
                    String writeStringD = deadline.type + " 0" + " " + deadline.description + " | " + deadline.endTime
                            + "\n";
                    writeToFile(filePath, writeStringD, true);
                    System.out.println(separationLine + "\n     Got it. I've added this task:\n       " + deadline
                            + "\n     Now you have " + taskStore.size() + " tasks in the list." + "\n"
                            + separationLine + "\n");
                    break;
                case "event":
                    if (!userInput.contains(" /at ")) {
                        // Exception for invalid deadline format
                        throw new DukeException(separationLine
                                + "\n     \u2639 OOPS!!! For event please use the format\n"
                                + "               \"event description /at period\"\n"
                                + separationLine + "\n");
                    }
                    String[] splitStringE = userInput.split(" /at ");
                    Event event = new Event(splitStringE[0].replace("event ", ""), 0,
                            splitStringE[1]);
                    taskStore.add(event);
                    String writeStringE = event.type + " 0" + " " + event.description + " | " + event.eventPeriod
                            + "\n";
                    writeToFile(filePath, writeStringE, true);
                    System.out.println(separationLine + "\n     Got it. I've added this task:\n       " + event
                            + "\n     Now you have " + taskStore.size() + " tasks in the list." + "\n"
                            + separationLine + "\n");
                    break;
                case "delete":
                    if (inputSplit.length != 2) {
                        // Exception if there is no task number or multiple words after "delete"
                        throw new DukeException(separationLine +
                                "\n     \u2639 OOPS!!! Please specify number of a single task to delete.\n" + separationLine + "\n");
                    }
                    int specifiedDel = Integer.parseInt(inputSplit[1]); // will throw NumberFormatException if not int after "done"
                    if (specifiedDel < 1 || specifiedDel > taskStore.size()) {
                        // Exception if task number is beyond current number of tasks
                        throw new DukeException(separationLine +
                                "\n     \u2639 OOPS!!! Please specify valid task number.\n" + separationLine + "\n");
                    }
                    Task delTask = taskStore.get(specifiedDel - 1);
                    taskStore.remove(specifiedDel - 1);
                    shouldOverwriteFile = true;
                    System.out.println(separationLine + "\n     Noted. I've removed this task:\n       " + delTask
                            + "\n     Now you have " + taskStore.size() + " tasks in the list." + "\n"
                            + separationLine + "\n");
                    break;
                default:
                    // Exception if invalid instruction
                    throw new DukeException(separationLine
                            + "\n     \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                            + separationLine + "\n");
                }
            } catch (DukeException de) {
                System.err.println(de.getMessage());
            } catch (NumberFormatException ne) {
                System.err.println(separationLine
                        + "\n     \u2639 OOPS!!! Please specify task number as one integer only.\n"
                        + separationLine + "\n");
            }

            if (shouldOverwriteFile) {
                String saveToFile = "";
                for (Task t : taskStore) {
                    saveToFile += String.format("%s %d %s", t.type, t.isDone, t.description);
                    if ("D".equals(t.type)) {
                        saveToFile += " | " + ((Deadline) t).endTime;
                    } else if ("E".equals(t.type)) {
                        saveToFile += " | " + ((Event) t).eventPeriod;
                    }
                    saveToFile += "\n";
                }
                writeToFile(filePath, saveToFile, false);
            }
            userInput = scan.nextLine();
        }
        System.out.println(separationLine + "\n     Bye. Hope to see you again soon!\n" + separationLine);
    }

    private static void writeToFile(String filePath, String textToAdd, boolean isAppend) throws IOException {
        FileWriter fw = isAppend ? new FileWriter(filePath, true) : new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
