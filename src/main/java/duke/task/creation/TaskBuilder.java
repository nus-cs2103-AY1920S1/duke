package duke.task.creation;

import duke.task.Task;
import error.task.TaskCreationException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to build tasks according to the TaskType enum and arguments.
 */
class TaskBuilder {
    /**
     * Uses Java reflection to parse the arguments according to the specifications of the TaskType and return
     * an instance of the corresponding task.
     * @param type TaskType corresponding to the type of task to be created.
     * @param arguments arguments to be used in the creating of the task instance.
     * @return a task instance matching the TaskType.
     * @throws TaskCreationException if task fails to be created.
     */
    static Task buildTask(TaskType type, TaskArguments arguments) throws TaskCreationException {
        // get constructor and parameters type of particular task
        Class<?> task = type.task;
        Constructor<?> constructor = task.getConstructors()[0];
        Class<?>[] parameters = constructor.getParameterTypes();

        // get arguments array
        Object[] argumentArray = getArgumentArray(arguments);

        return invokeTaskConstructor(constructor, argumentArray);
    }

    private static Object[] getArgumentArray(TaskArguments taskArguments) throws TaskCreationException {
        if (taskArguments.getDetails().equals("")) {
            throw new TaskCreationException("Please enter some task details.");
        }

        List<Object> argsList = new ArrayList<>();
        argsList.add(taskArguments.getDetails());
        argsList.addAll(taskArguments.getDateTimes());

        return argsList.toArray();
    }

    private static Task invokeTaskConstructor(Constructor<?> constructor, Object[] arguments)
            throws TaskCreationException {
        try {
            return (Task) constructor.newInstance(arguments);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new TaskCreationException("Unable to create task");
        }
    }
}
