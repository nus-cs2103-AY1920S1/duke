package weijie.duke.commands;

import weijie.duke.exceptions.DukeException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class TaskCommandFactory {

    private List<Object> registeredDependencies;

    public TaskCommandFactory() {
        registeredDependencies = new ArrayList<>();
    }

    public ITaskCommand tryMakeCommand(String command) throws DukeException {
        Class<? extends ITaskCommand> commandClass = CommandList.getCommandMap()
                .getOrDefault(command, CommandList.INVALID_COMMAND.getCommandClass());

        try {
            Constructor<?>[] constructors = commandClass.getConstructors();
            Arrays.sort(constructors, (constructor1, constructor2) ->
                    Integer.compare(constructor2.getParameterCount(), constructor1.getParameterCount()));

            for (Constructor<?> constructor : constructors) {
                Class<?>[] classes = constructor.getParameterTypes();
                Object[] args = generateConstructorDependencies(classes);

                if (args != null) {
                    return (ITaskCommand) constructor.newInstance(args);
                }
            }

            throw new DukeException("dependency not found");

        } catch (DukeException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            throw new DukeException("help");
        }
    }

    public void registerDependency(Object dependency) {
        registeredDependencies.add(dependency);
    }

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
