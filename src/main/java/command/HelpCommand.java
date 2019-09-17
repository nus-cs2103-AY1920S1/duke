package command;

import main.Storage;
import main.Ui;
import task.InsufficientTaskArgumentException;
import task.TaskList;

public class HelpCommand implements Command {

    public HelpCommand() {

    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws InsufficientTaskArgumentException {
        String helpText = "    ____________________________________________________________\n"
                + "    | command    | arguments   | description                          |\n"
                + "    ____________________________________________________________\n"
                + "    | todo            | name            | adds todo to your task list. |\n"
                + "    ____________________________________________________________\n"
                + "    | deadline      | name \"/by\"  | adds a deadline to your task|\n"
                + "    |                     | date             | list. date format: dd/mm/yyyy|\n"
                + "    ____________________________________________________________\n"
                + "    | event           | name \"/at\"   | adds an event to your task  |\n"
                + "    |                     | date time     | list. date format: dd/mm/yyyy|\n"
                + "    |                     |                     | time format: 24h-clock        |\n"
                + "    ____________________________________________________________\n"
                + "    | find             | name            | searches your task list and  |\n"
                + "    |                    |                      | returns the tasks that          |\n"
                + "    |                    |                      | matches the param name.  |\n"
                + "    ____________________________________________________________\n"
                + "    | delete         | tasknumber  | deletes the task with the     |\n"
                + "    |                    |                      | tasknumber.                        |\n"
                + "    ____________________________________________________________\n"
                + "    | list              | none             | displays all tasks in your      |\n"
                + "    |                    |                      | task list.                                |\n"
                + "    ____________________________________________________________\n"
                + "    | help            | none         | shows summary of commands|\n"
                + "    |                    |                  | and their descriptions.             |\n"
                + "    ____________________________________________________________\n"
                + "    | done            | tasknumber | marks task with the task      |\n"
                + "    |                     |                     | number as done.                  |\n"
                + "    ____________________________________________________________\n"
                + "    | bye              | none             | closes the application.         |\n"
                + "    ____________________________________________________________\n"
                + "    | undo            | none            | undo the previous command|\n"
                + "    |                     |                     | that changed the tasklist.    |\n"
                + "    ____________________________________________________________";
        ui.nextLine(helpText);
        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
