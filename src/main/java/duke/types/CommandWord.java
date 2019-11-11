package duke.types;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.NotDoneCommand;
import duke.command.SortCommand;
import duke.command.TagCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public enum CommandWord {
    TODO {
        @Override
        public Command parseCommand(String commandContent) {
            return new AddCommand(Todo.parse(commandContent));
        }
    },
    DEADLINE {
        @Override
        public Command parseCommand(String commandContent) throws DukeException {
            return new AddCommand(Deadline.parse(commandContent));
        }
    },
    EVENT {
        @Override
        public Command parseCommand(String commandContent) throws DukeException {
            return new AddCommand(Event.parse(commandContent));
        }
    },
    LIST {
        @Override
        public Command parseCommand(String commandContent) throws DukeException {
            return new ListCommand(commandContent);
        }
    },
    FIND {
        @Override
        public Command parseCommand(String commandContent) throws DukeException {
            return new FindCommand(commandContent);
        }
    },
    DONE {
        @Override
        public Command parseCommand(String commandContent) throws DukeException {
            return new DoneCommand(commandContent);
        }
    },
    DELETE {
        @Override
        public Command parseCommand(String commandContent) throws DukeException {
            return new DeleteCommand(commandContent);
        }
    },
    BYE {
        @Override
        public Command parseCommand(String commandContent) throws DukeException {
            return new ByeCommand(commandContent);
        }
    },
    CLEAR {
        @Override
        public Command parseCommand(String commandContent) throws DukeException {
            return new ClearCommand(commandContent);
        }
    },
    NOTDONE {
        @Override
        public Command parseCommand(String commandContent) throws DukeException {
            return new NotDoneCommand(commandContent);
        }
    },
    TAG {
        @Override
        public Command parseCommand(String commandContent) {
            return new TagCommand(commandContent);
        }
    },
    SORT {
        @Override
        public Command parseCommand(String commandContent) throws DukeException {
            return new SortCommand(commandContent);
        }
    };

    public abstract Command parseCommand(String commandContent) throws DukeException;
}
