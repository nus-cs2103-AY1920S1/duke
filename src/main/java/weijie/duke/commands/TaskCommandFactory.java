package weijie.duke.commands;

import weijie.duke.exceptions.DukeDependencyNotFoundException;
import weijie.duke.exceptions.DukeException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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

            throw new DukeDependencyNotFoundException("☹ OOPS!!! This command is not working properly");

        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new DukeException("☹ OOPS!!! An unexpected error has occurred.");
        }
    }

    public void registerDependency(Object dependency) {
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
        Object[] dependencies = IntStream.range(0, constructorClasses.length)
                .boxed()
                .flatMap(index -> registeredDependencies.stream()
                        .filter(dependency -> constructorClasses[index].isInstance(dependency))
                        .limit(1))
                .toArray();

        return dependencies.length == constructorClasses.length ? dependencies : null;
    }
}
