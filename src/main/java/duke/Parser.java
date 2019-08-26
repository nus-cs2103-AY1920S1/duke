package duke;

public class Parser {
    /**
     * Handles the various commands.
     * @param command String
     * @return boolean
     */
    public boolean handleCommand(String command) { 
        try {
            String keyword = command.split(" ")[0];
            switch (keyword) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                return true;
            case "list":
                this.handleListCommand();
                break;
            case "done":
                this.handleDoneCommand(command);
                break;
            case "todo":
                this.handleTodoCommand(command);
                break;
            case "deadline":
                this.handleDeadlineCommand(command);
                break;
            case "event":
                this.handleEventCommand(command);
                break;
            case "delete":
                this.handleDeleteCommand(command);
                break;
            default:
                throw new InvalidCommandException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return false;
        } catch (DukeException e) {
            System.out.println(e);
            return false;
        } catch (ParseException e) {
            System.out.println("Error parsing the date in the command");
            return false;
        }
    }

    /**
     * Handle List command.
     */
    private void handleListCommand() {
        System.out.printf("    Here are the tasks in your list:\n");
        int length = this.tasks.getSize();
        for (int i = 0; i < length; i++) {
            this.ui.prettyPrint4(String.format("%d.%s\n", i + 1, this.tasks.get(i)));
        }
    }

    /**
     * Handle Done command.
     * @param command String
     * @throws InvalidIndexException if the task to modify is invalid
     * @throws InvalidCommandException if the Done command is not correct
     */
    private void handleDoneCommand(String command) throws InvalidIndexException, InvalidCommandException {
        String[] doneArr = command.split(" ");
        if (doneArr.length != 2) {
            throw new InvalidCommandException("\u2639 OOPS!!! Done command should only have a valid index");
        }
        int indexToEdit = Integer.parseInt(doneArr[1]);
        if (indexToEdit > this.tasks.getSize() || indexToEdit < 1) {
            throw new InvalidIndexException("\u2639 OOPS!!! Trying to modify invalid task");
        }
        Task task = this.tasks.get(indexToEdit - 1);
        task.markDone();
        this.ui.prettyPrint4(String.format("Nice! I've marked this task as done:"));
        this.ui.prettyPrint6(String.format("%s", task)); 
        this.writeListToDisk();
    }

    /**
     * Handle Delete command.
     * @param command String
     * @throws InvalidIndexException if the index provided is invalid
     * @throws InvalidCommandException if the arguments provided are invalid
     */
    private void handleDeleteCommand(String command) throws InvalidIndexException, InvalidCommandException {
        String[] deleteArr = command.split(" ");
        if (deleteArr.length != 2) {
            throw new InvalidCommandException("\u2639 OOPS!!! Done command should only have a valid index");
        }
        int indexToEdit = Integer.parseInt(deleteArr[1]);
        if (indexToEdit > this.tasks.getSize() || indexToEdit < 1) {
            throw new InvalidIndexException("\u2639 OOPS!!! Trying to delete invalid task");
        }
        Task removedTask = this.tasks.remove(indexToEdit - 1);
        System.out.println("    Noted. I've removed the task:");
        this.ui.prettyPrint6(String.format("%s\n", removedTask)); 
        System.out.printf("    Now you have %d tasks in the list.\n", this.tasks.getSize()); 
        this.writeListToDisk();
    }

    /**
     * Handle Deadline command.
     * @param command String
     * @throws InvalidTaskArgumentException if Deadline arguments are invalid
     * @throws ParseException if it fails to parse the date
     */
    private void handleDeadlineCommand(String command) 
        throws InvalidTaskArgumentException, ParseException {
        String[] commandArr = command.replace("deadline", "").trim().split("/by ");
        if (commandArr.length != 2) {
            throw new InvalidTaskArgumentException("\u2639 OOPS!!! Deadline must have a description and date");
        }
        Task taskToAdd = new Deadline(commandArr[0].trim(), DateUtil.parseStringToDate(commandArr[1].trim()));
        this.addToList(taskToAdd);
    }

    /**
     * Handle Event command.
     * @param command String
     * @throws InvalidTaskArgumentException if Event arguments are invalid
     * @throws ParseException if it fails to parse the date
     */
    private void handleEventCommand(String command) 
        throws InvalidTaskArgumentException, ParseException {
        String[] commandArr = command.replace("event", "").trim().split("/at ");
        if (commandArr.length != 2) {
            throw new InvalidTaskArgumentException("\u2639 OOPS!!! Event must have a description and date");
        }
        Task taskToAdd = new Event(commandArr[0].trim(), DateUtil.parseStringToDate(commandArr[1].trim()));
        this.addToList(taskToAdd);
    }

    /**
     * Handle ToDo command.
     * @param command String
     * @throws InvalidTaskArgumentException if ToDo arguments are invalid
     */
    private void handleTodoCommand(String command) throws InvalidTaskArgumentException {
        String name = command.replace("todo", "").trim();
        if ("".equals(name)) {
            throw new InvalidTaskArgumentException("\u2639 OOPS!!! The description of a todo cannot be empty."); 
        }
        this.addToList(new ToDo(name));
    }

    /**
     * Adds task to list and performs other actions.
     */
    private void addToList(Task task) {
        this.tasks.add(task);
        this.ui.sendAddTaskAck(task, this.tasks.getSize());
        this.writeListToDisk();
    }
}
