public class HelpCommand {
    public static String Help() {
        String output = "Hello, Welcome to Duke!\n"
                + "Use the commands below to utilise Duke:\n" 
                + "To set Todo: \n" 
                + "\t\tTodo type_todo_here\n"
                + "To set Deadline: \n"
                + "\t\tDeadline type_deadline_here /by dd/mm/yyyy HHmm\n"
                + "To set Event: \n"
                + "\t\tEvent type_event_here /at dd/mm/yyyy HHmm\n"
                + "Alternatively, use:\n"
                + "\t\tlist\n\t\tdelete\n\t\tdone\n\t\tfind\n";
        return output;
    }
    
    public static String Invalid () {
        String output = "I'm sorry, please try again!";
        return output;
    }
}
