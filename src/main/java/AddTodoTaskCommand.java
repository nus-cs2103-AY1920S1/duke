class AddTodoTaskCommand extends AddCommand {
    public AddTodoTaskCommand(String command) {
        super(command);
    }

    protected Task instantiateTask() {
        String todoDescription = command;
        if (todoDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of a todo cannot be empty.");
        }
        return new Todo(todoDescription);
    }
}
