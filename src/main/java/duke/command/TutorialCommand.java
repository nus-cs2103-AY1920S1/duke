package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

public class TutorialCommand extends Command {

    private String output = "";
    public static boolean firstTime = true;

    public TutorialCommand(String message) {
        super(message);
        if (message.equals("")) {
            output = "Hi! Welcome to the tutorial!\n\n" +
                    "I will guide you through the use of the program and its features!\n\n" +
                    "For now, try to create a todo list!\n\n" +
                    "You can do so by typing:\n" +
                    "todo <task that you want to add>\n\n" +
                    "Try it out! Once you're done, type tutorial 1 to continue.";
            return;
        }
        int index = Integer.parseInt(message);
        switch(index) {
            case 1 :
                output = "Well done! Congratulations on creating your first todo task!\n\n" +
                        "Now, you can try creating an event or deadline!\n\n" +
                        "You can do so by typing the following:\n" +
                        "event <name of task> /at DD/MM/YYYY HHMM\n" +
                        "For example : event pizza party /at 01/06/2019 1800\n\n" +
                        "Type the following for deadline:\n" +
                        "deadline <name of task> /by DD/MM/YYYY HHMM\n\n" +
                        "Try it out! Once you're done, type tutorial 2 to continue.";
                break;
            case 2 :
                output = "Well done! That is how you create event or deadline!\n\n" +
                        "You can show your list of tasks by typing the following:\n" +
                        "list\n" +
                        "Try it out! Once you're done, type tutorial 3 to continue.";
                break;
            case 3 :
                output = "Those are your list of tasks that you have, with the appropriate index.\n\n" +
                        "You can mark task as done by typing the following:\n" +
                        "done <index>\n\n" +
                        "You can also delete a task by typing the following:\n" +
                        "delete <index>\n\n" +
                        "Try it out! Once you're done, type tutorial 4 to continue.";
                break;
            case 4 :
                output = "You now know how to update and change the list accordingly, well done!\n\n" +
                        "Did you know that you can delete multiple tasks at once by typing:\n" +
                        "massdelete <indexes split by a space>\n" +
                        "For example : massdelete 1 3 5 6\n\n" +
                        "Go ahead and try it out!\n\n" +
                        "Once you're done, type tutorial 5 to continue.";
                break;
            case 5 :
                output = "Well done! This feature is helpful when you want to delete many tasks at once.\n\n" +
                        "You can also delete the whole list by typing:\n" +
                        "clearall\n\n" +
                        "Try it out! Type tutorial 6 continue.";
                break;
            case 6 :
                output = "Amazing! This will be helpful when you want to start a new list.\n\n" +
                        "There is a command that lets you return a list containing only tasks that contains specific keyword!\n\n" +
                        "You can do so by typing the following:\n" +
                        "find <keyword>\n\n" +
                        "Try it out! Type tutorial 7 to proceed to the next step.";
                break;
            case 7 :
                output = "Well done! That about concludes the tutorial.\n\n" +
                        "You can type 'help' to obtain the list of commands.\n\n" +
                        "Have fun using the app!";
                break;
            default :
                output = "No such tutorial! Please only type numbers from 1 to 7";
        }
    }
    /**
     * Used to execute the command and modify the list of tasks accordingly.
     *
     * @param listOfTasks List of tasks to be modified according to the input.
     * @param storage     Used to modify the files in the hard drive.
     * @param ui          Prints out all the messages.
     * @throws Exception For when there are any errors when executing the method.
     */
    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        if (firstTime) {
            for (int i = listOfTasks.size() - 1; i >= 0; i --) {
                listOfTasks.removeTask(listOfTasks.get(i));
            }
            storage.updateTaskList(listOfTasks.getTasks());
            storage.writeToFile();
            firstTime = false;
        }
    }

    public String toString() {
        return output;
    }
}
