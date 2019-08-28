import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Bot {

    DukeException emptyDescription = new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");

    enum TaskType {
        TODO, DEADLINE, EVENT;
    }

    ArrayList<Task> list = new ArrayList<>();

    public void greeting () {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public Task add (String command, TaskType taskType) throws DukeException {

        String[] words = command.split(" ",2);

        if (words.length == 1) throw emptyDescription;

        Task task = null;

        String description;

        switch (taskType) {
        case TODO:
            description = words[1];

            if (description.isBlank()) throw emptyDescription;

            task = new Todo(description);

            break;

        case DEADLINE:
            String[] split = words[1].split(" /by ", 2);

            if (split.length == 1) throw emptyDescription;

            description = split[0];
            String by = split[1];

            if (description.isBlank() || by.isBlank()) throw emptyDescription;

            task = new Deadline(description, by);

            break;

        case EVENT:
            split = words[1].split(" /at ", 2);

            if (split.length == 1) throw emptyDescription;

            description = split[0];
            String at = split[1];

            if (description.isBlank() || at.isBlank()) throw emptyDescription;

            task = new Event(description, at);

            break;
        }

        list.add(task);

        System.out.println("Got it. I've added this task:");

        System.out.println(list.get(list.size() - 1));

        System.out.printf("Now you have %d tasks in the list.\n", list.size());

        return list.get(list.size() - 1);
    }

    public void done (String command) throws DukeException {

        String[] words = command.split(" ", 2);

        if (words.length == 1) throw emptyDescription;

        if (words[1].isBlank()) throw emptyDescription;

        System.out.println("Nice! I've marked this task as done:");

        int index = Integer.valueOf(words[1]) - 1;

        list.get(index).markAsDone();

        System.out.println(list.get(index));

    }

    public void list() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + "." + list.get(i));
        }
    }

    public void delete (String command) throws DukeException {

        String[] words = command.split(" ", 2);

        if (words.length == 1) throw emptyDescription;

        if (words[1].isBlank()) throw emptyDescription;

        System.out.println("Noted. I've removed this task:");

        int index = Integer.valueOf(words[1]) - 1;

        Task task = list.remove(index);

        System.out.println(task);

        System.out.printf("Now you have %d tasks in the list.\n", list.size());

    }

    public void retrieve () throws FileNotFoundException {
        File f = new File(FileWriterClass.DUKE_TXT_PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String text = s.nextLine();

            System.out.println(text);

            String[] split = text.split(" \\| ");

            System.out.println(Arrays.toString(split));

            String description = split[2];

            Task t = null;

            switch (split[0]) {
            case "T":
                t = new Todo(description);
                break;
            case "D":
                t = new Deadline(description, split[3]);
                break;
            case "E":
                t = new Event(description, split[3]);
                break;
            }

            boolean isDone = split[1].equals("1");

            if (t != null) {
                t.isDone = isDone;
                list.add(t);
            }
        }
    }

    public void save () throws IOException {

        String s = "";

        for (Task t : list) {
            s += t.saveFormat() + System.lineSeparator();
        }

        FileWriterClass.writeToFile(FileWriterClass.DUKE_TXT_PATH, s);
    }
}
