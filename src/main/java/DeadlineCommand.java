public class DeadlineCommand extends AddCommand {
    public DeadlineCommand(Deadline task) {
        super(task);
    }

    /**
     * Explains DeadlineCommand.
     */
    public static String getHelp() {
        StringBuilder builder = new StringBuilder("Command: deadline\n");
        builder.append("- Format: 'deadline {description} /by {full_date}'.");
        builder.append(" full_date has to be of the form '{date}/{month}/{year} {hour}:{minutes}:{seconds}'.\n");
        builder.append("- Description: Adds a deadline task to the list.\n");
        builder.append("- Example: 'deadline CS2103T Project /by 12/09/2019 23:59:00'");
        builder.append(" will add a new deadline task which description is 'CS2103T Project'");
        builder.append(" and the task is due on 12 September 2019 at 11.59 p.m.\n");

        return builder.toString();
    }
}
