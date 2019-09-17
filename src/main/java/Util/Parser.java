package util;

import command.Command;
import command.Instruction;
import command.ExitCommand;
import command.AddCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.ListCommand;
import command.UnknownCommand;

public class Parser {

    /** Parse the command according to user input
     * @param command user input
     * @return parse to corresponding command
     */
    public static Command parse(String command) {
        if (command.contains(Instruction.BYE.toString())) {
            return new ExitCommand(command);
        } else if (command.contains(Instruction.TODO.toString()) || command.contains(Instruction.EVENT.toString())
                || command.contains(Instruction.DEADLINE.toString())) {
            return new AddCommand(command);
        } else if (command.contains(Instruction.DELETE.toString())) {
            return new DeleteCommand(command);
        } else if (command.contains(Instruction.LIST.toString())) {
            return new ListCommand(command);
        } else if (command.contains(Instruction.DONE.toString())) {
            return new DoneCommand(command);
        } else {
            return new UnknownCommand(command);
        }
    }
}

