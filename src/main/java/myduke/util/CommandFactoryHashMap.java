package myduke.util;

import myduke.command.Command;
import myduke.exception.DukeException;


/**
 * A HashMap mapping a unique identifier to a throwable Command constructor.
 */
public class CommandFactoryHashMap extends ThrowableFunctionHashMap<String, String, Command, DukeException> {

}
