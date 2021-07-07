package myduke.util;

import java.util.Scanner;

import myduke.exception.DukeException;
import myduke.task.Task;


/**
 * A HashMap mapping a unique identifier to a throwable task constructor.
 */
public class TaskFactoryHashMap extends ThrowableFunctionHashMap<String, Scanner, Task, DukeException> {

}
