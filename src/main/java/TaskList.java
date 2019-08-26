import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public void addToList(String input) {
        String[] arrOfWords = input.split(" ");
        String taskWithoutType = input.replace(arrOfWords[0], "").trim();
        Task task = new Task(input);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy, hh:mm a");
        try {
            if (taskWithoutType.isEmpty()) {
                throw new DukeException();
            }
            switch (arrOfWords[0]) {
            case "todo":
                task = new ToDo(taskWithoutType);
                list.add(task);
                break;
            case "deadline":
                String[] arrOfWordsDeadline = taskWithoutType.split(" /by ");
                Date formattedDeadline = format.parse(arrOfWordsDeadline[1]);
                task = new Deadline(arrOfWordsDeadline[0], formatter.format(formattedDeadline));
                list.add(task);
                break;
            case "event":
                String[] arrOfWordsEvent = taskWithoutType.split(" /at ");
                Date formattedEventTime = format.parse(arrOfWordsEvent[1]);
                task = new Event(arrOfWordsEvent[0], formatter.format(formattedEventTime));
                list.add(task);
                break;
            }
            print("    Got it. I've added this task:");
            System.out.println("        " + task);
            print("    Now you have " + list.size() + " tasks in the list.");
        } catch (DukeException a) {
            print("    ☹ OOPS!!! The description of a " + arrOfWords[0] + " cannot be empty.");
        } catch (ArrayIndexOutOfBoundsException e) {
            print("    ☹ OOPS!!! The description of a " + arrOfWords[0] + " does not follow the specified format.");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void markAsDone(int num) {
        Task task = list.get(num - 1);
        task.setDone();
        print("    Nice! I've marked this task as done:");
        System.out.println("    " + task);
    }

    public void delete(int num) {
        Task deletedTask = list.get(num - 1);
        list.remove(num - 1);
        print("    Noted. I've removed this task:");
        System.out.println("        " + deletedTask);
        print("    Now you have " + list.size() + " tasks in the list.");
    }

    public void printList() {
        int i = 1;
        print("    Here are the tasks in your list:");
        for (Task task : list) {
            System.out.println("    " + i + ". " + task);
            i++;
        }
    }

    public void loadTasks(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String task = sc.nextLine();
            String[] arr = task.split(" \\| ");
            switch (arr[0]) {
            case "T":
                list.add(new ToDo(arr[2]));
                break;
            case "D":
                list.add(new Deadline(arr[2], arr[3]));
                break;
            case "E":
                list.add(new Event(arr[2], arr[3]));
                break;
            }
            if (arr[1].equals("1")) {
                list.get(list.size() - 1).setDone();
            }
        }
    }

    public void saveTasks(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : list) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                fw.write("D | " + d.isDone() + " | " + d.getDescription() + " | " + d.getBy() + "\n"); // need to overwrite getdescrition?
            } else if (task instanceof Event) {
                Event e = (Event) task;
                fw.write("E | " + e.isDone() + " | " + e.getDescription() + " | " + e.getAt() + "\n");
            } else if (task instanceof ToDo) {
                ToDo t = (ToDo) task;
                fw.write("T | " + t.isDone() + " | " + t.getDescription() + "\n");
            }
        }
        fw.close();
    }

    private void print(String x) {
        System.out.println(x);
    }
}
