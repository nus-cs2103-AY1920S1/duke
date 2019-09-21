package weomucat.doko.task;

/**
 * A recurring task is a task that repeats itself forever, until deleted.
 */
abstract class RecurringTask extends Task {

  /**
   * Creates a recurring task with a description.
   *
   * @param description a description of the task
   */
  RecurringTask(String description) {
    super(description);
  }

  abstract RecurringTask getNextRecurrence();

  abstract void removeRecurrence();
}
