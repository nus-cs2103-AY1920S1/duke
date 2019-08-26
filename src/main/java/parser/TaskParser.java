package parser;

import command.task.AddTaskCommand;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.function.BiFunction;
import java.util.function.Function;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;
import type.ErrorOutputter;
import ui.Ui;

public class TaskParser {

  private Parser<Task> parser;

  public TaskParser(Ui ui) {
    this.parser = new Parser<>(ui);
    addTaskSubParser(ToDoTask.TO_DO_SYMBOL, ToDoTask::new);
    addTaskSubParser(EventTask.EVENT_TASK_SYMBOL,
        TaskParser.generateTimedTaskParser(EventTask.ARGUMENTS_SEPARATOR,
            EventTask::new, ui::error
        ));
    addTaskSubParser(DeadlineTask.DEADLINE_TASK_SYMBOL,
        TaskParser.generateTimedTaskParser(DeadlineTask.ARGUMENTS_SEPARATOR,
            DeadlineTask::new, ui::error));
  }

  private void addTaskSubParser(char type, Function<String, Task> creator) {
    Function<String, Task> isDoneIntermediateParser = ParserUtils
        .generateFunctionSplittingAtFirstSpace((isDoneString, rest) -> {
          boolean isDone = Boolean.parseBoolean(isDoneString);
          Task task = creator.apply(rest);
          if (isDone) {
            task.markAsDone();
          }
          return task;
        });
    parser.addSubParser(String.valueOf(type), isDoneIntermediateParser);
  }

  public Task fromSerial(String serial) {
    return parser.parse(serial);
  }

  public static Function<String, Task> generateTimedTaskParser(
      String separator,
      BiFunction<String, LocalDateTime, Task> timedTaskConstructor,
      ErrorOutputter errorOutputter) {
    return ParserUtils.generateFunctionToParseTwoArguments(separator, (description, dateString) -> {
      try {
        return timedTaskConstructor.apply(description, DateParser.stringToDate(dateString));
      } catch (DateTimeParseException e) {
        errorOutputter.accept("Opps! I expected a date in the format: DD/MM/YYYY HHmm"
            + "\n\t(e.g. 31/01/2019 2359)");
        return null;
      }
    }, errorOutputter);
  }
}
