package duke.command.help;

public class HelpDoc {
    public static final String HELP_DOC = "Here is the full list of commands:\n"
            + "\n"
            + "Below are the commands to add various tasks. Feel free to use simple language\n"
            + "like 'mon 2359' or 'thursday'to specify the date. Adding a time is optional.\n"
            + "If you require a more specific date or time, follow the format [dd/mm/yyyy hhmm].\n"
            + "1. todo [description] - adds a todo with no time restrictions\n"
            + "2. deadline [description] [datetime] - adds a deadline to be completed by a certain time\n"
            + "3. event [description] [datetime] - adds an event that occurs at a specific time\n"
            + "4. after [description] [datetime] - adds a doafter that is done after a certain time\n"
            + "5. within [description] [datetime] to [datetime] - adds a dowithin that is done within a timeframe\n"
            + "\n"
            + "Below are other generic commands.\n"
            + "1. bye - exits the program\n"
            + "2. delete [index] - deletes a task at a particular index\n"
            + "3. delete all - deletes all tasks\n"
            + "4. done [index] - marks a task as done at a particular index\n"
            + "5. list - lists all your tasks\n"
            + "6. sort date - sorts our tasks by date\n"
            + "7. sort name - sorts your tasks by name\n"
            + "8. undo - undo your previous action\n"
            + "\n"
            + "May the force be with you.";

}
