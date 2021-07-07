package weijie.duke.commands;

import org.apache.commons.lang3.ClassUtils;
import weijie.duke.exceptions.DukeDependencyNotFoundException;
import weijie.duke.exceptions.DukeException;
import weijie.duke.exceptions.DukeIllegalArgumentException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Class which handles creation logic of commands, and the management of command dependencies.
 */
public class TaskCommandFactory {

    private List<Object> registeredDependencies;
    private Map<String, Class<? extends ITaskCommand>> commandMap;

    public TaskCommandFactory(Map<String, Class<? extends ITaskCommand>> commandMap) {
        this.registeredDependencies = new ArrayList<>();
        this.commandMap = commandMap;
    }

    /**
     * <p>
     *     Compares input command with a list of commands and returns the matching command, injecting any necessary
     *     constructor dependencies if they have been registered.
     * </p>
     * @param command Command invoked.
     * @return The matching command if any, and an InvalidCommand otherwise.
     * @throws DukeException If dependency injection fails, ie if a required dependency is not added.
     */
    public ITaskCommand tryMakeCommand(String command) throws DukeException {
        Class<? extends ITaskCommand> commandClass =
                commandMap.getOrDefault(command, CommandList.INVALID_COMMAND.getCommandClass());

        try {
            Constructor<?>[] constructors = commandClass.getDeclaredConstructors();
            Arrays.sort(constructors, (constructor1, constructor2) ->
                    Integer.compare(constructor2.getParameterCount(), constructor1.getParameterCount()));

            for (Constructor<?> constructor : constructors) {
                Class<?>[] classes = constructor.getParameterTypes();
                Object[] args = generateConstructorDependencies(classes);

                if (args != null) {
                    return (ITaskCommand) constructor.newInstance(args);
                }
            }

            throw new DukeDependencyNotFoundException("This command is missing a dependency.");

        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * <p>
     *     Registers the input object as a dependency for tasks. If any included command has the type of the input
     *     object as a constructor parameter, or has a constructor parameter that the input object can be assigned to,
     *     then the command will be automatically constructed with the dependency when created.
     *  </p>
     * @param dependency Dependency to be registered.
     * @throws DukeIllegalArgumentException When registered dependency is a primitive or a wrapper class.
     */
    public void registerDependency(Object dependency) throws DukeIllegalArgumentException {
        if (ClassUtils.isPrimitiveOrWrapper(dependency.getClass())) {
            throw new DukeIllegalArgumentException("Cannot register boxed/unboxed primitive dependencies.");
        }
        registeredDependencies.add(dependency);
    }

    /**
     * <p>
     *     Compares an array of classes against the registered dependencies and outputs the matching dependencies in
     *     an array.
     * </p>
     * @param constructorClasses Array of classes corresponding to a Command's constructor parameter types.
     * @return Object array of concrete dependencies matching the constructor, or null if such an array cannot
     *         be found.
     */
    private Object[] generateConstructorDependencies(Class<?>[] constructorClasses) {

        Object[] dependencies = Arrays.stream(constructorClasses)
                .flatMap(paramClass -> registeredDependencies.stream()
                        .filter(paramClass::isInstance)
                        .limit(1))
                .toArray();

        return dependencies.length == constructorClasses.length ? dependencies : null;
    }
}
