public class TodoCommand extends AddCommand {
    public TodoCommand(Todo task) {
        super(task);
    }

    public static String getHelp() {
        StringBuilder builder = new StringBuilder("Command: todo\n");
        builder.append("- Format: 'todo {description}'.\n");
        builder.append("- Description: Adds a todo task to the list.\n");
        builder.append("- Example: 'todo attending CS2103T lecture'");
        builder.append(" will add a new todo task which description is 'attending CS2103T lecture'.\n");

        return builder.toString();
    }
}
