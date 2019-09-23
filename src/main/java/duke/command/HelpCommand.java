package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents the actions to execute when the command 'help" is
 * triggered.
 */

public class HelpCommand extends Command{

    private enum Guide {

        OVERALL("Hello welcome to our user guide! Please find below all the commands available! \n"
                + "- todo\n" + "- deadline\n" + "- event\n" + "- list\n" + "- done\n"
                + "- delete\n" + "- find\n" + "- snooze\n" + "- bye\n" + "- help\n"),
        TODO("Todo creates a todo task from the following String and adds it into the TaskList.\n"
                + "Example of usage:\n" + "todo Do Laundry\n" + "Expected outcome: \n"
                + "Got it. I've added this task:\n" + "  [T][X] Do Laundry\n"
                + "Now you have X tasks in this list."),
        DEADLINE("Deadline creates a deadline task from the following String with a by date into the TaskList.\n"
                + "Example of usage:\n" + "deadline Do Laundry /by 20/09/2019 2300\n" + "Expected outcome: \n"
                + "Got it. I've added this task:\n" + "  [D][X] Do Laundry (by: 20th of September, 11.00pm)\n"
                + "Now you have X tasks in this list."),
        EVENT("Event creates an event task from the following String with an at time into the TaskList.\n"
                + "Example of usage:\n" + "event Do Laundry /at 20/09/2019 2300\n" + "Expected outcome: \n"
                + "Got it. I've added this task:\n" + "  [E][X] Do Laundry (at: 20th of September, 11.00pm)\n"
                + "Now you have X tasks in this list."),
        LIST("Prints all the tasks in the tasklist.\n"
                + "Example of usage:\n" + "list\n" + "Expected outcome: \n"
                + "Here are the tasks in your list:\n" + "1. [T][+] Read a book\n"
                + "2. [D][x] Do Laundry (by: 20 of September 2019, 11.00pm)\n"
                + "3. [E][x] Gaming Convention (at: 20 of September 2019, 12.00pm)"),
        DONE("Find and marks the task at the provided index as completed.\n"
                + "Example of usage:\n" + "done 2\n" + "Expected outcome: \n"
                + "Nice! I've marked this task as done:\n" + "  [E][+] Do Laundry (at: 20th of September, 11.00pm)"),
        DELETE("Find and deletes the task at the provided index from the task list.\n"
                + "Example of usage:\n" + "delete 2\n" + "Expected outcome: \n"
                + "Noted. I've removed this task:\n" + "  [E][+] Do Laundry (at: 20th of September, 11.00pm)\n"
                + "Now you have X tasks in this list."),
        FIND("Find and prints a list of tasks the task list that contains the specified keyword.\n"
                + "Example of usage:\n" + "find College\n" + "Expected outcome: \n"
                + "Here are the tasks in your list:\n"
                + "  [E][+] College Welcome Tea (at: 18th of September 2019, 6.00pm)\n"
                + "  [T][X] Move out of Tembusu College`"),
        SNOOZE("Finds a deadline or event of a specified index in the task list and changes its timing.\n"
                + "Example of usage:\n" + "snooze 1 18/09/2019 2000\n" + "Expected outcome: \n"
                + "Nice! I've changed the timing of this task:\n"
                + "  [E][+] College Welcome Tea (at: 18th of September 2019, 8.00pm)"),
        BYE("Prints a goodbye message and closes the Virgil chatbot.\n"
                + "Example of usage:\n" + "bye\n" + "Expected outcome: \n"
                + "Bye! Hope to see you again soon!"),
        HELP("Prints a user guide explaining how to use Virgil as well as all the commands\n"
                + "available.\n"
                + "Example of usage:\n" + "help\n" + "Expected outcome: \n"
                + "Hello wlecome to our user guide! Please find below all the commands available!\n" +
                "- todo\n" +
                "- deadline\n" +
                "- event\n" +
                "- list\n" +
                "- done\n" +
                "- delete\n" +
                "- find\n" +
                "- snooze\n" +
                "- bye\n" +
                "- help"),
        WRONGINPUT("Sorry, I don't understand the command inputted. "
                + "Please find below all the commands available! \n"
                + "- todo\n" + "- deadline\n" + "- event\n" + "- list\n" + "- done\n"
                + "- delete\n" + "- find\n" + "- snooze\n" + "- bye\n" + "- help\n");

        private String userGuide;

        Guide(String userGuide){
            this.userGuide = userGuide;
        }

        public String getUserGuide(){
            return this.userGuide;
        }
    }

    private String guideRequest;

    public HelpCommand(String commandArray[]){
        if(commandArray.length == 1) {
            this.guideRequest = "overall";
        } else {
            this.guideRequest = commandArray[1];
        }
    }

    /**
     * Returns guides on various command based on user input.
     *
     * @param tasks
     * @param storage
     * @return
     */

    @Override
    public String execute(TaskList tasks, Storage storage){
        switch(guideRequest){

        case("overall"):
            return Guide.OVERALL.getUserGuide();

        case "todo":
            return Guide.TODO.getUserGuide();

        case "deadline":
            return Guide.DEADLINE.getUserGuide();

        case "event":
            return Guide.EVENT.getUserGuide();

        case "list":
            return Guide.LIST.getUserGuide();

        case "done":
            return Guide.DONE.getUserGuide();

        case "delete":
            return Guide.DELETE.getUserGuide();

        case "find":
            return Guide.FIND.getUserGuide();

        case "snooze":
            return Guide.SNOOZE.getUserGuide();

        case "bye":
            return Guide.BYE.getUserGuide();

        case "help":
            return Guide.HELP.getUserGuide();

        default:
            return Guide.WRONGINPUT.getUserGuide();
        }
    }

}
