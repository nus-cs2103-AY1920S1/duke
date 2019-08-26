class Parser {
    private static void todoCheck(String[] tasks) throws DukeException {
        if (tasks.length <= 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static void deadlineCheck(String[] tasks, String userInput) throws DukeException {
        if (tasks.length <= 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (!userInput.contains("/by")) {
            throw new DukeException("OOPS!!! Deadline must include /by (date to complete task).");
        } else if (userInput.substring(userInput.indexOf("/by") + 3).equals("")
                || userInput.substring(userInput.indexOf("/by") + 4).equals("")) {
            throw new DukeException("OOPS!!! Please include the date to complete task after /by command.");
        }
    }

    private static void eventCheck(String[] tasks, String userInput) throws DukeException {
        if (tasks.length <= 1) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        } else if (!userInput.contains("/at")) {
            throw new DukeException("OOPS!!! Event must include /at (time of event).");
        } else if (userInput.substring(userInput.indexOf("/at") + 3).equals("")
                || userInput.substring(userInput.indexOf("/at") + 4).equals("")) {
            throw new DukeException("OOPS!!! Please include the time of event after /at.");
        }
    }

    static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else {
            String[] task = command.split(" ");
            String instruction = task[0];

            if (instruction.equals("done")) {
                int taskNumber = Integer.parseInt(task[1]);
                return new DoneCommand(taskNumber - 1);
            } else if (instruction.equals("delete")) {
                try {
                    int taskNumber = Integer.parseInt(task[1]);
                    return new DeleteCommand(taskNumber - 1);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! Provide a task number to delete!");
                }
            } else {
                switch (instruction) {
                case "todo": {
                    try {
                        todoCheck(task);
                        Task todo = new Todo(command.substring(5));
                        return new AddCommand(todo);
                    } catch (DukeException e) {
                        System.err.println("Something went wrong: " + e.getMessage());
                        break;
                    }
                }
                case "deadline": {
                    try {
                        deadlineCheck(task, command);
                        Task deadline = new Deadline(command.substring(9, command.indexOf("/by")),
                                Duke.dateFormatter(command.substring(command.indexOf("/by") + 4)));
                        return new AddCommand(deadline);
                    } catch (DukeException e) {
                        System.err.println("Something went wrong: " + e.getMessage());
                        break;
                    }
                }
                case "event": {
                    try {
                        eventCheck(task, command);
                        Task event = new Event(command.substring(9, command.indexOf("/at")),
                                Duke.dateFormatter(command.substring(command.indexOf("/at") + 4)));
                        return new AddCommand(event);
                    } catch (DukeException e) {
                        System.err.println("Something went wrong: " + e.getMessage());
                        break;
                    }
                }
                default: {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                }
            }
            return null;
        }
    }

}