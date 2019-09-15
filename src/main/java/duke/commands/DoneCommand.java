package duke.commands;

import java.io.IOException;
import java.util.List;


import duke.core.TaskList;
import duke.core.Ui;
import duke.core.Parser;

import duke.errors.DukeException;
import duke.errors.DukeExceptionType;

import duke.tasks.Task;


public class DoneCommand extends Command{

    private int index;

    private DoneCommand(int index){
        this.commandType = CommandType.DONE;
        this.index = index;
    }

    public static DoneCommand createDoneIfValid(String [] tokens) throws DukeException {
        try {
            int index = Integer.parseInt(tokens[1])-1;
            return new DoneCommand(index);
        } catch (NumberFormatException error) {
            throw new DukeException("Must be integer", DukeExceptionType.NOTINTEGER);
        }
    }


    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException, IOException {
        try {
            Task task = taskList.getTaskAt(index+1);
            boolean isDoneBefore = task.setDone();
            if (isDoneBefore) {
                throw new IllegalArgumentException("Task has already been done");
            }
            taskList.setDoneInList(this.index+1);

            List<String> inst = List.of("Nice! I've marked this task as done: ",
                    "  "+task.toString());
            ui.printInput(inst);


        } catch (IndexOutOfBoundsException error3) {
            ui.printOneLine(new DukeException("No such task", DukeExceptionType.MISSINGTASK).getMessage());
        } catch (IllegalArgumentException error2) {
            ui.printOneLine(new DukeException(error2.getMessage(), DukeExceptionType.GENERALMISTAKE).getMessage());
        }
    }

}
