
import java.io.IOException;

class Parser {

    private static int getPrepositionPos(String[] input_split) {
        for (int i = 0; i < input_split.length; i++) {
            if (input_split[i].charAt(0) == '/') {
                return i;
            }
        }
        return 0;
    }
    private static Task construct(String[] input_split)
            throws EmptyDescriptionException, EmptyTimeDueException {
        if (input_split.length < 2) {
            // System.out.println("what's the " + type);
            throw new EmptyDescriptionException();
        }
        Task task;
        int prepAt = getPrepositionPos(input_split);
        StringBuilder description = new StringBuilder();
        String preposition = input_split[prepAt].substring(1);
        StringBuilder memo = new StringBuilder();
        boolean prepRequired = input_split[0].equals("deadline") || input_split[0].equals("event");

        if (prepRequired && prepAt == 0) {
            // System.out.println("what's the date due?");
            throw new EmptyTimeDueException();
        }
        if (prepRequired) {
            for (int k = 1; k < prepAt; k++) {
                description.append(" ").append(input_split[k]);
            }
        } else {
            for (int k = 1; k < input_split.length; k++) {
                description.append(" ").append(input_split[k]);
            }
        }
        for (int i = prepAt + 1; i < input_split.length; i++) {
            memo.append(" ").append(input_split[i]);
        }

        switch (input_split[0]) {
        case "todo":
            task = new ToDo(description.deleteCharAt(0).toString());
            break;
        case "deadline":
            task = new Deadline(description.deleteCharAt(0).toString(), preposition, memo.deleteCharAt(0).toString());
            break;
        case "event":
            task = new Event(description.deleteCharAt(0).toString(), preposition, memo.deleteCharAt(0).toString());
            break;
        default:
            task = null;
        }

        return task;
    }

    void checkValidTaskIndex(String[] input_split) throws EmptyListIndexException {
        if (input_split.length < 2) {
            throw new EmptyListIndexException();
        }
        try {
            Integer.parseInt(input_split[1]);
        } catch (NumberFormatException e) {
            throw new EmptyListIndexException();
        }
    }

    boolean parse(String input, TaskList tasks, Ui ui, Storage storage) {
        String[] input_split = input.split(" ");
        String command = input_split[0];

        try {
            switch (command) {
            case "exit":
                return false;

            case "bye":
                storage.save(tasks.saveFormat());
                ui.printBye();
                return false;

            case "list":
                ui.printList(tasks);
                return true;

            case "done":
                checkValidTaskIndex(input_split);
                Task theTask = tasks.get(Integer.parseInt(input_split[1]) - 1);
                theTask.isDone = true;
                ui.printDone(theTask);
                return true;

            case "todo":
            case "deadline":
            case "event":
                Task task = construct(input_split);
                if (task != null) {
                    tasks.add(task);
                    ui.printCreated(task, tasks);
                }
                return true;

            case "delete":
                checkValidTaskIndex(input_split);
                Task removedTask = tasks.remove(Integer.parseInt(input_split[1]) - 1);
                ui.printRemoved(removedTask, tasks);
                return true;

            default:
                throw new InputUnknownException();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("unable to save " + e.getMessage());
        }
        return true;
    }
}
