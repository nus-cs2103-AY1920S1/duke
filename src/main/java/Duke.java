import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    private static String dateTimeConvert(String s) {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(s);
            DateFormat df = new SimpleDateFormat("d 'of' MMMMMMMMM yyyy, h:mm a ");
            return df.format(date).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return s;
    }

    private static String getTasks(LinkedList<Task> list) {
        return String.format("Now you have %d tasks in the list.", list.size());
    }

    private static LinkedList<Task> readFile() throws IOException, DukeException {
        File f = new File(filePath);
        LinkedList<Task> lst = new LinkedList<>();

        if (f.exists()) {
            Scanner fs = new Scanner(f);

            while (fs.hasNext()) {
                String next = fs.nextLine();
                String[] arr = next.split("/");
                String task = arr[0];
                int done = Integer.parseInt(arr[1]);
                String desc = arr[2];
                switch (task) {
                    case "D":
                        Deadline deadline = Deadline.of(desc, arr[3]);
                        if (done == 1) {
                            deadline.markAsDone();
                        }
                        lst.addLast(deadline);
                        break;
                    case "T":
                        Todo todo = Todo.of(desc);
                        if (done == 1) {
                            todo.markAsDone();
                        }
                        lst.addLast(todo);
                        break;
                    case "E":
                        Event event = Event.of(desc, arr[3]);
                        if (done == 1) {
                            event.markAsDone();
                        }
                        lst.addLast(event);
                        break;
                }
            }
            fs.close();
        } else {
            f.getParentFile().mkdir();
        }
        return lst;
    }

    private static void saveTasksAsFile(LinkedList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : list) {
            fw.write(taskToString(task) + System.lineSeparator());
        }
        fw.close();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();

        case "bye":
        try {
            saveTasksAsFile(lst);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
            break;
        }
        case "list":
        LinkedList<String> list = new LinkedList<>();
        list.addLast("Here are the tasks in your list:");
        int i = 1;
        for (Task task : lst) {
            list.addLast(String.format("%d.%s", i, task));
            ++i;
        }
        break;
        case "done":
        try {
            int val = Integer.parseInt(arr[1]);
            if (arr.length > 2) throw new InputMismatchException();
            Task temp = lst.get(val - 1);
            temp.markAsDone();
            LinkedList<String> done = new LinkedList<>();
            done.addLast("Nice! I've marked this task as done:");
            done.addLast(String.format("%s", temp));
        } catch (IndexOutOfBoundsException e) {
            error.addLast("☹ OOPS!!! The index to be completed is not within the task list size.");
            error.removeFirst();
        } catch (NumberFormatException e) {
            error.addLast("☹ OOPS!!! Please enter a valid number.");
            error.removeFirst();
        } catch (InputMismatchException e) {
            error.addLast("☹ OOPS!!! Please enter a single number.");
            error.removeFirst();
        }
        break;
        case "todo":
        try {
            Todo todoTask = Todo.of(next);
            lst.addLast(todoTask);
        } catch (DukeException e) {
            error.addLast(e.getMessage());
            error.removeFirst();
        }
        break;
        case "deadline":
        try {
            String[] deadlineArr = next.split("/by");
            Deadline deadlineTask = Deadline.of(deadlineArr[0], deadlineArr[1]);
            lst.addLast(deadlineTask);
        } catch (DukeException e) {
            error.addLast(e.getMessage());
            error.removeFirst();
        } catch (ArrayIndexOutOfBoundsException e) {
            error.addLast("☹ OOPS!!! Please follow correct format of \"deadline [Description] /by [Date]\".");
            error.removeFirst();
        }
        break;
        case "event":
        try {
            String[] eventArr = next.split("/at");
            Event eventTask = Event.of(eventArr[0], eventArr[1]);
            lst.addLast(eventTask);
        } catch (DukeException e) {
            error.addLast(e.getMessage());
            error.removeFirst();
        } catch (ArrayIndexOutOfBoundsException e) {
            error.addLast("☹ OOPS!!! Please follow correct format of \"event [Description] /at [Date]\".");
            error.removeFirst();
        }
        break;
        case "delete":
        try {
            if (arr.length > 2) throw new InputMismatchException();
            Task removed = lst.remove(del - 1);
        } catch (IndexOutOfBoundsException e) {
            error.addLast("☹ OOPS!!! The index to delete is not within the task list size.");
            error.removeFirst();
        } catch (NumberFormatException e) {
            error.addLast("☹ OOPS!!! Please enter a valid number.");
            error.removeFirst();
        } catch (InputMismatchException e) {
            error.addLast("☹ OOPS!!! Please enter a single number.");
            error.removeFirst();
        }
        break;
    }
}
