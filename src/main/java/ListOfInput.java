import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class ListOfInput {
    private List<Task> list;

    public ListOfInput() {
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

    private void print(String x) {
        System.out.println(x);
    }
}
