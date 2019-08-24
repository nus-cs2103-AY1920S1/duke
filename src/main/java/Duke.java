import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final String line = "\t____________________________________________________________";
    private ArrayList<Task> tasks = new ArrayList<>();
    private String filePath;

    public Duke(String filePath) throws IOException {
        this.filePath = filePath;
        File file = new File(filePath);
        if (file.exists()) {
            Scanner fileSc = new Scanner(new File(filePath));
            while (fileSc.hasNextLine()) {
                try {
                    tasks.add(parseFileLine(fileSc.nextLine()));
                } catch (DukeException e) {
                    dukePrint(e.toString());
                }
            }
        } else {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public void start() throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        dukePrint("Hello from\n" + logo + "\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (runCommand(input)) {
            input = sc.nextLine();
        }
    }

    private void dukePrint(String s) {
        System.out.println(line);
        String[] arr = s.split("\n");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("\t " + arr[i]);
        }
        System.out.println(line + "\n");
    }

    private boolean runCommand(String s) throws IOException {
        String[] arg = s.split(" ", 2);
        String result = "";
        try {
            switch (arg[0]) {
            case "bye":
                dukePrint("Bye. Hope to see you again soon!");
                break;
            case "list":
                result = "Here are the tasks in your list:";
                for (int i = 0; i < tasks.size(); i++) {
                    result += "\n" + (i + 1) + ". " + tasks.get(i);
                }
                dukePrint(result);
                break;
            case "done":
            case "delete":
                if (arg.length < 2 || arg[1].trim().length() < 1) {
                    throw new DukeEmptyDescException(arg[0]);
                }
                int idx;
                try {
                    idx = Integer.parseInt(arg[1]) - 1;
                } catch (NumberFormatException e) {
                    throw new DukeInvalidTaskException(arg[1]);
                }
                if (idx < 0 || idx >= tasks.size()) {
                    throw new DukeInvalidTaskException(arg[1]);
                }
                if (arg[0].equals("done")) {
                    tasks.get(idx).markAsDone();
                    result = "Nice! I've marked this task as done:\n  ";
                    dukePrint(result + tasks.get(idx));
                } else if (arg[0].equals("delete")) {
                    result = "Noted. I've removed this task:\n";
                    result += "  " + tasks.remove(idx) + "\n";
                    result += "Now you have " + tasks.size() + " tasks in the list.";
                    dukePrint(result);
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                if (arg.length < 2 || arg[1].trim().length() < 1) {
                    throw new DukeEmptyDescException(arg[0]);
                }
                Task t;
                if (arg[0].equals("todo")) {
                    t = new TodoTask(arg[1]);
                } else if (arg[0].equals("deadline")) {
                    String[] temp = arg[1].split(" /by ", 2);
                    if (temp.length < 2 || temp[1].trim().length() < 1) {
                        throw new DukeEmptyDateException(arg[0]);
                    }
                    t = new DeadlineTask(temp[0], temp[1]);
                } else { //Task must be "event"
                    String[] temp = arg[1].split(" /at ", 2);
                    if (temp.length < 2 || temp[1].trim().length() < 1) {
                        throw new DukeEmptyDateException(arg[0]);
                    }
                    t = new EventTask(temp[0], temp[1]);
                }
                tasks.add(t);
                result = "Got it. I've added this task:\n";
                result += "  " + t + "\n";
                result += "Now you have " + tasks.size() + " tasks in the list.";
                dukePrint(result);
                break;
            default:
                throw new DukeException();
            }
            writeToFile();
        } catch (DukeException e) {
            dukePrint(e.toString());
        } finally {
            return !arg[0].equals("bye");
        }
    }

    private void writeToFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        for (Task t : tasks) {
            bw.write(t.toFileString());
        }
        bw.close();
    }

    private Task parseFileLine(String line) throws DukeException {
        String[] arg = line.split("\t");
        if (arg.length < 3) {
            throw new DukeInvalidTaskFormatException(line);
        }
        Task t;
        switch (arg[0]) {
        case "T":
            t = new TodoTask(arg[2]);
            break;
        case "D":
            if (arg.length < 4) {
                throw new DukeInvalidTaskFormatException(line);
            }
            t = new DeadlineTask(arg[2], arg[3]);
            break;
        case "E":
            if (arg.length < 4) {
                throw new DukeInvalidTaskFormatException(line);
            }
            t = new EventTask(arg[2], arg[3]);
            break;
        default:
            throw new DukeInvalidTaskFormatException(line);
        }
        if (arg[1].equals("1")) {
            t.markAsDone();
        }
        return t;
    }
}
