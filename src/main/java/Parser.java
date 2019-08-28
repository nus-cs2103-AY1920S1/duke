public class Parser {
    TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }


    // Takes in a string, parses it and immediately runs the command as parsed.
    // Returns a String output for the UI.
    public String parse(String input) throws DukeException {
        /*
         * Check if command suffix to parse, split accordingly and run command
         */
        // "done" commands
        if (input.startsWith("done ")) {
            if (input.length() > 5) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(5));
                    Task item = this.taskList.get(taskIndex-1);
                    item.setDone();
                    return ("Nice! I've marked this task as done:\n  " + item);
                } catch (NumberFormatException e) {
                    throw new DukeException("The description of a done must be an integer.");
                }
            } else {
                throw new DukeException("The description of a done cannot be empty.");
            }
        }
        // "delete" commands
        else if (input.startsWith("delete ")) {
            // Process input
            if (input.length() > 7) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(7));
                    if (this.taskList.size() >= taskIndex && taskIndex > 0) {
                        String output = ("Noted. I've removed this task:\n  " + this.taskList.get(taskIndex - 1) +
                                "\nNow you have " + (this.taskList.size() - 1) + " tasks in the list.");
                        this.taskList.remove(taskIndex - 1);
                        return output;
                    }
                    else {
                        throw new DukeException("The integer entered for deletion is not valid.");
                    }
                } catch (NumberFormatException e) {
                    throw new DukeException("The description of a delete must be an integer.");
                }
            } else {
                throw new DukeException("The description of a delete cannot be empty.");
            }
        }
        // "to-do" commands
        else if (input.startsWith("todo")) {
            return addToList(input, TaskType.Todo);
        }
        // "deadline" commands
        else if (input.startsWith("deadline ")) {
            return addToList(input, TaskType.Deadline);
        }
        // "event" commands
        else if (input.startsWith("event ")) {
            return addToList(input, TaskType.Event);
        }
        // "list" command
        else if (input.equals("list")) {
            return this.taskList.getList();
        }
        // Other unrecognized commands
        else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    //Adds a list of a particular task type to the task list.
    public String addToList(String input, TaskType type) throws DukeException {
        Task task = null;
        // Process input string (Cut command suffix)
        switch(type) {
            case Todo:
                if (input.length() > 5) {
                    task = new Todo(input.substring(5));
                } else {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                break;
            case Deadline:
                if (input.length() > 9) {
                    task = new Deadline(input.substring(9));
                } else {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                break;
            case Event:
                if (input.length() > 6) {
                    task = new Event(input.substring(6));
                } else {
                    throw new DukeException("The description of an event cannot be empty.");
                }
                break;
        }
        this.taskList.add(task);
        return ("Got it. I've added this task:\n  " + task
                + "\nNow you have " + this.taskList.size() + " tasks in the list.");
    }
}
