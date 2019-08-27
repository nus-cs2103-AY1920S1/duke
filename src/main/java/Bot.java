public class Bot {

    enum TaskType {
        TODO, DEADLINE, EVENT;
    }

    Task[] list = new Task[100];
    int listIndex = 0;

    public void greeting () {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void add (String command, TaskType taskType) throws DukeException {

        DukeException emptyDescription = new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");

        String[] words = command.split(" ",2);

        if (words.length == 1) throw emptyDescription;

        String description;

        switch (taskType) {
        case TODO:
            description = words[1];

            if (description.isBlank()) throw emptyDescription;

            list[listIndex++] = new Todo(description);
            break;
        case DEADLINE:
            String[] split = words[1].split(" /by ", 2);

            if (split.length == 1) throw emptyDescription;

            description = split[0];
            String by = split[1];

            if (description.isBlank() || by.isBlank()) throw emptyDescription;

            list[listIndex++] = new Deadline(description, by);
            break;
        case EVENT:
            split = words[1].split(" /at ", 2);

            if (split.length == 1) throw emptyDescription;

            description = split[0];
            String at = split[1];

            if (description.isBlank() || at.isBlank()) throw emptyDescription;

            list[listIndex++] = new Event(description, at);
            break;
        }

        System.out.println("Got it. I've added this task:");

        System.out.println(list[listIndex-1]);

        System.out.printf("Now you have %d tasks in the list.\n", listIndex);
    }

    public void done (String command) {

        String[] words = command.split(" ", 2);

        System.out.println("Nice! I've marked this task as done:");

        int index = Integer.valueOf(words[1]) - 1;

        list[index].markAsDone();

        System.out.println(list[index]);

    }

    public void list() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < listIndex; i++) {
            System.out.println(i+1 + "." + list[i]);
        }
    }
}
