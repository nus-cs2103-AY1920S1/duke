public class Parser {
    public static void parseDeadline(String input, TaskList t,Ui ui,Storage s) throws DukeException {
        String deadlineDetails = input.substring(9);
        String[] arr = deadlineDetails.split("/");
        try {
            String details = arr[1].substring(2);
            Deadline deadline = new Deadline(arr[0], details);
            if (arr[1].length() == 0) {
                throw new DeadlineDetailsEmptyException("OOPS!!! Deadline details cannot be empty");
            }
            t.addTask(deadline);
            s.writeListToFile(t);
            ui.showAdded();
            System.out.println(deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.inputWrong();
        }
    }

    public static void parseEvent(String input, TaskList t,Ui ui,Storage s) throws DukeException {
        String eventDetails = input.substring(6);
        String[] arr = eventDetails.split("/");
        try {
            String details = arr[1].substring(2);
            Event event = new Event(arr[0],details);

            if (arr[1].length() == 0) {
                throw new EventDetailsEmptyException("OOPS!!! Event details cannot be empty.");
            }
            t.addTask(event);
            s.writeListToFile(t);
            ui.showAdded();
            System.out.println(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.inputWrong();
        }
    }

    public static void parseToDo(String input, TaskList t,Ui ui,Storage s) throws DukeException {
        if (input.length() == 4) {
            throw new TodoEmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
        }
        ToDo todo = new ToDo(input.substring(5));
        t.addTask(todo);
        s.writeListToFile(t);
        ui.showAdded();
        System.out.println(todo);
    }

    public static void parseDone(String input, TaskList t,Storage s) throws DukeException {
        String[] arr = input.split(" ");
        int number = Integer.parseInt(arr[1]);
        if (number > t.getCommandList().size() + 1) {
            throw new TaskNotFoundException("OOPS!!! Task number is incorrect");
        }
        t.getCommandList().get(number - 1).complete();
        s.writeListToFile(t);
    }

    public static void parseDelete(String input, TaskList t,Ui ui, Storage s) throws DukeException {
        String[] arr = input.split(" ");
        int number = Integer.parseInt(arr[1]);
        if (number > t.getCommandList().size() + 1) {
            throw new TaskNotFoundException("OOPS!!! Task number is incorrect");
        }
        ui.showDeleted();
        System.out.println(t.getCommandList().get(number - 1));
        t.deleteTask(number);
        s.writeListToFile(t);
    }
}

