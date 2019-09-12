public class EventCommand extends AddCommand {
    public EventCommand(Event task) {
        super(task);
    }

    /**
     * Explains EventCommand.
     */
    public static String getHelp() {
        StringBuilder builder = new StringBuilder("Command: event\n");
        builder.append("- Format: 'event {description} /at {full_date}'.");
        builder.append(" full_date has to be of the form '{date}/{month}/{year} {hour}:{minutes}:{seconds}'.\n");
        builder.append("- Description: Adds an event task to the list.\n");
        builder.append("- Example: 'event computing career fair /at 11/09/2019 13:30:00'");
        builder.append(" will add a new event task which descripton is 'computing career fair'");
        builder.append(" and the event is happening on 11 September 2019 at 1.30 p.m.\n");

        return builder.toString();
    }
}
